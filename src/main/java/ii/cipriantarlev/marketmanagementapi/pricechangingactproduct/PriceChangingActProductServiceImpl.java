package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTOForList;
import ii.cipriantarlev.marketmanagementapi.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PriceChangingActProductServiceImpl implements PriceChangingActProductService {

    @Autowired
    private PriceChangingActProductRepository repository;

    @Autowired
    private PriceChangingActProductMapper mapper;

    @Autowired
    private ProductService productService;

    @Override
    public List<PriceChangingActProductDTO> findAllByPriceChangingActId(UUID priceChangingActId) {
        List<PriceChangingActProductDTO> priceChangingActProductDTOList =
                repository.findAllByPriceChangingActId(priceChangingActId).stream()
                        .map(priceChangingActProduct -> mapper.mapEntityToDTO(priceChangingActProduct))
                        .toList();

        if (priceChangingActProductDTOList == null || priceChangingActProductDTOList.isEmpty()) {
            return new ArrayList<>();
        }

        return priceChangingActProductDTOList;
    }

    @Override
    public PriceChangingActProductDTO findById(UUID id) {
        var priceChangingActOptional = repository.findById(id);

        if (priceChangingActOptional.isPresent()) {
            return mapper.mapEntityToDTO(priceChangingActOptional.get());
        }

        throw new DTONotFoundException(String.format("PriceChangingActProduct with %s not found", id), id);
    }

    @Override
    public PriceChangingActProductDTO save(PriceChangingActProductDTO priceChangingActProductDTO) {
        UUID id = priceChangingActProductDTO.getId();
        if(Objects.nonNull(id) && repository.findById(id).isPresent()) {
            throw new DTOFoundWhenSaveException(
                    String.format(
                            "PriceChangingActProduct with id: '%s' already exists in database. "
                                    + "Please use update in order to save the changes in database",
                            id),
                    id);
        }
        updateProductRetailPrice(priceChangingActProductDTO);
        return mapper.mapEntityToDTO(repository.save(mapper.mapDTOToEntity(priceChangingActProductDTO)));
    }

    @Override
    public PriceChangingActProductDTO update(PriceChangingActProductDTO priceChangingActProductDTO) {
        findById(priceChangingActProductDTO.getId());
        updateProductRetailPrice(priceChangingActProductDTO);
        return mapper.mapEntityToDTO(repository.save(mapper.mapDTOToEntity(priceChangingActProductDTO)));
    }

    @Override
    public void deleteById(UUID id) {
        updateRetailPriceWhenPriceChangingActProductIsDeleted(findById(id));
        repository.deleteById(id);
    }

    private void updateRetailPriceWhenPriceChangingActProductIsDeleted(PriceChangingActProductDTO priceChangingActProductDTO) {
        ProductDTOForList product = priceChangingActProductDTO.getProduct();
        product.setRetailPrice(product.getOldRetailPrice());
        product.setOldRetailPrice(BigDecimal.ZERO);
        if(productService.updateRetailPrice(
                product.getRetailPrice(),
                product.getTradeMargin(),
                product.getOldRetailPrice(),
                product.getId()) <= 0)
            throw new DTONotFoundException(String.format("Product with %d not found",
                    product.getId()), product.getId());
    }

    private void updateProductRetailPrice(PriceChangingActProductDTO priceChangingActProductDTO) {
        if(productService.updateRetailPrice(
                priceChangingActProductDTO.getProduct().getRetailPrice(),
                priceChangingActProductDTO.getProduct().getTradeMargin(),
                priceChangingActProductDTO.getProduct().getOldRetailPrice(),
                priceChangingActProductDTO.getProduct().getId()) <= 0)
            throw new DTONotFoundException(String.format("Product with %d not found",
                    priceChangingActProductDTO.getProduct().getId()), priceChangingActProductDTO.getProduct().getId());
    }
}