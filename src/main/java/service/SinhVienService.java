/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.view.model.SinhVien.ThongTinSinhVien;
import java.util.ArrayList;
import model.SinhVien;
import repository.DAO_SinhVien;

/**
 *
 * @author 84374
 */
public class SinhVienService {
    private DAO_SinhVien sinhVienRepo = new DAO_SinhVien();
    
    public Integer xacThucTaiKhoanSinhVien(SinhVien sinhVien) throws Exception{
        SinhVien maSVCanTim = sinhVienRepo.xacThucTaiKhoanSinhVien(sinhVien.getMaSV());
       if(maSVCanTim != null){
           String matKhau = sinhVien.getMatKhau();
           if(maSVCanTim.getMatKhau().equals(matKhau)){
               return 1; // tìm thấy tài khoản và mật khẩu
           }else{
               return -1; // Không tìm thấy mật khẩu
           }
       }
       return 0; // Không tìm thấy tên tài khoản (mã GV)
   }
    
    public SinhVien addSv(SinhVien sv){
        return sinhVienRepo.save(sv);
    }
    
    public SinhVien findBId(String id){
        return sinhVienRepo.findById(id);
    }
    
    public String delete(String id){
        return sinhVienRepo.delete(id);
    }
    
    public SinhVien update(SinhVien sv){
       return sinhVienRepo.update(sv); 
    }
    
    public ArrayList<ThongTinSinhVien> getAll(){
        try {
            return sinhVienRepo.getAll();
        } catch (Exception e) {
            return null;
        }
    }
    
    public ThongTinSinhVien getByMaSV(String maSV){
        try {
            return sinhVienRepo.getByMaSV(maSV).get(0);
        } catch (Exception e) {
            return null;
        }
    }
    
}
