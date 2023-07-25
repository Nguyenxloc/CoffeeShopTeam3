/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import ultilities.DBConnection;
/**
 *
 * @author MSI-G8
 */
public class NhanVienRepository {
    public List<NhanVien> getAll(){
        List<NhanVien> nhanViens = new ArrayList<NhanVien>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT Id, Ten, TenDem, Ho FROM NhanVien";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                String id = rs.getString("Id");
                String ten = rs.getString("Ten");
                String tenDem = rs.getString("TenDem");
                String ho = rs.getString("Ho");
                
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(id);
                nhanVien.setTen(ten);
                nhanVien.setTenDem(tenDem);
                nhanVien.setHo(ho);
               
               
                
                nhanViens.add(nhanVien);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanViens;
    }
    
    public NhanVien timIDNhanVien(String id){
        List<NhanVien> nhanViens = new ArrayList<NhanVien>();
        NhanVien nv = new NhanVien();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT Id, Ten, TenDem, Ho " + "FROM NhanVien WHERE Id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                nv.setId(rs.getString(1));
                nv.setTen(rs.getString(2));
                nv.setTenDem(rs.getString(3));
                nv.setHo(rs.getString(4));
                
                nhanViens.add(nv);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }
}
