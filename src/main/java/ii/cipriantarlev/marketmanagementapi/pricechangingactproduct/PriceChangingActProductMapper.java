package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceChangingActProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PriceChangingActProductDTO mapEntityToDTO(PriceChangingActProduct priceChangingActProduct) {
        return modelMapper.map(priceChangingActProduct, PriceChangingActProductDTO.class);
    }

    public PriceChangingActProduct mapDTOToEntity(PriceChangingActProductDTO priceChangingActProductDTO) {
        return modelMapper.map(priceChangingActProductDTO, PriceChangingActProduct.class);
    }
}
