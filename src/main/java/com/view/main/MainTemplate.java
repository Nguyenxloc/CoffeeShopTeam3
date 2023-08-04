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
import com.view.form.QuanLyNhanVien;
import com.view.form.WalletForm;
import com.view.form_Template.Container;
import com.view.form_Template.FormLichHoc;
import com.view.form_Template.Form_BanHang;
import com.view.form_Template.Form_CapBac;
import com.view.form_Template.Form_ChamCong;
import com.view.form_Template.Form_GiaoCa;
import com.view.form_Template.Form_GiaoCa1;
import com.view.form_Template.Form_KhuyenMai;
import com.view.form_Template.Form_LoaiDoUong;
import com.view.form_Template.Form_LopHoc;
import com.view.form_Template.Form_QLDoUong;
import com.view.form_Template.Form_QLHoaDon;
import com.view.form_Template.Form_QuanLyKho;
import com.view.form_Template.Form_TaoTaiKhoan;
import com.view.form_Template.Form_ThongKe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import static java.awt.SystemColor.menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 */
public class MainTemplate extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private WalletForm home;
    private ThongBaoForm formThongbao;
    private Form_BanHang formBanHang;
    private Form_TaoTaiKhoan form_TaoTaiKhoan;
    private Form_QLDoUong form_QLDoUong;
    private Form_KhuyenMai form_KhuyenMai;
    private com.view.component.paneOfProduct paneOfProduct2;
    private QuanLyNhanVien formQLNhanVien;
    private Form_QLHoaDon formQLHoaDon;
    private Form_QuanLyKho form_QuanLyKho;
    private Form_GiaoCa form_GiaoCa1;
    private Form_ChamCong form_ChamCong;
    private Form_LoaiDoUong form_LoaiDoUong;
    private Form_CapBac form_CapBac;
    private Form_ThongKe form_ThongKe;
//    private Form_QuanLyKho form_QuanLyKho;
//    private Form_GiaoCa1 form_GiaoCa1;

    public MainTemplate() {
        initComponents();
        home = new WalletForm();
        formThongbao = new ThongBaoForm();
        formBanHang = new Form_BanHang();
        form_KhuyenMai = new Form_KhuyenMai();
        formQLNhanVien = new QuanLyNhanVien();
        form_TaoTaiKhoan = new Form_TaoTaiKhoan();
        form_QLDoUong = new Form_QLDoUong();
        formQLHoaDon = new Form_QLHoaDon();
        form_QuanLyKho = new Form_QuanLyKho();
        form_GiaoCa1 = new Form_GiaoCa();
        form_ChamCong = new Form_ChamCong();
        form_LoaiDoUong = new Form_LoaiDoUong();
        form_CapBac = new Form_CapBac();
        form_ThongKe = new Form_ThongKe();
        setBackground(new Color(0, 0, 0, 0));
        menuOfCB1.initMoving(MainTemplate.this);
        menuOfCB1.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 5) {
                    setForm(formQLHoaDon);
                } else if (index == 6) {
                    setForm(form_ThongKe);
                } else if (index == 0) {
                    setForm(formThongbao);
                } else if (index == 1) {
                    setForm(formBanHang);
                } else if (index == 7) {
                    setForm(formQLNhanVien);
                } else if (index == 8) {
                    setForm(form_TaoTaiKhoan);
                } else if (index == 2) {
//                    DoanhThu_singleton.getInstance().doanhThuNow =  ;
                    setForm(form_GiaoCa1);
                } else if (index == 3) {
                    setForm(form_QLDoUong);
                } else if (index == 4) {
                    setForm(form_KhuyenMai);
                } else if (index == 9) {
                    setForm(form_ChamCong);
                } else if (index == 10) {
                    setForm(form_QuanLyKho);
                } else if (index == 11) {
                    setForm(form_LoaiDoUong);
                } else if (index == 12) {
                    setForm(form_CapBac);
                } else if (index == 13) {
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
        mainPanel = new javax.swing.JPanel();
        menuOfCB1 = new com.view.component.MenuOfCB();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menuOfCB1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuOfCB1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private com.view.component.MenuOfCB menuOfCB1;
    private com.view.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
