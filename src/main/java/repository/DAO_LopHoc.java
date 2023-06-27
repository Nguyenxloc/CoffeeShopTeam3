/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.LopHoc;
import ultilities.DBConnection;

/**
 *
 * @author ADMIN
 */
public class DAO_LopHoc {
    final String INSERT_SQL = "INSERT INTO [dbo].[LOPHOC] VALUES(?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[LOPHOC] SET[MACHUYENNGANH]=?,[TENLOPHOC]=?,[NAM]=?,[MUA]=? WHERE [MALOPHOC] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[LOPHOC] WHERE [MALOPHOC] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[LOPHOC] WHERE [MALOPHOC] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[LOPHOC]";
    
    private LopHoc lopHoc;
    private ArrayList<LopHoc> _lstLopHoc;
    private DBConnection dBConnection = new DBConnection();
    public DAO_LopHoc() {
        _lstLopHoc = new ArrayList<>();
    }

    public ArrayList<LopHoc> findAll() {
        _lstLopHoc = new ArrayList<>();
        return getSelectSql(SELECT_ALL_SQL);
    }

    public LopHoc findById(String id) {
        LopHoc lopHoc;
        lopHoc = getSelectSql(SELECT_BY_SQL).get(0);
        return lopHoc;
    }
    
    public LopHoc save(LopHoc lopHoc) {
        DBConnection.ExcuteDungna(INSERT_SQL, lopHoc.getMaLopHoc(), lopHoc.getMaChuyenNganh(), lopHoc.getTenLopHoc(), lopHoc.getNam(), lopHoc.getMua());
        return lopHoc;
    }

    public String delete(String id) {
        DBConnection.ExcuteDungna(DELETE_SQL, id);
        return id;
    }
    
    public LopHoc update(LopHoc lopHoc) {
        DBConnection.ExcuteDungna(UPDATE_SQL, lopHoc.getMaLopHoc(), lopHoc.getMaChuyenNganh(), lopHoc.getTenLopHoc(), lopHoc.getNam(), lopHoc.getMua());
        return lopHoc;
    }

    private ArrayList<LopHoc> getSelectSql(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                System.out.println(rs.getLong(4));
                _lstLopHoc.add(new LopHoc(rs.getString("MALOPHOC"), rs.getString("MACHUYENNGANH"), rs.getString("TENLOPHOC"), rs.getInt("NAM"), rs.getString("MUA")));
            }
            return _lstLopHoc;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
    
    public Boolean updateLH(String id,LopHoc lopHoc){
        String sql = "update LOPHOC set TENLOPHOC=?,NAM=?,MUA=? where MALOPHOC=?";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, lopHoc.getTenLopHoc());
                st.setObject(2, lopHoc.getNam());
                st.setObject(3, lopHoc.getMua());
                st.setObject(4, id);
                int result = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    
}



