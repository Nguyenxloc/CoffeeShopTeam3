/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.PhongHoc;
import ultilities.DBConnection;

/**
 *
 * @author 84374
 */
public class DAO_PhongHoc {

    final String INSERT_SQL = "INSERT dbo.PHONGHOC(MAPHONGHOC,TENPHONGHOC,TOANHA)VALUES(?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.PHONGHOC SET TENPHONGHOC=?,TOANHA=? WHERE MAPHONGHOC=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[PHONGHOC] WHERE [MAPHONGHOC] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[PHONGHOC] WHERE [MAPHONGHOC] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[PHONGHOC]";

    private PhongHoc phongHoc;
    private ArrayList<PhongHoc> _lstPhongHoc;

    public DAO_PhongHoc() {
        _lstPhongHoc = new ArrayList<>();
    }

    public ArrayList<PhongHoc> findAll() {
        _lstPhongHoc = new ArrayList<>();
        return getSelectSql(SELECT_ALL_SQL);
    }

    public PhongHoc findById(String id) {
        PhongHoc phongHoc;
        phongHoc = getSelectSql(SELECT_BY_SQL).get(0);
        return phongHoc;
    }

    public PhongHoc save(PhongHoc phongHoc) {
        DBConnection.ExcuteDungna(INSERT_SQL,phongHoc.getMaPhongHoc(),phongHoc.getTenPhongHoc(),phongHoc.getToaNha());
        return phongHoc;
    }

    public String delete(String id) {
        DBConnection.ExcuteDungna(DELETE_SQL, id);
        return id;
    }

    public PhongHoc update(PhongHoc phongHoc) {
        DBConnection.ExcuteDungna(UPDATE_SQL,phongHoc.getTenPhongHoc(),phongHoc.getToaNha(),phongHoc.getMaPhongHoc());
        return phongHoc;
    }

    private ArrayList<PhongHoc> getSelectSql(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                _lstPhongHoc.add(new PhongHoc(rs.getString("MAPHONGHOC"), rs.getString("TENPHONGHOC"), rs.getString("TOANHA")));
            }
            return _lstPhongHoc;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
