/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_canbo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.GiangVien;
import model.Lich;
import model.LichFull;
import model.LopHoc;
import model.MonHoc;
import model.PhongHoc;
import model.SinhVien;
import service.CanBoService;
import service.GiangVienService;
import service.SinhVienService;

/**
 *
 * @author Lê Chấn Khang
 */
public class FormLichHoc extends javax.swing.JPanel {

    CanBoService cbService = new CanBoService();
    SinhVienService svService = new SinhVienService();
    GiangVienService gvService = new GiangVienService();
    int count = -1;
    ArrayList<LichFull> lstLichFull = new ArrayList<>();

    ArrayList<LopHoc> lstLopHoc = new ArrayList<>();
    ArrayList<MonHoc> lstMonHoc = new ArrayList<>();
    ArrayList<PhongHoc> lstPhongHoc = new ArrayList<>();
    ArrayList<String> lstNgayHoc = new ArrayList<>();
    ArrayList<String> lstThoiGianHoc = new ArrayList<>();

    ArrayList<LopHoc> lstLopThi = new ArrayList<>();
    ArrayList<MonHoc> lstMonThi = new ArrayList<>();
    ArrayList<PhongHoc> lstPhongThi = new ArrayList<>();
    ArrayList<String> lstNgayThi = new ArrayList<>();
    ArrayList<String> lstThoiGianThi = new ArrayList<>();

