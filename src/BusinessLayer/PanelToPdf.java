package BusinessLayer;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PanelToPdf {
    private final String filename;
    private final JPanel panel;

    public PanelToPdf(JPanel panel, String filename) {
        this.filename = filename;
        this.panel = panel;
    }

    public void printPdf(){
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.dir") + "/" + this.filename));
            document.open();
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(500, 500);
            Graphics2D g2 = new PdfGraphics2D(contentByte, 500, 500);
            this.panel.print(g2);
            g2.dispose();
            contentByte.addTemplate(template, 30, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(document.isOpen()){
                document.close();
            }
        }
    }
}
