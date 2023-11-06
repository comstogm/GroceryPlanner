package com.enterprise4045.groceryplanner;

import com.enterprise4045.groceryplanner.dao.ILoggedItemDAO;
import com.enterprise4045.groceryplanner.dto.LoggedItem;
import com.enterprise4045.groceryplanner.service.LoggedItemServiceStub;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.enterprise4045.groceryplanner.service.ILoggedItemService;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class GroceryPlannerApplicationTests {


    @Autowired
    private ILoggedItemService loggedItemService;

    @MockBean
    private ILoggedItemDAO loggedItemDAO;
    private LoggedItem loggedItem = new LoggedItem();

    @Test
    void contextLoads() {
    }

    @Test
    void fetchLoggedItemById_returnsBroccoli() throws Exception {
        givenLoggedItemDataAreAvailable();
        whenLoggedItem01AddedIsBroccoli();
        whenSearchLoggedItemWithId1();
        thenReturnBroccoliForId1();
    }

    private void whenLoggedItem01AddedIsBroccoli() throws ExecutionException, InterruptedException {
        LoggedItem broccoli = new LoggedItem();
        broccoli.setLoggedItemId("1");
        broccoli.setDescription("Broccoli");
        Mockito.when(loggedItemDAO.fetch(1)).thenReturn(broccoli);
    }

    private void givenLoggedItemDataAreAvailable() throws Exception{
        Mockito.when(loggedItemDAO
                .save(loggedItem))
                .thenReturn(loggedItem);
        loggedItemService = new LoggedItemServiceStub(loggedItemDAO);
    }

    private void whenSearchLoggedItemWithId1() throws ExecutionException, InterruptedException {
        loggedItem = loggedItemService.fetchById(1);
    }

    private void thenReturnBroccoliForId1() {
        String description = loggedItem.getDescription();
        assertEquals("Broccoli", description);
    }

    @Test
    void saveLoggedItem_validateReturnLoggedItem() throws Exception {
        givenLoggedItemDataAreAvailable();
        whenUserAddsANewItemAndSaves();
        thenCreateNewItemRecordAndReturnIt();
    }

    private void whenUserAddsANewItemAndSaves() {
        loggedItem.setLoggedItemId("2");
        loggedItem.setDescription("Carrot");
    }

    private void thenCreateNewItemRecordAndReturnIt() throws Exception {
        LoggedItem createdloggedItem = loggedItemService.save(loggedItem);
        assertEquals(loggedItem, createdloggedItem);
        verify(loggedItemDAO, atLeastOnce()).save(loggedItem);
    }

}
