/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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

        jPanel1 = new javax.swing.JPanel();
        txtMaNV = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtHoTenNV = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnDangKy1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rdoNu = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtHoTenNV = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboCapBac = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboCapBac = new javax.swing.JComboBox<>();
        btnDangKy1 = new javax.swing.JButton();
        btnClearForm = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Mã nhân viên");

        jLabel3.setText("Họ tên nhân viên");

        jLabel4.setText("Giới tính");

        jLabel8.setText("Mật khẩu");

        txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        txtHoTenNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtMaNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtMaNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel5.setText("Ngày sinh");

        jLabel6.setText("Số điện thoại");

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiActionPerformed(evt);
            }
        });

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtHoTenNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Create Account");

        jLabel2.setText("Mã nhân viên");

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        jLabel3.setText("Họ tên nhân viên");

        jLabel9.setText("Cấp bậc");

        jLabel4.setText("Giới tính");

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        rdoNu.setText("Nữ");

        jLabel5.setText("Ngày sinh");

        cboCapBac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Số điện thoại");

        jLabel8.setText("Mật khẩu");
        jLabel7.setText("Địa chỉ");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel9.setText("Cấp bậc");

        cboCapBac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jLabel7.setText("Địa chỉ");

        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHoTenNV)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoDienThoai)
                            .addComponent(txtDiaChi)
                            .addComponent(cboCapBac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDangKy1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNu)
                            .addComponent(rdoNam)
                            .addComponent(jLabel4))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(cboCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(btnDangKy1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Create Account");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHoTenNV)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoDienThoai)
                            .addComponent(txtDiaChi)
                            .addComponent(cboCapBac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDangKy1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(202, 202, 202))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNu)
                    .addComponent(rdoNam)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangKy1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void btnDangKy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKy1ActionPerformed
        // TODO add your handling code here:
        createAccount();
    }//GEN-LAST:event_btnDangKy1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangKy1;
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
    private javax.swing.JPanel jPanel1;
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
