/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import DoUong_ThongKe_Model.ChiTietDoUong;
import DoUong_ThongKe_Service.ChiTietDoUongService;
import Model_DoUong_ThongKe.LoaiDoUong;
import com.view.component.ChooseFileFrame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Form_QLDoUong extends javax.swing.JPanel {

    ChiTietDoUongService chiTietDoUongService = new ChiTietDoUongService();
    ArrayList<LoaiDoUong> lstLoaiDoUong = new ArrayList<>();
    ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
    int index = -1;
    byte[] imgBytes = new byte[5000];
    String url = null;

    /**
     * Creates new form Form_QlThongTinSV
     */
    public Form_QLDoUong() {
        initComponents();
        loadDanhMucDoUong();
        loadToCboTimKiemDanhMucDoUong();
        loadData();
    }

    public void loadDanhMucDoUong() {
        try {
            DefaultComboBoxModel modelCbo = new DefaultComboBoxModel();
            modelCbo = (DefaultComboBoxModel) cboDanhMucDoUong.getModel();
            modelCbo.removeAllElements();
            lstLoaiDoUong = chiTietDoUongService.getListLoaiDoUong();
            for (LoaiDoUong loaiDoUong : lstLoaiDoUong) {
                modelCbo.addElement(loaiDoUong.getTenLoaiDoUong().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadToCboTimKiemDanhMucDoUong() {
        try {
            DefaultComboBoxModel modelCbo = new DefaultComboBoxModel();
            modelCbo = (DefaultComboBoxModel) cboTimKiemDanhMucDoUong.getModel();
            modelCbo.removeAllElements();
            lstLoaiDoUong = chiTietDoUongService.getListLoaiDoUong();
            for (LoaiDoUong loaiDoUong : lstLoaiDoUong) {
                modelCbo.addElement(loaiDoUong.getTenLoaiDoUong().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            DefaultTableModel model = new DefaultTableModel();
            model = (DefaultTableModel) tblDanhSachDoUong.getModel();
            model.setRowCount(0);
            lstChiTietDoUong = chiTietDoUongService.getListChiTietDoUong();
            int i = 1;
            for (ChiTietDoUong chiTietDoUong : lstChiTietDoUong) {
                model.addRow(new Object[]{
                    i++,
                    chiTietDoUong.getTenDoUong(),
                    chiTietDoUong.getLoaiDoUong().getTenLoaiDoUong(),
                    chiTietDoUong.getGiaNhap(),
                    chiTietDoUong.getGiaBan(),
                    chiTietDoUong.getMoTa(),});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(ChiTietDoUong chiTietDoUong) {

        try {
            chiTietDoUongService.saveChiTietDoUong(chiTietDoUong);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(ChiTietDoUong chiTietDoUong) {
        try {
            chiTietDoUongService.updateChiTietDoUong(chiTietDoUong);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            index = tblDanhSachDoUong.getSelectedRow();
            chiTietDoUongService.deleteChiTietDoUong(lstChiTietDoUong.get(index).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xuatFileExcel() throws FileNotFoundException, IOException {
        System.out.println(lstChiTietDoUong);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Danh sách sản phẩm");

        //format date 
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("m/d/yy"));

        int rowCount = 0;
        //header
        Object[] header = {"Tên đồ uống", "Loại đồ uống", "Giá nhập", "Giá bán", "Mô tả"};
        Row headerRow = sheet.createRow(0);

        Cell headerCell0 = headerRow.createCell(0);
        headerCell0.setCellValue((String) header[0]);

        Cell headerCell1 = headerRow.createCell(1);
        headerCell1.setCellValue((String) header[1]);

        Cell headerCell2 = headerRow.createCell(2);
        headerCell2.setCellValue((String) header[2]);

        Cell headerCell3 = headerRow.createCell(3);
        headerCell3.setCellValue((String) header[3]);

        Cell headerCell4 = headerRow.createCell(4);
        headerCell4.setCellValue((String) header[4]);

        //
        for (ChiTietDoUong sp : lstChiTietDoUong) {
            System.out.println("test loop1");
            //create a row
            Row row = sheet.createRow(++rowCount);
            int columnCount = -1;
            // write a row
            Object[] obj = {sp.getTenDoUong(), sp.getLoaiDoUong().getTenLoaiDoUong(), sp.getGiaNhap(), sp.getGiaBan(), sp.getMoTa()};
            for (int colNum = 0; colNum < obj.length; colNum++) {
                System.out.println(rowCount);
                Cell cell = row.createCell(++columnCount);
                if (obj[colNum] instanceof String) {
                    cell.setCellValue((String) obj[colNum]);
                } else if (obj[colNum] instanceof Integer) {
                    cell.setCellValue((Integer) obj[colNum]);
                } else if (obj[colNum] instanceof Integer) {
                    cell.setCellValue((Integer) obj[colNum]);
                } else if (obj[colNum] instanceof Double) {
                    cell.setCellValue((Double) obj[colNum]);
                } else if (obj[colNum] instanceof Date) {
                    System.out.println("test date");
                    cell.setCellValue((Date) obj[colNum]);
                    cell.setCellStyle(cellStyle);
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("DSSanPham.xlsx")) {
            workbook.write(outputStream);
        }
    }

    public void convertURLToBytes() throws IOException {
        BufferedImage bImage = ImageIO.read(new File(lblUrl.getText()));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        imgBytes = bos.toByteArray();
    }

    public void clear() {
        lblHinhAnh.setIcon(null);
        lblHinhAnh.setText("Ảnh");
        lblUrl.setText("#url");
        txtTenDoUong.setText("");
        cboDanhMucDoUong.setSelectedIndex(0);
        txtGiaNhapDoUong.setText("");
        txtGiaBanDoUong.setText("");
        taraMota.setText("");
        index = -1;
        imgBytes = null;
        loadData();
    }

    public void timKiem() {
        String tenDoUong = txtTimKiemTenDoUong.getText();
        if(tenDoUong.equalsIgnoreCase(""))
           tenDoUong = null;
        int count = cboTimKiemDanhMucDoUong.getSelectedIndex();
        String idLoaiDoUong = lstLoaiDoUong.get(count).getId();
        double giaBatDau=0;
        double giaKetThuc=0;
//        try {
//            giaBatDau = Double.parseDouble(txtStartPrice.getText());
//        } catch (Exception e) {
//            giaBatDau = 0;
//        }
//        
//        try {
//             giaKetThuc = Double.parseDouble(txtEndPrice.getText());
//        } catch (Exception e) {
//             giaKetThuc = 0;
//        }
        try {
            DefaultTableModel model = new DefaultTableModel();
            model = (DefaultTableModel) tblDanhSachDoUong.getModel();
            model.setRowCount(0);
            lstChiTietDoUong = chiTietDoUongService.getTimKiem(tenDoUong, idLoaiDoUong, giaBatDau,giaKetThuc);
            int i = 1;
            for (ChiTietDoUong chiTietDoUong : lstChiTietDoUong) {
                model.addRow(new Object[]{
                    i++,
                    chiTietDoUong.getTenDoUong(),
                    chiTietDoUong.getLoaiDoUong().getTenLoaiDoUong(),
                    chiTietDoUong.getGiaNhap(),
                    chiTietDoUong.getGiaBan(),
                    chiTietDoUong.getMoTa(),});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//    public void loadHinhAnh(){
//        ImageIcon  oriImgIcon = new ImageIcon(lstChiTietDoUong.get(index).getHinhAnh());
//        Image image = oriImgIcon.getImage(); // transform it
//        Image newimg = image.getScaledInstance(79,120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
//        ImageIcon imageIcon = new ImageIcon(newimg);
//        lblHinhAnh.setIcon(imageIcon);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taraMota = new javax.swing.JTextArea();
        txtTenDoUong = new javax.swing.JTextField();
        txtGiaNhapDoUong = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnChonAnh = new javax.swing.JButton();
        cboDanhMucDoUong = new javax.swing.JComboBox<>();
        btnSua = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtGiaBanDoUong = new javax.swing.JTextField();
        lblHinhAnh = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        btnXuatFileExcel = new javax.swing.JButton();
        lblUrl = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTimKiemTenDoUong = new javax.swing.JTextField();
        cboTimKiemDanhMucDoUong = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSachDoUong = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tên đồ uống:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Loại đồ uống:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Giá nhập:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Mô tả:");

        taraMota.setColumns(20);
        taraMota.setRows(5);
        jScrollPane1.setViewportView(taraMota);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Giá bán:");

        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setText("Ảnh");
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 204), null));
        lblHinhAnh.setMaximumSize(new java.awt.Dimension(80, 40));
        lblHinhAnh.setMinimumSize(new java.awt.Dimension(80, 40));

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnXuatFileExcel.setText("Xuất file excel");
        btnXuatFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcelActionPerformed(evt);
            }
        });

        lblUrl.setForeground(new java.awt.Color(0, 51, 255));
        lblUrl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUrl.setText("#url");

        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 172, Short.MAX_VALUE)
                .addComponent(btnChonAnh)
                .addGap(178, 178, 178))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboDanhMucDoUong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaNhapDoUong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaBanDoUong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTenDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnXuatFileExcel))
                                    .addGap(34, 34, 34)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(37, 37, 37)
                                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnClear)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblUrl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClear, btnSua, btnThem, btnXuatFileExcel});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUrl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChonAnh)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboDanhMucDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtGiaNhapDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBanDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear)
                    .addComponent(btnXuatFileExcel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboDanhMucDoUong, txtGiaBanDoUong, txtGiaNhapDoUong, txtTenDoUong});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClear, btnSua, btnThem, btnXuatFileExcel});

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tên đồ uống: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Loại đồ uống: ");

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTimKiemTenDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTimKiemDanhMucDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTimKiem)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemTenDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimKiemDanhMucDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(btnTimKiem)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        tblDanhSachDoUong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên đồ uống", "Loại đồ uống", "Giá nhập", "Giá bán", "Mô tả"
            }
        ));
        tblDanhSachDoUong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachDoUongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhSachDoUong);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        try {
            convertURLToBytes();
        } catch (Exception e) {
            imgBytes = new byte[5000];
            e.printStackTrace();
        }

        int count = cboDanhMucDoUong.getSelectedIndex();
        LoaiDoUong loaiDoUong = lstLoaiDoUong.get(count);
        String tenDoUong = txtTenDoUong.getText();
        Double giaNhap = Double.parseDouble(txtGiaNhapDoUong.getText());
        Double giaBan = Double.parseDouble(txtGiaBanDoUong.getText());
        String moTa = taraMota.getText();
        System.out.println(imgBytes);
        ChiTietDoUong chiTietDoUong = new ChiTietDoUong(null, tenDoUong, giaNhap, giaBan, moTa, imgBytes, loaiDoUong);
        save(chiTietDoUong);
        loadData();
        JOptionPane.showMessageDialog(this, "Thêm thành công !");
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblDanhSachDoUongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachDoUongMouseClicked
        lblUrl.setText("#url");
        index = tblDanhSachDoUong.getSelectedRow();
        if (lstChiTietDoUong.get(index).getHinhAnh() != null) {
            System.out.println("test condition 1");
            ImageIcon oriImgIcon = new ImageIcon(lstChiTietDoUong.get(index).getHinhAnh());
            Image image = oriImgIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(145, 140, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            ImageIcon imageIcon = new ImageIcon(newimg);
            lblHinhAnh.setText("");
            lblHinhAnh.setIcon(imageIcon);
            txtTenDoUong.setText(tblDanhSachDoUong.getValueAt(index, 1).toString());
            cboDanhMucDoUong.setSelectedItem(tblDanhSachDoUong.getValueAt(index, 2).toString());
            txtGiaNhapDoUong.setText(tblDanhSachDoUong.getValueAt(index, 3).toString());
            txtGiaBanDoUong.setText(tblDanhSachDoUong.getValueAt(index, 4).toString());
            taraMota.setText(tblDanhSachDoUong.getValueAt(index, 5).toString());
        } else {
            lblHinhAnh.setIcon(null);
            lblHinhAnh.setText("Ảnh");
            txtTenDoUong.setText(tblDanhSachDoUong.getValueAt(index, 1).toString());
            cboDanhMucDoUong.setSelectedItem(tblDanhSachDoUong.getValueAt(index, 2).toString());
            txtGiaNhapDoUong.setText(tblDanhSachDoUong.getValueAt(index, 3).toString());
            txtGiaBanDoUong.setText(tblDanhSachDoUong.getValueAt(index, 4).toString());
            taraMota.setText(tblDanhSachDoUong.getValueAt(index, 5).toString());
        }

    }//GEN-LAST:event_tblDanhSachDoUongMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        String tenDoUong = txtTenDoUong.getText();
        Double giaNhap = Double.parseDouble(txtGiaNhapDoUong.getText());
        Double giaBan = Double.parseDouble(txtGiaBanDoUong.getText());
        String moTa = taraMota.getText();
        try {
            convertURLToBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(imgBytes);
        ChiTietDoUong chiTietDoUong = new ChiTietDoUong(lstChiTietDoUong.get(index).getId(), tenDoUong, giaNhap, giaBan, moTa, imgBytes, lstLoaiDoUong.get(cboDanhMucDoUong.getSelectedIndex()));
        update(chiTietDoUong);
        loadData();
        JOptionPane.showMessageDialog(this, "Sửa thành công !");
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa đồ uống này ?", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            // Saving code here
            delete();
            loadData();
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXuatFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcelActionPerformed
        // TODO add your handling code here:
        try {
            xuatFileExcel();
            JOptionPane.showMessageDialog(this, "Xuất file thành công !");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, "Không thể xuất file excel !");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatFileExcelActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChooseFileFrame(lblUrl, lblHinhAnh).setVisible(true);
            }
        });
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_btnTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatFileExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboDanhMucDoUong;
    private javax.swing.JComboBox<String> cboTimKiemDanhMucDoUong;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblUrl;
    private javax.swing.JTextArea taraMota;
    private javax.swing.JTable tblDanhSachDoUong;
    private javax.swing.JTextField txtGiaBanDoUong;
    private javax.swing.JTextField txtGiaNhapDoUong;
    private javax.swing.JTextField txtTenDoUong;
    private javax.swing.JTextField txtTimKiemTenDoUong;
    // End of variables declaration//GEN-END:variables

}