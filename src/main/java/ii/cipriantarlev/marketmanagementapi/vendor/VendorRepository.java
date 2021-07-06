package ii.cipriantarlev.marketmanagementapi.vendor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	public List<Vendor> findAllByOrderByIdAsc();
}
