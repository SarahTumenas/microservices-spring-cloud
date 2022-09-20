package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorCLient;
import br.com.alura.microservice.loja.controller.dto.CompraDTO;

import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompraService {

    @Autowired
    private FornecedorCLient fornecedorCLient;
    public void realizaCompra(CompraDTO compra) {

        InfoFornecedorDTO info = fornecedorCLient.getInfoPorEstado(compra.getEndereco().getEstado());

        System.out.println(info.getEndereco());

    }
}

