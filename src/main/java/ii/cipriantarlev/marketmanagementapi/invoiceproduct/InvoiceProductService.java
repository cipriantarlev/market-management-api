/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.invoice.Invoice;
import ii.cipriantarlev.marketmanagementapi.product.Product;

import java.util.List;
import java.util.Map;

/**
 * {@link InvoiceProduct} service used to manage it.
 */
public interface InvoiceProductService {

	/**
	 * Method used to find all {@link InvoiceProduct} based on provided
	 * {@link Invoice} id.
	 *
	 * @param invoiceId {@link Invoice} id
	 * @return the list of all {@link InvoiceProduct} related to provided {@link Invoice} id.
	 * If the list is empty the {@link DTOListNotFoundException}
	 * will be thrown.
	 */
	List<InvoiceProductDTO> findAllByInvoiceId(Long invoiceId);

	/**
	 * Method used to find {@link InvoiceProduct} by provided id.
	 *
	 * @param id the id of {@link InvoiceProduct} to be found.
	 * @return the found {@link InvoiceProduct}.
	 * If the {@link InvoiceProduct} is not found the {@link DTONotFoundException}
	 * will be thrown.
	 */
	InvoiceProductDTO findById(Long id);

	/**
	 * Method to save the provided {@link InvoiceProductDTO} as {@link InvoiceProduct}
	 * in the database.
	 *
	 * @param invoiceProductDTO {@link InvoiceProductDTO} that should be saved in the database.
	 * @return the new created {@link InvoiceProductDTO}. If the {@link InvoiceProduct} is found the
	 * {@link DTOFoundWhenSaveException} will be thrown.
	 */
	InvoiceProductDTO save(InvoiceProductDTO invoiceProductDTO);

	/**
	 * Method to update the provided {@link InvoiceProductDTO} as {@link InvoiceProduct}
	 * in the database.
	 *
	 * @param invoiceProductDTO {@link InvoiceProductDTO} that should be updated in the database.
	 * @return the updated {@link InvoiceProductDTO}. If the {@link InvoiceProduct} is not found the
	 * {@link DTONotFoundException} will be thrown.
	 */
	InvoiceProductDTO update(InvoiceProductDTO invoiceProductDTO);

	/**
	 * Method to update {@link Product#isChecked()} marker.
	 *
	 * @param productsToUpdate {@link Map} that consists of keys that hold that value for
	 * {@link Product#isChecked()} marker and values that hold the id of {@link Product}
	 * that should be updated.
	 * @return the number of updated rows.
	 */
	int updateIsProductChecked(Map<Boolean, List<Long>> productsToUpdate);

	/**
	 * Method to delete an {@link InvoiceProductDTO} based on provided id.
	 * If the {@link InvoiceProductDTO} is not found the {@link DTONotFoundException}
	 * will be thrown.
	 *
	 * @param id id the id of the {@link InvoiceProductDTO} to be deleted.
	 */
	void deleteById(Long id);
}
