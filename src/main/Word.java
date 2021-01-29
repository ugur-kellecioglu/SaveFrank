
package main;



import java.io.File;
import java.io.FileReader;
import java.util.Locale;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static main.Controller.control;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Word {
    
    private String word;
    private String definition;
    
    public Word(String category){
        get_random_word(category);
    }
    
    private int pick_random_number(int a){
        //pick a random number between 0 - A
        int x = (int) ( Math.random() * a) ;
        return x ;
    }
    public void get_random_word(String category){

        //JSON parser object to parse read file
        JSONParser parser = new JSONParser();
        String path = "";
        try 
        {   
            path = System.getProperty("user.dir") + "\\kelime.json";
            File file = new File(path);
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonobj = (JSONObject) obj;
            
            JSONArray jsonarray = (JSONArray) jsonobj.get(category);
            
            JSONObject word = (JSONObject) jsonarray.get(pick_random_number(jsonarray.size()));
            
            
            this.word = word.get("word").toString().toUpperCase(Locale.ENGLISH);
            this.definition = word.get("definition").toString();
            control.logger.info("Word is randomly picked. " + this.word);
            control.logger.info("Definition is randomly picked. " + this.definition);
            
        } 
        catch(Exception e){
            JOptionPane.showInputDialog("DOSYA AÇILIRKEN HATA OLUŞTU " + path); 
        }  
    }
    

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
    
    
    
}
