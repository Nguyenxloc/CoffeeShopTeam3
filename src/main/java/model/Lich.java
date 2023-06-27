/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class Lich {
    public String maSV;
    public String maMonHoc;
    public String maLopHoc;
    public String maPhongHoc;
    public String maGiangVien;
    public String maChuyenNganh;
    public int nam;
    public String mua;
    public Date ngayHoc;
    public String thoiGian;
    public String loaiLich;

    public Lich() {
    }

    public Lich(String maSV, String maMonHoc, String maLopHoc, String maPhongHoc, String maGiangVien, String maChuyenNganh, int nam, String mua, Date ngayHoc, String thoiGian, String loaiLich) {
        this.maSV = maSV;
        this.maMonHoc = maMonHoc;
        this.maLopHoc = maLopHoc;
        this.maPhongHoc = maPhongHoc;
        this.maGiangVien = maGiangVien;
        this.maChuyenNganh = maChuyenNganh;
        this.nam = nam;
        this.mua = mua;
        this.ngayHoc = ngayHoc;
        this.thoiGian = thoiGian;
        this.loaiLich = loaiLich;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public String getMaPhongHoc() {
        return maPhongHoc;
    }

    public void setMaPhongHoc(String maPhongHoc) {
        this.maPhongHoc = maPhongHoc;
    }

    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getMaChuyenNganh() {
        return maChuyenNganh;
    }

    public void setMaChuyenNganh(String maChuyenNganh) {
        this.maChuyenNganh = maChuyenNganh;
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

    public Date getNgayHoc() {
        return ngayHoc;
    }

    public void setNgayHoc(Date ngayHoc) {
        this.ngayHoc = ngayHoc;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getLoaiLich() {
        return loaiLich;
    }

    public void setLoaiLich(String loaiLich) {
        this.loaiLich = loaiLich;
    }

    @Override
    public String toString() {
        return "Lich{" + "maSV=" + maSV + ", maMonHoc=" + maMonHoc + ", maLopHoc=" + maLopHoc + ", maPhongHoc=" + maPhongHoc + ", maGiangVien=" + maGiangVien + ", maChuyenNganh=" + maChuyenNganh + ", nam=" + nam + ", mua=" + mua + ", ngayHoc=" + ngayHoc + ", thoiGian=" + thoiGian + ", loaiLich=" + loaiLich + '}';
    }

    
}
