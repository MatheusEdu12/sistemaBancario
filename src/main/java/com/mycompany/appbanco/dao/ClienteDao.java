/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbanco.dao;

import com.mycompany.appbanco.conf.Conexao;
import com.mycompany.appbanco.model.ClienteModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author matheus
 */
public class ClienteDao {
    
    private Conexao conexao;
    private PreparedStatement ps;
    
    public ClienteDao(){
        conexao = new Conexao();        
    }
    
    public void inserir(ClienteModel cliente){
        try {
            String SQL = "INSERT INTO cliente(nome, cpf, telefone, endereco) " +
                    "VALUES (?, ?, ?, ?)";
            
            ps = conexao.getConn().prepareStatement(SQL);
            
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
//            ps.setString(3, cliente.getTelefone());
//            ps.setString(4, cliente.getEndereco());           
                        
            ps.executeUpdate();
                        
            ps.close(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void excluir(ClienteModel cliente){
        try {
            String SQL = "DELETE FROM cliente WHERE id = ?";
            
            ps = conexao.getConn().prepareStatement(SQL);
            
//            ps.setInt(1, cliente.getId());           
                        
            ps.executeUpdate();
                        
            ps.close(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
    
    
    
}
