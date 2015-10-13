package com.example;

import static org.hamcrest.Matchers.endsWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mszarlinski on 2015-10-05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GeojsonApplication.class)
public class ShopControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ShopRepository tripRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private JsonSerializer jsonSerializer;

    @Before
    public void setup() {
        initMocks(this);

        final ShopController shopController = new ShopController(tripRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(shopController).build();

        jsonSerializer = new JsonSerializer(objectMapper);
    }

    @Test
    public void shouldCreateTrip() throws Exception {
        //given
        final Shop shop = Shop.builder()
            .name("name")
            .location(new GeoJsonPoint(-1.5, 3.14))
            .build();

        doReturn(null).when(tripRepository).insert(any(Shop.class));

        // when
        final ResultActions response = mockMvc.perform(post("/shop")
            .contentType(APPLICATION_JSON)
            .content(jsonSerializer.toJson(shop)));

        // then
        verify(tripRepository).insert(shop);

        response
            .andExpect(status().isCreated());
    }
}
