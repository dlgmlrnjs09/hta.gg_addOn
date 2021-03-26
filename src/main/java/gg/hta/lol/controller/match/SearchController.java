package gg.hta.lol.controller.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gg.hta.lol.service.RatingService;
import gg.hta.lol.service.match.SearchService;
import gg.hta.lol.vo.match.MostChampVo;
import gg.hta.lol.vo.match.SearchVo;
import lombok.Setter;


@Controller
public class SearchController {
	
	@Setter(onMethod_ = @Autowired)
	private SearchService searchService;
	
	@Setter(onMethod_ = @Autowired)
	private RatingService ratingService;

	
	@GetMapping("/match/search")
	public String search(String sName,Model model,HttpServletRequest request ,RedirectAttributes redirectAttributes) {

		List<HashMap<String, String>> list = searchService.getTargetMatchList(sName);
		/*List<String> tempList = new ArrayList<String>();*/
		/*StringBuffer sb = new StringBuffer();*/

		ArrayList<HashMap<String, Object>> duoRateList = new ArrayList<>();

		for (int i=0; i<list.size(); i++) {
			int count = 0;
			int winCount = 0;
			int loseCount = 0;
			String checkNickName = list.get(i).get("SNICKNAME");
			/*sb.append(checkNickName);*/

			if (!checkNickName.equals(sName)) {
				for (HashMap<String, String> item : list) {
					if (checkNickName.equals(item.get("SNICKNAME"))) {
						count++;

						if (item.get("WINLOSE").equals("Win")) {
							winCount++;
						} else {
							loseCount++;
						}
					}
				}

				boolean b = false;
				for (HashMap<String, Object> m : duoRateList) {
					if (m.get("snickname").equals(checkNickName)) {
						b = true;
						break;
					}
				}

				if (count > 2 && !b) {
					HashMap<String, Object> map = new HashMap<>();
					map.put("snickname", checkNickName);
					map.put("count", count);
					map.put("winCount", winCount);
					map.put("loseCount", loseCount);
					duoRateList.add(map);
				}
			}
		}

		sName = sName.replaceAll(" ", "");

		SearchVo svo = searchService.getSummoner(sName); // 소환사 정보 테이블 삽입 후 조회하여 vo로 받아옴
		
		if(svo == null) {
			System.out.println("소환사 정보 없음");
			redirectAttributes.addFlashAttribute("isError",true);
		    return "redirect:"+request.getHeader("Referer");
		}else {
			model.addAttribute("svo",svo);
			
			model.addAttribute("rating", ratingService.getRatingAvg(sName));

			searchService.readMatchList(svo.getAccountId(), 0, 20,svo.getSnickname()); // AccountId로
			
			List<MostChampVo> mlist = searchService.getMost(sName);

			int index = 1;
			mlist.stream().forEach(item -> {
				if(item.getName().length()>5) {
					int diff = item.getName().length()-(item.getName().length()-5);
					item.setName(item.getName().substring(0, diff).concat("..."));
				}
			});

			model.addAttribute("duoRateList", duoRateList);
			model.addAttribute("most", mlist);
			
			return ".header2.match.sinfo";
		}
	}
}
