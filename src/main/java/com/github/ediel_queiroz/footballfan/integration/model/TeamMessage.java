package com.github.ediel_queiroz.footballfan.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

record TeamMessage(@JsonProperty("Nm") String name) {
}
