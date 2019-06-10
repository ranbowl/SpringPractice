package com.springpractice.api.model.stock;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.ArrayList;
import java.util.List;

public class History {

    private List<StockPriceDailyModel> hisotryPrices = new ArrayList<>();

    @JsonAnySetter
    public void handleHistoryPrices(String date, StockPriceDailyModel stockPriceDailyModel) {
        stockPriceDailyModel.setDate(date);
        hisotryPrices.add(stockPriceDailyModel);
    }

    public List<StockPriceDailyModel> getHisotryPrices() {
        return hisotryPrices;
    }

    public void setHisotryPrices(List<StockPriceDailyModel> hisotryPrices) {
        this.hisotryPrices = hisotryPrices;
    }
}
