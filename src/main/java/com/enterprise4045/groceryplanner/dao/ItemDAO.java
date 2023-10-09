package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.Item;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

@Repository
public class ItemDAO implements IItemDAO {
    @Override
    public List<Item> getItems() throws IOException {
        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
        IItemRetrofitDAO itemRetrofitDAO = retrofitInstance.create(IItemRetrofitDAO.class);
        Call<List<Item>> allItems = itemRetrofitDAO.getItems();
        Response<List<Item>> execute = allItems.execute();
        List<Item> items = execute.body();
        return items;
    }
}
