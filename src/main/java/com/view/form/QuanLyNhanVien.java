/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form;

import Repository1.NhanVienDao;
import Repository1.NhanVienService;
import com.view.Helper.Ximage;
import com.view.model.CapBac;
import com.view.model.NhanVien;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lê Chấn Khang
 */
public class QuanLyNhanVien extends javax.swing.JPanel {

    ArrayList<NhanVien> listNhanVien = new ArrayList<>();
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private DefaultComboBoxModel comboBoxModel  = new DefaultComboBoxModel();
    private NhanVienDao nhanVienDao = new NhanVienDao();
    private NhanVienService nhanVienService = new NhanVienService();
    
    static String url = null;
    byte[] imgBytes = new byte[5000];
    int count = -1;
    
    public QuanLyNhanVien() {
        initComponents();
        loadCbo();
        loadData();
    }

    private void loadCbo() {
        ArrayList<CapBac> lit = nhanVienDao.getCboCapbac();
        comboBoxModel = (DefaultComboBoxModel) cboVaitro.getModel();
        for (CapBac capBac : lit) {
            comboBoxModel.addElement(capBac);
        }
    }
    
    private void loadData() {
        
        defaultTableModel = (DefaultTableModel) tblForm.getModel();
        defaultTableModel.setRowCount(0);
        listNhanVien = nhanVienDao.getList();
        for (NhanVien nv : listNhanVien) {
            defaultTableModel.addRow(new Object[]{
                 nv.getMa(),
                 nv.getTen(),
                 nv.getGioiTinh(),
                 nv.getNgaySinh(),
                 nv.getDiaChi(),
                 nv.getSdt(),
                 nv.getTaiKhoan(),
                 nv.getMatKhau(),
                 nv.getIdCB(),
                 nv.getTrangThai(),
                 nv.getImg()
            });
        }
    }
    
