/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ViHocPhi {
    
    public String maVi;
    
    public String maSV;
    
    public double soDuHocPhi;

    public ViHocPhi() {
    }

    public ViHocPhi(String maVi, String maSV, double soDuHocPhi) {
        this.maVi = maVi;
        this.maSV = maSV;
        this.soDuHocPhi = soDuHocPhi;
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

    public double getSoDuHocPhi() {
        return soDuHocPhi;
    }

    public void setSoDuHocPhi(double soDuHocPhi) {
        this.soDuHocPhi = soDuHocPhi;
    }

    @Override
    public String toString() {
        return "ViHocPhi{" + "maVi=" + maVi + ", maSV=" + maSV + ", soDuHocPhi=" + soDuHocPhi + '}';
    }
}
