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

    final String INSERT_SQL = "INSERT INTO GiaoCa(MaGiaoCa,NgayGiaoCa,NguoiGiao,NguoiNhan,GioKiemKe, SoTienTrenHeThong,SoTienThucKiem,TrangThai,GhiChu,CaLamViec) \n"
            + "VALUES(?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE GIAOCA\n"
            + "SET NGAYGIAOCA = ?,\n"
            + "    NGUOIGIAO = ?,\n"
            + "    NGUOINHAN = ?,\n"
            + "    GIOKIEMKE = ?,\n"
            + "    SOTIENTRENHETHONG = ?,\n"
            + "	   SOTIENTHUCKIEM = ?,\n"
            + "    TRANGTHAI = ?,\n"
            + "	   CALAMVIEC = ?,\n"
            + "    GHICHU = ?\n"
            + "WHERE MAGIAOCA = ?";
    final String DELETE_SQL = " DELETE GIAOCA WHERE MaGiaoCa = ?";
    final String SELECT_BY_DATE_SQL = "SELECT MaGiaoCa, CaLamViec, NgayGiaoCa, NguoiGiao, NguoiNhan, SoTienTrenHeThong, SoTienThucKiem, GioKiemKe, TrangThai, GhiChu\n"
            + "FROM GiaoCa\n"
            + "WHERE CONVERT(VARCHAR, GiaoCa.NgayGiaoCa, 105) BETWEEN ? AND ?";
    final String SELECT_ALL_SQL = "SELECT MaGiaoCa,CaLamViec, NgayGiaoCa,NguoiGiao,NguoiNhan, SoTienTrenHeThong,SoTienThucKiem,GioKiemKe,TrangThai,GhiChu FROM GiaoCa";
    final String SELECT_REVENUE_SQL = "select  HoaDon.Ma, NhanVien.Ten, convert(varchar, hoadon.NgayTao, 105) as 'NgayTao',convert(varchar, hoadon.ThoiGian, 105) as 'ThoiGian',\n"
            + "                         (sum(SoLuong)) as 'SoLuongSP', sum(ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong) as 'TongTien',CASE WHEN dbo.HoaDon.MaGiamGia IS NULL THEN 0 ELSE dbo.GiamGia.GiaTri END AS giatri,\n"
            + "						 HoaDon.TinhTrangThanhToan, sum(ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong - 0*(ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong)*(CASE WHEN dbo.HoaDon.MaGiamGia IS NULL THEN 0 ELSE dbo.GiamGia.GiaTri END )/100) as 'ThucNhan' from HoaDonChiTiet\n"
            + "                         LEFT JOIN ChiTietDoUong on HoaDonChiTiet.IdChiTietDoUong = ChiTietDoUong.Id\n"
            + "                         LEFT JOIN HoaDon on HoaDonChiTiet.IdHoaDon = HoaDon.Id\n"
            + "                         LEFT JOIN NhanVien on HoaDon.IdNV = NhanVien.Id\n"
            + "                         LEFT JOIN GiamGia on GiamGia.MaGiamGia = HoaDon.MaGiamGia\n"
            + "						 WHERE TinhTrangThanhToan = 1\n"
            + "                         group by HoaDon.Ma,dbo.HoaDon.MaGiamGia, NhanVien.Ten, HoaDon.NgayTao, HoaDon.ThoiGian, GiamGia.GiaTri, HoaDon.Stt,dbo.HoaDon.TinhTrangThanhToan,dbo.HoaDon.SoTienNhanVao";

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

    public void save(GiaoCa giaoCa) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, giaoCa.getMaGiaoCa(), giaoCa.getNgayGiaoCa(),
                    giaoCa.getNguoiGiao().getId(),
                    giaoCa.getNguoiNhan().getId(), giaoCa.getGioKiemKe(),
                    giaoCa.getTongCong(), giaoCa.getThucKiem(), giaoCa.getTrangThai(), giaoCa.getGhiChu(), giaoCa.getCaLamViec());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String maGiaoCa, GiaoCa giaoCa) {
        DBConnection1 dbConn = new DBConnection1();
        Connection connection = dbConn.openDbConnection();
        try {

            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setDate(1, new java.sql.Date(giaoCa.getNgayGiaoCa().getTime()));
            ps.setObject(2, giaoCa.getNguoiGiao().getId());
            ps.setObject(3, giaoCa.getNguoiNhan().getId());
            ps.setObject(4, new java.sql.Time(giaoCa.getGioKiemKe().getTime()));
            ps.setObject(5, giaoCa.getTongCong());
            ps.setObject(6, giaoCa.getThucKiem());
            ps.setObject(7, giaoCa.getTrangThai());
            ps.setObject(8, giaoCa.getCaLamViec());
            ps.setObject(9, giaoCa.getGhiChu());
            ps.setObject(10, maGiaoCa);

            int resualt = ps.executeUpdate();
            System.out.println(resualt);
//            dbConn.ExcuteSQL(UPDATE_SQL, giaoCa.getNgayGiaoCa(), giaoCa.getNguoiGiao().getId(), giaoCa.getNguoiNhan().getId(),
//                    giaoCa.getGioKiemKe(), giaoCa.getTongCong(), giaoCa.getThucKiem(),
//                    giaoCa.getTrangThai(), giaoCa.getCaLamViec(), giaoCa.getGhiChu(), giaoCa.getMaGiaoCa());
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
}
