/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.history;

import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;

/**
 * {@link EntitiesHistory} service used to manage it.
 */
public interface EntitiesHistoryService {

    /**
     * Method to create {@link EntitiesHistory} record and save it in the database.
     *
     * @param newEntity the updated entity that has been saved in the database.
     * @param oldEntity the old entity. Could be null if the entity was created for the first time.
     * @param action the {@link HistoryAction} used to create {@link EntitiesHistory}.
     * @param <T> the subtype or {@link SuperEntity}.
     */
    <T extends SuperEntity> void createEntityHistoryRecord(T newEntity, T oldEntity, HistoryAction action);
}
