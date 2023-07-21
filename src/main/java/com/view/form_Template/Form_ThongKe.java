/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import DoUong_HoaDon_ThongKe_Model.HoaDonChiTiet;
import DoUong_HoaDon_ThongKe_Model.LichSuHoaDon;
import DoUong_HoaDon_ThongKe_Service.LichSuHoaDonService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
public class Form_ThongKe extends javax.swing.JPanel {

    private LichSuHoaDonService lichSuHoaDonService = new LichSuHoaDonService();
    private DefaultTableModel model = new DefaultTableModel();
    ArrayList<LichSuHoaDon> lstLichSuHoaDon = new ArrayList<>();
    ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
    int index;

    public Form_ThongKe() {
        initComponents();


    }

    

    public void xuatFileExcel() throws FileNotFoundException, IOException {
        System.out.println(lstLichSuHoaDon);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Danh sách hóa đơn");

        //format date 
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("m/d/yy"));

        int rowCount = 0;
        //header
        Object[] header = {"Mã hóa đơn", "Tên nhân viên", "Thời gian tạo", "Thời gian thanh toán", "Tổng số lượng", "Tiền hóa đơn", "Khuyến mại", "Tiền nhận về", "Trạng thái"};
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

        Cell headerCell5 = headerRow.createCell(5);
        headerCell5.setCellValue((String) header[5]);

        Cell headerCell6 = headerRow.createCell(6);
        headerCell6.setCellValue((String) header[6]);

        Cell headerCell7 = headerRow.createCell(7);
        headerCell7.setCellValue((String) header[7]);

        Cell headerCell8 = headerRow.createCell(8);
        headerCell8.setCellValue((String) header[8]);

        //
        for (LichSuHoaDon lichSuHoaDon : lstLichSuHoaDon) {
            //create a row
            Row row = sheet.createRow(++rowCount);
            int columnCount = -1;
            // write a row
            Object[] obj = {lichSuHoaDon.getMaHoaDon(), lichSuHoaDon.getTenNhanVien(), lichSuHoaDon.getTimeTao(), lichSuHoaDon.getTimeThanhToan(), lichSuHoaDon.getSoLuong(),
                lichSuHoaDon.getTongTienHoaDon(), lichSuHoaDon.getChietKhau(), lichSuHoaDon.tienThucNhan(), lichSuHoaDon.getTrangThai()};
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
                    cell.setCellValue((Date) obj[colNum]);
                    cell.setCellStyle(cellStyle);
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("DSHoaDon.xlsx")) {
            workbook.write(outputStream);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jLabel1.setText("jLabel1");

        jTextField2.setText("jTextField2");

        jScrollPane3.setViewportView(jTextPane1);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 926, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

}
