/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_canbo;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChuyenNganh;
import model.DiemThanhPhan;
import model.LopHoc;
import model.MonHoc;
import model.SinhVien;
import service.CanBoService;

/**
 *
 * @author Lê Chấn Khang
 */
public class Form_Diem extends javax.swing.JPanel {

    private CanBoService canBoService = new CanBoService();
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    
    public Form_Diem() {
        initComponents();
        
        loadData();
        loadSV();
        loadLH();
        loadCN();
        loadMHoc();
    }

    private void loadData() {
       ArrayList<DiemThanhPhan> lit = canBoService.getAllDiem();
       defaultTableModel = (DefaultTableModel) tblQLDSV.getModel();
       defaultTableModel.setRowCount(0);
        for (DiemThanhPhan diem : lit) {
            defaultTableModel.addRow(new Object[]{
                diem.getMaSV(),diem.getMaMonHoc(),diem.getMaNganhHoc(),diem.getMaLopHoc(),
                diem.getNam(),diem.getMua(),diem.getLab1(),diem.getLab2(),diem.getLab3(),
                diem.getLab4(),diem.getAssignment(),diem.getDiemThi(),diem.getDiemTongKet()
            });
        }
    }

    private void loadSV() {
           ArrayList<SinhVien> lit = canBoService.getCboSV();
           comboBoxModel = (DefaultComboBoxModel) cboSV.getModel();
           for (SinhVien sv : lit) {
              comboBoxModel.addElement(sv.getMaSV());
        }
    }

    private void loadLH() {
        ArrayList<LopHoc> list = canBoService.getCboLH();
        comboBoxModel = (DefaultComboBoxModel) cboMaLop.getModel();
        for (LopHoc lopHoc : list) {
            comboBoxModel.addElement(lopHoc.getMaLopHoc());
        }
    }

