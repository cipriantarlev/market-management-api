package ii.cipriantarlev.marketmanagementapi.history;

public interface EntitiesHistoryService {

    <T> void createEntityHistoryRecord(T newEntity, T oldEntity, HistoryAction action, String username);
}
