package gg.hta.lol.controller.batting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import gg.hta.lol.service.BattingService;
import gg.hta.lol.vo.BattingTeamInfoVo;
import gg.hta.lol.vo.MatchVo;

@Controller
public class MatchListController {
	@Autowired
	BattingService service;

	@GetMapping(value = "/match/list")
	public String matchlist() {
		return ".header2.batting.matchlist";
		
	}

	@GetMapping(value = "/match/yesdaylist")
	@ResponseBody
	public Map<String, Object> match_list(String mDate) {
		System.out.println("dd");
		Map<String, Object> matchlist = new HashMap<String, Object>();
		//커밋테스트
		matchlist.put("matchinfo", service.matchinfo(mDate));

		return matchlist;
	}
	@GetMapping(value="/match/teamrank")
	@ResponseBody
	public Map<String, Object> teamrank(){
		Map<String,Object> teamrank=new HashMap<String, Object>();
		teamrank.put("teamrank",service.teamrank());
		return teamrank;
	}
	
}
