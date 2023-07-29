/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Repository;

import DoUong_HoaDon_ThongKe_Model.BieuDoThongKe;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface iThongKe {
    
    double getTongDoanhThu();
    
    int getTongHoaDon();
    
    int getTongSanPham();
    
    double getTongDonhThuTheoNgayChon(String d1, String d2);
    
    int getTongHoaDonTheoNgayChon(String d1, String d2);
    
    int getTongSanPhamTheoNgayChon(String d1, String d2);
    
    ArrayList<BieuDoThongKe> getBieuDo();
    
    ArrayList<BieuDoThongKe> getBieuDoTheoNgay(String d1, String d2);
}
