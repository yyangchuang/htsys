package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * redis的配置类
 * @author ycc
 */
@Configuration
@PropertySource("classpath:application.yml")
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.maxIdle}")
    private int maxIdle;

    @Value("${spring.redis.pool.maxWait}")
    private int maxWait;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.blockWhenExhausted}")
    private boolean blockWhenExhausted;

    @Value("${spring.redis.jmxEnabled}")
    private boolean jmxEnabled;

    @Bean
    public JedisPool jedisPoolFactory() {
        System.out.println("JedisPool注入开始...");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setJmxEnabled(jmxEnabled);
        // 连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(jmxEnabled);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        System.out.println("JedisPool注入成功...");
        return jedisPool;
    }


}
