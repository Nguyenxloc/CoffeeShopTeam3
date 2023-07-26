/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.CapBac;
import model.NhanVien;
import ultilities.DBConnection1;

/**
 *
 * @author 84374
 */
public class DAO_NhanVien {

    final String INSERT_SQL = "INSERT INTO dbo.NhanVien(Ma,Ten,TenDem,Ho,GioiTinh,NgaySinh,DiaChi,Sdt,TaiKhoan,MatKhau,IdCB,TrangThai,HinhAnh)\n"
            + "VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.NhanVien SET Ten=?, TenDem=?,Ho=?,GioiTinh=?,NgaySinh=?,DiaChi=?,Sdt=?,TaiKhoan=?,MatKhau=?,IdCB=?,TrangThai=?,HinhAnh=? WHERE Id=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[NhanVien] WHERE [Id] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[NhanVien] WHERE Ma = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[NhanVien];";

    public DAO_NhanVien() {
    }

    public ArrayList<NhanVien> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        //get capBac obj
        DAO_CapBac dao_capBac = new DAO_CapBac();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                CapBac capBac = dao_capBac.selectByID(rs.getString("IdCB"));
                lstNhanVien.add(new NhanVien(rs.getString("Id"), rs.getString("Ma"), rs.getNString("Ten"), rs.getNString("TenDem"), rs.getNString("Ho"), rs.getNString("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("TaiKhoan"), rs.getString("MatKhau"), capBac, rs.getInt("TrangThai"), rs.getBytes("HinhAnh"), rs.getString("NgayTao")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstNhanVien;
    }

    public NhanVien selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        NhanVien nhanVien = new NhanVien();
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        DAO_CapBac dao_capBac = new DAO_CapBac();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            while (rs.next()) {
//                CapBac capBac = dao_capBac.selectByID(rs.getString("IdCB"));
                lstNhanVien.add(new NhanVien(rs.getString("Id"), rs.getString("Ma"), rs.getNString("Ten"), rs.getNString("TenDem"), rs.getNString("Ho"), rs.getNString("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("TaiKhoan"), rs.getString("MatKhau"), new CapBac(), rs.getInt("TrangThai"), rs.getBytes("HinhAnh"), rs.getString("NgayTao")));

                nhanVien = lstNhanVien.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanVien;
    }

    public void save(NhanVien nhanVien) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, nhanVien.getMa(), nhanVien.getTen(), nhanVien.getTenDem(), nhanVien.getHo(), nhanVien.getGioiTinh(), nhanVien.getDob(), nhanVien.getDiaChi(),
                    nhanVien.getSdt(), nhanVien.getTaiKhoan(), nhanVien.getMatKhau(), nhanVien.getCapBac().getIdCB(), nhanVien.getTrangThai(), nhanVien.getImg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(NhanVien nhanVien) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_SQL, nhanVien.getTen(), nhanVien.getTenDem(), nhanVien.getHo(), nhanVien.getGioiTinh(), nhanVien.getDob(),
                    nhanVien.getDiaChi(), nhanVien.getSdt(), nhanVien.getTaiKhoan(), nhanVien.getMatKhau(), nhanVien.getCapBac(), nhanVien.getTrangThai(), nhanVien.getImg(), nhanVien.getId());
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
