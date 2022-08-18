package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository pRepo;
    final CacheManager cacheManager;
    public ProductService(ProductRepository pRepo, CacheManager cacheManager) {
        this.pRepo = pRepo;
        this.cacheManager = cacheManager;
    }


    public ResponseEntity save(Product product) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        pRepo.save(product);
        hm.put(REnum.status, true);
        hm.put(REnum.result, product);
        cacheManager.getCache("productList").clear();
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity list() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, pRepo.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity delete( Integer pid ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            //int pid = Integer.parseInt(stPid);
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
                hm.put(REnum.result, pid);
                return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put(REnum.result, pid);
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity saveAll(List<Product> products) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        pRepo.saveAll(products);
        hm.put(REnum.status, true);
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity listPage( int page ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Pageable pageable = PageRequest.of(page, 10);
        Page<Product> products = pRepo.findAll(pageable);
        hm.put(REnum.status, true);
        hm.put(REnum.result, products);
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity search( String q, int page ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        Pageable pageable = PageRequest.of(page, 10);
        hm.put(REnum.result, pRepo.findByTitleContainsIgnoreCaseOrDetailContainsIgnoreCase(q,q, pageable));
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
