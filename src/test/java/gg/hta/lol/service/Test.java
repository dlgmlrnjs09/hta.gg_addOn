package gg.hta.lol.service;

import gg.hta.lol.vo.useItemCountVo;
import lombok.Setter;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

        /*List<useItemCountVo> list = service.useItemCountByChampion(3035);
        HashMap<String, Integer> resultMap = new HashMap<>();

        for (useItemCountVo item : list) {
            System.out.println(item.getChampionId() + "/" + item.getCnt());

            String championId = item.getChampionId();
            int cnt = item.getCnt();

            if (!resultMap.containsKey(championId)) {
                resultMap.put(championId, item.getCnt());
            } else {
                resultMap.put(championId, resultMap.get(championId) + cnt);
            }
        }

        List<Integer> keySetList = new ArrayList<>(resultMap.keySet());
        List<int[]> resultList = new ArrayList<>();

        Collections.sort(keySetList, (o1, o2) -> (resultMap.get(o2).compareTo(resultMap.get(o1))));
        for(Integer key : keySetList) {
            int[] i = {key, resultMap.get(key)};
            resultList.add(i);
        }

        System.out.println(resultList);*/
    }
}
