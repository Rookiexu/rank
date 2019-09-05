package cn.rookiex.rank.data;

/**
 * @Author : Rookiex
 * @Date : Created in 2019/9/5 13:53
 * @Describe :
 * @version: 1.0
 */
public class RankData {

    /**
     * 排行榜名称
     */
    private String rankName;

    /**
     * 用来排名的数值
     */
    private double value;

    /**
     * 用户id
     */
    private String userName;

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
