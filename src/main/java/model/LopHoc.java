/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class LopHoc {
    
    public String maLopHoc;
    
    public String maChuyenNganh;
    
    public String tenLopHoc;
    
    public int nam;
    
    public String mua;

    public LopHoc() {
    }

    public LopHoc(String maLopHoc, String maChuyenNganh, String tenLopHoc, int nam, String mua) {
        this.maLopHoc = maLopHoc;
        this.maChuyenNganh = maChuyenNganh;
        this.tenLopHoc = tenLopHoc;
        this.nam = nam;
        this.mua = mua;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public String getMaChuyenNganh() {
        return maChuyenNganh;
    }

    public void setMaChuyenNganh(String maChuyenNganh) {
        this.maChuyenNganh = maChuyenNganh;
    }

    public String getTenLopHoc() {
        return tenLopHoc;
    }

    public void setTenLopHoc(String tenLopHoc) {
        this.tenLopHoc = tenLopHoc;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public String getMua() {
        return mua;
    }

    public void setMua(String mua) {
        this.mua = mua;
    }

    @Override
    public String toString() {
        return tenLopHoc;
    }
    
}
