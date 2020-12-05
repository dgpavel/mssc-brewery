package ro.dma.learn.sbmbeer.customer.mappers;

import org.mapstruct.Mapper;
import ro.dma.learn.sbmbeer.customer.domain.Customer;
import ro.dma.learn.sbmbeer.customer.web.CustomerDto;

@Mapper
public interface CustomerMapper {

    Customer dtoToEntity(CustomerDto customerDto);

    CustomerDto entityToDto(Customer customer);
}
