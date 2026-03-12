package com.swapstyle.swapstyle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swapstyle.swapstyle.dto.request.ReserveRequestDTO;
import com.swapstyle.swapstyle.dto.response.ReserveResponseDTO;
import com.swapstyle.swapstyle.service.ReserveService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/reserves")
public class ReserveController {

    private final ReserveService reserveService;

    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @PostMapping
    public ResponseEntity<ReserveResponseDTO> toggleReservation(@RequestBody ReserveRequestDTO dto) {
        return reserveService.toggleReservation(dto)
                .map(response -> new ResponseEntity<>(response, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReserveResponseDTO>> getUserReservations(@PathVariable Integer userId) {
        List<ReserveResponseDTO> reservations = reserveService.getUserReservations(userId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

}