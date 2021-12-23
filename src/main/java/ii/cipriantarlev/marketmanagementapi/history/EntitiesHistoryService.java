/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.history;

public interface EntitiesHistoryService {

    <T> void createEntityHistoryRecord(T newEntity, T oldEntity, HistoryAction action);
}
