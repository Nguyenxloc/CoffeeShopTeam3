/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class DichVu {
    
    public String maDV;
    
    public String tenDV;
    
    public String phongBanPhuTrach;

    public DichVu() {
    }

    public DichVu(String maDV, String tenDV, String phongBanPhuTrach) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.phongBanPhuTrach = phongBanPhuTrach;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getPhongBanPhuTrach() {
        return phongBanPhuTrach;
    }

    public void setPhongBanPhuTrach(String phongBanPhuTrach) {
        this.phongBanPhuTrach = phongBanPhuTrach;
    }

    @Override
    public String toString() {
        return "DichVu{" + "maDV=" + maDV + ", tenDV=" + tenDV + ", phongBanPhuTrach=" + phongBanPhuTrach + '}';
    }
     
    
}
