package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.pricechangingactproduct.PriceChangingActProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PriceChangingActServiceImpl implements PriceChangingActService {

    @Autowired
    private PriceChangingActRepository repository;

    @Autowired
    private PriceChangingActMapper mapper;

    @Autowired
    private PriceChangingActProductService priceChangingActProductService;

    private int updatedRows;

    @Override
    public List<PriceChangingActDTO> findAll() {
        List<PriceChangingActDTO> priceChangingActs = repository.findAll().stream()
                .map(priceChangingAct -> mapper.mapEntityToDTO(priceChangingAct))
                .toList();

        if(!priceChangingActs.isEmpty()) {
            return priceChangingActs;
        }

        throw new DTOListNotFoundException("PriceChangingAct list not found");
    }

    @Override
    public PriceChangingActDTO findById(UUID id) {
        var priceChangingAct = repository.findById(id);

        if(priceChangingAct.isPresent())
            return mapper.mapEntityToDTO(priceChangingAct.get());

        throw new DTONotFoundException(String.format("PriceChangingAct with %s not found", id), id);
    }

    @Override
    public PriceChangingActDTO save(PriceChangingActDTO priceChangingActDTO) {
        if(Objects.nonNull(priceChangingActDTO.getId()) && repository.findById(priceChangingActDTO.getId()).isPresent())
            throw new DTOFoundWhenSaveException(
                    String.format(
                            "PriceChangingAct with id: '%s' already exists in database. "
                                    + "Please use update in order to save the changes in database",
                            priceChangingActDTO.getId()),
                    priceChangingActDTO.getId());

        return mapper.mapEntityToDTO(repository.save(mapper.mapDTOToEntity(priceChangingActDTO)));
    }

    @Override
    public PriceChangingActDTO update(PriceChangingActDTO priceChangingActDTO) {
        findById(priceChangingActDTO.getId());
        return mapper.mapEntityToDTO(repository.save(mapper.mapDTOToEntity(priceChangingActDTO)));
    }

    @Override
    public void deleteById(UUID id) {
        deleteRetailedPriceChangingActProducts(findById(id));
        repository.deleteById(id);
    }

    @Override
    public int updateIsApprovedMarker(Map<Boolean, List<UUID>> priceChangingActsToUpdate) {
        updatedRows = 0;
        priceChangingActsToUpdate.forEach((isApproved, actIds) -> actIds.forEach(id -> {
            findById(id);
            repository.updateIsApprovedMarker(isApproved, id);
            updatedRows = actIds.size();
        }));

        return updatedRows;
    }

    @Override
    public List<PriceChangingAct> findByInvoiceId(long invoiceId) {
        return repository.findByInvoiceId(invoiceId);
    }

    private void deleteRetailedPriceChangingActProducts(PriceChangingActDTO priceChangingActDTO) {
        priceChangingActProductService.findAllByPriceChangingActId(priceChangingActDTO.getId())
                .forEach(priceChangingActProductDTO -> priceChangingActProductService.deleteById(priceChangingActProductDTO.getId()));
    }
}
