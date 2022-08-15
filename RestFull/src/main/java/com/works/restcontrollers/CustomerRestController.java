package com.works.restcontrollers;

import com.works.entities.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class CustomerRestController {

    // Query String
    // @RequestParam
    @GetMapping("/data")
    public Map data(@RequestParam String name, @RequestParam int age) {
       Map<String, Object> hm  = new LinkedHashMap<>();
       hm.put("status", true);
       hm.put("name", name);
       hm.put("age", age);
       return hm;
    }

    // Object data send
    @PostMapping("/create")
    public Map create(@RequestBody Customer customer) {
        Map<String, Object> hm  = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("customer", customer);
        return hm;
    }

    @DeleteMapping("/delete/{cid}")
    public Map delete( @PathVariable int cid ) {
        Map<String, Object> hm  = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("cid", cid);
        return hm;
    }

}
