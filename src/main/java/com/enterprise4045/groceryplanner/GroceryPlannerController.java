package com.enterprise4045.groceryplanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GroceryPlannerController {
    /*
    Handle the root (/) endpoint and return a start page.
     */
    @RequestMapping("/")
    public String index() {
        return "start";
    }
}
