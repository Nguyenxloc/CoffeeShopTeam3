/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 84374
 */
public class GiamGia {
     private String maGiamGia;
     private double phanTram;
     private String moTa;
     private String ngayTao;

    public GiamGia() {
    }

    public GiamGia(String maGiamGia, double phanTram, String moTa, String ngayTao) {
        this.maGiamGia = maGiamGia;
        this.phanTram = phanTram;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public double getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(double phanTram) {
        this.phanTram = phanTram;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "GiamGia{" + "maGiamGia=" + maGiamGia + ", phanTram=" + phanTram + ", moTa=" + moTa + ", ngayTao=" + ngayTao + '}';
    }
   
}
