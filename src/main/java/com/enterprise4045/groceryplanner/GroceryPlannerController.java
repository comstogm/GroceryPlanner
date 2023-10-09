package com.enterprise4045.groceryplanner;

import com.enterprise4045.groceryplanner.dao.ILoggedItemDAO;
import com.enterprise4045.groceryplanner.dto.Item;
import com.enterprise4045.groceryplanner.dto.LoggedItem;
import com.enterprise4045.groceryplanner.service.ILoggedItemService;
import com.enterprise4045.groceryplanner.service.LoggedItemServiceStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class GroceryPlannerController {

    @Autowired
    ILoggedItemService loggedItemService;
    /*
    Handle the root (/) endpoint and return a start page.
    Populates the page w/ default LoggedItem
     */
    @RequestMapping("/")
    public String index(Model model) {
        LoggedItem loggedItem = new LoggedItem();
        loggedItem.setItemId(420);
        loggedItem.setLoggedItemId("620");
        loggedItem.setDescription("Milk");
        model.addAttribute(loggedItem);
        return "start";
    }

    @RequestMapping("/saveLoggedItem")
    public String saveLoggedItem(LoggedItem loggedItem) {
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
    public List<LoggedItem> fetchallLoggedItems() {
        return loggedItemService.fetchAll();
    }

    /*
    Fetches logged item by id
     */
    @GetMapping("/loggedItem/{id}/")
    public ResponseEntity fetchLoggedItemById(@PathVariable("id") String id) {
        LoggedItem foundLoggedItem = loggedItemService.fetchById(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundLoggedItem, headers, HttpStatus.OK);
    }

    /*
    Creates a new item
     */
    @PostMapping(value="/loggedItem", consumes="application/json", produces ="application/json")
    @ResponseBody
    public LoggedItem createLoggedItem(@RequestBody LoggedItem loggedItem) {
        LoggedItem newLoggedItem = null;
        try {
            newLoggedItem = loggedItemService.save(loggedItem);
        } catch (Exception e) {
            //TODO add logging
        }
        return newLoggedItem;
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

    /*
    Get mapping for start.html search field
     */
    @GetMapping("/items")
    public ResponseEntity searchItems(@RequestParam Map<String, String> requestParams) {
        int params = requestParams.size();
        String searchValue = requestParams.get("searchTerm");
        try {
            List<Item> items = loggedItemService.fetchItems();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(items, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/items")
//    public ResponseEntity searchItems(@RequestParam(value="searchTerm", required = false, defaultValue ="None") String searchTerm) {
//        return new ResponseEntity(HttpStatus.OK);
//    }

    /**
     * @return superCoolPage placeholder page
     */
    @RequestMapping("/superCoolPage")
    public String superCoolPage() {
        return "superCoolPage";
    }
}
