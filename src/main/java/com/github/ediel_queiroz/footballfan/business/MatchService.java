package com.github.ediel_queiroz.footballfan.business;

public class MatchService {

    private SimpleCacheManager<String, Match> cacheManager;

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
