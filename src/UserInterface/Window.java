package UserInterface;

import Utility.Values;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static Window windowInstance = null;
    private static Container container;
    private Window(){
        super("Luton Hotel");
        setSize(Values.displayPct(80));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        container = getContentPane();
        setVisible(true);
    }

    public static Container getWindow() {
        if( windowInstance == null ){
            windowInstance = new Window();
        }
        return container;
    }

    public void addContent(Component content){
        this.container.add(content);
        this.paint();
    }

    public void removeAllChild(){
        this.container.removeAll();
        this.paint();
    }

    private void paint(){
        this.container.revalidate();
        this.container.repaint();
    }
}
