package ro.dma.learn.sbmbeer.beer.mappers;

import org.mapstruct.Mapper;
import ro.dma.learn.sbmbeer.beer.domain.Beer;
import ro.dma.learn.sbmbeer.beer.web.BeerDto;

@Mapper
public interface BeerMapper {

    Beer dtoToEntity(BeerDto beerDto);

    BeerDto entityToDto(Beer beer);
}
