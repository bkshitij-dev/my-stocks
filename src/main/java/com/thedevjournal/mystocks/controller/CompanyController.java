package com.thedevjournal.mystocks.controller;

import com.thedevjournal.mystocks.constant.AppConstants;
import com.thedevjournal.mystocks.dto.request.CompanyRequestDto;
import com.thedevjournal.mystocks.dto.response.ApiResponse;
import com.thedevjournal.mystocks.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController extends BaseController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody CompanyRequestDto request) {
        companyService.create(request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_SAVE), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> list() {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, companyService.list()),
                HttpStatus.OK);
    }

    @GetMapping("/{scrip}")
    public ResponseEntity<ApiResponse> getByScrip(@PathVariable("scrip") String scrip) {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, companyService.getByScrip(scrip)),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id, @RequestBody CompanyRequestDto request) {
        companyService.update(id, request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_UPDATE), HttpStatus.OK);
    }

    @GetMapping("/fetch")
    public ResponseEntity<ApiResponse> fetch() throws IOException {
        String url = "https://merolagani.com/CompanyList.aspx";
        Document document = Jsoup.connect(url).get();
        Elements elements = document.getElementsByClass("panel panel-default");
        Element trading = elements.get(17);
        Elements el = trading.getElementsByTag("td");

        for (int i=0; i<el.size(); i=i+5) {
            List<Element> grp = el.subList(i,i+5);
            String scrip = grp.get(0).getElementsByTag("a").text();
            String name = grp.get(1).getElementsByTag("td").text();
            boolean exists = companyService.existsByScrip(scrip);
            if (!exists) {
                CompanyRequestDto request = new CompanyRequestDto();
                request.setScrip(scrip);
                request.setName(name);
                request.setSectorId(11L);
                companyService.create(request);
            }
        }
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE), HttpStatus.OK);
    }
}
