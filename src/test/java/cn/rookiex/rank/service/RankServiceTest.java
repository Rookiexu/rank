// package cn.rookiex.rank.service;
//
// import cn.rookiex.rank.RankApplication;
// import cn.rookiex.rank.data.LevelRank;
// import cn.rookiex.rank.data.RankData;
// import cn.rookiex.rank.exception.RedisInitFailException;
// import org.assertj.core.util.Lists;
// import org.junit.Assert;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
//
// import java.util.List;
// import java.util.Set;
//
// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = RankApplication.class)
// public class RankServiceTest {
//     private String rankName = "rankName-testRank";
//     private String userName = "testUser-";
//     private int testUserSize = 100;
//     private int initValue = 1000;
//     private int testRank = 10;
//     private RankService rankService;
//
//     @Autowired
//     public void setRankService(RankService rankService) {
//         this.rankService = rankService;
//     }
//
//     @Test
//     public void updateInsertRank() throws RedisInitFailException {
//         LevelRank rankData = new LevelRank();
//         rankData.setRankName(rankName);
//         rankData.setUserName(userName + testRank);
//         rankData.setScore(testRank + initValue);
//         rankService.updateInsertRank(rankData);
//         String nameByValue = rankService.getRankDataByValue(testRank + initValue, rankName);
//         Assert.assertEquals(userName + testRank,nameByValue);
//     }
//
//     @Test
//     public void getRankData() throws RedisInitFailException {
//         int low = 0;
//         int high = 120;
//         initRankInfo();
//         Set<String> rankData = rankService.getRankData(low, high, rankName);
//         Long rankSize = rankService.getRankSize(rankName);
//         Assert.assertEquals(testUserSize, rankData.size());
//         Assert.assertNotNull(rankSize);
//         Assert.assertEquals(testUserSize, rankSize.longValue());
//     }
//
//     private void initRankInfo() throws RedisInitFailException {
//
//         List<RankData> l = Lists.newArrayList();
//         for (int i = 0; i < testUserSize; i++) {
//             LevelRank rankData = new LevelRank();
//             rankData.setRankName(rankName);
//             rankData.setUserName(userName + i);
//             rankData.setScore(i + initValue);
//             l.add(rankData);
//         }
//         rankService.cleanRank(rankName);
//         rankService.initRank(rankName, l);
//     }
//
//     @Test
//     public void getRankDataByRank() throws RedisInitFailException {
//         initRankInfo();
//         String rankDataByRank = rankService.getRankDataByRank(testRank, rankName);
//         Assert.assertNotNull(rankDataByRank);
//
//         rankDataByRank = rankService.getRankDataByRank(testRank, rankName);
//         Assert.assertEquals(userName + testRank, rankDataByRank);
//     }
//
//     @Test
//     public void getRankDataByValue() throws RedisInitFailException {
//         initRankInfo();
//         String rankDataByRank = rankService.getRankDataByValue(testRank, rankName);
//         Assert.assertNull(rankDataByRank);
//
//
//         rankDataByRank = rankService.getRankDataByValue(testRank + initValue, rankName);
//         Assert.assertEquals(userName + testRank, rankDataByRank);
//     }
//
//     @Test
//     public void deleteRankData() throws RedisInitFailException {
//         initRankInfo();
//         LevelRank rankData = new LevelRank();
//         rankData.setRankName(rankName);
//         rankData.setUserName(userName + testRank);
//         rankData.setScore(testRank + initValue);
//         rankService.deleteRankData(rankData);
//         Long rankByUserName = rankService.getRankByUserName(rankName,userName + testRank);
//         Assert.assertNull(rankByUserName);
//     }
//
//     @Test
//     public void cleanRank() throws RedisInitFailException {
//         initRankInfo();
//         boolean b = rankService.cleanRank(rankName);
//         Assert.assertTrue(b);
//
//         Long rankSize = rankService.getRankSize(rankName);
//         Assert.assertEquals(0,rankSize.longValue());
//     }
//
//     @Test
//     public void getRankByUserName() throws RedisInitFailException {
//         initRankInfo();
//
//         Long rankByUserName = rankService.getRankByUserName(rankName, userName + testRank);
//         Assert.assertNotNull(rankByUserName);
//         Assert.assertEquals(rankByUserName.longValue(),testRank);
//     }
//
//     @Test
//     public void getRankSize() throws RedisInitFailException {
//         initRankInfo();
//
//         Long rankSize = rankService.getRankSize(rankName);
//         Assert.assertEquals(rankSize.longValue(),testUserSize);
//     }
// }