/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this temmenue file, choose Tools | Temmenues
 * and open the temmenue in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.menu;
import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aymen
 */
public class ServiceMenu {
      private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<menu> menus;
     public ServiceMenu() {
        request = DataSource.getInstance().getRequest();
    }
     
     
     
        public boolean addmenu(menu menu) {
     
        String url = Statics.BASE_URL + "api/AddMenu?prix=" + menu.getPrix()+"&boisson="+menu.getBoisson()+"&dessert="+menu.getDessert()+"&supplement="+menu.getSupplement()+"&image="+menu.getNom_image()+"&idP="+menu.getId_plat_nom();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
        
        
        
           public boolean Modifiermenu(menu menu,int id) {
     
        String url = Statics.BASE_URL + "api/UpdateMenu?prix=" + menu.getPrix()+"&boisson="+menu.getBoisson()+"&dessert="+menu.getDessert()+"&supplement="+menu.getSupplement()+"&image="+menu.getNom_image()+"&idP="+menu.getId_plat_nom()+"&id=" +id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
        
        
        
        
          public boolean Deletemenu(int id) {
     
        String url = Statics.BASE_URL + "api/DeleteMenu?id=" + id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
          
    public ArrayList<menu> getAllMenu() {
        String url = Statics.BASE_URL + "api/ListMenu";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                menus = parsemenus(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return menus;
    }

    public ArrayList<menu> parsemenus(String jsonText) {
        
        try {
            menus = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
       
            
            
            
            
           for (Map<String, Object> obj :  list) 
            {           
             int id = (int)Float.parseFloat(obj.get("id").toString()); 
            String boisson = obj.get("boisson").toString(); 
             String dessert = obj.get("dessert").toString(); 
             String supplement = obj.get("supplement").toString(); 
                int prix = (int)Float.parseFloat(obj.get("prix").toString()); 
                 String nomImage = obj.get("nomImage").toString(); 
           
            Map map = ((Map) obj.get("nomplat")) ;
   
            Double id_plat_nom = (Double) map.get("id");


   
            
              
            
            menus.add(new menu(id, boisson, dessert, supplement, prix, id_plat_nom.intValue(), nomImage));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return menus;
    }
     public ArrayList<menu> getstatcommandes() {
        String url = Statics.BASE_URL + "api/StatCommande";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                menus = parsestatcommande(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return menus;
    }
   public ArrayList<menu> parsestatcommande(String jsonText) {
        
        try {
            menus = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
       
            
            
            
            
           for (Map<String, Object> obj :  list) 
            {           
             int nbr = (int)Float.parseFloat(obj.get("nbr").toString()); 
            
            String nomplat = obj.get("nomplat").toString(); 
           
menu m =new menu();
m.setId(nbr);
m.setBoisson(nomplat);
              
            
            menus.add(m);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return menus;
    } 
    
}
