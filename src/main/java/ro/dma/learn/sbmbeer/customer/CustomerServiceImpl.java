package ro.dma.learn.sbmbeer.customer;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder().id(UUID.randomUUID()).name("John Doe").build();
    }

}
