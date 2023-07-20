/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import SingletonClass.LstChiTietDoUong_singleton;
import com.view.component.CreateBillPane;
import model.ChiTietDoUong;
import com.view.component.paneOfProduct;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.HoaDon;
import service.ChiTietDoUongService_Master;
import service.HoaDonService;

public class Form_BanHang extends javax.swing.JPanel {

    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private paneOfProduct paneProduct;
    private ArrayList<ChiTietDoUong> lstChiTietDoUongs = new ArrayList<>();
    private HoaDon localHoaDon = new HoaDon();
    HoaDonService hoaDonService = new HoaDonService();
    ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
    ArrayList<HoaDon> lstHoaDonCho = new ArrayList<>();
    int countHoaDonTbl = -1;

    /**
     * Creates new form Form_QlThongTinSV
     */
    public Form_BanHang() {
        initComponents();
        jScrollPane1.setBorder(null);
        this.setBorder(null);
        LoadlstProduct();
        localHoaDon.setId("#idHoaDon");
        loadHoaDonTbl();
        loadHoaDonChoTbl();
        //Truyền biến vào panel productOfPane
//      paneProduct.setVisible(true);
    }

    public void loadHoaDonTbl() {
        int stt = 0;
        lstHoaDon = hoaDonService.getListHoaDon();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        String thanhToanStt;
        String phaCheStt;
        for (HoaDon hoaDon : lstHoaDon) {
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
        lstHoaDonCho = hoaDonService.getListHoaDonCho();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblHoaDonCho.getModel();
        model.setRowCount(0);
        String thanhToanStt;
        String phaCheStt;
        for (HoaDon hoaDon : lstHoaDonCho) {
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
    
    public void reLoadHoaDonTbl() {
        int stt = 0;
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        String thanhToanStt;
        String phaCheStt;
        for (HoaDon hoaDon : lstHoaDon) {
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

    public void reLoadHoaDonChoTbl() {
        int stt = 0;
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblHoaDonCho.getModel();
        model.setRowCount(0);
        String thanhToanStt;
        String phaCheStt;
        for (HoaDon hoaDonCho : lstHoaDonCho) {
            stt++;
            if (hoaDonCho.getTinhTrangThanhToan() == 0) {
                thanhToanStt = "Chưa TT";
            } else {
                thanhToanStt = "Đã TT";
            }
            if (hoaDonCho.getTrangThaiPhaChe() == 0) {
                phaCheStt = "Chưa pha";
            } else {
                phaCheStt = "Đã pha";
            }
            model.addRow(new Object[]{stt, hoaDonCho.getMa(), hoaDonCho.getBan().getTen(), thanhToanStt, phaCheStt});
        }
    }

    public void moveToHoaDonChoTbl() {
        hoaDonService.updateStt(1,lstHoaDon.get(countHoaDonTbl).getId()); 
        loadHoaDonTbl();
        loadHoaDonChoTbl();
    }

    private void reLoadProduct() {
        paneProduct = new paneOfProduct(lstChiTietDoUongs, tblDrinkDetail, localHoaDon);
        jScrollPane1.setViewportView(paneProduct);
        jScrollPane1.getViewport().repaint();
        jScrollPane1.getViewport().revalidate();
    }
//gọi từ service list sản phầm theo order by desc để sql trả về danh sách từng loại đồ uống

    public void LoadlstProduct() {
        lstChiTietDoUongs.clear();
        lstChiTietDoUongs = LstChiTietDoUong_singleton.getInstance().lstChiTietDoUongs;
//        lstPerson.add(new DoUong("Coffe egg1","Coffe",25000.0,"Cafe+Trứng",null));
//        lstPerson.add(new DoUong("Coffe egg2", "Coffe",25000.0,"Cafe+Trứng",null));
//        lstPerson.add(new DoUong("Coffe egg3", "Coffe",25000.0,"Cafe+Trứng",null));
//        lstPerson.add(new DoUong("Coffe egg4", "Coffe",25000.0,"Cafe+Trứng",null));
//        lstPerson.add(new DoUong("Coffe egg5", "Coffe",25000.0,"Cafe+Trứng",null));
//        lstPerson.add(new DoUong("Coffe egg6", "Coffe",25000.0,"Cafe+Trứng",null));
//        lstPerson.add(new DoUong("Coffe egg7", "Coffe",25000.0,"Cafe+Trứng",null));
//        lstPerson.add(new DoUong("Coffe egg8", "Coffe",25000.0,"Cafe+Trứng",null));
//        lstPerson.add(new DoUong("Coffe egg9", "Coffe",25000.0,"Cafe+Trứng",null));
//        lstPerson.add(new DoUong("Coffe egg10", "Coffe",25000.0,"Cafe+Trứng",null));
//        lstPerson.add(new DoUong("Coffe egg11", "Coffe",25000.0,"Cafe+Trứng",null));
//        lstPerson.add(new DoUong("Coffe egg12", "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break1",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break2",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break3",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break4",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break5",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break6",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break7",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break8",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break9",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break10",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break11",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break12",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break13",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea break14",  "Trà",25000.0,"Trà truyền thống",null));
//        lstPerson.add(new DoUong("Tea Milk1",  "Trà sữa",25000.0,"Trà mix sữa",null));
//        lstPerson.add(new DoUong("Tea Milk2",   "Trà sữa",25000.0,"Trà mix sữa",null));
//        lstPerson.add(new DoUong("Tea Milk3",   "Trà sữa",25000.0,"Trà mix sữa",null));
//        lstPerson.add(new DoUong("Tea Milk4",   "Trà sữa",25000.0,"Trà mix sữa",null));
//        lstPerson.add(new DoUong("Tea Milk5",   "Trà sữa",25000.0,"Trà mix sữa",null));
//        lstPerson.add(new DoUong("Tea Milk6",   "Trà sữa",25000.0,"Trà mix sữa",null));
//        lstPerson.add(new DoUong("Tea Milk7",   "Trà sữa",25000.0,"Trà mix sữa",null));
//        lstPerson.add(new DoUong("Tea Milk8",   "Trà sữa",25000.0,"Trà mix sữa",null));
//        lstPerson.add(new DoUong("Tea Milk9",   "Trà sữa",25000.0,"Trà mix sữa",null));
//        lstPerson.add(new DoUong("Tea Milk10",   "Trà sữa",25000.0,"Trà mix sữa",null));
//        lstPerson.add(new DoUong("Tea Milk11",   "Trà sữa",25000.0,"Trà mix sữa",null));
        reLoadProduct();
    }

    public void showDetailHoaDonTab() {
        countHoaDonTbl = tblHoaDon.getSelectedRow();
        lblMaHD.setText(lstHoaDon.get(countHoaDonTbl).getMa());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDangPhaChe = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDrinkDetail = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblDangPhaChe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Bàn", "Thanh Toán", "Pha chế"
            }
        ));
        jScrollPane2.setViewportView(tblDangPhaChe);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Danh sách hóa đơn");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Hóa đơn chờ");

        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Bàn", "Thanh Toán", "Pha chế"
            }
        ));
        jScrollPane4.setViewportView(tblHoaDonCho);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Danh sách sản phẩm");

        jButton13.setText("Thêm mới");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Chờ");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Sử dụng");

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Bàn", "Thanh Toán", "Pha chế"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHoaDon);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Đang chờ pha chế");

        jButton19.setText("Hoàn  thành");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblDrinkDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane3.setViewportView(tblDrinkDetail);

        jButton18.setText("Thanh toán");

        jLabel3.setText("Hóa đơn chi tiết");

        lblMaHD.setText("#tenHere");

        jLabel5.setText("Mã HĐ");

        jLabel7.setText("Ngày:");

        jLabel8.setText("#tenHere");

        jLabel10.setText("#tenHere");

        jLabel9.setText("Bàn:");

        jLabel18.setText("#tenHere");

        jLabel17.setText("Thanh toán:");

        jLabel12.setText("#tenHere");

        jLabel11.setText("Thời gian:");

        jButton1.setText("Xóa");

        jButton2.setText("Sửa");

        jLabel14.setText("Tổng tiền: ");

        jLabel15.setText("#cash");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10)))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel18))
                                    .addComponent(jLabel11))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jButton2)
                        .addGap(27, 27, 27)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jButton18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton18, jButton2});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lblMaHD))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton18))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton18, jButton2});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(137, 137, 137)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButton19)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(158, 158, 158))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton13, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton13, jButton14, jButton15});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateBillPane(tblHoaDon).setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton13ActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        showDetailHoaDonTab();

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        moveToHoaDonChoTbl();
    }//GEN-LAST:event_jButton14ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JTable tblDangPhaChe;
    private javax.swing.JTable tblDrinkDetail;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonCho;
    // End of variables declaration//GEN-END:variables

}
