package ii.cipriantarlev.marketmanagementapi.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

	/**
	 * Front-end path
	 */
	public final String LOCAL_HOST = "http://localhost:3000";

	/**
	 * Paths related to id
	 */
	public final String ID_PATH = "/{id}";
	public final String INVOICE_ID_PATH = "/{invoiceId}";
	public final String PRODUCT_ID_ID_PATH = "/{productId}";

	/**
	 * Root paths
	 */
	public final String BARCODES_ROOT_PATH = "/barcodes";
	public final String CATEGORIES_ROOT_PATH = "/categories";
	public final String DOCUMENT_TYPE_ROOT_PATH = "/document-types";
	public final String INVOICES_ROOT_PATH = "/invoices";
	public final String MY_ORGANIZATIONS_ROOT_PATH = "/my-organizations";
	public final String VENDORS_ROOT_PATH = "/vendors";
	public final String INVOICE_PRODUCT_ROOT_PATH = "/invoice-products";
	public final String MEASURING_UNITS_ROOT_PATH = "/measuring-units";

	/**
	 * Single paths
	 */
	public final String PRODUCT_PATH = "/product";
}
