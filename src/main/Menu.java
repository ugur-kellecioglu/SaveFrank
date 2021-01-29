
package main;


import java.awt.Color;
import static main.Controller.control;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class Menu extends Controller {


    private PlainJButton animalsBtn;
    private PlainJButton fruitsBtn;
    private PlainJButton bodyBtn;
    private javax.swing.JTextField usernameText;
    
    private javax.swing.JLabel bestscore;
    public Menu() {
        
        initComponents();
        setLocationRelativeTo(null);
        animalsBtn = new PlainJButton();
        fruitsBtn = new PlainJButton();
        bodyBtn = new PlainJButton();
        usernameText = new javax.swing.JTextField();
        bestscore  = new javax.swing.JLabel();
        setTitle("Save Frank Menu");
        //username
        getContentPane().add(usernameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, 190, -1));
        getContentPane().setComponentZOrder(usernameText, 0);
        
        bestscore.setFont(new Font("Serif", Font.PLAIN, 34));
        bestscore.setForeground(Color.WHITE);
        bestscore.setText("Best Score: " + control.db.getBestScore());
        getContentPane().add(bestscore, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 400, -1));
        getContentPane().setComponentZOrder(bestscore, 0);
        
        //body section
        System.getProperty("user.dir");
        bodyBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/body.png"))); // NOI18N
        bodyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control.setCategory("bodyparts"); 
                control.formolustur2();
                control.formone.setVisible(true);
                control.formzero.setVisible(false);
                control.setUsername(usernameText.getText());
                
                
            }
        });
        getContentPane().add(bodyBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, -1, -1));
        
        //animal section
        animalsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/animals.png"))); // NOI18N
        animalsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                 control.setCategory("animals");
                 control.formolustur2();
                 control.formone.setVisible(true);
                 control.formzero.setVisible(false);
                 control.setUsername(usernameText.getText());
            }
        });
        getContentPane().add(animalsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, -1, -1));

        
        //fruit section
        fruitsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/fruits.png"))); // NOI18N
        
        fruitsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                  control.setCategory("fruits");
                  control.formolustur2();
                  control.formone.setVisible(true);
                  control.formzero.setVisible(false);
                  control.setUsername(usernameText.getText());

            }
        });
        getContentPane().add(fruitsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, -1, -1));
        
        getContentPane().setComponentZOrder(animalsBtn, 0);
        getContentPane().setComponentZOrder(fruitsBtn, 0);
        getContentPane().setComponentZOrder(bodyBtn, 0);
        
        animalsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fruitsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bodyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
      
      
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/menubg.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // creates menu and set it visible
                control = new Controller();
                control.formolustur();
                control.formzero.setVisible(true);

            }
        });
    }

    public void setUsernameText(String usernameText) {
        this.usernameText.setText(usernameText); 
    }

    
    
    public void setbestscore(String usernameText) {
        this.bestscore.setText(usernameText); 
    }

 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
