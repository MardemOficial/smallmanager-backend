package br.com.SmallManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class ConectionController {

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<String> status(){
        return ResponseEntity.status(HttpStatus.OK).body("Aceito");
    }

}
