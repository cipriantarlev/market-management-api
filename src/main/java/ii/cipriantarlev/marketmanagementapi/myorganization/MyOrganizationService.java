package ii.cipriantarlev.marketmanagementapi.myorganization;

import java.util.List;

public interface MyOrganizationService {

	List<MyOrganizationDTO> findAll();

	MyOrganizationDTO findById(Integer id);

	MyOrganization save(MyOrganizationDTO userDTO);

	void deleteById(Integer id);
}
