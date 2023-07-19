/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import model.HoaDon;
import ultilities.DBConnection1;

/**
 *
 * @author 84374
 */
public class DAO_HoaDon {

    final String INSERT_SQL = "INSERT INTO dbo.HoaDon(IdBan,IdKH,IdNV,Ma,NgayThanhToan,TinhTrangThanhToan,TrangThaiPhaChe,MaGiamGia)VALUES(?,?,?,?,?,0,0,?)";
    final String UPDATE_SQL = "UPDATE dbo.HoaDon SET IdBan=?, IdKH=?,IdNV=?,Ma=?,TinhTrangThanhToan=?,TrangThaiPhaChe=?,MaGiamGia=? WHERE Id=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[HoaDon] WHERE [Id] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[HoaDon] WHERE [Id] = ?";
    final String SELECT_ALL_SQL = "SELECT*FROM dbo.HoaDon;";
    
    
     public ArrayList<HoaDon> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
        DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                LoaiDoUong loaiDoUong = dAO_LoaiDoUong.selectByID(rs.getString("idLoaiDoUong"));
                lstChiTietDoUong.add(new ChiTietDoUong(rs.getString("id"), rs.getString("TenDoUong"), rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("MoTa"), rs.getBytes("HinhAnh"), loaiDoUong));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstChiTietDoUong;
    }


    public HoaDon selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        ChiTietDoUong chiTietDoUong = new ChiTietDoUong();
        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
        DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL, id);
            while (rs.next()) {
                LoaiDoUong loaiDoUong = dAO_LoaiDoUong.selectByID(rs.getString("idLoaiDoUong"));
                lstChiTietDoUong.add(new ChiTietDoUong(rs.getString("id"), rs.getString("TenDoUong"), rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("MoTa"), rs.getBytes("HinhAnh"), loaiDoUong));
                chiTietDoUong = lstChiTietDoUong.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietDoUong;
    }


    public void save(HoaDon hoaDon) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, chiTietDoUong.getLoaiDoUong().getId(), chiTietDoUong.getTenDoUong(), chiTietDoUong.getGiaNhap(), chiTietDoUong.getGiaBan(), chiTietDoUong.getHinhAnh(), chiTietDoUong.getMoTa());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(HoaDon hoaDon) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_SQL, chiTietDoUong.getTenDoUong(), chiTietDoUong.getGiaNhap(), chiTietDoUong.getGiaBan(), chiTietDoUong.getMoTa(), chiTietDoUong.getHinhAnh(), chiTietDoUong.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void delete(String id) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(DELETE_SQL, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public ArrayList<HoaDon> selectByFlexibleCondition(String tenDoUong, String idLoaiDoUong, double giaBatDau, double giaKetThuc) {
//        DBConnection1 dbConn = new DBConnection1();
//        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
//        DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
//        try {
//            System.out.println(idLoaiDoUong);
//            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_MULIPLECONDITION2, tenDoUong, idLoaiDoUong);
//            while (rs.next()) {
//                System.out.println("test");
//                LoaiDoUong loaiDoUong = dAO_LoaiDoUong.selectByID(rs.getString("idLoaiDoUong"));
//                lstChiTietDoUong.add(new ChiTietDoUong(rs.getString("id"), rs.getString("TenDoUong"), rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("MoTa"), rs.getBytes("HinhAnh"), loaiDoUong));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return lstChiTietDoUong;
//
//    }
}
