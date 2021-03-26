package gg.hta.lol.controller.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;

import gg.hta.lol.dao.MemberDao;
import gg.hta.lol.security.CustomUserDetailService;
import gg.hta.lol.service.MemberService;
import gg.hta.lol.util.NaverLoginBO;
import gg.hta.lol.util.PageUtil;
import gg.hta.lol.vo.AuthVo;
import gg.hta.lol.vo.MemberVo;
import gg.hta.lol.vo.VisitVo;

@Controller
public class LoginController {
	@Autowired private MemberService service;
	@Autowired private MemberDao dao;
	@Autowired private CustomUserDetailService serviceUser;
	/* NaverLoginBO */
	@Autowired private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	@RequestMapping(value = "/member/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session); // 네이버 인증 url 받아오기
		model.addAttribute("url", naverAuthUrl);
		return ".header2.member.login";
	}
	@RequestMapping(value = "/member/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request) throws IOException, ParseException {
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state); // 인증토큰 받아오기
		apiResult = naverLoginBO.getUserProfile(oauthToken); // 로그인한 사용자 정보 받아오기
		/** apiResult json 
		{"resultcode":"00",
		"message":"success",
		"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		**/
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		String id = (String)response_obj.get("id");
		String email = (String)response_obj.get("email");
		String nickname = (String)response_obj.get("nickname");	
		MemberVo member = new MemberVo();
		UUID uuid = UUID.randomUUID();
		member.setUsername("NAVER_"+id); // vo에 DB에 저장할 아이디 삽입
		member.setPassword(uuid.toString()); // vo에 DB에 저장할 무작위 비밀번호(밀리세컨드값) 삽입
		member.setEmail(email); // vo에 저장할 email값 삽입
		member.setNickname(nickname); // vo에 저장할 닉네임 삽입
		if(service.selectOne(member.getUsername()) == null) { // 중복되지 않은 아이디라면
			dao.insert(new AuthVo(member.getUsername(), code)); // insert auth table
			service.insert(member); // insert member table
		}

		// 로그인
		UserDetails userDetail = serviceUser.loadUserByUsername("NAVER_"+id); // db에서 유저정보 불러오기
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, member.getPassword(), userDetail.getAuthorities()); // 시큐리티 인증 객체 생성
		SecurityContext securityContext = SecurityContextHolder.getContext(); // 인증을 위한 SecurityContext 객체 생성
	    securityContext.setAuthentication(authentication); // 인증값 삽입
	    session = request.getSession(true); // 세션 얻어오기
	    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext); // 세션에 인증값 삽입
	    return ".header.home"; // 메인페이지로 이동
	}
	@GetMapping("/member/id")
	public String id() {
		return ".header2.member.idSearch";
	}
	@GetMapping("/member/pwd")
	public String pwd() {
		return ".header2.member.pwdSearch";
	}
	// 비밀번호 변경
	@PostMapping("/member/pwdChange")
	public String change(String username, String password) {
		int n = service.updatePwd(username, password);
		if(n>0) {
			return ".header2.member.login";
		}else {
			return ".header2.member.error";
		}
	}
	@GetMapping("/member/all/main")
	public String all() {
		return ".header.home";
	}
	@GetMapping("/member/member/main")
	public String member() {
		return ".header.home";
	}
	@GetMapping("/member/admin/main")
	public String admin() {
		return ".header.home";
	}
	@GetMapping("/member/admin/connection")
	public String connection(@RequestParam(value="pageNum", defaultValue = "1")int pageNum, Model m) {
		return ".adminpage.connection";
	}
	@GetMapping(value = "/member/admin/connectionList", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	public List<VisitVo> connectionList() {
		List<VisitVo> list = service.visitList();
		return list;
	}
}
