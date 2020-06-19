/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author aymen
 */
public class commande {
    private int id;
    private int id_U;
    private int id_Menu;

    public commande() {
    }

    public commande(int id, int id_U, int id_Menu) {
        this.id = id;
        this.id_U = id_U;
        this.id_Menu = id_Menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_U() {
        return id_U;
    }

    public void setId_U(int id_U) {
        this.id_U = id_U;
    }

    public int getId_Menu() {
        return id_Menu;
    }

    public void setId_Menu(int id_Menu) {
        this.id_Menu = id_Menu;
    }
    
            
    
}
