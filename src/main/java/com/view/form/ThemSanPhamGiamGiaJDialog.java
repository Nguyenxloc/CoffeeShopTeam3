/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.form;

import DoUong_ThongKe_Service.LoaiDoUongService;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.list;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.parser.DTDConstants;
import model.ChiTietDoUong;
import model.KhuyenMai;
import service.ChiTietDoUongService_Master;
import service.SaleService;

/**
 *
 * @author HP
 */
public class ThemSanPhamGiamGiaJDialog extends javax.swing.JDialog {

    private SaleService service = new SaleService();
    private ChiTietDoUongService_Master chiTietDoUongService = new ChiTietDoUongService_Master();
    private LoaiDoUongService loadDoUongService = new LoaiDoUongService();
    private ArrayList<KhuyenMai> listSale = new ArrayList<>();
    private ArrayList<ChiTietDoUong> listChiTietSP = new ArrayList<>();
    private ArrayList<Model_DoUong_ThongKe.LoaiDoUong> listLoaiDoUong = new ArrayList<>();

    private int row;
    static String maGiamGia;
    static String maSP;

   

    public ThemSanPhamGiamGiaJDialog(java.awt.Frame parent, boolean modal, String magiamgia, String maSP) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        fillToTableSanPhamDoUong();
        fillComboboxLoaiSP();
    }

