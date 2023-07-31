/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
//import model.ChiTietDoUong;
import model.KhuyenMai;
//import model.LoaiDoUong;
//import ultilities.DBConnection1;
import java.sql.*;
import model.ChiTietDoUong;
import model.GiaoCa;
import model.LoaiDoUong;
import model.NhanVien;
import service.NhanVienService;
import ultilities.DBConnection1;
//import ultilities.DBConnection;
import ultilities.Utilitys;

/**
 *
 * @author LENOVO T560
 */
public class DAO_GiaoCa {

    private Connection connection = Utilitys.openDbConnection();

    final String INSERT_SQL = "INSERT INTO dbo.ChiTietDoUong(idLoaiDoUong,TenDoUong,GiaNhap,GiaBan,HinhAnh,MoTa)VALUES(?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.ChiTietDoUong SET TenDoUong=?, GiaNhap=?,GiaBan=?,MoTa=?,HinhAnh=? WHERE Id=?";
    final String DELETE_SQL = " DELETE GIAOCA WHERE MaGiaoCa = ?";
    final String SELECT_BY_DATE_SQL = "SELECT MaGiaoCa, CaLamViec, NgayGiaoCa, NguoiGiao, NguoiNhan, SoTienTrenHeThong, SoTienThucKiem, GioKiemKe, TrangThai, GhiChu\n"
            + "FROM GiaoCa\n"
            + "WHERE CONVERT(VARCHAR, GiaoCa.NgayGiaoCa, 105) BETWEEN ? AND ?";
    final String SELECT_ALL_SQL = "SELECT MaGiaoCa,CaLamViec, NgayGiaoCa,NguoiGiao,NguoiNhan, SoTienTrenHeThong,SoTienThucKiem,GioKiemKe,TrangThai,GhiChu FROM GiaoCa";

    public ArrayList<GiaoCa> selectALL() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<GiaoCa> listGiaoCa = new ArrayList<>();
        NhanVienService service = new NhanVienService();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);

            while (rs.next()) {
                String nguoiGiaoString = rs.getString("NguoiGiao");
                String nguoiNhanString = rs.getString("NguoiNhan");

                NhanVien nguoiGiao = service.selectByIDNhanVien(nguoiGiaoString);
                NhanVien nguoiNhan = service.selectByIDNhanVien(nguoiNhanString);
                GiaoCa giaoCa = new GiaoCa();
                giaoCa.setMaGiaoCa(rs.getString("MaGiaoCa"));
                giaoCa.setCaLamViec(rs.getString("CaLamViec"));
                giaoCa.setNgayGiaoCa(rs.getDate("NgayGiaoCa"));
                giaoCa.setNguoiGiao(nguoiGiao);
                giaoCa.setNguoiNhan(nguoiNhan);
                giaoCa.setTongCong(rs.getBigDecimal("SoTienTrenHeThong"));
                giaoCa.setThucKiem(rs.getBigDecimal("SoTienThucKiem"));
                giaoCa.setGioKiemKe(rs.getTime("GioKiemKe"));
                giaoCa.setTrangThai(rs.getString("TrangThai"));
                listGiaoCa.add(giaoCa);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listGiaoCa;
    }

    public ArrayList<GiaoCa> selectAllByDate(String tuNgay, String denNgay) {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<GiaoCa> listGiaoCa = new ArrayList<>();
        NhanVienService service = new NhanVienService();
        try {
            Connection connection = dbConn.openDbConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_DATE_SQL);
            ps.setString(1, tuNgay);
            ps.setString(2, denNgay);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nguoiGiaoString = rs.getString("NguoiGiao");
                String nguoiNhanString = rs.getString("NguoiNhan");

                NhanVien nguoiGiao = service.selectByIDNhanVien(nguoiGiaoString);
                NhanVien nguoiNhan = service.selectByIDNhanVien(nguoiNhanString);
                GiaoCa giaoCa = new GiaoCa();
                giaoCa.setMaGiaoCa(rs.getString("MaGiaoCa"));
                giaoCa.setCaLamViec(rs.getString("CaLamViec"));
                giaoCa.setNgayGiaoCa(rs.getDate("NgayGiaoCa"));
                giaoCa.setNguoiGiao(nguoiGiao);
                giaoCa.setNguoiNhan(nguoiNhan);
                giaoCa.setTongCong(rs.getBigDecimal("SoTienTrenHeThong"));
                giaoCa.setThucKiem(rs.getBigDecimal("SoTienThucKiem"));
                giaoCa.setGioKiemKe(rs.getTime("GioKiemKe"));
                giaoCa.setTrangThai(rs.getString("TrangThai"));
                listGiaoCa.add(giaoCa);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listGiaoCa;
    }
//    public ArrayList<KhuyenMai> getSelectAll() {
//        Utilitys dbConn = new Utilitys();
//        ArrayList<KhuyenMai> lstKM = new ArrayList<>();
////        DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
//        try {
//            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
//            while (rs.next()) {
//                KhuyenMai km = new KhuyenMai();
//                km.setMaKM(rs.getString("MaGiamGia"));
//                km.setTenKM(rs.getString("TENKHUYENMAI"));
//                km.setLoaiKM(rs.getString("LOAIKHUYENMAI"));
//                km.setGiaTri(rs.getInt("GIATRI"));
//                km.setNgayBatDau(rs.getDate("NgayTao"));
//                km.setNgayKetThuc(rs.getDate("NGAYKETTHUC"));
//                km.setTrangThai(rs.getString("TRANGTHAI"));
//                lstKM.add(km);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return lstKM;
//    }
//    public ChiTietDoUong selectByID(String id) {
//        DBConnection1 dbConn = new DBConnection1();
//        ChiTietDoUong chiTietDoUong = new ChiTietDoUong();
//        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
//        DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
//        try {
//            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL, id);
//            while (rs.next()) {
//                LoaiDoUong loaiDoUong = dAO_LoaiDoUong.selectByID(rs.getString("idLoaiDoUong"));
//                lstChiTietDoUong.add(new ChiTietDoUong(rs.getString("id"), rs.getString("TenDoUong"), rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("MoTa"), rs.getBytes("HinhAnh"), loaiDoUong));
//                chiTietDoUong = lstChiTietDoUong.get(0);
//                break;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return chiTietDoUong;
//    }
//    public void save(ChiTietDoUong chiTietDoUong) {
//        DBConnection1 dbConn = new DBConnection1();
//        try {
//            dbConn.ExcuteSQL(INSERT_SQL, chiTietDoUong.getLoaiDoUong().getId(), chiTietDoUong.getTenDoUong(), chiTietDoUong.getGiaNhap(), chiTietDoUong.getGiaBan(), chiTietDoUong.getHinhAnh(), chiTietDoUong.getMoTa());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(ChiTietDoUong chiTietDoUong) {
//        DBConnection1 dbConn = new DBConnection1();
//        try {
//            dbConn.ExcuteSQL(UPDATE_SQL, chiTietDoUong.getTenDoUong(), chiTietDoUong.getGiaNhap(), chiTietDoUong.getGiaBan(), chiTietDoUong.getMoTa(), chiTietDoUong.getHinhAnh(), chiTietDoUong.getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//

    public void delete(String id) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(DELETE_SQL, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
