package com.tramas.infrastructure.config;

import com.tramas.domain.port.AseguradoRepository;
import com.tramas.domain.port.LoteRepository;
import com.tramas.domain.port.TramaAtencionRepository;
import com.tramas.domain.service.LoteService;
import com.tramas.domain.service.TramaAtencionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServiceConfig {
    
    @Bean
    public LoteService loteService(LoteRepository loteRepository) {
        return new LoteService(loteRepository);
    }
    
    @Bean
    public TramaAtencionService tramaAtencionService(
            TramaAtencionRepository tramaAtencionRepository,
            LoteRepository loteRepository) {
        return new TramaAtencionService(tramaAtencionRepository, loteRepository);
    }
}