    /**
     * Creates new form FormLichHoc
     */
    public FormLichHoc() {
        initComponents();
//        loadToTblLichHoc();
        try {
            loadToLichThiFaster();
            loadToTblLichHocFaster();
            loadToCboLopHocThi();
            loadToCboMaMonThi();
            loadToCboPhongThi();
            loadToCboNgayThi();
            loadToCboThoiGianThi();
            loadToCboLopHocHoc();
            loadToCboMaMonHoc();
            loadToCboPhongHoc();
            loadToCboNgayHoc();
            loadToCboThoiGianHoc();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadToTblLichThi() {
        DefaultTableModel modelOfLH = new DefaultTableModel();
        modelOfLH = (DefaultTableModel) tblLichThi.getModel();
        modelOfLH.setRowCount(0);
        ArrayList<Lich> lstLich = new ArrayList<>();
        lstLich = cbService.selectLich();
        for (Lich lichObj : lstLich) {
            SinhVien sv = new SinhVien();
            GiangVien gv = new GiangVien();
            sv = svService.findBId(lichObj.getMaSV());
            gv = gvService.getByMaGV(lichObj.getMaGiangVien());
            MonHoc mh = new MonHoc();
            mh = cbService.selectMonHocByID(lichObj.getMaMonHoc());
            modelOfLH.addRow(new Object[]{lichObj.getMaSV(), sv.getHoTen(), lichObj.getMaGiangVien(), gv.getHoTen(), lichObj.getMaMonHoc(), mh.getTenMonHoc(), lichObj.getMaLopHoc(), lichObj.getNgayHoc(), lichObj.getMaPhongHoc(), lichObj.getThoiGian()});
        }
    }

    public void loadToLichThiFaster() {
        DefaultTableModel modelOfLT = new DefaultTableModel();
        modelOfLT = (DefaultTableModel) tblLichThi.getModel();
        modelOfLT.setRowCount(0);
        lstLichFull = cbService.selectLichFull();
        for (LichFull lichObj : lstLichFull) {
            modelOfLT.addRow(new Object[]{lichObj.getMaSV(), lichObj.getHoTenSv(), lichObj.getMaGiangVien(), lichObj.getHoTenGV(), lichObj.getMaMonHoc(), lichObj.getTenMonHoc(), lichObj.getMaLopHoc(), lichObj.getNgayHoc(), lichObj.getMaPhongHoc(), lichObj.getThoiGian()});
        }
    }

    public void loadToTblLichHocFaster() {
        DefaultTableModel modelOfLH = new DefaultTableModel();
        modelOfLH = (DefaultTableModel) tblLichHoc.getModel();
        modelOfLH.setRowCount(0);
        lstLichFull = cbService.selectLichFull();
        for (LichFull lichObj : lstLichFull) {
            modelOfLH.addRow(new Object[]{lichObj.getMaSV(), lichObj.getHoTenSv(), lichObj.getMaGiangVien(), lichObj.getHoTenGV(), lichObj.getMaMonHoc(), lichObj.getTenMonHoc(), lichObj.getMaLopHoc(), lichObj.getNgayHoc(), lichObj.getMaPhongHoc(), lichObj.getThoiGian()});
        }
    }

    public void loadToCboLopHocThi() {
        DefaultComboBoxModel modelOfCBOlopHocThi = new DefaultComboBoxModel();
        modelOfCBOlopHocThi = (DefaultComboBoxModel) cboLopHocThi.getModel();
        modelOfCBOlopHocThi.removeAllElements();
        lstLopThi = cbService.selectLopHoc();
        modelOfCBOlopHocThi.addElement("Tất cả");
        for (LopHoc lopThi : lstLopThi) {
            System.out.println("test cbo");
            modelOfCBOlopHocThi.addElement(lopThi.getMaLopHoc());
        }
    }

    public void loadToCboMaMonThi() {
        DefaultComboBoxModel modelOfCBOMaMon = new DefaultComboBoxModel();
        modelOfCBOMaMon = (DefaultComboBoxModel) cboMaMonThi.getModel();
        modelOfCBOMaMon.removeAllElements();
        lstMonThi = cbService.selectMonHoc();
        modelOfCBOMaMon.addElement("Tất cả");
        for (MonHoc monThi : lstMonThi) {
            System.out.println("test cbo");
            String mh = monThi.getMaMonHoc() + "-" + monThi.getTenMonHoc();
            modelOfCBOMaMon.addElement(mh);
        }
    }

    public void loadToCboPhongThi() {
        DefaultComboBoxModel modelOfCBOPhongThi = new DefaultComboBoxModel();
        modelOfCBOPhongThi = (DefaultComboBoxModel) cboPhongThi.getModel();
        modelOfCBOPhongThi.removeAllElements();
        lstPhongThi = cbService.selectPhongHoc();
        modelOfCBOPhongThi.addElement("Tất cả");
        for (PhongHoc phongThi : lstPhongThi) {
            System.out.println("test cbo");
            modelOfCBOPhongThi.addElement(phongThi.getMaPhongHoc());
        }
    }

    public void loadToCboNgayThi() {
        DefaultComboBoxModel modelOfCBONgayThi = new DefaultComboBoxModel();
        modelOfCBONgayThi = (DefaultComboBoxModel) cboNgayThi.getModel();
        modelOfCBONgayThi.removeAllElements();
        ArrayList<String> lstNgayThi = new ArrayList<>();
        lstNgayThi = cbService.getDay();
        modelOfCBONgayThi.addElement("Tất cả");
        for (String ngayThi : lstNgayThi) {
            modelOfCBONgayThi.addElement(ngayThi.toString());
        }
    }

    public void loadToCboThoiGianThi() {
        DefaultComboBoxModel modelOfCBOThoiGian = new DefaultComboBoxModel();
        modelOfCBOThoiGian = (DefaultComboBoxModel) cboThoiGianThi.getModel();
        modelOfCBOThoiGian.removeAllElements();
        lstThoiGianThi = cbService.getTime();
        modelOfCBOThoiGian.addElement("Tất cả");
        for (String ngayThi : lstThoiGianThi) {
            modelOfCBOThoiGian.addElement(ngayThi.toString());
        }
    }

    public void loadToTblLichHoc() {
        DefaultTableModel modelOfLH = new DefaultTableModel();
        modelOfLH = (DefaultTableModel) tblLichHoc.getModel();
        modelOfLH.setRowCount(0);
        ArrayList<Lich> lstLich = new ArrayList<>();
        lstLich = cbService.selectLich();
        for (Lich lichObj : lstLich) {
            SinhVien sv = new SinhVien();
            GiangVien gv = new GiangVien();
            sv = svService.findBId(lichObj.getMaSV());
            gv = gvService.getByMaGV(lichObj.getMaGiangVien());
            MonHoc mh = new MonHoc();
            mh = cbService.selectMonHocByID(lichObj.getMaMonHoc());
            modelOfLH.addRow(new Object[]{lichObj.getMaSV(), sv.getHoTen(), lichObj.getMaGiangVien(), gv.getHoTen(), lichObj.getMaMonHoc(), mh.getTenMonHoc(), lichObj.getMaLopHoc(), lichObj.getNgayHoc(), lichObj.getMaPhongHoc(), lichObj.getThoiGian()});
        }
    }

///////////////////////////////////cboLichHoc///////////////////////// 
    public void loadToCboLopHocHoc() {
        DefaultComboBoxModel modelOfCBOlopHoc = new DefaultComboBoxModel();
        modelOfCBOlopHoc = (DefaultComboBoxModel) cboLopHocHoc.getModel();
        modelOfCBOlopHoc.removeAllElements();
        lstLopHoc = cbService.selectLopHoc();
        modelOfCBOlopHoc.addElement("Tất cả");
        for (LopHoc lopHoc : lstLopHoc) {
            System.out.println("test cbo");
            modelOfCBOlopHoc.addElement(lopHoc.getMaLopHoc());
        }
    }

    public void loadToCboMaMonHoc() {
        DefaultComboBoxModel modelOfCBOMaMonHoc = new DefaultComboBoxModel();
        modelOfCBOMaMonHoc = (DefaultComboBoxModel) cboMaMonHoc.getModel();
        modelOfCBOMaMonHoc.removeAllElements();
        lstMonHoc = cbService.selectMonHoc();
        modelOfCBOMaMonHoc.addElement("Tất cả");
        for (MonHoc monHoc : lstMonHoc) {
            System.out.println("test cbo");
            String mh = monHoc.getMaMonHoc() + "-" + monHoc.getTenMonHoc();
            modelOfCBOMaMonHoc.addElement(mh);
        }
    }

    public void loadToCboPhongHoc() {
        DefaultComboBoxModel modelOfCBOPhongHoc = new DefaultComboBoxModel();
        modelOfCBOPhongHoc = (DefaultComboBoxModel) cboPhongHoc.getModel();
        modelOfCBOPhongHoc.removeAllElements();
        lstPhongHoc = cbService.selectPhongHoc();
        modelOfCBOPhongHoc.addElement("Tất cả");
        for (PhongHoc phongHoc : lstPhongHoc) {
            System.out.println("test cbo");
            modelOfCBOPhongHoc.addElement(phongHoc.getMaPhongHoc());
        }
    }

    public void loadToCboNgayHoc() {
        DefaultComboBoxModel modelOfCBONgayHoc = new DefaultComboBoxModel();
        modelOfCBONgayHoc = (DefaultComboBoxModel) cboNgayHoc.getModel();
        modelOfCBONgayHoc.removeAllElements();

        lstNgayHoc = cbService.getDay();
        modelOfCBONgayHoc.addElement("Tất cả");
        for (String ngayThi : lstNgayHoc) {
            modelOfCBONgayHoc.addElement(ngayThi.toString());
        }
    }

    public void loadToCboThoiGianHoc() {
        DefaultComboBoxModel modelOfCBOThoiGian = new DefaultComboBoxModel();
        modelOfCBOThoiGian = (DefaultComboBoxModel) cboThoiGianHoc.getModel();
        modelOfCBOThoiGian.removeAllElements();

        lstThoiGianHoc = cbService.getTime();
        modelOfCBOThoiGian.addElement("Tất cả");
        for (String ngayThi : lstThoiGianHoc) {
            modelOfCBOThoiGian.addElement(ngayThi.toString());
        }
    }

    public void showDetailLichThi() {
        count = tblLichThi.getSelectedRow();
        txtMaGVLichThi.setText(lstLichFull.get(count).getMaGiangVien());
        txtMaSVLichThi.setText(lstLichFull.get(count).getMaSV());
        txtHoTenGVLichThi.setText(lstLichFull.get(count).getHoTenGV());
        txtHoTenSVLichThi.setText(lstLichFull.get(count).getHoTenSv());
        cboMaMonThi.setSelectedItem(lstLichFull.get(count).getMaMonHoc() + "-" + lstLichFull.get(count).getTenMonHoc());
        txtTenMonLichThi.setText(lstLichFull.get(count).getTenMonHoc());
        cboLopHocThi.setSelectedItem(lstLichFull.get(count).getMaLopHoc());
        cboNgayThi.setSelectedItem(lstLichFull.get(count).getNgayHoc());
        cboPhongThi.setSelectedItem(lstLichFull.get(count).getMaPhongHoc());
        cboThoiGianThi.setSelectedItem(lstLichFull.get(count).getThoiGian());
    }

    public void showDetailLichHoc() {
        count = tblLichHoc.getSelectedRow();
        txtMaGVLichHoc.setText(lstLichFull.get(count).getMaGiangVien());
        txtMaSVLichHoc.setText(lstLichFull.get(count).getMaSV());
        txtHoTenGVLichHoc.setText(lstLichFull.get(count).getHoTenGV());
        txtHoTenSVLichHoc.setText(lstLichFull.get(count).getHoTenSv());
        cboMaMonHoc.setSelectedItem(lstLichFull.get(count).getMaMonHoc() + "-" + lstLichFull.get(count).getTenMonHoc());
        txtMonHocLichHoc.setText(lstLichFull.get(count).getTenMonHoc());
        cboLopHocHoc.setSelectedItem(lstLichFull.get(count).getMaLopHoc());
        cboNgayHoc.setSelectedItem(lstLichFull.get(count).getNgayHoc());
        cboPhongHoc.setSelectedItem(lstLichFull.get(count).getMaPhongHoc());
        cboThoiGianHoc.setSelectedItem(lstLichFull.get(count).getThoiGian());
    }

    public void clearOLichHoc() {
        txtMaGVLichHoc.setText(null);
        txtMaSVLichHoc.setText(null);
        txtHoTenGVLichHoc.setText(null);
        txtHoTenSVLichHoc.setText(null);
        cboMaMonHoc.setSelectedIndex(0);
        txtMonHocLichHoc.setText(null);
        cboLopHocHoc.setSelectedIndex(0);
        cboNgayHoc.setSelectedIndex(0);
        cboPhongHoc.setSelectedIndex(0);
        cboThoiGianHoc.setSelectedIndex(0);
    }

    public void clearOLichThi() {
        txtMaGVLichThi.setText(null);
        txtMaSVLichThi.setText(null);
        txtHoTenGVLichThi.setText(null);
        txtHoTenSVLichThi.setText(null);
        cboMaMonThi.setSelectedIndex(0);
        txtTenMonLichThi.setText(null);
        cboLopHocThi.setSelectedIndex(0);
        cboNgayThi.setSelectedIndex(0);
        cboPhongThi.setSelectedIndex(0);
        cboThoiGianThi.setSelectedIndex(0);
    }

    public void update(Lich lh, String maSv, String maMonHoc, String maLopHoc) {
        cbService.updateLich(lh, maSv, maMonHoc, maLopHoc);
    }

    public void findByTxtLichHoc(String maSv, String hoTenSv, String maMonHoc, String maLopHoc, String maPhongHoc, String maGiangVien, String hoTenGV, String tenMonHoc, Date ngayHoc, String thoiGian, String loaiLich) {
        ArrayList<LichFull> lstLichHocFulls = cbService.selectLichFullByMultipleCondition(maSv, hoTenSv, maMonHoc, maLopHoc, maPhongHoc, maGiangVien, hoTenGV, tenMonHoc, ngayHoc, thoiGian, loaiLich);
        DefaultTableModel modelOfLH = new DefaultTableModel();
        modelOfLH = (DefaultTableModel) tblLichHoc.getModel();
        modelOfLH.setRowCount(0);
        for (LichFull lichObj : lstLichHocFulls) {
            modelOfLH.addRow(new Object[]{lichObj.getMaSV(), lichObj.getHoTenSv(), lichObj.getMaGiangVien(), lichObj.getHoTenGV(), lichObj.getMaMonHoc(), lichObj.getTenMonHoc(), lichObj.getMaLopHoc(), lichObj.getNgayHoc(), lichObj.getMaPhongHoc(), lichObj.getThoiGian()});
        }
    }

     public void findByTxtLichThi(String maSv, String hoTenSv, String maMonHoc, String maLopHoc, String maPhongHoc, String maGiangVien, String hoTenGV, String tenMonHoc, Date ngayHoc, String thoiGian, String loaiLich) {
        ArrayList<LichFull> lstLichThiFulls = cbService.selectLichFullByMultipleCondition(maSv, hoTenSv, maMonHoc, maLopHoc, maPhongHoc, maGiangVien, hoTenGV, tenMonHoc, ngayHoc, thoiGian, loaiLich);
        DefaultTableModel modelOfLT = new DefaultTableModel();
        modelOfLT = (DefaultTableModel) tblLichThi.getModel();
        modelOfLT.setRowCount(0);
        for (LichFull lichObj : lstLichThiFulls) {
            modelOfLT.addRow(new Object[]{lichObj.getMaSV(), lichObj.getHoTenSv(), lichObj.getMaGiangVien(), lichObj.getHoTenGV(), lichObj.getMaMonHoc(), lichObj.getTenMonHoc(), lichObj.getMaLopHoc(), lichObj.getNgayHoc(), lichObj.getMaPhongHoc(), lichObj.getThoiGian()});
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLichThi = new javax.swing.JTable();
        txtTenMonLichThi = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtHoTenGVLichThi = new javax.swing.JTextField();
        btnSearchLichThi = new javax.swing.JButton();
        btnUpdateLichThi = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtMaSVLichThi = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtMaGVLichThi = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtHoTenSVLichThi = new javax.swing.JTextField();
        cboMaMonThi = new javax.swing.JComboBox<>();
        cboLopHocThi = new javax.swing.JComboBox<>();
        cboNgayThi = new javax.swing.JComboBox<>();
        cboPhongThi = new javax.swing.JComboBox<>();
        cboThoiGianThi = new javax.swing.JComboBox<>();
        btnClearLichThi = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMonHocLichHoc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLichHoc = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaGVLichHoc = new javax.swing.JTextField();
        btnSearchOnLichHoc = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cboMaMonHoc = new javax.swing.JComboBox<>();
        cboLopHocHoc = new javax.swing.JComboBox<>();
        cboPhongHoc = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtHoTenSVLichHoc = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtHoTenGVLichHoc = new javax.swing.JTextField();
        txtMaSVLichHoc = new javax.swing.JTextField();
        cboNgayHoc = new javax.swing.JComboBox<>();
        cboThoiGianHoc = new javax.swing.JComboBox<>();
        btnClearLichHoc = new javax.swing.JButton();

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 255));
        jLabel9.setText("QUẢN LÝ LỊCH THI");

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Mã môn");

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tên môn");

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Lớp");

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Phòng");

        tblLichThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SV", "Tên SV", "Mã GV", "Giảng Viên", "Mã  môn", "Tên môn", "Lớp", "Ngày", "Phòng", "Thời gian"
            }
        ));
        tblLichThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichThiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLichThi);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Ngày");

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Giảng viên");

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Thời gian");

        btnSearchLichThi.setBackground(new java.awt.Color(0, 102, 255));
        btnSearchLichThi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearchLichThi.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchLichThi.setText("Tìm kiếm");
        btnSearchLichThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchLichThiActionPerformed(evt);
            }
        });

        btnUpdateLichThi.setBackground(new java.awt.Color(0, 102, 255));
        btnUpdateLichThi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateLichThi.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateLichThi.setText("Sửa ");
        btnUpdateLichThi.setPreferredSize(new java.awt.Dimension(79, 23));
        btnUpdateLichThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateLichThiActionPerformed(evt);
            }
        });

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Mã SV");

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Mã GV");

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Sinh viên");

        txtHoTenSVLichThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenSVLichThiActionPerformed(evt);
            }
        });

        cboMaMonThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaMonThiActionPerformed(evt);
            }
        });

        cboNgayThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNgayThiActionPerformed(evt);
            }
        });

        btnClearLichThi.setBackground(new java.awt.Color(0, 102, 255));
        btnClearLichThi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClearLichThi.setForeground(new java.awt.Color(255, 255, 255));
        btnClearLichThi.setText("Làm mới");
        btnClearLichThi.setPreferredSize(new java.awt.Dimension(79, 23));
        btnClearLichThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearLichThiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(473, 473, 473))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboMaMonThi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboLopHocThi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboPhongThi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                        .addComponent(txtHoTenSVLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMaSVLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(192, 192, 192)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel21)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 497, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(21, 21, 21)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTenMonLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtHoTenGVLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaGVLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboThoiGianThi, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(89, 89, 89)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSearchLichThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdateLichThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClearLichThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(683, 683, 683))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSearchLichThi, btnUpdateLichThi});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboLopHocThi, cboMaMonThi, cboPhongThi, txtHoTenSVLichThi, txtMaSVLichThi});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboNgayThi, cboThoiGianThi, txtHoTenGVLichThi, txtMaGVLichThi, txtTenMonLichThi});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel9)
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearchLichThi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaSVLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)
                        .addComponent(jLabel20)
                        .addComponent(txtMaGVLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTenGVLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtHoTenSVLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(btnUpdateLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMaMonThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txtTenMonLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(cboLopHocThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cboPhongThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(cboThoiGianThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lịch thi", jPanel3);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("QUẢN LÝ LỊCH HỌC");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Mã môn");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tên môn");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Lóp");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Phòng");

        tblLichHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SV", "Tên SV", "Mã GV", "Tên GV", "Mã môn", "Tên môn", "Lớp", "Ngày", "Phòng", "Thời gian"
            }
        ));
        tblLichHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichHocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLichHoc);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Ngày");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Mã GV");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Thời gian");

        btnSearchOnLichHoc.setBackground(new java.awt.Color(0, 102, 255));
        btnSearchOnLichHoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearchOnLichHoc.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchOnLichHoc.setText("Tìm kiếm");
        btnSearchOnLichHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchOnLichHocActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Sửa");
        jButton3.setPreferredSize(new java.awt.Dimension(79, 23));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Mã SV");

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Sinh Viên");

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Giảng viên");

        btnClearLichHoc.setBackground(new java.awt.Color(0, 102, 255));
        btnClearLichHoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClearLichHoc.setForeground(new java.awt.Color(255, 255, 255));
        btnClearLichHoc.setText("Làm mới");
        btnClearLichHoc.setPreferredSize(new java.awt.Dimension(79, 23));
        btnClearLichHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearLichHocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel2))
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboPhongHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboLopHocHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboMaMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(205, 205, 205)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel8))
                                        .addGap(7, 7, 7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtHoTenSVLichHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaSVLichHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel19)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(489, 489, 489)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboThoiGianHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNgayHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtHoTenGVLichHoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMonHocLichHoc)
                                    .addComponent(txtMaGVLichHoc))
                                .addGap(93, 93, 93)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSearchOnLichHoc)
                                    .addComponent(btnClearLichHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(671, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClearLichHoc, btnSearchOnLichHoc, jButton3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboLopHocHoc, cboMaMonHoc, cboNgayHoc, cboPhongHoc, cboThoiGianHoc, txtHoTenGVLichHoc, txtHoTenSVLichHoc, txtMaGVLichHoc, txtMaSVLichHoc, txtMonHocLichHoc});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchOnLichHoc)
                    .addComponent(jLabel17)
                    .addComponent(jLabel7)
                    .addComponent(txtMaGVLichHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSVLichHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtHoTenSVLichHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtHoTenGVLichHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonHocLichHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(cboMaMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearLichHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(cboLopHocHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNgayHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(cboPhongHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboThoiGianHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClearLichHoc, btnSearchOnLichHoc, jButton3});

        jTabbedPane1.addTab("Lịch học", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed


    }//GEN-LAST:event_jButton3ActionPerformed

    private void cboMaMonThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaMonThiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMaMonThiActionPerformed

    private void txtHoTenSVLichThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenSVLichThiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenSVLichThiActionPerformed

    private void tblLichHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichHocMouseClicked
        // TODO add your handling code here:
        showDetailLichHoc();
    }//GEN-LAST:event_tblLichHocMouseClicked

    private void tblLichThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichThiMouseClicked
        // TODO add your handling code here:
        showDetailLichThi();
    }//GEN-LAST:event_tblLichThiMouseClicked

    private void btnClearLichThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearLichThiActionPerformed
        // TODO add your handling code here:
        clearOLichThi();
    }//GEN-LAST:event_btnClearLichThiActionPerformed

    private void btnUpdateLichThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateLichThiActionPerformed
        // TODO add your handling code here:
        count = tblLichThi.getSelectedRow();
        LichFull lichFull = lstLichFull.get(count);
        Lich lich = new Lich();
        lich = new Lich(lichFull.getMaSV(), lichFull.getMaMonHoc(), lichFull.getMaLopHoc(), String.valueOf(cboPhongHoc.getSelectedItem()), lichFull.getMaGiangVien(), lichFull.getMaChuyenNganh(), lstLichFull.get(count).getNam(), lichFull.getMua(), lichFull.getNgayHoc(), lichFull.getThoiGian(), lichFull.getLoaiLich());
        cbService.updateLich(lich, lstLichFull.get(count).getMaSV(), lstLichFull.get(count).getMaMonHoc(), lstLichFull.get(count).getMaLopHoc());
    }//GEN-LAST:event_btnUpdateLichThiActionPerformed

    private void cboNgayThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNgayThiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNgayThiActionPerformed

    private void btnSearchOnLichHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchOnLichHocActionPerformed
        // TODO add your handling code here:
        String maSv = null;
        String hotenSV = null;
        String maMonHoc = null;
        String maLopHoc = null;
        String maPhongHoc = null;
        String day = null;

        String maGv = null;
        String hoTenGV = null;
        String tenMonHoc = null;
        Date ngayHoc = null;
        String thoiGian = null;

        if (txtMaSVLichHoc.getText().equalsIgnoreCase(null)) {
            maSv = null;

        } else {
            maSv = txtMaSVLichHoc.getText();
        }
        if (txtHoTenSVLichHoc.getText().equalsIgnoreCase(null)) {
            hotenSV = null;
        } else {
            hotenSV = txtHoTenSVLichHoc.getText();
        }

        if (cboMaMonHoc.getSelectedIndex() != 0) {
            int count = cboMaMonHoc.getSelectedIndex();
            maMonHoc = lstMonHoc.get(count - 1).getMaMonHoc();
        } else {
            maMonHoc = null;
        }
        if (cboLopHocHoc.getSelectedIndex() != 0) {
            maLopHoc = cboLopHocHoc.getSelectedItem().toString();
        } else {
            maLopHoc = null;
        }

        if (cboPhongHoc.getSelectedIndex() != 0) {
            maPhongHoc = cboPhongHoc.getSelectedItem().toString();
        } else {
            maPhongHoc = null;
        }

        if (txtMaGVLichHoc.getText().equalsIgnoreCase(null)) {
            maGv = null;
        } else {
            maGv = txtMaGVLichHoc.getText();
        }

        if (txtHoTenGVLichHoc.getText().equalsIgnoreCase(null)) {
            hoTenGV = null;
        } else {
            hoTenGV = txtHoTenGVLichHoc.getText();
        }

        if (txtMonHocLichHoc.getText().equalsIgnoreCase(null)) {
            tenMonHoc = null;
        } else {
            tenMonHoc = txtMonHocLichHoc.getText();
        }

        if (cboNgayHoc.getSelectedIndex() != 0) {
            int count = cboNgayHoc.getSelectedIndex();
            ngayHoc = Date.valueOf(lstNgayHoc.get(count - 1));
        } else {
            ngayHoc = null;
        }
        if (cboThoiGianHoc.getSelectedIndex() != 0) {
            thoiGian = String.valueOf(cboThoiGianHoc.getSelectedItem());
        } else {
            thoiGian = null;
        }
        try {
            System.out.println("masv=" + maSv);
            System.out.println("day" + day);
//            System.out.println(maSv + hotenSV + maMonHoc + maLopHoc + maPhongHoc + maGv + hoTenGV + tenMonHoc + ngayHoc + thoiGian + "Lich Hoc");
            findByTxtLichHoc(maSv, hotenSV, maMonHoc, maLopHoc, maPhongHoc, maGv, hoTenGV, tenMonHoc, ngayHoc, thoiGian, "Lịch học");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchOnLichHocActionPerformed

    private void btnClearLichHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearLichHocActionPerformed
        // TODO add your handling code here:
        clearOLichHoc();
        loadToTblLichHocFaster();
    }//GEN-LAST:event_btnClearLichHocActionPerformed

    private void btnSearchLichThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchLichThiActionPerformed
        // TODO add your handling code here:
        String maSv = null;
        String hotenSV = null;
        String maMonHoc = null;
        String maLopHoc = null;
        String maPhongHoc = null;
        String day = null;

        String maGv = null;
        String hoTenGV = null;
        String tenMonHoc = null;
        Date ngayHoc = null;
        String thoiGian = null;

        if (txtMaSVLichThi.getText().equalsIgnoreCase(null)) {
            maSv = null;

        } else {
            maSv = txtMaSVLichThi.getText();
        }
        if (txtHoTenSVLichThi.getText().equalsIgnoreCase(null)) {
            hotenSV = null;
        } else {
            hotenSV = txtHoTenSVLichThi.getText();
        }

        if (cboMaMonThi.getSelectedIndex() != 0) {
            int count = cboMaMonThi.getSelectedIndex();
            maMonHoc = lstMonThi.get(count - 1).getMaMonHoc();
        } else {
            maMonHoc = null;
        }
        if (cboLopHocThi.getSelectedIndex() != 0) {
            maLopHoc = cboLopHocThi.getSelectedItem().toString();
        } else {
            maLopHoc = null;
        }

        if (cboPhongThi.getSelectedIndex() != 0) {
            maPhongHoc = cboPhongThi.getSelectedItem().toString();
        } else {
            maPhongHoc = null;
        }

        if (txtMaGVLichThi.getText().equalsIgnoreCase(null)) {
            maGv = null;
        } else {
            maGv = txtMaGVLichThi.getText();
        }

        if (txtHoTenGVLichThi.getText().equalsIgnoreCase(null)) {
            hoTenGV = null;
        } else {
            hoTenGV = txtHoTenGVLichThi.getText();
        }

        if (txtTenMonLichThi.getText().equalsIgnoreCase(null)) {
            tenMonHoc = null;
        } else {
            tenMonHoc = txtTenMonLichThi.getText();
        }

        if (cboNgayThi.getSelectedIndex() != 0) {
            int count = cboNgayThi.getSelectedIndex();
            ngayHoc = Date.valueOf(lstNgayThi.get(count - 1));
        } else {
            ngayHoc = null;
        }
        if (cboThoiGianThi.getSelectedIndex() != 0) {
            thoiGian = String.valueOf(cboThoiGianThi.getSelectedItem());
        } else {
            thoiGian = null;
        }
        try {
            System.out.println("masv=" + maSv);
            System.out.println("day" + day);
//            System.out.println(maSv + hotenSV + maMonHoc + maLopHoc + maPhongHoc + maGv + hoTenGV + tenMonHoc + ngayHoc + thoiGian + "Lich Hoc");
            findByTxtLichThi(maSv, hotenSV, maMonHoc, maLopHoc, maPhongHoc, maGv, hoTenGV, tenMonHoc, ngayHoc, thoiGian, "Lịch học");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchLichThiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearLichHoc;
    private javax.swing.JButton btnClearLichThi;
    private javax.swing.JButton btnSearchLichThi;
    private javax.swing.JButton btnSearchOnLichHoc;
    private javax.swing.JButton btnUpdateLichThi;
    private javax.swing.JComboBox<String> cboLopHocHoc;
    private javax.swing.JComboBox<String> cboLopHocThi;
    private javax.swing.JComboBox<String> cboMaMonHoc;
    private javax.swing.JComboBox<String> cboMaMonThi;
    private javax.swing.JComboBox<String> cboNgayHoc;
    private javax.swing.JComboBox<String> cboNgayThi;
    private javax.swing.JComboBox<String> cboPhongHoc;
    private javax.swing.JComboBox<String> cboPhongThi;
    private javax.swing.JComboBox<String> cboThoiGianHoc;
    private javax.swing.JComboBox<String> cboThoiGianThi;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblLichHoc;
    private javax.swing.JTable tblLichThi;
    private javax.swing.JTextField txtHoTenGVLichHoc;
    private javax.swing.JTextField txtHoTenGVLichThi;
    private javax.swing.JTextField txtHoTenSVLichHoc;
    private javax.swing.JTextField txtHoTenSVLichThi;
    private javax.swing.JTextField txtMaGVLichHoc;
    private javax.swing.JTextField txtMaGVLichThi;
    private javax.swing.JTextField txtMaSVLichHoc;
    private javax.swing.JTextField txtMaSVLichThi;
    private javax.swing.JTextField txtMonHocLichHoc;
    private javax.swing.JTextField txtTenMonLichThi;
    // End of variables declaration//GEN-END:variables
}
