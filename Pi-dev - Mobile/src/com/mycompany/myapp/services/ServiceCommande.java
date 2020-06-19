/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.commande;

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
public class ServiceCommande {
     private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<commande> commandes;
     public ServiceCommande() {
        request = DataSource.getInstance().getRequest();
    }
     
     
     
        public boolean addcommande(commande commande) {
     
        String url = Statics.BASE_URL + "api/AddCommande?idU=" + commande.getId_U()+"&idM="+commande.getId_Menu();

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
        
        
      
        
        
        
          public boolean Deletecommande(int id) {
     
        String url = Statics.BASE_URL + "api/commande?id=" + id;

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
          
    public ArrayList<commande> getAllcommandes() {
        String url = Statics.BASE_URL + "api/AllCommande";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parsecommande(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return commandes;
    }

    public ArrayList<commande> parsecommande(String jsonText) {
        
        try {
            commandes = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
       
            
            
            
            
           for (Map<String, Object> obj :  list) 
            {           
             int id = (int)Float.parseFloat(obj.get("id").toString()); 
           
           
            Map map_menu = ((Map) obj.get("menu")) ;
            
             
   
            Double id_plat_nom = (Double) map_menu.get("id");

   Map user = ((Map) obj.get("user")) ;
   
                   Double id_u = (Double) user.get("id"); 
              
            
            commandes.add(new commande(id, id_u.intValue(), id_plat_nom.intValue()));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return commandes;
    }
    
    
     
    
    
    
    
}
