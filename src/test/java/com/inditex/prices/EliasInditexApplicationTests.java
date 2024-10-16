package com.inditex.prices;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = EliasInditexApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@AutoConfigureTestDatabase
public class EliasInditexApplicationTests {

    // TODO: cambiar tests integración

    @Autowired
    private MockMvc mockMvc;

    // Este test verifica que la aplicación carga Spring correctamente
    @Test
    void contextLoads() {
        assertNotNull(mockMvc);
    }

    // Tests para validar los resultados que devuelve el servicio de precios
    private static final String brandId = "1";
    private static final String productId = "35455";

    // GET
    // http://localhost:8080/api/prices/search?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1
    @Disabled
    @Test
    public void testGetApplicablePrice1() throws Exception {
        mockMvc.perform(get("/api/prices/search")
                .param("applicationDate", "2020-06-14T10:00:00")
                .param("productId", productId)
                .param("brandId", brandId))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"price\": 35.50}"));
    }

    // GET
    // http://localhost:8080/api/prices/search?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1
    @Disabled
    @Test
    public void testGetApplicablePrice2() throws Exception {
        mockMvc.perform(get("/api/prices/search")
                .param("applicationDate", "2020-06-14T16:00:00")
                .param("productId", productId)
                .param("brandId", brandId))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"price\": 25.45}"));
    }

    // Probar que un happy path no existe
    // @Test
    // public void
    // givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
    // throws ClientProtocolException, IOException {

    // // Given
    // String name = RandomStringUtils.randomAlphabetic(8);
    // HttpUriRequest request = new HttpGet("https://localhost:8080/api/prices/" +
    // name);

    // // When
    // HttpResponse httpResponse =
    // HttpClientBuilder.create().build().execute(request);

    // // Then
    // assertThat(
    // httpResponse.getStatusLine().getStatusCode(),
    // equalTo(HttpStatus.SC_NOT_FOUND));
    // }
}
