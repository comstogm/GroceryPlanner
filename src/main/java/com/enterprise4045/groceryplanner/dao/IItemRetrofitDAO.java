package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface IItemRetrofitDAO {
    @GET("/comstogm/ItemList/ItemList")
    Call<List<Item>> getItems();
}