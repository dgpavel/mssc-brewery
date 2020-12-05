package ro.dma.learn.sbmbeer.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CustomerDto {
    @Null
    private UUID id;
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
}
