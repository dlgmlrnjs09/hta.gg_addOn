package gg.hta.lol.service;

import gg.hta.lol.joinvo.WinloseRuntimeVo;
import gg.hta.lol.joinvo.WinningSettingVo;
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
    ChampService service;

    @org.junit.Test
    public void test() {

        /*HashMap<String, List> rankerItemListMap = new HashMap<>();
        HashMap<String, Object> rankerItemListPram = new HashMap<>();
        List<HashMap<String, Object>> championRankMap = service.getChampionRank(79);
        rankerItemListPram.put("championid", 79);
        rankerItemListPram.put("snickname", "더덕순대국");
        rankerItemListMap.put("더덕순대국", service.getChampionRankerItemList(rankerItemListPram));

        for (int a : service.getChampionRankerItemList(rankerItemListPram)) {
            System.out.println(a);
        }*/

        /*System.out.println(rankerItemListMap);*/

       /* for (int i=0; i<3; i++) {

            List<HashMap<String, Object>> championRankMap = service.getChampionRank(79);
            HashMap<String, Object> rankerItemListPram = new HashMap<>();
            rankerItemListPram.put("championid", 79);
            rankerItemListPram.put("snickname", championRankMap.get(i).get("SNICKNAME"));

            rankerItemListMap.put(championRankMap.get(i).get("SNICKNAME") + "", service.getChampionRankerItemList(rankerItemListPram));

            System.out.println(rankerItemListMap);
        }*/
    }
}
