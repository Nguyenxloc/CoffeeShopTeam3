/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.view.component;

import SingletonClass.LstHoaDonCho_SingLeTon;
import SingletonClass.LstHoaDonDangPhaChe_singleton;
import SingletonClass.LstHoaDon_singleton;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import service.HoaDonChiTietService;
import service.HoaDonService;

/**
 *
 * @author 84374
 */
public class BillFrame extends javax.swing.JFrame {

    /**
     * Creates new form BillFrame
     */
    private String LocalId;
    HoaDonService hoaDonService = new HoaDonService();
    HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
    double totalCheck =0 ;
    double localMoneyTake = 0;
    JTable localTblHoaDon;
    JTable localTblHoaDonDangPhaChe;
    JTable localTblHoaDonCho;
    public BillFrame(String id,JTable tblHoaDon, JTable tblHoaDonDangPhaChe,JTable tblHoaDonCho) {
        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        LocalId = id;
        localTblHoaDon=tblHoaDon;
        localTblHoaDonDangPhaChe=tblHoaDonDangPhaChe;
        localTblHoaDonCho = tblHoaDonCho;
        loadData();
    }

    public void loadData() {
        int stt = 0;
        double cellCheck = 0;
        HoaDon hoaDon = hoaDonService.getHoaDonByID(LocalId);
        String checkStt;
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblDrinkDetail.getModel();
        model.setRowCount(0);
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = hoaDonChiTietService.getListHoaDonChiTietByID(LocalId);
        lblMaHD.setText(hoaDon.getMa());
        lblBan.setText(hoaDon.getBan().getTen());
        if (hoaDon.getTinhTrangThanhToan() == 0) {
            checkStt = "Chưa thanh toán";
        } else {
            checkStt = "Đã thanh toán";
        }
        lblCheckStt.setText(checkStt);
        lblDate.setText(String.valueOf(hoaDon.getNgayTao()));
        lblTime.setText(hoaDon.getThoiGian());
        for (HoaDonChiTiet hoaDonChiTiet : lstHoaDonChiTiet) {
            stt++;
            cellCheck = Double.valueOf(hoaDonChiTiet.getSoLuong()) * Double.valueOf(hoaDonChiTiet.getChiTietDoUong().getGiaBan());
            totalCheck += cellCheck;
            model.addRow(new Object[]{stt, hoaDonChiTiet.getChiTietDoUong().getTenDoUong(), hoaDonChiTiet.getSoLuong(),
                hoaDonChiTiet.getChiTietDoUong().getGiaBan(), cellCheck});
        }
        lblTotalCheck.setText(String.valueOf(totalCheck));
        txtEnterMoney.setText(String.valueOf(hoaDon.getMoneyTake()));
    }
    public void updateMoneyTake(){
          
          hoaDonService.updateMoneyTake(LocalId,localMoneyTake);
    }
    
    public void updateSttCheckBill(){
          hoaDonService.updateSttCheckBill(1,LocalId);
    }
    
