/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.category;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Category} entity.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
