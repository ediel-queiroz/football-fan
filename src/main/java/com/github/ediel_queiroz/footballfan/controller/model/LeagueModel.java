package com.github.ediel_queiroz.footballfan.controller.model;

import java.util.List;

record LeagueModel(String leagueName, List<MatchModel> matches) {
}
