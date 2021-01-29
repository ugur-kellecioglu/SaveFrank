/**
*
* This class for creating forms, displaying them, controlling game status either wins or lose
* 
* @author  Uğur Kellecioğlu
* @version 1.0
* @since   2021-01-17
*/
package main;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.JOptionPane;

import javax.swing.*;
import org.apache.log4j.BasicConfigurator;
public class Controller extends javax.swing.JFrame{

    
     Menu formzero;
     Main formone;
     MiniGameOne formtwo;
     static Controller control;

    private Word word = null;
    private ArrayList<JLabel> bodyparts;
    private ArrayList<JButton> buttons;
    private ArrayList<JButton> questionMarks;
    private ArrayList<JLabel> questionMarkLetters;
    private boolean minigamecheck = true;
    private String category = "";
    private int countbodyparts = 0;
    private boolean game = false;
    private int score = 0;
    private boolean check = false;
    private int countletter = 0;
    private int life = 3;
    private boolean minigame_check = false;
    private Locale enLocale = new Locale("en", "US");

    Logger logger;
    
    private SoundController s;
    private SoundController btnSound;
    
    //müzik dosyaları çok boyutlu olduğu için sildik.
    //hata mesajı almamak için yorum satırı olarak bıraktık
    //final String backtogameafterminigame = System.getProperty("user.dir") + "\\sounds\\backtogameafterminigame.wav";
    //final String correctanswer = System.getProperty("user.dir") + "\\sounds\\correctanswer.wav";
    //final String losefail = System.getProperty("user.dir") + "\\sounds\\losefail.wav";
    //final String maingamemusic = System.getProperty("user.dir") + "\\sounds\\maingamemusic.wav";
    //final String mainmenu = System.getProperty("user.dir") + "\\sounds\\mainmenu.wav";
    //final String minigamemusicLOOP = System.getProperty("user.dir") + "\\sounds\\minigamemusicLOOP.wav";
    //final String minigamesfail = System.getProperty("user.dir") + "\\sounds\\minigamesfail.wav";
    //final String click = System.getProperty("user.dir") + "\\sounds\\click.wav";
    
    Database db;
    private String username;
    public void formolustur() {
        control.db = new Database();
        control.logger = Logger.getLogger("logger");
        BasicConfigurator.configure();
        formzero = new Menu();
        /*
            try{
            control.s.filePath = mainmenu;
            control.s = new SoundController();
            control.s.play();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        */
        
    }
    
    public void formolustur2(){
            formone = new Main();
            formtwo = new MiniGameOne();
           /*
                 try {
                control.s.stop();
                control.s.filePath = maingamemusic;
                control.s.resetAudioStream();
                control.s.play();
             } 
            catch (Exception e) {
                e.printStackTrace();
            }
            */
    }
    