    //Hinh
    public void convertURLToBytes() throws IOException {
        String url = lblURL.getText();
        BufferedImage bImage = ImageIO.read(new File(url.toString()));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        imgBytes = bos.toByteArray();
    }
   //
      public void showDetail() {
        count=tblForm.getSelectedRow();
        txtMa.setText(listNhanVien.get(count).getMa());
        txtTen.setText(listNhanVien.get(count).getTen());
         rdoNam.setSelected(listNhanVien.get(count).getGioiTinh().equalsIgnoreCase("Nam"));
         rdoNu.setSelected(listNhanVien.get(count).getGioiTinh().equalsIgnoreCase("Nữ"));
        txtNgaySinh.setText(listNhanVien.get(count).getNgaySinh());
        txtDiachi.setText(listNhanVien.get(count).getDiaChi());
        txtSDT.setText(listNhanVien.get(count).getSdt());
        txtTK.setText(listNhanVien.get(count).getTaiKhoan());
        txtMK.setText(listNhanVien.get(count).getMatKhau());
        
         cboVaitro.setSelectedItem(listNhanVien.get(count).getIdCB());
        //txtTrangthai.setText(Integer.parseInt(listNhanVien.get(count).getTrangThai()));
        txtTrangthai.setText(listNhanVien.get(count).getTrangThai()+"");
        
        lblAvatar.setText("");
        ImageIcon  oriImgIcon = new ImageIcon(listNhanVien.get(count).getImg());
        Image image = oriImgIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(140,150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon imageIcon = new ImageIcon(newimg);
        lblAvatar.setIcon(imageIcon);
     
    }
  
      
    public void save() {
        try {
            convertURLToBytes();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi nhập ảnh !");
        }
       String ma = txtMa.getText();
        String ten = txtTen.getText();
        String gioiTinh;
        if(rdoNam.isSelected()){
            gioiTinh="Nam";
        }else{
            gioiTinh="Nữ";
        }
        String ngaySinh = txtNgaySinh.getText();
        String diaChi = txtDiachi.getText();
        String sdt = txtSDT.getText();
        String taiKhoan = txtTK.getText();
        String matKhau = txtMK.getText();
        CapBac cb = (CapBac) cboVaitro.getSelectedItem();
        String vaiTro = cb.getId();
        Integer trangThai = Integer.parseInt(txtTrangthai.getText());
              
        NhanVien nhanVien = new NhanVien(ma, ten, gioiTinh, ngaySinh, diaChi, sdt, taiKhoan, matKhau, vaiTro, trangThai, imgBytes);
        
        try {
            nhanVienDao.save(nhanVien);
            JOptionPane.showMessageDialog(this, "Thêm thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Thêm thất bại !");
        }
        loadData();
    }
    
    
    public void update(){
        count = tblForm.getSelectedRow();
        System.out.println(imgBytes);
        String mes ="";
        try {
            convertURLToBytes();
        } catch (Exception e) {
            e.printStackTrace();
            mes+="\n Lỗi nhập ảnh";
        }
        System.out.println(lblURL.getText());
        System.out.println(!lblURL.getText().equals(url));
        if(!lblURL.getText().equals("#URL")){
            
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        String gioiTinh;
        if(rdoNam.isSelected()){
            gioiTinh="Nam";
        }else{
            gioiTinh="Nữ";
        }
        String ngaySinh = txtNgaySinh.getText();
        String diaChi = txtDiachi.getText();
        String sdt = txtSDT.getText();
        String taiKhoan = txtTK.getText();
        String matKhau = txtMK.getText();
        CapBac cb = (CapBac) cboVaitro.getSelectedItem();
        String vaiTro = cb.getId();
        Integer trangThai = Integer.parseInt(txtTrangthai.getText());
              
        NhanVien nhanVien = new NhanVien(listNhanVien.get(count).getMa(), ten, gioiTinh, ngaySinh, diaChi, sdt, taiKhoan, matKhau, vaiTro, trangThai, imgBytes);
                try {
            nhanVienDao.update(nhanVien);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại !");
        }
        loadData();
        }
        else{
            mes+="\n Thêm thất bại !";
            JOptionPane.showMessageDialog(this,mes);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblForm = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMK = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiachi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtTrangthai = new javax.swing.JTextField();
        cboVaitro = new javax.swing.JComboBox<>();
        lblAvatar = new javax.swing.JLabel();
        btnChooseFile = new javax.swing.JButton();
        lblURL = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        tblForm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Tài khoản", "Mật khẩu", "Vai trò", "Trạng thái"
            }
        ));
        tblForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFormMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblForm);

        jLabel1.setText("Mã NV");

        jLabel2.setText("Tên");

        jLabel3.setText("Tài khoản");

        jLabel4.setText("Mật khẩu");

        jLabel5.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel6.setText("Ngày sinh");

        jLabel7.setText("Địa chỉ");

        jLabel8.setText("Số điện thoại");

        jLabel9.setText("Vai tro");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");

        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setText("Trạng thái");

        lblAvatar.setText("#Avartar here");
        lblAvatar.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 204), null));
        lblAvatar.setMaximumSize(new java.awt.Dimension(80, 40));
        lblAvatar.setMinimumSize(new java.awt.Dimension(80, 40));

        btnChooseFile.setText("Chọn ảnh");
        btnChooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseFileActionPerformed(evt);
            }
        });

        lblURL.setBackground(new java.awt.Color(0, 0, 255));
        lblURL.setForeground(new java.awt.Color(0, 0, 255));
        lblURL.setText("#URL");

        jLabel11.setText("Avatar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMa)
                                        .addComponent(txtTen)
                                        .addComponent(txtTK)
                                        .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdoNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNu)))
                                .addGap(225, 225, 225)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNgaySinh)
                                    .addComponent(txtDiachi)
                                    .addComponent(txtSDT)
                                    .addComponent(txtTrangthai)
                                    .addComponent(cboVaitro, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(btnThem)
                        .addGap(44, 44, 44)
                        .addComponent(btnSua)
                        .addGap(46, 46, 46)
                        .addComponent(jButton3)
                        .addGap(40, 40, 40)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addGap(32, 32, 32)
                        .addComponent(btnChooseFile)
                        .addGap(18, 18, 18)
                        .addComponent(lblURL, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cboVaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu)
                    .addComponent(jLabel10)
                    .addComponent(txtTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChooseFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblURL)
                    .addComponent(jLabel11))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(57, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblFormMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormMouseClicked
        // TODO add your handling code here:
//        int index = tblForm.getSelectedRow();
//        NhanVien nhanVien = nhanVienDao.getList().get(index);
//        String ma= tblForm.getValueAt(index, 0).toString();
//        String ten= tblForm.getValueAt(index, 1).toString();
//        String gioiTinh= tblForm.getValueAt(index, 2).toString();
//        String ngaySinh= tblForm.getValueAt(index, 3).toString();
//        String diaChi= tblForm.getValueAt(index, 4).toString();
//        String sdt= tblForm.getValueAt(index, 5).toString();
//        String taiKhoan= tblForm.getValueAt(index, 6).toString();
//        String matKhau= tblForm.getValueAt(index, 7).toString();
//        String vaiTro= tblForm.getValueAt(index, 8).toString();
//        String trangThai= tblForm.getValueAt(index, 9).toString();
//        //String img = tblForm.getValueAt(index, 10).toString();
//        
//        txtMa.setText(ma);
//        txtTen.setText(ten);
//        if(gioiTinh.equalsIgnoreCase("Nam")){
//            rdoNam.setSelected(true);
//        }else{
//            rdoNu.setSelected(true);
//        }
//       txtNgaySinh.setText(ngaySinh);
//       txtDiachi.setText(diaChi);
//       txtSDT.setText(sdt);
//       txtTK.setText(taiKhoan);
//       txtMK.setText(matKhau);
//       txtTrangthai.setText(trangThai);
//      
//        lblAvatar.setText("");
//        ImageIcon  oriImgIcon = new ImageIcon(listNhanVien.get(count).getImg());
//        Image image = oriImgIcon.getImage(); // transform it
//        Image newimg = image.getScaledInstance(79,120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
//        ImageIcon imageIcon = new ImageIcon(newimg);
//        lblAvatar.setIcon(imageIcon);
//       
//       int i=0;
//        while (true) {            
//            String name = cboVaitro.getItemAt(i).toString();
//            if(name.equalsIgnoreCase(vaiTro)){
//                cboVaitro.setSelectedIndex(i);
//            }
//            i++;
//        }
       showDetail();
       
    }//GEN-LAST:event_tblFormMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txtMa.setText("");
        txtTen.setText("");
        txtNgaySinh.setText("");
        txtDiachi.setText("");
        txtSDT.setText("");
        txtTK.setText("");
        txtMK.setText("");
        txtTrangthai.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
