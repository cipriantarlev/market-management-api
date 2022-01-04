/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.plu;

import java.util.List;

public interface PluService {

	List<PluDTO> findAll();

	PluDTO findById(Long id);

	PluDTO generateNewPlu();

	void deleteById(Long id);
}
