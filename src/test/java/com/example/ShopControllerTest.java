package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author mszarlinski on 2015-10-05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GeojsonApplication.class)
@WebAppConfiguration
public class ShopControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    ShopController shopController;

    @Mock
    private ShopRepository shopRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private JsonSerializer jsonSerializer;

    @Before
    public void setup() {
        initMocks(this);

       ReflectionTestUtils.setField(shopController, "shopRepository", shopRepository);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        jsonSerializer = new JsonSerializer(objectMapper);
    }

    @Test
    public void shouldCreateTrip() throws Exception {
        //given
        final Shop shop = Shop.builder()
            .name("name")
            .location(new GeoJsonPoint(-1.5, 3.14))
            .build();

//        doReturn(null).when(tripRepository).insert(any(Shop.class));

        // when
        final byte[] content = jsonSerializer.toJson(shop);
        final ResultActions response = mockMvc.perform(post("/shop")
                .contentType(APPLICATION_JSON)
                .content(content)).andDo(MockMvcResultHandlers.print());

        // then
        verify(shopRepository).insert(shop);

        response
            .andExpect(status().isCreated());
    }
}
