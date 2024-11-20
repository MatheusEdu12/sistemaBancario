package com.mycompany.appbanco.service;

import com.mycompany.appbanco.dao.ClienteDao;
import com.mycompany.appbanco.model.Clientes;
import com.mycompany.appbanco.view.PaginaPrincipal;
import java.math.BigDecimal;
import java.util.Objects;
import javax.swing.JOptionPane;

public class ClienteService {

    private ClienteDao clienteDao;

    // Construtor que inicializa o ClienteDao
    public ClienteService() {
        clienteDao = new ClienteDao();
    }
    
    public Clientes logar(String cpf, String senha) {
        
        Clientes cliente = clienteDao.login(cpf, senha);  
        if (!Objects.equals(cliente, null)) {
            return cliente;
            
        }
        JOptionPane.showMessageDialog(null, "CPF ou Senha não localizados..");
        return null;
    }

    // Método para inserir um cliente
    public boolean inserirCliente(Clientes cliente) {
        if (cliente.getNome().isEmpty() || cliente.getCpf().isEmpty() || cliente.getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Dados inválidos");
            return false;
        }
        
        // Chama o método de inserção no DAO
        clienteDao.inserir(cliente);
        JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso!");
        return true;
    }

    // Método para deletar um cliente
    public void deletarCliente(Long clienteId) {
        Clientes cliente = clienteDao.findById(clienteId);
        if (cliente.getNome().isEmpty() || cliente.getCpf().isEmpty() || cliente.getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cliente não localizado.");
            return;
        }

        // Chama o método de exclusão no DAO
        clienteDao.deletar(cliente);
        JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!");
    }

    // Método para sacar de um cliente
    public void sacar(Long clienteId, BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(null, "Valor de saque inválido.");
            return;
        }

        Clientes cliente = clienteDao.findById(clienteId);
        if (cliente.getNome().isEmpty() || cliente.getCpf().isEmpty() || cliente.getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cliente não localizado.");
            return;
        }

        if (cliente.getSaldo().compareTo(valor) < 0) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar o saque.");
            return;
        }

        // Chama o método de saque no DAO
        clienteDao.sacar(clienteId, valor);
        JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
    }

    // Método para depositar em um cliente
    public void depositar(Long clienteId, BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(null, "Valor de depósito inválido.");
            return;
        }

        Clientes cliente = clienteDao.findById(clienteId);
        if (cliente.getNome().isEmpty() || cliente.getCpf().isEmpty() || cliente.getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cliente não localizado.");
            return;
        }

        // Chama o método de depósito no DAO
        clienteDao.depositar(clienteId, valor);
        JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
    }

    // Método para realizar transferência entre clientes
    public void transferir(Long idTransferidor, String chavePixRecebedor, BigDecimal valor) {
        Clientes cliente = clienteDao.findById(idTransferidor);

        if (cliente.getSaldo().compareTo(valor) < 0) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar a transferência.");
            return;
        }
        
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(null, "Valor de transferência inválido.");
            return;
        }

        // Chama o método de transferência no DAO
        clienteDao.transferir(idTransferidor, chavePixRecebedor, valor);
        JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
    }

    // Método para alterar a chave PIX de um cliente
    public void alterarChavePix(Long clienteId, String novaChavePix) {
        if (novaChavePix == null || novaChavePix.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Chave pix não pode ser vazia.");
            return;
        }

        Clientes cliente = clienteDao.findById(clienteId);
        if (cliente.getNome().isEmpty() || cliente.getCpf().isEmpty() || cliente.getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cliente não localizado.");
            return;
        }

        // Chama o método de alteração da chave PIX no DAO
        clienteDao.alterarChavePix(clienteId, novaChavePix);
        JOptionPane.showMessageDialog(null, "Sua chave pix foi alterada com sucesso!");
    }

    public Clientes findById(Long id) {
        return clienteDao.findById(id);
    }
}
