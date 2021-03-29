package gg.hta.lol.controller.item;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;

import com.github.scribejava.core.utils.MapUtils;
import com.sun.istack.Nullable;
import gg.hta.lol.vo.useItemCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gg.hta.lol.service.ItemService;
import gg.hta.lol.vo.ItemVo;

@RestController
public class ItemListController {
    @Autowired
    ItemService service;

    @RequestMapping(value = "/itemlist", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<ItemVo> Item_list() {
        List<ItemVo> list = service.item_alllist();
        return list;
    }

    @RequestMapping(value = "/iteminfo", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ItemVo item_info(int inum) {
        ItemVo info = service.item_select(inum);
        return info;
    }

    @RequestMapping(value = "/select/iteminfo", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ItemVo item_info2(int inum) {
        ItemVo info = service.item_select(inum);
        return info;
    }

    @GetMapping(value = "/sortItemBestByAll")
    public List<HashMap<String, Object>> sortItemBestByAll() {
        return service.sortPopItemByAll();
    }

    @GetMapping(value = "/sortItemBestByPrice")
    public List<HashMap<String, Object>> sortItemBest(int minPrice, int maxPrice) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("minPrice", minPrice);
        map.put("maxPrice", maxPrice);

        return service.sortPopItemByPrice(map);
    }

    @GetMapping(value = "/sortItemBestByTag")
    public List<HashMap<String, Object>> sortItemTag(String tag) {
        return service.sortPopItemByTag(tag);
    }

    @GetMapping(value = "/sortItemBestByRating")
    @ResponseBody
    public ArrayList<List<Object>> sortItemBestByRating(int minPrice, int maxPrice, String tag) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("minPrice", minPrice);
        map.put("maxPrice", maxPrice);
        map.put("tag", tag);

        List<HashMap<String, Integer>> itemList = service.getAllItemPrice(map);

        HashMap<Object, Integer> resultMap = new HashMap<>();

        int winCount = 0;
        int loseCount = 0;

        for (HashMap<String, Integer> m : itemList) {
            // 가격, 태그 조건맞는 아이템 목록 가져온 상태
            String[] col = {"ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"};

            HashMap<String, Object> paramMap = new HashMap<>();

            winCount = 0;
            loseCount = 0;

            paramMap.put("inum", m.get("INUM"));

            // 승리횟수 구하기
            paramMap.put("winlose", "Win");
            for (String colName : col) {
                paramMap.put("col", colName);
                winCount += service.getHaveItemWinloseCount(paramMap);
            }

            // 패배횟수 구하기
            paramMap.put("winlose", "Fail");
            for (String colName : col) {
                paramMap.put("col", colName);
                loseCount += service.getHaveItemWinloseCount(paramMap);
            }

            ArrayList<Integer> list = new ArrayList<>();
            list.add(winCount);
            list.add(loseCount);

            int rating = 0;
            if (winCount > 0 || loseCount > 0) {
                rating = winCount * 100 / (winCount + loseCount);
            }

            resultMap.put(m.get("INUM"), rating);
        }

        List<Object> keySetList = new ArrayList<>(resultMap.keySet());

        Collections.sort(keySetList, (o1, o2) -> (resultMap.get(o2).compareTo(resultMap.get(o1))));

        ArrayList<List<Object>> resultList = new ArrayList<>();

        /*LinkedHashMap<Object, Object> finalResultMap = new LinkedHashMap<>();*/
        for(Object key : keySetList) {
           /* finalResultMap.put(key, resultMap.get(key));*/
            /*resultList.add(key);*/
            ArrayList<Object>  detailList = new ArrayList<>();
            detailList.add(key);
            detailList.add(resultMap.get(key));
            resultList.add(detailList);

        }

        /*System.out.println(finalResultMap);*/
        /*System.out.println(resultList);*/

        return resultList;
    }

    @GetMapping(value = "/getKdaAvg")
    @ResponseBody
    public double[] getKdaAvg(String inum) {
        String[] col = {"ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"};

        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("inum", inum);

        double killAvg = 0, deathAvg = 0, assistAvg = 0;

        for (String colName : col) {
            paramMap.put("col", colName);
            HashMap<String, Double> resultMap = service.getKdaAvg(paramMap);
            if (resultMap != null) {
                killAvg += Double.parseDouble(String.valueOf(resultMap.get("KILL")));
                deathAvg += Double.parseDouble(String.valueOf(resultMap.get("DEATH")));
                assistAvg += Double.parseDouble(String.valueOf(resultMap.get("ASSIST")));
            }
        }

        return new double[]{Math.round((killAvg/5) * 100)/100.0, Math.round((deathAvg/5) * 100)/100.0, Math.round((assistAvg/5) * 100)/100.0};
    }

    @GetMapping(value = "/getUseItemCountByChampion")
    @ResponseBody
    public ArrayList<HashMap<String, Integer>> getUseItemCountByChampion(int inum) {
        List<useItemCountVo> list = service.useItemCountByChampion(inum);
        HashMap<String, Integer> resultMap = new HashMap<>();

        for (useItemCountVo item : list) {

            item.setChampionId(service.getPictureName(item.getChampionId()));

            String championId = item.getChampionId();
            int cnt = item.getCnt();

            if (!resultMap.containsKey(championId)) {
                resultMap.put(championId, item.getCnt());
            } else {
                resultMap.put(championId, resultMap.get(championId) + cnt);
            }
        }

        List<String> keySetList = new ArrayList<>(resultMap.keySet());
        ArrayList<HashMap<String, Integer>> resultList = new ArrayList<>();

        Collections.sort(keySetList, (o1, o2) -> (resultMap.get(o2).compareTo(resultMap.get(o1))));
        for(String key : keySetList) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put(key, resultMap.get(key));
            resultList.add(map);
        }

        return resultList;
    }
}
	