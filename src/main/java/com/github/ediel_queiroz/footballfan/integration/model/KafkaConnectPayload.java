package com.github.ediel_queiroz.footballfan.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KafkaConnectPayload(@JsonProperty(value = "value", required = true) String value) {

}
