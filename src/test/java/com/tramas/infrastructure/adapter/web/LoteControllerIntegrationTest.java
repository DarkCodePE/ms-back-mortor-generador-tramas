package com.tramas.infrastructure.adapter.web;

import com.tramas.application.dto.CreateLoteRequest;
import com.tramas.application.dto.LoteDto;
import com.tramas.application.service.LoteApplicationService;
import com.tramas.domain.model.EstadoLote;
import com.tramas.domain.model.TipoLote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@WebFluxTest(LoteController.class)
class LoteControllerIntegrationTest {
    
    @Autowired
    private WebTestClient webTestClient;
    
    @MockBean
    private LoteApplicationService loteApplicationService;
    
    @Test
    void crearLote_ConDatosValidos_DeberiaRetornarLoteCreado() {
        // Arrange
        CreateLoteRequest request = new CreateLoteRequest(
                "LOTE001",
                "admin",
                "TRAMA_ATENCION",
                null
        );
        
        LoteDto loteDto = new LoteDto(
                1L,
                "LOTE001",
                LocalDateTime.now(),
                "admin",
                EstadoLote.PENDIENTE.getValor(),
                TipoLote.TRAMA_ATENCION.getValor(),
                null,
                0, 0, 0, 0,
                null, null, null, null,
                null, null
        );
        
        when(loteApplicationService.crearLote(any(CreateLoteRequest.class)))
                .thenReturn(Mono.just(loteDto));
        
        // Act & Assert
        webTestClient.post()
                .uri("/api/v1/lotes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(LoteDto.class)
                .value(lote -> {
                    assert lote.numeroLote().equals("LOTE001");
                    assert lote.usuarioCreacion().equals("admin");
                    assert lote.estadoLote().equals(EstadoLote.PENDIENTE.getValor());
                });
    }
    
    @Test
    void obtenerLote_ConIdValido_DeberiaRetornarLote() {
        // Arrange
        Long loteId = 1L;
        LoteDto loteDto = new LoteDto(
                loteId,
                "LOTE001",
                LocalDateTime.now(),
                "admin",
                EstadoLote.PENDIENTE.getValor(),
                TipoLote.TRAMA_ATENCION.getValor(),
                null,
                0, 0, 0, 0,
                null, null, null, null,
                null, null
        );
        
        when(loteApplicationService.obtenerLotePorId(loteId))
                .thenReturn(Mono.just(loteDto));
        
        // Act & Assert
        webTestClient.get()
                .uri("/api/v1/lotes/{loteId}", loteId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LoteDto.class)
                .value(lote -> {
                    assert lote.loteId().equals(loteId);
                    assert lote.numeroLote().equals("LOTE001");
                });
    }
    
    @Test
    void obtenerLote_ConIdInvalido_DeberiaRetornarNotFound() {
        // Arrange
        Long loteId = 999L;
        
        when(loteApplicationService.obtenerLotePorId(loteId))
                .thenReturn(Mono.empty());
        
        // Act & Assert
        webTestClient.get()
                .uri("/api/v1/lotes/{loteId}", loteId)
                .exchange()
                .expectStatus().isNotFound();
    }
    
    @Test
    void listarLotes_SinFiltros_DeberiaRetornarTodosLosLotes() {
        // Arrange
        LoteDto lote1 = new LoteDto(
                1L, "LOTE001", LocalDateTime.now(), "admin",
                EstadoLote.PENDIENTE.getValor(), TipoLote.TRAMA_ATENCION.getValor(),
                null, 0, 0, 0, 0, null, null, null, null, null, null
        );
        
        LoteDto lote2 = new LoteDto(
                2L, "LOTE002", LocalDateTime.now(), "admin",
                EstadoLote.PROCESADO.getValor(), TipoLote.TRAMA_FARMACIA.getValor(),
                null, 0, 0, 0, 0, null, null, null, null, null, null
        );
        
        when(loteApplicationService.listarTodosLosLotes())
                .thenReturn(Flux.just(lote1, lote2));
        
        // Act & Assert
        webTestClient.get()
                .uri("/api/v1/lotes")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(LoteDto.class)
                .hasSize(2);
    }
    
    @Test
    void crearLote_ConDatosInvalidos_DeberiaRetornarBadRequest() {
        // Arrange
        CreateLoteRequest requestInvalido = new CreateLoteRequest(
                "", // numeroLote vacío - inválido
                "admin",
                "TRAMA_ATENCION",
                null
        );
        
        // Act & Assert
        webTestClient.post()
                .uri("/api/v1/lotes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestInvalido)
                .exchange()
                .expectStatus().isBadRequest();
    }
}