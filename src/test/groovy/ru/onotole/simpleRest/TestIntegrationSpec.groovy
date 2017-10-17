package ru.onotole.simpleRest;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles("test")
@SpringBootTest
class TestIntegrationSpec extends Specification {
    @Autowired
    protected WebApplicationContext context

    private static final int LIMITS_RESPONSE_MEDIUM_DELAY = 150
    private static final BigDecimal NORMAL_LOG_SIGMA = 0.1

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(4567))

    private MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .build()
    }

    void "get nearest velo parking with bike"() {
        given:
        defaultStub()

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/nearestParking?lat=55.7789992&lon=37.6461563&needFreePlace=false")
                .contentType(MediaType.APPLICATION_JSON)
        )

        then:
        response.andExpect(status().is2xxSuccessful())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().json("{\"items\":[{\"id\":\"0454\",\"position\":{\"lat\":55.7789992,\"lon\":37.6461563},\"freePlaces\":0,\"totalPlaces\":12}]}", true))
    }

    def defaultStub() {
        stubFor(get(urlEqualTo("/ride/parkings"))
                .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "text/json; charset=utf-8")
                .withBodyFile("mock-responses/parking.json")
                .withLogNormalRandomDelay(LIMITS_RESPONSE_MEDIUM_DELAY, NORMAL_LOG_SIGMA))
        )
    }

    void "get nearest velo parking"() {
        given:
        defaultStub()

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/nearestParking?lat=55.7789992&lon=37.6461563")
                .contentType(MediaType.APPLICATION_JSON)
        )

        then:
        response.andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().json("{\"items\":[{\"id\":\"0454\",\"position\":{\"lat\":55.7789992,\"lon\":37.6461563},\"freePlaces\":0,\"totalPlaces\":12}]}", true))
    }

    void "get nearest velo parking with free place"() {
        given:
        defaultStub()

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/nearestParking?lat=55.7789992&lon=37.6461563&needFreePlace=true")
                .contentType(MediaType.APPLICATION_JSON)
        )

        then:
        response.andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().json("{\"items\":[{\"id\":\"0463\",\"position\":{\"lat\":55.7763965,\"lon\":37.6501702},\"freePlaces\":6,\"totalPlaces\":12}]}", true))
    }

}
