package cn.rookiex.rank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author rookiexu
 */
@SpringBootApplication
public class RankApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(RankApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RankApplication.class, args);
    }
}
