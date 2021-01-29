/**
*
* This class basically creates buttons.
* 
* @author  Uğur Kellecioğlu
* @version 1.0
* @since   2021-01-17
*/
package main;

import java.awt.Font;
import java.util.ArrayList;
import static main.Controller.control;



 public class Alphabeth extends Controller{
    
     private String alphabethString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
     private char [] alphabeth =  new char[26];
     
    public Alphabeth() {
        alphabeth = alphabethString.toCharArray();
    }
    
    public ArrayList<javax.swing.JButton> create_butons(){
        ArrayList<javax.swing.JButton> letters = new ArrayList<javax.swing.JButton>();
        
        int b = 0;
        int a = 1;
        for(int i = 0 ; i < alphabeth.length; i++) {
            final javax.swing.JButton letter = new javax.swing.JButton();
            if(i%5 == 0 && i!= 0){
                a = 1;
                b++;
            }
            a++;
            letter.setBounds(a*72+430, 62*b + 40, 60, 70);
            letter.setFont(new Font("Arial", Font.PLAIN, 28));
            letter.setText(Character.toString(alphabeth[i]).toUpperCase());
            letters.add(letter);
            letter.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               do_move(control.getButtons(), letter.getText().toLowerCase(getLocale()));
               letter.setEnabled(false);
               control.logger.info("Oyuncu harfe tıkladı: " + letter.getText());
               
            }
            
            });
   
        }

            return letters;
    
    }
    
    
 }