    private void loadCN() {
        ArrayList<ChuyenNganh> lit = canBoService.getCboCN();
        comboBoxModel = (DefaultComboBoxModel) cboNganh.getModel();
        for (ChuyenNganh chuyenNganh : lit) {
            comboBoxModel.addElement(chuyenNganh.getMaChuyenNganh());
        }
    }
    
    
    private void loadMHoc() {
        ArrayList<MonHoc> lit = canBoService.getCboMHoc();
        comboBoxModel = (DefaultComboBoxModel) cboMH.getModel();
        for (MonHoc mh : lit) {
            comboBoxModel.addElement(mh.getMaMonHoc());
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLDSV = new javax.swing.JTable();
        txtNam = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLap1 = new javax.swing.JTextField();
        txtLap2 = new javax.swing.JTextField();
        txtLap3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboMH = new javax.swing.JComboBox<>();
        cboNganh = new javax.swing.JComboBox<>();
        cboMaLop = new javax.swing.JComboBox<>();
        txtMua = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDiemThi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtLap4 = new javax.swing.JTextField();
        txtASM = new javax.swing.JTextField();
        cboSV = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("QUẢN LÝ ĐIỂM SINH VIÊN");

        jLabel2.setText("Mã sinh viên");

        jLabel3.setText("Mã môn");

        jLabel4.setText("Mã ngành");

        tblQLDSV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sinh viên", "Mã môn", "Mã ngành", "Mã lớp", "Năm", "Mùa", "Lap1", "Lap2", "Lap3", "Lap4", "Assignment", "Điểm thi", "Tổng kết"
            }
        ));
        tblQLDSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLDSVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLDSV);

        jLabel6.setText("Lap1");

        jLabel7.setText("Lap2");

        jLabel8.setText("Lap3");

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sửa ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Xóa");

        jLabel9.setText("Năm");

        jLabel10.setText("Mã lớp");

        jLabel5.setText("Mùa");

        jLabel11.setText("Lap4");

        jLabel12.setText("Assignment");

        jLabel13.setText("Điểm thi");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(322, 322, 322))
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cboMH, javax.swing.GroupLayout.Alignment.LEADING, 0, 147, Short.MAX_VALUE)
                                    .addComponent(cboNganh, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboSV, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNam, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMua, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtLap1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                        .addComponent(txtLap2))
                                    .addComponent(txtLap4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLap3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtASM, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDiemThi, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel12))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboMH, cboMaLop, cboNganh, cboSV, txtMua, txtNam});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtASM, txtDiemThi, txtLap1, txtLap2, txtLap3, txtLap4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(130, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel3)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtLap1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtLap2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtLap3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3))))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5)))
                                    .addComponent(txtNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtLap4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtASM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtDiemThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addGap(67, 391, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboMH, cboMaLop, cboNganh, cboSV, txtMua, txtNam});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtASM, txtDiemThi, txtLap1, txtLap2, txtLap3, txtLap4});

    }// </editor-fold>//GEN-END:initComponents

    private void tblQLDSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLDSVMouseClicked
        // TODO add your handling code here:
        int index = tblQLDSV.getSelectedRow();
        DiemThanhPhan thanhPhan = canBoService.getAllDiem().get(index);
        cboSV.setSelectedItem(thanhPhan.getMaSV());
        cboMH.setSelectedItem(thanhPhan.getMaMonHoc());
        cboNganh.setSelectedItem(thanhPhan.getMaNganhHoc());
        cboMaLop.setSelectedItem(thanhPhan.getMaLopHoc());
        txtNam.setText(thanhPhan.getNam()+"");
        txtMua.setText(thanhPhan.getMua());
        txtLap1.setText(thanhPhan.getLab1()+"");
        txtLap2.setText(thanhPhan.getLab2()+"");
        txtLap3.setText(thanhPhan.getLab3()+"");
        txtLap4.setText(thanhPhan.getLab4()+"");
        txtASM.setText(thanhPhan.getAssignment()+"");
        txtDiemThi.setText(thanhPhan.getDiemThi()+"");
        
    }//GEN-LAST:event_tblQLDSVMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String maSV =  cboSV.getSelectedItem().toString();
        String maMH =  cboMH.getSelectedItem().toString();
        String maNganh =  cboNganh.getSelectedItem().toString();
        String maLop =  cboMaLop.getSelectedItem().toString();
        Integer nam = Integer.parseInt(txtNam.getText());
        String mua = txtMua.getText();
        Double lap1 = Double.parseDouble(txtLap1.getText());
        Double lap2 = Double.parseDouble(txtLap2.getText());
        Double lap3 = Double.parseDouble(txtLap3.getText());
        Double lap4 = Double.parseDouble(txtLap4.getText());
        Double asm = Double.parseDouble(txtASM.getText());
        Double diemThi = Double.parseDouble(txtDiemThi.getText());
        
        DiemThanhPhan diemThanhPhan = new DiemThanhPhan(maSV, maMH, maNganh, 
                maLop, nam, mua, lap1, lap2, lap3, 
                lap4, asm, diemThi);
        JOptionPane.showMessageDialog(this, canBoService.addDiem(diemThanhPhan));
        loadData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String maSV =  cboSV.getSelectedItem().toString();
        String maMH =  cboMH.getSelectedItem().toString();
        String maNganh =  cboNganh.getSelectedItem().toString();
        String maLop =  cboMaLop.getSelectedItem().toString();
        Integer nam = Integer.parseInt(txtNam.getText());
        String mua = txtMua.getText();
        Double lap1 = Double.parseDouble(txtLap1.getText());
        Double lap2 = Double.parseDouble(txtLap2.getText());
        Double lap3 = Double.parseDouble(txtLap3.getText());
        Double lap4 = Double.parseDouble(txtLap4.getText());
        Double asm = Double.parseDouble(txtASM.getText());
        Double diemThi = Double.parseDouble(txtDiemThi.getText());
        
        DiemThanhPhan diemThanhPhan = new DiemThanhPhan("", maMH, maNganh, 
                maLop, nam, mua, lap1, lap2, lap3, 
                lap4, asm, diemThi);
        JOptionPane.showMessageDialog(this, canBoService.updateDiem(maSV, diemThanhPhan));
        loadData();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboMH;
    private javax.swing.JComboBox<String> cboMaLop;
    private javax.swing.JComboBox<String> cboNganh;
    private javax.swing.JComboBox<String> cboSV;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblQLDSV;
    private javax.swing.JTextField txtASM;
    private javax.swing.JTextField txtDiemThi;
    private javax.swing.JTextField txtLap1;
    private javax.swing.JTextField txtLap2;
    private javax.swing.JTextField txtLap3;
    private javax.swing.JTextField txtLap4;
    private javax.swing.JTextField txtMua;
    private javax.swing.JTextField txtNam;
    // End of variables declaration//GEN-END:variables

    
}
