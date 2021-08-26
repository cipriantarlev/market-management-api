/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeDTO;
import ii.cipriantarlev.marketmanagementapi.category.CategoryDTO;
import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentTypeDTO;
import ii.cipriantarlev.marketmanagementapi.invoice.InvoiceDTO;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataBuilder {

	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

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
}
