package com.mycompany.appbanco.dao;

import com.mycompany.appbanco.model.Clientes;
import jakarta.persistence.*;
import java.math.BigDecimal;

public class ClienteDao {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    // Construtor para inicializar EntityManager
    public ClienteDao() {
        emf = Persistence.createEntityManagerFactory("meu-persistence-unit"); // nome definido no persistence.xml
        em = emf.createEntityManager();
    }
    
    public Clientes findById(Long clienteId) {
        try {
            return em.find(Clientes.class, clienteId); // Busca o cliente pelo ID
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna null em caso de erro
    }
}

    
    // Método para inserir um cliente
    public void inserir(Clientes cliente) {
        em.getTransaction().begin();
        em.persist(cliente); // Insere o cliente no banco
        em.getTransaction().commit();
    }
    
    public void deletar(Clientes cliente) {
        em.getTransaction().begin();
        cliente = em.merge(cliente); // Sincroniza o cliente com o banco de dados
        em.remove(cliente); // Remove o cliente do banco
        em.getTransaction().commit();
    }
    
    public void sacar(Long clienteId, BigDecimal valor) {
        Clientes cliente = em.find(Clientes.class, clienteId); // Busca o cliente no banco
        cliente.sacar(valor);  // Chama o método de saque na classe Cliente
        em.getTransaction().begin();
        em.merge(cliente);  // Atualiza o saldo do cliente no banco
        em.getTransaction().commit();
    }
    
    public void depositar(Long clienteId, BigDecimal valor) {
        Clientes cliente = em.find(Clientes.class, clienteId); // Busca o cliente no banco
        cliente.depositar(valor);  // Chama o método de depósito na classe Cliente
        em.getTransaction().begin();
        em.merge(cliente);  // Atualiza o saldo do cliente no banco
        em.getTransaction().commit();
    }
    
    public void transferir(Long idTransferidor, String chavePixRecebedor, BigDecimal valor) {
        // Buscar o cliente transferidor pelo ID
        Clientes clienteTransferidor = em.find(Clientes.class, idTransferidor);

        // Buscar o cliente recebedor pela chave PIX
        Clientes clienteRecebedor = em.createQuery("SELECT c FROM Clientes c WHERE c.chavePix = :chavePix", Clientes.class)
                                        .setParameter("chavePix", chavePixRecebedor)
                                        .getSingleResult();

        // Realizar o saque na conta do transferidor
        clienteTransferidor.sacar(valor);
        // Realizar o depósito na conta do recebedor
        clienteRecebedor.depositar(valor);

        // Iniciar transação para atualizar os saldos no banco
        em.getTransaction().begin();
        em.merge(clienteTransferidor);  // Atualiza a conta do transferidor
        em.merge(clienteRecebedor);     // Atualiza a conta do recebedor
        em.getTransaction().commit();
    }
    
    // Método para alterar a chave PIX de um cliente
    public void alterarChavePix(Long clienteId, String novaChavePix) {
        Clientes cliente = em.find(Clientes.class, clienteId); // Busca o cliente no banco
        cliente.setChavePix(novaChavePix); // Altera a chave PIX do cliente
        em.getTransaction().begin();
        em.merge(cliente);  // Atualiza o cliente no banco
        em.getTransaction().commit();
    }

    public Clientes login(String cpf, String senha) {
        try {
            Clientes cliente = em.createQuery("SELECT c FROM Clientes c WHERE c.cpf = :cpf AND c.senha = :senha", Clientes.class)
                                 .setParameter("cpf", cpf)
                                 .setParameter("senha", senha)
                                 .getSingleResult();
            return cliente;
        } catch (NoResultException e) {
            return null;
        }
    }

    
}
