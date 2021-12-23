/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.history;

import ii.cipriantarlev.marketmanagementapi.core.AuthenticationInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntitiesHistoryServiceImpl implements EntitiesHistoryService {

    @Autowired
    private EntitiesHistoryRepository entitiesHistoryRepository;

    @Autowired
    private AuthenticationInformation authenticationInformation;

    @Override
    public <T> void createEntityHistoryRecord(T newEntity, T oldEntity, HistoryAction action) {
        EntitiesHistory entitiesHistory = new EntitiesHistory();
        entitiesHistory.setUsername(authenticationInformation.getAuthentication().getName());
        entitiesHistory.setAction(action.getAction());
        entitiesHistory.setEntityName(newEntity.getClass().getSimpleName());
        entitiesHistory.setNewEntity(newEntity.toString());
        entitiesHistory.setOldEntity(oldEntity != null ? oldEntity.toString() : null);
        entitiesHistory.setCreated(LocalDateTime.now());
        entitiesHistoryRepository.save(entitiesHistory);
    }
}
