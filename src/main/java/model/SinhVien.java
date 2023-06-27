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
public class SinhVien {
    
    public String matKhau;
    public String vaiTro;
    public byte[] anh;
    public int numOrder;
    public String maSV;
    public String hoTen;
    public int gioitinh;
    public Date ngaySinh;
    public String email;
    public String soDienThoai;
    public String diaChi;
    public String trangThai;
    public int nienKhoa;
    public String maChuyenNganh;
    public Date ngayNhapHoc;

    public SinhVien() {
    }

    public SinhVien(String matKhau, String vaiTro, byte[] anh, int numOrder, String maSV, String hoTen, int gioitinh, Date ngaySinh, String email, String soDienThoai, String diaChi, String trangThai, int nienKhoa, String maChuyenNganh, Date ngayNhapHoc) {
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.anh = anh;
        this.numOrder = numOrder;
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
        this.ngayNhapHoc = ngayNhapHoc;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public int getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(int numOrder) {
        this.numOrder = numOrder;
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

    public Date getNgayNhapHoc() {
        return ngayNhapHoc;
    }

    public void setNgayNhapHoc(Date ngayNhapHoc) {
        this.ngayNhapHoc = ngayNhapHoc;
    }

    @Override
    public String toString() {
        return "SinhVien{" + "matKhau=" + matKhau + ", vaiTro=" + vaiTro + ", anh=" + anh + ", numOrder=" + numOrder + ", maSV=" + maSV + ", hoTen=" + hoTen + ", gioitinh=" + gioitinh + ", ngaySinh=" + ngaySinh + ", email=" + email + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + ", trangThai=" + trangThai + ", nienKhoa=" + nienKhoa + ", maChuyenNganh=" + maChuyenNganh + ", ngayNhapHoc=" + ngayNhapHoc + '}';
    }
}
