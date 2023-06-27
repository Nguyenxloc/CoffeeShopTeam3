/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import model.DichVu;
import ultilities.DBConnection;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class DAO_DichVu {
    private DBConnection dBConnection = new DBConnection();
    
    public ArrayList<DichVu> getListDV2023(){
        ArrayList<DichVu> lit = new ArrayList<>();
        String sql = "select MADV,TENDV,PHONGBANPHUTRACH from DICHVU";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {                
                DichVu dichVu = new DichVu();
                dichVu.setMaDV(rs.getString("MADV"));
                dichVu.setTenDV(rs.getString("TENDV"));
                dichVu.setPhongBanPhuTrach(rs.getString("PHONGBANPHUTRACH"));
                lit.add(dichVu);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return lit;
    }
    
    
    public Boolean addDV2023(DichVu dichVu){
        String sql = "insert into DICHVU(MADV,TENDV,PHONGBANPHUTRACH) values(?,?,?)";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, dichVu.getMaDV());
                st.setObject(2, dichVu.getTenDV());
                st.setObject(3, dichVu.getPhongBanPhuTrach());
                int result = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    
    public Boolean updateDV2023(String ma,DichVu dichVu){
        String sql = "update DICHVU set TENDV=?,PHONGBANPHUTRACH=? where MADV=?";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {     
                st.setObject(1, dichVu.getTenDV());
                st.setObject(2, dichVu.getPhongBanPhuTrach());
                st.setObject(3, ma);
                int result = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    
    public DichVu getByMaDV2023(String ma){
        String sql = "select MADV,TENDV,PHONGBANPHUTRACH from DICHVU where MADV=?";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
              st.setObject(1, ma);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {                
                DichVu dichVu = new DichVu();
                dichVu.setMaDV(rs.getString("MADV"));
                dichVu.setTenDV(rs.getString("TENDV"));
                dichVu.setPhongBanPhuTrach(rs.getString("PHONGBANPHUTRACH"));
                return dichVu;
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
    
    public Boolean delete2023(String ma){
        String sql = "delete DICHVU where MADV=?";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, ma);
                int result = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
        }
        return false;
    }
    
    
}
