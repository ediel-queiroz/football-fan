package com.github.ediel_queiroz.footballfan.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ediel_queiroz.footballfan.business.Match;

record MatchMessage(@JsonProperty("Eid") String matchId, @JsonProperty("Tr1") String homeTeamScore,
                           @JsonProperty("Tr2") String awayTeamScore, @JsonProperty("Esd") String matchStartDate,
                           @JsonProperty("Eps") String matchStatus) {
    Match toMatch(final String country, final String leagueName) {
        return new Match(matchId(), homeTeamScore(), awayTeamScore(), matchStartDate(), matchStatus(), country, leagueName);
    }
}
