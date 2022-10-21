/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MyOrganizationRepository extends JpaRepository<MyOrganization, Long> {

    Optional<MyOrganization> findByIsDefaultTrue();

    @Transactional
    @Modifying
    @Query("update MyOrganization u set u.isDefault = 'false'")
    int updateIsDefaultToFalse();
}
