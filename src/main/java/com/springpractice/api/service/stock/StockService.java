package com.springpractice.api.service.stock;

import com.springpractice.api.model.stock.StockResponse;

public interface StockService {

    public void handleStockRequest(String symbol);
}
