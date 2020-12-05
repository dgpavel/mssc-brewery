package ro.dma.learn.sbmbeer.beer.web;

import java.util.UUID;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {
    @Null
    private UUID id;
    @NotBlank
    private String beerName;
    @NotBlank
    private String beerStyle;
    @Positive
    private Long upc;
}
