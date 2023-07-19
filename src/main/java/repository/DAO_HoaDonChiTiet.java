/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.HoaDonChiTiet;
import ultilities.DBConnection1;

/**
 *
 * @author 84374
 */
public class DAO_HoaDonChiTiet {

    final String INSERT_SQL = "INSERT INTO dbo.HoaDonChiTiet(IdHoaDon,IdChiTietDoUong,SoLuong)VALUES(NULL,NULL,NULL)";
    final String UPDATE_SQL = "UPDATE dbo.HoaDonChiTiet SET SoLuong=? WHERE IdHoaDon=?,IdChiTietDoUong=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[HoaDonChiTiet] WHERE IdHoaDon=?,IdChiTietDoUong=?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[HoaDonChiTiet] WHERE IdHoaDon=?,IdChiTietDoUong=?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[HoaDonChiTiet];";
    
    
        public ArrayList<HoaDonChiTiet> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                //get hoaDon
                //get chiTietDoUong
                lstHoaDonChiTiet.add(new HoaDonChiTiet(rs.getString("IdHoaDon"), rs.getString("IdChiTietDoUong"), rs.getNString("SoLuong")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstLoaiDoUong;
    }


    public LoaiDoUong selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        LoaiDoUong loaiDoUong = new LoaiDoUong();
        ArrayList<LoaiDoUong> lstLoaiDoUong = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            while (rs.next()) {
                lstLoaiDoUong.add(new LoaiDoUong(rs.getString("Id"), rs.getString("Ma"), rs.getString("Ten")));
                loaiDoUong = lstLoaiDoUong.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loaiDoUong;
    }


    public void save(LoaiDoUong loaiDoUong) {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<LoaiDoUong> lstLoaiDoUong = new ArrayList<>();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, loaiDoUong.getMa(), loaiDoUong.getTenLoaiDoUong());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(LoaiDoUong loaiDoUong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
