package com.springpractice.api.controller;

import com.springpractice.api.service.QueueService;
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

    private QueueService queueService;

    @Autowired
    public StockController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping("/{symbol}")
    public ResponseEntity<String> handleHistoryStockData(@PathVariable String symbol) {
        queueService.pushTest(symbol);
        return ResponseEntity.ok("Received stock -> " + symbol);
    }
}
