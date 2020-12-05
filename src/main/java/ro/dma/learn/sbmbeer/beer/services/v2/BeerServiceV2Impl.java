package ro.dma.learn.sbmbeer.beer.services.v2;

import org.springframework.stereotype.Service;
import ro.dma.learn.sbmbeer.beer.web.v2.BeerDtoV2;
import ro.dma.learn.sbmbeer.beer.web.v2.BeerStyleEnum;

import java.util.UUID;

@Service
public class BeerServiceV2Impl implements BeerServiceV2 {

    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return BeerDtoV2.builder().id(beerId).beerName("Azuga").beerStyle(BeerStyleEnum.ALE).build();
    }

    @Override
    public BeerDtoV2 createBeer(BeerDtoV2 beerDto) {
        // todo: to implement
        beerDto.setId(UUID.randomUUID());
        return beerDto;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {
        // todo: to implement
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        // todo: to implement
    }
}
