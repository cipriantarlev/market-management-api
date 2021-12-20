package ii.cipriantarlev.marketmanagementapi.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntitiesHistoryServiceImpl implements EntitiesHistoryService {

    @Autowired
    private EntitiesHistoryRepository entitiesHistoryRepository;

    @Override
    public <T> void createEntityHistoryRecord(T newEntity, T oldEntity, HistoryAction action, String username) {
        EntitiesHistory entitiesHistory = new EntitiesHistory();
        entitiesHistory.setUsername(username);
        entitiesHistory.setAction(action.getAction());
        entitiesHistory.setEntityName(newEntity.getClass().getSimpleName());
        entitiesHistory.setNewEntity(newEntity.toString());
        entitiesHistory.setOldEntity(oldEntity != null ? oldEntity.toString() : null);
        entitiesHistory.setCreated(LocalDateTime.now());
        entitiesHistoryRepository.save(entitiesHistory);
    }
}
