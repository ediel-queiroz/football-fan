package com.github.ediel_queiroz.footballfan.controller.model;

import com.github.ediel_queiroz.footballfan.business.Match;
import com.github.javafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MatchesModelTest {

    private static final Faker faker = new Faker();

    @Test
    @DisplayName("Should build a BuildMatchesList model from Matches")
    public void shouldBuildMatchesListModelFromMatches() {
        var match1 = sampleMatch("Brazil", "Campeonato Brasileiro");
        var match2 = sampleMatch("Portugal", "Liga Portugal");
        var matches = List.of(match1, match2);

        var matchesListModel = MatchesModel.from(matches);

        var leagueCampeonatoBrasileiro = new LeagueModel("Campeonato Brasileiro", List.of(MatchModel.from(match1)));
        var leagueLigaPortugal = new LeagueModel("Liga Portugal", List.of(MatchModel.from(match2)));
        var countryBrazil = new CountryModel("Brazil", List.of(leagueCampeonatoBrasileiro));
        var countryPortugal = new CountryModel("Portugal", List.of(leagueLigaPortugal));
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(matchesListModel.countries()).as("countries quantity").hasSize(2);
            softly.assertThat(matchesListModel.countries()).as("contains Brazil leagues").containsOnlyOnce(countryBrazil);
            softly.assertThat(matchesListModel.countries()).as("contains Portugal leagues").containsOnlyOnce(countryPortugal);
        });
    }

    @Test
    @DisplayName("Should build a BuildMatchesList model from several Matches")
    public void shouldBuildMatchesListModelFromSeveralMatches() {
        var match1 = sampleMatch("Brazil", "Campeonato Brasileiro");
        var match2 = sampleMatch("Portugal", "Liga Portugal");
        var match3 = sampleMatch("Brazil", "Campeonato Brasileiro");
        var match4 = sampleMatch("Brazil", "Copa do Brasil");
        var match5 = sampleMatch("Portugal", "Liga Portugal");
        var match6 = sampleMatch("Spain", "La Liga");
        var matches = List.of(match1, match2, match3, match4, match5, match6);

        var matchesListModel = MatchesModel.from(matches);

        var leagueCampeonatoBrasileiro = new LeagueModel("Campeonato Brasileiro", List.of(MatchModel.from(match1), MatchModel.from(match3)));
        var leagueCopaDoBrasil = new LeagueModel("Copa do Brasil", List.of(MatchModel.from(match4)));
        var leagueLigaPortugal = new LeagueModel("Liga Portugal", List.of(MatchModel.from(match2), MatchModel.from(match5)));
        var leagueLaLiga = new LeagueModel("La Liga", List.of(MatchModel.from(match6)));
        var countryBrazil = new CountryModel("Brazil", List.of(leagueCampeonatoBrasileiro, leagueCopaDoBrasil));
        var countryPortugal = new CountryModel("Portugal", List.of(leagueLigaPortugal));
        var countrySpain = new CountryModel("Spain", List.of(leagueLaLiga));
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(matchesListModel.countries()).as("countries quantity").hasSize(3);
            softly.assertThat(matchesListModel.countries()).as("contains Brazil leagues").containsOnlyOnce(countryBrazil);
            softly.assertThat(matchesListModel.countries()).as("contains Portugal leagues").containsOnlyOnce(countryPortugal);
            softly.assertThat(matchesListModel.countries()).as("contains Spain leagues").containsOnlyOnce(countrySpain);
        });
    }

    private Match sampleMatch(final String countryName, final String leagueName) {
        var country = countryName != null ? countryName : faker.country().name();
        var league = leagueName != null ? leagueName : faker.esports().league();

        return new Match(faker.number().digits(7), faker.esports().team(), faker.esports().team(), String.valueOf(faker.number().numberBetween(0, 10)), String.valueOf(faker.number().numberBetween(0, 10)), "20231210003000", String.valueOf(faker.number().numberBetween(0, 90)), country, league);
    }
}
