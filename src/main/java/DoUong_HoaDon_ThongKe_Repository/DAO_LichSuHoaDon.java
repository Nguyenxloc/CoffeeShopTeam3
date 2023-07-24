/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Repository;

import DoUong_HoaDon_ThongKe_Model.HoaDonChiTiet;
import DoUong_HoaDon_ThongKe_Model.LichSuHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import ultilities.DBConnection1;
import ultilities.DbConnection_Sang;

/**
 *
 * @author ADMIN
 */
public class DAO_LichSuHoaDon implements iLichSuHoaDon {

    final String SELECT_ALL_SQL = "select  HoaDon.Ma, NhanVien.Ten, convert(varchar, hoadon.NgayTao, 105) as 'NgayTao',convert(varchar, hoadon.NgayTao, 105) as 'NgayTao', \n"
            + "            (sum(SoLuong)) as 'SoLuongSP', sum(ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong) as 'TongTien', GiamGia.GiaTri, HoaDon.Stt  from HoaDonChiTiet\n"
            + "            join ChiTietDoUong on HoaDonChiTiet.IdChiTietDoUong = ChiTietDoUong.Id\n"
            + "            join HoaDon on HoaDonChiTiet.IdHoaDon = HoaDon.Id\n"
            + "            join NhanVien on HoaDon.IdNV = NhanVien.Id\n"
            + "            join GiamGia on GiamGia.MaGiamGia = HoaDon.MaGiamGia\n"
            + "            group by HoaDon.Ma, NhanVien.Ten, HoaDon.NgayTao, HoaDon.ThoiGian, GiamGia.GiaTri, HoaDon.Stt";

    final String SELECT_ALL_HOADON = "select HoaDon.Ma, ChiTietDoUong.TenDoUong, HoaDonChiTiet.SoLuong, (ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong) as 'ThanhTien' from HoaDonChiTiet\n"
            + "join ChiTietDoUong on ChiTietDoUong.Id = HoaDonChiTiet.IdChiTietDoUong\n"
            + "join HoaDon on HoaDon.Id = HoaDonChiTiet.IdHoaDon";

    final String SELECT_BY_MAHD = "select ChiTietDoUong.TenDoUong, HoaDonChiTiet.SoLuong, (ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong) from HoaDonChiTiet\n"
            + "join ChiTietDoUong on ChiTietDoUong.Id = HoaDonChiTiet.IdChiTietDoUong\n"
            + "join HoaDon on HoaDon.Id = HoaDonChiTiet.IdHoaDon\n"
            + "where HoaDon.Ma = ?";

