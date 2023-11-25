package com.github.ediel_queiroz.footballfan.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MatchesMessage(@JsonProperty("Cnm") String countryName, @JsonProperty("Snm") String leagueName,
                             @JsonProperty("Scu") boolean isCup, @JsonProperty("Events") List<Match> matches) {
    public record Match(@JsonProperty("Eid") String matchId, @JsonProperty("Tr1") String homeTeamScore,
                        @JsonProperty("Tr2") String awayTeamScore, @JsonProperty("Esd") String matchStartDate,
                        @JsonProperty("Eps") String matchStatus) {
    }
}
