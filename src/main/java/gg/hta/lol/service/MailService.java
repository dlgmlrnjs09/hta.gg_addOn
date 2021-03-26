package gg.hta.lol.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import gg.hta.lol.dao.MemberDao;
import gg.hta.lol.util.MailHandler;
import gg.hta.lol.vo.AuthVo;
import gg.hta.lol.vo.MemberVo;

@Service
public class MailService {
	@Autowired private JavaMailSender mailSender;
	@Autowired private MemberDao dao;

	// 회원가입 인증번호 전송 후 auth값 insert
	public void regist(AuthVo vo, String email) throws Exception {
//      String key = new TempKey().generateKey(30);
		dao.AuthDelte(vo.getUsername()); // 기존에 존재하던 auto 데이터 삭제
		Random random = new Random();
		String key = String.format("%04d", random.nextInt(10000));
		vo.setCode(key); // 파라미터로 받은 vo에 key값 삽입
		dao.insert(vo); // auto 테이블에 insert
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("Hello, This is the 'hta.gg' authentication mail.");
        sendMail.setText(
           new StringBuffer()
              .append("<h1>Join Membership Mail Authentication</h1>")
              .append("Authentication number : ")
              .append("<h3 style='display:inline'>" + key + "</h3>")
              .append("<br>Please go back to the homepage and enter your email verification number.")
              .toString());
        sendMail.setFrom("hta.gg@gmail.com", "hta.gg");
		if (email.substring(email.lastIndexOf("@")).trim().equals("@daum")) {
			sendMail.setTo(email + ".net");
			sendMail.send();
		} else {
			sendMail.setTo(email + ".com");
			sendMail.send();
		}
	}
	public String check(String username) {
		return dao.selectAuth(username).getCode();
	}

	// 아이디 찾기 인증번호 전송 후 auth값 update
	public void idSearch(String email) throws Exception {
		Random random = new Random();
		String code = String.format("%04d", random.nextInt(10000)); // 랜덤난수(인증번호) 생성
		List<MemberVo> list = dao.emailList(email); // 해당 이메일로 가입된 아이디를 list에 저장
		for(MemberVo vo:list) {
			dao.authUpdate(new AuthVo(vo.getUsername(),code)); // auth 테이블의 코드값 업데이트
		}

        MailHandler sendMail = new MailHandler(mailSender); // 메일 핸들러 객체 생성 (parameter : javaMailSender 클래스에서 리턴받은 아이디, 비밀번호)
        sendMail.setSubject("Hello, This is the 'hta.gg' authentication mail."); // 메일 제목 설정
        sendMail.setText( // 메일 내용 설정
           new StringBuffer()
              .append("<h1>ID Find Mail Authentication</h1>")
              .append("Authentication number : ")
              .append("<h3 style='display:inline'>" + code + "</h3>")
              .append("<br>Please go back to the homepage and enter your email verification number.")
              .toString());
        sendMail.setFrom("hta.gg@gmail.com", "hta.gg"); // 이메일, 보내는사람 이름 설정
        sendMail.setTo(email); // 받는사람 설정
		sendMail.send(); // 이메일 전송
	}
	public AuthVo idSeachCheck(String email) {
		return dao.EmailselectAuth(email);
	}

	// 비밀번호 찾기 인증번호 전송 후 auth값 update
	public void pwdSearch(String id, String email) throws Exception {
		Random random = new Random();
		String code = String.format("%04d", random.nextInt(10000));
		dao.authUpdate(new AuthVo(id,code));
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("Hello, This is the 'hta.gg' authentication mail.");
        sendMail.setText(
           new StringBuffer()
              .append("<h1>Find/Change Password Authentication</h1>")
              .append("Authentication number : ")
              .append("<h3 style='display:inline'>" + code + "</h3>")
              .append("<br>Please go back to the homepage and enter your email verification number.")
              .toString());
        sendMail.setFrom("hta.gg@gmail.com", "hta.gg");
        sendMail.setTo(email);
		sendMail.send();
	}
}
