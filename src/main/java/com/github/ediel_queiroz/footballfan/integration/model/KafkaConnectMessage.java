package com.github.ediel_queiroz.footballfan.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KafkaConnectMessage(@JsonProperty(value = "payload", required = true) KafkaConnectPayload payload) {

}
