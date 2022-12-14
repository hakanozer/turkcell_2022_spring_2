package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/product")
@Validated
public class ProductRestController {

    final ProductService pService;
    public ProductRestController(ProductService pService) {
        this.pService = pService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Product product) {
        return pService.save(product);
    }

    @GetMapping("/list")
    @Cacheable("productList")
    public ResponseEntity list() {
        return pService.list();
    }

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity delete(
            @NotNull(message = "pid NotNull")
            @Min(value = 1, message = "pid min 1")
            @PathVariable Integer pid
    ) {
        return pService.delete(pid);
    }

    @PostMapping("/saveAll")
    public ResponseEntity saveAll(@RequestBody List<Product> products) {
        return pService.saveAll(products);
    }

    @GetMapping("/listPage")
    public ResponseEntity listPage(@RequestParam(defaultValue = "0") int page) {
        return pService.listPage(page);
    }


    @GetMapping("/search")
    public ResponseEntity search(@RequestParam(defaultValue = "") String q, @RequestParam(defaultValue = "0") int page) {
        return pService.search(q, page);
    }

}
