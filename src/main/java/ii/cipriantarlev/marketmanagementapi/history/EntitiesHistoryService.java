/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.history;

import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;

public interface EntitiesHistoryService {

    <T extends SuperEntity> void createEntityHistoryRecord(T newEntity, T oldEntity, HistoryAction action);
}
