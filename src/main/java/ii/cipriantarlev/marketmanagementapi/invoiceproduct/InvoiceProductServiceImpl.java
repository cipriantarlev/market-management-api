/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

	@Autowired
	private InvoiceProductRepository invoiceProductRepository;

	@Autowired
	private InvoiceProductMapper invoiceProductMapper;

	@Override
	public List<InvoiceProductDTO> findAllByInvoiceId(Long invoiceId) {
		return invoiceProductRepository.findAllByInvoiceId(invoiceId).stream()
				.map(invoiceProduct -> invoiceProductMapper.mapEntityToDTO(invoiceProduct))
				.collect(Collectors.toList());
	}

	@Override
	public InvoiceProductDTO findById(Long id) {
		var invoiceProduct = invoiceProductRepository.findById(id);

		if (invoiceProduct.isPresent()) {
			return invoiceProductMapper.mapEntityToDTO(invoiceProduct.get());
		}

		return null;
	}

	@Override
	public InvoiceProductDTO save(InvoiceProductDTO invoiceProductDTO) {
		var invoiceProduct = invoiceProductRepository.save(invoiceProductMapper.mapDTOToEntity(invoiceProductDTO));
		return invoiceProductMapper.mapEntityToDTO(invoiceProduct);
	}

	@Override
	public void deleteById(Long id) {
		invoiceProductRepository.deleteById(id);
	}
}
