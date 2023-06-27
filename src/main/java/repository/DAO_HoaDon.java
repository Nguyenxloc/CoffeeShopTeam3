/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.HoaDon;
import ultilities.DBConnection;

/**
 *
 * @author ADMIN
 */
public class DAO_HoaDon {
    final String INSERT_SQL = "INSERT INTO [dbo].[HOADON] VALUES(?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[HOADON] SET[MAVI]=?,[MASV]=?,[SOTIEN]=?,[NGAYGD]=?,[GIOGD]=?,[CHUTHICH]=? WHERE [MAHD] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[HOADON] WHERE [MAHD] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[HOADON] WHERE [MAHD] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[HOADON]";
    
    private HoaDon hoaDon;
    private ArrayList<HoaDon> _lstHoaDon;

    public DAO_HoaDon() {
        _lstHoaDon = new ArrayList<>();
    }
    
    public ArrayList<HoaDon> findAll() {
        _lstHoaDon = new ArrayList<>();
        return getSelectSql(SELECT_ALL_SQL);
    }

    public HoaDon findById(String id) {
        HoaDon hoaDon;
        hoaDon = getSelectSql(SELECT_BY_SQL).get(0);
        return hoaDon;
    }
    
    public HoaDon save(HoaDon hoaDon) {
        DBConnection.ExcuteDungna(INSERT_SQL, hoaDon.getMaHoaDon(), hoaDon.getMaVi(), hoaDon.getMaSV(), hoaDon.getSoTien(), hoaDon.getNgayGiaoDich(), hoaDon.getGioGiaoDich(), hoaDon.getChuThich());
        return hoaDon;
    }

    public String delete(String id) {
        DBConnection.ExcuteDungna(DELETE_SQL, id);
        return id;
    }
    
    public HoaDon update(HoaDon hoaDon) {
        DBConnection.ExcuteDungna(UPDATE_SQL, hoaDon.getMaHoaDon(), hoaDon.getMaVi(), hoaDon.getMaSV(), hoaDon.getSoTien(), hoaDon.getNgayGiaoDich(), hoaDon.getGioGiaoDich(), hoaDon.getChuThich());
        return hoaDon;
    }

    private ArrayList<HoaDon> getSelectSql(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                System.out.println(rs.getLong(4));
                _lstHoaDon.add(new HoaDon(rs.getString("MAHD"), rs.getString("MAVI"), rs.getString("MASV"), rs.getDouble("SOTIEN"), rs.getDate("NGAYGD"), rs.getTime("GIOGD"), rs.getString("CHUTHICH")));
            }
            return _lstHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
}
