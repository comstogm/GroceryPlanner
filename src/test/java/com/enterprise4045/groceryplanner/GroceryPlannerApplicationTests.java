package com.enterprise4045.groceryplanner;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.enterprise4045.groceryplanner.service.ILoggedItemService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GroceryPlannerApplicationTests {


    @Autowired
    private ILoggedItemService loggedItemService;
    private LoggedItem loggedItem;

    @Test
    void contextLoads() {
    }

    @Test
    void fetchLoggedItemById_returnsBroccoli() {
        givenLoggedItemDataAreAvailable();
        whenSearchLoggedItemWithId1();
        thenReturnBroccoliForId1();
    }

    private void givenLoggedItemDataAreAvailable() {
    }

    private void whenSearchLoggedItemWithId1() {
        loggedItem = loggedItemService.fetchById(1);
    }

    private void thenReturnBroccoliForId1() {
        String description = loggedItem.getDescription();
        assertEquals("Broccoli", description);
    }

    @Test
    void saveLoggedItem_validateReturnLoggedItem() {
        givenLoggedItemDataAreAvailable();
        whenUserAddsANewItemAndSaves();
        thenCreateNewItemRecordAndReturnIt();
    }

    private void whenUserAddsANewItemAndSaves() {
        loggedItem.setLoggedItemId("2");
        loggedItem.setDescription("Carrot");
    }

    private void thenCreateNewItemRecordAndReturnIt() {
        LoggedItem createdloggedItem = loggedItemService.save(loggedItem);
    }

}
