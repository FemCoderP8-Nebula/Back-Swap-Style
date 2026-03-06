package com.swapstyle.swapstyle.dto.response;

import java.time.LocalDateTime;

public record ReserveResponseDTO(
    Integer idReserve,
    LocalDateTime reservationDate,
    LocalDateTime expiryDate,
    String userNameWhoWants,
    String articleTitle,
    Double articlePrice
) {

}