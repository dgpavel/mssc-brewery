package ro.dma.learn.sbmbeer.beer.services;

import java.util.UUID;

import org.springframework.stereotype.Service;
import ro.dma.learn.sbmbeer.beer.web.BeerDto;

@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(beerId).beerName("Azuga").beerStyle("Nepasteurizata").build();
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        // todo: to implement
        beerDto.setId(UUID.randomUUID());
        return beerDto;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        // todo: to implement
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        // todo: to implement
    }
}
