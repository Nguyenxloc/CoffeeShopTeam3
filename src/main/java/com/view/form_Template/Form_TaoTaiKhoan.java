/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import com.view.model.QuanLyTaiKhoan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CapBac;
import model.TaiKhoan;
import service.TaoTaiKhoanService;

/**
 *
 * @author LENOVO T560
 */
public class Form_TaoTaiKhoan extends javax.swing.JPanel {

    private TaoTaiKhoanService service = new TaoTaiKhoanService();
    private ArrayList<TaiKhoan> listAccount = service.findAll();
    private ArrayList<CapBac> listCapBac = new ArrayList<>();

    public Form_TaoTaiKhoan() {
        initComponents();
        fillToComboBox();

    }

    // Chức năng fillToComboBox
    private void fillToComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboCapBac.getModel();
        model.removeAllElements();
        listCapBac = service.getTenCapBac();
        for (CapBac cb : listCapBac) {
            model.addElement(cb.getTenCB());
        }
    }

    // Chức năng lấy giá trị từ form 
    private TaiKhoan getFomr() {

        String hoTen = txtHoTenNV.getText();
        String[] hoTenArray = hoTen.split(" "); // Tách chuỗi thành mảng các từ
        String ho = hoTenArray[0]; // Lấy Họ từ mảng
        String tenDem = ""; // Khởi tạo Tên Đệm rỗng ban đầu
        String ten = ""; // Khởi tạo Tên rỗng ban đầu

        if (hoTenArray.length > 1) {
            ten = hoTenArray[hoTenArray.length - 1]; // Lấy Tên từ mảng

            // Lấy Tên Đệm (nếu có)
            for (int i = 1; i < hoTenArray.length - 1; i++) {
                tenDem += hoTenArray[i] + " ";
            }
            tenDem = tenDem.trim(); // Xóa khoảng trắng dư thừa
        }

        String gioiTinh = "";
        TaiKhoan createAcount = new TaiKhoan();
        createAcount.setMaNV(txtMaNV.getText());
        createAcount.setHoNV(ho);
        createAcount.setTenDemNV(tenDem);
        createAcount.setTenNV(ten);
        if (rdoNam.isSelected()) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date ngaySinh = format.parse(txtNgaySinh.getText());
            createAcount.setNgaySinh(ngaySinh);
        } catch (Exception e) {
            e.printStackTrace();
        }

        createAcount.setGioiTinh(gioiTinh);
        createAcount.setDiaChi(txtDiaChi.getText());
        createAcount.setSoDT(txtSoDienThoai.getText());
        createAcount.setMatKhau(String.valueOf((txtPassword.getPassword())));
        int count = cboCapBac.getSelectedIndex();
        CapBac capBac = listCapBac.get(count);
        createAcount.setCapBac(capBac);

        return createAcount;

    }

    // Chức năng đăng ký form
    private void createAccount() {
        if (validateForm()) {
            TaiKhoan createAcount = getFomr();

            if (createAcount == null) {
                JOptionPane.showMessageDialog(this, "Lỗi trống dữ liệu");
                return;
            }
            service.save(createAcount);
            JOptionPane.showMessageDialog(this, "Đăng ký tài khoản thành công");
            clearForm();
        }

    }

    private boolean validateForm() {
        // Validate để trống trường dữ liệu
        if (txtMaNV.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mã nhân viên");
            return false;
        }

        if (txtHoTenNV.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào họ tên nhân viên");
            return false;
        }
        if (String.valueOf(txtPassword.getPassword()).trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mật khẩu");
            return false;
        }
        if (txtNgaySinh.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ngày sinh");
            return false;
        }
        if (txtSoDienThoai.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào số điện thoại");
            return false;
        }
        if (txtDiaChi.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào địa chỉ");
            return false;
        }

        // Check trùng mã nhân viên
        int count = service.selectById(txtMaNV.getText().trim());
        if (count > 0) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại vui lòng nhập lại");
            return false;
        }

        // Check mã nhân viên chứa ký tự đặc biệt
        String employeeId = txtMaNV.getText().trim();
        if (!isValidEmployeeId(employeeId)) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không hợp lệ, vui lòng nhập lại");
            return false;
        }
        // Validate Tên nhân viên đúng định dạng không chứa ký tự đặc biệt hoặc số
        if (!isValidEmployeeName(txtHoTenNV.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Họ tên nhân viên không hợp lệ, vui lòng nhập lại");
            return false;
        }

        // Tạo Regex hợp lệ cho ngày sinh
        String dobRegex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d{2}$";
        String dob = txtNgaySinh.getText().trim();
        if (!dob.matches(dobRegex)) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ");
            return false;
        }

        // Validate ngày sinh hợp lệ
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dob);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ");
            return false;
        }

        // Validate Phone đúng định dạng
        String[] phoneNumbers = txtSoDienThoai.getText().trim().split(",");
        for (String phoneNumber : phoneNumbers) {
            if (!isValidPhoneNumber(phoneNumber.trim())) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ, vui lòng nhập lại số điện thoại hợp lệ");
                return false;
            }
        }
        String phoneNumber = txtSoDienThoai.getText().trim();
        if (isPhoneNumberExists(phoneNumber)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại, vui lòng nhập lại số điện thoại");
            return false;
        }
        // Validate Password đúng định dạng
        if (!isValidPassword(String.valueOf(txtPassword.getPassword()))) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không đủ mạnh. Vui lòng sử dụng ít nhất 8 ký tự và bao gồm cả chữ hoa, chữ thường và số");
            return false;
        }

        return true;
    }

    // Regex Phone in Java, ValidatePhone
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Validate the phone number using a regular expression
        String regex = "^(\\+?84|0)(3[2-9]|5[2689]|7[06789]|8[1-689]|9[0-9])(\\d{7})$";
        return phoneNumber.matches(regex);
    }

    // Check số điện thoại đã tồn tại
    private boolean isPhoneNumberExists(String phoneNumber) {
        ArrayList<String> existingPhoneNumbers = service.getAllPhoneNumbers();
        return existingPhoneNumbers.contains(phoneNumber);

    }

    // Regex Password in Java, isValidPassword
    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        return password.matches(regex);
    }

    // Regex ID Employee in Java, 
    private boolean isValidEmployeeId(String employeeId) {
//        String regex = "^[a-zA-Z][a-zA-Z0-9]*$";
        String regex = "^NV[a-zA-Z0-9]*$";
        return employeeId.matches(regex);
    }

    // Regex Name Employee in Java
    private boolean isValidEmployeeName(String employeeName) {
        String vietnameseRegex = "^[\\p{Lu}][\\p{L}\\s]*$";
        return employeeName.matches(vietnameseRegex);
    }

    // Chức năng Clear Form
    private void clearForm() {
        txtMaNV.setText("");
        txtHoTenNV.setText("");
        txtPassword.setText("");
        txtNgaySinh.setText("");
        txtSoDienThoai.setText("");
        txtDiaChi.setText("");
        cboCapBac.setSelectedIndex(0);

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
        rdoNam = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        rdoNu = new javax.swing.JRadioButton();
        cboCapBac = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        btnClearForm = new javax.swing.JButton();
        txtHoTenNV = new javax.swing.JTextField();
        btnDangKy1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        jLabel9.setText("Cấp bậc");

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        cboCapBac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Mật khẩu");

        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiActionPerformed(evt);
            }
        });

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Create Account");

        jLabel2.setText("Mã nhân viên");

        jLabel3.setText("Họ tên nhân viên");

        jLabel4.setText("Giới tính");

        jLabel5.setText("Ngày sinh");

        jLabel6.setText("Số điện thoại");

        jLabel7.setText("Địa chỉ");

        txtMaNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnClearForm.setBackground(new java.awt.Color(0, 153, 255));
        btnClearForm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClearForm.setForeground(new java.awt.Color(255, 255, 255));
        btnClearForm.setText("Clear Form");
        btnClearForm.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFormActionPerformed(evt);
            }
        });

        txtHoTenNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnDangKy1.setBackground(new java.awt.Color(0, 153, 255));
        btnDangKy1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDangKy1.setForeground(new java.awt.Color(255, 255, 255));
        btnDangKy1.setText("SIGN UP");
        btnDangKy1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDangKy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKy1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHoTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(txtMaNV))
                        .addGap(111, 111, 111))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDangKy1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(cboCapBac, 0, 220, Short.MAX_VALUE)
                            .addComponent(txtNgaySinh)))
                    .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNu)
                    .addComponent(rdoNam)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDangKy1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearFormActionPerformed

    private void btnDangKy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKy1ActionPerformed
        // TODO add your handling code here:
        createAccount();
    }//GEN-LAST:event_btnDangKy1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnDangKy1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboCapBac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTenNV;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables
}
