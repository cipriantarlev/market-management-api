package ii.cipriantarlev.marketmanagementapi.product.history;

import ii.cipriantarlev.marketmanagementapi.core.AuthenticationInformation;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductHistoryServiceImpl implements ProductHistoryService {

    @Autowired
    private ProductHistoryRepository productHistoryRepository;

    @Autowired
    private AuthenticationInformation authenticationInformation;

    @Override
    public void createProductHistoryRecord(ProductDTO productDTO, HistoryAction action) {
        ProductHistory productHistory = new ProductHistory();
        productHistory.setUsername(authenticationInformation.getAuthentication().getName());
        productHistory.setAction(action.getAction());
        productHistory.setProductDTO(productDTO);
        productHistory.setProductId(productDTO.getId());
        productHistory.setDiscountPrice(productDTO.getDiscountPrice());
        productHistory.setRetailPrice(productDTO.getRetailPrice());
        productHistory.setCreated(LocalDateTime.now());
        productHistoryRepository.save(productHistory);
    }

    @Override
    public Set<ProductHistory> findProductPriceHistory(Long productId) {
        return new HashSet<>(productHistoryRepository.findDistinctByProductIdAndDiscountPriceNotNullAndRetailPriceNotNullOrderByCreatedDesc(productId));
    }
}
