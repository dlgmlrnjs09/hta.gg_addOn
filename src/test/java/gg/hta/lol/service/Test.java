package gg.hta.lol.service;

import gg.hta.lol.joinvo.WinloseRuntimeVo;
import gg.hta.lol.joinvo.WinningSettingVo;
import lombok.Setter;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value =
        {
                "file:src/main/webapp/WEB-INF/spring/root-context.xml",
                "file:src/main/webapp/WEB-INF/spring/security_config.xml",
                "file:src/main/webapp/WEB-INF/spring/email/email-config.xml"
        })
@Setter
public class Test {

    @Autowired
    ItemService service;

    @org.junit.Test
    public void test() {

        int minPrice = 1;
        int maxPrice = 5000;
        String tag = "Active";

        HashMap<String, Object> map = new HashMap<>();
        map.put("minPrice", minPrice);
        map.put("maxPrice", maxPrice);
        map.put("tag", tag);

        List<HashMap<String, Integer>> itemList = service.getAllItemPrice(map);

        HashMap<Object, Integer> resultMap = new HashMap<>();

        for (HashMap<String, Integer> m : itemList) {
            // 가격, 태그 조건맞는 아이템 목록 가져온 상태
            String[] col = {"ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"};

            HashMap<String, Object> paramMap = new HashMap<>();

            int winCount = 0;
            int loseCount = 0;

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
            System.out.println(list);
            int rating = winCount * 100 / (winCount + loseCount);

            resultMap.put(m.get("INUM"), rating);
        }

        List<Object> keySetList = new ArrayList<>(resultMap.keySet());

        Collections.sort(keySetList, (o1, o2) -> (resultMap.get(o2).compareTo(resultMap.get(o1))));

        LinkedHashMap<Object, Object> finalResultMap = new LinkedHashMap<>();
        for(Object key : keySetList) {
            finalResultMap.put(key, resultMap.get(key));
        }

        System.out.println(finalResultMap);
    }
}
