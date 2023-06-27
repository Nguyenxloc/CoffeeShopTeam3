/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import model.ViHocPhi;
import java.sql.*;
import java.sql.SQLException;
import ultilities.DBConnection;

/**
 *
 * @author ADMIN
 */
public class DAO_ViHocPhi {
    
    final String INSERT_SQL = "INSERT INTO [dbo].[VIHOCPHI] VALUES(?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[VIHOCPHI] SET [MASV]=?,[SODUHOCPHI]=? WHERE [MAVI] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[VIHOCPHI] WHERE [MAVI] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[VIHOCPHI] WHERE [MAVI] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[VIHOCPHI]";
    
    private ViHocPhi viHocPhi;
    
    private ArrayList<ViHocPhi> _lstViHocPhi;

    public DAO_ViHocPhi() {
        _lstViHocPhi = new ArrayList<>();
    }

    public ArrayList<ViHocPhi> findAll() {
        _lstViHocPhi = new ArrayList<>();
        return getSelectSql(SELECT_ALL_SQL);
    }

    public ViHocPhi findById(String id) {
        ViHocPhi viHocPhi;
        viHocPhi = getSelectSql(SELECT_BY_SQL, id).get(0);
        return viHocPhi;
    }
    
    public ViHocPhi save(ViHocPhi viHocPhi) {
        DBConnection.ExcuteDungna(INSERT_SQL, viHocPhi.getMaVi(), viHocPhi.getMaSV(), viHocPhi.getSoDuHocPhi());
        return viHocPhi;
    }

    public String delete(String id) {
        DBConnection.ExcuteDungna(DELETE_SQL, id);
        return id;
    }
    
    public ViHocPhi update(ViHocPhi viHocPhi) {
        DBConnection.ExcuteDungna(UPDATE_SQL, viHocPhi.getMaVi(), viHocPhi.getMaSV(), viHocPhi.getSoDuHocPhi());
        return viHocPhi;
    }

    private ArrayList<ViHocPhi> getSelectSql(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                System.out.println(rs.getLong(4));
                _lstViHocPhi.add(new ViHocPhi(rs.getString("MAVI"), rs.getString("MASV"), rs.getDouble("SODUHOCPHI")));
            }
            return _lstViHocPhi;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
}
