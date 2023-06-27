/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import com.view.model.SinhVien.LichHocSinhVien;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface QLLichHocSinhVienService {
    
    ArrayList<LichHocSinhVien> getAll();
    
    ArrayList<LichHocSinhVien> getLichHocByMaSV(String maSV);
    
    
}
