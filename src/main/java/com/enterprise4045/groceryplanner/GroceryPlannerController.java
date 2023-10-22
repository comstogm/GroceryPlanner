package com.enterprise4045.groceryplanner;

import com.enterprise4045.groceryplanner.dto.Item;
import com.enterprise4045.groceryplanner.dto.LoggedItem;
import com.enterprise4045.groceryplanner.service.ILoggedItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
public class GroceryPlannerController {

    private final ILoggedItemService loggedItemService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public GroceryPlannerController(ILoggedItemService loggedItemService) {
        this.loggedItemService = loggedItemService;
    }

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
        model.addAttribute("itemList", loggedItemService.fetchAll());
        return "start";
    }

    @RequestMapping("/saveLoggedItems")
    public String saveLoggedItem(LoggedItem loggedItem, Model model) {
        try {
            loggedItemService.save(loggedItem);
            model.addAttribute("itemList", loggedItemService.fetchAll());
        } catch (Exception e) {
            log.error("Error in saveLoggedItem endpoint", e);
            return "start";
        }
        return "start";
    }


    /*`
    Fetches all logged items
    `*/
    @GetMapping(value="/loggedItems/")
    @ResponseBody
    public List<LoggedItem> fetchAllLoggedItems() {
        return loggedItemService.fetchAll();
    }

    /*
    Fetches logged item by id
     */
    @GetMapping("/loggedItems/{id}/")
    public ResponseEntity<LoggedItem> fetchLoggedItemById(@PathVariable("id") String id) {
        LoggedItem foundLoggedItem = loggedItemService.fetchById(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (foundLoggedItem==null) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundLoggedItem, headers, HttpStatus.OK);
    }

    /*
    Creates a new item
     */
    @PostMapping(value="/loggedItems", consumes="application/json", produces ="application/json")
    public ResponseEntity<Object> createLoggedItem(@RequestBody LoggedItem loggedItem) {
        // Validation
        if (loggedItem.getDescription() == null || loggedItem.getDescription().isEmpty()) {
            return new ResponseEntity<>("Description cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        // Process the item
        try {
            LoggedItem newLoggedItem = loggedItemService.save(loggedItem);
            return new ResponseEntity<>(newLoggedItem, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    Deletes and item based on id
     */
    @DeleteMapping("/loggedItems/{id}/")
    public ResponseEntity<LoggedItem> deleteLoggedItem(@PathVariable("id") String id) {
        try {
            loggedItemService.delete(Integer.parseInt(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    Get mapping for start.html search field
    returns JSON list of items
     */
    @GetMapping(value="/items", consumes="application/json", produces ="application/json")
    public ResponseEntity<Object> searchItems(@RequestParam(value="searchTerm", required = false, defaultValue = "None") String searchTerm) {
        try {
            List<Item> items = loggedItemService.fetchItems();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(items, headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error("Error in searchItems endpoint", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/items")
    public String searchItemsForm(@RequestParam(value="searchTerm", required = false, defaultValue = "None") String searchTerm, Model model) {
        try {
            List<Item> items = loggedItemService.fetchItems();
            model.addAttribute("items", items);
            return "items";
        } catch (IOException e) {
            log.error("Error in searchItemsForm endpoint", e);
            return "error";
        }
    }

    /**
     * @return superCoolPage placeholder page
     */
    @RequestMapping("/superCoolPage")
    public String superCoolPage() {
        return "superCoolPage";
    }
}