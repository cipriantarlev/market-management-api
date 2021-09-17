/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/

package ii.cipriantarlev.marketmanagementapi.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeDTO;
import ii.cipriantarlev.marketmanagementapi.category.CategoryDTO;
import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentTypeDTO;
import ii.cipriantarlev.marketmanagementapi.invoice.InvoiceDTO;
import ii.cipriantarlev.marketmanagementapi.invoiceproduct.InvoiceProductDTO;
import ii.cipriantarlev.marketmanagementapi.measuringunit.MeasuringUnitDTO;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTO;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.plu.PluDTO;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTO;
import ii.cipriantarlev.marketmanagementapi.productscode.ProductCodeDTO;
import ii.cipriantarlev.marketmanagementapi.region.RegionDTO;
import ii.cipriantarlev.marketmanagementapi.role.RoleDTO;
import ii.cipriantarlev.marketmanagementapi.subcategory.SubcategoryDTO;
import ii.cipriantarlev.marketmanagementapi.subcategory.SubcategoryDTONoCategory;
import ii.cipriantarlev.marketmanagementapi.user.UserDTO;
import ii.cipriantarlev.marketmanagementapi.vat.VatDTO;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTO;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataDTOBuilder {

	public BarcodeDTO getNullValueBarcodeDTO() {
		return BarcodeDTO.builder().id(1L).build();
	}

	public BarcodeDTO getBlankValueBarcodeDTO() {
		return BarcodeDTO.builder().id(1L).value(" ").build();
	}

	public BarcodeDTO getEmptyValueBarcodeDTO() {
		return BarcodeDTO.builder().id(1L).value("").build();
	}

	public BarcodeDTO getInvalidCharactersValueBarcodeDTO() {
		return BarcodeDTO.builder().id(1L).value("A3").build();
	}

	public BarcodeDTO getToManyCharactersValueBarcodeDTO() {
		return BarcodeDTO.builder().id(1L).value("31248712301254").build();
	}

	public CategoryDTO getNullValueCategoryDTO() {
		return CategoryDTO.builder().id(1).build();
	}

	public CategoryDTO getBlankValueCategoryDTO() {
		return CategoryDTO.builder().id(1).name(" ").build();
	}

	public CategoryDTO getEmptyValueCategoryDTO() {
		return CategoryDTO.builder().id(1).name("").build();
	}

	public CategoryDTO getInvalidCharactersValueCategoryDTO() {
		return CategoryDTO.builder().id(1).name("A3").build();
	}

	public CategoryDTO getToManyCharactersValueCategoryDTO() {
		return CategoryDTO.builder().id(1).name("taAsttes testtest".repeat(10)).build();
	}

	public DocumentTypeDTO getNullValueDocumentTypeDTO() {
		return DocumentTypeDTO.builder().id(1).build();
	}

	public DocumentTypeDTO getBlankValueDocumentTypeDTO() {
		return DocumentTypeDTO.builder().id(1).name(" ").build();
	}

	public DocumentTypeDTO getEmptyValueDocumentTypeDTO() {
		return DocumentTypeDTO.builder().id(1).name("").build();
	}

	public DocumentTypeDTO getInvalidCharactersValueDocumentTypeDTO() {
		return DocumentTypeDTO.builder().id(1).name("A3").build();
	}

	public DocumentTypeDTO getToManyCharactersValueDocumentTypeDTO() {
		return DocumentTypeDTO.builder().id(1).name("taAsttes testtest".repeat(20)).build();
	}
	
	public InvoiceDTO getNullDocumentTypeInvoiceDTO() {
		return InvoiceDTO.builder()
				.id(1L)
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();		
	}
	
	public InvoiceDTO getNullMyOrganizationDTO() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();		
	}
	
	public InvoiceDTO getNullVendorDTO() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();		
	}

	public InvoiceDTO getNullDateCreated() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getNullInVoiceNumber() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getBlankInvoiceNumber() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber(" ")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getEmptyInvoiceNumber() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getInvalidInvoiceNumber() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("00 #5A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getTooLongInvoiceNumber() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005AD3".repeat(10))
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getNullInvoiceDate() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.build();
	}
	
	public InvoiceDTO getEmptyNote() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.note("sfjsdjk 873@24")
				.build();
	}
	
	public InvoiceDTO getTooLongNote() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.note("sfjsdjk 873234".repeat(25))
				.build();
	}
	
	public InvoiceDTO getNegativeTotalDiscountPrice() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalDiscountPrice(BigDecimal.valueOf(-1))
				.build();
	}
	
	public InvoiceDTO getWorngRangeTotalDiscountPrice() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalDiscountPrice(BigDecimal.valueOf(1000008.233))
				.build();
	}
	
	public InvoiceDTO getNegativeTotalRetailPrice() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalRetailPrice(BigDecimal.valueOf(-1))
				.build();
	}
	
	public InvoiceDTO getWorngRangeTotalRetailPrice() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalRetailPrice(BigDecimal.valueOf(1000008.233))
				.build();
	}
	
	public InvoiceDTO getNegativeTotalTradeMargin() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalTradeMargin(BigDecimal.valueOf(-1))
				.build();
	}
	
	public InvoiceDTO getWorngRangeTotalTradeMargin() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalTradeMargin(BigDecimal.valueOf(1000008.233))
				.build();
	}
	
	public InvoiceDTO getNegativeTradeMargin() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.tradeMargin(BigDecimal.valueOf(-1))
				.build();
	}
	
	public InvoiceDTO getWorngRangeTradeMargin() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.tradeMargin(BigDecimal.valueOf(1000008.233))
				.build();
	}
	
	public InvoiceDTO getNegativeVatSum() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.vatSum(BigDecimal.valueOf(-1))
				.build();
	}
	
	public InvoiceDTO getWorngRangeVatSum() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.vatSum(BigDecimal.valueOf(1000008.233))
				.build();
	}
	
	public InvoiceProductDTO getNullInvoiceDTO() {
		return InvoiceProductDTO.builder()
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNullProductDTO() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNullQunatity() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNegativeQunatity() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(-0.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getWorngRangeQunatity() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(1000000.21020))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNullVatSum() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNegativeVatSumInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(-1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getWorngRangeVatSumInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1000000.21020))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNullTotalDiscountPricem() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNegativeTotalDiscountPricemInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(-1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getWorngRangeTotalDiscountPricemInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1000000.21020))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNullTotalRetailPricem() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNegativeTotalRetailPricemInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(-1.0))
				.build();
	}
	
	public InvoiceProductDTO getWorngRangeTotalRetailPricemInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(geInvoiceDTO())
				.product(getProductDTO())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1000000.21020))
				.build();
	}

	public MeasuringUnitDTO getNullValueMeasuringUnitDTO() {
		return MeasuringUnitDTO.builder().id(1).build();
	}

	public MeasuringUnitDTO getBlankValueMeasuringUnitDTO() {
		return MeasuringUnitDTO.builder().id(1).name(" ").build();
	}

	public MeasuringUnitDTO getEmptyValueMeasuringUnitDTO() {
		return MeasuringUnitDTO.builder().id(1).name("").build();
	}

	public MeasuringUnitDTO getInvalidCharactersValueMeasuringUnitDTO() {
		return MeasuringUnitDTO.builder().id(1).name("A3 s@").build();
	}

	public MeasuringUnitDTO getToManyCharactersValueMeasuringUnitDTO() {
		return MeasuringUnitDTO.builder().id(1).name("taAsttestesttest".repeat(6)).build();
	}

	public MyOrganizationDTO getNullNameMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.bank("Ad2")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getBlankNameMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name(" ")
				.bank("Ad2")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getEmptyNameMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("")
				.bank("Ad2")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getInvalidCharactersNameMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Asjfd ndjd @")
				.bank("Ad2")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getToManyCharactersNameMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Asjfd ndjdd3d2 d3ds".repeat(10))
				.bank("Ad2")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}
	
	public MyOrganizationDTO getNullBankMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getBlankBankMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank(" ")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getEmptyBankMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getInvalidCharactersBankMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Asjfd ndjd")
				.bank("Ad2  @")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getToManyCharactersBankMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("dfA")
				.bank("Asjfd ndjdd3d2 d3ds".repeat(15))
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}
	
	public MyOrganizationDTO getNullFiscalCodeMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getBlankFiscalCodeMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.fiscalCode(" ")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getEmptyFiscalCodeMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.fiscalCode("")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getInvalidCharactersFiscalCodeMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Asjfd ndjd")
				.bank("Ad2 f")
				.fiscalCode("01254A")
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getToManyCharactersFiscalCodeMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("dfA")
				.bank("Asjfd ndjdd3d2 d3ds")
				.fiscalCode("012545643".repeat(4))
				.bankAccount("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}
	
	public MyOrganizationDTO getNullBankAccountMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.fiscalCode("6554")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getBlankBankAccountMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.fiscalCode("6554")
				.bankAccount(" ")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getEmptyBankAccountMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.fiscalCode("6554")
				.bankAccount("")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getInvalidCharactersBankAccountMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Asjfd ndjd")
				.bank("Ad2 f")
				.fiscalCode("01254")
				.bankAccount("6554A")
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getToManyCharactersBankAccountMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("dfA")
				.bank("Asjfd ndjdd3d2 d3ds")
				.fiscalCode("012545643")
				.bankAccount("012545643".repeat(12))
				.vatCode("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}
	
	public MyOrganizationDTO getNullVatCodeMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.fiscalCode("6554")
				.bankAccount("21254")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getBlankVatCodeMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.fiscalCode("6554")
				.bankAccount("21254")
				.vatCode(" ")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getEmptyVatCodeMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.fiscalCode("6554")
				.bankAccount("21254")
				.vatCode("")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getInvalidCharactersVatCodeMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Asjfd ndjd")
				.bank("Ad2 f")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("6554A")
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getToManyCharactersVatCodeMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("dfA")
				.bank("Asjfd ndjdd3d2 d3ds")
				.fiscalCode("012545643")
				.bankAccount("012545643")
				.vatCode("012545643".repeat(12))
				.city("BS")
				.phoneNumber("064-54")
				.build();
	}
	
	public MyOrganizationDTO getNullCityMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.fiscalCode("6554")
				.bankAccount("21254")
				.vatCode("554")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getBlankCityMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.fiscalCode("6554")
				.bankAccount("21254")
				.vatCode("5124")
				.city(" ")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getEmptyCityMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Ad2")
				.bank("01254")
				.fiscalCode("6554")
				.bankAccount("21254")
				.vatCode("545121")
				.city("")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getInvalidCharactersCityMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Asjfd ndjd")
				.bank("Ad2 f")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("6554")
				.city("BSA#")
				.phoneNumber("064-54")
				.build();
	}

	public MyOrganizationDTO getToManyCharactersCityMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("dfA")
				.bank("Asjfd ndjdd3d2 d3ds")
				.fiscalCode("012545643")
				.bankAccount("012545643")
				.vatCode("012545643")
				.city("BSffd ds4f dsdsds".repeat(12))
				.phoneNumber("064-54")
				.build();
	}
	
	public MyOrganizationDTO getInvalidCharactersPhoneNumberMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Asjfd ndjd")
				.bank("Ad2 f")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("6554")
				.city("BSA")
				.phoneNumber("064-54 ")
				.build();
	}

	public MyOrganizationDTO getToManyCharactersPhoneNumberMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("dfA")
				.bank("Asjfd ndjdd3d2 d3ds")
				.fiscalCode("012545643")
				.bankAccount("012545643")
				.vatCode("012545643")
				.city("BSffd ds4f dsdsds")
				.phoneNumber("064-54-545454-5454".repeat(12))
				.build();
	}
	
	public MyOrganizationDTO getInvalidCharactersEmailMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Asjfd ndjd")
				.bank("Ad2 f")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("6554")
				.city("BSA")
				.email("ffff@ffff")
				.build();
	}

	public MyOrganizationDTO getToManyCharactersEmailMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("dfA")
				.bank("Asjfd ndjdd3d2 d3ds")
				.fiscalCode("012545643")
				.bankAccount("012545643")
				.vatCode("012545643")
				.city("BSffd ds4f dsdsds")
				.email("064fdf54545df4545454".repeat(12) + "@email.com")
				.build();
	}
	
	public MyOrganizationDTO getInvalidCharactersNoteMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("Asjfd ndjd")
				.bank("Ad2 f")
				.fiscalCode("01254")
				.bankAccount("6554")
				.vatCode("6554")
				.city("BSA")
				.note("ffff@ffff")
				.build();
	}

	public MyOrganizationDTO getToManyCharactersNoteMyOrganizationDTO() {
		return MyOrganizationDTO.builder()
				.id(1)
				.name("dfA")
				.bank("Asjfd ndjdd3d2 d3ds")
				.fiscalCode("012545643")
				.bankAccount("012545643")
				.vatCode("012545643")
				.city("BSffd ds4f dsdsds")
				.note("064fdf54545df4545454 fd sds 064fdf54545df4545454 fd sds".repeat(20))
				.build();
	}
	
	public PluDTO getNullValuePluDTO() {
		return PluDTO.builder()
				.id(1)
				.build();
	}
	
	public PluDTO getNegativeValuePluDTO() {
		return PluDTO.builder()
				.id(1)
				.value(-1)
				.build();
	}
	
	public PluDTO getZeroValuePluDTO() {
		return PluDTO.builder()
				.id(1)
				.value(0)
				.build();
	}
	
	public PluDTO getWorngRangeValuePluDTO() {
		return PluDTO.builder()
				.id(1)
				.value(10)
				.build();
	}
	
	public ProductDTO getNullNameRomProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRus("Fdrффв")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getBlankNameRomProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom(" ")
				.nameRus("Fdrффв")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getEmptyNameRomProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("")
				.nameRus("Fdrффв")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getInvalidCharactersNameRomProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Ds$%")
				.nameRus("Fdrффв")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getToManyCharactersNameRomProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Ds dd 4353 dsdfsda sds 3rw sfds ".repeat(30))
				.nameRus("Fdrффв")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getNullNameRusProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Df")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getBlankNameRusProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Fdrds 53")
				.nameRus(" ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getEmptyNameRusProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Fdrds 53")
				.nameRus("")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getInvalidCharactersNameRusProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Fdrds 53")
				.nameRus("Fdrффв$5")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getToManyCharactersNameRusProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ".repeat(30))
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getNullCategoryProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getNullSubcategoryProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getNegativeDiscountPriceProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.discountPrice(BigDecimal.valueOf(-1))
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getWrongRangeDiscountPriceProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.discountPrice(BigDecimal.valueOf(000.0001))
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getNegativeRetailPriceProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.retailPrice(BigDecimal.valueOf(-1))
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getWrongRangeRetailPriceProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.retailPrice(BigDecimal.valueOf(000.0001))
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getNegativeTradeMarginProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.tradeMargin(BigDecimal.valueOf(-1))
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getWrongRangeTradeMarginProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.tradeMargin(BigDecimal.valueOf(000.0001))
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getNullMeasuringUnitProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getNullVatProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getNullBarcodesProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getNullProductCodeProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.build();
	}
	
	public ProductDTO getNegativeStockProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.stock(BigDecimal.valueOf(-1))
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
	
	public ProductDTO getWrongRangeStockProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.stock(BigDecimal.valueOf(000.0001))
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}

	public ProductCodeDTO getNullValueProductCodeDTO() {
		return ProductCodeDTO.builder().id(1L).build();
	}

	public ProductCodeDTO getBlankValueProductCodeDTO() {
		return ProductCodeDTO.builder().id(1L).value(" ").build();
	}

	public ProductCodeDTO getEmptyValueProductCodeDTO() {
		return ProductCodeDTO.builder().id(1L).value("").build();
	}

	public ProductCodeDTO getInvalidCharactersValueProductCodeDTO() {
		return ProductCodeDTO.builder().id(1L).value("A3 s@").build();
	}

	public ProductCodeDTO getToManyCharactersValueProductCodeDTO() {
		return ProductCodeDTO.builder().id(1L).value("taAsttestesttest".repeat(6)).build();
	}

	public RegionDTO getNullNameRegionDTO() {
		return RegionDTO.builder().id(1).build();
	}

	public RegionDTO getBlankNameRegionDTO() {
		return RegionDTO.builder().id(1).name(" ").build();
	}

	public RegionDTO getEmptyNameRegionDTO() {
		return RegionDTO.builder().id(1).name("").build();
	}

	public RegionDTO getInvalidCharactersNameRegionDTO() {
		return RegionDTO.builder().id(1).name("A3 s@").build();
	}

	public RegionDTO getToManyCharactersNameRegionDTO() {
		return RegionDTO.builder().id(1).name("taAsttestesttest".repeat(10)).build();
	}

	public RoleDTO getNullRoleRoleDTO() {
		return RoleDTO.builder().id(1).build();
	}

	public RoleDTO getBlankRoleRoleDTO() {
		return RoleDTO.builder().id(1).role(" ").build();
	}

	public RoleDTO getEmptyRoleRoleDTO() {
		return RoleDTO.builder().id(1).role("").build();
	}

	public RoleDTO getInvalidCharactersRoleRoleDTO() {
		return RoleDTO.builder().id(1).role("A3 s@").build();
	}

	public RoleDTO getToManyCharactersRoleRoleDTO() {
		return RoleDTO.builder().id(1).role("taAsttestesttest".repeat(10)).build();
	}

	public SubcategoryDTO getNullNameSubcategoryDTO() {
		return SubcategoryDTO.builder()
				.id(1)
				.category(CategoryDTO.builder().name("dsds").build())
				.build();
	}

	public SubcategoryDTO getBlankNameSubcategoryDTO() {
		return SubcategoryDTO.builder()
				.id(1)
				.name(" ")
				.category(CategoryDTO.builder().name("dsds").build())
				.build();
	}

	public SubcategoryDTO getEmptyNameSubcategoryDTO() {
		return SubcategoryDTO.builder()
				.id(1)
				.name("")
				.category(CategoryDTO.builder().name("dsds").build())
				.build();
	}

	public SubcategoryDTO getInvalidCharactersNameSubcategoryDTO() {
		return SubcategoryDTO.builder()
				.id(1)
				.name("A3 s@")
				.category(CategoryDTO.builder().name("dsds").build())
				.build();
	}

	public SubcategoryDTO getToManyCharactersNameSubcategoryDTO() {
		return SubcategoryDTO.builder()
				.id(1)
				.name("taAsttestesttest".repeat(10))
				.category(CategoryDTO.builder().name("dsds").build())
				.build();
	}
	
	public SubcategoryDTO getNullCategorySubcategoryDTO() {
		return SubcategoryDTO.builder()
				.id(1)
				.name("taAsttestesttest")
				.build();
	}
	
	public UserDTO getNullRolesUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprian")
				.password("ciprian")
				.email("email@email.com")
				.build();
	}
	
	public UserDTO getNullUsernameUserDTO() {
		return UserDTO.builder()
				.id(1)
				.password("ciprian")
				.roles(new ArrayList<RoleDTO>())
				.email("email@email.com")
				.build();
	}

	public UserDTO getBlankUsernameUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username(" ")
				.password("ciprian")
				.roles(new ArrayList<RoleDTO>())
				.email("email@email.com")
				.build();
	}

	public UserDTO getEmptyUsernameUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("")
				.password("ciprian")
				.roles(new ArrayList<RoleDTO>())
				.email("email@email.com")
				.build();
	}

	public UserDTO getInvalidCharactersUsernameUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprian#")
				.password("ciprian")
				.roles(new ArrayList<RoleDTO>())
				.email("email@email.com")
				.build();
	}

	public UserDTO getToManyCharactersUsernameUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprianciprianciprianciprian".repeat(10))
				.password("ciprian")
				.roles(new ArrayList<RoleDTO>())
				.email("email@email.com")
				.build();
	}
	
	public UserDTO getNullPasswordUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprian")
				.roles(new ArrayList<RoleDTO>())
				.email("email@email.com")
				.build();
	}

	public UserDTO getBlankPasswordUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprian")
				.password(" ")
				.roles(new ArrayList<RoleDTO>())
				.email("email@email.com")
				.build();
	}

	public UserDTO getEmptyPasswordUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprian")
				.password("")
				.roles(new ArrayList<RoleDTO>())
				.email("email@email.com")
				.build();
	}

	public UserDTO getToManyCharactersPasswordUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprian")
				.password("ciprianciprianciprianciprian".repeat(10))
				.roles(new ArrayList<RoleDTO>())
				.email("email@email.com")
				.build();
	}
	
	public UserDTO getNullEmailUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprian")
				.password("ciprian")
				.roles(new ArrayList<RoleDTO>())
				.build();
	}

	public UserDTO getBlankEmailUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprian")
				.password("ciprian")
				.roles(new ArrayList<RoleDTO>())
				.email(" ")
				.build();
	}

	public UserDTO getEmptyEmailUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprian")
				.password("ciprian")
				.roles(new ArrayList<RoleDTO>())
				.email("")
				.build();
	}

	public UserDTO getInvalidCharactersEmailUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprian")
				.password("ciprian")
				.roles(new ArrayList<RoleDTO>())
				.email("email@email")
				.build();
	}

	public UserDTO getToManyCharactersEmailUserDTO() {
		return UserDTO.builder()
				.id(1)
				.username("ciprianciprianciprianciprian")
				.password("ciprian")
				.roles(new ArrayList<RoleDTO>())
				.email("emddddddail@emaiddddl.com".repeat(10))
				.build();
	}
	
	public VatDTO getNullValueVatDTO() {
		return VatDTO.builder()
				.id(1)
				.name("20%")
				.build();
	}

	public VatDTO getNegativeValueVatDTO() {
		return VatDTO.builder()
				.id(1)
				.value(-1)
				.name("20%")
				.build();
	}	
	
	public VatDTO getWorngRangeValueVatDTO() {
		return VatDTO.builder()
				.id(1)
				.value(100)
				.name("20%")
				.build();
	}	
	
	public VatDTO getNullNameVatDTO() {
		return VatDTO.builder()
				.id(1)
				.value(6)
				.build();
	}

	public VatDTO getBlankNameVatDTO() {
		return VatDTO.builder()
				.id(1)
				.value(6)
				.name(" ")
				.build();
	}

	public VatDTO getEmptyNameVatDTO() {
		return VatDTO.builder()
				.id(1)
				.value(6)
				.name("")
				.build();
	}

	public VatDTO getInvalidCharactersNameVatDTO() {
		return VatDTO.builder()
				.id(1)
				.value(6)
				.name("20%$#")
				.build();
	}

	public VatDTO getToManyCharactersNameVatDTO() {
		return VatDTO.builder()
				.id(1)
				.value(6)
				.name("20%20%20%20%20%20%20%".repeat(10))
				.build();
	}
	
	public VendorDTO getNullNameVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.bank("Bank")
				.fiscalCode("1205")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getBlankNameVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name(" ")
				.bank("Bank")
				.fiscalCode("1205")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getEmptyNameVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("")
				.bank("Bank")
				.fiscalCode("1205")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getInvalidCharactersNameVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd#4")
				.bank("Bank")
				.fiscalCode("1205")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getToManyCharactersNameVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s".repeat(10))
				.bank("Bank")
				.fiscalCode("1205")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullBankVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.fiscalCode("1205")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getBlankBankVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank(" ")
				.fiscalCode("1205")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getEmptyBankVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("")
				.fiscalCode("1205")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getInvalidCharactersBankVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd#4")
				.fiscalCode("1205")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getToManyCharactersBankVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s".repeat(10))
				.fiscalCode("1205")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullFiscalCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("F1205")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getBlankFiscalCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode(" ")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getEmptyFiscalCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getInvalidCharactersFiscalCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd4")
				.fiscalCode("dsfd#4")
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getToManyCharactersFiscalCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s")
				.fiscalCode("12054353453453453".repeat(10))
				.bankAccount("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullBankAccountVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("F1205")
				.fiscalCode("0051")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getBlankBankAccountVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount(" ")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getEmptyBankAccountVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getInvalidCharactersBankAccountVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd4")
				.fiscalCode("54484")
				.bankAccount("dsfd#4")
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getToManyCharactersBankAccountVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s")
				.fiscalCode("12054353453453453")
				.bankAccount("12054353453453453".repeat(10))
				.currency("MDL")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullCurrencyVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("F1205")
				.fiscalCode("0051")
				.bankAccount("324")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getBlankCurrencyVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency(" ")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getEmptyCurrencyVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getInvalidCharactersCurrencyVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd4")
				.fiscalCode("54484")
				.bankAccount("2222")
				.currency("MD#s43L")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getToManyCharactersCurrencyVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s")
				.fiscalCode("12054353453453453")
				.bankAccount("12054353453453453")
				.currency("MDLDSFSF".repeat(2))
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullVatCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("F1205")
				.fiscalCode("0051")
				.bankAccount("324")
				.currency("DKf")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getBlankVatCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DM")
				.vatCode(" ")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getEmptyVatCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DF")
				.vatCode("")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getInvalidCharactersVatCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd4")
				.fiscalCode("54484")
				.bankAccount("2222")
				.currency("MDD")
				.vatCode("0545F$")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getToManyCharactersVatCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s")
				.fiscalCode("12054353453453453")
				.bankAccount("12054353453453453")
				.currency("MDLDSFSF")
				.vatCode("05434225".repeat(10))
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullCityVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("F1205")
				.fiscalCode("0051")
				.bankAccount("324")
				.currency("DKf")
				.vatCode("221")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getBlankCityVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DM")
				.vatCode("12")
				.city(" ")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getEmptyCityVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DF")
				.vatCode("3244")
				.city("")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getInvalidCharactersCityVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd4")
				.fiscalCode("54484")
				.bankAccount("2222")
				.currency("MDD")
				.vatCode("0545")
				.city("BS#$")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getToManyCharactersCityVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s")
				.fiscalCode("12054353453453453")
				.bankAccount("12054353453453453")
				.currency("MDLDSFSF")
				.vatCode("05434225")
				.city("BS dsfdsf 4fefe fefe".repeat(10))
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullRegionVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("F1205")
				.fiscalCode("0051")
				.bankAccount("324")
				.currency("DKf")
				.vatCode("221")
				.city("dsf")
				.phoneNumber("00-55-")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullPhoneNumberVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("F1205")
				.fiscalCode("0051")
				.bankAccount("324")
				.currency("DKf")
				.vatCode("221")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.city("0Ds")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getBlankPhoneNumberVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DM")
				.vatCode("12")
				.city("DM")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber(" ")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getEmptyPhoneNumberVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DF")
				.vatCode("3244")
				.city("DF")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getInvalidCharactersPhoneNumberVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd4")
				.fiscalCode("54484")
				.bankAccount("2222")
				.currency("MDD")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-$")
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getToManyCharactersPhoneNumberVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s")
				.fiscalCode("12054353453453453")
				.bankAccount("12054353453453453")
				.currency("MDLDSFSF")
				.vatCode("05434225")
				.city("BS dsfdsf 4fefe fefe")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-3343-43-434-".repeat(5))
				.postalCode("15d")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullPostalCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("F1205")
				.fiscalCode("0051")
				.bankAccount("324")
				.currency("DKf")
				.vatCode("221")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.city("0Ds")
				.phoneNumber("24-5")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getBlankPostalCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DM")
				.vatCode("12")
				.city("DM")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("15-454")
				.postalCode(" ")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getEmptyPostalCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DF")
				.vatCode("3244")
				.city("DF")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("31-545")
				.postalCode("")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getInvalidCharactersPostalCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd4")
				.fiscalCode("54484")
				.bankAccount("2222")
				.currency("MDD")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-3")
				.postalCode("15d$3#")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getToManyCharactersPostalCodeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s")
				.fiscalCode("12054353453453453")
				.bankAccount("12054353453453453")
				.currency("MDLDSFSF")
				.vatCode("05434225")
				.city("BS dsfdsf 4fefe fefe")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-3343-43-434-")
				.postalCode("15dds32fdfd4443")
				.businessAddress("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullBusinessAddressVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("F1205")
				.fiscalCode("0051")
				.bankAccount("324")
				.currency("DKf")
				.vatCode("221")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.city("0Ds")
				.phoneNumber("24-5")
				.postalCode("Strada")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getBlankBusinessAddressVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DM")
				.vatCode("12")
				.city("DM")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("15-454")
				.postalCode("Strada")
				.businessAddress(" ")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getEmptyBusinessAddressVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DF")
				.vatCode("3244")
				.city("DF")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("31-545")
				.postalCode("Strada")
				.businessAddress("")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getInvalidCharactersBusinessAddressVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd4")
				.fiscalCode("54484")
				.bankAccount("2222")
				.currency("MDD")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-3")
				.postalCode("15e3")
				.businessAddress("Strada$#")
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getToManyCharactersBusinessAddressVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s")
				.fiscalCode("12054353453453453")
				.bankAccount("12054353453453453")
				.currency("MDLDSFSF")
				.vatCode("05434225")
				.city("BS dsfdsf 4fefe fefe")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-3343-43-434-")
				.postalCode("15dds")
				.businessAddress("Strada fdfd s4re dsf sd ".repeat(20))
				.vendorType("FD df")
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullVendorTypeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("F1205")
				.fiscalCode("0051")
				.bankAccount("324")
				.currency("DKf")
				.vatCode("221")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.city("0Ds")
				.phoneNumber("24-5")
				.postalCode("Strada")
				.businessAddress("FD df")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getBlankVendorTypeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DM")
				.vatCode("12")
				.city("DM")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("15-454")
				.postalCode("Strada")
				.businessAddress("FD df")
				.vendorType(" ")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getEmptyVendorTypeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DF")
				.vatCode("3244")
				.city("DF")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("31-545")
				.postalCode("Strada")
				.businessAddress("FD df")
				.vendorType("")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getInvalidCharactersVendorTypeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd4")
				.fiscalCode("54484")
				.bankAccount("2222")
				.currency("MDD")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-3")
				.postalCode("15e3")
				.businessAddress("Strada")
				.vendorType("Strada$#")
				.vendorLegalType("dddd d")
				.build();
	}

	public VendorDTO getToManyCharactersVendorTypeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s")
				.fiscalCode("12054353453453453")
				.bankAccount("12054353453453453")
				.currency("MDLDSFSF")
				.vatCode("05434225")
				.city("BS dsfdsf 4fefe fefe")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-3343-43-434-")
				.postalCode("15dds")
				.businessAddress("Strada fdfd s4re dsf sd ")
				.vendorType("Strada fdfd s4re dsf sd ".repeat(10))
				.vendorLegalType("dddd d")
				.build();
	}
	
	public VendorDTO getNullVendorLegalTypeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("F1205")
				.fiscalCode("0051")
				.bankAccount("324")
				.currency("DKf")
				.vatCode("221")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.city("0Ds")
				.phoneNumber("24-5")
				.postalCode("Strada")
				.businessAddress("FD df")
				.vendorType("dddd d")
				.build();
	}

	public VendorDTO getBlankVendorLegalTypeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DM")
				.vatCode("12")
				.city("DM")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("15-454")
				.postalCode("Strada")
				.businessAddress("FD df")
				.vendorType("dddd d")
				.vendorLegalType(" ")
				.build();
	}

	public VendorDTO getEmptyVendorLegalTypeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Bank")
				.bank("1205")
				.fiscalCode("0051")
				.bankAccount("0051")
				.currency("DF")
				.vatCode("3244")
				.city("DF")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("31-545")
				.postalCode("Strada")
				.businessAddress("FD df")
				.vendorType("dddd d")
				.vendorLegalType("")
				.build();
	}

	public VendorDTO getInvalidCharactersVendorLegalTypeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd4")
				.fiscalCode("54484")
				.bankAccount("2222")
				.currency("MDD")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-3")
				.postalCode("15e3")
				.businessAddress("Strada")
				.vendorType("Strada")
				.vendorLegalType("d#4ddd d")
				.build();
	}

	public VendorDTO getToManyCharactersVendorLegalTypeVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s")
				.fiscalCode("12054353453453453")
				.bankAccount("12054353453453453")
				.currency("MDLDSFSF")
				.vatCode("05434225")
				.city("BS dsfdsf 4fefe fefe")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-3343-43-434-")
				.postalCode("15dds")
				.businessAddress("Strada fdfd s4re dsf sd ")
				.vendorType("Strada fdfd s4re dsf sd ")
				.vendorLegalType("Strada fdfd s4re dsf sd ".repeat(15))
				.build();
	}
	
	public VendorDTO getInvalidCharactersNoteVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("dsfd4")
				.bank("dsfd4")
				.fiscalCode("54484")
				.bankAccount("2222")
				.currency("MDD")
				.vatCode("0545")
				.city("BS")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-3")
				.postalCode("15e3")
				.businessAddress("Strada")
				.vendorType("Strada")
				.vendorLegalType("dddd d")
				.note("fdfdf$#%")
				.build();
	}

	public VendorDTO getToManyCharactersNoteVendorDTO() {
		return VendorDTO.builder()
				.id(1)
				.name("Fdfdfds fdsfFd 5 ds sdfsd s")
				.bank("Fdfdfds fdsfFd 5 ds sdfsd s")
				.fiscalCode("12054353453453453")
				.bankAccount("12054353453453453")
				.currency("MDLDSFSF")
				.vatCode("05434225")
				.city("BS dsfdsf 4fefe fefe")
				.region(RegionDTO.builder().id(1).name("A3 s").build())
				.phoneNumber("00-55-3343-43-434-")
				.postalCode("15dds")
				.businessAddress("Strada fdfd s4re dsf sd ")
				.vendorType("Strada fdfd s4re dsf sd ")
				.vendorLegalType("Strada fdfd s4re dsf sd ")
				.note("sdfdsfdsfds sdfsdf4sfdsf sdfsd".repeat(25))
				.build();
	}

	private List<BarcodeDTO> getBarcodeList() {
		List<BarcodeDTO> barcodes = new ArrayList<BarcodeDTO>();
		BarcodeDTO barcode = BarcodeDTO.builder().value("222").build();
		barcodes.add(barcode);
		return barcodes;
	}
	
	private InvoiceDTO geInvoiceDTO() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().name("test").build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().name("test").build())
				.vendor(VendorDTOOnlyName.builder().name("test").build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.vatSum(BigDecimal.valueOf(18.33))
				.build();
	}
	
	private ProductDTO getProductDTO() {
		return ProductDTO.builder()
				.id(1L)
				.nameRom("Dsds")
				.nameRus("Ds dd 4353 dsdfsda sds 3rw sfds ")
				.category(CategoryDTO.builder().name("dsds").build())
				.subcategory(SubcategoryDTONoCategory.builder().name("dds").build())
				.tradeMargin(BigDecimal.valueOf(00.01))
				.measuringUnit(MeasuringUnitDTO.builder().name("kg").build())
				.vat(VatDTO.builder().value(2).name("2%").build())
				.barcodes(getBarcodeList())
				.plu(PluDTO.builder().value(1).build())
				.productCode(ProductCodeDTO.builder().value("MD000").build())
				.build();
	}
}
