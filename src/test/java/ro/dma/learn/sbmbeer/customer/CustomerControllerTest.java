package ro.dma.learn.sbmbeer.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @MockBean
    CustomerService customerService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    private static CustomerDto validCustomer;
    private static CustomerDto dbCustomer;
    private static final String CUSTOMERS_PATH_V1 = "/api/v1/customers";

    @BeforeAll
    public static void setUp() {
        validCustomer = CustomerDto.builder()
                .name("ABC")
                .build();
        dbCustomer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name(validCustomer.getName())
                .build();
    }

    @Test
    void getCustomer() throws Exception {
        // given
        given(customerService.getCustomerById(any(UUID.class))).willReturn(dbCustomer);
        //
        mockMvc.perform(get(CUSTOMERS_PATH_V1 + "/" + dbCustomer.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(dbCustomer.getId().toString())))
                .andExpect(jsonPath("$.name", is(dbCustomer.getName())));
    }

    @Test
    void saveNewCustomer() throws Exception {
        // given
        String customerDtoJson = objectMapper.writeValueAsString(validCustomer);
        given(customerService.createCustomer(any())).willReturn(dbCustomer);
        // when
        mockMvc.perform(post(CUSTOMERS_PATH_V1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerDtoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/v1/customers/" + dbCustomer.getId().toString()));

    }

    @Test
    void updateBeer() throws Exception {
        // given
        String customerDtoJson = objectMapper.writeValueAsString(validCustomer);
        // when
        mockMvc.perform(put(CUSTOMERS_PATH_V1 + "/" + dbCustomer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerDtoJson))
                .andExpect(status().isNoContent());
        // then
        then(customerService).should().updateCustomer(any(), any());

    }
}
