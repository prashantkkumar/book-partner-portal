package org.example.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.backend.entities.*;
import org.example.backend.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final AuthorRepository authorRepository;
    private final TitleRepository titleRepository;
    private final RoyschedRepository royschedRepository;
    private final TitleauthorRepository titleauthorRepository;
    private final SaleRepository salesRepository;


    // 1. Number of titles per author
    @GetMapping("/author/title/count")
    public Map<String, Long> getTitleCountPerAuthor() {
        Map<String, Long> result = new HashMap<>();
        for (Author author : authorRepository.findAll()) {
            long count = titleauthorRepository.findByAu_AuId(author.getAuId()).size();
            result.put(author.getAuFname() + " " + author.getAuLname(), count);
        }
        return result;
    }

    // 2. Total sales per store
    @GetMapping("/store/sales")
    public ResponseEntity<Map<String, Integer>> getTotalSalesPerStore() {
        List<Sale> sales = salesRepository.findAll();
        Map<String, Integer> result = new HashMap<>();
        for (Sale sale : sales) {
            String storeId = sale.getStor().getStorId();
            result.put(storeId, result.getOrDefault(storeId, 0) + sale.getQty());
        }
        return ResponseEntity.ok(result);
    }


    // 3. Total sales per title
    @GetMapping("/title/sales")
    public ResponseEntity<Map<String, Integer>> getTotalSalesPerTitle() {
        List<Sale> sales = salesRepository.findAll();
        Map<String, Integer> result = new HashMap<>();
        for (Sale sale : sales) {
            String titleId = sale.getTitle().getTitleId();
            result.put(titleId, result.getOrDefault(titleId, 0) + sale.getQty());
        }
        return ResponseEntity.ok(result);
    }


    // 4. Total royalty per author
    @GetMapping("/author/royalties")
    public Map<String, Double> getRoyaltiesPerAuthor() {
        Map<String, Double> royalties = new HashMap<>();

        for (Author author : authorRepository.findAll()) {
            List<Titleauthor> titleauthors = titleauthorRepository.findByAu_AuId(author.getAuId());
            double totalRoyalty = 0;

            for (Titleauthor ta : titleauthors) {
                List<Roysched> royscheds = royschedRepository.findByTitle_TitleId(ta.getTitle().getTitleId());

                for (Roysched r : royscheds) {
                    int avgSales = (r.getLorange() + r.getHirange()) / 2;
                    totalRoyalty += avgSales * (r.getRoyalty() / 100.0);
                }
            }

            royalties.put(author.getAuFname() + " " + author.getAuLname(), totalRoyalty);
        }

        return royalties;
    }

    // 5. Top authors by sales
    @GetMapping("/top-authors")
    public List<Map.Entry<String, Integer>> getTopAuthorsBySales() {
        Map<String, Integer> authorSales = new HashMap<>();

        for (Author author : authorRepository.findAll()) {
            List<Titleauthor> titleauthors = titleauthorRepository.findByAu_AuId(author.getAuId());
            int totalSales = 0;

            for (Titleauthor ta : titleauthors) {
                List<Sale> salesList = salesRepository.findByTitle_TitleId(ta.getTitle().getTitleId());
                totalSales += salesList.stream().mapToInt(Sale::getQty).sum();
            }

            authorSales.put(author.getAuFname() + " " + author.getAuLname(), totalSales);
        }

        return authorSales.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    // 6. Top titles by revenue
    @GetMapping("/top-titles")
    public List<Map.Entry<String, Double>> getTopTitlesByRevenue() {
        Map<String, Double> titleRevenue = new HashMap<>();

        for (Title title : titleRepository.findAll()) {
            List<Sale> salesList = salesRepository.findByTitle_TitleId(title.getTitleId());
            double revenue = salesList.stream()
                    .mapToDouble(sale -> sale.getQty() * title.getPrice().doubleValue())
                    .sum();
            titleRevenue.put(title.getTitle(), revenue);
        }

        return titleRevenue.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());
    }


   //7  Total monthly-sales
    @GetMapping("/monthly-sales")
    public ResponseEntity<Map<String, Integer>> getMonthlySalesSummary() {
        List<Sale> sales = salesRepository.findAll();
        Map<String, Integer> result = new HashMap<>();
        for (Sale sale : sales) {
            String month = sale.getOrdDate().toString().substring(0, 7); // e.g. "2025-06"
            result.put(month, result.getOrDefault(month, 0) + sale.getQty());
        }
        return ResponseEntity.ok(result);
    }



}
