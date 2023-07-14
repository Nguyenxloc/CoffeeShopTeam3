/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.main;

import com.view.event.EventMenuSelected;
import com.view.form.DangKyDVForm;
import com.view.form.ThongBaoForm;
import com.view.form.LichHocForm;
import com.view.form.DiemForm;
import com.view.form.WalletForm;
import com.view.form_Template.FormLichHoc;
import com.view.form_Template.Form_BanHang;
import com.view.form_Template.Form_QLDoUong;
import com.view.form_Template.Form_LopHoc;
import com.view.form_Template.Form_TaoTaiKhoan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import static java.awt.SystemColor.menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author RAVEN
 */
public class MainTemplate extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private WalletForm home;
    private ThongBaoForm formThongbao;
    private LichHocForm formLichHoc;
    private DiemForm formDiem;
    private DangKyDVForm form4;
    private Form_BanHang formBanHang;
    private Form_QLDoUong form_QLDoUong;
    private FormLichHoc formLH;
    private com.view.component.paneOfProduct paneOfProduct2;

    public MainTemplate() {
        initComponents();
        formLH = new FormLichHoc();
        home = new WalletForm();
        formThongbao = new ThongBaoForm();
        formLichHoc = new LichHocForm();
        formDiem = new DiemForm();
        formBanHang = new Form_BanHang();
        form_QLDoUong = new Form_QLDoUong();
        JScrollPane scroll = new JScrollPane();
        Component[] com = formBanHang.getComponents();

        setBackground(new Color(0, 0, 0, 0));
        menuOfCB1.initMoving(MainTemplate.this);
        menuOfCB1.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 5) {
//                    setForm(fromDKDV);
                } else if (index == 6) {
//                    setForm(formBaoCao);
                } else if (index == 0) {
                    setForm(formThongbao);

                } else if (index == 1) {
                    setForm(formBanHang);
                } else if (index == 7) {
                    setForm(formLH);
                } else if (index == 8) {
                    setForm(new Form_TaoTaiKhoan());
                } else if (index == 2) {
//                    setForm(formQLDiem);
                } else if (index == 3) {
                    setForm(form_QLDoUong);
//                    setForm();
                } else if (index == 4) {
                    setForm(home);
                } else if (index == 9) {
                    MessageFrame messageFrame = new MessageFrame();
                    messageFrame.show();
                    messageFrame.setMessage("Bạn có chắc chắn muốn đăng xuất không?");
                    messageFrame.setButtonOK(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                            messageFrame.dispose();
                            new LoginFrame().setVisible(true);
                        }
                    });

                    messageFrame.setButtonCancel(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            messageFrame.dispose();
                        }
                    });
                }
            }
        });

        //  set when system open start with home form
        setForm(new ThongBaoForm());

    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.view.swing.PanelBorder();
        header2 = new com.view.component.Header();
        mainPanel = new javax.swing.JPanel();
        menuOfCB1 = new com.view.component.MenuOfCB();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        header2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel1.setText("Người dùng: ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cán bộ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menuOfCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(menuOfCB1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.view.component.Header header2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainPanel;
    private com.view.component.MenuOfCB menuOfCB1;
    private com.view.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
