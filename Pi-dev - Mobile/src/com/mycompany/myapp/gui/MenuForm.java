/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.menu;
import com.mycompany.myapp.entities.plat;

import com.mycompany.myapp.services.ServiceMenu;
import com.mycompany.myapp.services.Serviceplat;

import java.io.IOException;


/**
 *
 * @author aymen
 */
public class MenuForm extends Form {
     EncodedImage enc;
   Form prev;
    public MenuForm(Form Back) {
           super("Menu ", new BoxLayout(BoxLayout.Y_AXIS));
           prev=Back;
     
           this.getToolbar().addCommandToOverflowMenu("commande",null,ev-> 
           {
               new Commande_AdminForm(this).show();
           }
           
           );
          
          this.getToolbar().addCommandToOverflowMenu("plat",null,ev-> 
           {
               new PlatsForm(this).show();
           }
           
           );
          
        this.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Menu", "Title")
                )
        );
           
     
        this.add(new InfiniteProgress());
Display.getInstance().scheduleBackgroundTask(()-> {
    // this will take a while...

    Display.getInstance().callSerially(() -> {
        this.removeAll();
    
        for(menu c : new ServiceMenu().getAllMenu()) {
      
                  for(plat x : new Serviceplat().getAllPlats()) {
      

 if (x.getId()==c.getId_plat_nom())
 {
     System.out.println(x.getId()+"  "+c.getId_plat_nom());
        this.add(addItem_menu(c,x.getNomplat()));
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
        
        
        
        
        
                this.getToolbar().addCommandToOverflowMenu("Add Menu", null, ev -> {

            Form ajout = new Form("Add Menu", BoxLayout.y());

            TextField Boisson = new TextField("", "Boisson", 20, TextArea.ANY);
            TextField desert = new TextField("", "desert", 20, TextArea.ANY);
            TextField supplement = new TextField("", "supplement", 20, TextArea.ANY);
        
            TextField Prix = new TextField("", "Prix", 20, TextArea.NUMERIC);
            ComboBox cmb_nom_plat = new ComboBox();
        
            for (plat c : new Serviceplat().getAllPlats()) {

               
                    cmb_nom_plat.addItem(c.getNomplat());
                

            }
         

            ajout.add("Boisson : ").add(Boisson);
            ajout.add("desert : ").add(desert);
            ajout.add("supplement : ").add(supplement);
            ajout.add("Prix : ").add(Prix);
            ajout.add("nom_plat : ").add(cmb_nom_plat);
     
  Validator val_Boisson = new Validator();

            val_Boisson.addConstraint(Boisson, new LengthConstraint(8));

            String text_saisir_des_caracteres = "^[0-9]+$";

            val_Boisson.addConstraint(Boisson, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_desert = new Validator();

            val_desert.addConstraint(desert, new LengthConstraint(8));

            val_desert.addConstraint(desert, new RegexConstraint(text_saisir_des_caracteres, ""));

          
                Validator val_supplement = new Validator();

            val_supplement.addConstraint(supplement, new LengthConstraint(8));

            val_supplement.addConstraint(supplement, new RegexConstraint(text_saisir_des_caracteres, ""));

            
            
            
            
            
            
            Validator val_Prix = new Validator();

            val_Prix.addConstraint(Prix, new LengthConstraint(8));

            val_Prix.addConstraint(Prix, new RegexConstraint(text_saisir_des_caracteres, ""));

       

            ajout.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });

            ajout.addComponent(new SpanLabel("Drag your photo here"));
            if (DropTarget.isSupported()) {

                DropTarget dnd = DropTarget.create((evt) -> {

                    String srcFile = (String) evt.getSource();
                    System.out.println("Src file is " + srcFile);

                    System.out.println("Location: " + evt.getX() + ", " + evt.getY());
                    if (srcFile != null) {
                        try {

                            Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile)).scaled(300, 300);
                            Button submit = new Button("Submit");
                            String nomImage = srcFile.substring(19, srcFile.length());

                            ajout.add(img);

                            ajout.add(submit);

                            ajout.revalidate();

                            submit.addActionListener(lll
                                    -> {

                                   if (Boisson.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Boisson ", "OK", null);

                                } else if (val_Boisson.isValid()) {
                                    Dialog.show("Erreur Boisson !", "il faut saisir des caracteres  !", "OK", null);
                                } else if (desert.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de desert ", "OK", null);

                                } else if (val_desert.isValid()) {
                                    Dialog.show("Erreur desert !", "il faut saisir des caracteres  !", "OK", null);
                                } 
                                else if (supplement.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de supplement ", "OK", null);

                                } else if (val_supplement.isValid()) {
                                    Dialog.show("Erreur supplement !", "il faut saisir des caracteres  !", "OK", null);
                                } 
                                
                                
                                
                                
                                
                                
                                
                                else if (Prix.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Prix ", "OK", null);

                                } else if (!val_Prix.isValid()) {
                                    Dialog.show("Erreur Prix !", "il faut saisir des numbers", "OK", null);

                                } else if (Integer.valueOf(Prix.getText()) <= 0) {
                                    Dialog.show("Erreur Prix !", "Prix n'est pas acceptable", "OK", null);

                                } 
                                           else
                                {
                                    int id=0;
                                       for (plat c : new Serviceplat().getAllPlats()) {
if (cmb_nom_plat.getSelectedItem().toString().equals(c.getNomplat()))
{
    id=c.getId();
    
}
               
          
                

            }
         
                                    menu m =new menu(Boisson.getText(), desert.getText(), supplement.getText(), Integer.valueOf(Prix.getText()), id, nomImage);
                                    
                                     new ServiceMenu().addmenu(m);
                                new MenuForm(prev).show();   
                                }
                                
                                      
                                
                                
                            

                            }
                            );

                        } catch (IOException ex) {
                            Log.e(ex);
                        }

                    }

                }, Display.GALLERY_IMAGE);
            }
         

            ajout.show();

        });
        
        
        
        
        
        
        
        
        
        
    }
      public MultiButton addItem_menu(menu c,String nom)
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
            
          
     
           
            


           Button Modifier = new Button("Modifier");
            Button Supprimer = new Button("Supprimer");
       

            f2.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
                this.showBack();
            });
            f2.add(imgv2).add("nom plat : ").add(nom).add("Boisson :").add(c.getBoisson()).add("Desert :").add(c.getDessert()).add("Supplement :").add(c.getSupplement()).add("prix :").add(String.valueOf(c.getPrix())).add(Modifier).add(Supprimer);
   Supprimer.addActionListener(ev
                    -> {
       try
       {
                 new ServiceMenu().Deletemenu(c.getId());
                new MenuForm(prev).show(); 
       }
       catch (Exception e)
       {
           Dialog.show("Erreur", "il faut d'abord supprimer de COmmande  ", "OK", null);
       }
         
            }
            );
               Modifier.addActionListener(eva
                    -> {
                Form fmodifier = new Form("Edit Menu", BoxLayout.y());

                AutoCompleteTextField Boisson = new AutoCompleteTextField(c.getBoisson());
                Boisson.setMinimumElementsShownInPopup(1);
                                AutoCompleteTextField desert = new AutoCompleteTextField(c.getDessert());
                desert.setMinimumElementsShownInPopup(1);
                   AutoCompleteTextField supplement = new AutoCompleteTextField(c.getSupplement());
                supplement.setMinimumElementsShownInPopup(1);
    AutoCompleteTextField Prix = new AutoCompleteTextField(String.valueOf(c.getPrix()));
                Prix.setMinimumElementsShownInPopup(1);
           
          ComboBox cmb_nom_plat = new ComboBox();
        
            for (plat cq : new Serviceplat().getAllPlats()) {

               
                    cmb_nom_plat.addItem(cq.getNomplat());
                

            }
 fmodifier.add("Boisson : ").add(Boisson);
            fmodifier.add("desert : ").add(desert);
            fmodifier.add("supplement : ").add(supplement);
            fmodifier.add("Prix : ").add(Prix);
            fmodifier.add("nom_plat : ").add(cmb_nom_plat);
     
              
       
       
  Validator val_Boisson = new Validator();

            val_Boisson.addConstraint(Boisson, new LengthConstraint(8));

            String text_saisir_des_caracteres = "^[0-9]+$";

            val_Boisson.addConstraint(Boisson, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_desert = new Validator();

            val_desert.addConstraint(desert, new LengthConstraint(8));

            val_desert.addConstraint(desert, new RegexConstraint(text_saisir_des_caracteres, ""));

          
                Validator val_supplement = new Validator();

            val_supplement.addConstraint(supplement, new LengthConstraint(8));

            val_supplement.addConstraint(supplement, new RegexConstraint(text_saisir_des_caracteres, ""));

            
            
            
            
            
            
            Validator val_Prix = new Validator();

            val_Prix.addConstraint(Prix, new LengthConstraint(8));

            val_Prix.addConstraint(Prix, new RegexConstraint(text_saisir_des_caracteres, ""));
    fmodifier.addComponent(new SpanLabel("Drag your photo here"));
       
            if (DropTarget.isSupported()) {

                DropTarget dnd = DropTarget.create((evt) -> {

                    String srcFile = (String) evt.getSource();
                    System.out.println("Src file is " + srcFile);

                    System.out.println("Location: " + evt.getX() + ", " + evt.getY());
                    if (srcFile != null) {
                        try {

                            Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile)).scaled(300, 300);
                            Button submit = new Button("Submit");
                            String nomImage = srcFile.substring(19, srcFile.length());

                            fmodifier.add(img);

                            fmodifier.add(submit);

                            fmodifier.revalidate();

                            submit.addActionListener(lll
                                    -> {

                                   if (Boisson.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Boisson ", "OK", null);

                                } else if (val_Boisson.isValid()) {
                                    Dialog.show("Erreur Boisson !", "il faut saisir des caracteres  !", "OK", null);
                                } else if (desert.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de desert ", "OK", null);

                                } else if (val_desert.isValid()) {
                                    Dialog.show("Erreur desert !", "il faut saisir des caracteres  !", "OK", null);
                                } 
                                else if (supplement.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de supplement ", "OK", null);

                                } else if (val_supplement.isValid()) {
                                    Dialog.show("Erreur supplement !", "il faut saisir des caracteres  !", "OK", null);
                                } 
                                
                                
                                
                                
                                
                                
                                
                                else if (Prix.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Prix ", "OK", null);

                                } else if (!val_Prix.isValid()) {
                                    Dialog.show("Erreur Prix !", "il faut saisir des numbers", "OK", null);

                                } else if (Integer.valueOf(Prix.getText()) <= 0) {
                                    Dialog.show("Erreur Prix !", "Prix n'est pas acceptable", "OK", null);

                                } 
                                           else
                                {
                                    int id=0;
                                       for (plat cq : new Serviceplat().getAllPlats()) {
if (cmb_nom_plat.getSelectedItem().toString().equals(cq.getNomplat()))
{
    id=cq.getId();
    
}
               
          
                

            }
         
                                    menu mq =new menu(Boisson.getText(), desert.getText(), supplement.getText(), Integer.valueOf(Prix.getText()), id, nomImage);
                                    
                                     new ServiceMenu().Modifiermenu(mq,c.getId());
                                new MenuForm(prev).show();   
                                }
                                
                                      
                                
                                
                            

                            }
                            );

                        } catch (IOException ex) {
                            Log.e(ex);
                        }

                    }

                }, Display.GALLERY_IMAGE);
            }
       
                          

                

              fmodifier.show();
            }
            );
            f2.show();

        }
                      );
                    
                    return m;
    }

}
