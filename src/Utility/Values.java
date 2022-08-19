package Utility;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Values {
    private static String resPath;
    private static int screenHeight;
    private static int screenWidth;
    private static Dimension dimension;

    private static boolean isInitialised = false;

    private Values() {
        resPath = this.getClass().getResource("/res").toString().replace("file:", "");
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = (int) dimension.getHeight();
        screenWidth =  (int) dimension.getWidth();
        isInitialised = true;
    }

    public static void initializeValues(){
        if(!isInitialised){
            new Values();
        }
    }

    public static String resPath() {
        return resPath;
    }

    public static int screenHeight() {
        return screenHeight;
    }

    public static int screenWidth() {
        return screenWidth;
    }

    public static Dimension display() {
        return dimension;
    }
    public static Dimension parent(Component parent) {
        return new Dimension(parent.getWidth(), parent.getHeight());
    }

    public static Dimension displayPct(int percentage){
        float percent = (float) percentage / 100;
        return new Dimension((int) (screenWidth() * percent), (int) (screenHeight() * percent) );
    }

    //return the applied dimension of the component, i.e. via setSize() or setMaximumSize() etc
    private static Dimension getDimension(Component parent){
        int w = parent.getWidth();
        int h = parent.getHeight();
        Dimension max = parent.getMaximumSize();
        Dimension min = parent.getMinimumSize();
        Dimension preferred = parent.getPreferredSize();
        w = (w == 0) ? (int) max.getWidth() : w ;
        w = (w == 0) ? (int) min.getWidth() : w ;
        w = (w == 0) ? (int) preferred.getWidth() : w ;
        h = (h == 0) ? (int) max.getHeight() : h;
        h = (h == 0) ? (int) min.getHeight() : h;
        h = (h == 0) ? (int) preferred.getHeight() : h;
        return new Dimension(w, h);
    }
    public static int heightPct(Component parent, int percentage){
        Dimension dim = getDimension(parent);
        float percent = (float)  percentage / 100;
        return (int) ( dim.getHeight() * percent );
    }

    public static int widthPct(Component parent, int percentage){
        Dimension dim = getDimension(parent);
        float percent = (float) percentage / 100;
        return (int) ( dim.getWidth() * percent );
    }


    public static Dimension fillParent(Component parent){
        Dimension dimension = getDimension(parent);
        return new Dimension((int) dimension.getWidth(), (int) dimension.getHeight());
    }

    public static String getResPath(String name){
        return resPath() + "/"  + name;
    }
}
