package com.github.ediel_queiroz.footballfan.business;

public record Match(String id, String homeTeamName, String awayTeamName, String homeTeamScore, String awayTeamScore,
                    String matchStartDate, String matchStatus, String country, String leagueName) {
}
