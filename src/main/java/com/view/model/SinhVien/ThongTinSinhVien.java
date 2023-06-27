/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.model.SinhVien;

import java.sql.Date;
import model.ChuyenNganh;

/**
 *
 * @author ADMIN
 */
public class ThongTinSinhVien {

    public String maSV;
    
    public byte[] anh;
    
    public String hoTen;
    
    public Integer gioitinh;
    
    public Date ngaySinh;
    
    public String email;
    
    public String soDienThoai;
    
    public String diaChi;
    
    public String trangThai;
    
    public Integer nienKhoa;
    
    public String maChuyenNganh;
    
    public Date ngayNhapHoc;

    public ThongTinSinhVien() {
    }

    public ThongTinSinhVien(String maSV, byte[] anh, String hoTen, Integer gioitinh, Date ngaySinh, String email, String soDienThoai, String diaChi, String trangThai, Integer nienKhoa, String maChuyenNganh, Date ngayNhapHoc) {
        this.maSV = maSV;
        this.anh = anh;
        this.hoTen = hoTen;
        this.gioitinh = gioitinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.nienKhoa = nienKhoa;
        this.maChuyenNganh = maChuyenNganh;
        this.ngayNhapHoc = ngayNhapHoc;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Integer getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(Integer nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public String getMaChuyenNganh() {
        return maChuyenNganh;
    }

    public void setMaChuyenNganh(String maChuyenNganh) {
        this.maChuyenNganh = maChuyenNganh;
    }

    public Date getNgayNhapHoc() {
        return ngayNhapHoc;
    }

    public void setNgayNhapHoc(Date ngayNhapHoc) {
        this.ngayNhapHoc = ngayNhapHoc;
    }

    @Override
    public String toString() {
        return "ThongTinSinhVien{" + "maSV=" + maSV + ", anh=" + anh + ", hoTen=" + hoTen + ", gioitinh=" + gioitinh + ", ngaySinh=" + ngaySinh + ", email=" + email + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + ", trangThai=" + trangThai + ", nienKhoa=" + nienKhoa + ", maChuyenNganh=" + maChuyenNganh + ", ngayNhapHoc=" + ngayNhapHoc + '}';
    }
    
    public String loadTenChuyenNganh(){
        if (maChuyenNganh.equals("LTW")) {
            return "Lập trình web";
        } else if (maChuyenNganh.equals("PTPM")) {
            return "Phát triển phần mềm";
        } else {
            return "Tự động hóa";
        }
    }
}
