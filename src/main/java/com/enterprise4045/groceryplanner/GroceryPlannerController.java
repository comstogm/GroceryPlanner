package com.enterprise4045.groceryplanner;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import com.enterprise4045.groceryplanner.service.ILoggedItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroceryPlannerController {

    private final ILoggedItemService loggedItemService;
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public GroceryPlannerController(ILoggedItemService loggedItemService) {
        this.loggedItemService = loggedItemService;
    }

    /*
        Handle the root (/) endpoint and return a start page.
         */
    @RequestMapping("/")
    public String index() {
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
            log.error("Error happened in createLoggedItem endpoint", e);
            e.printStackTrace();
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
}