    @Override
    public ArrayList<LichSuHoaDon> getAll() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<LichSuHoaDon> lstLichSuHoaDon = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                lstLichSuHoaDon.add(new LichSuHoaDon(rs.getString("Ma"), rs.getString("Ten"), rs.getString("NgayTao"), rs.getString("ThoiGian"),
                        rs.getInt("tinhTrangThanhToan"), rs.getInt("SoLuongSP"), rs.getDouble("TongTien"), rs.getDouble("GiaTri"), rs.getDouble("TongTienThuVe"), rs.getInt("Stt")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstLichSuHoaDon;
    }

    @Override
    public ArrayList<HoaDonChiTiet> getAllll() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_HOADON);
            while (rs.next()) {
                lstHoaDonChiTiet.add(new HoaDonChiTiet(rs.getString("Ma"), rs.getString("TenDoUong"), rs.getInt("SoLuong"), rs.getDouble("ThanhTien")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDonChiTiet;
    }

    @Override
    public void selectByMaHD(String maHoaDon) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(SELECT_BY_MAHD, maHoaDon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    DbConnection_Sang dbConnection;

    @Override
    public ArrayList<LichSuHoaDon> getByTime(String d1, String d2) {
        ArrayList<LichSuHoaDon> lstLichSuHoaDon = new ArrayList<>();
        String sql = "select  HoaDon.Ma, NhanVien.Ten, convert(varchar, hoadon.ngaytao, 105) as 'NgayTao',convert(varchar, hoadon.ThoiGian, 105) as 'ThoiGian', "
                + "(sum(SoLuong)) as 'SoLuongSP', sum(ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong) as 'TongTien', GiamGia.GiaTri, HoaDon.Stt  from HoaDonChiTiet\n"
                + "join ChiTietDoUong on HoaDonChiTiet.IdChiTietDoUong = ChiTietDoUong.Id\n"
                + "join HoaDon on HoaDonChiTiet.IdHoaDon = HoaDon.Id\n"
                + "join NhanVien on HoaDon.IdNV = NhanVien.Id\n"
                + "join GiamGia on GiamGia.MaGiamGia = HoaDon.MaGiamGia\n"
                + "where convert(varchar, hoadon.ThoiGian, 105) between ? and ?\n"
                + "group by HoaDon.Ma, NhanVien.Ten, HoaDon.NgayTao, HoaDon.ThoiGian, GiamGia.GiaTri, HoaDon.Stt";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, d1);
            ps.setObject(2, d2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LichSuHoaDon lichSuHoaDon = new LichSuHoaDon();
                lichSuHoaDon.setMaHoaDon(rs.getString("Ma"));
                lichSuHoaDon.setTenNhanVien(rs.getString("Ten"));
                lichSuHoaDon.setTimeTao(rs.getString("NgayTao"));
                lichSuHoaDon.setTimeThanhToan(rs.getString("ThoiGian"));
                lichSuHoaDon.setSoLuong(rs.getInt("SoLuongSP"));
                lichSuHoaDon.setTongTienHoaDon(rs.getDouble("TongTien"));
                lichSuHoaDon.setChietKhau(rs.getDouble("GiaTri"));
                lichSuHoaDon.setTrangThai(rs.getInt("Stt"));
                lstLichSuHoaDon.add(lichSuHoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstLichSuHoaDon;
    }

    @Override
    public ArrayList<HoaDonChiTiet> loadDataByMa(String maHoaDon) {
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
        String sql = "select ChiTietDoUong.TenDoUong, HoaDonChiTiet.SoLuong, (ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong) as 'ThanhTien' from HoaDonChiTiet\n"
                + "join ChiTietDoUong on ChiTietDoUong.Id = HoaDonChiTiet.IdChiTietDoUong\n"
                + "join HoaDon on HoaDon.Id = HoaDonChiTiet.IdHoaDon\n"
                + "where HoaDon.Ma = ?";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setTenSanPham(rs.getString("TenDoUong"));
                hoaDonChiTiet.setSoLuong(rs.getInt("SoLuong"));
                hoaDonChiTiet.setThanhTien(rs.getDouble("ThanhTien"));
                lstHoaDonChiTiet.add(hoaDonChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDonChiTiet;
    }

    @Override
    public ArrayList<LichSuHoaDon> getByMa(String maHoaDon) {
        ArrayList<LichSuHoaDon> lstLichSuHoaDon = new ArrayList<>();
        String sql = "select  HoaDon.Ma, NhanVien.Ten, convert(varchar, hoadon.ngaytao, 105) as 'NgayTao',convert(varchar, hoadon.NgayTao, 105) as 'NgayThanhToan', (sum(SoLuong)) as 'SoLuongSP', sum(ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong) as 'TongTien', GiamGia.PhamTram, HoaDon.Stt  from HoaDonChiTiet\n"
                + "join ChiTietDoUong on HoaDonChiTiet.IdChiTietDoUong = ChiTietDoUong.Id\n"
                + "join HoaDon on HoaDonChiTiet.IdHoaDon = HoaDon.Id\n"
                + "join NhanVien on HoaDon.IdNV = NhanVien.Id\n"
                + "join GiamGia on GiamGia.MaGiamGia = HoaDon.MaGiamGia\n"
                + "where HoaDon.Ma = ?\n"
                + "group by HoaDon.Ma, NhanVien.Ten, HoaDon.NgayTao, HoaDon.ThoiGian, GiamGia.GiaTri, HoaDon.Stt";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LichSuHoaDon lichSuHoaDon = new LichSuHoaDon();
                lichSuHoaDon.setMaHoaDon(rs.getString("Ma"));
                lichSuHoaDon.setTenNhanVien(rs.getString("Ten"));
                lichSuHoaDon.setTimeTao(rs.getString("NgayTao"));
                lichSuHoaDon.setTimeThanhToan(rs.getString("ThoiGian"));
                lichSuHoaDon.setSoLuong(rs.getInt("SoLuongSP"));
                lichSuHoaDon.setTongTienHoaDon(rs.getDouble("TongTien"));
                lichSuHoaDon.setChietKhau(rs.getDouble("GiaTri"));
                lichSuHoaDon.setTrangThai(rs.getInt("Stt"));
                lstLichSuHoaDon.add(lichSuHoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstLichSuHoaDon;
    }

}
