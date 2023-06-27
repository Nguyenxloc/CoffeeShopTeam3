/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import model.DVDangKy;
import ultilities.DBConnection;
import java.sql.*;
/**
 *
 * @author Lê Chấn Khang
 */
public class DAO_DVDK20 {
    private DBConnection dBConnection = new DBConnection();
    
    public ArrayList<DVDangKy> getList3(){
        ArrayList<DVDangKy> lit = new ArrayList<>();
        String sql = "select SINHVIEN.MASV,SINHVIEN.HOTEN,DICHVU.TENDV,DICHVU.MOTA,DICHVUSVDADANGKY.TRANGTHAI from DICHVUSVDADANGKY join DICHVU on DICHVUSVDADANGKY.MADV=DICHVU.MADV join SINHVIEN on DICHVUSVDADANGKY.MASV=SINHVIEN.MASV";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {                
                   DVDangKy dangKy = new DVDangKy();
                   dangKy.setMasv(rs.getString(1));
                   dangKy.setHoten(rs.getString(2));
                   dangKy.setTenDV(rs.getString(3));
                   dangKy.setMoTa(rs.getString(4));
                   dangKy.setTrangThai(rs.getString(5));
                   lit.add(dangKy);
            }
        } catch (Exception e) {
        }
        return lit;
    }
    
    public Boolean update3(String masv,DVDangKy dVDangKy){
        String sql = "update DICHVUSVDADANGKY set TRANGTHAI=? where MASV =?";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, dVDangKy.getTrangThai());
                st.setObject(2, masv);
                int result = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
        }
        return false;
    }
    
    
}
