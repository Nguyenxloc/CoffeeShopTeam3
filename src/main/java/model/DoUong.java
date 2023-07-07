/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 84374
 */
public class DoUong {
    String ten;
    String loai;
    Double giaCa;
    String moTa;
    byte[] img;

    public DoUong() {
    }

    public DoUong(String ten, String loai, Double giaCa, String moTa, byte[] img) {
        this.ten = ten;
        this.loai = loai;
        this.giaCa = giaCa;
        this.moTa = moTa;
        this.img = img;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Double getGiaCa() {
        return giaCa;
    }

    public void setGiaCa(Double giaCa) {
        this.giaCa = giaCa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "DoUong{" + "ten=" + ten + ", loai=" + loai + ", giaCa=" + giaCa + ", moTa=" + moTa + ", img=" + img + '}';
    }
    
}
