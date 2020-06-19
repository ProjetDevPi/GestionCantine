/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.commande;
import com.mycompany.myapp.entities.menu;
import com.mycompany.myapp.entities.plat;
import com.mycompany.myapp.services.ServiceCommande;
import com.mycompany.myapp.services.ServiceMenu;
import com.mycompany.myapp.services.Serviceplat;
import java.io.IOException;

/**
 *
 * @author aymen
 */
public class Commande_user extends Form{
    EncodedImage enc;
   Form prev;
    public Commande_user(Form Back) {
         super("Menu ", new BoxLayout(BoxLayout.Y_AXIS));
           prev=Back;
          this.getToolbar().addCommandToLeftBar("back", null, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
                new Interface_user_menu(prev).show();
         }
     });
        this.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Commandes user", "Title")
                )
        );
                this.add(new InfiniteProgress());
Display.getInstance().scheduleBackgroundTask(()-> {
    // this will take a while...

    Display.getInstance().callSerially(() -> {
        this.removeAll();
    
        for(commande c : new ServiceCommande().getAllcommandes()) {
            
            System.out.println(c.getId());
            
            
      
                  for(menu x : new ServiceMenu().getAllMenu()) {
      

 if (x.getId() == c.getId_Menu() ) 
 {
     if (c.getId_U() == 12)//12static
     {
          for(plat p : new Serviceplat().getAllPlats()) {
      
              System.out.println("id : "+p.getId()+" "+x.getId_plat_nom());
              System.out.println("nom : "+p.getNomplat());
 if (p.getId() == x.getId_plat_nom())
 {
  
        this.add(addItem_Commande(c,x,p.getNomplat()));
 }
            
            
        }  
     }
     
     
        
 }
            
            
        }
 
            
    
        }
       
        this.revalidate();
    });
});
        
        

 
        
        
        
        this.getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : this.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        this.getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : this.getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        this.getContentPane().animateLayout(150);
    }
}, 4);
           
    }
      public MultiButton addItem_Commande(commande q,menu c,String nom)
    {
        String res="";
          MultiButton m = new MultiButton();
                   
             
                                m.setTextLine1(nom);  
                     m.setTextLine2(String.valueOf(c.getPrix()));
         try {
             enc = EncodedImage.create("/log.png");
         } catch (IOException ex) {
          
         }
                    String url="http://localhost/kidoPI-DEV/web/images/"+c.getNom_image();
                    String url24="http://localhost/kidoPI-DEV/web/images/";
                   
         Image imgs;
Image imgs2;
       
                          imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE_TO_FILL);
                  imgs2= URLImage.createToStorage(enc, url24, url24, URLImage.RESIZE_SCALE_TO_FILL);
                          m.setEmblem(imgs2);
                          
                          m.setIcon(imgs);
                          
                      m.addActionListener(l-> 
                    {

            Form f2 = new Form("Details", BoxLayout.y());
                  String url2 = "http://localhost/kidoPI-DEV/web/images/" + c.getNom_image();
            ImageViewer imgv2;
            Image imge2;
            EncodedImage enc2;
            enc2 = EncodedImage.createFromImage(imgs, false);
            imge2 = URLImage.createToStorage(enc2, url2, url2);
            imgv2 = new ImageViewer(imge2);
            

           
            


          
 
       

            f2.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
                this.showBack();
            });
            f2.add(imgv2).add("nom plat : ").add(nom).add("Boisson :").add(c.getBoisson()).add("Desert :").add(c.getDessert()).add("Supplement :").add(c.getSupplement()).add("prix :").add(String.valueOf(c.getPrix()));


            f2.show();

        }
                      );
                    
                    return m;
    }
    
}
