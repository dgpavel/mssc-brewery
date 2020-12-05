package ro.dma.learn.sbmbeer.customer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Customer {
    private UUID id;
    private String name;
}
