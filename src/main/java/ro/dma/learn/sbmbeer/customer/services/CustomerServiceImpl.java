package ro.dma.learn.sbmbeer.customer.services;

import java.util.UUID;

import org.springframework.stereotype.Service;
import ro.dma.learn.sbmbeer.customer.web.CustomerDto;

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
