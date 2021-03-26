package gg.hta.lol.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import gg.hta.lol.service.MemberService;
import gg.hta.lol.vo.MemberVo;

@Controller

public class EmailCheckController {
	@Autowired MemberService service;
	//http://localhost:8119/member/emailCheck/test@test
	@GetMapping(value = "/member/emailCheck/{email}", produces = "application/xml;charset=utf-8")
	@ResponseBody
	// 해당 이메일로 가입여부 체크
	public String select(@PathVariable("email")String email) {
		boolean using = false;
		if (email.substring(email.lastIndexOf("@")).trim().equals("@daum")) { // email값에 공백제외 @daum이 포함되어 있을때
			email+=".net"; // email에 .net추가
		}else {
			email+=".com"; // email에 .com추가
		}
		List<MemberVo> list = service.emailList(email); // 입력받은 email로 select하여 return값을 list에 저장
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		if(!list.isEmpty()) { // list가 비어있지 않으면 (해당 이메일로 가입한 계정이 존재하면)
			using = true;
			sb.append("<using>" + using +"</using>"); // return true
		}
		else {
			sb.append("<using>" + using +"</using>");	// return false
		}
		sb.append("</data>");
		return sb.toString();
	}
	@GetMapping(value = "/member/emailCheck/{id}/{email}", produces = "application/xml;charset=utf-8")
	@ResponseBody
	public String select(@PathVariable("id")String id, @PathVariable("email")String email) {
		boolean using = false;
		if (email.substring(email.lastIndexOf("@")).trim().equals("@daum")) {
			email+=".net";
		}else {
			email+=".com";
		}
		MemberVo vo = service.selectOne(id, email);
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		if(vo!=null) {
			using = true;
			sb.append("<using>" + using +"</using>");
		}
		else {
			sb.append("<using>" + using +"</using>");		
		}
		sb.append("</data>");
		return sb.toString();
	}
}
