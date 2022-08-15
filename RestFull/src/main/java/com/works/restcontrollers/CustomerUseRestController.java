package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerUseRestController {

    final CustomerService cService;
    public CustomerUseRestController(CustomerService cService) {
        this.cService = cService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Customer customer) {
         return cService.save(customer);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return cService.list();
    }

}
