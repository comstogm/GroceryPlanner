package com.enterprise4045.groceryplanner.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

public @Data class Item {

    @SerializedName("itemId")
    private Integer itemId;
    @SerializedName("loggedItemId")
    private String loggedItemId;
    @SerializedName("description")
    private String description;
}