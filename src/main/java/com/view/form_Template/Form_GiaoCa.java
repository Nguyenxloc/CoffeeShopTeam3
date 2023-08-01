/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import DoUong_HoaDon_ThongKe_Model.LoaiDoUong;
import DoUong_HoaDon_ThongKe_Service.LoaiDoUongService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ChiTietDoUong;
import model.GiaoCa;
import model.KhuyenMai;
import model.NhanVien;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.ChiTietDoUongService_Master;
import service.GiaoCaService;
import service.NhanVienService;
import service.SaleService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.sql.Time;

/**
 *
 * @author LENOVO T560
 */
public class Form_GiaoCa extends javax.swing.JPanel {

    private SaleService serviceSale = new SaleService();
    private GiaoCaService giaoCaService = new GiaoCaService();
    private NhanVienService nhanVienService = new NhanVienService();
    private ChiTietDoUongService_Master chiTietDoUongService = new ChiTietDoUongService_Master();
    private LoaiDoUongService loadDoUongService = new LoaiDoUongService();
    private ArrayList<KhuyenMai> listSale = new ArrayList<>();
    private ArrayList<ChiTietDoUong> listChiTietSP = new ArrayList<>();
    private ArrayList<LoaiDoUong> listLoaiDoUong = new ArrayList<>();
    private ArrayList<GiaoCa> listGiaoCa = new ArrayList<>();
    private ArrayList<NhanVien> listNhanVien = new ArrayList<>();

    private int row;

    public Form_GiaoCa() {
        initComponents();
        listGiaoCa = giaoCaService.selectALL();
        fillToTableGiaoCa(listGiaoCa);
        fillComboBoxNhanVien();
//        fillComboBoxCaLamViec();
    }

