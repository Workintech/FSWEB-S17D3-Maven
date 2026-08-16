package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Integer, Kangaroo> kangaroos;

    public KangarooController() {
        kangaroos = new HashMap<>();
    }

    // GET - Tüm kangaroos
    @GetMapping
    public List<Kangaroo> getAll() {
        return new ArrayList<>(kangaroos.values());
    }

    // GET - ID'ye göre kangaroo
    @GetMapping("/{id}")
    public Kangaroo getById(@PathVariable Integer id) {

        Kangaroo kangaroo = kangaroos.get(id);

        if (kangaroo == null) {
            throw new ZooException(
                    "Kangaroo bulunamadı: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        return kangaroo;
    }

    // POST - Kangaroo ekleme
    @PostMapping
    public Kangaroo create(@RequestBody Kangaroo kangaroo) {

        if (kangaroo.getId() == null) {
            throw new ZooException(
                    "Kangaroo id boş olamaz.",
                    HttpStatus.BAD_REQUEST
            );
        }

        kangaroos.put(kangaroo.getId(), kangaroo);

        return kangaroo;
    }

    // PUT - Güncelleme
    @PutMapping("/{id}")
    public Kangaroo update(
            @PathVariable Integer id,
            @RequestBody Kangaroo kangaroo) {

        if (!kangaroos.containsKey(id)) {
            throw new ZooException(
                    "Kangaroo bulunamadı: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        kangaroo.setId(id);
        kangaroos.put(id, kangaroo);

        return kangaroo;
    }

    // DELETE - Silme
    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable Integer id) {

        Kangaroo kangaroo = kangaroos.get(id);

        if (kangaroo == null) {
            throw new ZooException(
                    "Kangaroo bulunamadı: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        kangaroos.remove(id);

        return kangaroo;
    }
}