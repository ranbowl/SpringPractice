package com.springpractice.api.serviceimpl.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springpractice.api.config.ServiceConfig;
import com.springpractice.api.model.stock.StockResponse;
import com.springpractice.api.service.stock.StockService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final String STOCK_URL = "https://api.worldtradingdata.com/api/v1/history";

    private ServiceConfig serviceConfig;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public StockServiceImpl(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    private static final Logger logger = LogManager.getLogger(StockServiceImpl.class);

    @Override
    public void handleStockRequest(String symbol) {
        //0 from, 1 to
        List<String> dateList = createDateList();
        RestTemplate restTemplate = new RestTemplate();

        //adding the query params to the URL
        String url = UriComponentsBuilder.fromHttpUrl(STOCK_URL)
                .queryParam("symbol", symbol)
                .queryParam("api_token", serviceConfig.getStockToken())
                .queryParam("date_from", dateList.get(0))
                .queryParam("date_to", dateList.get(1)).toUriString();
        logger.info("URL -> {}", url);

        try {
            ResponseEntity<StockResponse> responseResponseEntity = restTemplate.getForEntity(url, StockResponse.class);
            if (responseResponseEntity.getStatusCode().is2xxSuccessful()) {
                logger.info("Received stock history of symbol -> {}, total number -> {}", symbol, responseResponseEntity.getBody().getHistory().getHisotryPrices().size());
//                for (int i = 0; i < 10; ++i) {
//                    System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseResponseEntity.getBody().getHistory().getHisotryPrices().get(i)));
//                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }

    //create date from and to
    public List<String> createDateList() {
        //get current date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        String dateTo = simpleDateFormat.format(new Date());
        //create date year - 1
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        String dateFrom = simpleDateFormat.format(calendar.getTime());
        List<String> list = new ArrayList<>();
        list.add(dateFrom);
        list.add(dateTo);
        return list;
    }
}
