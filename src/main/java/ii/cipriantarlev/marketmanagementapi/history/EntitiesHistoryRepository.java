package ii.cipriantarlev.marketmanagementapi.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntitiesHistoryRepository extends JpaRepository<EntitiesHistory, UUID> {
}
