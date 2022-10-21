package ii.cipriantarlev.marketmanagementapi.utils;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;
import ii.cipriantarlev.marketmanagementapi.invoice.Invoice;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganization;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationMapper;
import ii.cipriantarlev.marketmanagementapi.plu.Plu;
import ii.cipriantarlev.marketmanagementapi.pricechangingact.PriceChangingActDTO;
import ii.cipriantarlev.marketmanagementapi.pricechangingact.PriceChangingActService;
import ii.cipriantarlev.marketmanagementapi.pricechangingactproduct.PriceChangingActProductDTO;
import ii.cipriantarlev.marketmanagementapi.pricechangingactproduct.PriceChangingActProductService;
import ii.cipriantarlev.marketmanagementapi.product.Product;
import ii.cipriantarlev.marketmanagementapi.product.ProductMapper;
import ii.cipriantarlev.marketmanagementapi.product.history.ProductHistory;
import ii.cipriantarlev.marketmanagementapi.productscode.ProductCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class MarketManagementFactory {

    @Autowired
    private MyOrganizationMapper myOrganizationMapper;

    @Autowired
    private PriceChangingActService priceChangingActService;

    @Autowired
    private PriceChangingActProductService priceChangingActProductService;

    @Autowired
    private ProductMapper productMapper;

    public Plu getNewPlu(int value) {
        return new Plu(value + 1);
    }

    public DocumentType getNewDocumentType(long documentTypeId) {
        return new DocumentType(documentTypeId);
    }

    public ProductHistory getProductHistory() {
        return new ProductHistory();
    }

    public ProductCode getProductCode(String value) {
        return new ProductCode(value);
    }

    public Invoice getClonedInvoice(Invoice sourceInvoice) {
        return new Invoice(sourceInvoice);
    }

    public PriceChangingActDTO createPriceChangingActWhenProductPriceHasChanged(Invoice invoice, List<Product> products) {
        BigDecimal oldRetailPrice = BigDecimal.ZERO;
        BigDecimal retailPrice = BigDecimal.ZERO;

        for(Product product : products) {
            oldRetailPrice = oldRetailPrice.add(product.getOldRetailPrice());
            retailPrice = retailPrice.add(product.getRetailPrice());
        }

        return priceChangingActService.save(generatePriceChangingActDTO(getNoteBasedOnInvoiceType(invoice), oldRetailPrice, retailPrice, invoice));
    }

    public void createPriceChangingActProductWhenProductPriceHasChanged(
            PriceChangingActDTO priceChangingActDTO, List<Product> products) {
        products.forEach(product -> priceChangingActProductService
                .save(generatePriceChangingActProductDTO(priceChangingActDTO, product)));
    }

    private PriceChangingActDTO generatePriceChangingActDTO(
            String note, BigDecimal oldRetailPrice, BigDecimal retailPrice, Invoice invoice) {
        return PriceChangingActDTO.builder()
                .id(UUID.randomUUID())
                .dateCreated(LocalDate.now())
                .myOrganization(myOrganizationMapper.mapEntityToMyOrganizationDTOOnlyName(invoice.getMyOrganization()))
                .oldPrices(oldRetailPrice)
                .newPrices(retailPrice)
                .pricesDifference(retailPrice.subtract(oldRetailPrice))
                .note(note)
                .isApproved(true)
                .isSent(false)
                .invoiceId(invoice.getId())
                .build();
    }

    private PriceChangingActProductDTO generatePriceChangingActProductDTO( PriceChangingActDTO priceChangingActDTO, Product product) {
        return PriceChangingActProductDTO.builder()
                .id(UUID.randomUUID())
                .priceChangingAct(priceChangingActDTO)
                .product(productMapper.mapEntityToDTOForList(product))
                .oldPrice(product.getOldRetailPrice())
                .priceDifference(product.getRetailPrice().subtract(product.getOldRetailPrice()))
                .build();
    }

    private String getNoteBasedOnInvoiceType(Invoice invoice) {
        if (invoice.getDocumentType().getName().equalsIgnoreCase("Income Invoice"))
            return "Incoming Invoice Nr. " + invoice.getId();
        else if (invoice.getDocumentType().getName().equalsIgnoreCase("Outcome Invoice"))
            return "Outcoming Invoice Nr. " + invoice.getId();

        return "Updated in Product section";
    }
}