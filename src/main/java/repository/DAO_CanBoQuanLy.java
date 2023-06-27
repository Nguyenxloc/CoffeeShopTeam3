/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import model.CanBo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ultilities.DBConnectorGV;

/**
 *
 * @author ADMIN
 */
public class DAO_CanBoQuanLy {
    
    public List<CanBo> getTaiKhoan() throws Exception{  //Lấy danh sách tài khoản của cán bộ
        List<CanBo> lstTKCanBo = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT MACB, MATKHAU, VAITRO FROM dbo.CANBO ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
           String ma = rs.getString("MACB");
           String matKhau = rs.getString("MATKHAU");
           String vaiTro = rs.getString("VAITRO");
           
           CanBo canBo = new CanBo();
           canBo.setMaCB(ma);
           canBo.setMatKhau(matKhau);
           canBo.setVaiTro(vaiTro);
           
           lstTKCanBo.add(canBo);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstTKCanBo;
    }
    
    public CanBo xacThucTaiKhoanCanBo(String username) throws Exception{ // Tìm theo tên tài khoản 
        List<CanBo> lstTKCanBo = getTaiKhoan();
        CanBo maCBCanTim = null;
        for (CanBo canBo : lstTKCanBo) {
            if(canBo.getMaCB().equals(username)){
                maCBCanTim = canBo;
                break;
            }
        }
        return maCBCanTim;
    }
}
