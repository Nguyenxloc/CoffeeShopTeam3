/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.CapBac;
import model.TaiKhoan;
import ultilities.Utilitys;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO T560
 */
public class DAO_TaoTaiKhoan {

    final String INSERT_SQL = "INSERT INTO NhanVien(Ma,Ho,TenDem,Ten,GioiTinh,NgaySinh,DiaChi,Sdt,MatKhau,IdCB)\n"
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";
    final String SELECT_CAPBAC_SQL = "SELECT id, Ten FROM CapBac";
    final String SELECT_PHONE_SQL = "SELECT Sdt FROM NhanVien";
    final String SELECT_BY_SQL = "SELECT COUNT(*) FROM nhanvien WHERE ma = ?";
    final String SELECT_ALL_SQL = "SELECT NhanVien.Ma, NhanVien.Ho + ' ' + NhanVien.TenDem + ' ' + NhanVien.Ten as TENNHANVIEN, GioiTinh,NgaySinh,DiaChi,Sdt,MatKhau,CapBac.Id,TrangThai  FROM NhanVien JOIN \n"
            + "CapBac ON NhanVien.IdCB = CapBac.Id";

//    private TaiKhoan acount;
    private ArrayList<TaiKhoan> listAcount;
    private Connection connection;

    public DAO_TaoTaiKhoan() throws Exception {
        connection = Utilitys.getConnection();
    }

    // Lấy dữ liệu tài khỏan
    public ArrayList<TaiKhoan> findAll() {
        listAcount = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaNV(rs.getString("Ma"));
                tk.setTenNV(rs.getString("TENNHANVIEN"));
                tk.setGioiTinh(rs.getString("GioiTinh"));
                tk.setNgaySinh(rs.getDate("NgaySinh"));
                tk.setDiaChi(rs.getString("DiaChi"));
                tk.setSoDT(rs.getString("Sdt"));
                tk.setMatKhau(rs.getString("MatKhau"));
//                tk.setIdCB(rs.getString("Id"));
                tk.setTrangThai(rs.getString("TrangThai"));
                listAcount.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listAcount;
    }

    // LẤY SỐ ĐIỆN THOẠI CỦA NHÂN VIÊN
    public ArrayList<String> getAllPhoneNumbers() {
        ArrayList<String> listPhones = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_PHONE_SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String phone = rs.getString("Sdt");
                listPhones.add(phone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listPhones;
    }

    // Lấy dữ liệu theo mã Nhân viên
    public int selectByID(String id) {
        Utilitys dbConn = new Utilitys();
        ArrayList<TaiKhoan> listAcount = new ArrayList<>();
        TaiKhoan tk = new TaiKhoan();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lấy dữ liệu CapBac đổ lêncomboBox
    public ArrayList<CapBac> getTenCapBac() {
        ArrayList<CapBac> listCapBac = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_CAPBAC_SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String idCB = rs.getString("id");
                String tenCB = rs.getString("Ten");
                CapBac capBac = new CapBac();
                capBac.setIdCB(idCB);
                capBac.setTenCB(tenCB);
                listCapBac.add(capBac);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TaoTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCapBac;
    }

//    public void save(TaiKhoan acount) {
//        String hashPasString = Utilitys.hashPassword(acount.getMatKhau());
//        try {
//            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
//            ps.setString(1, acount.getMaNV());
//            ps.setString(2, acount.getHoNV());
//            ps.setString(3, acount.getTenDemNV());
//            ps.setString(4, acount.getTenNV());
//            ps.setString(5, acount.getGioiTinh());
//            ps.setDate(6, new java.sql.Date(acount.getNgaySinh().getTime()));
//            ps.setString(7, acount.getDiaChi());
//            ps.setString(8, acount.getSoDT());
//            ps.setString(9, hashPasString);
//            ps.setString(10, acount.getCapBac().getIdCB());
//            int resualt = ps.executeUpdate();
//            System.out.println(resualt);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
