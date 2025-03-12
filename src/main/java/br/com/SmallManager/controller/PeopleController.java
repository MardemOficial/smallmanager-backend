package br.com.SmallManager.controller;

import br.com.SmallManager.domain.People;
import br.com.SmallManager.records.PeopleDTO;
import br.com.SmallManager.service.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public ResponseEntity<Page<PeopleDTO>> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(peopleService.listAllPeoples(pageable).map(PeopleDTO::new));
    }

    @GetMapping("/sobre")
    public String hey(){
        return "Hey, what your name?";
    }

    @PostMapping
    public ResponseEntity<People> salvar(@RequestBody @Valid PeopleDTO peopleDTO){
        try{
            var people = new People();
            BeanUtils.copyProperties(peopleDTO, people);
            return ResponseEntity.status(HttpStatus.CREATED).body(peopleService.salvar(people));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new People());
        }

    }


}
