/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lê Chấn Khang
 */
public class DVDangKy {
    private String masv;
    private String hoten;
    private String tenDV;
    private String moTa;
    private String trangThai;

    public DVDangKy() {
    }

    public DVDangKy(String masv, String trangThai) {
        this.masv = masv;
        this.trangThai = trangThai;
    }

    
    public DVDangKy(String masv, String hoten, String tenDV, String moTa, String trangThai) {
        this.masv = masv;
        this.hoten = hoten;
        this.tenDV = tenDV;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
    
}
