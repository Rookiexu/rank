package cn.rookiex.rank.service;

import cn.rookiex.rank.data.RankData;
import cn.rookiex.rank.exception.RedisInitFailException;

import java.util.List;
import java.util.Set;

/**
 * @Author : Rookiex
 * @Date : Created in 2019/9/5 13:52
 * @Describe : 排行榜接口
 * @version: 1.0
 */
public interface RankService {

    /**
     * 如果没有排行就直接写数据进去
     *
     * @param rankData 排行数据
     * @return 操作结果
     * @throws RedisInitFailException redis not init
     */
    boolean updateInsertRank(RankData rankData) throws RedisInitFailException;

    /**
     * 获得排行榜数据
     *
     * @param lowRank  最低排名(包含)
     * @param highRank 最高排名(包含)
     * @param rankName 排行数据
     * @return 获取的排行榜数据 key = elementName
     * @throws RedisInitFailException redis not init
     */
    Set<String> getRankData(int lowRank, int highRank, String rankName) throws RedisInitFailException;

    /**
     * 获得排行榜数据
     *
     * @param rank     指定排行的数据
     * @param rankName 指定排行的value
     * @return elementName
     * @throws RedisInitFailException redis not init
     */
    String getRankDataByRank(int rank, String rankName) throws RedisInitFailException;

    /**
     * 获得排行榜数据
     *
     * @param value    指定排行的value
     * @param rankName 指定排行的value
     * @return elementName
     * @throws RedisInitFailException redis not init
     */
    String getRankDataByValue(int value, String rankName) throws RedisInitFailException;

    /**
     * 清除指定排行榜数据
     *
     * @param rankData 排行榜数据
     * @return 操作结果
     * @throws RedisInitFailException redis not init
     */
    boolean deleteRankData(RankData rankData) throws RedisInitFailException;

    /**
     * 清除某个排行榜
     *
     * @param rankName 排行榜名字
     * @return 操作结果
     * @throws RedisInitFailException redis not init
     */
    boolean cleanRank(String rankName) throws RedisInitFailException;

    /**
     * 初始化排行榜数据
     *
     * @param rankName     排行榜名字
     * @param initDataList 初始化数据
     * @return result
     * @throws RedisInitFailException redis not init
     */
    boolean initRank(String rankName, List<RankData> initDataList) throws RedisInitFailException;

    /**
     * 初始化排行榜数据
     *
     * @param rankName     排行榜名字
     * @param elementName 初始化数据
     * @return result
     * @throws RedisInitFailException redis not init
     */
    Long getRankByUserName(String rankName, String elementName) throws RedisInitFailException;

    /**
     * 初始化排行榜数据
     *
     * @param rankName     排行榜名字
     * @return result
     * @throws RedisInitFailException redis not init
     */
    Long getRankSize(String rankName) throws RedisInitFailException;
}
