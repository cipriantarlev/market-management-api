package ii.cipriantarlev.marketmanagementapi.utils;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;
import ii.cipriantarlev.marketmanagementapi.plu.Plu;
import ii.cipriantarlev.marketmanagementapi.product.history.ProductHistory;
import ii.cipriantarlev.marketmanagementapi.productscode.ProductCode;
import org.springframework.stereotype.Component;

@Component
public class MarketManagementFactory {

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
}