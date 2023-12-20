package com.github.ediel_queiroz.footballfan.integration.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ediel_queiroz.footballfan.JsonConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KafkaConnectMessageConverterTest {

    private static final ObjectMapper MAPPER = new JsonConfiguration().objectMapper();

    @Test
    @DisplayName("Should deserialize kafka connect message successfully")
    public void shouldDeserializeKafkaConnectMessage() throws JsonProcessingException {
        KafkaConnectMessage message = MAPPER.readValue(samplePayload(), KafkaConnectMessage.class);
        Assertions.assertThat(message).isNotNull().extracting("payload").isNotNull().extracting("value").isEqualTo("{\"Sid\":\"15376\",\"Snm\":\"Azadegan League\",\"Scd\":\"azadegan-league\",\"Cid\":\"31\",\"Cnm\":\"Iran\",\"Csnm\":\"Iran\",\"Ccd\":\"iran\",\"Scu\":0,\"Sds\":\"Azadegan League\",\"Chi\":0,\"Shi\":0,\"Ccdiso\":\"IRN\",\"Sdn\":\"Azadegan League\",\"Events\":[{\"Eid\":\"1107695\",\"Pids\":{\"8\":\"1107695\",\"1\":\"4352212\",\"6\":\"46061583\",\"32\":\"2867653\"},\"Sids\":{\"8\":\"15376\",\"1\":\"887258\",\"12\":\"SBTC3_59558\",\"6\":\"78309\",\"32\":\"21030\"},\"Tr1\":\"2\",\"Tr2\":\"0\",\"Trh1\":\"2\",\"Trh2\":\"0\",\"Tr1OR\":\"2\",\"Tr2OR\":\"0\",\"T1\":[{\"Nm\":\"Fajr Sepasi\",\"ID\":\"4356\",\"Img\":\"enet/101623.png\",\"Abr\":\"FAJ\",\"tbd\":0,\"Gd\":1,\"Pids\":{\"12\":[\"SBTP_22499\"],\"1\":[\"101623\"],\"6\":[\"53717\"],\"8\":[\"4356\"],\"32\":[\"2014\"]},\"CoNm\":\"Iran\",\"CoId\":\"IRN\",\"HasVideo\":false}],\"T2\":[{\"Nm\":\"Pars Jonoubi Jam Bushehr\",\"ID\":\"5046\",\"Img\":\"enet/775750.png\",\"Abr\":\"PAR\",\"tbd\":0,\"Gd\":1,\"Pids\":{\"12\":[\"SBTP_389560\"],\"1\":[\"775750\"],\"6\":[\"295810\"],\"8\":[\"5046\"],\"32\":[\"7435\"]},\"CoNm\":\"Iran\",\"CoId\":\"IRN\",\"HasVideo\":false}],\"Eps\":\"47'\",\"Esid\":3,\"Epr\":1,\"Ecov\":0,\"Ern\":17,\"ErnInf\":\"17\",\"Et\":1,\"Esd\":20231220173000,\"Eact\":1,\"EO\":807141391,\"EOX\":807141391,\"IncsX\":1,\"ComX\":0,\"LuX\":0,\"StatX\":0,\"SubsX\":0,\"SDFowX\":0,\"SDInnX\":0,\"LuC\":0,\"Ehid\":0,\"Spid\":1,\"Stg\":{\"Sid\":\"15376\",\"Snm\":\"Azadegan League\",\"Scd\":\"azadegan-league\",\"Cid\":\"31\",\"Cnm\":\"Iran\",\"Csnm\":\"Iran\",\"Ccd\":\"iran\",\"Scu\":0,\"Sds\":\"Azadegan League\",\"Chi\":0,\"Shi\":0,\"Ccdiso\":\"IRN\",\"Sdn\":\"Azadegan League\"},\"Pid\":8}]}");
    }

    private String samplePayload() {
        return """
                {
                    "schema": {
                       "type": "struct",
                       "fields": [
                          {
                             "type": "string",
                             "optional": false,
                             "doc": "HTTP Record Value",
                             "field": "value"
                          },
                          {
                             "type": "string",
                             "optional": true,
                             "doc": "HTTP Record Key",
                             "field": "key"
                          },
                          {
                             "type": "int64",
                             "optional": true,
                             "doc": "HTTP Record Timestamp",
                             "field": "timestamp"
                          }
                       ],
                       "optional": false,
                       "name": "com.github.castorm.kafka.connect.http.Value",
                       "doc": "Message Value"
                    },
                    "payload": {
                       "value": "{\\"Sid\\":\\"15376\\",\\"Snm\\":\\"Azadegan League\\",\\"Scd\\":\\"azadegan-league\\",\\"Cid\\":\\"31\\",\\"Cnm\\":\\"Iran\\",\\"Csnm\\":\\"Iran\\",\\"Ccd\\":\\"iran\\",\\"Scu\\":0,\\"Sds\\":\\"Azadegan League\\",\\"Chi\\":0,\\"Shi\\":0,\\"Ccdiso\\":\\"IRN\\",\\"Sdn\\":\\"Azadegan League\\",\\"Events\\":[{\\"Eid\\":\\"1107695\\",\\"Pids\\":{\\"8\\":\\"1107695\\",\\"1\\":\\"4352212\\",\\"6\\":\\"46061583\\",\\"32\\":\\"2867653\\"},\\"Sids\\":{\\"8\\":\\"15376\\",\\"1\\":\\"887258\\",\\"12\\":\\"SBTC3_59558\\",\\"6\\":\\"78309\\",\\"32\\":\\"21030\\"},\\"Tr1\\":\\"2\\",\\"Tr2\\":\\"0\\",\\"Trh1\\":\\"2\\",\\"Trh2\\":\\"0\\",\\"Tr1OR\\":\\"2\\",\\"Tr2OR\\":\\"0\\",\\"T1\\":[{\\"Nm\\":\\"Fajr Sepasi\\",\\"ID\\":\\"4356\\",\\"Img\\":\\"enet/101623.png\\",\\"Abr\\":\\"FAJ\\",\\"tbd\\":0,\\"Gd\\":1,\\"Pids\\":{\\"12\\":[\\"SBTP_22499\\"],\\"1\\":[\\"101623\\"],\\"6\\":[\\"53717\\"],\\"8\\":[\\"4356\\"],\\"32\\":[\\"2014\\"]},\\"CoNm\\":\\"Iran\\",\\"CoId\\":\\"IRN\\",\\"HasVideo\\":false}],\\"T2\\":[{\\"Nm\\":\\"Pars Jonoubi Jam Bushehr\\",\\"ID\\":\\"5046\\",\\"Img\\":\\"enet/775750.png\\",\\"Abr\\":\\"PAR\\",\\"tbd\\":0,\\"Gd\\":1,\\"Pids\\":{\\"12\\":[\\"SBTP_389560\\"],\\"1\\":[\\"775750\\"],\\"6\\":[\\"295810\\"],\\"8\\":[\\"5046\\"],\\"32\\":[\\"7435\\"]},\\"CoNm\\":\\"Iran\\",\\"CoId\\":\\"IRN\\",\\"HasVideo\\":false}],\\"Eps\\":\\"47'\\",\\"Esid\\":3,\\"Epr\\":1,\\"Ecov\\":0,\\"Ern\\":17,\\"ErnInf\\":\\"17\\",\\"Et\\":1,\\"Esd\\":20231220173000,\\"Eact\\":1,\\"EO\\":807141391,\\"EOX\\":807141391,\\"IncsX\\":1,\\"ComX\\":0,\\"LuX\\":0,\\"StatX\\":0,\\"SubsX\\":0,\\"SDFowX\\":0,\\"SDInnX\\":0,\\"LuC\\":0,\\"Ehid\\":0,\\"Spid\\":1,\\"Stg\\":{\\"Sid\\":\\"15376\\",\\"Snm\\":\\"Azadegan League\\",\\"Scd\\":\\"azadegan-league\\",\\"Cid\\":\\"31\\",\\"Cnm\\":\\"Iran\\",\\"Csnm\\":\\"Iran\\",\\"Ccd\\":\\"iran\\",\\"Scu\\":0,\\"Sds\\":\\"Azadegan League\\",\\"Chi\\":0,\\"Shi\\":0,\\"Ccdiso\\":\\"IRN\\",\\"Sdn\\":\\"Azadegan League\\"},\\"Pid\\":8}]}",
                       "key": "15376",
                       "timestamp": 1703072227569
                    }
                 }
                """;
    }
}
