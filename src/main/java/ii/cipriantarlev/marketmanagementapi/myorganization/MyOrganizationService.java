/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

import java.util.List;

public interface MyOrganizationService {

	List<MyOrganizationDTO> findAll();

	List<MyOrganizationDTOOnlyName> findAllMyOrganizationDTOOnlyName();

	MyOrganizationDTO findById(Integer id);

	MyOrganizationDTO save(MyOrganizationDTO userDTO);

	MyOrganizationDTO update(MyOrganizationDTO userDTO);

	void deleteById(Integer id);
}
