package cn.rookiex.rank;

import cn.rookiex.rank.data.RankData;
import cn.rookiex.rank.exception.RedisInitFailException;
import cn.rookiex.rank.service.RankService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RankApplication.class)
public class RankServiceApplicationTests {

    private RankService rankService;

    @Autowired
    public void setRankService(RankService rankService) {
        this.rankService = rankService;
    }

    @Test
    public void contextLoads() {
        String rankName = "rankName-testRank";
        String userName = "testUser-";
        int testUserSize = 100;
        List<RankData> l = Lists.newArrayList();
        for (int i = 0; i < testUserSize; i++) {
            RankData rankData = new RankData();
            rankData.setRankName(rankName);
            rankData.setUserName(userName+i);
            rankData.setValue(i + 1000);
            l.add(rankData);
        }


        try {
            rankService.cleanRank(rankName);
            rankService.initRank(rankName,l);

            Set<String> rankData1 = rankService.getRankData(20, 50, rankName);
            rankData1.forEach(s -> {
                System.out.println(" rank ==> " + s);
            });
        } catch (RedisInitFailException e) {
            e.printStackTrace();
        }


    }


}
