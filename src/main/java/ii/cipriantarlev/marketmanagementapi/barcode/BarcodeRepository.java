package ii.cipriantarlev.marketmanagementapi.barcode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<Barcode, Long> {

	Barcode findFirst1ByValueStartingWithOrderByValueDesc(String value);
}
