package com.mycompany.appbanco.service;

import com.mycompany.appbanco.dao.ClienteDao;
import com.mycompany.appbanco.model.ClienteModel;
import java.math.BigDecimal;

public class ClienteService {

    private ClienteDao clienteDao;

    // Construtor que inicializa o ClienteDao
    public ClienteService() {
        clienteDao = new ClienteDao();
    }

    // Método para inserir um cliente
    public void inserirCliente(ClienteModel cliente) {
        if (cliente == null) {
            System.out.println("Cliente não pode ser nulo.");
            return;
        }
        
        if (cliente.getChavePix() == null || cliente.getChavePix().isEmpty()) {
            System.out.println("Chave PIX do cliente não pode ser vazia.");
            return;
        }
        
        // Chama o método de inserção no DAO
        clienteDao.inserir(cliente);
    }

    // Método para deletar um cliente
    public void deletarCliente(Long clienteId) {
        if (clienteId == null) {
            System.out.println("ID do cliente não pode ser nulo.");
            return;
        }
        
        ClienteModel cliente = clienteDao.findById(clienteId);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        // Chama o método de exclusão no DAO
        clienteDao.deletar(cliente);
    }

    // Método para sacar de um cliente
    public void sacar(Long clienteId, BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Valor de saque inválido.");
            return;
        }

        ClienteModel cliente = clienteDao.findById(clienteId);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        if (cliente.getSaldo().compareTo(valor) < 0) {
            System.out.println("Saldo insuficiente para realizar o saque.");
            return;
        }

        // Chama o método de saque no DAO
        clienteDao.sacar(clienteId, valor);
    }

    // Método para depositar em um cliente
    public void depositar(Long clienteId, BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Valor de depósito inválido.");
            return;
        }

        ClienteModel cliente = clienteDao.findById(clienteId);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        // Chama o método de depósito no DAO
        clienteDao.depositar(clienteId, valor);
    }

    // Método para realizar transferência entre clientes
    public void transferir(Long idTransferidor, String chavePixRecebedor, BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Valor de transferência inválido.");
            return;
        }

        // Chama o método de transferência no DAO
        clienteDao.transferir(idTransferidor, chavePixRecebedor, valor);
    }

    // Método para alterar a chave PIX de um cliente
    public void alterarChavePix(Long clienteId, String novaChavePix) {
        if (novaChavePix == null || novaChavePix.isEmpty()) {
            System.out.println("Chave PIX não pode ser vazia.");
            return;
        }

        ClienteModel cliente = clienteDao.findById(clienteId);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        // Chama o método de alteração da chave PIX no DAO
        clienteDao.alterarChavePix(clienteId, novaChavePix);
    }
}
