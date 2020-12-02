package ro.dma.learn.sbmbeer.customer;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
}