    public void do_move(ArrayList<javax.swing.JButton> question_marks, String letter){
        /*
                try {
                control.btnSound.filePath = click;
                control.btnSound = new SoundController();
                control.btnSound.play();
                control.btnSound.clip.stop();
                control.btnSound.clip.start();
        } 
            catch (Exception e) {
                e.printStackTrace();
            }
        */
        
       int idx = control.word.getWord().toUpperCase(Locale.ENGLISH).indexOf(letter.toUpperCase(Locale.ENGLISH));
       check = false;
   
       while(idx != -1){ // if letter is in the word set the question mark invisible so that letter will be seen by player
           control.logger.info("Kelimede " + letter + " bulundu. Index: " + idx);
           check = true;
           control.questionMarks.get(idx).setVisible(false);
           control.questionMarkLetters.get(idx).setVisible(true);
           countletter++;
           idx = control.word.getWord().toUpperCase(Locale.ENGLISH).indexOf(letter.toUpperCase(Locale.ENGLISH), idx + 1);
       }
       
       
       if(!check && control.countbodyparts < 6){
           control.bodyparts.get(control.countbodyparts).setVisible(true);
           control.countbodyparts++;
       }
       
        
       
       if(control.countbodyparts == 6 ){
           control.logger.info("Oyun bitti. Ekrandaki vücut parça sayısı : " + control.bodyparts);
           control.game = false;
           control.life--;
           int reply = JOptionPane.showConfirmDialog(null, "You lost! If you want to keep playing, there is your chance!", "LOSE", JOptionPane.YES_NO_OPTION);

           if(control.life >= 0) {
               
                if (reply == JOptionPane.YES_OPTION ) {
                     control.formtwo.setVisible(true);//minigame
                     control.formtwo.print_buttons();
                     control.formone.setVisible(false);//maingame will be invisible
                     
                     //sounds
                    /*
                      try {
                    control.s.stop();
                    control.s.filePath = minigamemusicLOOP;
                    control.s.resetAudioStream();
                    control.s.play();
                    } 
                    catch (Exception e) {
                    e.printStackTrace();
                    }
                     */
               }
                else{
                   
                    
                    //kabul etmezse
                    control.formone.setVisible(false);// make main game invisible
                    control.formzero.setVisible(true); // make menu visible
                    
           
                     String query = "INSERT INTO scoretable (username, score) VALUES('"+control.username+"' , "+control.score+")";
                    control.db.save(query);
                    control.formzero.setbestscore("Best Score: " +control.db.getBestScore());
                    control.formzero.setUsernameText("");
                    control.score = 0;
                    control.life = 3;
                    control.countbodyparts = 0;
                    
                }
            } 
            else {
                //canı kalmazsa
                
                control.formone.setVisible(false);
                control.formzero.setVisible(true);
                control.db.save("INSERT INTO scoretable (username, score) VALUES('"+control.username+"' , '"+control.score+"')");
                control.formzero.setbestscore("Best Score: " + control.db.getBestScore());
                control.formzero.setUsernameText("");
                control.score = 0;
                control.life = 3;
                control.countbodyparts = 0;
              
            }
       }
    
       
       //oyub kazanılması
       if(countletter==control.word.getWord().length()){
           control.logger.info("Oyun kazanıldı. Kelime uzunluğu ile sayaç eşit.");
           JOptionPane.showMessageDialog(null, "You win the game!");
           control.score++;
           control.game = true;
           control.countbodyparts = 0;
           control.formone.play();
       }
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
 


    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public ArrayList<JLabel> getBodyparts() {
        return bodyparts;
    }

    public void setBodyparts(ArrayList<JLabel> bodyparts) {
        this.bodyparts = bodyparts;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<JButton> buttons) {
        this.buttons = buttons;
    }

    public ArrayList<JButton> getQuestionMarks() {
        return questionMarks;
    }

    public void setQuestionMarks(ArrayList<JButton> questionMarks) {
        this.questionMarks = questionMarks;
    }

    public ArrayList<JLabel> getQuestionMarkLetters() {
        return questionMarkLetters;
    }

    public void setQuestionMarkLetters(ArrayList<JLabel> questionMarkLetters) {
        this.questionMarkLetters = questionMarkLetters;
    }

    public boolean isMinigamecheck() {
        return minigamecheck;
    }

    public void setMinigamecheck(boolean minigamecheck) {
        this.minigamecheck = minigamecheck;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCountbodyparts() {
        return countbodyparts;
    }

    public void setCountbodyparts(int countbodyparts) {
        this.countbodyparts = countbodyparts;
    }

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getCountletter() {
        return countletter;
    }

    public void setCountletter(int countletter) {
        this.countletter = countletter;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isMinigame_check() {
        return minigame_check;
    }

    public void setMinigame_check(boolean minigame_check) {
        this.minigame_check = minigame_check;
    }

    public Locale getEnLocale() {
        return enLocale;
    }

    public void setEnLocale(Locale enLocale) {
        this.enLocale = enLocale;
    }
    
    
    
    
}

class SoundController  
{   
    
    // to store current position 
    Long currentFrame; 
    public Clip clip; 
      
    // current status of clip 
    String status; 
      
    AudioInputStream audioInputStream; 
    public static String filePath; 
  
    // constructor to initialize streams and clip 
    public SoundController() 
        throws UnsupportedAudioFileException, 
        IOException, LineUnavailableException  
    { 
        // create AudioInputStream object 
        audioInputStream =  
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
          
        // create clip reference 
        clip = AudioSystem.getClip(); 
          
        // open audioInputStream to the clip 
        clip.open(audioInputStream); 
          
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
    } 
  
    // Work as the user enters his choice 
      
    
      
    // Method to play the audio 
    public void play()  
    { 
       
        //start the clip 
        clip.start(); 
          
        status = "play"; 
    } 
      
    // Method to pause the audio 
    public void pause()  
    { 
        if (status.equals("paused"))  
        { 
            System.out.println("audio is already paused"); 
            return; 
        } 
        this.currentFrame =  
        this.clip.getMicrosecondPosition(); 
        clip.stop(); 
        status = "paused"; 
    } 
      
    // Method to restart the audio 
    public void restart() throws IOException, LineUnavailableException, 
                                            UnsupportedAudioFileException  
    { 
        clip.stop(); 
        clip.close(); 
        resetAudioStream(); 
        currentFrame = 0L; 
        clip.setMicrosecondPosition(0); 
        this.play(); 
    } 
    // Method to stop the audio 
    public void stop() throws UnsupportedAudioFileException, 
    IOException, LineUnavailableException  
    { 
        currentFrame = 0L; 
        clip.stop(); 
        clip.close(); 
    } 
 
    // Method to reset audio stream 
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
                                            LineUnavailableException  
    { 
        audioInputStream = AudioSystem.getAudioInputStream( 
        new File(filePath).getAbsoluteFile()); 
        clip.open(audioInputStream); 
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
    } 

    public Long getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(Long currentFrame) {
        this.currentFrame = currentFrame;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }

    public void setAudioInputStream(AudioInputStream audioInputStream) {
        this.audioInputStream = audioInputStream;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        SoundController.filePath = filePath;
    }
  
    
    
    
    
    
} 