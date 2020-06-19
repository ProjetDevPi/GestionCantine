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
public class plat {
    private int id;
    private String nomplat;

    public plat() {
    }

    public int getId() {
        return id;
    }

    public plat(int id, String nomplat) {
        this.id = id;
        this.nomplat = nomplat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomplat() {
        return nomplat;
    }

    public void setNomplat(String nomplat) {
        this.nomplat = nomplat;
    }
    
}
