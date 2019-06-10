package com.springpractice.api.stock;

import com.springpractice.api.serviceimpl.stock.StockServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {
    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    public void testDateString() {
        System.out.println(stockService.createDateList().get(0) + " -> " + stockService.createDateList().get(1));
    }

}
