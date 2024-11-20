/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.appbanco;

import com.mycompany.appbanco.view.Login;

/**
 *
 * @author matheus
 */
public class AppBanco {

    public static void main(String[] args) {
        
        // Cria e configura o formulário Login
        Login loginForm = new Login(); 
        
        // Define a posição do formulário no centro da tela
        loginForm.setLocationRelativeTo(null); 
        
        // Torna o formulário visível
        loginForm.setVisible(true);
    }
}
