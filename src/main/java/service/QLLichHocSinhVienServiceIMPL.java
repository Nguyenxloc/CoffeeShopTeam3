/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.view.model.SinhVien.LichHocSinhVien;
import java.util.ArrayList;
import repository.DAO_Lich;

/**
 *
 * @author ADMIN
 */
public class QLLichHocSinhVienServiceIMPL implements QLLichHocSinhVienService{
    
    private DAO_Lich dAO_Lich = new DAO_Lich();

    @Override
    public ArrayList<LichHocSinhVien> getAll() {
//        return dAO_Lich.getAll();
    return null;
    }

    @Override
    public ArrayList<LichHocSinhVien> getLichHocByMaSV(String maSV) {
        return dAO_Lich.getLichHocByMaSV(maSV);
    }

   
}