//    public String getMasp() {
//        return masp;
//    }
//
//    public static void setMasp(String masp) {
//        ThemSanPhamGiamGiaJDialog.masp = masp;
//    }

    private void fillToTableSanPhamDoUong() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);

        row = tblSanPham.getSelectedRow();
        if (row == -1) {
            listChiTietSP = chiTietDoUongService.getListChiTietDoUong();
        } else {
            String maKM = tblSanPham.getValueAt(row, 0).toString();
            listChiTietSP = chiTietDoUongService.selectAllByMaGiamGia(maKM);
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");

        for (ChiTietDoUong ctdu : listChiTietSP) {
            String loaiKM = ctdu.getKhuyenMai().getLoaiKM();
            if (loaiKM.equals("%")) {
                String formattedNumber = decimalFormat.format(ctdu.getGiaBan());
                double giaBan = ctdu.getGiaBan();
                double giaTriGiamGia = ctdu.getKhuyenMai().getGiaTri();
                double giaTriDaGiamGia = giaBan - (giaBan * (giaTriGiamGia / 100));

                model.addRow(new Object[]{ctdu.getId(), ctdu.getTenDoUong(), ctdu.getLoaiDoUong(), formattedNumber});
            } else {
                String formattedNumber = decimalFormat.format(ctdu.getGiaBan());
                double giaBan = ctdu.getGiaBan();
                double giaTriGiamGia = ctdu.getKhuyenMai().getGiaTri();
                double giaTriDaGiamGia = giaBan - giaTriGiamGia;
                model.addRow(new Object[]{ctdu.getId(), ctdu.getTenDoUong(), ctdu.getLoaiDoUong(), formattedNumber});
            }
        }
    }

    //Chức năng load dữ liệu loại sản phẩm lên Combobox
    private void fillComboboxLoaiSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboloaiSanPham.getModel();
        model.removeAllElements();
        listLoaiDoUong = loadDoUongService.selectALl();
        model.addElement("Tất cả");
        for (Model_DoUong_ThongKe.LoaiDoUong ldu : listLoaiDoUong) {
            model.addElement(ldu.getTenLoaiDoUong());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtGiam = new javax.swing.JTextField();
        btnxacnhan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cboloaiSanPham = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thêm Sản Phẩm Giảm Giá");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Đồ Uống", "Giá", "Chọn sản phẩm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Giảm(%)");

        txtGiam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnxacnhan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnxacnhan.setText("Xác nhận");
        btnxacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxacnhanActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Loại sản phẩm");

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setForeground(new java.awt.Color(102, 102, 102));
        jCheckBox1.setText("Tất cả");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboloaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(354, 354, 354)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(43, 43, 43)
                            .addComponent(txtGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(147, 147, 147)
                            .addComponent(btnxacnhan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboloaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnxacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhanActionPerformed
        // TODO add your handling code here:
//        insert();
    }//GEN-LAST:event_btnxacnhanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ThemSanPhamGiaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ThemSanPhamGiaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ThemSanPhamGiaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ThemSanPhamGiaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ThemSanPhamGiaJDialog dialog = new ThemSanPhamGiaJDialog(new javax.swing.JFrame(), true, magg);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnxacnhan;
    private javax.swing.JComboBox<String> cboloaiSanPham;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiam;
    // End of variables declaration//GEN-END:variables

//    ChiTietGiamGiaDao dao = new ChiTietGiamGiaDao();
//    int row;
//    DaoGiamGia daogg = new DaoGiamGia();
//    private void fillToTableSP() {
//        model.setRowCount(0);
//        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String datenow = simple.format(date);
//        date = XDate.toDate(datenow, "yyyy-MM-dd");
//        try {
//            List<SanPham> list = daoSP.selectAll();
//            for (SanPham x : list) {
//                //               
//                giamGia gg = (giamGia) daogg.selectKhoangNgay(x.getId_sp());
//                if (gg == null) {
//                    model.addRow(new Object[]{x.getId_sp(), x.getTen_sp(),
//                        daoLSP.selectNameByID(x.getId_loaiSP()), x.getGia_sp()
//                    });
//                    continue;
//                }
//                if (date.before(gg.getNgayBD()) || date.after(gg.getNgayKT())) {
//                    System.out.println(magg);
//                    model.addRow(new Object[]{x.getId_sp(), x.getTen_sp(),
//                        daoLSP.selectNameByID(x.getId_loaiSP()), x.getGia_sp()
//                    });
//                } else {
//                    continue;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private GiamGiaChitiet getform() {
//        GiamGiaChitiet gg = new GiamGiaChitiet();
//        row = tblSanPham.getSelectedRow();
//        System.out.println(magg);
//        gg.setidgiamgia(magg);
//
//        System.out.println(gg.getidgiamgia());
//
//        gg.setidSp(tblSanPham.getValueAt(row, 0).toString());
//        System.out.println(gg.getidSP());
//        gg.setPhantramgiam(Integer.parseInt(txtGiam.getText()));
//        return gg;
//    }
//    private void insert() {
//        try {
//            if (checkGiamGia()) return;
//            if (MsgBox.confirm(this, "bạn có muốn thêm sản phẩm này cho sự kiện giảm giá ?")) {
//                for (int i = 0; i < tblSanPham.getRowCount(); i++) {
//                    if (tblSanPham.getValueAt(i, 4) == null) {
//                        continue;
//                    }
//                    if ((boolean) tblSanPham.getValueAt(i, 4).equals(true)) {
//                        GiamGiaChitiet gg = new GiamGiaChitiet();
//                        row = tblSanPham.getSelectedRow();
////                    System.out.println(magg);
//                        gg.setidgiamgia(magg);
//                        System.out.println(gg.getidgiamgia());
//                        gg.setidSp(tblSanPham.getValueAt(i, 0).toString());
//
//                        gg.setPhantramgiam(Integer.parseInt(txtGiam.getText()));
//                        dao.insert(gg);
//                    }
//                };
//                JOptionPane.showMessageDialog(this, "đã thêm sản phẩm vào giảm giá");
//                fillToTableSP();
//                dispose();
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private boolean checkGiamGia(){
//        String  regex  = "\\d+";
//        if (txtGiam.getText().equals("")) {
//            MsgBox.alert(this, "hãy nhập vào phần trăm giảm gía");
//            return true;
//        }
//        else if (!txtGiam.getText().matches(regex)) {
//             MsgBox.alert(this, " phần trăm giảm gía nhập là số");
//            return true;
//        }else;
//        return false;
//    }
}
