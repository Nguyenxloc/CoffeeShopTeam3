/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import model.DiemThanhPhan;
import ultilities.DBConnection;
import java.sql.*;
import model.ChuyenNganh;
import model.LopHoc;
import model.MonHoc;
import model.SinhVien;
/**
 *
 * @author ADMIN
 */
public class DAO_DiemThanhPhan {
    
    private DBConnection dBConnection = new DBConnection();
    
    public ArrayList<DiemThanhPhan> getList(){
        ArrayList<DiemThanhPhan> list = new ArrayList<>();
        String sql = "select * from DIEMTHANHPHAN";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {                
                   DiemThanhPhan thanhPhan = new DiemThanhPhan();
                   thanhPhan.setMaSV(rs.getString(1));
                   thanhPhan.setMaMonHoc(rs.getString(2));
                   thanhPhan.setMaNganhHoc(rs.getString(3));
                   thanhPhan.setMaLopHoc(rs.getString(4));
                   thanhPhan.setNam(rs.getInt(5));
                   thanhPhan.setMua(rs.getString(6));
                   thanhPhan.setLab1(rs.getDouble(7));
                   thanhPhan.setLab2(rs.getDouble(8));
                   thanhPhan.setLab3(rs.getDouble(9));
                   thanhPhan.setLab4(rs.getDouble(10));
                   thanhPhan.setAssignment(rs.getDouble(11));
                   thanhPhan.setDiemThi(rs.getDouble(12));
                   list.add(thanhPhan);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return list;
    }
    
    
    public ArrayList<SinhVien> getSV(){
        ArrayList<SinhVien> litSV = new ArrayList<>();
        String sql = "select MASV from SINHVIEN";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
              ResultSet rs = st.executeQuery();
              while (rs.next()) {                
                SinhVien sinhVien = new SinhVien();
                sinhVien.setMaSV(rs.getString("MASV"));
                litSV.add(sinhVien);
            }
                
        } catch (Exception e) {
             e.printStackTrace();
        }
        return litSV;
    }
    
    public ArrayList<LopHoc> getLH(){
        ArrayList<LopHoc> litSV = new ArrayList<>();
        String sql = "select MALOPHOC from LOPHOC";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
              ResultSet rs = st.executeQuery();
              while (rs.next()) {                
                LopHoc lopHoc = new LopHoc();
                lopHoc.setMaLopHoc(rs.getString("MALOPHOC"));
                litSV.add(lopHoc);
            }
                
        } catch (Exception e) {
             e.printStackTrace();
        }
        return litSV;
    }
    
    public ArrayList<ChuyenNganh> getCNganh(){
        ArrayList<ChuyenNganh> litSV = new ArrayList<>();
        String sql = "select MACHUYENNGANH from CHUYENNGANH";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
              ResultSet rs = st.executeQuery();
              while (rs.next()) {                
                ChuyenNganh chuyenNganh = new ChuyenNganh();
                chuyenNganh.setMaChuyenNganh(rs.getString("MACHUYENNGANH"));
                litSV.add(chuyenNganh);
            }
                
        } catch (Exception e) {
             e.printStackTrace();
        }
        return litSV;
    }
    
    public ArrayList<MonHoc> getMHoc(){
        ArrayList<MonHoc> litSV = new ArrayList<>();
        String sql = "select MAMONHOC from MONHOC";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
              ResultSet rs = st.executeQuery();
              while (rs.next()) {                
                MonHoc monHoc = new MonHoc();
                monHoc.setMaMonHoc(rs.getString("MAMONHOC"));
                litSV.add(monHoc);
            }
                
        } catch (Exception e) {
             e.printStackTrace();
        }
        return litSV;
    }
    
    
    public Boolean themDiem(DiemThanhPhan diemThanhPhan){
        String sql = "insert into DIEMTHANHPHAN values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                 st.setObject(1, diemThanhPhan.getMaSV());
                 st.setObject(2, diemThanhPhan.getMaMonHoc());
                 st.setObject(3, diemThanhPhan.getMaNganhHoc());
                 st.setObject(4, diemThanhPhan.getMaLopHoc());
                 st.setObject(5, diemThanhPhan.getNam());
                 st.setObject(6, diemThanhPhan.getMua());
                 st.setObject(7, diemThanhPhan.getLab1());
                 st.setObject(8, diemThanhPhan.getLab2());
                 st.setObject(9, diemThanhPhan.getLab3());
                 st.setObject(10, diemThanhPhan.getLab4());
                 st.setObject(11, diemThanhPhan.getAssignment());
                 st.setObject(12, diemThanhPhan.getDiemThi());
                 int result = st.executeUpdate();
                 return result>0;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    
    
    
    public Boolean suaDiem(String maSV,DiemThanhPhan diemThanhPhan){
        String sql ="update DIEMTHANHPHAN set MAMONHOC=?,MACHUYENNGANH=?,MALOPHOC=?,NAM=?,MUA=?,LAB1=?,LAB2=?,LAB3=?,LAB4=?,ASSIGNMENT=?,DIEMTHI=? where MASV=?";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                 
                 st.setObject(1, diemThanhPhan.getMaMonHoc());
                 st.setObject(2, diemThanhPhan.getMaNganhHoc());
                 st.setObject(3, diemThanhPhan.getMaLopHoc());
                 st.setObject(4, diemThanhPhan.getNam());
                 st.setObject(5, diemThanhPhan.getMua());
                 st.setObject(6, diemThanhPhan.getLab1());
                 st.setObject(7, diemThanhPhan.getLab2());
                 st.setObject(8, diemThanhPhan.getLab3());
                 st.setObject(9, diemThanhPhan.getLab4());
                 st.setObject(10, diemThanhPhan.getAssignment());
                 st.setObject(11, diemThanhPhan.getDiemThi());
                 st.setObject(12, maSV);
                 int result = st.executeUpdate();
                 return result>0;
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        return false;
    }
    
    
    public DiemThanhPhan getByMaSV(String maSV){
        String sql = "select MACHUYENNGANH from CHUYENNGANH where MASV=?";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, maSV);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {                
                   DiemThanhPhan thanhPhan = new DiemThanhPhan();
                   thanhPhan.setMaSV(rs.getString(1));
                   thanhPhan.setMaMonHoc(rs.getString(2));
                   thanhPhan.setMaNganhHoc(rs.getString(3));
                   thanhPhan.setMaLopHoc(rs.getString(4));
                   thanhPhan.setNam(rs.getInt(5));
                   thanhPhan.setMua(rs.getString(6));
                   thanhPhan.setLab1(rs.getDouble(7));
                   thanhPhan.setLab2(rs.getDouble(8));
                   thanhPhan.setLab3(rs.getDouble(9));
                   thanhPhan.setLab4(rs.getDouble(10));
                   thanhPhan.setAssignment(rs.getDouble(11));
                   thanhPhan.setDiemThi(rs.getDouble(12));
                   return thanhPhan;
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        return null;
    }
    
    
    
}
