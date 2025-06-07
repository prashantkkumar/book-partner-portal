package org.example.backend.mapper;



import org.example.backend.dto.StoreDto;
import org.example.backend.entities.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    public StoreDto toDto(Store store) {
        if (store == null) return null;

        return new StoreDto(
                store.getStorId(),
                store.getStorName(),
                store.getStorAddress(),
                store.getCity(),
                store.getState(),
                store.getZip()
        );
    }

    public Store toEntity(StoreDto dto) {
        if (dto == null) return null;

        Store store = new Store();
        store.setStorId(dto.getStorId());
        store.setStorName(dto.getStorName());
        store.setStorAddress(dto.getStorAddress());
        store.setCity(dto.getCity());
        store.setState(dto.getState());
        store.setZip(dto.getZip());
        return store;
    }

    public void updateEntity(Store store, StoreDto dto) {
        if (store == null || dto == null) return;

        // storId is usually not updated (primary key)
        store.setStorName(dto.getStorName());
        store.setStorAddress(dto.getStorAddress());
        store.setCity(dto.getCity());
        store.setState(dto.getState());
        store.setZip(dto.getZip());
    }
}