    private void fillToTableGiaoCa(ArrayList<GiaoCa> listGiaoCa) {
        DefaultTableModel model = (DefaultTableModel) tblPhieuGiaoCa.getModel();
        model.setRowCount(0);
//        listGiaoCa = giaoCaService.selectALL();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (GiaoCa giaoCa : listGiaoCa) {
            NhanVien nguoiGiao = giaoCa.getNguoiGiao();
            NhanVien nguoiNhan = giaoCa.getNguoiNhan();
            String hoTenNguoiGiao = nguoiGiao.getHo() + " " + nguoiGiao.getTenDem() + " " + nguoiGiao.getTen();
            String hoTenNguoiNhan = nguoiNhan.getHo() + " " + nguoiNhan.getTenDem() + " " + nguoiNhan.getTen();

            BigDecimal tongTien = giaoCa.getTongCong();
            BigDecimal tongTienThucKiem = giaoCa.getThucKiem();
            String tongTienFormat = decimalFormat.format(tongTien);
            String tongTienTKFormat = decimalFormat.format(tongTienThucKiem);

            model.addRow(new Object[]{giaoCa.getMaGiaoCa(), giaoCa.getCaLamViec(), giaoCa.getNgayGiaoCa(), hoTenNguoiGiao, hoTenNguoiNhan, tongTienFormat, tongTienTKFormat, giaoCa.getGioKiemKe()});
        }
    }

    // Chức năng fill dữ liệu lên ComboxBox Nhân viên
    private void fillComboBoxNhanVien() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNvTimKiem.getModel();
        DefaultComboBoxModel comboBoxModelNguoiGiao = (DefaultComboBoxModel) cboNguoiGiao.getModel();
        DefaultComboBoxModel comboBoxModelNguoiNhan = (DefaultComboBoxModel) cboNguoiNhan.getModel();
        model.removeAllElements();
        comboBoxModelNguoiGiao.removeAllElements();
        comboBoxModelNguoiNhan.removeAllElements();
        listNhanVien = nhanVienService.selectCBONhanVien();
        for (NhanVien nv : listNhanVien) {
            model.addElement(nv.getTen());
            comboBoxModelNguoiGiao.addElement(nv.getTen());
            comboBoxModelNguoiNhan.addElement(nv.getTen());
        }
    }

    // Chức năng fill dữ liệu lên ComboxBox Ca làm việc
    private void fillComboBoxCaLamViec() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboCaLamViec.getModel();
        model.removeAllElements();
        listGiaoCa = giaoCaService.selectALL();
        for (GiaoCa gc : listGiaoCa) {
            model.addElement(gc.getCaLamViec());
        }
    }

    // Chức năng set form khi click vào 1 dòng table
    private void showDetailtData() {
        row = tblPhieuGiaoCa.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng dữ liệu trên table phiếu giao ca");
            return;
        }
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date ngayGiaoCalbl = inputFormat.parse(tblPhieuGiaoCa.getValueAt(row, 2).toString());
            String ngayGCFormat = outputFormat.format(ngayGiaoCalbl);

            lblMaPhieu.setText(tblPhieuGiaoCa.getValueAt(row, 0).toString());
            lblCaLamViec.setText(tblPhieuGiaoCa.getValueAt(row, 1).toString());
            lblDate.setText(ngayGCFormat);
            txtNgayGiaoCa.setDate(ngayGiaoCalbl);
            String caLamViec = tblPhieuGiaoCa.getValueAt(row, 1).toString();
            cboCaLamViec.setSelectedItem(caLamViec);
            String tenNguoiGiao = tblPhieuGiaoCa.getValueAt(row, 3).toString();
            cboNguoiGiao.setSelectedItem(tenNguoiGiao);
            String tenNguoiNhan = tblPhieuGiaoCa.getValueAt(row, 4).toString();
            cboNguoiNhan.setSelectedItem(tenNguoiNhan);

            lblDoanhThu.setText(tblPhieuGiaoCa.getValueAt(row, 5).toString() + " VND");

            Date ngayGiaoCaFormat = outputFormat.parse(ngayGCFormat);
            txtNgayGiaoCa.setDate(ngayGiaoCaFormat);

            // Chuyển đổi tổng tiền và tổng tiền thực kiểm
            String tongTienStr = tblPhieuGiaoCa.getValueAt(row, 5).toString();
            String tongTienThucKiemStr = tblPhieuGiaoCa.getValueAt(row, 6).toString();
            lblTongTien.setText(tongTienStr);
            txtThucKiem.setText(tongTienThucKiemStr);

            // Các dòng mã không thay đổi
            lblGioKiemKe.setText(tblPhieuGiaoCa.getValueAt(row, 7).toString());
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }

    }

    // Chức năng Set thời gian hiện tại
    private void setTime() {
        Date now = new Date();
        String pattern = "HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String timeFormat = dateFormat.format(now);
        lblGioKiemKe.setText(timeFormat);

    }

    // Chức năng xóa Trắng form
    private void clearForm() {
        lblMaPhieu.setText("");
        lblCaLamViec.setText("#CaLV");
        lblDate.setText("#Date");
        lblDoanhThu.setText("#Revenue");
        lblTongTien.setText("0.000.000");
        lblGioKiemKe.setText("00:00");
        cboCaLamViec.setSelectedIndex(0);
        txtNgayGiaoCa.setDate(null);
        cboNguoiGiao.setSelectedIndex(0);
        cboNguoiNhan.setSelectedIndex(0);
        txtThucKiem.setText("");
        cboTrangThai.setSelectedIndex(0);
        txtGhiChu.setText("");
        row = tblPhieuGiaoCa.getSelectedRow();
        row = -1;

    }

    // Chức năng thêm phiếu giao ca
    private void addPhieuGiaoCa() {
//        if (validateForm()) {
        GiaoCa giaoCa = getForm();

        System.out.println(giaoCa.getMaGiaoCa());
        System.out.println(giaoCa.getCaLamViec());
        System.out.println(giaoCa.getNgayGiaoCa().toString());
        System.out.println(giaoCa.getTongCong().toString());
        System.out.println(giaoCa.getNguoiGiao().getTen());
        System.out.println(giaoCa.getNguoiNhan().getTen());
        System.out.println(giaoCa.getGioKiemKe().toString());
        System.out.println(giaoCa.getThucKiem().toString());
        System.out.println(giaoCa.getTrangThai());
        System.out.println(giaoCa.getGhiChu());

//            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm phiếu giao ca này ?", "ADD ?", JOptionPane.YES_NO_OPTION);
//            if (choice == JOptionPane.YES_OPTION) {
////                giaoCaService.delete(maPhieuGiaoCa);
//                JOptionPane.showMessageDialog(this, "Thêm phiếu giao ca thành công");
//                listGiaoCa = giaoCaService.selectALL();
//                fillToTableGiaoCa(listGiaoCa);
//                clearForm();
//            }
//        }
    }

    // Chức năng xóa phiếu giao ca
    private void deletePhieuGiaoCa() {
        row = tblPhieuGiaoCa.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 phiếu giao ca trên table muốn xóa");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa phiếu giao ca này ?", "Delete ?", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String maPhieuGiaoCa = tblPhieuGiaoCa.getValueAt(row, 0).toString();
            System.out.println(maPhieuGiaoCa);
//            giaoCaService.delete(maPhieuGiaoCa);
            JOptionPane.showMessageDialog(this, "Xóa phiếu giao ca thành công");
            listGiaoCa = giaoCaService.selectALL();
            fillToTableGiaoCa(listGiaoCa);
            clearForm();
        }

    }

    // Chức năng cập nhật Phiếu giao ca
    private void updatePhieuGiaoCa() {
        int row = tblPhieuGiaoCa.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 phiếu giao ca trên table muốn xóa");
            return;
        }
    }

    // Chức năng lấy giá trị từ form
    private GiaoCa getForm() {
        GiaoCa giaoCa = new GiaoCa();

        String idNhanVien = "";
        NhanVien nvGiaoCa = null;
        NhanVien nvNhanCa = null;
        String tenNguoiGiao = cboNguoiGiao.getSelectedItem().toString();
        String tenNguoiNhan = cboNguoiNhan.getSelectedItem().toString();

        // Truy vấn cơ sở dữ liệu để lấy idNhanVien dựa vào tên người giao và tên người nhận
        String idNhanVienGiao = "B9994504-EE0C-47E3-96EB-1EFD01397241";
        String idNhanVienNhan = "226EEAF5-C082-477A-A701-598451CFBF47";

        nvGiaoCa = nhanVienService.selectByIDNhanVien(idNhanVienGiao);
        nvNhanCa = nhanVienService.selectByIDNhanVien(idNhanVienNhan);

        giaoCa.setMaGiaoCa(lblMaPhieu.getText());
        giaoCa.setCaLamViec(cboCaLamViec.getSelectedItem().toString());
        // Định dạng ngày giao ca
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date ngayGiaoCaDate = txtNgayGiaoCa.getDate();
            String ngayGiaoCaString = inputFormat.format(ngayGiaoCaDate);
            Date ngayGiaoCa = inputFormat.parse(ngayGiaoCaString);
            giaoCa.setNgayGiaoCa(ngayGiaoCa);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        giaoCa.setNguoiGiao(nvGiaoCa);
        giaoCa.setNguoiNhan(nvNhanCa);
        // Xóa dấu phẩy trong chuỗi số và chuyển đổi thành số BigDecimal
        try {
            String tongTienString = lblTongTien.getText().replace(",", "");
            String tongTienTKString = txtThucKiem.getText().replace(",", "");

            BigDecimal tongTien = new BigDecimal(tongTienString);
            BigDecimal tongTienTK = new BigDecimal(tongTienTKString);

            giaoCa.setTongCong(tongTien);
            giaoCa.setThucKiem(tongTienTK);
        } catch (NumberFormatException ex) {
            ex.printStackTrace(System.out);
        }
        Time gioKiemKe = Time.valueOf(lblGioKiemKe.getText());
        giaoCa.setGioKiemKe(gioKiemKe);
        giaoCa.setTrangThai(cboTrangThai.getSelectedItem().toString());
        giaoCa.setGhiChu(txtGhiChu.getText());

        if (giaoCa == null) {
            JOptionPane.showMessageDialog(this, "Dữ liệu đang bị trống vui lòng kiểm tra lại");

        }

        return giaoCa;
    }

    // Chức năng Validate Form
    private boolean validateForm() {

        if (txtNgayGiaoCa.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào ngày giao ca");
            return false;
        }

        if (lblGioKiemKe.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào nút giờ hiện tại");
            return false;
        }

        if (txtThucKiem.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào giá trị thực kiểm");
            return false;
        }

        Double giaThucKiem = Double.parseDouble(txtThucKiem.getText().trim());
        if (giaThucKiem < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào giá trị thực kiểm phải lớn hơn không");
            return false;
        }

        return true;
    }

    // Chức năng xuất dữ liệu ra File Excel
    public void xuatFileExcel() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách phiếu giảm giá");

            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã phiếu giao ca");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Ca");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Ngày giao ca");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Giờ kiểm kê");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Người Giao");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Người nhận ");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Tổng tiền doanh thu ");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Tiền thực kiểm ");

            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Trạng thái");

            // Định dạng ngày tháng năm
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            // Định dạng giờ
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            // Định dạng tiền tệ Việt Nam
            DecimalFormat currencyFormat = new DecimalFormat("#,###.### VND");

            XSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd-MM-yyyy"));

            XSSFCellStyle timeCellStyle = workbook.createCellStyle();
            timeCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("HH:mm:ss"));

            XSSFCellStyle currencyCellStyle = workbook.createCellStyle();
            currencyCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("#,###.### VND"));

