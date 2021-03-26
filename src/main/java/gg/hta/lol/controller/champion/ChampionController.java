package gg.hta.lol.controller.champion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import gg.hta.lol.joinvo.WinloseRuntimeVo;
import gg.hta.lol.joinvo.WinningSettingVo;
import gg.hta.lol.joinvo.championidTogetherVo;
import gg.hta.lol.riotapi.RuneSpellConverter2;
import gg.hta.lol.vo.OnlyItemVo;
import org.json.simple.ItemList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import gg.hta.lol.riotapi.RuneSpellConverter;
import gg.hta.lol.service.ChampService;
import gg.hta.lol.vo.ChampionVo;

@Controller
public class ChampionController {
	@Autowired
	ChampService service;

	@Autowired
	RuneSpellConverter2 converter;


	@GetMapping(value = "/champ/ChampInsert")
	public String ChmapList() {
		return "champ/ChampInsert";
	}

	@ResponseBody
	@PostMapping(value = "champ/ChampInsert2", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public String ChamInsert2(ChampionVo vo) {

		service.insertChamp(vo);
		return "{\"code\":\"" + vo + "\"}";
	}

	@ResponseBody
	@GetMapping(value = "/champ/ChampDelete")
	public String ChampDelete() {

		service.deleteChamp();

		return "{\"code\":\"dd\"}";
	}
	
	@GetMapping(value = "/champ/ChampList1")
	public String ChampList(Model mv) {
		
		
		return ".header2.champ.ChampList";
	}

	@ResponseBody
	@GetMapping(value = "/champ/ChampList", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public Map<String, Object> ChampAll_List() {
		Map<String, Object> map = new HashMap<String, Object>();

	
		map.put("map", service.ChampAll_List());
		map.put("banlist", service.banlistAll());
		map.put("picklist", service.pickrank());
		map.put("winlist", service.winlistAll());

		
		
		return map;

	}

	@GetMapping(value = "/champ/selectList{championid}")
	public String selectList(int championid, Model mv) {

		List<WinningSettingVo> list = service.getWinningSetting(championid);

		ArrayList<Integer> itemTotal = new ArrayList<>();
		ArrayList<Integer> spellTotal = new ArrayList<>();
		ArrayList<Integer> runeTotal = new ArrayList<>();

		for (WinningSettingVo vo : list) {
			if (vo.getItem1() > 0) itemTotal.add(vo.getItem1());
			if (vo.getItem2() > 0) itemTotal.add(vo.getItem2());
			if (vo.getItem3() > 0) itemTotal.add(vo.getItem3());
			if (vo.getItem4() > 0) itemTotal.add(vo.getItem4());
			if (vo.getItem5() > 0) itemTotal.add(vo.getItem5());
			if (vo.getItem6() > 0) itemTotal.add(vo.getItem6());
			spellTotal.add(vo.getSpell1());
			spellTotal.add(vo.getSpell2());
			runeTotal.add(vo.getRune1());
			runeTotal.add(vo.getRune2());
		}


		List<WinloseRuntimeVo> wrVo = service.getWinloseRuntime(championid);

		List<HashMap<String, Integer>> resultList = new ArrayList<>();
		HashMap<String, Integer> winMap = new HashMap<>();
		winMap.put("win0025", 0);
		winMap.put("win2530", 0);
		winMap.put("win3035", 0);
		winMap.put("win3540", 0);
		winMap.put("win4000", 0);

		HashMap<String, Integer> loseMap = new HashMap<>();
		loseMap.put("lose0025", 0);
		loseMap.put("lose2530", 0);
		loseMap.put("lose3035", 0);
		loseMap.put("lose3540", 0);
		loseMap.put("lose4000", 0);

		resultList.add(winMap);
		resultList.add(loseMap);

		for (WinloseRuntimeVo vo : wrVo) {
			// OO분 OO초 -> 숫자 4자리로 변환
			int convertRuntime = Integer.parseInt(vo.getRuntime().replace("분", "").replace("초", "").replaceAll(" ", ""));

			if (vo.getWinlose().equals("Win")) {
				if (convertRuntime <= 25)
					winMap.put("win0025", winMap.get("win0025") + 1);
				else if (convertRuntime <= 3000)
					winMap.put("win2530", winMap.get("win2530") + 1);
				else if (convertRuntime <= 3500)
					winMap.put("win3035", winMap.get("win3035") + 1);
				else if (convertRuntime <= 4000)
					winMap.put("win3540", winMap.get("win3540") + 1);
				else
					winMap.put("win4000", winMap.get("win4000") + 1);
			} else {
				if (convertRuntime <= 25)
					loseMap.put("lose0025", loseMap.get("lose0025") + 1);
				else if (convertRuntime <= 3000)
					loseMap.put("lose2530", loseMap.get("lose2530") + 1);
				else if (convertRuntime <= 3500)
					loseMap.put("lose3035", loseMap.get("lose3035") + 1);
				else if (convertRuntime <= 4000)
					loseMap.put("lose3540", loseMap.get("lose3540") + 1);
				else
					loseMap.put("lose4000", loseMap.get("lose4000") + 1);
			}
		}

		List<HashMap<String, Object>> championRankMap = service.getChampionRank(championid);

		HashMap<String, List> rankerItemListMap = new HashMap<>();

		for (int i=0; i<3; i++) {
			HashMap<String, Object> rankerItemListPram = new HashMap<>();
			rankerItemListPram.put("championid", championid);
			rankerItemListPram.put("snickname", championRankMap.get(i).get("SNICKNAME"));

			List<OnlyItemVo> itemList = service.getChampionRankerItemList(rankerItemListPram);

			ArrayList<Integer> listArray = new ArrayList<>();

			for (int j=0; j<itemList.size(); j++) {
				if (itemList.get(j).getItem1() > 0) listArray.add(itemList.get(j).getItem1());
				if (itemList.get(j).getItem2() > 0) listArray.add(itemList.get(j).getItem2());
				if (itemList.get(j).getItem3() > 0) listArray.add(itemList.get(j).getItem3());
				if (itemList.get(j).getItem4() > 0) listArray.add(itemList.get(j).getItem4());
				if (itemList.get(j).getItem5() > 0) listArray.add(itemList.get(j).getItem5());
				if (itemList.get(j).getItem6() > 0) listArray.add(itemList.get(j).getItem6());
			}

			rankerItemListMap.put(championRankMap.get(i).get("SNICKNAME")+"", descending(listArray));

		}


		HashMap<String, ArrayList<Integer>> winloseByTierMap = new HashMap<>();
		HashMap<String, Object> paramMap = new HashMap<>();

		String[] tierList = {"IRON", "BRONZE", "SILVER", "GOLD", "PLATINUM", "DIAMOND", "MASTER", "GRANDMASTER", "CHALLENGER"};

		paramMap.put("championid", championid);

		for (int i=0; i<tierList.length; i++) {
			paramMap.put("tier", tierList[i]);
			ArrayList<Integer> arr = new ArrayList<>();
			paramMap.put("winlose", "Win");
			arr.add(service.getWinloseCountByTier(paramMap));
			paramMap.put("winlose", "Fail");
			arr.add(service.getWinloseCountByTier(paramMap));
			winloseByTierMap.put(tierList[i], arr);
			System.out.println(service.getWinloseCountByTier(paramMap));
		}

		/*winloseByTierMap.put("ironWin", service.getWinloseCountByTier("IRON"));*/

		mv.addAttribute("winloseByTierMap", winloseByTierMap);
		mv.addAttribute("championRankMap", championRankMap);
		mv.addAttribute("rankerItemListMap", rankerItemListMap);
		mv.addAttribute("resultList", resultList);
		mv.addAttribute("recommItem", descending(itemTotal));
		mv.addAttribute("recommSpell", descending(spellTotal));
		mv.addAttribute("recommrune", descending(runeTotal));
		mv.addAttribute("championid", championid);
		return ".header2.champ.selectList";
	}

	@ResponseBody
	@GetMapping(value = "/champ/selectList1")
	public ChampionVo selectList1(int championid) {
	
		service.selectList(championid);
		
		return service.selectList(championid);
	}
	
	@ResponseBody
	@GetMapping(value="/champ/count")
	public Map<String, Object> count(int championid){
		Map<String, Object> mc=new HashMap<String, Object>();
		System.out.println(service.wincount(championid));
		System.out.println(service.banCount(championid));
		System.out.println(service.matchCount(championid));
		mc.put("allCount", service.allCount(championid));
		mc.put("wincount", service.wincount(championid));
		mc.put("bancount", service.banCount(championid));
		mc.put("matchcount", service.matchCount(championid));
		mc.put("maxCount",service.maxCount(championid));
		mc.put("winrank",service.winrank(championid));
		mc.put("banrank",service.banrank(championid));
		mc.put("itemlist",service.itemtree(championid));
		return mc;
				
	}
	@ResponseBody
	@GetMapping(value="/champ/itemtree")
	public Map<String,Object> itemtree(int championid){
		Map<String, Object> mp=new HashMap<String, Object>();
		

		mp.put("itemlist",service.itemtree(championid));
		return mp;
	}
	@ResponseBody
	@GetMapping(value="/champ/conver")
	public Map<String,Object> conver(String spell1){
		Map<String,Object> aa=new HashMap<String, Object>();
		System.out.println("아아");
		System.out.println(spell1);
		
		return null;
		
	}


	@GetMapping(value = "/champ/ChampPickList")
	public String ChampPickList() {
		return "champ/ChampPick";
	}


	public ArrayList<Integer> descending(ArrayList<Integer> total) {
		HashMap<Integer, Integer> temp = new HashMap<>();

		for (int a : total) {
			temp.put(a, temp.getOrDefault(a, 0) + 1);
		}

		List<Integer> keySetList = new ArrayList<>(temp.keySet());

		Collections.sort(keySetList, (o1, o2) -> (temp.get(o2).compareTo(temp.get(o1))));

		return new ArrayList<>(keySetList);
	}


}
