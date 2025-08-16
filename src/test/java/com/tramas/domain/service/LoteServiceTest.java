package com.tramas.domain.service;

import com.tramas.domain.model.EstadoLote;
import com.tramas.domain.model.Lote;
import com.tramas.domain.model.TipoLote;
import com.tramas.domain.port.LoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoteServiceTest {
    
    @Mock
    private LoteRepository loteRepository;
    
    private LoteService loteService;
    
    @BeforeEach
    void setUp() {
        loteService = new LoteService(loteRepository);
    }
    
    @Test
    void crearLote_CuandoLoteNoExiste_DeberiaCrearLoteExitosamente() {
        // Arrange
        String numeroLote = "LOTE001";
        String usuarioCreacion = "admin";
        TipoLote tipoLote = TipoLote.TRAMA_ATENCION;
        
        Lote loteEsperado = Lote.create(numeroLote, usuarioCreacion, tipoLote);
        
        when(loteRepository.existsByNumeroLote(numeroLote))
                .thenReturn(Mono.just(false));
        when(loteRepository.save(any(Lote.class)))
                .thenReturn(Mono.just(loteEsperado));
        
        // Act & Assert
        StepVerifier.create(loteService.crearLote(numeroLote, usuarioCreacion, tipoLote))
                .expectNext(loteEsperado)
                .verifyComplete();
    }
    
    @Test
    void crearLote_CuandoLoteYaExiste_DeberiaLanzarExcepcion() {
        // Arrange
        String numeroLote = "LOTE001";
        String usuarioCreacion = "admin";
        TipoLote tipoLote = TipoLote.TRAMA_ATENCION;
        
        when(loteRepository.existsByNumeroLote(numeroLote))
                .thenReturn(Mono.just(true));
        
        // Act & Assert
        StepVerifier.create(loteService.crearLote(numeroLote, usuarioCreacion, tipoLote))
                .expectErrorMatches(throwable -> 
                    throwable instanceof IllegalArgumentException &&
                    throwable.getMessage().contains("El lote ya existe"))
                .verify();
    }
    
    @Test
    void actualizarEstadoLote_CuandoLoteExiste_DeberiaActualizarEstado() {
        // Arrange
        Long loteId = 1L;
        EstadoLote nuevoEstado = EstadoLote.EN_PROCESO;
        
        Lote loteExistente = Lote.create("LOTE001", "admin", TipoLote.TRAMA_ATENCION);
        Lote loteActualizado = loteExistente.withEstado(nuevoEstado);
        
        when(loteRepository.findById(loteId))
                .thenReturn(Mono.just(loteExistente));
        when(loteRepository.save(any(Lote.class)))
                .thenReturn(Mono.just(loteActualizado));
        
        // Act & Assert
        StepVerifier.create(loteService.actualizarEstadoLote(loteId, nuevoEstado))
                .expectNext(loteActualizado)
                .verifyComplete();
    }
    
    @Test
    void obtenerPorId_CuandoLoteNoExiste_DeberiaLanzarExcepcion() {
        // Arrange
        Long loteId = 999L;
        
        when(loteRepository.findById(loteId))
                .thenReturn(Mono.empty());
        
        // Act & Assert
        StepVerifier.create(loteService.obtenerPorId(loteId))
                .expectErrorMatches(throwable -> 
                    throwable instanceof IllegalArgumentException &&
                    throwable.getMessage().contains("Lote no encontrado"))
                .verify();
    }
}