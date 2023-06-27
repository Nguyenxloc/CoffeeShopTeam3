/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Lê Chấn Khang
 */
public class ThongTinSV {
    public String maSV;
    public String hoTen;
    public int gioitinh;
    public String ngaySinh;
    public String email;
    public String soDienThoai;
    public String diaChi;
    public String trangThai;
    public int nienKhoa;
    public String maChuyenNganh;

    public ThongTinSV() {
    }

    public ThongTinSV(String maSV, String hoTen, int gioitinh, String ngaySinh, String email, String soDienThoai, String diaChi, String trangThai, int nienKhoa, String maChuyenNganh) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.gioitinh = gioitinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.nienKhoa = nienKhoa;
        this.maChuyenNganh = maChuyenNganh;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
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

    public int getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(int nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public String getMaChuyenNganh() {
        return maChuyenNganh;
    }

    public void setMaChuyenNganh(String maChuyenNganh) {
        this.maChuyenNganh = maChuyenNganh;
    }
    
    public String getHienThiGioiTinh(){
        if(gioitinh==1){
            return "Nam";
        }else{
            return "Nữ";
        }
    }
    
}
