/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository interface for {@link EntitiesHistory} entity.
 */
public interface EntitiesHistoryRepository extends JpaRepository<EntitiesHistory, UUID> {
}
