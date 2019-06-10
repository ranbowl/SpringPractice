package com.springpractice.api.model.stock;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History {

    private Map<String, StockPriceDailyModel> hisotryPrices = new HashMap<>();

    @JsonAnySetter
    public void handleHistoryPrices(String date, StockPriceDailyModel stockPriceDailyModel) {
        hisotryPrices.put(date, stockPriceDailyModel);
    }

    public Map<String, StockPriceDailyModel> getHisotryPrices() {
        if (hisotryPrices == null) {
            hisotryPrices = new HashMap<>();
        }
        return hisotryPrices;
    }

    public void setHisotryPrices(Map<String, StockPriceDailyModel> hisotryPrices) {
        this.hisotryPrices = hisotryPrices;
    }
}
