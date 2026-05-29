package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer,Koala> koalas;

    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> get(){
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getByID(@PathVariable int id){
        if(id <= 0){
            throw  new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(!koalas.containsKey(id)){
            throw new ZooException("User with given id is not exist" + id,HttpStatus.NOT_FOUND);
        }

          return koalas.get(id);
    }

    @PostMapping
    public Koala post(@RequestBody Koala koala){
        if(koala.getId() <= 0){
            throw new ZooException("Id must be greater then 0", HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(),koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala put(@PathVariable int id,@RequestBody Koala updatedKoala){
        if(koalas.containsKey(id)){
            koalas.put(id,updatedKoala);
            return  updatedKoala;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable int id){
        if(koalas.containsKey(id)){
            Koala removedKoala = koalas.remove(id);
            return  removedKoala;
        }
        return null;
    }





}
