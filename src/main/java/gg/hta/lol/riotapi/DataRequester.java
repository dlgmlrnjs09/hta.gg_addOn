package gg.hta.lol.riotapi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class DataRequester {

	private final String KEY = "RGAPI-19840818-756b-4f29-9e24-c2e2cbcd1f4f";
	private final int MAX_TRY = 10;

	// 소환사 정보
	public JsonObject getSummonerInfo(String name) {
		String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/%s?api_key=" + KEY; // api URL + api KEY

		try {
			name = URLEncoder.encode(name, "utf-8"); // 소환사 닉네임 utf-8로 변환
			url = String.format(url, name); // url에 name 삽입
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

		JsonElement element = getData(url); // api 요청 결과값 받아오기

		if (element == null) {
			return null;
		} else {
			return element.getAsJsonObject(); // 요청 결과값 가공하기 위해 String 값으로 return
		}
	}

	// 소환사가 진행한 게임 정보
	public JsonArray getLeagueInfo(String sid) {
		String url = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/%s?api_key=" + KEY;

		try {
			sid = URLEncoder.encode(sid, "utf-8");
			url = String.format(url, sid);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		return getData(url).getAsJsonArray();
	}

	// 소환사가 진행한 게임 리스트
	public JsonObject getMatchList(String aid, int start, int end) {
		String url = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/%s?endIndex=%d&beginIndex=%d&api_key="
				+ KEY;

		try {
			aid = URLEncoder.encode(aid, "utf-8");
			url = String.format(url, aid, end, start);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		return getData(url).getAsJsonObject();
	}
	
	public JsonObject getMatchList(String aid) {
		String url = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/%s?api_key="
				+ KEY;
		
		try {
			aid = URLEncoder.encode(aid, "utf-8");
			url = String.format(url, aid);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		return getData(url).getAsJsonObject();
	}

	public JsonObject getMatchInfo(String mid) {
		String url = "https://kr.api.riotgames.com/lol/match/v4/matches/%s?api_key=" + KEY;

		try {
			mid = URLEncoder.encode(mid, "utf-8");
			url = String.format(url, mid);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

		JsonElement element = getData(url);
		if (element == null) {
			return null;
		} else {
			return element.getAsJsonObject();
		}
	}

	public JsonElement getData(String url) {
		int cnt = 0;
		BufferedReader br = null;
		HttpURLConnection con = null;
		try {
			while (true) {
				URL obj = new URL(url); // 호출할 url
				con = (HttpURLConnection) obj.openConnection(); // url에 연결

				con.setRequestMethod("GET"); // url에 접근 방식 get으로 설정

				if (con.getResponseCode() != 200) {
					System.out.println("api 요청 실패 에러코드 : " + con.getResponseCode());
					if(con.getResponseCode() == 404) {
						return null;
					}
					if(con.getResponseCode() == 429) {
						System.out.println("연동 횟수 초과");
						Thread.sleep(200);
					}
					cnt++;
					// 시도 횟수가 10보다 작다면 다시 시도
					if (cnt < MAX_TRY) {
						continue;
					}
					// 시도 횟수가 10보다 크다면 예외 발생
					else {
						throw new Exception();
					}
				}

				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

				StringBuilder sb = new StringBuilder();
				String temp = null;

				while ((temp = br.readLine()) != null) {
					sb.append(temp);
				}
				JsonParser parser = new JsonParser();

				return parser.parse(sb.toString()); // JsonParser객체에 결과값 담아서 return
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
		catch (Exception e) {
//			e.printStackTrace();
			System.out.println("api 연동 실패");
			return null;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			con.disconnect();
		}

	}

	public JsonElement getStaticData(String url) {

		BufferedInputStream bi = null;

		try {
			URL obj = new URL(url); // 호출할 url
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");

			if (con.getResponseCode() != 200) {
				System.out.println("api 연동 실패 에러코드 : " + con.getResponseCode());
				throw new Exception();
			}

			bi = new BufferedInputStream(con.getInputStream());

			String data = new String(bi.readAllBytes());

			JsonParser parser = new JsonParser();
			return parser.parse(data);

		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("api 연동 에러");
			return null;
		} finally {
			if (bi != null)
				try {
					bi.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

}
