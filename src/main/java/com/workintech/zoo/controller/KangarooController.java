package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> get(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getById(@PathVariable int id){
     if(id <= 0){
         throw new ZooException("Id must be greater then 0", HttpStatus.BAD_REQUEST);
     }
     if(!kangaroos.containsKey(id)){
         throw new ZooException("User with given id is not exist" + id,HttpStatus.NOT_FOUND);

     }
      return   kangaroos.get(id);
    }

    @PostMapping
    public  Kangaroo post(@RequestBody Kangaroo kangaroo){
        if(kangaroo.getId() <= 0){
            throw new ZooException("Id must be greater then 0", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo put(@PathVariable int id ,@RequestBody Kangaroo updatedKangaroo){
        if(kangaroos.containsKey(id)){
            kangaroos.put(id,updatedKangaroo);
            return updatedKangaroo;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable int id){
        if(kangaroos.containsKey(id)){
            Kangaroo removedKangaroo = kangaroos.remove(id);
            return removedKangaroo;
        }
        return null;
    }



}
