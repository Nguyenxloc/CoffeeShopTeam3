/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class DVSinhVienDaDangKy {
    
    public String maDV;
    
    public String maSV;
    
    public String ngayDK;
    
    public String fileDinhKem;
    
    public String chuThich;

    public DVSinhVienDaDangKy() {
    }

    public DVSinhVienDaDangKy(String maDV, String maSV, String ngayDK, String fileDinhKem, String chuThich) {
        this.maDV = maDV;
        this.maSV = maSV;
        this.ngayDK = ngayDK;
        this.fileDinhKem = fileDinhKem;
        this.chuThich = chuThich;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(String ngayDK) {
        this.ngayDK = ngayDK;
    }

    public String getFileDinhKem() {
        return fileDinhKem;
    }

    public void setFileDinhKem(String fileDinhKem) {
        this.fileDinhKem = fileDinhKem;
    }

    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }

    @Override
    public String toString() {
        return "DVSinhVienDaDangKy{" + "maDV=" + maDV + ", maSV=" + maSV + ", ngayDK=" + ngayDK + ", fileDinhKem=" + fileDinhKem + ", chuThich=" + chuThich + '}';
    }
    
    
}
