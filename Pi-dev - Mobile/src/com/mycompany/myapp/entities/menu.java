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
public class menu {
    private int id;
    private String boisson;
    private String dessert;
    private String supplement;
     private int prix;
     private int id_plat_nom;
         private String nom_image;

    public menu() {
    }

    public menu(String boisson, String dessert, String supplement, int prix, int id_plat_nom, String nom_image) {
        this.boisson = boisson;
        this.dessert = dessert;
        this.supplement = supplement;
        this.prix = prix;
        this.id_plat_nom = id_plat_nom;
        this.nom_image = nom_image;
    }

    public menu(int id, String boisson, String dessert, String supplement, int prix, int id_plat_nom, String nom_image) {
        this.id = id;
        this.boisson = boisson;
        this.dessert = dessert;
        this.supplement = supplement;
        this.prix = prix;
        this.id_plat_nom = id_plat_nom;
        this.nom_image = nom_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoisson() {
        return boisson;
    }

    public void setBoisson(String boisson) {
        this.boisson = boisson;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId_plat_nom() {
        return id_plat_nom;
    }

    public void setId_plat_nom(int id_plat_nom) {
        this.id_plat_nom = id_plat_nom;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }
         
}
