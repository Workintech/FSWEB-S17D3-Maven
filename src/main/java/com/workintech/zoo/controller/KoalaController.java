package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    public KoalaController() {
        koalas = new HashMap<>();
    }

    // GET - Tüm koalalar
    @GetMapping
    public List<Koala> getAll() {
        return new ArrayList<>(koalas.values());
    }

    // GET - ID'ye göre koala
    @GetMapping("/{id}")
    public Koala getById(@PathVariable Integer id) {

        Koala koala = koalas.get(id);

        if (koala == null) {
            throw new ZooException(
                    "Koala bulunamadı: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        return koala;
    }

    // POST - Koala ekleme
    @PostMapping
    public Koala create(@RequestBody Koala koala) {

        if (koala.getId() == null) {
            throw new ZooException(
                    "Koala id boş olamaz.",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (koalas.containsKey(koala.getId())) {
            throw new ZooException(
                    "Bu id ile kayıtlı bir koala zaten var.",
                    HttpStatus.BAD_REQUEST
            );
        }

        koalas.put(koala.getId(), koala);

        return koala;
    }

    // PUT - Güncelleme
    @PutMapping("/{id}")
    public Koala update(
            @PathVariable Integer id,
            @RequestBody Koala koala) {

        if (!koalas.containsKey(id)) {
            throw new ZooException(
                    "Koala bulunamadı: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        koala.setId(id);
        koalas.put(id, koala);

        return koala;
    }

    // DELETE - Silme
    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable Integer id) {

        Koala koala = koalas.get(id);

        if (koala == null) {
            throw new ZooException(
                    "Koala bulunamadı: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        koalas.remove(id);

        return koala;
    }
}