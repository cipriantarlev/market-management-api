/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.history;

public enum HistoryAction {

    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");

    private final String action;

    public String getAction() {
        return this.action;
    }

    private HistoryAction(String action) {
        this.action = action;
    }
}
