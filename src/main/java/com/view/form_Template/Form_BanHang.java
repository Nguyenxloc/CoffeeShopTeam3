/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import SingletonClass.IdHD_singleton;
import SingletonClass.LstChiTietDoUong_singleton;
import SingletonClass.LstHoaDonCho_SingLeTon;
import SingletonClass.LstHoaDonDangPhaChe_singleton;
import SingletonClass.LstHoaDon_singleton;
import com.view.component.BillFrame;
import com.view.component.CreateBillPane;
import model.ChiTietDoUong;
import com.view.component.paneOfProduct;
import com.view.form_Template.Container.hoaDonModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.HoaDon;
import model.HoaDonChiTiet;
import service.ChiTietDoUongService_Master;
import service.HoaDonChiTietService;
import service.HoaDonService;

public class Form_BanHang extends javax.swing.JPanel {

    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private paneOfProduct paneProduct;
    private ArrayList<ChiTietDoUong> lstChiTietDoUongs = new ArrayList<>();
    private HoaDon localHoaDon = new HoaDon();
    HoaDonService hoaDonService = new HoaDonService();
    HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
    ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
    ArrayList<HoaDon> lstHoaDonCho = new ArrayList<>();
    Container.DefaultHoaDonModel localModel = new Container.DefaultHoaDonModel();
    hoaDonModel modelHD = new Container.DefaultHoaDonModel();
    int countHoaDonTbl = -1;
    int countHoaDonChoTbl = -1;
    int countHoaDonDangPhaCheTbl = -1;
    String localID;
    Date localDateNow;

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
        loadHoaDonDangPhaChe();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        lblDayNow.setText(formatter.format(date));
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        localDateNow = sqlDate;
        //Truyền biến vào panel productOfPane
//      paneProduct.setVisible(true);
    }

    public void loadHoaDonTbl() {
        int stt = 0;
        LstHoaDon_singleton.getInstance().lstHoaDon = hoaDonService.getListHoaDon();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblHoaDon.getModel();
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

    public void loadHoaDonChoTbl() {
        int stt = 0;
        LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho = hoaDonService.getListHoaDonCho();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblHoaDonCho.getModel();
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

    public void loadHoaDonDangPhaChe() {
        int stt = 0;
        LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe = hoaDonService.getHoaDonDangPhaChe();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblDangPhaChe.getModel();
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

    public void moveToHoaDonChoTbl() {
        hoaDonService.updateStt(0, LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getId());
        loadHoaDonTbl();
        loadHoaDonChoTbl();
    }

    public void moveToHoaDon() {
        hoaDonService.updateStt(1, LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho.get(countHoaDonChoTbl).getId());
        loadHoaDonTbl();
        loadHoaDonChoTbl();
    }

    private void reLoadProduct() {
        paneProduct = new paneOfProduct(lstChiTietDoUongs, tblDrinkDetail, localHoaDon, lblTotalCash);
        jScrollPane1.setViewportView(paneProduct);
        jScrollPane1.getViewport().repaint();
        jScrollPane1.getViewport().revalidate();
    }
//gọi từ service list sản phầm theo order by desc để sql trả về danh sách từng loại đồ uống

    public void LoadlstProduct() {
        lstChiTietDoUongs.clear();
        lstChiTietDoUongs = LstChiTietDoUong_singleton.getInstance().lstChiTietDoUongs;
        reLoadProduct();
    }

    public void showDetailHoaDonTab() {
        tblHoaDonCho.clearSelection();
        countHoaDonTbl = tblHoaDon.getSelectedRow();
        String checkStt;
        lblMaHD.setText(LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getMa());
        lblBan.setText(LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getBan().getTen());
        lblTime.setText(LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getThoiGian());
        lblMaNV.setText(LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getNhanVien().getMa());
        lblTenNV.setText(LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getNhanVien().getTen());
        if (LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getTinhTrangThanhToan() == 0) {
            checkStt = "Chưa thanh toán";
        } else {
            checkStt = "Đã thanh toán";
        }
        lblCheckStt.setText(checkStt);
        lblDate.setText(LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getNgayTao().toString());
        if (LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getStt() == 0) {
            lblStt.setText("Chờ");
        } else {
            lblStt.setText("Xử lý");
        }
        IdHD_singleton.getInstance().id = LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getId();
        IdHD_singleton.getInstance().maHD = LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getMa();
    }

    public void showDetailHoaDonTab_Waiting() {
        tblHoaDon.clearSelection();
        countHoaDonChoTbl = tblHoaDonCho.getSelectedRow();
        String checkStt;
        lblMaHD.setText(LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho.get(countHoaDonChoTbl).getMa());
        lblBan.setText(LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho.get(countHoaDonChoTbl).getBan().getTen());
        lblTime.setText(LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho.get(countHoaDonChoTbl).getThoiGian());
        lblMaNV.setText(LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho.get(countHoaDonChoTbl).getNhanVien().getMa());
        lblTenNV.setText(LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho.get(countHoaDonChoTbl).getNhanVien().getTen());
        if (LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho.get(countHoaDonChoTbl).getTinhTrangThanhToan() == 0) {
            checkStt = "Chưa thanh toán";
        } else {
            checkStt = "Đã thanh toán";
        }
        lblCheckStt.setText(checkStt);
        lblDate.setText(LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho.get(countHoaDonChoTbl).getNgayTao().toString());
        if (LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho.get(countHoaDonChoTbl).getStt() == 0) {
            lblStt.setText("Chờ");
        } else {
            lblStt.setText("Xử lý");
        }
        IdHD_singleton.getInstance().id = LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho.get(countHoaDonChoTbl).getId();
        IdHD_singleton.getInstance().maHD = LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho.get(countHoaDonChoTbl).getMa();
    }

    public void showDetailHoaDonDangPhaCheTab() {
        tblHoaDonCho.clearSelection();
        tblHoaDon.clearSelection();
        countHoaDonTbl = tblDangPhaChe.getSelectedRow();
        String checkStt;
        lblMaHD.setText(LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe.get(countHoaDonTbl).getMa());
        lblBan.setText(LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe.get(countHoaDonTbl).getBan().getTen());
        lblTime.setText(LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe.get(countHoaDonTbl).getThoiGian());
        lblMaNV.setText(LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe.get(countHoaDonTbl).getNhanVien().getMa());
        lblTenNV.setText(LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe.get(countHoaDonTbl).getNhanVien().getTen());
        if (LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe.get(countHoaDonTbl).getTinhTrangThanhToan() == 0) {
            checkStt = "Chưa thanh toán";
        } else {
            checkStt = "Đã thanh toán";
        }
        lblCheckStt.setText(checkStt);
        lblDate.setText(LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe.get(countHoaDonTbl).getNgayTao().toString());
        if (LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe.get(countHoaDonTbl).getStt() == 0) {
            lblStt.setText("Chờ");
        } else {
            lblStt.setText("Xử lý");
        }
        IdHD_singleton.getInstance().id = LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getId();
        IdHD_singleton.getInstance().maHD = LstHoaDon_singleton.getInstance().lstHoaDon.get(countHoaDonTbl).getMa();
    }

    public void showLstDrink() {
        double cellCheck = 0;
        double totalCheck = 0;
        int stt = 0;
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblDrinkDetail.getModel();
        model.setRowCount(0);
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
        lstHoaDonChiTiet = hoaDonChiTietService.getListHoaDonChiTietByID(IdHD_singleton.getInstance().id);
        for (HoaDonChiTiet hdChiTiet : lstHoaDonChiTiet) {
            System.out.println("test");
            stt++;
            cellCheck = Double.valueOf(hdChiTiet.getSoLuong()) * Double.valueOf(hdChiTiet.getChiTietDoUong().getGiaBan());
            totalCheck += cellCheck;
            model.addRow(new Object[]{stt, hdChiTiet.getChiTietDoUong().getTenDoUong(), hdChiTiet.getSoLuong(),
                hdChiTiet.getChiTietDoUong().getGiaBan(), cellCheck});
        }
        lblTotalCash.setText(String.valueOf(totalCheck));
    }

    public void hoanThanhPhaChe() {
        countHoaDonDangPhaCheTbl = tblDangPhaChe.getSelectedRow();
        String id = LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe.get(countHoaDonDangPhaCheTbl).getId();
        hoaDonService.updateTTPhaChe(id, 1);
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
        btnAdd = new javax.swing.JButton();
        btnWating = new javax.swing.JButton();
        btnUse = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        btnCompleteOrder = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDrinkDetail = new javax.swing.JTable();
        btnCheck = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblBan = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblCheckStt = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lblTotalCash = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblStt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        lblDayNow = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

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
        tblDangPhaChe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDangPhaCheMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDangPhaChe);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Hóa đơn chính ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Hóa đơn chờ");

        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Bàn", "Thanh Toán", "Pha chế"
            }
        ));
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHoaDonCho);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Danh sách sản phẩm");

        btnAdd.setText("Thêm mới");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnWating.setText("Chờ");
        btnWating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWatingActionPerformed(evt);
            }
        });

        btnUse.setText("Sử dụng");
        btnUse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseActionPerformed(evt);
            }
        });

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

        btnCompleteOrder.setText("Hoàn  thành");
        btnCompleteOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteOrderActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblDrinkDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane3.setViewportView(tblDrinkDetail);

        btnCheck.setText("Thanh toán");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        jLabel3.setText("Hóa đơn chi tiết");

        lblMaHD.setText("#tenHere");

        jLabel5.setText("Mã HĐ");

        jLabel7.setText("Ngày:");

        lblDate.setText("#tenHere");

        lblBan.setText("#tenHere");

        jLabel9.setText("Bàn:");

        lblCheckStt.setText("#tenHere");

        jLabel17.setText("Thanh toán:");

        lblTime.setText("#tenHere");

        jLabel11.setText("Thời gian:");

        btnDelete.setText("Xóa");

        btnUpdate.setText("Sửa");

        jLabel14.setText("Tổng tiền: ");

        lblTotalCash.setText("#cash");

        jLabel6.setText("Trạng thái:");

        lblStt.setText("#Stt ");

        jLabel8.setText("Tên NV:");

        lblTenNV.setText("#tenNV");

        jLabel15.setText("Mã NV:");

        lblMaNV.setText("#maNV ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblBan))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblCheckStt)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(36, 36, 36)
                                        .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDate)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblTime)
                                                .addGap(65, 65, 65)
                                                .addComponent(jLabel6)
                                                .addGap(21, 21, 21)
                                                .addComponent(lblStt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalCash, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpdate)
                                .addGap(36, 36, 36)))
                        .addComponent(btnDelete)
                        .addGap(35, 35, 35)
                        .addComponent(btnCheck)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCheck, btnDelete, btnUpdate});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lblMaHD))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel11)
                        .addComponent(lblTime)
                        .addComponent(lblBan))
                    .addComponent(jLabel6)
                    .addComponent(lblStt))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel17)
                        .addComponent(lblCheckStt))
                    .addComponent(lblDate))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblMaNV)
                    .addComponent(jLabel8)
                    .addComponent(lblTenNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lblTotalCash))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCheck)
                            .addComponent(btnDelete)
                            .addComponent(btnUpdate))
                        .addContainerGap())))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCheck, btnDelete, btnUpdate});

        lblDayNow.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDayNow.setForeground(new java.awt.Color(255, 0, 51));
        lblDayNow.setText("#GetTime");

        jLabel10.setText("Ngày:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCompleteOrder)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnWating, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnUse, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDayNow, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdd, btnUse, btnWating});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel4))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDayNow, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnWating, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUse))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCompleteOrder)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateBillPane(tblHoaDon).setVisible(true);
            }
        });
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        showDetailHoaDonTab();
        showLstDrink();

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnWatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWatingActionPerformed
        // TODO add your handling code here:
        moveToHoaDonChoTbl();
        loadHoaDonDangPhaChe();
    }//GEN-LAST:event_btnWatingActionPerformed

    private void btnUseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseActionPerformed
        // TODO add your handling code here:
        moveToHoaDon();
        loadHoaDonDangPhaChe();
    }//GEN-LAST:event_btnUseActionPerformed

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        // TODO add your handling code here:
        showDetailHoaDonTab_Waiting();
        showLstDrink();
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void tblDangPhaCheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDangPhaCheMouseClicked
        // TODO add your handling code here:
        showDetailHoaDonDangPhaCheTab();
        showLstDrink();
    }//GEN-LAST:event_tblDangPhaCheMouseClicked

    private void btnCompleteOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteOrderActionPerformed
        // TODO add your handling code here:
        hoanThanhPhaChe();
        loadHoaDonDangPhaChe();
        loadHoaDonTbl();
    }//GEN-LAST:event_btnCompleteOrderActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        // TODO add your handling code here:

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillFrame(IdHD_singleton.getInstance().id, tblHoaDon, tblDangPhaChe, tblHoaDonCho).setVisible(true);
            }
        });
    }//GEN-LAST:event_btnCheckActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnCompleteOrder;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUse;
    private javax.swing.JButton btnWating;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblBan;
    private javax.swing.JLabel lblCheckStt;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDayNow;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblStt;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTotalCash;
    private javax.swing.JTable tblDangPhaChe;
    private javax.swing.JTable tblDrinkDetail;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonCho;
    // End of variables declaration//GEN-END:variables

}
