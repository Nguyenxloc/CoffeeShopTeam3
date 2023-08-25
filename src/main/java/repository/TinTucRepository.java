/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import com.view.model.QuanLyKhuyenMai;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.ChiTietDoUong;
import model.KhuyenMai;
import model.LoaiDoUong;
import model.NhanVien;
import model.TinTuc;
import ultilities.DBConnection1;

/**
 *
 * @author ADMIN
 */
public class TinTucRepository {

    final String INSERT_SQL = "INSERT INTO dbo.tintuc(TieuDe,MoTa,NoiDung,IdNv,NgayTao,HinhAnh)VALUES(?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.tintuc SET TieuDe=?, MoTa=?,NoiDung=?,IdNv=?,NgayTao=? ,HinhAnh=? WHERE Id=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[tintuc] WHERE [Id] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[tintuc] WHERE [Id] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[tintuc] ORDER BY IdLoaiDoUong;";
    final String SELECT_BY_UNIID = "SELECT * FROM [dbo].[tintuc] WHERE [Id] = ?";
    final String SELECT_BY_MULIPLECONDITION = "DECLARE @tenDoUong AS NVARCHAR(50) = ?, @idLoaiDoUong AS uniqueidentifier =?,@giaBatDau as decimal(20, 0)=?,@giaKeThuc as decimal(20, 0)=?"
            + "SELECT*FROM dbo.ChiTietDoUong \n"
            + "WHERE (@tenDoUong IS NULL OR TenDoUong=@tenDoUong) AND (@IdLoaiDoUong IS NULL OR IdLoaiDoUong=@idLoaiDoUong) AND (@giaBatDau =0 OR GiaBan>=@giaBatDau) AND (@giaKetThuc =0 OR GiaBan<=@giaKetThuc)";

    final String SELECT_BY_MULIPLECONDITION2 = "DECLARE @tenDoUong AS NVARCHAR(50) =?, @idLoaiDoUong AS varchar(50) =?\n"
            + "SELECT*FROM dbo.ChiTietDoUong\n"
            + "WHERE (@tenDoUong IS NULL OR TenDoUong=@tenDoUong) AND (@IdLoaiDoUong IS NULL OR IdLoaiDoUong=@idLoaiDoUong)";

    public TinTucRepository() {
    }


    public ArrayList<TinTuc> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<TinTuc> lstTinTuc = new ArrayList<>();
        NhanVienRepository nhanVienRepository = new NhanVienRepository();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                NhanVien idNV = nhanVienRepository.timIDNhanVien(rs.getString("IdNv"));
                lstTinTuc.add(new TinTuc("",rs.getString("TieuDe"), rs.getString("MoTa"), rs.getString("NoiDung"), idNV, rs.getDate("NgayTao"), rs.getBytes("HinhAnh")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTinTuc;
    }


    public ChiTietDoUong selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        ChiTietDoUong chiTietDoUong = new ChiTietDoUong();
        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
        DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
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


    public void save(ChiTietDoUong chiTietDoUong) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, chiTietDoUong.getLoaiDoUong().getId(), chiTietDoUong.getTenDoUong(), chiTietDoUong.getGiaNhap(), chiTietDoUong.getGiaBan(), chiTietDoUong.getHinhAnh(), chiTietDoUong.getMoTa());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(ChiTietDoUong chiTietDoUong) {
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


    public ArrayList<ChiTietDoUong> selectByFlexibleCondition(String tenDoUong, String idLoaiDoUong, double giaBatDau, double giaKetThuc) {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
        DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
        try {
            System.out.println(idLoaiDoUong);
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_MULIPLECONDITION2, tenDoUong, idLoaiDoUong);
            while (rs.next()) {
                System.out.println("test");
                LoaiDoUong loaiDoUong = dAO_LoaiDoUong.selectByID(rs.getString("idLoaiDoUong"));
                lstChiTietDoUong.add(new ChiTietDoUong(rs.getString("id"), rs.getString("TenDoUong"), rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("MoTa"), rs.getBytes("HinhAnh"), loaiDoUong));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstChiTietDoUong;

    }

}
