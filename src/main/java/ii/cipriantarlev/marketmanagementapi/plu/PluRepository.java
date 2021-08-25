/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.plu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PluRepository extends JpaRepository<Plu, Integer> {

	Plu findFirst1ByOrderByValueDesc();
}
