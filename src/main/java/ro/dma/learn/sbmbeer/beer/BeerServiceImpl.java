package ro.dma.learn.sbmbeer.beer;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID()).beerName("Azuga").beerStyle("classic").build();
    }

}
