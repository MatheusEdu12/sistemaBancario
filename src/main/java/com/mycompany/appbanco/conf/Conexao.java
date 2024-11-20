/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbanco.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author matheus
 */
public class Conexao {
    
    private Connection conn;
        
    public Conexao() {        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aula_java", "root", "root"); // Ajeitar para o meu banco

            System.out.println("Conexão criada com sucesso!");
        }
        catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Falha na conexão");
            e.printStackTrace();
        }
    }
    
    public Connection getConn(){
        return conn;
    }
    
    public static void main(String[] args) {
        Conexao c = new Conexao();
    }
}
