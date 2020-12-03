package ro.dma.learn.sbmbeer.beer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @MockBean
    BeerService beerService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    private static BeerDto validBeer;
    private static final String API_V_1_BEERS = "/api/v1/beers";

    @BeforeAll
    public static void setUp() {
        validBeer = BeerDto.builder().id(UUID.randomUUID())
                .beerName("Azuga")
                .beerStyle("Nepasteurizata")
                .upc(123456789012L)
                .build();
    }

    @Test
    void getBeer() throws Exception {
        // definesc comportament BeerService
        given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);
        //
        mockMvc.perform(get(API_V_1_BEERS + "/" + validBeer.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is("Azuga")));
    }

    @Test
    void createBeer() throws Exception {
        //given
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        UUID beerId = UUID.randomUUID();
        BeerDto savedDto = BeerDto.builder().id(beerId).beerName("New Beer").build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.createBeer(any())).willReturn(savedDto);

        mockMvc.perform(post(API_V_1_BEERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/v1/beers/" + beerId));

    }

    @Test
    void updateBeer() throws Exception {
        //given
        BeerDto beerDto = validBeer;
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        //when
        mockMvc.perform(put(API_V_1_BEERS + "/" + validBeer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerService).should().updateBeer(any(), any());

    }
}
