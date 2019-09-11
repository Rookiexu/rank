package cn.rookiex.rank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author : Rookiex
 * @Date : Created in 2019/9/5 17:50
 * @Describe :
 * @version: 1.0
 */
@Configuration
@ComponentScan(basePackages = "cn.rookiex.rank.service")
public class RankAutoConfig {
    @Bean
    StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
