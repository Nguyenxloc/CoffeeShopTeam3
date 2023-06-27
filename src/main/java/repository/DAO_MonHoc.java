/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.MonHoc;
import ultilities.DBConnection;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class DAO_MonHoc {

    final String INSERT_SQL = "INSERT INTO [dbo].[MONHOC] VALUES(?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[MONHOC] SET[MACHUYENNGANH]=?,[MALOPHOC]=?,[TENMONHOC]=?,[SOTINCHI]=?,[NAM]=?,[MUA]=? WHERE [MAMONHOC] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[MONHOC] WHERE [MAMONHOC] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[MONHOC] WHERE [MAMONHOC] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[MONHOC]";

    private DBConnection dBConnection = new DBConnection();
    
    public DAO_MonHoc() {
        MonHoc monHoc = new MonHoc();
        ArrayList<MonHoc> lstMH = new ArrayList<>();
        lstMH = new ArrayList<>();
    }

    public ArrayList<MonHoc> findAll() {
        return getSelectSql(SELECT_ALL_SQL);
    }

    public MonHoc update(MonHoc mh) {
        MonHoc monHoc = new MonHoc();
        ArrayList<MonHoc> lstMH = new ArrayList<>();
        DBConnection.ExcuteDungna(UPDATE_SQL, monHoc.getMaMonHoc(), monHoc.getMaChuyenNganh(), monHoc.getMaLopHoc(), monHoc.getTenMonHoc(), monHoc.getSoTinChi(), monHoc.getNam(), monHoc.getMua());
        return mh;
    }

    public MonHoc save(MonHoc mh) {
        MonHoc monHoc = new MonHoc();
        ArrayList<MonHoc> lstMH = new ArrayList<>();
        DBConnection.ExcuteDungna(INSERT_SQL, monHoc.getMaMonHoc(), monHoc.getMaChuyenNganh(), monHoc.getMaLopHoc(), monHoc.getTenMonHoc(), monHoc.getSoTinChi(), monHoc.getNam(), monHoc.getMua());
        return mh;
    }

    public MonHoc findById(String id) {
        MonHoc mh;
        mh = getSelectSql(SELECT_BY_SQL, id).get(0);
        return mh;
    }

    public String delete(String id) {
        DBConnection.ExcuteDungna(DELETE_SQL, id);
        return id;
    }

    private ArrayList<MonHoc> getSelectSql(String sql, Object... args) {
        MonHoc monHoc = new MonHoc();
        ArrayList<MonHoc> lstMH = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                lstMH.add(new MonHoc(rs.getString("MAMONHOC"), rs.getString("MACHUYENNGANH"), rs.getString("MALOPHOC"), rs.getString("TENMONHOC"), rs.getInt("SOTINCHI"), rs.getInt("NAM"), rs.getString("MUA")));
            }
            return lstMH;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
    
    public Boolean update2(String id,MonHoc monHoc){
        String sql = "update MONHOC set MAMONHOC=?,MACHUYENNGANH=?,MALOPHOC=?,TENMONHOC=?,SOTINCHI=?,MUA=? where NUMORDER=?";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, monHoc.getMaMonHoc());
                st.setObject(2, monHoc.getMaChuyenNganh());
                st.setObject(3, monHoc.getMaLopHoc());
                st.setObject(4, monHoc.getTenMonHoc());
                st.setObject(5, monHoc.getSoTinChi());
                st.setObject(6, monHoc.getMua());
                st.setObject(7, id);
                int result = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    
    public Boolean save2(MonHoc monHoc){
        String sql = "insert into MONHOC(MAMONHOC,MACHUYENNGANH,MALOPHOC,TENMONHOC,SOTINCHI,MUA) values(?,?,?,?,?,?)";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {              
                st.setObject(1, monHoc.getMaMonHoc());
                st.setObject(2, monHoc.getMaChuyenNganh());
                st.setObject(3, monHoc.getMaLopHoc());
                st.setObject(4, monHoc.getTenMonHoc());
                st.setObject(5, monHoc.getSoTinChi());
                st.setObject(6, monHoc.getMua());
                int result = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        return false;
    }
    
    public MonHoc findById2(String id){
        String sql = "select MAMONHOC,MACHUYENNGANH,MALOPHOC,TENMONHOC,SOTINCHI,MUA from MONHOC where NUMORDER=?";
        try(Connection con = dBConnection.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {                
                   MonHoc monHoc = new MonHoc();
                   monHoc.setMaMonHoc(rs.getString(1));
                   monHoc.setMaChuyenNganh(rs.getString(2));
                   monHoc.setMaLopHoc(rs.getString(3));
                   monHoc.setTenMonHoc(rs.getString(4));
                   monHoc.setSoTinChi(rs.getInt(5));
                   monHoc.setMua(rs.getString(6));
                   return monHoc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
