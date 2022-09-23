package br.com.alura.microservice.transportador.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long pedidoId;

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    private LocalDate dataParaBusca;

    private LocalDate previsaoParaEntrega;

    private String enderecoOrigem;

    private String enderecoDestino;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataParaBusca() {
        return dataParaBusca;
    }

    public void setDataParaBusca(LocalDate dataParaBusca) {
        this.dataParaBusca = dataParaBusca;
    }

    public LocalDate getPrevisaoParaEntrega() {
        return previsaoParaEntrega;
    }

    public void setPrevisaoParaEntrega(LocalDate previsaoParaEntrega) {
        this.previsaoParaEntrega = previsaoParaEntrega;
    }

    public String getEnderecoOrigem() {
        return enderecoOrigem;
    }

    public void setEnderecoOrigem(String enderecoOrigem) {
        this.enderecoOrigem = enderecoOrigem;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }
}
