/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_canbo;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.BaoCao22;

/**
 *
 * @author Lê Chấn Khang
 */
public class Form_BaoCao extends javax.swing.JPanel {

    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    
    public Form_BaoCao() {
        initComponents();
        
        loadData();
        loadData2();
    }
    
    
     private void loadData() {
         ArrayList<BaoCao22> list = new ArrayList<>();
         BaoCao22 a = new BaoCao22("BC1", "Đánh giá chật lượng dạy giảng viên", "Giảng viên dạy dễ hiểu,luôn trả lời câu hỏi sinh viên ngay trong buổi học", "MH01", "LH03", "sv1");
         BaoCao22 b = new BaoCao22("BC2", "Đánh giá chật lượng dạy giảng viên", "Giảng viên dạy dễ hiểu,giảng viên hay vào muộn", "MH02", "LH01", "sv1");
         BaoCao22 c = new BaoCao22("BC3", "Đánh giá chật lượng dạy giảng viên", "Giảng viên dạy hơi nhanh,luôn trả lời câu hỏi sinh viên ngay trong buổi học", "MH05", "LH02", "sv3");
         BaoCao22 d = new BaoCao22("BC4", "Đánh giá chật lượng dạy giảng viên", "Giảng viên dạy dễ hiểu,luôn trả lời câu hỏi sinh viên ngay trong buổi học", "MH03", "LH09", "sv2");
         BaoCao22 e = new BaoCao22("BC5", "Đánh giá chật lượng dạy giảng viên", "Giảng viên dạy dễ hiểu,luôn trả lời câu hỏi sinh viên ngay trong buổi học", "MH04", "LH04", "sv11");
         BaoCao22 f = new BaoCao22("BC6", "Đánh giá chật lượng dạy giảng viên", "Giảng viên dạy dễ hiểu,luôn trả lời câu hỏi sinh viên ngay trong buổi học", "MH02", "LH05", "sv20");
         BaoCao22 g = new BaoCao22("BC7", "Đánh giá chật lượng dạy giảng viên", "Giảng viên dạy dễ hiểu,luôn trả lời câu hỏi sinh viên ngay trong buổi học", "MH06", "LH08", "sv6");
         BaoCao22 h = new BaoCao22("BC8", "Đánh giá chật lượng dạy giảng viên", "Giảng viên dạy dễ hiểu,luôn trả lời câu hỏi sinh viên ngay trong buổi học", "MH07", "LH04", "sv7");
         BaoCao22 j = new BaoCao22("BC9", "Đánh giá chật lượng dạy giảng viên", "Giảng viên dạy dễ hiểu,luôn trả lời câu hỏi sinh viên ngay trong buổi học", "MH08", "LH07", "sv9");
         BaoCao22 y = new BaoCao22("BC10", "Đánh giá chật lượng dạy giảng viên", "Giảng viên dạy dễ hiểu,luôn trả lời câu hỏi sinh viên ngay trong buổi học", "MH10", "LH01", "sv4");
    
      list.add(a);
      list.add(b);
      list.add(c);
      list.add(d);
      list.add(e);
      list.add(f);
      list.add(g);
      list.add(h);
      list.add(j);
      list.add(y);
      
      defaultTableModel = (DefaultTableModel) tblFormSV.getModel();
      defaultTableModel.setRowCount(0);
         for (BaoCao22 bc : list) {
             defaultTableModel.addRow(new Object[]{
                 bc.getMaBaoCao(),bc.getTieuDe(),bc.getNoiDung(),bc.getMaMH(),bc.getMaLop(),bc.getMaSV()
             });
         }
     }
    
    private void loadData2() {
         ArrayList<BaoCao22> list = new ArrayList<>();
         BaoCao22 a = new BaoCao22("BC1", "Đánh giá chật lượng lớp học", "Lóp ngoan chịu khó học", "MH01", "LH03", "gv1");
         BaoCao22 b = new BaoCao22("BC2", "Đánh giá chật lượng lớp học", "Lớp không chú ý nghe bài và nói chuyện nhiều", "MH02", "LH01", "gv2");
         BaoCao22 c = new BaoCao22("BC3", "Đánh giá chật lượng lớp học", "Lớp ồn không nghe giảng", "MH05", "LH02", "gv4");
         BaoCao22 d = new BaoCao22("BC4", "Đánh giá chật lượng lớp học", "Lóp chăm chú nghe giảng", "MH03", "LH09", "gv7");
         BaoCao22 e = new BaoCao22("BC5", "Đánh giá chật lượng lớp học", "Lớp nhiều học sinh khá giỏi", "MH04", "LH04", "gv6");
         BaoCao22 f = new BaoCao22("BC6", "Đánh giá chật lượng lớp học", "Một số chú ý nghe giảng còn lại chơi game trong giờ", "MH02", "LH05", "gv5");
         BaoCao22 g = new BaoCao22("BC7", "Đánh giá chật lượng lớp học", "Lớp tốt", "MH06", "LH08", "gv4");
         BaoCao22 h = new BaoCao22("BC8", "Đánh giá chật lượng lớp học", "Lớp ồn không nghe giảng", "MH07", "LH04", "gv9");
         BaoCao22 j = new BaoCao22("BC9", "Đánh giá chật lượng lớp học", "Lóp ngoan chịu khó học", "MH08", "LH07", "gv12");
         BaoCao22 y = new BaoCao22("BC10", "Đánh giá chật lượng lớp học", "Lóp chăm chú nghe giảng", "MH10", "LH01", "gv2");
    
      list.add(a);
      list.add(b);
      list.add(c);
      list.add(d);
      list.add(e);
      list.add(f);
      list.add(g);
      list.add(h);
      list.add(j);
      list.add(y);
      
      defaultTableModel = (DefaultTableModel) tblFormGV.getModel();
      defaultTableModel.setRowCount(0);
         for (BaoCao22 bc : list) {
             defaultTableModel.addRow(new Object[]{
                 bc.getMaBaoCao(),bc.getTieuDe(),bc.getNoiDung(),bc.getMaMH(),bc.getMaLop(),bc.getMaSV()
             });
         }    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFormSV = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFormGV = new javax.swing.JTable();

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblFormSV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MaBC", "Tiêu đề", "Nội dung", "Mã môn học", "Mã lớp học", "MaSV"
            }
        ));
        jScrollPane1.setViewportView(tblFormSV);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Feedback sinh viên", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblFormGV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MABC", "Tiêu đề", "Nội dung", "Mã môn dạy", "Mã lớp dạy", "MaGV"
            }
        ));
        jScrollPane2.setViewportView(tblFormGV);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        jTabbedPane1.addTab("Feedback giáo viên", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblFormGV;
    private javax.swing.JTable tblFormSV;
    // End of variables declaration//GEN-END:variables

    

   
}
