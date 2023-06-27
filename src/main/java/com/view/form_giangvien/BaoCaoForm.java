package com.view.form_giangvien;

import com.view.main.MessageFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BaoCao;
import model.LopHoc;
import model.MonHoc;
import service.GiangVienService;
import ultilities.UserInfo;

/**
 *
 * @author Hello
 */
public class BaoCaoForm extends javax.swing.JPanel {

    DefaultTableModel model = new DefaultTableModel();
    DefaultComboBoxModel<MonHoc> modelMonHoc = new DefaultComboBoxModel<MonHoc>();
    DefaultComboBoxModel<LopHoc> modelLopHoc = new DefaultComboBoxModel<LopHoc>();
    GiangVienService giangVienService = new GiangVienService();

    /**
     * Creates new form BaoCaoForm
     */
    public BaoCaoForm() {
        initComponents();

        Color ivory = new Color(255, 255, 255);
        tblLichSuBaoCao.setOpaque(true);
        tblLichSuBaoCao.setFillsViewportHeight(true);
        tblLichSuBaoCao.setBackground(ivory);

        loadComboboxMonHoc();

        cbbMonHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbMonHoc.getSelectedIndex() == 0) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH01");
                    cbbLopHoc.setModel((ComboBoxModel) modelLopHoc);
                    modelLopHoc.removeAllElements();
                    for (LopHoc lopHoc : lstLopHoc) {
                        modelLopHoc.addElement(lopHoc);
                    }
                } else if (cbbMonHoc.getSelectedIndex() == 1) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH02");
                    cbbLopHoc.setModel((ComboBoxModel) modelLopHoc);
                    modelLopHoc.removeAllElements();
                    for (LopHoc lopHoc : lstLopHoc) {
                        modelLopHoc.addElement(lopHoc);
                    }
                } else if (cbbMonHoc.getSelectedIndex() == 2) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH03");
                    cbbLopHoc.setModel((ComboBoxModel) modelLopHoc);
                    modelLopHoc.removeAllElements();
                    for (LopHoc lopHoc : lstLopHoc) {
                        modelLopHoc.addElement(lopHoc);
                    }
                } else if (cbbMonHoc.getSelectedIndex() == 3) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH04");
                    cbbLopHoc.setModel((ComboBoxModel) modelLopHoc);
                    modelLopHoc.removeAllElements();
                    for (LopHoc lopHoc : lstLopHoc) {
                        modelLopHoc.addElement(lopHoc);
                    }
                } else if (cbbMonHoc.getSelectedIndex() == 4) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH05");
                    cbbLopHoc.setModel((ComboBoxModel) modelLopHoc);
                    modelLopHoc.removeAllElements();
                    for (LopHoc lopHoc : lstLopHoc) {
                        modelLopHoc.addElement(lopHoc);
                    }
                } else if (cbbMonHoc.getSelectedIndex() == 5) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH06");
                    cbbLopHoc.setModel((ComboBoxModel) modelLopHoc);
                    modelLopHoc.removeAllElements();
                    for (LopHoc lopHoc : lstLopHoc) {
                        modelLopHoc.addElement(lopHoc);
                    }
                } else if (cbbMonHoc.getSelectedIndex() == 6) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH07");
                    cbbLopHoc.setModel((ComboBoxModel) modelLopHoc);
                    modelLopHoc.removeAllElements();
                    for (LopHoc lopHoc : lstLopHoc) {
                        modelLopHoc.addElement(lopHoc);
                    }
                } else if (cbbMonHoc.getSelectedIndex() == 7) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH08");
                    cbbLopHoc.setModel((ComboBoxModel) modelLopHoc);
                    modelLopHoc.removeAllElements();
                    for (LopHoc lopHoc : lstLopHoc) {
                        modelLopHoc.addElement(lopHoc);
                    }
                } else if (cbbMonHoc.getSelectedIndex() == 8) {
                    List<LopHoc> lstLopHoc = giangVienService.getLopHocTheoMonHoc("MH09");
                    cbbLopHoc.setModel((ComboBoxModel) modelLopHoc);
                    modelLopHoc.removeAllElements();
                    for (LopHoc lopHoc : lstLopHoc) {
                        modelLopHoc.addElement(lopHoc);
                    }
                }
            }
        });
        
        LoadTableLichSuBaoCao();
    }

    private void LoadTableLichSuBaoCao() {
        List<BaoCao> lstBaoCao = giangVienService.getLichSuBaoCaoTheoMaGV(UserInfo.maGV);
        model = (DefaultTableModel) tblLichSuBaoCao.getModel();
        model.setRowCount(0);
        int count = 1;
        for (BaoCao baoCao : lstBaoCao) {
            model.addRow(new Object[]{count++, baoCao.getMaGV(), baoCao.getMaMonHoc(), baoCao.getMaLopHoc(), baoCao.getTieuDe(), baoCao.getNoiDung()});
        }
    }

    private void loadComboboxMonHoc() {
        List<MonHoc> lstMonHoc = giangVienService.getAllMonHoc();
        cbbMonHoc.setModel((ComboBoxModel) modelMonHoc);
        modelMonHoc.removeAllElements();
        for (MonHoc monHoc : lstMonHoc) {
            modelMonHoc.addElement(monHoc);
        }
    }

    private void ClearForm() {
        txtMaGV.setText("");
        txtTieuDe.setText("");
        txtNoiDung.setText("");
        cbbMonHoc.setSelectedIndex(0);
        cbbLopHoc.setSelectedIndex(0);
    }

    private BaoCao addBaoCao() {
        String maGV = txtMaGV.getText();
        String tieuDe = txtTieuDe.getText();
        String noiDung = txtNoiDung.getText();

        BaoCao baoCao = new BaoCao();
        baoCao.setMaGV(maGV);
        baoCao.setTieuDe(tieuDe);
        baoCao.setNoiDung(noiDung);
        if (cbbMonHoc.getSelectedIndex() == 0 && cbbLopHoc.getSelectedIndex() == 0) {
            baoCao.setMaMonHoc("MH01");
            baoCao.setMaLopHoc("LH02");
        } else if (cbbMonHoc.getSelectedIndex() == 0 && cbbLopHoc.getSelectedIndex() == 1) {
            baoCao.setMaMonHoc("MH01");
            baoCao.setMaLopHoc("LH03");
        } else if (cbbMonHoc.getSelectedIndex() == 1 && cbbLopHoc.getSelectedIndex() == 0) {
            baoCao.setMaMonHoc("MH02");
            baoCao.setMaLopHoc("LH01");
        } else if (cbbMonHoc.getSelectedIndex() == 1 && cbbLopHoc.getSelectedIndex() == 1) {
            baoCao.setMaMonHoc("MH02");
            baoCao.setMaLopHoc("LH02");
        } else if (cbbMonHoc.getSelectedIndex() == 2 && cbbLopHoc.getSelectedIndex() == 0) {
            baoCao.setMaMonHoc("MH03");
            baoCao.setMaLopHoc("LH01");
        } else if (cbbMonHoc.getSelectedIndex() == 2 && cbbLopHoc.getSelectedIndex() == 1) {
            baoCao.setMaMonHoc("MH03");
            baoCao.setMaLopHoc("LH02");
        } else if (cbbMonHoc.getSelectedIndex() == 3 && cbbLopHoc.getSelectedIndex() == 0) {
            baoCao.setMaMonHoc("MH04");
            baoCao.setMaLopHoc("LH06");
        } else if (cbbMonHoc.getSelectedIndex() == 4 && cbbLopHoc.getSelectedIndex() == 0) {
            baoCao.setMaMonHoc("MH05");
            baoCao.setMaLopHoc("LH05");
        } else if (cbbMonHoc.getSelectedIndex() == 5 && cbbLopHoc.getSelectedIndex() == 0) {
            baoCao.setMaMonHoc("MH06");
            baoCao.setMaLopHoc("LH04");
        } else if (cbbMonHoc.getSelectedIndex() == 6 && cbbLopHoc.getSelectedIndex() == 0) {
            baoCao.setMaMonHoc("MH07");
            baoCao.setMaLopHoc("LH09");
        } else if (cbbMonHoc.getSelectedIndex() == 7 && cbbLopHoc.getSelectedIndex() == 0) {
            baoCao.setMaMonHoc("MH08");
            baoCao.setMaLopHoc("LH08");
        } else if (cbbMonHoc.getSelectedIndex() == 8 && cbbLopHoc.getSelectedIndex() == 0) {
            baoCao.setMaMonHoc("MH09");
            baoCao.setMaLopHoc("LH07");
        }

        return baoCao;
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
        lblMaGV = new javax.swing.JLabel();
        lblMonHoc = new javax.swing.JLabel();
        txtMaGV = new javax.swing.JTextField();
        lblLopHoc = new javax.swing.JLabel();
        cbbMonHoc = new javax.swing.JComboBox<>();
        cbbLopHoc = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnGui = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JTextArea();
        lblNoiDung = new javax.swing.JLabel();
        lblTieuDe = new javax.swing.JLabel();
        txtTieuDe = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lblLichSuBaoCao = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLichSuBaoCao = new javax.swing.JTable();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lblMaGV.setText("Mã giảng viên: ");

        lblMonHoc.setText("Môn dạy: ");

        lblLopHoc.setText("Lớp dạy: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblMonHoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMonHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(248, 248, 248))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblLopHoc, lblMaGV, lblMonHoc});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMonHoc)
                    .addComponent(cbbMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLopHoc)
                    .addComponent(cbbLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblLopHoc, lblMaGV, lblMonHoc});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbLopHoc, cbbMonHoc, txtMaGV});

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnGui.setText("Gửi");
        btnGui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtNoiDung.setColumns(20);
        txtNoiDung.setRows(5);
        jScrollPane4.setViewportView(txtNoiDung);

        lblNoiDung.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblNoiDung.setText("Nội dung: ");

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTieuDe.setText("Tiêu đề: ");

        txtTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(239, 239, 239)
                            .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTieuDe))
                    .addComponent(lblNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnGui, btnLamMoi});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(lblNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi))
                .addGap(62, 62, 62))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnGui, btnLamMoi});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblTieuDe, txtTieuDe});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Báo cáo", jPanel1);

        lblLichSuBaoCao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLichSuBaoCao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLichSuBaoCao.setText("Lịch sử báo cáo");

        tblLichSuBaoCao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã giảng viên", "Môn", "Lớp", "Tiêu đề ", "Nội dung"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblLichSuBaoCao);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLichSuBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(343, 343, 343))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblLichSuBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Lịch sử báo cáo", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // clearForm
        ClearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnGuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiActionPerformed
        // gửi báo cáo
        if (txtMaGV.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mã giảng viên!");
            txtMaGV.requestFocus();
            return;
        }
        if (txtTieuDe.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mã giảng viên!");
            txtTieuDe.requestFocus();
            return;
        }
        if (txtNoiDung.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mã giảng viên!");
            txtNoiDung.requestFocus();
            return;
        }
        BaoCao baoCao = addBaoCao();
        int ketQua = giangVienService.GuiBaoCao(baoCao);
        if (ketQua > -1) {
            MessageFrame mess = new MessageFrame();
            mess.show();
            mess.setMessage("Gửi báo cáo thành công");
            mess.setButtonCancel(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mess.dispose();
                }
            });
            mess.setButtonOK(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mess.dispose();
                }
            });
        } else {
            MessageFrame mess = new MessageFrame();
            mess.show();
            mess.setMessage("Gửi báo cáo thất bại!");
            mess.setButtonCancel(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mess.dispose();
                }
            });
            mess.setButtonOK(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mess.dispose();
                }
            });
        }
        LoadTableLichSuBaoCao();
    }//GEN-LAST:event_btnGuiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGui;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JComboBox<String> cbbLopHoc;
    private javax.swing.JComboBox<String> cbbMonHoc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblLichSuBaoCao;
    private javax.swing.JLabel lblLopHoc;
    private javax.swing.JLabel lblMaGV;
    private javax.swing.JLabel lblMonHoc;
    private javax.swing.JLabel lblNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JTable tblLichSuBaoCao;
    private javax.swing.JTextField txtMaGV;
    private javax.swing.JTextArea txtNoiDung;
    private javax.swing.JTextField txtTieuDe;
    // End of variables declaration//GEN-END:variables
}
