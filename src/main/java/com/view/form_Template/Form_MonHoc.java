/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_canbo;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.ChuyenNganh;
import model.LopHoc;
import model.MonHoc;
import service.CanBoService;

/**
 *
 * @author Lê Chấn Khang
 */
public class Form_MonHoc extends javax.swing.JPanel {

    private CanBoService service = new CanBoService();
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    
    public Form_MonHoc() {
        initComponents();
        
        loadData();
        loadCN();
        loadLop();
    }

    
    private void loadCN() {
          ArrayList<ChuyenNganh> lit = service.selectChuyenNganh();
          comboBoxModel = (DefaultComboBoxModel) cboMaCN.getModel();
          for (ChuyenNganh cn : lit) {
            comboBoxModel.addElement(cn.getMaChuyenNganh());
        }
    }

    private void loadLop() {
           ArrayList<LopHoc> lit = service.selectLopHoc();
          comboBoxModel = (DefaultComboBoxModel) cboMaLop.getModel();
          for (LopHoc cn : lit) {
            comboBoxModel.addElement(cn.getMaLopHoc());
        }    }
    
    private void loadData() {
         ArrayList<MonHoc> list = service.selectMonHoc();
         defaultTableModel = (DefaultTableModel) tblQLMH.getModel();
         defaultTableModel.setRowCount(0);
         for (MonHoc monHoc : list) {
            defaultTableModel.addRow(new Object[]{
                monHoc.getMaMonHoc(),monHoc.getMaChuyenNganh(),monHoc.getMaLopHoc(),monHoc.getTenMonHoc(),
                monHoc.getSoTinChi(),monHoc.getMua()
            });
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLMH = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaMon = new javax.swing.JTextField();
        txtMua = new javax.swing.JTextField();
        txtTenMH = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtSoTC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboMaLop = new javax.swing.JComboBox<>();
        cboMaCN = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("QUẢN LÝ MÔN HỌC");

        tblQLMH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã môn học", "Mã chuyên ngành", "Mã lớp học", "Tên môn học", "Số tín chỉ", "Mùa"
            }
        ));
        tblQLMH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLMHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLMH);

        jLabel2.setText("Mã môn học");

        jLabel3.setText("Mã chuyên ngành");

        jLabel4.setText("Mã lớp học");

        jLabel5.setText("Tên môn học");

        jLabel6.setText("Mùa");

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Thêm môn học");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Sửa môn học");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Xóa môn học");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setText("Số tín chỉ");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel8.setText("Tìm kiếm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(326, 326, 326))
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSearch)
                                    .addComponent(txtSoTC, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                    .addComponent(txtMaMon)
                                    .addComponent(txtTenMH)
                                    .addComponent(txtMua, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboMaCN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton3)
                            .addComponent(jButton2))))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3, jButton4});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboMaCN, cboMaLop, txtMaMon, txtMua, txtSearch, txtSoTC, txtTenMH});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jButton2))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboMaCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton2, jButton3, jButton4});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboMaCN, cboMaLop, txtMaMon, txtMua, txtSearch, txtSoTC, txtTenMH});

    }// </editor-fold>//GEN-END:initComponents

    private void tblQLMHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLMHMouseClicked
        // TODO add your handling code here:
        int index = tblQLMH.getSelectedRow();
        
        MonHoc monHoc = service.selectMonHoc().get(index);
        txtMaMon.setText(monHoc.getMaMonHoc());
        cboMaCN.setSelectedItem(monHoc.getMaChuyenNganh());
        cboMaLop.setSelectedItem(monHoc.getMaLopHoc());
        txtTenMH.setText(monHoc.getTenMonHoc());
        txtSoTC.setText(monHoc.getSoTinChi()+"");
        txtMua.setText(monHoc.getMua());
        
    }//GEN-LAST:event_tblQLMHMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String maMH = txtMaMon.getText();
        String maCN = cboMaCN.getSelectedItem().toString();
        String maLH = cboMaLop.getSelectedItem().toString();
        String ten = txtTenMH.getText();
        Integer soTC = Integer.parseInt(txtSoTC.getText());
        String mua = txtMua.getText();
        
        MonHoc mh = new MonHoc(maMH, maCN, maLH, ten, soTC, mua);
        JOptionPane.showMessageDialog(this, service.saveMonHoc2(mh));
        loadData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String maMH = txtMaMon.getText();
        String maCN = cboMaCN.getSelectedItem().toString();
        String maLH = cboMaLop.getSelectedItem().toString();
        String ten = txtTenMH.getText();
        Integer soTC = Integer.parseInt(txtSoTC.getText());
        String mua = txtMua.getText();
        
        MonHoc mh = new MonHoc("", maCN, maLH, ten, soTC, mua);
        JOptionPane.showMessageDialog(this, service.updateMonHoc2(mua, mh));
        loadData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không");
        if(hoi!=JOptionPane.YES_OPTION){
            return;
        }
        String maMH = txtMaMon.getText();
        JOptionPane.showMessageDialog(this, service.deleteMonHoc(maMH));
        loadData();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        DefaultTableModel ab = (DefaultTableModel) tblQLMH.getModel();
        TableRowSorter<DefaultTableModel> ob =  new TableRowSorter<>(ab);
        tblQLMH.setRowSorter(ob);
        ob.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboMaCN;
    private javax.swing.JComboBox<String> cboMaLop;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblQLMH;
    private javax.swing.JTextField txtMaMon;
    private javax.swing.JTextField txtMua;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoTC;
    private javax.swing.JTextField txtTenMH;
    // End of variables declaration//GEN-END:variables

    

    
}
