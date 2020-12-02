package ro.dma.learn.sbmbeer.beer;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
}