//        String ma = txtMa.getText();
//        String ten = txtTen.getText();
//        String gioiTinh;
//        if(rdoNam.isSelected()){
//            gioiTinh="Nam";
//        }else{
//            gioiTinh="Nữ";
//        }
//        String ngaySinh = txtNgaySinh.getText();
//        String diaChi = txtDiachi.getText();
//        String sdt = txtSDT.getText();
//        String taiKhoan = txtTK.getText();
//      btnSua  String matKhau = txtMK.getText();
//        CapBac cb = (CapBac) cboVaitro.getSelectedItem();
//        String vaiTro = cb.getId();
//        Integer trangThai = Integer.parseInt(txtTrangthai.getText());
//       
//        
//        NhanVien nhanVien = new NhanVien(ma, ten, gioiTinh, ngaySinh, diaChi, sdt, taiKhoan, matKhau, vaiTro, trangThai, "");
//        JOptionPane.showMessageDialog(this, nhanVienService.them(nhanVien));
//        loadData();
      save();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnChooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseFileActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChooseFileFrame(url, lblURL).setVisible(true);
            }
        });
    }//GEN-LAST:event_btnChooseFileActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseFile;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<Object> cboVaitro;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblURL;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblForm;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTK;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTrangthai;
    // End of variables declaration//GEN-END:variables


 

    

    



}
