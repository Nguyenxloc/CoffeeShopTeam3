/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MSI-G8
 */
public class NhanVien {
    private String idNV;
    private String ten;
    private String tenDem;
    private String ho;

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public NhanVien() {
    }

    public NhanVien(String idNV, String ten, String tenDem, String ho) {
        this.idNV = idNV;
        this.ten = ten;
        this.tenDem = tenDem;
        this.ho = ho;
    }

    @Override
    public String toString() {
        return ten + " " + "" + tenDem + " " + ho;
    }

}
