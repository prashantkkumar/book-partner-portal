package org.example.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.DiscountDto;
import org.example.backend.entities.Discount;
import org.example.backend.entities.DiscountId;
import org.example.backend.mapper.DiscountMapper;
import org.example.backend.repository.DiscountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    // GET all
    @GetMapping
    public List<DiscountDto> getAllDiscounts() {
        return discountRepository.findAll()
                .stream()
                .map(discountMapper::toDto)
                .toList();
    }

    // POST
    @PostMapping
    public ResponseEntity<DiscountDto> createDiscount(@RequestBody DiscountDto discountDto) {
        Discount discount = discountMapper.toEntity(discountDto);
        Discount saved = discountRepository.save(discount);
        return ResponseEntity.ok(discountMapper.toDto(saved));
    }

    // PUT
    @PutMapping("/{discounttype}/{storId}")
    public ResponseEntity<DiscountDto> updateDiscount(
            @PathVariable String discounttype,
            @PathVariable String storId,
            @RequestBody DiscountDto discountDto
    ) {
        return discountRepository.findById_DiscounttypeAndId_StorId(discounttype, storId)
                .map(existing -> {
                    discountMapper.partialUpdate(discountDto, existing);
                    Discount updated = discountRepository.save(existing);
                    return ResponseEntity.ok(discountMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
