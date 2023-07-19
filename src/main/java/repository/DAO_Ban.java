/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.Ban;
import ultilities.DBConnection1;

/**
 *
 * @author 84374
 */
public class DAO_Ban {

    final String INSERT_SQL = "INSERT INTO dbo.Ban(IdBan)VALUES(?)";
    final String DELETE_SQL = "DELETE FROM [dbo].[Ban] WHERE [IdBan] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[Ban] WHERE [IdBan] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[Ban]";

    public ArrayList<Ban> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<Ban> lstBan = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                lstBan.add(new Ban(rs.getNString("IdBan"),rs.getString("NgayTao")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstBan;
    }

    public Ban selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        Ban ban = new Ban();
        ArrayList<Ban> lstBan = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            while (rs.next()) {
                lstBan.add(new Ban(rs.getNString("IdBan"),rs.getString("NgayTao")));
                ban = lstBan.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ban;
    }

    public void save(Ban ban) {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<Ban> lstLoaiDoUong = new ArrayList<>();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, ban.getIdBan());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String idBan) {
         
    }

}
