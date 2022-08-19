package UserInterface;

import Utility.Values;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static Window windowInstance = null;
    private static Container container;

    //create private constructor to restrict access from outside
    private Window(){
        super("Luton Hotel");
        setSize(Values.displayPct(80));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        container = getContentPane();
        setVisible(true);
    }

    //always return a single instance of the window
    public static Window getWindow() {
        if( windowInstance == null ){
            windowInstance = new Window();
        }
        return windowInstance;
    }

    public Container getContainer(){
        return container;
    }
    @Override
    public Component add(Component content){
        this.container.add(content);
        this.paint();
        return content;
    }

    //method to remove all the childs added to the Window
    public void removeAllChild(){
        this.container.removeAll();
        this.paint();
    }

    private void paint(){
        this.container.revalidate();
        this.container.repaint();
    }
}
