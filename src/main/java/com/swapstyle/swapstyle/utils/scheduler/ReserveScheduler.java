package com.swapstyle.swapstyle.utils.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.swapstyle.swapstyle.service.ReserveService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReserveScheduler {

    private final ReserveService reserveService;

    public ReserveScheduler(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @Scheduled(fixedRate = 60000) // Acordar si implementamos logica de revision cada cuanto tiempo? ejemplo de
                                  // 1h? --> lo paso a cada minuto para la demo
    public void executeReservationCleanup() {
        log.info("Iniciando limpieza automática de reservas expiradas...");
        reserveService.clearExpiredReservations();
        log.info("Limpieza completada con éxito.");
    }

}
