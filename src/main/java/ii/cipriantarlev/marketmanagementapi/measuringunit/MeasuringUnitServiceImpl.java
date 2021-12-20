/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.measuringunit;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.core.AuthenticationInformation;
import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

@Service
public class MeasuringUnitServiceImpl implements MeasuringUnitService {

    @Autowired
    private MeasuringUnitRepository measuringUnitRepository;

    @Autowired
    private MeasuringUnitMapper measuringUnitMapper;

    @Autowired
    private EntitiesHistoryService entitiesHistoryService;

    @Autowired
    private AuthenticationInformation authenticationInformation;

    @Override
    public List<MeasuringUnitDTO> findAll() {
        List<MeasuringUnitDTO> measuringUnits = measuringUnitRepository.findAll().stream()
                .map(measuringUnit -> measuringUnitMapper.mapEntityToDTO(measuringUnit))
                .collect(Collectors.toList());

        if (measuringUnits == null || measuringUnits.isEmpty()) {
            throw new DTOListNotFoundException("Measuring unit list not found");
        }

        return measuringUnits;
    }

    @Override
    public MeasuringUnitDTO findById(Integer id) {
        Optional<MeasuringUnit> measuringUnit = measuringUnitRepository.findById(id);

        if (measuringUnit.isPresent()) {
            return measuringUnitMapper.mapEntityToDTO(measuringUnit.get());
        }

        throw new DTONotFoundException(String.format("Measuring Unit with %d not found", id), id);
    }

    @Override
    public MeasuringUnitDTO save(MeasuringUnitDTO measuringUnitDTO) {
        if (measuringUnitDTO.getId() != null
                && measuringUnitRepository.findById(measuringUnitDTO.getId()).isPresent()) {
            throw new DTOFoundWhenSaveException(
                    String.format(
                            "Measuring Unit with id: '%d' already exists in database. "
                                    + "Please use update in order to save the changes in database",
                            measuringUnitDTO.getId()),
                    measuringUnitDTO.getId());
        }

        var measuringUnit = measuringUnitRepository.save(measuringUnitMapper.mapDTOToEntity(measuringUnitDTO));
        entitiesHistoryService
                .createEntityHistoryRecord(measuringUnit, null, HistoryAction.CREATE,
                        authenticationInformation.getAuthentication().getName());
        return measuringUnitMapper.mapEntityToDTO(measuringUnit);
    }

    @Override
    public MeasuringUnitDTO update(MeasuringUnitDTO measuringUnitDTO) {
        var foundMeasuringUnit = this.findById(measuringUnitDTO.getId());
        var measuringUnit = measuringUnitRepository.save(measuringUnitMapper.mapDTOToEntity(measuringUnitDTO));
        entitiesHistoryService
                .createEntityHistoryRecord(measuringUnit, foundMeasuringUnit, HistoryAction.UPDATE,
                        authenticationInformation.getAuthentication().getName());
        return measuringUnitMapper.mapEntityToDTO(measuringUnit);
    }

    @Override
    public void deleteById(Integer id) {
        var measuringUnit = this.findById(id);
        entitiesHistoryService
                .createEntityHistoryRecord(measuringUnit, null, HistoryAction.DELETE,
                        authenticationInformation.getAuthentication().getName());
        measuringUnitRepository.deleteById(id);
    }
}
