package ro.dma.learn.sbmbeer.beer.web.v2;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDtoV2 {
    @Null
    private UUID id;
    @NotBlank
    private String beerName;
    @NotNull
    private BeerStyleEnum beerStyle;
    @Positive
    private Long upc;
}
