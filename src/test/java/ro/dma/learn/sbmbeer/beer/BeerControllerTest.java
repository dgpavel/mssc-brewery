package ro.dma.learn.sbmbeer.beer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.dma.learn.sbmbeer.beer.services.BeerService;
import ro.dma.learn.sbmbeer.beer.web.BeerController;
import ro.dma.learn.sbmbeer.beer.web.BeerDto;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @MockBean
    BeerService beerService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    private static BeerDto validBeer;
    private static BeerDto dbBeer;
    private static final String BEERS_PATH_V1 = "/api/v1/beers";

    @BeforeAll
    public static void setUp() {
        validBeer = BeerDto.builder()
                .beerName("Azuga")
                .beerStyle("Nepasteurizata")
                .upc(123456789012L)
                .build();
        dbBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName(validBeer.getBeerName())
                .beerStyle(validBeer.getBeerStyle())
                .upc(validBeer.getUpc())
                .build();
    }

    @Test
    void getBeer() throws Exception {
        // given
        given(beerService.getBeerById(any(UUID.class))).willReturn(dbBeer);
        // when
        mockMvc.perform(get(BEERS_PATH_V1 + "/" + dbBeer.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(dbBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(dbBeer.getBeerName())))
                .andExpect(jsonPath("$.beerStyle", is(dbBeer.getBeerStyle())))
                .andExpect(jsonPath("$.upc", is(dbBeer.getUpc())));
    }

    @Test
    void saveNewBeer() throws Exception {
        // given
        given(beerService.createBeer(any())).willReturn(dbBeer);
        String beerDtoJson = objectMapper.writeValueAsString(validBeer);
        // when
        mockMvc.perform(post(BEERS_PATH_V1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/v1/beers/" + dbBeer.getId().toString()));

    }

    @Test
    void updateBeer() throws Exception {
        // given
        String beerDtoJson = objectMapper.writeValueAsString(validBeer);
        // when
        mockMvc.perform(put(BEERS_PATH_V1 + "/" + dbBeer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
        // then
        then(beerService).should().updateBeer(any(), any());

    }
}
