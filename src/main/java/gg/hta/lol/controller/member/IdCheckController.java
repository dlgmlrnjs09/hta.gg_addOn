package gg.hta.lol.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import gg.hta.lol.service.MemberService;
import gg.hta.lol.vo.MemberVo;

@Controller
public class IdCheckController {
	@Autowired MemberService service;

	// 아이디 중복체크
	@GetMapping(value = "/member/idCheck/{id}", produces = "application/xml;charset=utf-8")
	@ResponseBody
	public String select(@PathVariable("id")String id) {
		//http://localhost:9090/member/idCheck/test11
		boolean using = false;
		MemberVo vo = service.selectOne(id); // 아이디로 member테이블에서 데이터 받아오기
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		if(vo!=null) { // 입력받은 아이디로 만들어진 아이디가 잇다면
			using = true;
			sb.append("<using>" + using +"</using>"); // return true
		}
		else { // 입력받은 아이디로 만들어진 아이디가 없다면
			sb.append("<using>" + using +"</using>");	// return false
		}
		sb.append("</data>");
		return sb.toString();
	}
}
