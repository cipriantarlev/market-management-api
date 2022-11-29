package ii.cipriantarlev.marketmanagementapi.utils;

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
	public final String PRODUCT_ID_PATH = "/{productId}";

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
	public final String PLU_ROOT_PATH = "/plu";
	public final String PRODUCTS_ROOT_PATH = "/products";
	public final String PRODUCTS_CODE_ROOT_PATH = "/products-code";
	public final String REGIONS_ROOT_PATH = "/regions";
	public final String ROLES_ROOT_PATH = "/roles";
	public final String SUBCATEGORIES_ROOT_PATH = "/subcategories";
	public final String USERS_ROOT_PATH = "/users";
	public final String VAT_ROOT_PATH = "/vat";
	public final String PRICE_CHANGING_ACTS_ROOT_PATH = "/price-changing-acts";
	public final String PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH = "/price-changing-act-products";

	/**
	 * Single paths
	 */
	public final String PRODUCT_PATH = "/product";
	public final String BARCODE_VALUE = "/{barcodeValue}";
	public final String CATEGORY_CATEGORY_ID = "/category/{categoryId}";
	public final String INCOME_INVOICES = "/income";
	public final String OUTCOME_INVOICES = "/outcome";
	public final String BARCODE_BY_VALUE = "/barcode/{value}";
	public final String PRODUCT_BY_NAME_ROM = "/product/name-rom/{value}";
	public final String PRODUCT_BY_NAME_RUS = "/product/name-rus/{value}";
	public final String IS_APPROVED = "/isApproved";
	public final String PRODUCT_HISTORY_BY_ID = "/product-history/{productId}";
	public final String IS_CHECKED = "/isChecked";
	public final String PRINT_PRODUCTS = "/printProducts";
	public final String PRICE_CHANGING_ACT_ID = "/price-changing-act/{id}";
	public final String LOGIN_PATH = "/login";
	public final String API_LOGIN_PATH = "/api/login";

}
