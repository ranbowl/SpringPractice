package com.springpractice.api.model.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockPriceDailyModel {

    private String open;

    private String close;

    private String high;

    private String low;

    private String volume;
}