//            XSSFCellStyle currencyCellStyle = workbook.createCellStyle();
//            currencyCellStyle.setDataFormat((short) 8); // Số 8 tương ứng với định dạng tiền tệ 
            listGiaoCa = giaoCaService.selectALL();
            for (int i = 0; i < listGiaoCa.size(); i++) {
                String nguoiGiao = listGiaoCa.get(i).getNguoiGiao().getHo() + " " + listGiaoCa.get(i).getNguoiGiao().getTenDem() + " " + listGiaoCa.get(i).getNguoiGiao().getTen();
                String nguoiNhan = listGiaoCa.get(i).getNguoiNhan().getHo() + " " + listGiaoCa.get(i).getNguoiNhan().getTenDem() + " " + listGiaoCa.get(i).getNguoiNhan().getTen();
                Double tongTienDouble = Double.parseDouble(listGiaoCa.get(i).getTongCong().toString());
                Double tongTienTKDouble = Double.parseDouble(listGiaoCa.get(i).getThucKiem().toString());

                row = sheet.createRow(4 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(listGiaoCa.get(i).getMaGiaoCa());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(listGiaoCa.get(i).getCaLamViec());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(listGiaoCa.get(i).getNgayGiaoCa());
                cell.setCellStyle(dateCellStyle);

                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(listGiaoCa.get(i).getGioKiemKe());
                cell.setCellStyle(timeCellStyle);

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(nguoiGiao);

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(nguoiNhan);

                cell = row.createCell(7, CellType.NUMERIC);
                cell.setCellValue(tongTienDouble);
                cell.setCellStyle(currencyCellStyle);

                cell = row.createCell(8, CellType.NUMERIC);
                cell.setCellValue(tongTienTKDouble);
                cell.setCellStyle(currencyCellStyle);

                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(listGiaoCa.get(i).getTrangThai());
            }

            File file = new File("E:\\DemoExcel\\ds.xlsx");
            if (!file.exists()) {
                file.createNewFile();
            }

            try {
//                
                FileOutputStream fis = new FileOutputStream(file);
                workbook.write(fis);
                fis.close();

            } catch (FileNotFoundException ex) {
                ex.printStackTrace(System.out);

            }

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    // Validate chức năng tìm kiếm
    private boolean validateFind() {
        if (txtTuNgay.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ngày bắt đầu tìm kiếm");
            return false;
        }

        if (txtDenNgay.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ngày kết thúc tìm kiếm");
            return false;
        }
        // Kiểm tra xem ngày bắt đầu có lớn hơn ngày kết thúc không
        if (txtTuNgay.getDate().after(txtDenNgay.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc");
            return false;
        }

        return true;
    }

    // Chức năng in phiếu giao ca ra file PDF
    private void printPhieuGiaoCa() {
        GiaoCa giaoCa = getForm(); // Lấy đối tượng GiaoCa từ hàm getForm()

        if (giaoCa == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 phiếu muốn in ra trên table");
            return;
        }

        String filePath = "E:\\File_PDF\\phieugiaoca.pdf"; // Đường dẫn đến file PDF bạn muốn tạo
        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Sử dụng font Unicode để hỗ trợ tiếng Việt
            BaseFont unicodeFont = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(unicodeFont, 16, Font.BOLD);
            Font contentFont = new Font(unicodeFont, 12);

            document.open();

            // Tiêu đề thông tin phiếu giao ca căn giữa
            Paragraph title = new Paragraph("Thông tin phiếu giao ca", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date ngayGiaoCa = giaoCa.getNgayGiaoCa();
            String ngayGiaoCaFormat = simpleDateFormat.format(ngayGiaoCa);
            String nguoiGiao = giaoCa.getNguoiGiao().getHo() + " " + giaoCa.getNguoiGiao().getTenDem() + " " + giaoCa.getNguoiGiao().getTen();
            String nguoiNhan = giaoCa.getNguoiNhan().getHo() + " " + giaoCa.getNguoiNhan().getTenDem() + " " + giaoCa.getNguoiNhan().getTen();;
            DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
            String tongTien = decimalFormat.format(giaoCa.getTongCong());
            String tongTienThucKiem = decimalFormat.format(giaoCa.getThucKiem());

            document.add(new Paragraph("Mã ca làm việc: " + giaoCa.getMaGiaoCa(), contentFont));
            document.add(new Paragraph("Ca làm việc: " + giaoCa.getCaLamViec(), contentFont));
            document.add(new Paragraph("Ngày giao ca: " + ngayGiaoCaFormat, contentFont));
            document.add(new Paragraph("Người giao ca: " + nguoiGiao, contentFont));
            document.add(new Paragraph("Người nhận ca: " + nguoiNhan, contentFont));
            document.add(new Paragraph("Giờ thực kiểm: " + giaoCa.getGioKiemKe(), contentFont));
            document.add(new Paragraph("Tổng tiền: " + tongTien, contentFont));
            document.add(new Paragraph("Thực kiểm: " + tongTienThucKiem, contentFont));
            document.add(new Paragraph("Trạng thái: " + giaoCa.getTrangThai(), contentFont));
            // Add thêm các thông tin khác của đối tượng GiaoCa tương tự

            document.close();
            writer.close();

            System.out.println("File PDF đã được tạo thành công tại đường dẫn: " + filePath);
            JOptionPane.showMessageDialog(this, "Xuất File PDF thành công");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Chức năng tìm kiếm theo ngày
    private void findDataByTime() {
        if (validateFind()) {
            Date tuNgayDate = txtTuNgay.getDate();
            Date denNgayDate = txtDenNgay.getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

            String tuNgay = simpleDateFormat.format(tuNgayDate);
            String denNgay = simpleDateFormat.format(denNgayDate);

            listGiaoCa = giaoCaService.selectAllByDate(tuNgay, denNgay);
            fillToTableGiaoCa(listGiaoCa);
        }
    }

//    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        cboTrangThai = new javax.swing.JComboBox<>();
        cboCaLamViec = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        txtThucKiem = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        btnGioHienTai = new javax.swing.JButton();
        lblGioKiemKe = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnInPhieuGiaoCa = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lblMaPhieu = new javax.swing.JLabel();
        cboNguoiGiao = new javax.swing.JComboBox<>();
        cboNguoiNhan = new javax.swing.JComboBox<>();
        txtNgayGiaoCa = new com.toedter.calendar.JDateChooser();
        jPanel12 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblPhieuGiaoCa = new javax.swing.JTable();
        btnXuatFileExcel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCaLamViec = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cboNvTimKiem = new javax.swing.JComboBox<>();
        btnChon = new javax.swing.JButton();
        txtTuNgay = new com.toedter.calendar.JDateChooser();
        lblDoanhThu = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        txtDenNgay = new com.toedter.calendar.JDateChooser();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel11.setForeground(new java.awt.Color(255, 255, 255));
        jPanel11.setName(""); // NOI18N

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khớp", "Không khớp" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        cboCaLamViec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca Sáng", "Ca Chiều", "Ca Tối" }));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel41.setText("PHIẾU GiAO CA");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel42.setText("Người giao");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel43.setText("Người nhận");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setText("Giờ kiểm kê");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel45.setText("Ca làm việc");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel46.setText("Trạng thái");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel47.setText("Ngày giao ca ");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel48.setText("Thực kiểm");

        btnGioHienTai.setText("Giờ hiện tại");
        btnGioHienTai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGioHienTaiActionPerformed(evt);
            }
        });

        lblGioKiemKe.setText("#getTimeNow");

        jLabel2.setText("Tổng cộng");

        lblTongTien.setText("0.000.000");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        jLabel8.setText("Ghi chú:");

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRefresh.setText("Làm mới");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnInPhieuGiaoCa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInPhieuGiaoCa.setText("In phiếu giao ca");
        btnInPhieuGiaoCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPhieuGiaoCaActionPerformed(evt);
            }
        });

        jLabel15.setText("Mã phiếu");

        lblMaPhieu.setText("#maGC");

        cboNguoiGiao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboNguoiNhan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtNgayGiaoCa.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel46)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(jLabel48)
                            .addComponent(jLabel45)
                            .addComponent(jLabel47))
                        .addGap(33, 33, 33)))
                .addGap(12, 12, 12)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMaPhieu))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnInPhieuGiaoCa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTongTien)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayGiaoCa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                            .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtThucKiem)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(lblGioKiemKe, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGioHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboCaLamViec, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(cboNguoiGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblMaPhieu))
                .addGap(2, 2, 2)
                .addComponent(jLabel41)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboCaLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47)
                    .addComponent(txtNgayGiaoCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTongTien))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(cboNguoiGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(cboNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(btnGioHienTai)
                    .addComponent(lblGioKiemKe))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThucKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(btnInPhieuGiaoCa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Tìm phiếu giao ca");

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        tblPhieuGiaoCa.setBackground(new java.awt.Color(255, 255, 255));
        tblPhieuGiaoCa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu", "Ca", "Ngày", "Người giao", "Người nhận", "Tổng tiền", "Thực kiểm", "Giờ thực kiểm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuGiaoCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuGiaoCaMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblPhieuGiaoCa);

        btnXuatFileExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatFileExcel.setText("Xuất file excel ");
        btnXuatFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcelActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Ca làm: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Doanh thu ca:");

        lblCaLamViec.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCaLamViec.setForeground(new java.awt.Color(204, 0, 0));
        lblCaLamViec.setText("#CaLV");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Ngày:");

        jLabel11.setText("Từ ngày");

        jLabel13.setText("Nhân viên: ");

        jLabel14.setText("Đến ngày");

        cboNvTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnChon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChon.setText("Chọn");

        txtTuNgay.setDateFormatString("dd-MM-yyyy");

        lblDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDoanhThu.setForeground(new java.awt.Color(204, 0, 0));
        lblDoanhThu.setText("#Revenue");

        lblDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDate.setForeground(new java.awt.Color(204, 0, 0));
        lblDate.setText("#date");

        txtDenNgay.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnChon))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDoanhThu)
                                    .addComponent(lblCaLamViec)
                                    .addComponent(lblDate))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuatFileExcel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(cboNvTimKiem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(15, 15, 15))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57)
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel14))
                            .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cboNvTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(btnTimKiem)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnXuatFileExcel)
                                .addGap(34, 34, 34)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(lblCaLamViec))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(btnChon)
                                .addGap(43, 43, 43))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(lblDoanhThu))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1058, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 27, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 27, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addPhieuGiaoCa();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deletePhieuGiaoCa();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        updatePhieuGiaoCa();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        findDataByTime();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblPhieuGiaoCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuGiaoCaMouseClicked
        // TODO add your handling code here:
        showDetailtData();
    }//GEN-LAST:event_tblPhieuGiaoCaMouseClicked

    private void btnGioHienTaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGioHienTaiActionPerformed
        // TODO add your handling code here:
        setTime();
    }//GEN-LAST:event_btnGioHienTaiActionPerformed

    private void btnXuatFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcelActionPerformed
        // TODO add your handling code here:
        try {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn chắc chắc muốn xuất file ?", "Confirm ?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                xuatFileExcel();
                JOptionPane.showMessageDialog(this, "Xuất file thành công !");
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, "Không thể xuất file excel !");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatFileExcelActionPerformed

    private void btnInPhieuGiaoCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInPhieuGiaoCaActionPerformed
        row = tblPhieuGiaoCa.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu giao ca trong bảng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn in phiếu giao ca này ?", "Xác nhận in phiếu", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                printPhieuGiaoCa();
            }
        }


    }//GEN-LAST:event_btnInPhieuGiaoCaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnGioHienTai;
    private javax.swing.JButton btnInPhieuGiaoCa;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatFileExcel;
    private javax.swing.JComboBox<String> cboCaLamViec;
    private javax.swing.JComboBox<String> cboNguoiGiao;
    private javax.swing.JComboBox<String> cboNguoiNhan;
    private javax.swing.JComboBox<String> cboNvTimKiem;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JLabel lblCaLamViec;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblGioKiemKe;
    private javax.swing.JLabel lblMaPhieu;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblPhieuGiaoCa;
    private com.toedter.calendar.JDateChooser txtDenNgay;
    private javax.swing.JTextArea txtGhiChu;
    private com.toedter.calendar.JDateChooser txtNgayGiaoCa;
    private javax.swing.JTextField txtThucKiem;
    private com.toedter.calendar.JDateChooser txtTuNgay;
    // End of variables declaration//GEN-END:variables
}
