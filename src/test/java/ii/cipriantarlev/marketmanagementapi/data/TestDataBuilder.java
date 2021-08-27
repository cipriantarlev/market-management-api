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
}
