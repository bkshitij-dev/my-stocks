package com.thedevjournal.mystocks.service.impl;

import com.thedevjournal.mystocks.dto.request.StockHistoryRequestDto;
import com.thedevjournal.mystocks.dto.request.StockMarketHistoryRequestDto;
import com.thedevjournal.mystocks.dto.response.*;
import com.thedevjournal.mystocks.mapper.StockHistoryMapper;
import com.thedevjournal.mystocks.model.Company;
import com.thedevjournal.mystocks.service.CompanyService;
import com.thedevjournal.mystocks.service.StockHistoryService;
import com.thedevjournal.mystocks.service.StockMarketHistoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

@Service
@RequiredArgsConstructor
public class StockHistoryServiceImpl implements StockHistoryService {

    private final CompanyService companyService;
    private final StockMarketHistoryService stockMarketHistoryService;
    private final StockHistoryMapper stockHistoryMapper;

    private static final Logger logger = LoggerFactory.getLogger(StockHistoryServiceImpl.class);
    private static final Integer PERIOD = 14;

    @Override
    @Transactional
    public void fetchLiveData() throws Exception {
        String url = "https://www.sharesansar.com/live-trading";
        logger.info("Fetching data from {}>>>>>>", url);
        Document document = Jsoup.connect(url).get();
        String lastUpdated = Objects.requireNonNull(document.getElementById("dDate")).text();

        StockMarketHistoryRequestDto stockMarketHistoryRequestDto = StockMarketHistoryRequestDto.builder()
                .date(lastUpdated.split("\\s+")[0])
                .time(lastUpdated.split("\\s+")[1])
                .build();

        Elements sliderElements = document.getElementsByClass("mu-list");

        for(Element sliderElement: sliderElements) {
            if (sliderElement.getElementsByTag("h4").text().equals("NEPSE Index")) {
                stockMarketHistoryRequestDto.setIndex(getValue(Objects.requireNonNull(
                        sliderElement.getElementsByClass("mu-value").first())));
                stockMarketHistoryRequestDto.setPercentageChange(getValue(Objects.requireNonNull(
                        sliderElement.getElementsByClass("mu-percent").first())));
            }
        }
        Long stockMarketHistoryId = stockMarketHistoryService.create(stockMarketHistoryRequestDto);

        // Select all <tr> elements
        Elements rows = document.select("tr");

        // Iterate over each <tr> element
        for (int i=1; i<rows.size(); i++) {
            // Select the <a> element for the name
            Element link = rows.get(i).selectFirst("a");
            if (link != null) {
                Elements tdElements = rows.get(i).select("td");
                StockHistoryRequestDto stockHistoryDto = StockHistoryRequestDto.builder()
                        .scrip(link.text())
                        .ltp(getValue(tdElements.get(2)))
                        .pointsChange(getValue(tdElements.get(3)))
                        .percentageChange(getValue(tdElements.get(4)))
                        .open(getValue(tdElements.get(5)))
                        .high(getValue(tdElements.get(6)))
                        .low(getValue(tdElements.get(7)))
                        .volume(getValue(tdElements.get(8)))
                        .stockMarketHistoryId(stockMarketHistoryId)
                        .build();
                create(stockHistoryDto);
            }
        }
    }

    private void create(StockHistoryRequestDto request) throws Exception {
        try {
            Company company = companyService.findByScrip(request.getScrip());
            stockHistoryMapper.updateLiveData(request, company.getId());
        } catch (Exception e) {
           logger.error(e.getMessage());
        }
    }

    private BigDecimal getValue(Element e) {
        return new BigDecimal(e.text().replace(",","").replace("%", "").trim());
    }

    @Override
    public RecentStockDataResponseDo getRecentData(String scrip) {
        RecentStockDataResponseDo data = stockHistoryMapper.getRecentData(scrip);
        Collections.reverse(data.getRecentData());
        return data;
    }

    @Override
    public List<StockMFIResponseDto> getMFI() {
        List<StockMFIResponseDto> mfis = new ArrayList<>();
        List<Company> companies = companyService.listMajor();
        companies.forEach(c -> {
            List<StockMFIParamsResponseDto> data = stockHistoryMapper.getMFI(c.getScrip());
            BigDecimal positiveMoneyFlow = BigDecimal.ZERO;
            BigDecimal negativeMoneyFlow = BigDecimal.ZERO;
            for (int i=0; i<data.size()-1; i++) {
                StockMFIParamsResponseDto datum = data.get(i);
                if (datum.getTypicalPrice().compareTo(data.get(i+1).getTypicalPrice()) > 0) {
                    positiveMoneyFlow = positiveMoneyFlow.add(datum.getTypicalPrice().multiply(datum.getVolume()));
                } else {
                    negativeMoneyFlow = negativeMoneyFlow.add(datum.getTypicalPrice().multiply(datum.getVolume()));
                }
            }
            if (negativeMoneyFlow.compareTo(BigDecimal.ZERO) == 0) {
                mfis.add(StockMFIResponseDto.builder()
                        .scrip(c.getScrip())
                        .mfi(new BigDecimal(100))
                        .build());
            } else {
                BigDecimal moneyFlowRatio = positiveMoneyFlow.divide(negativeMoneyFlow.abs(), 2,
                        RoundingMode.HALF_UP);
                mfis.add(StockMFIResponseDto.builder()
                        .scrip(c.getScrip())
                        .mfi(new BigDecimal(100)
                                .subtract(new BigDecimal(100)
                                        .divide(BigDecimal.ONE.add(moneyFlowRatio), 2, RoundingMode.HALF_UP)))
                        .build());
            }
        });
        mfis.sort(Comparator.comparing(StockMFIResponseDto::getMfi).reversed());
        return mfis;
    }

    @Override
    public List<StockRSIResponseDto> getRSI() {
        List<StockRSIResponseDto> rsis = new ArrayList<>();
        List<Company> companies = companyService.listMajor();
        companies.forEach(c -> {
            List<StockRSIParamsResponseDto> data = stockHistoryMapper.getRSI(c.getScrip());
            if (data.size() < PERIOD) {
                return;
            }
            BigDecimal gains = BigDecimal.ZERO;
            BigDecimal losses = BigDecimal.ZERO;
            for (StockRSIParamsResponseDto datum : data) {
                if (datum.getPointsChange().compareTo(BigDecimal.ZERO) > 0) {
                    gains = gains.add(datum.getPointsChange());
                } else {
                    losses = losses.add(datum.getPointsChange());
                }
            }
            if (losses.compareTo(BigDecimal.ZERO) == 0) {
                rsis.add(StockRSIResponseDto.builder()
                        .scrip(c.getScrip())
                        .rsi(new BigDecimal(100))
                        .build());
            } else {
                BigDecimal averageGains = gains.divide(BigDecimal.valueOf(PERIOD), 2, RoundingMode.HALF_UP);
                BigDecimal averageLosses = losses.divide(BigDecimal.valueOf(PERIOD), 2, RoundingMode.HALF_UP);
                BigDecimal relativeStrength = averageGains.divide(averageLosses.abs(), 2, RoundingMode.HALF_UP);
                rsis.add(StockRSIResponseDto.builder()
                        .scrip(c.getScrip())
                        .rsi(new BigDecimal(100)
                                .subtract(new BigDecimal(100)
                                        .divide(BigDecimal.ONE.add(relativeStrength), 2, RoundingMode.HALF_UP)))
                        .build());
            }
        });
        rsis.sort(Comparator.comparing(StockRSIResponseDto::getRsi).reversed());
        return rsis;
    }
}