      public void loadHoaDonTbl() {
        int stt = 0;
        LstHoaDon_singleton.getInstance().lstHoaDon = hoaDonService.getListHoaDon();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) localTblHoaDon.getModel();
        model.setRowCount(0);
        String thanhToanStt;
        String phaCheStt;
        for (HoaDon hoaDon : LstHoaDon_singleton.getInstance().lstHoaDon) {
            stt++;
            if (hoaDon.getTinhTrangThanhToan() == 0) {
                thanhToanStt = "Chưa TT";
            } else {
                thanhToanStt = "Đã TT";
            }
            if (hoaDon.getTrangThaiPhaChe() == 0) {
                phaCheStt = "Chưa pha";
            } else {
                phaCheStt = "Đã pha";
            }

            model.addRow(new Object[]{stt, hoaDon.getMa(), hoaDon.getBan().getTen(), thanhToanStt, phaCheStt});
        }
    }
      public void loadHoaDonDangPhaChe() {
        int stt = 0;
        LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe = hoaDonService.getHoaDonDangPhaChe();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) localTblHoaDonDangPhaChe.getModel();
        model.setRowCount(0);
        String thanhToanStt;
        String phaCheStt;
        for (HoaDon hoaDon : LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe) {
            stt++;
            if (hoaDon.getTinhTrangThanhToan() == 0) {
                thanhToanStt = "Chưa TT";
            } else {
                thanhToanStt = "Đã TT";
            }
            if (hoaDon.getTrangThaiPhaChe() == 0) {
                phaCheStt = "Chưa pha";
            } else {
                phaCheStt = "Đã pha";
            }

            model.addRow(new Object[]{stt, hoaDon.getMa(), hoaDon.getBan().getTen(), thanhToanStt, phaCheStt});
        }
    }
      public void loadHoaDonChoTbl() {
        int stt = 0;
        LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho = hoaDonService.getListHoaDonCho();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) localTblHoaDonCho.getModel();
        model.setRowCount(0);
        String thanhToanStt;
        String phaCheStt;
        for (HoaDon hoaDon : LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho) {
            stt++;
            if (hoaDon.getTinhTrangThanhToan() == 0) {
                thanhToanStt = "Chưa TT";
            } else {
                thanhToanStt = "Đã TT";
            }
            if (hoaDon.getTrangThaiPhaChe() == 0) {
                phaCheStt = "Chưa pha";
            } else {
                phaCheStt = "Đã pha";
            }
            model.addRow(new Object[]{stt, hoaDon.getMa(), hoaDon.getBan().getTen(), thanhToanStt, phaCheStt});
        }
    }
      
      
      
    public void reloadTbl(){
         loadHoaDonTbl();
         loadHoaDonDangPhaChe();
         loadHoaDonChoTbl();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDrinkDetail = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblBan = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblCheckStt = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblTotalCheck = new javax.swing.JLabel();
        txtEnterMoney = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnCaculating = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lblMoneyChange = new javax.swing.JLabel();
        btnCheck = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("COFFEE CODER");

        jLabel2.setText("Bàn:");

        jLabel3.setText("Ngày:");

        jLabel4.setText("Thời gian:");

        jLabel5.setText("Thanh toán: ");

        tblDrinkDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tblDrinkDetail);

        jLabel6.setText("Mã  HĐ:");

        lblMaHD.setText("#MaHD");

        lblBan.setText("#ban");

        lblDate.setText("#Ngay");

        lblCheckStt.setText("#checkStt");

        lblTime.setText("#time");

        jLabel12.setText("Tổng cộng: ");

        lblTotalCheck.setText("#totalCheck");

        jLabel14.setText("Số tiền nhận:");

        btnCaculating.setText("Tính tiền");
        btnCaculating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaculatingActionPerformed(evt);
            }
        });

        jLabel15.setText("Số tiền trả lại:");

        lblMoneyChange.setText("#moneyChange");

        btnCheck.setText("Thanh Toán");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDate))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lblBan)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(32, 32, 32)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTime)
                            .addComponent(lblCheckStt))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMaHD)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(24, 24, 24)
                                .addComponent(lblTotalCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(lblMoneyChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(133, 133, 133)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(txtEnterMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCheck)
                            .addComponent(btnCaculating))
                        .addGap(36, 36, 36))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCaculating, btnCheck});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblMaHD))
                .addGap(3, 3, 3)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(lblBan)
                    .addComponent(lblCheckStt))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(lblDate)
                    .addComponent(lblTime))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblTotalCheck))
                .addGap(18, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtEnterMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCaculating))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblMoneyChange))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCheck)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCaculatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaculatingActionPerformed
        // TODO add your handling code here:
        double moneyTake = Double.valueOf(txtEnterMoney.getText());
        localMoneyTake = moneyTake;
        double moneyChange = moneyTake - totalCheck;
        lblMoneyChange.setText(String.valueOf(moneyChange));
    }//GEN-LAST:event_btnCaculatingActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        // TODO add your handling code here:
        localMoneyTake = Double.valueOf(txtEnterMoney.getText());
        updateMoneyTake();
        updateSttCheckBill();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillFinishFrame(LocalId).setVisible(true);
            }
        });
        reloadTbl();
        this.dispose();
    }//GEN-LAST:event_btnCheckActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCaculating;
    private javax.swing.JButton btnCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBan;
    private javax.swing.JLabel lblCheckStt;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMoneyChange;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTotalCheck;
    private javax.swing.JTable tblDrinkDetail;
    private javax.swing.JTextField txtEnterMoney;
    // End of variables declaration//GEN-END:variables
}
