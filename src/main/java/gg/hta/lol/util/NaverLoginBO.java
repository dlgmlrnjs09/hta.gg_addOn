package gg.hta.lol.util;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class NaverLoginBO {
	//naver id, secret ,callback URL
	private final static String CLIENT_ID = "3MPuJ2pPpJPAXp6LAddC"; // 발급받은 클라이언트 아이디
	private final static String CLIENT_SECRET = "JdYlVoxZFv"; // 발급받은 클라이언트 secret code
	private final static String REDIRECT_URI = "http://localhost:9090/member/callback"; // 로그인 인증의 결과를 전달받을 URL
	private final static String SESSION_STATE = "oauth_state"; // 상태 토큰
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me"; // 네이버 api URL

	// 인증 url 생성
	public String getAuthorizationUrl(HttpSession session) {


		String state = generateRandomString(); // 세션 유효성 체크 난수 생성
		setSession(session,state); // 난수를 세션에 저장
		OAuth20Service oauthService = new ServiceBuilder()
		.apiKey(CLIENT_ID)
		.apiSecret(CLIENT_SECRET)
		.callback(REDIRECT_URI)
		.state(state) // 생성했던 난수값 (인증url생성시 사용)
		.build(NaverLoginApi.instance());
		return oauthService.getAuthorizationUrl();
	}

	// 액세스 토큰 획득
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException{
		String sessionState = getSession(session);
		if(StringUtils.pathEquals(sessionState, state)){
			OAuth20Service oauthService = new ServiceBuilder()
			.apiKey(CLIENT_ID)
			.apiSecret(CLIENT_SECRET)
			.callback(REDIRECT_URI)
			.state(state)
			.build(NaverLoginApi.instance());
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}
	// 세션 유효성검증 난수 생성
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}
	// 세션에 데이터 저장
	private void setSession(HttpSession session,String state){
		session.setAttribute(SESSION_STATE, state);
	}
	// 세션에서 데이터 가져오기
	private String getSession(HttpSession session){
		return (String) session.getAttribute(SESSION_STATE);
	}
	// 액세스 토큰을 이용해 사용자 프로필 api 호출
	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException{
		OAuth20Service oauthService =new ServiceBuilder()
		.apiKey(CLIENT_ID)
		.apiSecret(CLIENT_SECRET)
		.callback(REDIRECT_URI).build(NaverLoginApi.instance());
		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
	}
}