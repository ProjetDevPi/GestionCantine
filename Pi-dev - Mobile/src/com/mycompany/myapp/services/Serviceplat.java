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
import com.mycompany.myapp.entities.plat;
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
public class Serviceplat {
        private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<plat> plats;
     public Serviceplat() {
        request = DataSource.getInstance().getRequest();
    }
     
     
     
        public boolean addplat(plat plat) {
     
        String url = Statics.BASE_URL + "api/AddPlat?nomplat=" + plat.getNomplat();

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
        
        
        
           public boolean Modifierplat(plat plat,int id) {
     
        String url = Statics.BASE_URL + "api/UpdatePlat?nomplat=" + plat.getNomplat()+"&id=" +id;

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
        
        
        
        
          public boolean Deleteplat(int id) {
     
        String url = Statics.BASE_URL + "api/DeletePlat?id=" + id;

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
          
    public ArrayList<plat> getAllPlats() {
        String url = Statics.BASE_URL + "api/ListPlat";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                plats = parseplats(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return plats;
    }

    public ArrayList<plat> parseplats(String jsonText) {
        
        try {
            plats = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
       
            
            
            
            
           for (Map<String, Object> obj :  list) 
            {           
             
            String nomplat = obj.get("nomplat").toString(); 
    
            
            int id = (int)Float.parseFloat(obj.get("id").toString());   
            
            plats.add(new plat(id,nomplat));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return plats;
    }
     
}
