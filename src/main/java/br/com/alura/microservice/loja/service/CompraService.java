package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorCLient;
import br.com.alura.microservice.loja.dto.CompraDTO;

import br.com.alura.microservice.loja.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompraService {

    @Autowired
    private FornecedorCLient fornecedorCLient;
    public Compra realizaCompra(CompraDTO compra) {

        InfoFornecedorDTO info = fornecedorCLient.getInfoPorEstado(compra.getEndereco().getEstado());
        InfoPedidoDTO pedido = fornecedorCLient.realizaPedido(compra.getItens());

        System.out.println(info.getEndereco());

        Compra compraSalva = new Compra();

        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());

        return compraSalva;


    }
}

