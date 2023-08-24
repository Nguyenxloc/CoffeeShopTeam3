/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.view.form_Template;

import SingletonClass.LstChiTietDoUong_singleton;
import static Test.ComponentImageCapture.getScreenShot;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.view.component.paneOfmenu;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.ChiTietDoUong;

/**
 *
 * @author 84374
 */
public class GenMenuFrame extends javax.swing.JFrame {

    /**
     * Creates new form GenMenuFrame
     */
    private ArrayList<paneOfmenu> lstPaneMenu = new ArrayList<>();
    private ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
    private paneOfmenu paneMenu;
    public GenMenuFrame() {
        initComponents();
        panelMenuDisplay.setLayout(new BorderLayout());
        loadFirstAppear();
//        this.setLayout(new BorderLayout());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelMenuDisplay = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton3.setText("In Menu");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        panelMenuDisplay.setBorder(new javax.swing.border.MatteBorder(null));
        panelMenuDisplay.setOpaque(false);

        javax.swing.GroupLayout panelMenuDisplayLayout = new javax.swing.GroupLayout(panelMenuDisplay);
        panelMenuDisplay.setLayout(panelMenuDisplayLayout);
        panelMenuDisplayLayout.setHorizontalGroup(
            panelMenuDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
        );
        panelMenuDisplayLayout.setVerticalGroup(
            panelMenuDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 563, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelMenuDisplay);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
//        panelMenuDisplay.setPreferredSize(new Dimension(640, 800));
//        imgToPdf();
        saveScreenShot(paneMenu);
    }//GEN-LAST:event_jButton3ActionPerformed

    public void loadFirstAppear() {
        lstChiTietDoUong = LstChiTietDoUong_singleton.getInstance().lstChiTietDoUongs;
        paneMenu = new paneOfmenu(lstChiTietDoUong, null);
        paneMenu.setPreferredSize(new Dimension(640, 800));
        lstPaneMenu.add(paneMenu);
        setForm(lstPaneMenu.get(0));
    }

    public void next() {

    }

    public void pre() {

    }

    public void imgToPdf() {
        try {
            // Create a new file with the provided output file name
            // Create a new PDF document with the provided file name
            PdfWriter writer = new PdfWriter(new FileOutputStream("menu.pdf"));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);
            BufferedImage imgBuf = getScreenShot(panelMenuDisplay);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imgBuf, "png", baos);
            byte[] byteArr = baos.toByteArray();
            com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(ImageDataFactory.create(byteArr));
            document.add(image);
            document.close();
        } catch (Exception ee) {
            JOptionPane.showMessageDialog(null, "An error occurred while converting the image to PDF: " + ee.getMessage());
        }
    }

    private void setForm(JComponent com) {
        panelMenuDisplay.removeAll();
        panelMenuDisplay.add(com);
        panelMenuDisplay.repaint();
        panelMenuDisplay.revalidate();
    }

    public static BufferedImage getScreenShot(
            Component component) {
        BufferedImage image = new BufferedImage(
                640, 800,
                BufferedImage.TYPE_INT_RGB
        );
        // call the Component's paint method, using
        // the Graphics object of the image.
        component.paint(image.getGraphics()); // alternately use .printAll(..)
        return image;
    }

    public void saveScreenShot(Component f) {
        BufferedImage img = getScreenShot(
                f);
        JOptionPane.showMessageDialog(
                null,
                new JLabel(
                        new ImageIcon(
                                img.getScaledInstance(
                                        img.getWidth(null) / 2,
                                        img.getHeight(null) / 2,
                                        Image.SCALE_SMOOTH)
                        )));
        try {
            // write the image as a PNG
            ImageIO.write(
                    img,
                    "png",
                    new File("menu.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GenMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenMenuFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMenuDisplay;
    // End of variables declaration//GEN-END:variables
}
