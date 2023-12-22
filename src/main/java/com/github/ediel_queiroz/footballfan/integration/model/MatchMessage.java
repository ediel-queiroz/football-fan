package com.github.ediel_queiroz.footballfan.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ediel_queiroz.footballfan.business.Match;

import java.util.List;

record MatchMessage(@JsonProperty("Eid") String matchId, @JsonProperty("Tr1") String homeTeamScore,
                    @JsonProperty("Tr2") String awayTeamScore, @JsonProperty("Esd") String matchStartDate,
                    @JsonProperty("Eps") String matchStatus, @JsonProperty("T1") List<TeamMessage> homeTeam,
                    @JsonProperty("T2") List<TeamMessage> awayTeam) {
    Match toMatch(final String country, final String leagueName) {
        return new Match(matchId(), homeTeam.getFirst().name(), awayTeam.getFirst().name(), homeTeamScore(), awayTeamScore(), matchStartDate(), matchStatus(), country, leagueName);
    }
}
