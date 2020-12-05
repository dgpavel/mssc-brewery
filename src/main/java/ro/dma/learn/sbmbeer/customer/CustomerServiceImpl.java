package ro.dma.learn.sbmbeer.customer;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder().id(customerId).name("John Doe").build();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        // todo: to implement later
        customerDto.setId(UUID.randomUUID());
        return customerDto;
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        // todo: to implement later
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        // todo: to implement later
    }
}
