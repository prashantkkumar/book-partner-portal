package org.example.backend.controllers;

import org.example.backend.entities.Store;
import org.example.backend.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // GET /api/stores/city/{city} - Get stores by city
    @GetMapping("/city/{city}")
    public List<Store> getStoresByCity(@PathVariable String city) {
        return store.findByCity(city);
    }

    // GET /api/stores/state/{state} - Get stores by state
    @GetMapping("/state/{state}")
    public List<Store> getStoresByState(@PathVariable String state) {
        return store.findByState(state);
    }

    // GET /api/stores/zip/{zip} - Get stores by ZIP
    @GetMapping("/zip/{zip}")
    public List<Store> getStoresByZip(@PathVariable String zip) {
        return store.findByZip(zip);
    }


    // POST /api/stores - Add a new store
    @PostMapping
    public Store createStore(@RequestBody Store stores) {
        return store.save(stores);
    }

    // PUT /api/stores/{stor_id} - Update a store
    @PutMapping("/{stor_id}")
    public ResponseEntity<Store> updateStore(@PathVariable String stor_id, @RequestBody Store storeDetails) {
        Optional<Store> optionalStore = store.findById(stor_id);
        if (optionalStore.isPresent()) {
            Store stores = optionalStore.get();
            stores.setStorName(storeDetails.getStorName());
            stores.setStorAddress(storeDetails.getStorAddress());
            stores.setCity(storeDetails.getCity());
            stores.setState(storeDetails.getState());
            stores.setZip(storeDetails.getZip());
            return ResponseEntity.ok(store.save(stores));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

