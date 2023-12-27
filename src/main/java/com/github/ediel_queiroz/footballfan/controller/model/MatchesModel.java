package com.github.ediel_queiroz.footballfan.controller.model;

import com.github.ediel_queiroz.footballfan.business.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record MatchesModel(List<CountryModel> countries) {
    public static MatchesModel from(List<Match> matches) {
        final var groupedMatches = matches.stream().collect(Collectors.groupingBy(Match::country, Collectors.groupingBy(Match::leagueName)));
        final List<CountryModel> countriesModel = new ArrayList<>();

        groupedMatches.forEach((country, leagues) -> {
            final List<LeagueModel> leaguesModel = new ArrayList<>();
            leagues.forEach((league, leagueMatches) -> leaguesModel.add(new LeagueModel(league, leagueMatches.stream().map(MatchModel::from).toList())));
            countriesModel.add(new CountryModel(country, leaguesModel));
        });
        return new MatchesModel(countriesModel);
    }

}
