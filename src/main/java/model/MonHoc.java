/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class MonHoc {
    
    public String maMonHoc;
    
    public String maChuyenNganh;
    
    public String maLopHoc;
    
    public String tenMonHoc;
    
    public int soTinChi;
    
    public int nam;
    
    public String mua;

    public MonHoc() {
    }

    
    public MonHoc(String maMonHoc, String maChuyenNganh, String maLopHoc, String tenMonHoc, int soTinChi,  String mua) {
        this.maMonHoc = maMonHoc;
        this.maChuyenNganh = maChuyenNganh;
        this.maLopHoc = maLopHoc;
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        this.mua = mua;
    }
    
    public MonHoc(String maMonHoc, String maChuyenNganh, String maLopHoc, String tenMonHoc, int soTinChi, int nam, String mua) {
        this.maMonHoc = maMonHoc;
        this.maChuyenNganh = maChuyenNganh;
        this.maLopHoc = maLopHoc;
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        this.nam = nam;
        this.mua = mua;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getMaChuyenNganh() {
        return maChuyenNganh;
    }

    public void setMaChuyenNganh(String maChuyenNganh) {
        this.maChuyenNganh = maChuyenNganh;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
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
        return tenMonHoc;
    }
    
}
