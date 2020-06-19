/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.plat;
import com.mycompany.myapp.services.Serviceplat;
import java.io.IOException;


/**
 *
 * @author aymen
 */
public class PlatsForm extends Form{
     EncodedImage enc;
   Form prev;
    public PlatsForm(Form Back) {
           super("Plats", new BoxLayout(BoxLayout.Y_AXIS));
           prev=Back;
          this.getToolbar().addCommandToLeftBar("back", null, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
                new MenuForm(prev).showBack();
         }
     });
        this.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Plats", "Title")
                )
        );
           
     
        this.add(new InfiniteProgress());
Display.getInstance().scheduleBackgroundTask(()-> {
    // this will take a while...

    Display.getInstance().callSerially(() -> {
        this.removeAll();
    
        for(plat c : new Serviceplat().getAllPlats()) {
      

 
            
            this.add(addItem_plat(c));
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
     
        
        
        
        
        
                this.getToolbar().addCommandToOverflowMenu("Add plat", null, ev -> {

            Form ajout = new Form("Add plat", BoxLayout.y());

            TextField nom_plat = new TextField("", "nom plat", 20, TextArea.ANY);
           
     

            ajout.add("nom plat : ").add(nom_plat);
          
  Validator val_nom_plat = new Validator();

            val_nom_plat.addConstraint(nom_plat, new LengthConstraint(8));

            String text_saisir_des_caracteres = "^[0-9]+$";

            val_nom_plat.addConstraint(nom_plat, new RegexConstraint(text_saisir_des_caracteres, ""));

                Validator val_Prix = new Validator();
  ajout.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
 Button submit = new Button("Submit");
    ajout.add(submit);
    
                            submit.addActionListener(lll
                                    -> {

                                           if (nom_plat.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de nom_plat ", "OK", null);

                                } else if (val_nom_plat.isValid()) {
                                    Dialog.show("Erreur nom_plat !", "il faut saisir des caracteres  !", "OK", null);
                                } 
                                           else
                                {
                                   plat p =new plat(0, nom_plat.getText());
                                  new Serviceplat().addplat(p);
                                  
                                  new PlatsForm(prev).show();
                                }
                                
                                
                            

                            }
                            );
       



            ajout.show();

        });
        
        
        
        
        
        
        
        
        
    }
    
        public MultiButton addItem_plat(plat c)
    {
          MultiButton m = new MultiButton();
                    m.setTextLine1(c.getNomplat());
         try {
             enc = EncodedImage.create("/log.png");
         } catch (IOException ex) {
          
         }
                    String url="http://localhost/kidoPI-DEV/web/images/";
         Image imgs;

         
                          imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE_TO_FILL);
                    m.setEmblem(imgs);
                      m.addActionListener(l-> 
                    {

            Form f2 = new Form("Details", BoxLayout.y());
            Label nom = new Label(c.getNomplat());
            


           Button Modifier = new Button("Modifier");
            Button Supprimer = new Button("Supprimer");
       

            f2.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
                this.showBack();
            });
            f2.add("nom plat : ").add(nom).add(Modifier).add(Supprimer);
   Supprimer.addActionListener(ev
                    -> {
                new Serviceplat().Deleteplat(c.getId());
                new PlatsForm(prev).show();
            }
            );
               Modifier.addActionListener(eva
                    -> {
                Form fmodifier = new Form("Edit plat", BoxLayout.y());

                AutoCompleteTextField nom_plat = new AutoCompleteTextField(c.getNomplat());
                nom_plat.setMinimumElementsShownInPopup(1);

       

              
                fmodifier.add("Nom plat : ").add(nom_plat);
       
  Validator val_nom_plat = new Validator();

            val_nom_plat.addConstraint(nom_plat, new LengthConstraint(8));

            String text_saisir_des_caracteres = "^[0-9]+$";

            val_nom_plat.addConstraint(nom_plat, new RegexConstraint(text_saisir_des_caracteres, ""));
                                Button submit = new Button("Submit");
                            

                            

                                fmodifier.add(submit);

                                fmodifier.revalidate();
                                fmodifier.getToolbar().addCommandToLeftBar("Return", null, (evtx) -> {
                                    this.showBack();
                                });

                                submit.addActionListener(lll
                                        -> {
                                       if (nom_plat.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de nom_plat ", "OK", null);

                                } else if (val_nom_plat.isValid()) {
                                    Dialog.show("Erreur nom_plat !", "il faut saisir des caracteres  !", "OK", null);
                                }  
                                       else
                                {
                                  plat p =new plat();
                                  p.setNomplat(nom_plat.getText());
                                  new Serviceplat().Modifierplat(p, c.getId());
                                  
                                   new PlatsForm(prev).show();
                                }
            

                });

              fmodifier.show();
            }
            );
            f2.show();

        }
                      );
                    
                    return m;
    }
}
