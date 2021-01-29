
package main;


import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.*;
public class Main extends Controller {


    public Main() {
        play();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1024, 768));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                control = new Controller();
                control.formolustur2();
                control.formone.setVisible(true);
            }
        });
    }
    
    public void play(){
        
        //each turn form should be draw again
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();

        initComponents();
        
        setTitle("Save Frank Main Game");
        //score text
        scoreLbl = new JLabel();
        scoreLbl.setFont(addFont(26));
        scoreLbl.setText("Score: ");
        mainPanel.add(scoreLbl, addItem(20, 20, -1, -1));
        
        //actual score counter
        scoreCount = new JLabel();
        scoreCount.setFont(addFont(26));
        scoreCount.setText(String.valueOf(control.getScore()));
        mainPanel.add(scoreCount, addItem(100, 22, -1, -1));
        
        //heart icon that is at left upper corner
        lifeImage = new JLabel();
        lifeImage.setIcon(addIcon("heart"));
        mainPanel.add(lifeImage, addItem(20, 60, -1, -1));
        
        //life count
        lifeCount = new JLabel();
        lifeCount.setText(String.valueOf(control.getLife()));
        lifeCount.setFont(addFont(30));
        mainPanel.add(lifeCount, addItem(100, 65, -1, -1));
        
        // buttons that will be replaced at right top corner
        Alphabeth alphabeth = new Alphabeth();
        setButtons(alphabeth.create_butons());
        for(JButton button : getButtons()){
          mainPanel.add(button, addItem(button.getX(), button.getY(), button.getHeight(), button.getWidth()));
        }
        
        
        ///body parts 
        initializeBodyParts();
        for(JLabel bodyPart: control.getBodyparts()){
            mainPanel.add(bodyPart, addItem(bodyPart.getX(), bodyPart.getY(), -1, -1));
            bodyPart.setVisible(false);
        }
        
        
        //question marks that will be replaced at bottom left corner and definition
        Word word = new Word(control.getCategory());
        control.setWord(word);
        
        //word definition
        wordDefinition = new JLabel();
        wordDefinition.setBounds(80,630,-1,-1);
        wordDefinition.setFont(addFont(32));
        wordDefinition.setForeground(Color.white);
        wordDefinition.setText("<html><p style=\"width:600px\">"+word.getDefinition()+"</p></html>");
        
        mainPanel.add(wordDefinition, addItem(wordDefinition.getX(), wordDefinition.getY(), wordDefinition.getHeight(), wordDefinition.getWidth()));
        
        //panele question marks
        int wordLength = word.getWord().length();
        initializeQuestionMarkImages(wordLength);
        for(JButton questionMark : control.getQuestionMarks()){
            mainPanel.add(questionMark, addItem(questionMark.getX(), questionMark.getY(), -1, -1));
            
        }
        
        initializeQuestionMarkLetter(wordLength);
        for(JLabel questionMarkLetter : control.getQuestionMarkLetters()){
            mainPanel.add(questionMarkLetter, addItem(questionMarkLetter.getX(), questionMarkLetter.getY(), -1, -1));
            
        }

        //adding background image
        JLabel backgroundimage = new JLabel();
        backgroundimage.setIcon(addIcon("bgMain"));
        mainPanel.add(backgroundimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        
    }
    
    // It returns a Icon. This way easier to add an image to labels.
    private Icon addIcon(String name){ 
        String st = "/assets/" + name + ".png";
        ImageIcon i = new ImageIcon(getClass().getResource(st));
        return i;
    }
    
    // to make adding items to panel easier
    private org.netbeans.lib.awtextra.AbsoluteConstraints addItem(int x, int y, int height, int width){
        return new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, height, width);
    }
    
    //For using a general font
    private Font addFont(int size){
        return new Font("Arial", Font.PLAIN, size);
    }
    
    
    private void initializeBodyParts(){
        control.setBodyparts(new ArrayList<JLabel>()); 
        headImage = new JLabel();
        headImage.setIcon(addIcon("head"));
        headImage.setBounds(142 , 220, -1, -1);
        
        chestImage = new JLabel();
        chestImage.setIcon(addIcon("chest"));
        chestImage.setBounds(170 , 310, -1, -1);
        
        leftArmImage = new JLabel();
        leftArmImage.setIcon(addIcon("leftarm"));
        leftArmImage.setBounds(153 , 324, -1, -1);

        rightArmImage = new JLabel();
        rightArmImage.setIcon(addIcon("rightarm"));
        rightArmImage.setBounds(242 , 324, -1, -1);
        
        leftLegImage = new JLabel();
        leftLegImage.setIcon(addIcon("leftleg"));
        leftLegImage.setBounds(185 , 368, -1, -1);
        
        rightLegImage = new JLabel();
        rightLegImage.setIcon(addIcon("rightleg"));
        rightLegImage.setBounds(215 , 368, -1, -1);
        
        control.getBodyparts().add(headImage);
        control.getBodyparts().add(chestImage);
        control.getBodyparts().add(leftArmImage);
        control.getBodyparts().add(rightArmImage);
        control.getBodyparts().add(leftLegImage);
        control.getBodyparts().add(rightLegImage);
    }
    
    private void initializeQuestionMarkImages(int wordLength){
        control.setQuestionMarks(new ArrayList<JButton>());
        for(int i = 0 ; i < wordLength; i++){
            questionMarkImage = new PlainJButton();
            questionMarkImage.setIcon(addIcon("questionmark45x50"));
            questionMarkImage.setBounds(i*60+100,550, 100, 100);
            questionMarkImage.setSize(100, 80);
            control.getQuestionMarks().add(questionMarkImage);
        }
    }
    private void initializeQuestionMarkLetter(int wordLength){
        control.setQuestionMarkLetters(new ArrayList<JLabel>());
        for(int i = 0 ; i < wordLength; i++){
            questionMarkLetter = new JLabel();
            questionMarkLetter.setBounds(i*60+100,550, 100, 100);
            questionMarkLetter.setSize(100, 80);
            questionMarkLetter.setFont(addFont(44));
            questionMarkLetter.setVisible(false);
            questionMarkLetter.setForeground(Color.white);
            questionMarkLetter.setText(Character.toString(control.getWord().getWord().charAt(i)).toUpperCase(Locale.ENGLISH));
            control.getQuestionMarkLetters().add(questionMarkLetter);
        }
    }
    
    //declarations
    private JLabel lifeImage;
    JLabel lifeCount;
    private JLabel scoreLbl;
    private JLabel scoreCount;
    private JLabel headImage;
    private JLabel chestImage;
    private JLabel leftArmImage;
    private JLabel rightArmImage;
    private JLabel leftLegImage;
    private JLabel rightLegImage;
    private JButton questionMarkImage;
    private JLabel questionMarkLetter;
    private JLabel wordDefinition;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
