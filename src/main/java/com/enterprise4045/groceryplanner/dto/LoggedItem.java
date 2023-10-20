package com.enterprise4045.groceryplanner.dto;

import lombok.Data;

public @Data class LoggedItem {
    private int itemId;
    private String loggedItemId;
    private String description;

    public String getLoggedItemId() {
        return null;
    }
}
