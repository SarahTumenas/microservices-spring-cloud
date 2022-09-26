package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorCLient;
import br.com.alura.microservice.loja.client.TransportadorClient;
import br.com.alura.microservice.loja.dto.*;

import br.com.alura.microservice.loja.model.Compra;
import br.com.alura.microservice.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private FornecedorCLient fornecedorCLient;

    @Autowired
    private TransportadorClient transportadorClient;

    @Autowired
    private CompraRepository compraRepository;

    @HystrixCommand
    public Compra getById(Long id) {
        return compraRepository.findById(id).orElse(new Compra());

    }

    @HystrixCommand(fallbackMethod = "realizaCompraFallback" )
    public Compra realizaCompra(CompraDTO compra) {

        final String estado = compra.getEndereco().getEstado();

        LOG.info("buscando informações do fornecedor de {}", estado);

        InfoFornecedorDTO info = fornecedorCLient.getInfoPorEstado(estado);

        LOG.info("realizando um pedido");
        InfoPedidoDTO pedido = fornecedorCLient.realizaPedido(compra.getItens());

        InfoEntregaDTO entregaDto = new InfoEntregaDTO();
        entregaDto.setPedidoId(pedido.getId());
        entregaDto.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
        entregaDto.setEnderecoOrigem(info.getEndereco());
        entregaDto.setEnderecoDestino(compra.getEndereco().toString());

        VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDto);

        Compra compraSalva = new Compra();

        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        compraSalva.setDa

        compraRepository.save(compraSalva);

        return compraSalva;
    }

    public Compra realizaCompraFallback(CompraDTO compra) {
        Compra compraFallback = new Compra();
        compraFallback.setEnderecoDestino(compra.getEndereco().toString());
        return compraFallback;
    }
}

