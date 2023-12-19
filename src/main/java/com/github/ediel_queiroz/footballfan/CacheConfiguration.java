package com.github.ediel_queiroz.footballfan;

import com.github.ediel_queiroz.footballfan.business.Match;
import com.github.ediel_queiroz.footballfan.business.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;

@Configuration
public class CacheConfiguration {

    @Bean
    public SimpleCacheManager<String, Match> cache() {
        return new SimpleCacheManager<>(Collections.synchronizedMap(new HashMap<>()));
    }

}
