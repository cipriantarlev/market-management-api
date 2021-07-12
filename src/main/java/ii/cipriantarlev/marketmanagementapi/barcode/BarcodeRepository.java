package ii.cipriantarlev.marketmanagementapi.barcode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<Barcode, Long> {

	List<Barcode> findAllByProductId(Long productId);

	Barcode findFirst1ByValueStartingWithOrderByValueDesc(String value);
}
