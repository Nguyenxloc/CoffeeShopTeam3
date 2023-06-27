/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Time;


/**
 *
 * @author ADMIN
 */
public class HoaDon {
    
    public String maHoaDon;
    
    private String maVi;
    
    public String maSV;
    
    public double soTien;
    
    public Date ngayGiaoDich;
    
    public Time gioGiaoDich;
    
    public String chuThich;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, String maVi, String maSV, double soTien, Date ngayGiaoDich, Time gioGiaoDich, String chuThich) {
        this.maHoaDon = maHoaDon;
        this.maVi = maVi;
        this.maSV = maSV;
        this.soTien = soTien;
        this.ngayGiaoDich = ngayGiaoDich;
        this.gioGiaoDich = gioGiaoDich;
        this.chuThich = chuThich;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaVi() {
        return maVi;
    }

    public void setMaVi(String maVi) {
        this.maVi = maVi;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public Date getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(Date ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public Time getGioGiaoDich() {
        return gioGiaoDich;
    }

    public void setGioGiaoDich(Time gioGiaoDich) {
        this.gioGiaoDich = gioGiaoDich;
    }

    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", maVi=" + maVi + ", maSV=" + maSV + ", soTien=" + soTien + ", ngayGiaoDich=" + ngayGiaoDich + ", gioGiaoDich=" + gioGiaoDich + ", chuThich=" + chuThich + '}';
    }
    
}
