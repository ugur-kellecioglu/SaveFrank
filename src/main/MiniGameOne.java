
package main;

import static main.Controller.control;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class MiniGameOne extends Controller {

    ArrayList<Integer> numbers = new ArrayList<Integer>();
    public javax.swing.JButton btn;
    ArrayList<Integer> useranswer = new ArrayList<Integer>();
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    


    public MiniGameOne() {
    
        initComponents();
        
        setTitle("Save Frank Mini Game");
        setLocationRelativeTo(null);
        
    }
    
    public void add_random_numbers(){

        int a = 1;
        int b = 1;
        for(int i = 0; i < 12; i++){
            numbers.add((int) (Math.random()*100) + 1);
            btn = new javax.swing.JButton();
            
            btn.setText(numbers.get(i).toString());

           
           
            if(i%4 == 0){
                a = 1;
                b++;
         }
            a++;
            btn.setBounds(a*100+100, 80*b + 150, 100, 100);
            btn.setSize(100, 60);
            btn.setFont(new Font("Arial", Font.PLAIN, 32));
            buttons.add(btn);
           
        }
        
        //cevap
        Collections.sort(numbers);
          
        
    }
    public void addclick(){
        
        for(final JButton btn : buttons){
            
            btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
 
               useranswer.add(Integer.parseInt(btn.getText()));
               btn.setVisible(false);
               
               if(useranswer.size() == numbers.size()){
                   for(int i = 0 ; i < numbers.size(); i++){
                       if(numbers.get(i) != useranswer.get(i)){
                           JOptionPane.showMessageDialog(null, "Kaybettiniz");
                           control.formzero.setVisible(true);
                           control.db.save("INSERT INTO scoretable (username, score) VALUES('"+control.getUsername()+"' , '"+control.getScore()+"')");
                           control.formzero.setbestscore("Best Score: " + control.db.getBestScore());
                           control.setScore(0);
                           control.setCountbodyparts(0);
                           control.setLife(3);
                           control.formtwo.setVisible(false);
                           control.setMinigamecheck(false);
                           break;
                       }
                   }
           
                if(control.isMinigamecheck()){
                      control.getBodyparts().get(control.getBodyparts().size()-1).setVisible(false);
                      control.setCountbodyparts(control.getCountbodyparts() - 1); 
                      control.formone.setVisible(true);
                      control.formtwo.setVisible(false);
                      control.formone.lifeCount.setText(String.valueOf(control.getLife()));
                      JOptionPane.showMessageDialog(null, "You win");
                      
                          
                  }
               }
            }
            });
        }
        
        
    }
    public void print_buttons(){
        numbers = new ArrayList<Integer>();
        buttons = new ArrayList<JButton>();
        useranswer = new ArrayList<Integer>();
        add_random_numbers();
        addclick();
        for (JButton btn : buttons){
            getContentPane().add(btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(btn.getX(), btn.getY(), -1, -1));
            getContentPane().setComponentZOrder(btn, 0);
            
        }
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/MinigameOne.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(2, 2, 2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
    public static void main(String args[]) {
  

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                control.formtwo.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
