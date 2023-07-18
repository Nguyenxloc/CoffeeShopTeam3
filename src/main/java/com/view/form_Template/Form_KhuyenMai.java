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
public class Form_KhuyenMai extends javax.swing.JPanel {

    private TaoTaiKhoanService service = new TaoTaiKhoanService();
    private ArrayList<TaiKhoan> listAccount = service.findAll();
    private ArrayList<CapBac> listCapBac = new ArrayList<>();

    public Form_KhuyenMai() {
        initComponents();
//        fillToComboBox();

    }

//    // Chức năng fillToComboBox
//    private void fillToComboBox() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboCapBac.getModel();
//        model.removeAllElements();
//        listCapBac = service.getTenCapBac();
//        for (CapBac cb : listCapBac) {
//            model.addElement(cb.getTenCB());
//        }
//    }
//
//    // Chức năng lấy giá trị từ form 
//    private TaiKhoan getFomr() {
//
//        String hoTen = txtHoTenNV.getText();
//        String[] hoTenArray = hoTen.split(" "); // Tách chuỗi thành mảng các từ
//        String ho = hoTenArray[0]; // Lấy Họ từ mảng
//        String tenDem = ""; // Khởi tạo Tên Đệm rỗng ban đầu
//        String ten = ""; // Khởi tạo Tên rỗng ban đầu
//
//        if (hoTenArray.length > 1) {
//            ten = hoTenArray[hoTenArray.length - 1]; // Lấy Tên từ mảng
//
//            // Lấy Tên Đệm (nếu có)
//            for (int i = 1; i < hoTenArray.length - 1; i++) {
//                tenDem += hoTenArray[i] + " ";
//            }
//            tenDem = tenDem.trim(); // Xóa khoảng trắng dư thừa
//        }
//
//        String gioiTinh = "";
//        TaiKhoan createAcount = new TaiKhoan();
//        createAcount.setMaNV(txtMaNV.getText());
//        createAcount.setHoNV(ho);
//        createAcount.setTenDemNV(tenDem);
//        createAcount.setTenNV(ten);
//        if (rdoNam.isSelected()) {
//            gioiTinh = "Nam";
//        } else {
//            gioiTinh = "Nữ";
//        }
//        try {
//            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//            Date ngaySinh = format.parse(txtNgaySinh.getText());
//            createAcount.setNgaySinh(ngaySinh);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        createAcount.setGioiTinh(gioiTinh);
//        createAcount.setDiaChi(txtDiaChi.getText());
//        createAcount.setSoDT(txtSoDienThoai.getText());
//        createAcount.setMatKhau(String.valueOf((txtPassword.getPassword())));
//        int count = cboCapBac.getSelectedIndex();
//        CapBac capBac = listCapBac.get(count);
//        createAcount.setCapBac(capBac);
//
//        return createAcount;
//
//    }
//
//    // Chức năng đăng ký form
//    private void createAccount() {
//        if (validateForm()) {
//            TaiKhoan createAcount = getFomr();
//
//            if (createAcount == null) {
//                JOptionPane.showMessageDialog(this, "Lỗi trống dữ liệu");
//                return;
//            }
//            service.save(createAcount);
//            JOptionPane.showMessageDialog(this, "Đăng ký tài khoản thành công");
//            clearForm();
//        }
//
//    }
//
//    private boolean validateForm() {
//        // Validate để trống trường dữ liệu
//        if (txtMaNV.getText().trim().equals("")) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mã nhân viên");
//            return false;
//        }
//
//        if (txtHoTenNV.getText().trim().equals("")) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào họ tên nhân viên");
//            return false;
//        }
//        if (String.valueOf(txtPassword.getPassword()).trim().equals("")) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mật khẩu");
//            return false;
//        }
//        if (txtNgaySinh.getText().trim().equals("")) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ngày sinh");
//            return false;
//        }
//        if (txtSoDienThoai.getText().trim().equals("")) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào số điện thoại");
//            return false;
//        }
//        if (txtDiaChi.getText().trim().equals("")) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào địa chỉ");
//            return false;
//        }
//
//        // Check trùng mã nhân viên
//        int count = service.selectById(txtMaNV.getText().trim());
//        if (count > 0) {
//            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại vui lòng nhập lại");
//            return false;
//        }
//
//        // Check mã nhân viên chứa ký tự đặc biệt
//        String employeeId = txtMaNV.getText().trim();
//        if (!isValidEmployeeId(employeeId)) {
//            JOptionPane.showMessageDialog(this, "Mã nhân viên không hợp lệ, vui lòng nhập lại");
//            return false;
//        }
//        // Validate Tên nhân viên đúng định dạng không chứa ký tự đặc biệt hoặc số
//        if (!isValidEmployeeName(txtHoTenNV.getText().trim())) {
//            JOptionPane.showMessageDialog(this, "Họ tên nhân viên không hợp lệ, vui lòng nhập lại");
//            return false;
//        }
//
//        // Tạo Regex hợp lệ cho ngày sinh
//        String dobRegex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d{2}$";
//        String dob = txtNgaySinh.getText().trim();
//        if (!dob.matches(dobRegex)) {
//            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ");
//            return false;
//        }
//
//        // Validate ngày sinh hợp lệ
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        sdf.setLenient(false);
//        try {
//            sdf.parse(dob);
//        } catch (ParseException e) {
//            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ");
//            return false;
//        }
//
//        // Validate Phone đúng định dạng
//        String[] phoneNumbers = txtSoDienThoai.getText().trim().split(",");
//        for (String phoneNumber : phoneNumbers) {
//            if (!isValidPhoneNumber(phoneNumber.trim())) {
//                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ, vui lòng nhập lại số điện thoại hợp lệ");
//                return false;
//            }
//        }
//        String phoneNumber = txtSoDienThoai.getText().trim();
//        if (isPhoneNumberExists(phoneNumber)) {
//            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại, vui lòng nhập lại số điện thoại");
//            return false;
//        }
//        // Validate Password đúng định dạng
//        if (!isValidPassword(String.valueOf(txtPassword.getPassword()))) {
//            JOptionPane.showMessageDialog(this, "Mật khẩu không đủ mạnh. Vui lòng sử dụng ít nhất 8 ký tự và bao gồm cả chữ hoa, chữ thường và số");
//            return false;
//        }
//
//        return true;
//    }
//
//    // Regex Phone in Java, ValidatePhone
//    private boolean isValidPhoneNumber(String phoneNumber) {
//        // Validate the phone number using a regular expression
//        String regex = "^(\\+?84|0)(3[2-9]|5[2689]|7[06789]|8[1-689]|9[0-9])(\\d{7})$";
//        return phoneNumber.matches(regex);
//    }
//
//    // Check số điện thoại đã tồn tại
//    private boolean isPhoneNumberExists(String phoneNumber) {
//        ArrayList<String> existingPhoneNumbers = service.getAllPhoneNumbers();
//        return existingPhoneNumbers.contains(phoneNumber);
//
//    }
//
//    // Regex Password in Java, isValidPassword
//    private boolean isValidPassword(String password) {
//        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
//        return password.matches(regex);
//    }
//
//    // Regex ID Employee in Java, 
//    private boolean isValidEmployeeId(String employeeId) {
////        String regex = "^[a-zA-Z][a-zA-Z0-9]*$";
//        String regex = "^NV[a-zA-Z0-9]*$";
//        return employeeId.matches(regex);
//    }
//
//    // Regex Name Employee in Java
//    private boolean isValidEmployeeName(String employeeName) {
//        String vietnameseRegex = "^[\\p{Lu}][\\p{L}\\s]*$";
//        return employeeName.matches(vietnameseRegex);
//    }
//
//    // Chức năng Clear Form
//    private void clearForm() {
//        txtMaNV.setText("");
//        txtHoTenNV.setText("");
//        txtPassword.setText("");
//        txtNgaySinh.setText("");
//        txtSoDienThoai.setText("");
//        txtDiaChi.setText("");
//        cboCapBac.setSelectedIndex(0);
//
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jTextField16 = new javax.swing.JTextField();
        jComboBox16 = new javax.swing.JComboBox<>();
        jTextField17 = new javax.swing.JTextField();
        jComboBox17 = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jButton13 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jDateChooser8 = new com.toedter.calendar.JDateChooser();
        jButton14 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jComboBox21 = new javax.swing.JComboBox<>();
        jComboBox22 = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblKhuyenMai4 = new javax.swing.JTable();
        jLabel57 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jComboBox23 = new javax.swing.JComboBox<>();
        jCheckBox5 = new javax.swing.JCheckBox();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblSanPham4 = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel12.setText("jLabel12");

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel11.setForeground(new java.awt.Color(255, 255, 255));
        jPanel11.setName(""); // NOI18N

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel41.setText("SALE");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel42.setText("Mã khuyến mại");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel43.setText("Tên khuyến mại");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setText("Ngày kết thúc");

        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton13.setText("Thêm");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel45.setText("Loại khuyến mại");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel46.setText("Trạng thái");

        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton14.setText("Cập nhật");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel47.setText("Ngày bắt đầu");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel48.setText("Giá trị");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(jLabel45)
                            .addComponent(jLabel47)
                            .addComponent(jLabel44)
                            .addComponent(jLabel46))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField16)
                    .addComponent(jTextField17)
                    .addComponent(jComboBox17, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField18)
                    .addComponent(jDateChooser7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel41)
                .addGap(60, 60, 60)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))
                        .addGap(21, 21, 21)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel43)))
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGap(23, 23, 23)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(25, 25, 25)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel54.setText("Tìm khuyến mãi:");

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel55.setText("Loại KM");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel56.setText("Trạng thái");

        jButton17.setText("Tìm");

        jComboBox21.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblKhuyenMai4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã KM", "Tên KM", "Loại KM", "Giá trị", "Trạng thái"
            }
        ));
        jScrollPane9.setViewportView(tblKhuyenMai4);

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel57.setText("Tìm sản phẩm");

        jButton18.setText("Tìm");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel58.setText("Loại Sản phẩm");

        jComboBox23.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCheckBox5.setText("Tất cả");

        tblSanPham4.setBackground(new java.awt.Color(255, 255, 255));
        tblSanPham4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Giá cũ", "Giá mới", ""
            }
        ));
        jScrollPane10.setViewportView(tblSanPham4);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel56)
                                    .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(jCheckBox5)))))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10)
                            .addComponent(jScrollPane9))
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jLabel58))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18)
                    .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox21;
    private javax.swing.JComboBox<String> jComboBox22;
    private javax.swing.JComboBox<String> jComboBox23;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private com.toedter.calendar.JDateChooser jDateChooser8;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTable tblKhuyenMai4;
    private javax.swing.JTable tblSanPham4;
    // End of variables declaration//GEN-END:variables
}
