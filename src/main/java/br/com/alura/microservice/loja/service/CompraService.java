package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorCLient;
import br.com.alura.microservice.loja.dto.CompraDTO;

import br.com.alura.microservice.loja.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.model.Compra;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private FornecedorCLient fornecedorCLient;
    public Compra realizaCompra(CompraDTO compra) {

        final String estado = compra.getEndereco().getEstado();

        LOG.info("buscando informações do fornecedor de {}", estado);

        InfoFornecedorDTO info = fornecedorCLient.getInfoPorEstado(estado);

        LOG.info("realizando um pedido");
        InfoPedidoDTO pedido = fornecedorCLient.realizaPedido(compra.getItens());

        System.out.println(info.getEndereco());

        Compra compraSalva = new Compra();

        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());

        return compraSalva;


    }
}

