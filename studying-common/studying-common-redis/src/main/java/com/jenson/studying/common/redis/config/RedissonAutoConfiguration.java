package com.jenson.studying.common.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(RedissonClient.class)
    @ConditionalOnProperty(prefix = "redisson", name = "enabled", havingValue = "true")
    public RedissonClient redissonClient(RedissonProperties properties) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(properties.getAddress());
        singleServerConfig.setDatabase(properties.getDatabase());
        singleServerConfig.setTimeout(properties.getTimeout());
        singleServerConfig.setIdleConnectionTimeout(properties.getIdleConnectionTimeout());
        singleServerConfig.setConnectionMinimumIdleSize(properties.getConnectionMinimumIdleSize());
        singleServerConfig.setConnectionPoolSize(properties.getConnectionPoolSize());
        if (StringUtils.hasText(properties.getPassword())) {
            singleServerConfig.setPassword(properties.getPassword());
        }
        return Redisson.create(config);
    }
}
