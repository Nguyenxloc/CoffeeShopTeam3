/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_ThongKe_Repository;

import DoUong_ThongKe_Model.ChiTietDoUong;
import Model_DoUong_ThongKe.LoaiDoUong;
import java.sql.ResultSet;
import java.util.ArrayList;
import ultilities.DBConnection1;


/**
 *
 * @author ADMIN
 */
public class DAO_ChiTietDoUong implements iChiTietDoUong{
    
    final String INSERT_SQL = "INSERT INTO dbo.ChiTietDoUong(idLoaiDoUong,TenDoUong,GiaNhap,GiaBan,MoTa)VALUES(?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.ChiTietDoUong SET GiaNhap=?,GiaBan=?,MoTa=? WHERE TenDoUong=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[ChiTietDoUong] WHERE [Id] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[ChiTietDoUong] WHERE [Id] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[ChiTietDoUong] order by TenDoUong";
    final String SELECT_BY_UNIID = "SELECT * FROM [dbo].[ChiTietDoUong] WHERE [Id] = ?";

    @Override
    public ArrayList<ChiTietDoUong> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
        DAO_LoaiDoUong dAO_LoaiDoUong = new DAO_LoaiDoUong();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                LoaiDoUong loaiDoUong = dAO_LoaiDoUong.selectByID(rs.getString("idLoaiDoUong"));
                lstChiTietDoUong.add(new ChiTietDoUong(rs.getString("id"), rs.getString("TenDoUong"), rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("MoTa"), loaiDoUong));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstChiTietDoUong;
    }

    @Override
    public ChiTietDoUong selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        ChiTietDoUong chiTietDoUong = new ChiTietDoUong();
        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
        DAO_LoaiDoUong dAO_LoaiDoUong = new DAO_LoaiDoUong();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL, id);
            while (rs.next()) {
                LoaiDoUong loaiDoUong = dAO_LoaiDoUong.selectByID(rs.getString("idLoaiDoUong"));
                lstChiTietDoUong.add(new ChiTietDoUong(rs.getString("id"), rs.getString("TenDoUong"), rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("MoTa"), loaiDoUong));
                chiTietDoUong = lstChiTietDoUong.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietDoUong;
    }
    
    
    
    

    @Override
    public void save(ChiTietDoUong chiTietDoUong) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL,chiTietDoUong.getLoaiDoUong().getId(),chiTietDoUong.getTenDoUong(),chiTietDoUong.getGiaNhap(),chiTietDoUong.getGiaBan(), chiTietDoUong.getMoTa());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ChiTietDoUong chiTietDoUong) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_SQL,chiTietDoUong.getGiaNhap(),chiTietDoUong.getGiaBan(), chiTietDoUong.getMoTa(), chiTietDoUong.getTenDoUong());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(DELETE_SQL,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
