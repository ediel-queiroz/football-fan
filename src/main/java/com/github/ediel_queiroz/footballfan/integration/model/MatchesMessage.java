package com.github.ediel_queiroz.footballfan.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ediel_queiroz.footballfan.business.Match;

import java.util.List;

public record MatchesMessage(@JsonProperty("Cnm") String countryName, @JsonProperty("Snm") String leagueName,
                             @JsonProperty("Scu") boolean isCup,
                             @JsonProperty("Events") List<MatchMessage> matchMessages) {

    public List<Match> toMatchList() {
        return this.matchMessages.stream().map(m -> m.toMatch(countryName, leagueName)).toList();
    }

}
