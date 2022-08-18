package com.works.restcontrollers;

import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
public class ErrorRestController {

    @GetMapping("/errorx")
    public ResponseEntity error() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.message, "Fail Message");
        return new ResponseEntity(hm, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
