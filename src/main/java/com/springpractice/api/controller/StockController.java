package com.springpractice.api.controller;

import com.springpractice.api.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/{symbol}")
    public ResponseEntity<String> handleHistoryStockData(@PathVariable String symbol) {
        stockService.handleStockRequest(symbol);
        return ResponseEntity.ok("Received stock -> " + symbol);
    }
}
