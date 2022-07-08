package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceChangingActMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PriceChangingActDTO mapEntityToDTO(PriceChangingAct priceChangingAct) {
        return modelMapper.map(priceChangingAct, PriceChangingActDTO.class);
    }

    public PriceChangingAct mapDTOToEntity(PriceChangingActDTO priceChangingActDTO) {
        return modelMapper.map(priceChangingActDTO, PriceChangingAct.class);
    }
}
