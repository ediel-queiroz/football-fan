package com.github.ediel_queiroz.footballfan.controller.model;

import java.util.List;

record CountryModel(String countryName, List<LeagueModel> leagues) {
}
