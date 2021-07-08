package ii.cipriantarlev.marketmanagementapi.vat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VatServiceImpl implements VatService {

	@Autowired
	private VatRepository vatRepository;

	@Autowired
	private VatMapper vatMapper;

	@Override
	public List<VatDTO> findAll() {
		return vatRepository.findAll().stream()
				.map(vat -> vatMapper.mapVatToVatDTO(vat))
				.collect(Collectors.toList());
	}

	@Override
	public VatDTO findById(Integer id) {
		Optional<Vat> vat = vatRepository.findById(id);

		if (vat.isPresent()) {
			return vatMapper.mapVatToVatDTO(vat.get());
		}

		return null;
	}

	@Override
	public VatDTO save(VatDTO vatDTO) {
		var vat = vatRepository.save(vatMapper.mapVatDTOToVat(vatDTO));
		return vatMapper.mapVatToVatDTO(vat);
	}

	@Override
	public void deleteById(Integer id) {
		vatRepository.deleteById(id);
	}
}
