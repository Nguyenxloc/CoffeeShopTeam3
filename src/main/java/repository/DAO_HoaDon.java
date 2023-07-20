/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.Ban;
import model.GiamGia;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import ultilities.DBConnection1;

/**
 *
 * @author 84374
 */
public class DAO_HoaDon {

    final String INSERT_SQL = "INSERT INTO dbo.HoaDon(IdBan,IdKH,IdNV,NgayThanhToan,TinhTrangThanhToan,TrangThaiPhaChe,MaGiamGia,Stt)VALUES(?,?,?,?,0,0,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.HoaDon SET IdBan=?, IdKH=?,IdNV=?,Ma=?,TinhTrangThanhToan=?,TrangThaiPhaChe=?,MaGiamGia=? WHERE Id=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[HoaDon] WHERE [Id] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[HoaDon] WHERE [Id] = ?";
    final String SELECT_ALL_SQL = "SELECT*FROM dbo.HoaDon where stt = 1;";
    final String SELECT_ALL_SQL_HOADONCHO = "SELECT*FROM dbo.HoaDon where stt = 0;";
    final String UPDATE_STT = "UPDATE dbo.HoaDon SET stt=? WHERE Id=?";
    DAO_Ban dao_Ban = new DAO_Ban();
    DAO_KhachHang dao_KhachHang = new DAO_KhachHang();
    DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
    DAO_GiamGia dao_GiamGia = new DAO_GiamGia();

    public ArrayList<HoaDon> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                Ban ban = dao_Ban.selectByID(rs.getInt("IdBan"));
//                KhachHang khachHang = dao_KhachHang.selectByID("IdKH");
//                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
//                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                KhachHang khachHang = null;
                NhanVien nhanVien = null;
                GiamGia giamGia = null;
                lstHoaDon.add(new HoaDon(rs.getString("id"), ban, khachHang, nhanVien, rs.getString("Ma"), rs.getString("NgayTao"),
                        rs.getString("NgayThanhToan"), rs.getInt("TinhTrangThanhToan"), rs.getInt("TrangThaiPhaChe"), giamGia,rs.getInt("Stt")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

    public ArrayList<HoaDon> selectHoaDonCho() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL_HOADONCHO);
            while (rs.next()) {
                Ban ban = dao_Ban.selectByID(rs.getInt("IdBan"));
//                KhachHang khachHang = dao_KhachHang.selectByID("IdKH");
//                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
//                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                KhachHang khachHang = null;
                NhanVien nhanVien = null;
                GiamGia giamGia = null;
                lstHoaDon.add(new HoaDon(rs.getString("id"), ban, khachHang, nhanVien, rs.getString("Ma"), rs.getString("NgayTao"),
                        rs.getString("NgayThanhToan"), rs.getInt("TinhTrangThanhToan"), rs.getInt("TrangThaiPhaChe"), giamGia,rs.getInt("Stt")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

    public HoaDon selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        HoaDon hoaDon = new HoaDon();
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL, id);
            while (rs.next()) {
                Ban ban = dao_Ban.selectByID(rs.getInt("IdBan"));
//                KhachHang khachHang = dao_KhachHang.selectByID("IdKH");
//                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
//                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                KhachHang khachHang = null;
                NhanVien nhanVien = null;
                GiamGia giamGia = null;
                lstHoaDon.add(new HoaDon(rs.getString("id"), ban, khachHang, nhanVien, rs.getString("Ma"), rs.getString("NgayTao"),
                        rs.getString("NgayThanhToan"), rs.getInt("TinhTrangThanhToan"), rs.getInt("TinhTrangPhaChe"), giamGia,rs.getInt("Stt")));
                hoaDon = lstHoaDon.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDon;
    }

    public void save(HoaDon hoaDon) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, hoaDon.getBan().getIdBan(), null, null,
                    hoaDon.getNgayThanhToan(),null,hoaDon.getStt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(HoaDon hoaDon) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_SQL, hoaDon.getBan().getIdBan(), hoaDon.getKhachHang().getId(), hoaDon.getNhanVien().getId(),
                    hoaDon.getMa(), hoaDon.getTinhTrangThanhToan(), hoaDon.getTrangThaiPhaChe(), hoaDon.getMaGiamGia());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStt(int stt, String id) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_STT, stt, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void delete(String id) {
//        DBConnection1 dbConn = new DBConnection1();
//        try {
//            dbConn.ExcuteSQL(DELETE_SQL, id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
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
