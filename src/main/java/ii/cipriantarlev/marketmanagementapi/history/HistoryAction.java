/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.history;

/**
 * Enumeration used to define the action happened to an {@link EntitiesHistory}.
 */
public enum HistoryAction {

    /**
     * Action used when the {@link EntitiesHistory} has been created for the first time.
     */
    CREATE("create"),

    /**
     * Action used when the {@link EntitiesHistory} has been updated.
     */
    UPDATE("update"),

    /**
     * Action used when the {@link EntitiesHistory} has been deleted.
     */
    DELETE("delete");

    private final String action;

    /**
     * Method to the type of action in String.
     *
     * @return the type of action in String.
     */
    public String getAction() {
        return this.action;
    }

    private HistoryAction(String action) {
        this.action = action;
    }
}
