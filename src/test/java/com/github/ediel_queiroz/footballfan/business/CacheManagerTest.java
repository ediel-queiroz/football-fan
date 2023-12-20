package com.github.ediel_queiroz.footballfan.business;


import com.github.javafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class CacheManagerTest {
    private static final Map<String, Match> cache = Collections.synchronizedMap(new HashMap<>());
    private static final SimpleCacheManager<String, Match> cacheManager = new SimpleCacheManager<>(cache);
    private static final Faker faker = new Faker();

    @BeforeEach
    public void setUp() {
        cache.clear();
    }

    @Test
    @DisplayName("Should put one match in cache")
    public void shouldPutMatchInCache() {
        final Match match = sampleMatch();
        cacheManager.put(match.id(), match);

        assertThat(cache.size()).isEqualTo(1);
        assertThat(cache.get(match.id())).isEqualTo(match);

    }

    @Test
    @DisplayName("Should put three matches in cache")
    public void shouldPutSeveralMatchesInCache() {
        final Match match1 = sampleMatch();
        final Match match2 = sampleMatch();
        final Match match3 = sampleMatch();

        cacheManager.put(match1.id(), match1);
        cacheManager.put(match2.id(), match2);
        cacheManager.put(match3.id(), match3);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(cache.size()).as("Matches in cache").isEqualTo(3);
            softly.assertThat(cache.get(match1.id())).as("Match 1").isEqualTo(match1);
            softly.assertThat(cache.get(match2.id())).as("Match 2").isEqualTo(match2);
            softly.assertThat(cache.get(match3.id())).as("Match 3").isEqualTo(match3);
        });
    }

    @Test
    @DisplayName("Should replace a object when the same key is used")
    public void shouldReplaceObjectWithSameKeyInCache() {
        final Match match1 = sampleMatch();
        final Match match2 = sampleMatch();
        cacheManager.put(match1.id(), match1);
        cacheManager.put(match1.id(), match2);

        assertThat(cache.size()).isEqualTo(1);
        assertThat(cache.get(match1.id())).isEqualTo(match2);
    }

    @Test
    @DisplayName("Should throw NullPointerException when key is null")
    public void shouldThrowNullPointerExceptionWhenKeyIsNull() {
        assertThatNullPointerException().as("Key is required").isThrownBy(() -> cacheManager.put(null, sampleMatch())).withMessage("Key is required");
    }

    @Test
    @DisplayName("Should throw NullPointerException when value is null")
    public void shouldThrowNullPointerExceptionWhenValueIsNull() {
        assertThatNullPointerException().as("Value is required").isThrownBy(() -> cacheManager.put("123", null)).withMessage("Value is required");
    }

    @Test
    @DisplayName("Should get match from cache")
    public void shouldGetMatchFromCache() {
        final Match match = sampleMatch();
        cache.put(match.id(), match);

        assertThat(cacheManager.get(match.id())).isEqualTo(match);

    }

    @Test
    @DisplayName("Should return null when key does not exist")
    public void shouldReturnNullWhenKeyDoesNotExist() {
        Match match = cacheManager.get("123");
        assertThat(match).isNull();
    }

    @Test
    @DisplayName("Should return null when key is null")
    public void shouldReturnNullWhenKeyIsNull() {
        Match match = cacheManager.get(null);
        assertThat(match).isNull();
    }

    @Test
    @DisplayName("Should return all objects from cache")
    public void shouldReturnAllCacheObjects() {
        final Match match1 = sampleMatch();
        final Match match2 = sampleMatch();
        cacheManager.put(match1.id(), match1);
        cacheManager.put(match2.id(), match2);

        List<Match> matches = cacheManager.listValues();

        assertThat(matches).containsExactly(match1, match2);
    }

    @Test
    @DisplayName("Should return no objects from cache")
    public void shouldReturnNoObjectsFromCache() {
        List<Match> matches = cacheManager.listValues();

        assertThat(matches).isEmpty();
    }

    private Match sampleMatch() {
        return new Match(faker.number().digits(7), String.valueOf(faker.number().numberBetween(0, 10)), String.valueOf(faker.number().numberBetween(0, 10)), "20231210003000", String.valueOf(faker.number().numberBetween(0, 90)), faker.country().name(), faker.esports().league());
    }

}
