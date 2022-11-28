/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.history;

import ii.cipriantarlev.marketmanagementapi.core.AuthenticationInformation;
import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Class to implement {@link EntitiesHistoryService} interface.
 */
@Service
public class EntitiesHistoryServiceImpl implements EntitiesHistoryService {

    /**
     * {@link EntitiesHistoryRepository} used to connect with database.
     */
    @Autowired
    private EntitiesHistoryRepository entitiesHistoryRepository;

    /**
     * {@link AuthenticationInformation} used to get details about authenticated user.
     */
    @Autowired
    private AuthenticationInformation authenticationInformation;

    @Override
    public <T extends SuperEntity> void createEntityHistoryRecord(T newEntity, T oldEntity, HistoryAction action) {
        EntitiesHistory entitiesHistory = new EntitiesHistory();
        entitiesHistory.setUsername(authenticationInformation.getAuthentication().getName());
        entitiesHistory.setAction(action.getAction());
        entitiesHistory.setEntityName(newEntity.getClass().getSimpleName());
        entitiesHistory.setNewEntity(newEntity);
        entitiesHistory.setOldEntity(oldEntity);
        entitiesHistory.setCreated(LocalDateTime.now());
        entitiesHistoryRepository.save(entitiesHistory);
    }
}
