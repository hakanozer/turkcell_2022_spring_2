package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository pRepo;
    public ProductService(ProductRepository pRepo) {
        this.pRepo = pRepo;
    }


    public ResponseEntity save(Product product) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        pRepo.save(product);
        hm.put(REnum.status, true);
        hm.put(REnum.result, product);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity list() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, pRepo.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity delete( String stPid ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            int pid = Integer.parseInt(stPid);
            Optional<Product> oProduct = pRepo.findById(pid);
            if ( oProduct.isPresent() ) {
                // product notnull
                Product product = oProduct.get();
                pRepo.deleteById(pid);
                hm.put(REnum.status, true);
                hm.put(REnum.result, product);
                return new ResponseEntity(hm, HttpStatus.OK);
            }else {
                hm.put(REnum.status, false);
                hm.put(REnum.result, stPid);
                return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put(REnum.result, stPid);
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }

}
