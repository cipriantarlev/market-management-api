/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeDTO;
import ii.cipriantarlev.marketmanagementapi.category.CategoryDTO;
import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentTypeDTO;
import ii.cipriantarlev.marketmanagementapi.invoice.InvoiceDTO;
import ii.cipriantarlev.marketmanagementapi.invoiceproduct.InvoiceProductDTO;
import ii.cipriantarlev.marketmanagementapi.measuringunit.MeasuringUnitDTO;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTO;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTO;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataBuilder {

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
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();		
	}
	
	public InvoiceDTO getNullMyOrganizationDTO() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();		
	}
	
	public InvoiceDTO getNullVendorDTO() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();		
	}

	public InvoiceDTO getNullDateCreated() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getNullInVoiceNumber() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getBlankInvoiceNumber() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber(" ")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getEmptyInvoiceNumber() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getInvalidInvoiceNumber() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("00 #5A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getTooLongInvoiceNumber() {
		return InvoiceDTO.builder()
				.id(1L).documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005AD3".repeat(10))
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.build();
	}
	
	public InvoiceDTO getNullInvoiceDate() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.build();
	}
	
	public InvoiceDTO getEmptyNote() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.note("sfjsdjk 873@24")
				.build();
	}
	
	public InvoiceDTO getTooLongNote() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.note("sfjsdjk 873234".repeat(25))
				.build();
	}
	
	public InvoiceDTO getNegativeTotalDiscountPrice() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalDiscountPrice(BigDecimal.valueOf(-1))
				.build();
	}
	
	public InvoiceDTO getWorngRangeTotalDiscountPrice() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalDiscountPrice(BigDecimal.valueOf(1000008.233))
				.build();
	}
	
	public InvoiceDTO getNegativeTotalRetailPrice() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalRetailPrice(BigDecimal.valueOf(-1))
				.build();
	}
	
	public InvoiceDTO getWorngRangeTotalRetailPrice() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalRetailPrice(BigDecimal.valueOf(1000008.233))
				.build();
	}
	
	public InvoiceDTO getNegativeTotalTradeMargin() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalTradeMargin(BigDecimal.valueOf(-1))
				.build();
	}
	
	public InvoiceDTO getWorngRangeTotalTradeMargin() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.totalTradeMargin(BigDecimal.valueOf(1000008.233))
				.build();
	}
	
	public InvoiceDTO getNegativeTradeMargin() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.tradeMargin(BigDecimal.valueOf(-1))
				.build();
	}
	
	public InvoiceDTO getWorngRangeTradeMargin() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.tradeMargin(BigDecimal.valueOf(1000008.233))
				.build();
	}
	
	public InvoiceDTO getNegativeVatSum() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.vatSum(BigDecimal.valueOf(-1))
				.build();
	}
	
	public InvoiceDTO getWorngRangeVatSum() {
		return InvoiceDTO.builder()
				.id(1L)
				.documentType(DocumentTypeDTO.builder().build())
				.myOrganization(MyOrganizationDTOOnlyName.builder().build())
				.vendor(VendorDTOOnlyName.builder().build())
				.dateCreated(LocalDate.parse("2021-01-23"))
				.invoiceNumber("005A")
				.invoiceDate(LocalDate.parse("2021-01-23"))
				.vatSum(BigDecimal.valueOf(1000008.233))
				.build();
	}
	
	public InvoiceProductDTO getNullInvoiceDTO() {
		return InvoiceProductDTO.builder()
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNullProductDTO() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNullQunatity() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNegativeQunatity() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(-0.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getWorngRangeQunatity() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(1000000.21020))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNullVatSum() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNegativeVatSumInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(-1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getWorngRangeVatSumInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1000000.21020))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNullTotalDiscountPricem() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNegativeTotalDiscountPricemInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(-1.0))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getWorngRangeTotalDiscountPricemInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1000000.21020))
				.totalRetailPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNullTotalRetailPricem() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.build();
	}
	
	public InvoiceProductDTO getNegativeTotalRetailPricemInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(-1.0))
				.build();
	}
	
	public InvoiceProductDTO getWorngRangeTotalRetailPricemInvoiceProduct() {
		return InvoiceProductDTO.builder()
				.invoice(InvoiceDTO.builder().build())
				.product(ProductDTO.builder().build())
				.quantity(BigDecimal.valueOf(1.0))
				.vatSum(BigDecimal.valueOf(1.0))
				.totalDiscountPrice(BigDecimal.valueOf(1.0))
				.totalRetailPrice(BigDecimal.valueOf(1000000.21020))
				.build();
	}

	public MeasuringUnitDTO getNullValueMeasuringUnitDTO() {
		return MeasuringUnitDTO.builder().id(1).build();
	}

	public MeasuringUnitDTO getBlankValueMeasuringUnitDTOO() {
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
}
