/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

//import com.view.DAO1.NhanVienService;
import java.awt.Image;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.ChamCong;
import model.NhanVien;
import service.ChamCongService;
import service.NhanVienService;

/**
 *
 * @author LENOVO T560
 */
public class Form_ChamCong extends javax.swing.JPanel implements Runnable {

    // Ngày giờ hiện tại
    Date now = new Date();
    NhanVienService nhanVineService = new NhanVienService();
    ChamCongService chamCongService = new ChamCongService();

    public Form_ChamCong() {
        initComponents();
        setForm();
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            lblDongHo.setText(getTime());
        }
    }

    public String getTime() {
        Date now = new Date();
        String pattern = "HH:mm:ss a dd-MM-yyyy ";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(now);

    }
////

    private void setForm() {
        // Lấy ngày và thời gian hiện tại
//        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng trong Java được đếm từ 0 (0 - 11)
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        String ngay = day + "-" + month + "-" + year;
        String gioVao = hour + ":" + minute + ":" + second;
        txtNgay.setText(ngay);
        txtGioVao.setText(gioVao);

    }

    private void setTime() {
        // Lấy ngày và thời gian hiện tại
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        String gioRa = hour + ":" + minute + ":" + second;
        txtGioRa.setText(gioRa);
    }

    private void chamCongGioVao() {
        if (validateData()) {
            NhanVien nv = nhanVineService.selectByID(txtMaNhanVien.getText().trim());
            txtHoTenNhanVien.setText(nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen());

            byte[] hinhAnh = nv.getImg();
            lblHinhAnh.setText("");
            ImageIcon oriImgIcon = new ImageIcon(hinhAnh);
            Image image = oriImgIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(140, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            ImageIcon imageIcon = new ImageIcon(newimg);
            lblHinhAnh.setIcon(imageIcon);

            ChamCong chamCong = getFormGioVao();
            if (chamCong == null) {
                JOptionPane.showMessageDialog(this, "Lỗi để trống dữ liệu");
            }

            int choice = JOptionPane.showConfirmDialog(this, "Xác thực chấm công giờ vào", "Accuracy?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {

                System.out.println(chamCong.getNv().getId());
                System.out.println(chamCong.getNgay());
                System.out.println(chamCong.getGioVao());
                System.out.println(chamCong.getGioRa());

                chamCongService.save(chamCong);
                JOptionPane.showMessageDialog(this, "Chấm công giờ vào thành công");
                txtMaNhanVien.setText("");
            }
        }

    }

    private void chamCongGioRa() {
        if (validateData()) {

            NhanVien nv = nhanVineService.selectByID(txtMaNhanVien.getText().trim());
            txtHoTenNhanVien.setText(nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen());
            setTime();
            byte[] hinhAnh = nv.getImg();
            lblHinhAnh.setText("");
            ImageIcon oriImgIcon = new ImageIcon(hinhAnh);
            Image image = oriImgIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(140, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            ImageIcon imageIcon = new ImageIcon(newimg);
            lblHinhAnh.setIcon(imageIcon);

            ChamCong chamCong = getFormGioRa();
            if (chamCong == null) {
                JOptionPane.showMessageDialog(this, "Lỗi để trống dữ liệu");
            }

            int choice = JOptionPane.showConfirmDialog(this, "Xác thực chấm công giờ ra", "Accuracy?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.out.println(chamCong.getNv().getId());
                System.out.println(chamCong.getNgay());
                System.out.println(chamCong.getGioVao());
                System.out.println(chamCong.getGioRa());
                System.out.println(chamCong.getIdChamCong());
                String idChamCong = chamCong.getIdChamCong();
                chamCongService.update(idChamCong, chamCong);
                JOptionPane.showMessageDialog(this, "Chấm công giờ Ra thành công");
                txtMaNhanVien.setText("");
            }
        }

    }

    private ChamCong getFormGioVao() {
        ChamCong chamCong = new ChamCong();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        NhanVien nv = nhanVineService.selectByID(txtMaNhanVien.getText().trim());
        if (nv == null) {
            JOptionPane.showMessageDialog(this, "Lỗi để trốn dữ liệu");
            return null;
        }
        chamCong.setNv(nv);

        // Đặt giờ vào vào thuộc tính của đối tượng chamCong dưới dạng Date
        Date gioVao = calendar.getTime();
        Time gioVaoTime = new Time(gioVao.getTime());
        chamCong.setGioVao(gioVaoTime);
        Date ngay = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String ngayString = dateFormat.format(ngay);

        try {
            // Chuyển đổi chuỗi thành kiểu dữ liệu Date
            Date ngayTaoDate = dateFormat.parse(ngayString);
            chamCong.setNgay(ngayTaoDate);

        } catch (ParseException ex) {
            Logger.getLogger(Form_ChamCong.class.getName()).log(Level.SEVERE, null, ex);
        }

        return chamCong;

    }

    private ChamCong getFormGioRa() {
        ChamCong chamCong = new ChamCong();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        NhanVien nv = nhanVineService.selectByID(txtMaNhanVien.getText().trim());
        if (nv == null) {
            JOptionPane.showMessageDialog(this, "Lỗi để trốn dữ liệu");
            return null;
        }
        chamCong.setNv(nv);
        Date gioVao = calendar.getTime();
        Time gioVaoTime = new Time(gioVao.getTime());
        ChamCong chamCong2 = chamCongService.selectByID(gioVaoTime);
        chamCong.setIdChamCong(chamCong2.getIdChamCong());
        chamCong.setGioVao(chamCong2.getGioVao());

        Time gioRaTime = new Time(new Date().getTime());
        chamCong.setGioRa(gioRaTime);
        chamCong.setNgay(chamCong2.getNgay());

        return chamCong;

    }

    // vALIDATE DỮ LIỆU
    private boolean validateData() {

        if (txtMaNhanVien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhâp vào mã nhân viên");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHoTenNhanVien = new javax.swing.JLabel();
        btnChamCongGioVao = new javax.swing.JButton();
        ChamCongGioRa = new javax.swing.JButton();
        txtNgay = new javax.swing.JLabel();
        txtGioVao = new javax.swing.JLabel();
        txtGioRa = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setText("Ảnh");

        lblDongHo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDongHo.setText("00:00:00 AM");

        jLabel3.setText("Mã nhân viên :");

        jLabel4.setText("Ngày :");

        jLabel5.setText("Giờ vào :");

        jLabel6.setText("Giờ ra :");

        txtHoTenNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHoTenNhanVien.setText("Tên Nhân Viên");

        btnChamCongGioVao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnChamCongGioVao.setText("Chấm công giờ vào");
        btnChamCongGioVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChamCongGioVaoActionPerformed(evt);
            }
        });

        ChamCongGioRa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ChamCongGioRa.setText("Chấm công giờ ra");
        ChamCongGioRa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChamCongGioRaActionPerformed(evt);
            }
        });

        txtNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNgay.setText("24-07-2023");

        txtGioVao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGioVao.setText("07:00:00 AM");

        txtGioRa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGioRa.setText("00:00:00 AM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHoTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnChamCongGioVao)
                        .addGap(74, 74, 74)
                        .addComponent(ChamCongGioRa))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNgay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGioVao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                .addComponent(txtGioRa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDongHo)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNgay))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtHoTenNhanVien))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtGioVao))))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtGioRa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChamCongGioVao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChamCongGioRa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChamCongGioVaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChamCongGioVaoActionPerformed
        // TODO add your handling code here:
        chamCongGioVao();
    }//GEN-LAST:event_btnChamCongGioVaoActionPerformed

    private void ChamCongGioRaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChamCongGioRaActionPerformed
        // TODO add your handling code here:
        chamCongGioRa();
    }//GEN-LAST:event_ChamCongGioRaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChamCongGioRa;
    private javax.swing.JButton btnChamCongGioVao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel txtGioRa;
    private javax.swing.JLabel txtGioVao;
    private javax.swing.JLabel txtHoTenNhanVien;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JLabel txtNgay;
    // End of variables declaration//GEN-END:variables
}
