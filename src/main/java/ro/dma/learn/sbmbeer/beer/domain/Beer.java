package ro.dma.learn.sbmbeer.beer.domain;

import lombok.*;
import ro.dma.learn.sbmbeer.beer.web.v2.BeerStyleEnum;

import java.util.UUID;
@Getter
@Setter
@Builder
public class Beer {
    private UUID id;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private Long upc;
}
