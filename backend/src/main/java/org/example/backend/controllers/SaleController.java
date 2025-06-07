package org.example.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.SaleDto;
import org.example.backend.dto.SaleIdDto;
import org.example.backend.entities.Sale;
import org.example.backend.entities.SaleId;
import org.example.backend.mapper.SaleMapper;
import org.example.backend.repository.SaleRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    // GET all sales
    @GetMapping
    public List<SaleDto> getAllSales() {
        return saleRepository.findAll().stream().map(saleMapper::toDto).toList();
    }

    // POST new sale
    @PostMapping
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDto, @RequestParam String storId,
                                              @RequestParam String ordNum, @RequestParam String titleId) {
        SaleIdDto idDto = new SaleIdDto(storId, ordNum, titleId);
        Sale sale = saleMapper.toEntity(saleDto, idDto);
        return ResponseEntity.ok(saleMapper.toDto(saleRepository.save(sale)));
    }

    // PUT update sale
    @PutMapping("/{storId}/{ordNum}/{titleId}")
    public ResponseEntity<SaleDto> updateSale(@PathVariable String storId,
                                              @PathVariable String ordNum,
                                              @PathVariable String titleId,
                                              @RequestBody SaleDto dto) {
        SaleId id = new SaleId();
        id.setStorId(storId);
        id.setOrdNum(ordNum);
        id.setTitleId(titleId);

        return saleRepository.findById(id)
                .map(existing -> {
                    saleMapper.partialUpdate(dto, existing);
                    return ResponseEntity.ok(saleMapper.toDto(saleRepository.save(existing)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // GET sales by store
    @GetMapping("/store/{storeId}")
    public List<SaleDto> getSalesByStore(@PathVariable String storeId) {
        return saleRepository.findByStor_StorId(storeId).stream().map(saleMapper::toDto).toList();
    }

    // GET sales by title
    @GetMapping("/title/{titleId}")
    public List<SaleDto> getSalesByTitle(@PathVariable String titleId) {
        return saleRepository.findByTitle_TitleId(titleId).stream().map(saleMapper::toDto).toList();
    }

    // GET sales by date
    @GetMapping("/date/{ordDate}")
    public List<SaleDto> getSalesByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant ordDate) {
        return saleRepository.findByOrdDate(ordDate).stream().map(saleMapper::toDto).toList();
    }
}
