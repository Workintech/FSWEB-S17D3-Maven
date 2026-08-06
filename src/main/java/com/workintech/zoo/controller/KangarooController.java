package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    Map<Long, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> getAllKangaroos(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangaroosById(@PathVariable long id){
        if(id<0){
            throw new ZooException("Id must be positive", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Could not find id = " + id, HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getId() < 0) {
            throw new ZooException("Id must be positive", HttpStatus.BAD_REQUEST);
        }
        if (kangaroos.containsKey(kangaroo.getId())) {
            throw new ZooException("Id is already in use = " + kangaroo.getId(), HttpStatus.CONFLICT);
        }
        if(kangaroo.getName() == null){
            throw new ZooException("Invalid kangaroo" + kangaroo.getId(), HttpStatus.BAD_REQUEST);
        }

        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo editKangaroo(@PathVariable long id, @RequestBody Kangaroo kangaroo){
        if(id<0){
            throw new ZooException("Id must be positive", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Could not find id = " + id, HttpStatus.NOT_FOUND);
        }

        Kangaroo kangaroo1 = new Kangaroo(kangaroo.getId(),kangaroo.getName(),kangaroo.getHeight(),kangaroo.getWeight(),kangaroo.getGender(),kangaroo.isAggressive());
        kangaroos.put(kangaroo.getId(),kangaroo1);
        return kangaroo1;

    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable long id){
        if(id<0){
            throw new ZooException("Id must be positive", HttpStatus.BAD_REQUEST);
        }

        if(!kangaroos.containsKey(id)){
            throw new ZooException("Could not find id = " + id, HttpStatus.NOT_FOUND);
        }
        Kangaroo kangaroo = kangaroos.remove(id);
        return kangaroo;
    }


}
