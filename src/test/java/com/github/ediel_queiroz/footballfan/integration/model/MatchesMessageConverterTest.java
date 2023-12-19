package com.github.ediel_queiroz.footballfan.integration.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ediel_queiroz.footballfan.JsonConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class MatchesMessageConverterTest {

    private static ObjectMapper MAPPER;

    @BeforeAll
    public static void setup() {
        MAPPER = new JsonConfiguration().objectMapper();
    }

    @Test
    public void shouldNotThrowExceptionDueToUnknownFields() {
        assertThatNoException().isThrownBy(() -> {
            MAPPER.readValue(samplePayload(), MatchesMessage.class);
        });
    }

    @Test
    public void shouldDeserializeJsonIntoMatchesMessage() throws JsonProcessingException {
        // act
        MatchesMessage matchesMessage = MAPPER.readValue(samplePayload(), MatchesMessage.class);

        // assert
        assertThat(matchesMessage).extracting("countryName", "leagueName", "isCup").containsExactly("Italy", "Serie C: Group B", false);
        assertThat(matchesMessage.matchMessages()).hasSize(1).containsExactly(new MatchesMessage.MatchMessage("1046668", "1", "0", "20231113003000", "51"));
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
