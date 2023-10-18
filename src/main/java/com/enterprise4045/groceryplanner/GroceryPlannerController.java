package com.enterprise4045.groceryplanner;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import com.enterprise4045.groceryplanner.service.ILoggedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@RestController
public class GroceryPlannerController {

    private final ILoggedItemService loggedItemService;

    @Autowired
    public GroceryPlannerController(ILoggedItemService loggedItemService) {
        this.loggedItemService = loggedItemService;
    }

    /*
        Handle the root (/) endpoint and return a start page.
         */
    @RequestMapping("/")
    public String index(Model model) {
        LoggedItem loggedItem = new LoggedItem();
        loggedItem.setDescription("Pawpaw fruit season");
        loggedItem.setLoggedItemId("1003");
        loggedItem.setItemId(84);
        return "start";
    }

    @RequestMapping("/saveLoggedItem")
    public String saveLoggedItem(LoggedItem loggedItem) throws Exception {
        try {
            loggedItemService.save(loggedItem);
        } catch (Exception e) {
            e.printStackTrace();
            return "start";

        }
        return "start";
    }



    /*
    Fetches all logged items
     */
    @GetMapping("/loggedItem/")
    @ResponseBody
    public List<LoggedItem> fetchAllLoggedItems() {
        return loggedItemService.fetchAll();
    }

    /*
    Fetches logged item by id
     */
    @GetMapping("/loggedItem/{id}/")
    public ResponseEntity fetchLoggedItemById(@PathVariable("id") String id) {
        LoggedItem foundloggedItem = loggedItemService.fetchById(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundloggedItem, headers, HttpStatus.OK);
    }

    /*
    Creates a new item
     */
    @PostMapping(value="/loggedItem", consumes="application/json", produces ="application/json")
    public LoggedItem createLoggedItem(@RequestBody LoggedItem loggedItem) throws Exception {
        LoggedItem newloggedItem = null;

        try{
               newloggedItem = loggedItemService.save(loggedItem);
           }
        catch (Exception e){
               //TO-Do add logging
           }

           return newloggedItem;
    }

    /*
    Deletes and item based on id
     */
    @DeleteMapping("/loggedItem/{id}/")
    public ResponseEntity deleteLoggedItem(@PathVariable("id") String id) {
        try {
            loggedItemService.delete(Integer.parseInt(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/plants")
    public ResponseEntity searchPlants(@RequestParam(value="searchTerm", required = false, defaultValue = "None" )String searchTerm){
        String newSearchTerm = searchTerm +"";
        return new ResponseEntity(HttpStatus.OK);

    }
    /*
        Handle the root (/) endpoint and return a start page.
         */
    @RequestMapping("/sustainability")
    public String sustainability() {

        return "sustainability";
    }
}
