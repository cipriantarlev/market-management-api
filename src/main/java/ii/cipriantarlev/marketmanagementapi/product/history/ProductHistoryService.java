package ii.cipriantarlev.marketmanagementapi.product.history;

import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTO;

import java.util.List;
import java.util.Set;

public interface ProductHistoryService {

    void createProductHistoryRecord(ProductDTO productDTO, HistoryAction action);

    Set<ProductHistory> findProductPriceHistory(Long productId);
}
