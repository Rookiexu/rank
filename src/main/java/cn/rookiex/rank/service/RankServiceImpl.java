package cn.rookiex.rank.service;

import cn.rookiex.rank.data.RankData;
import cn.rookiex.rank.exception.RedisInitFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author : Rookiex
 * @Date : Created in 2019/9/5 14:08
 * @Describe :
 * @version: 1.0
 */
@Component
public class RankServiceImpl implements RankService {

    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 如果没有排行就直接写数据进去
     *
     * @param rankData 排行数据
     * @return 操作结果
     */
    @Override
    public boolean updateInsertRank(RankData rankData) throws RedisInitFailException {
        if (redisTemplate == null) {
            throw new RedisInitFailException();
        }
        BoundZSetOperations<String, String> zSetOperations = redisTemplate.boundZSetOps(rankData.getRankName());
        Boolean add = zSetOperations.add(rankData.getUserName(), rankData.getValue());
        return add == null ? false : add;
    }

    /**
     * 获得排行榜数据
     *
     * @param lowRank  最低排名(包含)
     * @param highRank 最高排名(包含)
     * @param rankName 排行数据
     * @return 获取的排行榜数据
     */
    @Override
    public Set<String> getRankData(int lowRank, int highRank, String rankName) throws RedisInitFailException {
        if (redisTemplate == null) {
            throw new RedisInitFailException();
        }
        BoundZSetOperations<String, String> zSetOperations = redisTemplate.boundZSetOps(rankName);

        return zSetOperations.range(lowRank, highRank);
    }

    /**
     * 获得排行榜数据
     *
     * @param rank     指定排行的数据
     * @param rankName 指定排行的value
     * @return 获取的排行榜数据
     */
    @SuppressWarnings("DuplicatedCode")
    @Override
    public String getRankDataByRank(int rank, String rankName) throws RedisInitFailException {
        if (redisTemplate == null) {
            throw new RedisInitFailException();
        }
        BoundZSetOperations<String, String> zSetOperations = redisTemplate.boundZSetOps(rankName);
        Set<String> strings = zSetOperations.range(rank, rank);
        if (strings == null) {
            return null;
        }
        return strings.size() > 0 ? strings.iterator().next() : null;
    }

    /**
     * 获得排行榜数据
     *
     * @param value    指定排行的value
     * @param rankName 指定排行的value
     * @return 获取的排行榜数据
     */
    @SuppressWarnings("DuplicatedCode")
    @Override
    public String getRankDataByValue(int value, String rankName) throws RedisInitFailException {
        if (redisTemplate == null) {
            throw new RedisInitFailException();
        }
        BoundZSetOperations<String, String> zSetOperations = redisTemplate.boundZSetOps(rankName);
        Set<String> strings = zSetOperations.rangeByScore(value, value);
        if (strings == null) {
            return null;
        }
        return strings.size() > 0 ? strings.iterator().next() : null;
    }

    /**
     * 清除指定排行榜数据
     *
     * @param rankData 排行榜数据
     * @return 操作结果
     */
    @Override
    public boolean deleteRankData(RankData rankData) throws RedisInitFailException {
        if (redisTemplate == null) {
            throw new RedisInitFailException();
        }
        BoundZSetOperations<String, String> zSetOperations = redisTemplate.boundZSetOps(rankData.getRankName());
        Long remove = zSetOperations.remove(rankData.getUserName());
        return remove != null && remove > 0;
    }

    /**
     * 清除某个排行榜
     *
     * @param rankName 排行榜名字
     * @return 操作结果
     */
    @Override
    public boolean cleanRank(String rankName) throws RedisInitFailException {
        if (redisTemplate == null) {
            throw new RedisInitFailException();
        }
        Boolean delete = redisTemplate.delete(rankName);

        return delete == null ? false : delete;
    }

    /**
     * 初始化排行榜数据
     *
     * @param rankName     排行榜名字
     * @param initDataList 初始化数据
     * @return result
     */
    @Override
    public boolean initRank(String rankName, List<RankData> initDataList) throws RedisInitFailException {
        if (redisTemplate == null) {
            throw new RedisInitFailException();
        }
        for (RankData rankData : initDataList) {
            updateInsertRank(rankData);
        }
        return true;
    }

    /**
     * 初始化排行榜数据
     *
     * @param rankName    排行榜名字
     * @param elementName 初始化数据
     * @return result
     * @throws RedisInitFailException redis not init
     */
    @Override
    public Long getRankByUserName(String rankName, String elementName) throws RedisInitFailException {
        if (redisTemplate == null) {
            throw new RedisInitFailException();
        }
        BoundZSetOperations<String, String> zSetOperations = redisTemplate.boundZSetOps(rankName);
        return zSetOperations.rank(elementName);
    }

    /**
     * 初始化排行榜数据
     *
     * @param rankName 排行榜名字
     * @return result
     * @throws RedisInitFailException redis not init
     */
    @Override
    public Long getRankSize(String rankName) throws RedisInitFailException {
        if (redisTemplate == null) {
            throw new RedisInitFailException();
        }
        BoundZSetOperations<String, String> zSetOperations = redisTemplate.boundZSetOps(rankName);
        return zSetOperations.zCard();
    }

}
