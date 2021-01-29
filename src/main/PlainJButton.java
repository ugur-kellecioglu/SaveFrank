
package main;


public class PlainJButton extends javax.swing.JButton {
    public PlainJButton (){
        super();
        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
    }
    public PlainJButton (String text){
        super(text);
        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
    }
}
