package com.view.form_giangvien;

import com.view.main.MessageFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChuyenNganh;
import model.DiemThanhPhan;
import model.LopHoc;
import model.MonHoc;
import service.GiangVienService;
import ultilities.UserInfo;

/**
 *
 * @author Hello
 */
public class QuanLyDiemForm extends javax.swing.JPanel {

    GiangVienService giangVienService = new GiangVienService();
    DefaultTableModel model = new DefaultTableModel();
    int index;

    /**
     * Creates new form QuanLyDiemForm
     */
    public QuanLyDiemForm() {
        initComponents();

        Color ivory = new Color(255, 255, 255);
        tblNhapDiem.setOpaque(true);
        tblNhapDiem.setFillsViewportHeight(true);
        tblNhapDiem.setBackground(ivory);

//        loadComboboxChuyenNganh();
//        loadComboboxMonHoc();
//        loadComboboxLopHoc();
//        loadTableDiemThanhPhan();
        //Load combobox chuyên ngành theo năm
        cbbNamHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbNamHoc.getSelectedIndex() == 0) {
                    cbbMuaHoc.removeAllItems();
                    cbbChuyenNganh.removeAllItems();
                    cbbMonHoc.removeAllItems();
                    cbbLopHoc.removeAllItems();
                    model.setRowCount(0);
                } else if (cbbNamHoc.getSelectedIndex() == 1) {
                    cbbMuaHoc.removeAllItems();
                    cbbChuyenNganh.removeAllItems();
                    cbbMonHoc.removeAllItems();
                    cbbLopHoc.removeAllItems();
                    model.setRowCount(0);
                } else if (cbbNamHoc.getSelectedIndex() == 2) {
                    cbbMuaHoc.removeAllItems();
                    cbbMuaHoc.addItem("Spring");
                    cbbMuaHoc.addItem("Summer");
                    cbbMuaHoc.addItem("Fall");
                    cbbMuaHoc.addItem("Winter");
                }
            }
        });

        //Load combobox chuyên ngành theo mùa
        cbbMuaHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbMuaHoc.getSelectedIndex() == 0) {
                    List<ChuyenNganh> lstChuyenNganh = giangVienService.getChuyenNganhTheoMua("Spring");
                    if (lstChuyenNganh.isEmpty()) {
                        cbbChuyenNganh.removeAllItems();
                        cbbMonHoc.removeAllItems();
                        cbbLopHoc.removeAllItems();
                        model.setRowCount(0);
                    } else {
                        cbbChuyenNganh.removeAllItems();
                        for (ChuyenNganh chuyenNganh : lstChuyenNganh) {
                            cbbChuyenNganh.addItem(chuyenNganh + "");
                        }
                    }
                } else if (cbbMuaHoc.getSelectedIndex() == 1) {
                    List<ChuyenNganh> lstChuyenNganh = giangVienService.getChuyenNganhTheoMua("Summer");
                    if (lstChuyenNganh.isEmpty()) {
                        cbbChuyenNganh.removeAllItems();
                        cbbMonHoc.removeAllItems();
                        cbbLopHoc.removeAllItems();
                    } else {
                        cbbChuyenNganh.removeAllItems();
                        for (ChuyenNganh chuyenNganh : lstChuyenNganh) {
                            cbbChuyenNganh.addItem(chuyenNganh + "");
                        }
                    }
                } else if (cbbMuaHoc.getSelectedIndex() == 2) {
                    List<ChuyenNganh> lstChuyenNganh = giangVienService.getChuyenNganhTheoMua("Fall");
                    if (lstChuyenNganh.isEmpty()) {
                        cbbChuyenNganh.removeAllItems();
                        cbbMonHoc.removeAllItems();
                        cbbLopHoc.removeAllItems();
                        model.setRowCount(0);
                    } else {
                        cbbChuyenNganh.removeAllItems();
                        for (ChuyenNganh chuyenNganh : lstChuyenNganh) {
                            cbbChuyenNganh.addItem(chuyenNganh + "");
                        }
                    }
                } else if (cbbMuaHoc.getSelectedIndex() == 3) {
                    List<ChuyenNganh> lstChuyenNganh = giangVienService.getChuyenNganhTheoMua("Winter");
                    if (lstChuyenNganh.isEmpty()) {
                        cbbChuyenNganh.removeAllItems();
                        cbbMonHoc.removeAllItems();
                        cbbLopHoc.removeAllItems();
                        model.setRowCount(0);
                    } else {
                        cbbChuyenNganh.removeAllItems();
                        for (ChuyenNganh chuyenNganh : lstChuyenNganh) {
                            cbbChuyenNganh.addItem(chuyenNganh + "");
                        }
                    }
                }
            }
        });

        //Sử lý load combobox môn học theo chuyên ngành
        cbbChuyenNganh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbChuyenNganh.getSelectedIndex() == 0) {
                    List<MonHoc> lstMonHoc = giangVienService.getMonHocTheoChuyenNganh("LTW");
                    cbbMonHoc.removeAllItems();
                    for (MonHoc monHoc : lstMonHoc) {
                        cbbMonHoc.addItem(monHoc + "");
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 1) {
                    List<MonHoc> lstMonHoc = giangVienService.getMonHocTheoChuyenNganh("PTPM");
                    cbbMonHoc.removeAllItems();
                    for (MonHoc monHoc : lstMonHoc) {
                        cbbMonHoc.addItem(monHoc + "");
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 2) {
                    List<MonHoc> lstMonHoc = giangVienService.getMonHocTheoChuyenNganh("TDH");
                    cbbMonHoc.removeAllItems();
                    for (MonHoc monHoc : lstMonHoc) {
                        cbbMonHoc.addItem(monHoc + "");
                    }
                }
            }
        });

        //Load combobox lớp học theo môn học
        cbbMonHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbChuyenNganh.getSelectedIndex() == 0 && cbbMonHoc.getSelectedIndex() == 0) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH07");
                    cbbLopHoc.removeAllItems();
                    for (LopHoc lopHoc : lstLopHoc) {
                        cbbLopHoc.addItem(lopHoc + "");
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 0 && cbbMonHoc.getSelectedIndex() == 1) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH08");
                    cbbLopHoc.removeAllItems();
                    for (LopHoc lopHoc : lstLopHoc) {
                        cbbLopHoc.addItem(lopHoc + "");
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 0 && cbbMonHoc.getSelectedIndex() == 2) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH09");
                    cbbLopHoc.removeAllItems();
                    for (LopHoc lopHoc : lstLopHoc) {
                        cbbLopHoc.addItem(lopHoc + "");
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 0) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH01");
                    cbbLopHoc.removeAllItems();
                    for (LopHoc lopHoc : lstLopHoc) {
                        cbbLopHoc.addItem(lopHoc + "");
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 1) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH02");
                    cbbLopHoc.removeAllItems();
                    for (LopHoc lopHoc : lstLopHoc) {
                        cbbLopHoc.addItem(lopHoc + "");
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 2) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH03");
                    cbbLopHoc.removeAllItems();
                    for (LopHoc lopHoc : lstLopHoc) {
                        cbbLopHoc.addItem(lopHoc + "");
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 2 && cbbMonHoc.getSelectedIndex() == 0) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH04");
                    cbbLopHoc.removeAllItems();
                    for (LopHoc lopHoc : lstLopHoc) {
                        cbbLopHoc.addItem(lopHoc + "");
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 2 && cbbMonHoc.getSelectedIndex() == 1) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH05");
                    cbbLopHoc.removeAllItems();
                    for (LopHoc lopHoc : lstLopHoc) {
                        cbbLopHoc.addItem(lopHoc + "");
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 2 && cbbMonHoc.getSelectedIndex() == 2) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH06");
                    cbbLopHoc.removeAllItems();
                    for (LopHoc lopHoc : lstLopHoc) {
                        cbbLopHoc.addItem(lopHoc + "");
                    }
                }
            }
        });

        cbbLopHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbChuyenNganh.getSelectedIndex() == 0 && cbbMonHoc.getSelectedIndex() == 0) {
                    loadTableDiemTheoMaLH_MaGV("MH07", "LH09");
                    UserInfo.maMH = "MH07";
                    UserInfo.maLH = "LH09";
                } else if (cbbChuyenNganh.getSelectedIndex() == 0 && cbbMonHoc.getSelectedIndex() == 1) {
                    loadTableDiemTheoMaLH_MaGV("MH08", "LH08");
                    UserInfo.maMH = "MH08";
                    UserInfo.maLH = "LH08";
                } else if (cbbChuyenNganh.getSelectedIndex() == 0 && cbbMonHoc.getSelectedIndex() == 2) {
                    loadTableDiemTheoMaLH_MaGV("MH09", "LH07");
                    UserInfo.maMH = "MH09";
                    UserInfo.maLH = "LH07";
                } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 0) {
                    if (cbbLopHoc.getSelectedIndex() == 0) {
                        loadTableDiemTheoMaLH_MaGV("MH01","LH02");
                        UserInfo.maMH = "MH01";
                        UserInfo.maLH = "LH02";
                    } else if (cbbLopHoc.getSelectedIndex() == 1) {
                        loadTableDiemTheoMaLH_MaGV("MH01", "LH03");
                        UserInfo.maMH = "MH01";
                        UserInfo.maLH = "LH03";
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 1) {
                    if (cbbLopHoc.getSelectedIndex() == 0) {
                        loadTableDiemTheoMaLH_MaGV("MH02", "LH01");
                        UserInfo.maMH = "MH02";
                        UserInfo.maLH = "LH01";
                    } else if (cbbLopHoc.getSelectedIndex() == 1) {
                        loadTableDiemTheoMaLH_MaGV("MH02", "LH02");
                        UserInfo.maMH = "MH02";
                        UserInfo.maLH = "LH02";
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 2) {
                    if (cbbLopHoc.getSelectedIndex() == 0) {
                        loadTableDiemTheoMaLH_MaGV("MH03", "LH01");
                        UserInfo.maMH = "MH03";
                        UserInfo.maLH = "LH01";
                    } else if (cbbLopHoc.getSelectedIndex() == 1) {
                        loadTableDiemTheoMaLH_MaGV("MH03", "LH02");
                        UserInfo.maMH = "MH03";
                        UserInfo.maLH = "LH02";
                    }
                } else if (cbbChuyenNganh.getSelectedIndex() == 2 && cbbMonHoc.getSelectedIndex() == 0) {
                    loadTableDiemTheoMaLH_MaGV("MH04", "LH06");
                    UserInfo.maMH = "MH04";
                    UserInfo.maLH = "LH06";
                } else if (cbbChuyenNganh.getSelectedIndex() == 2 && cbbMonHoc.getSelectedIndex() == 1) {
                    loadTableDiemTheoMaLH_MaGV("MH05", "LH05");
                    UserInfo.maMH = "MH05";
                    UserInfo.maLH = "LH05";
                } else if (cbbChuyenNganh.getSelectedIndex() == 2 && cbbMonHoc.getSelectedIndex() == 2) {
                    loadTableDiemTheoMaLH_MaGV("MH06", "LH04");
                    UserInfo.maMH = "MH06";
                    UserInfo.maLH = "LH04";
                }
            }
        });
    }

    private void loadTableDiemTheoMaLH_MaGV(String maMH, String maLH) {
        List<DiemThanhPhan> lstDiem = giangVienService.getDiemTheoMaLH_MaGV(maMH, maLH, UserInfo.maGV);
        model = (DefaultTableModel) tblNhapDiem.getModel();
        model.setRowCount(0);
        for (DiemThanhPhan diem : lstDiem) {
            model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
        }
    }

    //Load chuyên ngành lên combobox
    private void loadComboboxChuyenNganh() {
        List<ChuyenNganh> lstChuyenNganh = giangVienService.getAllChuyenNganh();
        cbbChuyenNganh.removeAllItems();
        for (ChuyenNganh chuyenNganh : lstChuyenNganh) {
            cbbChuyenNganh.addItem(chuyenNganh + "");
        }
    }

    //Load môn học lên combobox
    private void loadComboboxMonHoc() {
        List<MonHoc> lstMonHoc = giangVienService.getAllMonHoc();
        cbbMonHoc.removeAllItems();
        for (MonHoc monHoc : lstMonHoc) {
            cbbMonHoc.addItem(monHoc + "");
        }
    }

    //Load lớp học lên combobox
    private void loadComboboxLopHoc() {
        List<LopHoc> lstLopHoc = giangVienService.getAllLopHoc();
        cbbLopHoc.removeAllItems();
        for (LopHoc lopHoc : lstLopHoc) {
            cbbLopHoc.addItem(lopHoc + "");
        }
    }

    //Load điểm lên table
    private void loadTableDiemThanhPhan() {
        List<DiemThanhPhan> lstDiem = giangVienService.getDiemThanhPhan();
        if (lstDiem == null) {
            JOptionPane.showMessageDialog(this, "Lỗi!");
        } else if (lstDiem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu!");
        } else {
            model = (DefaultTableModel) tblNhapDiem.getModel();
            model.setRowCount(0);
            for (DiemThanhPhan diem : lstDiem) {
                model.addRow(new Object[]{
                    diem.getMaSV(),
                    diem.getLab1(),
                    diem.getLab2(),
                    diem.getLab3(),
                    diem.getLab4(),
                    diem.getAssignment(),
                    diem.getDiemThi(),
                    diem.getDiemTongKet(),
                    diem.getTrangThai()});
            }
        }
    }

    private void showDeTail() {
        index = tblNhapDiem.getSelectedRow();
        String maSV = (String) tblNhapDiem.getValueAt(index, 0);
        Double lab1 = (Double) tblNhapDiem.getValueAt(index, 1);
        Double lab2 = (Double) tblNhapDiem.getValueAt(index, 2);
        Double lab3 = (Double) tblNhapDiem.getValueAt(index, 3);
        Double lab4 = (Double) tblNhapDiem.getValueAt(index, 4);
        Double assignment = (Double) tblNhapDiem.getValueAt(index, 5);
        Double diemThi = (Double) tblNhapDiem.getValueAt(index, 6);

        txtMaSV.setText(maSV);
        txtLab1.setText(lab1 + "");
        txtLab2.setText(lab2 + "");
        txtLab3.setText(lab3 + "");
        txtLab4.setText(lab4 + "");
        txtAssignment.setText(assignment + "");
        txtDiemThi.setText(diemThi + "");
    }

    private void clearForm() {
        txtMaSV.setText("");
        txtLab1.setText("");
        txtLab2.setText("");
        txtLab3.setText("");
        txtLab4.setText("");
        txtAssignment.setText("");
        txtDiemThi.setText("");
        tblNhapDiem.clearSelection();
    }

    public DiemThanhPhan addDiem() {
        try {
            String maSV = txtMaSV.getText();
            Double lab1 = Double.parseDouble(txtLab1.getText());
            Double lab2 = Double.parseDouble(txtLab2.getText());
            Double lab3 = Double.parseDouble(txtLab3.getText());
            Double lab4 = Double.parseDouble(txtLab4.getText());
            Double assignment = Double.parseDouble(txtAssignment.getText());
            Double diemThi = Double.parseDouble(txtDiemThi.getText());

            DiemThanhPhan diem = new DiemThanhPhan();
            diem.setMaSV(maSV);
            diem.setLab1(lab1);
            diem.setLab2(lab2);
            diem.setLab3(lab3);
            diem.setLab4(lab4);
            diem.setAssignment(assignment);
            diem.setDiemThi(diemThi);

            return diem;
        } catch (Exception e) {
            return null;
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
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblMaSinhVien = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        lblChuyenNganh = new javax.swing.JLabel();
        lblMonHoc = new javax.swing.JLabel();
        lblLopHoc = new javax.swing.JLabel();
        lblMuaHoc = new javax.swing.JLabel();
        cbbChuyenNganh = new javax.swing.JComboBox<>();
        cbbMuaHoc = new javax.swing.JComboBox<>();
        cbbMonHoc = new javax.swing.JComboBox<>();
        cbbLopHoc = new javax.swing.JComboBox<>();
        btnTim = new javax.swing.JButton();
        lblNamHoc = new javax.swing.JLabel();
        cbbNamHoc = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lblLab1 = new javax.swing.JLabel();
        txtLab1 = new javax.swing.JTextField();
        lblLab2 = new javax.swing.JLabel();
        txtLab2 = new javax.swing.JTextField();
        lblLab3 = new javax.swing.JLabel();
        txtLab3 = new javax.swing.JTextField();
        lblLab4 = new javax.swing.JLabel();
        txtLab4 = new javax.swing.JTextField();
        txtAssignment = new javax.swing.JTextField();
        lblAssignment = new javax.swing.JLabel();
        lblDiemThi = new javax.swing.JLabel();
        txtDiemThi = new javax.swing.JTextField();
        btnNhapDiem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnSuaDiem = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhapDiem = new javax.swing.JTable();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lblMaSinhVien.setText("Mã sinh viên: ");

        lblChuyenNganh.setText("Chuyên ngành: ");

        lblMonHoc.setText("Môn học");

        lblLopHoc.setText("Lớp học: ");

        lblMuaHoc.setText("Mùa học: ");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        lblNamHoc.setText("Năm học: ");

        cbbNamHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblMonHoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addComponent(lblLopHoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addComponent(lblMuaHoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblChuyenNganh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNamHoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cbbChuyenNganh, 0, 193, Short.MAX_VALUE)
                        .addComponent(cbbMuaHoc, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbLopHoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbNamHoc, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTim)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblChuyenNganh, lblLopHoc, lblMaSinhVien, lblMonHoc});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbbChuyenNganh, cbbLopHoc, cbbMonHoc, cbbMuaHoc, cbbNamHoc});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNamHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cbbNamHoc)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbMuaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMuaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChuyenNganh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbChuyenNganh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblChuyenNganh, lblLopHoc, lblMaSinhVien, lblMonHoc, lblMuaHoc, lblNamHoc});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnTim, cbbChuyenNganh, cbbLopHoc, cbbMonHoc, cbbMuaHoc, cbbNamHoc, txtMaSV});

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel10.setText("Nhập điểm: ");

        lblLab1.setText("Lab 1:");

        lblLab2.setText("Lab 2: ");

        lblLab3.setText("Lab 3: ");

        lblLab4.setText("Lab 4: ");

        txtLab4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLab4ActionPerformed(evt);
            }
        });

        lblAssignment.setText("Assignment: ");

        lblDiemThi.setText("Điểm thi: ");

        btnNhapDiem.setText("Nhập điểm");
        btnNhapDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapDiemActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnSuaDiem.setText("Sửa điểm");
        btnSuaDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNhapDiem)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblAssignment, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLab3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLab1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLab2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLab4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDiemThi, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLab1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLab4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAssignment)
                            .addComponent(txtDiemThi)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnSuaDiem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLamMoi)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(txtLab3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtLab2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblAssignment, lblDiemThi, lblLab1, lblLab2, lblLab3, lblLab4});

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi, btnNhapDiem, btnSuaDiem});

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAssignment, txtDiemThi, txtLab1, txtLab2, txtLab3, txtLab4});

        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtLab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(txtLab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLab4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAssignment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblLab1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLab2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLab3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLab4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAssignment, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDiemThi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiemThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhapDiem)
                    .addComponent(btnLamMoi)
                    .addComponent(btnSuaDiem))
                .addGap(45, 45, 45))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblAssignment, lblDiemThi, lblLab1, lblLab2, lblLab3, lblLab4, txtAssignment, txtDiemThi, txtLab1, txtLab2, txtLab3, txtLab4});

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoi, btnNhapDiem, btnSuaDiem});

        tblNhapDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sinh viên", "Lab 1", "Lab 2", "Lab 3", "Lab 4", "Assignment", "Điểm thi", "Điểm tổng kết", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhapDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhapDiemMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhapDiem);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nhập điểm", jPanel1);

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

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // clear form
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnNhapDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapDiemActionPerformed
        // Nhập điểm sinhn viên
        index = tblNhapDiem.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "vui lòng chọn sinh viên cần nhập điểm!");
            return;
        }
        MessageFrame mess = new MessageFrame();
        mess.show();
        mess.setMessage("Bạn có chắc chắn muốn nhập không?");
        mess.setButtonOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mess.dispose();
                DiemThanhPhan diem = addDiem();
                int ketQua = giangVienService.nhapDiem(diem);
                if (ketQua > -1) {
                    JOptionPane.showMessageDialog(null, "Nhập điểm thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Nhập điểm thất bại!");
                }
                loadTableDiemTheoMaLH_MaGV(UserInfo.maMH, UserInfo.maLH);
            }
        });
        UserInfo.maLH = null;

        mess.setButtonCancel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mess.dispose();
            }
        });
    }//GEN-LAST:event_btnNhapDiemActionPerformed

    private void txtLab4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLab4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLab4ActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // Tìm sinh viên theo mã
        String maSV = txtMaSV.getText();
        if (cbbChuyenNganh.getSelectedIndex() == 0 && cbbMonHoc.getSelectedIndex() == 0 && cbbLopHoc.getSelectedIndex() == 0) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "LTW", "MH07", "LH09");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        } else if (cbbChuyenNganh.getSelectedIndex() == 0 && cbbMonHoc.getSelectedIndex() == 1 && cbbLopHoc.getSelectedIndex() == 0) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "LTW", "MH08", "LH08");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        } else if (cbbChuyenNganh.getSelectedIndex() == 0 && cbbMonHoc.getSelectedIndex() == 2 && cbbLopHoc.getSelectedIndex() == 0) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "LTW", "MH09", "LH07");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 0 && cbbLopHoc.getSelectedIndex() == 0) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "PTPM", "MH01", "LH02");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 0 && cbbLopHoc.getSelectedIndex() == 1) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "PTPM", "MH01", "LH03");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 1 && cbbLopHoc.getSelectedIndex() == 0) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "PTPM", "MH02", "LH01");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 1 && cbbLopHoc.getSelectedIndex() == 1) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "PTPM", "MH02", "LH02");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 2 && cbbLopHoc.getSelectedIndex() == 0) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "PTPM", "MH03", "LH01");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        } else if (cbbChuyenNganh.getSelectedIndex() == 1 && cbbMonHoc.getSelectedIndex() == 2 && cbbLopHoc.getSelectedIndex() == 1) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "PTPM", "MH03", "LH02");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        } else if (cbbChuyenNganh.getSelectedIndex() == 2 && cbbMonHoc.getSelectedIndex() == 0 && cbbLopHoc.getSelectedIndex() == 0) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "TDH", "MH04", "LH06");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        } else if (cbbChuyenNganh.getSelectedIndex() == 2 && cbbMonHoc.getSelectedIndex() == 1 && cbbLopHoc.getSelectedIndex() == 0) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "TDH", "MH05", "LH05");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        } else if (cbbChuyenNganh.getSelectedIndex() == 2 && cbbMonHoc.getSelectedIndex() == 2 && cbbLopHoc.getSelectedIndex() == 0) {
            List<DiemThanhPhan> lstDiem = giangVienService.getSinhVienDeNhapDiem(maSV, "TDH", "MH06", "LH04");
            if (lstDiem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
                return;
            } else {
                model = (DefaultTableModel) tblNhapDiem.getModel();
                model.setRowCount(0);
                for (DiemThanhPhan diem : lstDiem) {
                    model.addRow(new Object[]{diem.getMaSV(), diem.getLab1(), diem.getLab2(), diem.getLab3(), diem.getLab4(), diem.getAssignment(), diem.getDiemThi(), diem.getDiemTongKet(), diem.getTrangThai()});
                }
            }
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void tblNhapDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhapDiemMouseClicked
        // showDetail điểm
        showDeTail();
    }//GEN-LAST:event_tblNhapDiemMouseClicked

    private void btnSuaDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDiemActionPerformed
        // Sửa điểm
        index = tblNhapDiem.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên cần sửa điểm!");
            return;
        }
        MessageFrame mess = new MessageFrame();
        mess.show();
        mess.setMessage("Bạn có chắc chắn muốn sửa không?");
        mess.setButtonOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mess.dispose();
                DiemThanhPhan diem = addDiem();
                int ketQua = giangVienService.nhapDiem(diem);
                if (ketQua > -1) {
                    JOptionPane.showMessageDialog(null, "Sửa điểm thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Sửa điểm thất bại!");
                }
                loadTableDiemTheoMaLH_MaGV(UserInfo.maMH, UserInfo.maLH);
            }
        });
        UserInfo.maLH = null;

        mess.setButtonCancel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mess.dispose();
            }
        });
    }//GEN-LAST:event_btnSuaDiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnNhapDiem;
    private javax.swing.JButton btnSuaDiem;
    private javax.swing.JButton btnTim;
    private javax.swing.JComboBox<String> cbbChuyenNganh;
    private javax.swing.JComboBox<String> cbbLopHoc;
    private javax.swing.JComboBox<String> cbbMonHoc;
    private javax.swing.JComboBox<String> cbbMuaHoc;
    private javax.swing.JComboBox<String> cbbNamHoc;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAssignment;
    private javax.swing.JLabel lblChuyenNganh;
    private javax.swing.JLabel lblDiemThi;
    private javax.swing.JLabel lblLab1;
    private javax.swing.JLabel lblLab2;
    private javax.swing.JLabel lblLab3;
    private javax.swing.JLabel lblLab4;
    private javax.swing.JLabel lblLopHoc;
    private javax.swing.JLabel lblMaSinhVien;
    private javax.swing.JLabel lblMonHoc;
    private javax.swing.JLabel lblMuaHoc;
    private javax.swing.JLabel lblNamHoc;
    private javax.swing.JTable tblNhapDiem;
    private javax.swing.JTextField txtAssignment;
    private javax.swing.JTextField txtDiemThi;
    private javax.swing.JTextField txtLab1;
    private javax.swing.JTextField txtLab2;
    private javax.swing.JTextField txtLab3;
    private javax.swing.JTextField txtLab4;
    private javax.swing.JTextField txtMaSV;
    // End of variables declaration//GEN-END:variables
}
