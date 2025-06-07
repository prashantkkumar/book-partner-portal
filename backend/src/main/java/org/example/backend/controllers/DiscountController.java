package org.example.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.DiscountDto;
import org.example.backend.entities.Discount;
import org.example.backend.entities.Store;
import org.example.backend.mapper.DiscountMapper;
import org.example.backend.repository.DiscountRepository;
import org.example.backend.repository.StoreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountRepository discountRepository;
    private final StoreRepository storeRepository;
    private final DiscountMapper discountMapper;

    // GET all discounts
    @GetMapping
    public List<DiscountDto> getAllDiscounts() {
        return discountRepository.findAll()
                .stream()
                .map(discountMapper::toDto)
                .toList();
    }

    // POST create new discount
    @PostMapping
    public ResponseEntity<DiscountDto> createDiscount(
            @RequestParam String storId,
            @RequestBody DiscountDto discountDto
    ) {
        return storeRepository.findById(storId)
                .map(store -> {
                    Discount discount = discountMapper.toEntity(discountDto);
                    discount.setStor(store);
                    Discount saved = discountRepository.save(discount);
                    return ResponseEntity.ok(discountMapper.toDto(saved));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT update discount by stor_id and discounttype
    @PutMapping("/{storId}/{discounttype}")
    public ResponseEntity<DiscountDto> updateDiscount(
            @PathVariable String storId,
            @PathVariable String discounttype,
            @RequestBody DiscountDto discountDto
    ) {
        return discountRepository.findByStor_StorIdAndDiscounttype(storId, discounttype)
                .map(existing -> {
                    discountMapper.partialUpdate(discountDto, existing);
                    Discount updated = discountRepository.save(existing);
                    return ResponseEntity.ok(discountMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
