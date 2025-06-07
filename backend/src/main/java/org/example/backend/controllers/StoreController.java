package org.example.backend.controllers;

import org.example.backend.entities.Store;
import org.example.backend.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreRepository store;

    @GetMapping
    public List<Store> getAllStores(){
        return store.findAll();
    }

    // GET /api/stores/{stor_id} - Get store by ID
    @GetMapping("/{stor_id}")
    public ResponseEntity<Store> getStoreById(@PathVariable String stor_id) {
        Optional<Store> stores = store.findById(stor_id);
        return stores.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

