package com.github.ediel_queiroz.footballfan.controller.model;

import com.github.ediel_queiroz.footballfan.business.Match;

record MatchModel(String homeTeamName, String awayTeamName, String homeTeamScore, String awayTeamScore,
                  String matchStartDate, String matchStatus) {
    static MatchModel from(final Match match) {
        return new MatchModel(match.homeTeamName(), match.awayTeamName(), match.homeTeamScore(), match.awayTeamScore(), match.matchStartDate(), match.matchStatus());
    }
}
