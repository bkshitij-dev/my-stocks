package com.thedevjournal.mystocks.controller;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import com.thedevjournal.mystocks.constant.AppConstants;
import com.thedevjournal.mystocks.dto.request.SectorRequestDto;
import com.thedevjournal.mystocks.dto.response.ApiResponse;
import com.thedevjournal.mystocks.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sectors")
@RequiredArgsConstructor
public class SectorController extends BaseController {

    private final SectorService sectorService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody SectorRequestDto request) {
        sectorService.create(request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_SAVE), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> list() {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, sectorService.list()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, sectorService.get(id)),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id, @RequestBody SectorRequestDto request) {
        sectorService.update(id, request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_UPDATE), HttpStatus.OK);
    }
}
