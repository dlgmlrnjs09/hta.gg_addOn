package gg.hta.lol.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import gg.hta.lol.service.MailService;
import gg.hta.lol.vo.AuthVo;

@Controller
public class MailController { 
    @Autowired private MailService service;

    // 회원가입시 인증번호 전송
    @GetMapping(value = "/member/email/{id}/{email}", produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String mailSend(@PathVariable("id")String id, @PathVariable("email")String email) {
    	StringBuffer sb = new StringBuffer();
    	// 메일 전송 성공시
    	try {
			service.regist(new AuthVo(id, null), email); // Autu테이블에 id, null, 이메일 추가
			sb.append("<data>");
			sb.append("<result>success</result>");
			sb.append("</data>");
			return sb.toString(); // return success
		}
    	// 메일 전송 실패시
    	catch (Exception e) {
			e.printStackTrace();
			sb.append("<data>");
				sb.append("<result>fail</code>");
			sb.append("</data>");
			return sb.toString(); // return fail
		}
    }
    @GetMapping(value = "/member/code/{id}", produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String code(@PathVariable("id")String id) {
    	StringBuffer sb = new StringBuffer();
    	try {
			String key = service.check(id);
			sb.append("<data>");
				sb.append("<code>");
					sb.append(key);
				sb.append("</code>");
			sb.append("</data>");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("<data>");
				sb.append("<code>fail</code>");
			sb.append("</data>");
			return sb.toString();
		}
    }

    // 인증메일 전송 메소드
    @GetMapping(value = "/member/idSearch/{email}", produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String mailSend(@PathVariable("email")String email) {
    	StringBuffer sb = new StringBuffer();
    	// 인증메일 전송 성공
    	try {
    		// 메일에 daum이 포함되어 있으면
    		if (email.substring(email.lastIndexOf("@")).trim().equals("@daum")) {
    			service.idSearch(email+".net"); // 메일에 .net append 후 인증메일 전송
    		}else {
    			service.idSearch(email+".com"); // 메일에 .com append 후 인증메일 전송
    		}
			sb.append("<data>");
				sb.append("<result>success</result>");
			sb.append("</data>");
			return sb.toString(); // return success
		}
    	// 인증메일 전송 실패
    	catch (Exception e) {
			e.printStackTrace();
			sb.append("<data>");
				sb.append("<result>fail</code>");
			sb.append("</data>");
			return sb.toString(); // return false
		}
    }

    // 인증번호 일치 확인 메소드
    @GetMapping(value = "/member/idSearchCode/{email}", produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String SearchCode(@PathVariable("email")String email) {
    	StringBuffer sb = new StringBuffer();
    	try {
    		if (email.substring(email.lastIndexOf("@")).trim().equals("@daum")) {
    			email=email+".net";
    		}else {
    			email=email+".com";
    		}
    		String code = service.idSeachCheck(email).getCode(); // 이메일로 쿼리 검색하여 코드값 저장
			String username = service.idSeachCheck(email).getUsername(); // 이메일로 쿼리 검색하여 username 저장
			sb.append("<data>");
				sb.append("<code>");
					sb.append(code);
				sb.append("</code>");
				sb.append("<id>");
					sb.append(username);
				sb.append("</id>");
			sb.append("</data>");
			return sb.toString(); // return code, username
		} catch (Exception e) { // exception 발생시 return fail
			e.printStackTrace();
			sb.append("<data>");
				sb.append("<code>fail</code>");
			sb.append("</data>");
			return sb.toString();
		}
    }
    @GetMapping(value = "/member/pwdSearch/{id}/{email}", produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String PwdmailSend(@PathVariable("id")String id, @PathVariable("email")String email) {
    	StringBuffer sb = new StringBuffer();
    	try {
    		if (email.substring(email.lastIndexOf("@")).trim().equals("@daum")) {
    			service.pwdSearch(id, email+".net");
    		}else {
    			service.pwdSearch(id, email+".com");
    		}
			sb.append("<data>");
				sb.append("<result>success</result>");
			sb.append("</data>");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("<data>");
				sb.append("<result>fail</code>");
			sb.append("</data>");
			return sb.toString();
		}
    }
}
