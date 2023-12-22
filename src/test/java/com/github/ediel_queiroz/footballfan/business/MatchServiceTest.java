package com.github.ediel_queiroz.footballfan.business;

import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {

    private static final Faker faker = new Faker();
    @Mock
    private SimpleCacheManager<String, Match> cacheManager;
    @InjectMocks
    private MatchService service;

    @Test
    @DisplayName("Should add a match")
    public void shouldAddMatch() {
        var match = sampleMatch();

        service.add(match);

        Mockito.verify(cacheManager, Mockito.times(1)).put(match.id(), match);
    }

    @Test
    @DisplayName("Should get a match")
    public void shouldGetMatch() {
        var match = sampleMatch();
        Mockito.when(cacheManager.get(match.id())).thenReturn(match);

        var returned = service.get(match.id());

        Mockito.verify(cacheManager, Mockito.times(1)).get(match.id());
        Assertions.assertThat(returned).isEqualTo(match);
    }

    @Test
    @DisplayName("Should list all matches")
    public void shouldListAllMatches() {
        var match1 = sampleMatch();
        var match2 = sampleMatch();
        Mockito.when(cacheManager.listValues()).thenReturn(List.of(match1, match2));

        List<Match> matches = service.listAll();

        Mockito.verify(cacheManager, Mockito.times(1)).listValues();
        Assertions.assertThat(matches).containsExactly(match1, match2);
    }

    private Match sampleMatch() {
        return new Match(faker.number().digits(7), faker.esports().team(), faker.esports().team(), String.valueOf(faker.number().numberBetween(0, 10)), String.valueOf(faker.number().numberBetween(0, 10)), "20231210003000", String.valueOf(faker.number().numberBetween(0, 90)), faker.country().name(), faker.esports().league());
    }

}
