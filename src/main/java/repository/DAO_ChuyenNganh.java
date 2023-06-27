/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ChuyenNganh;
import ultilities.DBConnection;

/**
 *
 * @author ADMIN
 */
public class DAO_ChuyenNganh {

    final String INSERT_SQL = "INSERT INTO [dbo].[CHUYENNGANH] VALUES(?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[CHUYENNGANH] SET[TENCHUYENNGANH]=?,[COSO]=?,[THOIGIANDAOTAO]=? WHERE [MACHUYENNGANH] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[CHUYENNGANH] WHERE [MACHUYENNGANH] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[CHUYENNGANH] WHERE [MACHUYENNGANH] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[CHUYENNGANH]";
    private ChuyenNganh chuyenNganh;
    private ArrayList<ChuyenNganh> _lstCN;

    public DAO_ChuyenNganh() {
        _lstCN = new ArrayList<>();
    }

    public ArrayList<ChuyenNganh> findAll() {
        _lstCN = new ArrayList<>();
        return getSelectSql(SELECT_ALL_SQL);
    }

    public ChuyenNganh findById(String id) {
        ChuyenNganh cn;
        cn = getSelectSql(SELECT_BY_SQL, id).get(0);
        return cn;
    }

    public ChuyenNganh save(ChuyenNganh cn) {
        DBConnection.ExcuteDungna(INSERT_SQL, cn.getMaChuyenNganh(), cn.getTenChuyenNganh(), cn.getCoSo(), cn.getThoiGianDaoTao());
        return cn;
    }

    public String delete(String id) {
        DBConnection.ExcuteDungna(DELETE_SQL, id);
        return id;
    }

    public long totalCount() {
        long total = 0;
        total = _lstCN.size();
        return total;
    }

    private ArrayList<ChuyenNganh> getSelectSql(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                System.out.println(rs.getLong(4));
                _lstCN.add(new ChuyenNganh(rs.getString("MACHUYENNGANH"), rs.getNString("TENCHUYENNGANH"), rs.getNString("COSO"), rs.getInt("THOIGIANDAOTAO")));
            }
            return _lstCN;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public ChuyenNganh update(ChuyenNganh cn) {
        DBConnection.ExcuteDungna(UPDATE_SQL, cn.getTenChuyenNganh(), cn.getCoSo(), cn.getThoiGianDaoTao(), cn.getMaChuyenNganh());
        return cn;
    }

}
