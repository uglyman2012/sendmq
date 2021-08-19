package com.cp.sendmq.rediscache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/10
 */
@EnableCaching
@Configuration
public class RedisCacheConfig {
    /**
     * 2.XX版本的配置
     */
    //@Bean
    //    //public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    //    //    RedisSerializer<String> redisSerializer = new StringRedisSerializer();
    //    //    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
    //    //    ObjectMapper om = new ObjectMapper();
    //    //    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    //    //    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    //    //    // 解决jackson2无法反序列化LocalDateTime的问题
    //    //    om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    //    //    om.registerModule(new JavaTimeModule());
    //    //
    //    //    jackson2JsonRedisSerializer.setObjectMapper(om);
    //    //
    //    //    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();  // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
    //    //    config = config.entryTtl(Duration.ofMinutes(2))
    //    //            //.computePrefixWith(name ->":")
    //    //            .serializeKeysWith((RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)))
    //    //            .serializeValuesWith((RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer)))// 设置缓存的默认过期时间，也是使用Duration设置
    //    //            .disableCachingNullValues();     // 不缓存空值
    //    //
    //    //    // 设置一个初始化的缓存空间set集合
    //    //    Set<String> cacheNames = new HashSet<>();
    //    //    cacheNames.add("catalog_test_id");
    //    //    cacheNames.add("catalog_test_name");
    //    //
    //    //    // 对每个缓存空间应用不同的配置
    //    //    Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
    //    //    configMap.put("catalog_test_id", config);
    //    //    configMap.put("catalog_test_name", config.entryTtl(Duration.ofMinutes(5)));
    //    //
    //    //    RedisCacheManager cacheManager = RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory)) // 使用自定义的缓存配置初始化一个cacheManager
    //    //            .initialCacheNames(cacheNames)// 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
    //    //            .withInitialCacheConfigurations(configMap)
    //    //            //.disableCreateOnMissingCache()//关闭动态创建Cache
    //    //            .transactionAware()//开启事务
    //    //            .build();
    //    //    return cacheManager;
    //    //}
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(redisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("http://127.0.0.1:6379").setPingConnectionInterval(6000 * 10);
        return Redisson.create(config);
    }

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {

        /*
           清理key策略过期策略,不是一定能清除
         */
        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setTTL(1000 * 10);
        cacheConfig.setMaxIdleTime(1000 * 10);
        cacheConfig.setMaxSize(1);
        Map<String, CacheConfig> configMap = new HashMap<>();
        configMap.put("catalog_test_id", cacheConfig);
        configMap.put("didi", cacheConfig);
        RedissonSpringCacheManager redissonSpringCacheManager = new RedissonSpringCacheManager(redissonClient, configMap);
        return redissonSpringCacheManager;
    }
    //@Bean
    //@ConditionalOnClass(JsonJacksonCodec.class)
    //public JsonJacksonCodec geObjMapper(){
    //    ObjectMapper objectMapper = new ObjectMapper().registerModule(new Jdk8Module())
    //            .registerModule(new JavaTimeModule());
    //    JsonJacksonCodec jsonJacksonCodec = new JsonJacksonCodec(objectMapper);
    //    return jsonJacksonCodec;
    //}

    @Bean
    @ConditionalOnClass(ObjectMapper.class)
    public ObjectMapper geObjMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new Jdk8Module());
        return mapper;
    }
}
