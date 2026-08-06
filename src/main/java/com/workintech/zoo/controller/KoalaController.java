package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    Map<Long, Koala> koalas;

    @PostConstruct
    public void init(){
        koalas=new HashMap<>();
    }

    @GetMapping
    public List<Koala> getAllKoalas(){
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoalasById(@PathVariable long id){
        if(id<0){
            throw new ZooException("Id must be positive", HttpStatus.BAD_REQUEST);
        }
        if(!koalas.containsKey(id)){
            throw new ZooException("Could not find id = " + id, HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    @PostMapping
    public Koala addKoala(@RequestBody Koala koala) {
        if (koala.getId() < 0) {
            throw new ZooException("Id must be positive", HttpStatus.BAD_REQUEST);
        }
        if (koalas.containsKey(koala.getId())) {
            throw new ZooException("Koala with id " + koala.getId() + " already exists", HttpStatus.CONFLICT);
        }
        koalas.put(koala.getId(), koala);
        return koala;
    }


    @PutMapping("/{id}")
    public Koala editKoala(@PathVariable long id, @RequestBody Koala koala){
        if(id<0){
            throw new ZooException("Id must be positive", HttpStatus.BAD_REQUEST);
        }

        if(!koalas.containsKey(id)){
            throw new ZooException("Could not find id = " + id, HttpStatus.NOT_FOUND);
        }
        Koala koala1 = new Koala(koala.getId(),koala.getName(),koala.getWeight(),koala.getSleepHour(),koala.getGender());
        koalas.put(koala.getId(),koala1);
        return koala1;

    }

    @DeleteMapping("/{id}")
    public void deleteKoala(@PathVariable long id){
        if(id<0){
            throw new ZooException("Id must be positive", HttpStatus.BAD_REQUEST);
        }

        if(!koalas.containsKey(id)){
            throw new ZooException("Could not find id = " + id, HttpStatus.NOT_FOUND);
        }
        koalas.remove(id);
    }
}
