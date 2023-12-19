package com.github.ediel_queiroz.footballfan.business;

import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final SimpleCacheManager<String, Match> cacheManager;

    public MatchService(final SimpleCacheManager<String, Match> cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void add(final Match match) {
        cacheManager.put(match.id(), match);
    }

    public Match get(String id) {
        return cacheManager.get(id);
    }
}
