/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import ultilities.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import model.ThongTinSV;
/**
 *
 * @author Lê Chấn Khang
 */
public class DAO_ThongTinSinhVien {
 
    private DBConnection dBConnection = new DBConnection();
    
    
    public ArrayList<ThongTinSV> getAllTTSV(){
        ArrayList<ThongTinSV> lstThongTinSinhVien = new ArrayList<>();
        String sql = "select MASV,HOTEN,GIOITINH,NGAYSINH,EMAIL,SDT,DIACHI,TRANGTHAI,NIENKHOA,TENCHUYENNGANH from SINHVIEN join ChuyenNganh on SINHVIEN.MACHUYENNGANH=ChuyenNganh.MACHUYENNGANH";
        try (Connection con = dBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {   
                
                ThongTinSV thongTinSinhVien = new ThongTinSV();
                thongTinSinhVien.setMaSV(rs.getString("MASV"));
                thongTinSinhVien.setHoTen(rs.getString("HOTEN"));
                thongTinSinhVien.setGioitinh(rs.getInt("GIOITINH"));
                thongTinSinhVien.setNgaySinh(rs.getString("NGAYSINH"));
                thongTinSinhVien.setEmail(rs.getString("EMAIL"));
                thongTinSinhVien.setSoDienThoai(rs.getString("SDT"));
                thongTinSinhVien.setDiaChi(rs.getString("DIACHI"));
                thongTinSinhVien.setTrangThai(rs.getString("TRANGTHAI"));
                thongTinSinhVien.setNienKhoa(rs.getInt("NIENKHOA"));
                thongTinSinhVien.setMaChuyenNganh(rs.getString("TENCHUYENNGANH"));              
                lstThongTinSinhVien.add(thongTinSinhVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstThongTinSinhVien;
    }
    
    
    public Boolean addTTSV(ThongTinSV thongTinSV){
        String sql = "insert into SINHVIEN(MASV,HOTEN,GIOITINH,NGAYSINH,EMAIL,SDT,DIACHI,TRANGTHAI,NIENKHOA,MACHUYENNGANH) values(?,?,?,?,?,?,?,?,?,?)";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, thongTinSV.getMaSV());
                st.setObject(2, thongTinSV.getHoTen());
                st.setObject(3, thongTinSV.getGioitinh());
                st.setObject(4, thongTinSV.getNgaySinh());
                st.setObject(5, thongTinSV.getEmail());
                st.setObject(6, thongTinSV.getSoDienThoai());
                st.setObject(7, thongTinSV.getDiaChi());
                st.setObject(8, thongTinSV.getTrangThai());
                st.setObject(9, thongTinSV.getNienKhoa());
                st.setObject(10, thongTinSV.getMaChuyenNganh());
                int result = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean updateTTSV(String ma,ThongTinSV thongTinSV){
        String sql = "update SINHVIEN set HOTEN=?,GIOITINH=?,NGAYSINH=?,EMAIL=?,SDT=?,DIACHI=?,TRANGTHAI=?,NIENKHOA=?,MACHUYENNGANH=? where MASV=?";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
               
                st.setObject(1, thongTinSV.getHoTen());
                st.setObject(2, thongTinSV.getGioitinh());
                st.setObject(3, thongTinSV.getNgaySinh());
                st.setObject(4, thongTinSV.getEmail());
                st.setObject(5, thongTinSV.getSoDienThoai());
                st.setObject(6, thongTinSV.getDiaChi());
                st.setObject(7, thongTinSV.getTrangThai());
                st.setObject(8, thongTinSV.getNienKhoa());
                st.setObject(9, thongTinSV.getMaChuyenNganh());
                st.setObject(10, ma);
                int result = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
}
