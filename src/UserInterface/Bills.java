package UserInterface;

import BusinessLayer.PanelToPdf;
import DataModel.CorporateInvoice;
import DataModel.IndividualBill;
import Utility.Values;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Bills {
    private Window window;
    public Bills(){
        this.window = Window.getWindow();;
    }

    public void individualBill(IndividualBill bill){
        JDialog dialog = new JDialog(this.window, "Individual Bill");
        dialog.setSize(new Dimension(Values.widthPct(this.window, 40), Values.heightPct(this.window, 80)));
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JPanel mainLay = new JPanel();
        mainLay.setLayout(new BoxLayout(mainLay, BoxLayout.Y_AXIS));

        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        mainLay.add(center);

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        center.add(mainContainer);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout(50, 10));
        mainContainer.add(container, BorderLayout.NORTH);

        JLabel title = new JLabel("Luton Hotel");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(title, BorderLayout.NORTH);

        JPanel midContainer = new JPanel();
        midContainer.setLayout(new BoxLayout(midContainer, BoxLayout.Y_AXIS));
        container.add(midContainer, BorderLayout.CENTER);

        JPanel detail = new JPanel();
        detail.setLayout(new BorderLayout());
        detail.setPreferredSize(new Dimension(Values.widthPct(this.window, 30), Values.heightPct(this.window, 20)));
        midContainer.add(detail);

        JPanel udetailHolder = new JPanel();
        udetailHolder.setLayout(new BoxLayout(udetailHolder, BoxLayout.Y_AXIS));
        detail.add(udetailHolder, BorderLayout.WEST);

        JLabel userDetail = new JLabel("Customer Details");
        userDetail.setFont(new Font("Serif", Font.BOLD, 20));
        udetailHolder.add(userDetail);

        JLabel name = new JLabel(bill.getCustomerName());
        name.setFont(new Font("Serif", Font.PLAIN, 17));
        udetailHolder.add(name);

        JLabel address = new JLabel(bill.getAddress());
        address.setFont(new Font("Serif", Font.PLAIN, 17));
        udetailHolder.add(address);

        JLabel contact = new JLabel(bill.getContact());
        contact.setFont(new Font("Serif", Font.PLAIN, 17));
        udetailHolder.add(contact);

        JPanel bdetailHolder = new JPanel();
        bdetailHolder.setLayout(new BoxLayout(bdetailHolder, BoxLayout.Y_AXIS));
        detail.add(bdetailHolder, BorderLayout.EAST);

        JLabel bookDetail = new JLabel("Booking Details");
        bookDetail.setFont(new Font("Serif", Font.BOLD, 20));
        bdetailHolder.add(bookDetail);


        JLabel checkIn = new JLabel("Arr. : " + bill.getCheckIn());
        checkIn.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(checkIn);

        JLabel checkOut = new JLabel("Dep. : " + bill.getCheckOut());
        checkOut.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(checkOut);

        JLabel roomNo = new JLabel("Room No.: " + bill.getRoomNo());
        roomNo.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(roomNo);

        JLabel roomType = new JLabel("Type: " + bill.getRoomType());
        roomType.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(roomType);

        JLabel billId = new JLabel("Bill Id: " + bill.getBillId());
        billId.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(billId);
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        model.addColumn("Description");
        model.addColumn("Value");
        model.addRow(new String[]{
                "Room Price: ",
                "Rs. " + bill.getRoomPrice()
        });
        model.addRow(new String[]{
                "Service Charge:",
                "Rs. " + bill.getServiceCharge()
        });
        model.addRow(new String[]{
                "Total Price: ",
                "Rs. " + bill.getTotalPrice()
        });
        JTable table = new JTable(model);
        table.setTableHeader(null);
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        container.add(scrollPane, BorderLayout.SOUTH);

        JButton print = new JButton("Print Bill");
        print.setFont(new Font("Serif", Font.BOLD, 30));
        print.setFocusable(false);
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String filename = name + "_LutonHotel_bill_" + String.valueOf(System.currentTimeMillis()).substring(5) + ".pdf";
                new PanelToPdf(container, filename);
                dialog.dispose();
            }
        });
        mainContainer.add(print, BorderLayout.SOUTH);
        dialog.add(mainLay);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void corporateBill(CorporateInvoice bill){
        JDialog dialog = new JDialog(this.window, "Corporate Monthly Bill");
        dialog.setSize(new Dimension(Values.widthPct(this.window, 40), Values.heightPct(this.window, 80)));
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JPanel mainLay = new JPanel();
        mainLay.setLayout(new BoxLayout(mainLay, BoxLayout.Y_AXIS));

        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        mainLay.add(center);

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        center.add(mainContainer);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout(50, 10));
        mainContainer.add(container, BorderLayout.NORTH);

        JLabel title = new JLabel("Luton Hotel");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(title, BorderLayout.NORTH);

        JPanel midContainer = new JPanel();
        midContainer.setLayout(new BoxLayout(midContainer, BoxLayout.Y_AXIS));
        container.add(midContainer, BorderLayout.CENTER);

        JPanel detail = new JPanel();
        detail.setLayout(new BorderLayout());
        detail.setPreferredSize(new Dimension(Values.widthPct(this.window, 30), Values.heightPct(this.window, 20)));
        midContainer.add(detail);

        JPanel udetailHolder = new JPanel();
        udetailHolder.setLayout(new BoxLayout(udetailHolder, BoxLayout.Y_AXIS));
        detail.add(udetailHolder, BorderLayout.WEST);

        JLabel userDetail = new JLabel("Org. Details");
        userDetail.setFont(new Font("Serif", Font.BOLD, 20));
        udetailHolder.add(userDetail);

        JLabel name = new JLabel(bill.getOrgName());
        name.setFont(new Font("Serif", Font.PLAIN, 17));
        udetailHolder.add(name);

        JLabel address = new JLabel(bill.getAddress());
        address.setFont(new Font("Serif", Font.PLAIN, 17));
        udetailHolder.add(address);

        JLabel contact = new JLabel(bill.getContact());
        contact.setFont(new Font("Serif", Font.PLAIN, 17));
        udetailHolder.add(contact);

        JPanel bdetailHolder = new JPanel();
        bdetailHolder.setLayout(new BoxLayout(bdetailHolder, BoxLayout.Y_AXIS));
        detail.add(bdetailHolder, BorderLayout.EAST);

        JLabel bookDetail = new JLabel("Booking Details");
        bookDetail.setFont(new Font("Serif", Font.BOLD, 20));
        bdetailHolder.add(bookDetail);

        JLabel checkIn = new JLabel("Total Invoice Sent : " + bill.getTotalSentInvoices());
        checkIn.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(checkIn);

        JLabel billId = new JLabel("Bill Id: " + bill.getInvoiceId());
        billId.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(billId);
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        model.addColumn("Description");
        model.addColumn("Value");

        model.addRow(new String[]{
                "Service Charge:",
                "Rs. " + bill.getServiceCharge()
        });
        model.addRow(new String[]{
                "Subtotal: ",
                "Rs. " + bill.getTotalPrice()
        });
        model.addRow(new String[]{
                "Discount: ",
                "Rs. " + bill.getDiscountAmount()
        });
        model.addRow(new String[]{
                "Total Price: ",
                "Rs. " + (bill.getTotalPrice() - bill.getDiscountAmount())
        });
        JTable table = new JTable(model);
        table.setTableHeader(null);
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        container.add(scrollPane, BorderLayout.SOUTH);

        JButton print = new JButton("Print Bill");
        print.setFont(new Font("Serif", Font.BOLD, 30));
        print.setFocusable(false);
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String filename = name + "_LutonHotel_bill_" + String.valueOf(System.currentTimeMillis()).substring(5) + ".pdf";
                new PanelToPdf(container, filename);
                dialog.dispose();
            }
        });
        mainContainer.add(print, BorderLayout.SOUTH);
        dialog.add(mainLay);
        dialog.pack();
        dialog.setVisible(true);

    }

    public void corporateInvoice(CorporateInvoice invoice){
        JDialog dialog = new JDialog(this.window, "Corporate Invoice");
        dialog.setSize(new Dimension(Values.widthPct(this.window, 40), Values.heightPct(this.window, 80)));
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JPanel mainLay = new JPanel();
        mainLay.setLayout(new BoxLayout(mainLay, BoxLayout.Y_AXIS));

        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        mainLay.add(center);

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        center.add(mainContainer);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout(50, 10));
        mainContainer.add(container, BorderLayout.NORTH);

        JLabel title = new JLabel("Luton Hotel");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(title, BorderLayout.NORTH);

        JPanel midContainer = new JPanel();
        midContainer.setLayout(new BoxLayout(midContainer, BoxLayout.Y_AXIS));
        container.add(midContainer, BorderLayout.CENTER);

        JPanel detail = new JPanel();
        detail.setLayout(new BorderLayout());
        detail.setPreferredSize(new Dimension(Values.widthPct(this.window, 30), Values.heightPct(this.window, 20)));
        midContainer.add(detail);

        JPanel udetailHolder = new JPanel();
        udetailHolder.setLayout(new BoxLayout(udetailHolder, BoxLayout.Y_AXIS));
        detail.add(udetailHolder, BorderLayout.WEST);

        JLabel userDetail = new JLabel("Org. Details");
        userDetail.setFont(new Font("Serif", Font.BOLD, 20));
        udetailHolder.add(userDetail);

        JLabel name = new JLabel(invoice.getOrgName());
        name.setFont(new Font("Serif", Font.PLAIN, 17));
        udetailHolder.add(name);

        JLabel address = new JLabel(invoice.getAddress());
        address.setFont(new Font("Serif", Font.PLAIN, 17));
        udetailHolder.add(address);

        JLabel contact = new JLabel(invoice.getContact());
        contact.setFont(new Font("Serif", Font.PLAIN, 17));
        udetailHolder.add(contact);

        JPanel bdetailHolder = new JPanel();
        bdetailHolder.setLayout(new BoxLayout(bdetailHolder, BoxLayout.Y_AXIS));
        detail.add(bdetailHolder, BorderLayout.EAST);

        JLabel bookDetail = new JLabel("Booking Details");
        bookDetail.setFont(new Font("Serif", Font.BOLD, 20));
        bdetailHolder.add(bookDetail);

        JLabel checkIn = new JLabel("Arr. : " + invoice.getCheckIn());
        checkIn.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(checkIn);

        JLabel checkOut = new JLabel("Dep. : " + invoice.getCheckOut());
        checkOut.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(checkOut);

        JLabel roomNo = new JLabel("Room No.: " + invoice.getRoomNo());
        roomNo.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(roomNo);

        JLabel roomType = new JLabel("Type: " + invoice.getRoomType());
        roomType.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(roomType);

        JLabel billId = new JLabel("Invoice Id: " + invoice.getInvoiceId());
        billId.setFont(new Font("Serif", Font.PLAIN, 17));
        bdetailHolder.add(billId);
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        model.addColumn("Description");
        model.addColumn("Value");
        model.addRow(new String[]{
                "Room Price: ",
                "Rs. " + invoice.getRoomPrice()
        });
        model.addRow(new String[]{
                "Service Charge:",
                "Rs. " + invoice.getServiceCharge()
        });
        model.addRow(new String[]{
                "Subtotal: ",
                "Rs. " + invoice.getTotalPrice()
        });
        model.addRow(new String[]{
                "Discount: ",
                "Rs. " + invoice.getDiscountAmount()
        });
        model.addRow(new String[]{
                "Total Price: ",
                "Rs. " + (invoice.getTotalPrice() - invoice.getDiscountAmount())
        });
        JTable table = new JTable(model);
        table.setTableHeader(null);
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        container.add(scrollPane, BorderLayout.SOUTH);

        JButton print = new JButton("Print Bill");
        print.setFont(new Font("Serif", Font.BOLD, 30));
        print.setFocusable(false);
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String filename = name + "_LutonHotel_bill_" + String.valueOf(System.currentTimeMillis()).substring(5) + ".pdf";
                new PanelToPdf(container, filename);
                dialog.dispose();
            }
        });
        mainContainer.add(print, BorderLayout.SOUTH);
        dialog.add(mainLay);
        dialog.pack();
        dialog.setVisible(true);

    }

}
