/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.Ban;
import java.sql.*;
import java.util.ArrayList;
import ultilities.DBConnection;
/**
 *
 * @author MSI-G8
 */
public class BanRepository {
    public List<Ban> getALL(){
        List<Ban> bans = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT IdBan, Ten "
                    + "FROM Ban";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Ban ban = new Ban();
                ban.setIdBan(rs.getInt("IdBan"));
                ban.setTen(rs.getString("Ten"));
                
                bans.add(ban);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bans;
    }
    
    public boolean checkMa(int id){
        int count = 0;
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT COUNT(*) FROM Ban WHERE Id";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count > 0;
    }
    
    public void them(Ban ban){
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO Ban "
                    + " (IdBan, Ten) "
                    + "VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, ban.getIdBan());
            st.setString(2, ban.getTen());
            
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void xoa(int id){
        try {
            Connection con = DBConnection.getConnection();
            String query = "DELETE FROM Ban " + "WHERE  (IdBan = ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sua(Ban ban){
        try {
            Connection con = DBConnection.getConnection();
            String query = "UPDATE Ban "
                    + "SET Ten =? "
                    + "WHERE  (IdBan = 1)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, ban.getTen());
            st.setInt(1, ban.getIdBan());
            
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
