package com.swapstyle.swapstyle.service;

import java.util.List;
import java.util.Optional;

import com.swapstyle.swapstyle.dto.request.ReserveRequestDTO;
import com.swapstyle.swapstyle.dto.response.ReserveResponseDTO;

public interface ReserveService {

    Optional<ReserveResponseDTO> toggleReservation(ReserveRequestDTO dto);

    List<ReserveResponseDTO> getUserReservations(Integer userId);

    void clearExpiredReservations();
}
