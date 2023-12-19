package com.github.ediel_queiroz.footballfan.integration.listener;

import com.github.ediel_queiroz.footballfan.business.Match;
import com.github.ediel_queiroz.footballfan.business.SimpleCacheManager;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.time.Duration;

@SpringBootTest
@EmbeddedKafka(ports = 9092, partitions = 1, topics = "live-results")
public class LiveScoreListenerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private SimpleCacheManager<String, Match> cacheManager;

    @Test
    public void shouldReceiveMatchesPayload() {
        kafkaTemplate.send("live-results", "test", samplePayload());

        Assertions.assertThatNoException().isThrownBy(() -> Awaitility.await().atMost(Duration.ofSeconds(1)).until(() -> cacheManager.get("1046668") != null));
    }

    private String samplePayload() {
        return """
                {
                    "Sid": "15242",
                    "Snm": "Serie C: Group B",
                    "Scd": "serie-c-group-b",
                    "badgeUrl": "2023-italy-serie-c.png",
                    "firstColor": "1F3860",
                    "Cid": "50",
                    "Cnm": "Italy",
                    "Csnm": "Italy",
                    "Ccd": "italy",
                    "CompId": "199",
                    "CompN": "Serie C: Group B",
                    "CompD": "Italy",
                    "CompST": "Italy",
                    "Scu": 0,
                    "Sds": "Serie C: Group B",
                    "Chi": 0,
                    "Shi": 0,
                    "Ccdiso": "ITA",
                    "Sdn": "Serie C: Group B",
                    "Events": [
                        {
                            "Eid": "1046668",
                            "Pids": {
                                "8": "1046668",
                                "1": "4288944",
                                "12": "SBTE_29649263",
                                "6": "42819895",
                                "32": "2853658"
                            },
                            "Sids": {
                                "8": "15242",
                                "1": "886492",
                                "12": "SBTC3_59599",
                                "6": "76983",
                                "32": "20870"
                            },
                            "Tr1": "1",
                            "Tr2": "0",
                            "Trh1": "1",
                            "Trh2": "0",
                            "Tr1OR": "1",
                            "Tr2OR": "0",
                            "T1": [
                                {
                                    "Nm": "Sestri Levante",
                                    "ID": "12061",
                                    "Img": "enet/583893.png",
                                    "Abr": "SES",
                                    "tbd": 0,
                                    "Gd": 1,
                                    "Pids": {
                                        "12": [
                                            "SBTP_232277"
                                        ],
                                        "1": [
                                            "583893"
                                        ],
                                        "6": [
                                            "117250"
                                        ],
                                        "8": [
                                            "12061"
                                        ],
                                        "32": [
                                            "4712"
                                        ]
                                    },
                                    "CoNm": "Italy",
                                    "CoId": "ITA",
                                    "HasVideo": false
                                }
                            ],
                            "T2": [
                                {
                                    "Nm": "Fermana",
                                    "ID": "4289",
                                    "Img": "enet/6476.png",
                                    "Abr": "FER",
                                    "tbd": 0,
                                    "Gd": 1,
                                    "Pids": {
                                        "12": [
                                            "SBTP_1132"
                                        ],
                                        "1": [
                                            "6476"
                                        ],
                                        "6": [
                                            "2755"
                                        ],
                                        "8": [
                                            "4289"
                                        ],
                                        "32": [
                                            "769"
                                        ]
                                    },
                                    "CoNm": "Italy",
                                    "CoId": "ITA",
                                    "HasVideo": false
                                }
                            ],
                            "Eps": "51",
                            "Esid": 3,
                            "Epr": 1,
                            "Ecov": 0,
                            "Ern": 13,
                            "ErnInf": "13",
                            "Et": 1,
                            "Esd": 20231113003000,
                            "Eact": 1,
                            "EO": 806895983,
                            "EOX": 806895983,
                            "IncsX": 1,
                            "ComX": 0,
                            "LuX": 1,
                            "StatX": 1,
                            "SubsX": 0,
                            "SDFowX": 0,
                            "SDInnX": 0,
                            "LuC": 1,
                            "Ehid": 0,
                            "Spid": 1,
                            "Stg": {
                                "Sid": "15242",
                                "Snm": "Serie C: Group B",
                                "Scd": "serie-c-group-b",
                                "badgeUrl": "2023-italy-serie-c.png",
                                "firstColor": "1F3860",
                                "Cid": "50",
                                "Cnm": "Italy",
                                "Csnm": "Italy",
                                "Ccd": "italy",
                                "CompId": "199",
                                "CompN": "Serie C: Group B",
                                "CompD": "Italy",
                                "CompST": "Italy",
                                "Scu": 0,
                                "Sds": "Serie C: Group B",
                                "Chi": 0,
                                "Shi": 0,
                                "Ccdiso": "ITA",
                                "Sdn": "Serie C: Group B"
                            },
                            "Pid": 8
                        }
                    ]
                }
                """;
    }
}
