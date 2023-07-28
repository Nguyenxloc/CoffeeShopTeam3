/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.util.ArrayList;
import java.util.List;
import model.LoaiDoUong;
import java.sql.*;
import ultilities.DBConnection;
/**
 *
 * @author MSI-G8
 */
public class LoaiDoUongRepository {
    public List<LoaiDoUong> getAll(){
        List<LoaiDoUong> loais = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT Id, Ma, Ten " + "FROM LoaiDoUong";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("Ten");
                
                LoaiDoUong loai = new LoaiDoUong();
                loai.setId(id);
                loai.setMa(ma);
                loai.setTenLoaiDoUong(ten);
                
                loais.add(loai);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loais;
    }
    
    public void them(LoaiDoUong ld){
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO LoaiDoUong " + " (Ma, Ten) " +
                                                    "VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, ld.getMa());
            st.setString(2, ld.getTenLoaiDoUong());
            
            st.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public void xoa(String id) throws Exception{
        Connection con = DBConnection.getConnection();
        String query = "DELETE FROM LoaiDoUong WHERE Id = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, id);
        
        st.execute();
        st.close();
        con.close();
    }
}
