package cn.rookiex.rank.data;

/**
 * @Author : Rookiex
 * @Date : Created in 2019/9/5 13:53
 * @Describe :
 * @version: 1.0
 */
public interface RankData {
    /**
     * 生成排行榜名称
     *
     * @return 排行榜名称
     */
    String buildRankName();

    /**
     * 生成用于用于比较排名的排行榜积分
     *
     * @return 比较分
     */
    double buildRankScore();

    /**
     * 生成排行榜数据对象对应的id
     *
     * @return dataId
     */
    String buildRankDataId();
}
