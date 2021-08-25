/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.plu;

import java.util.List;

public interface PluService {

	List<PluDTO> findAll();

	PluDTO findById(Integer id);

	PluDTO generateNewPlu();

	void deleteById(Integer id);
}
