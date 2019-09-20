package cn.rookiex.rank.data;

/**
 * 等级排行榜
 *
 * @author wh
 * @create 2019-09-10 14:33
 */
public class LevelRank implements RankData {
    String rankName;

    double score;

    String userName;

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String buildRankName() {
        return rankName;
    }

    @Override
    public double buildRankScore() {
        return score;
    }

    @Override
    public String buildRankDataId() {
        return userName;
    }
}