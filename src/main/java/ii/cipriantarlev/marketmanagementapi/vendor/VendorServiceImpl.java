/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vendor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private VendorMapper vendorMapper;

	@Autowired
	private EntitiesHistoryService entitiesHistoryService;

	@Override
	public List<VendorDTONoRegions> findAll() {
		List<VendorDTONoRegions> vendorList = vendorRepository.findAllByOrderByIdAsc().stream()
				.map(vendor -> vendorMapper.mapVendorToVendorDTONoRegions(vendor))
				.collect(Collectors.toList());

		if (vendorList == null || vendorList.isEmpty()) {
			throw new DTOListNotFoundException("Vendor list not found");
		}

		return vendorList;
	}

	@Override
	public VendorDTO findById(Integer id) {
		Optional<Vendor> vendor = vendorRepository.findById(id);

		if (vendor.isPresent()) {
			return vendorMapper.mapVendorToVendorDTO(vendor.get());
		}

		throw new DTONotFoundException(String.format("Vendor with %d not found", id), id);
	}

	@Override
	public VendorDTO save(VendorDTO vendorDTO) {
		if (vendorDTO.getId() != null && vendorRepository.findById(vendorDTO.getId()).isPresent()) {
			throw new DTOFoundWhenSaveException(
					String.format("Vendor with id: '%d' already exists in database. "
							+ "Please use update in order to save the changes in database", vendorDTO.getId()),
					vendorDTO.getId());
		}

		var vendor = vendorRepository.save(vendorMapper.mapVendorDTOToVendor(vendorDTO));
		entitiesHistoryService.createEntityHistoryRecord(vendor, null, HistoryAction.CREATE);
		return vendorMapper.mapVendorToVendorDTO(vendor);
	}

	@Override
	public VendorDTO update(VendorDTO vendorDTO) {
		var foundVendor = this.findById(vendorDTO.getId());
		var vendor = vendorRepository.save(vendorMapper.mapVendorDTOToVendor(vendorDTO));
		entitiesHistoryService.createEntityHistoryRecord(vendor, foundVendor, HistoryAction.UPDATE);
		return vendorMapper.mapVendorToVendorDTO(vendor);
	}

	@Override
	public void deleteById(Integer id) {
		entitiesHistoryService.createEntityHistoryRecord(this.findById(id), null, HistoryAction.DELETE);
		vendorRepository.deleteById(id);
	}

	@Override
	public List<VendorDTOOnlyName> findAllVendorDTOOnlyName() {
		List<VendorDTOOnlyName> vendorList = vendorRepository.findAllByOrderByIdAsc().stream()
				.map(vendor -> vendorMapper.mapEntityToVendorDTOOnlyName(vendor))
				.collect(Collectors.toList());

		if (vendorList == null || vendorList.isEmpty()) {
			throw new DTOListNotFoundException("Vendor list not found");
		}

		return vendorList;
	}
}
