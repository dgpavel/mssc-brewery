package ro.dma.learn.sbmbeer.beer;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID()).beerName("Azuga").beerStyle("classic").build();
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        // todo: to implement later
        return beerDto;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        // todo: to implement later
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        // todo: to implement later
    }
}
