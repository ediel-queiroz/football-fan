package com.github.ediel_queiroz.footballfan.integration.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ediel_queiroz.footballfan.integration.model.MatchesMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LiveScoreListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LiveScoreListener.class);
    private final ObjectMapper objectMapper;

    public LiveScoreListener(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(groupId = "footballfan", topics = "live-results", autoStartup = "${listen.auto.start:true}")
    public void listen(final ConsumerRecord<String, String> matchesRecord) {
        var key = matchesRecord.key();
        LOGGER.info("Receiving live results with key " + key);
        try {
            MatchesMessage matches = objectMapper.readValue(matchesRecord.value(), MatchesMessage.class);
        } catch (JsonProcessingException e) {
            throw new SerializationException("It is not possible to deserialize value with key [ " + key + "] ");
        }
    }

}