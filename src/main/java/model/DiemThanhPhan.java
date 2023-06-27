/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class DiemThanhPhan {
    
    public String maSV;
    
    public String maMonHoc;
    
    public String maNganhHoc;
    
    public String maLopHoc;
  
    public int nam;
    
     public String mua;
    
    public double lab1;
    
    public double lab2;
    
    public double lab3;
    
    public double lab4;
    
    public double assignment;
    
    public double diemThi;

    public DiemThanhPhan() {
    }

    public DiemThanhPhan(String maSV, String maMonHoc, String maNganhHoc, String maLopHoc, int nam, String mua, double lab1, double lab2, double lab3, double lab4, double assignment, double diemThi) {
        this.maSV = maSV;
        this.maMonHoc = maMonHoc;
        this.maNganhHoc = maNganhHoc;
        this.maLopHoc = maLopHoc;
        this.nam = nam;
        this.mua = mua;
        this.lab1 = lab1;
        this.lab2 = lab2;
        this.lab3 = lab3;
        this.lab4 = lab4;
        this.assignment = assignment;
        this.diemThi = diemThi;
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

    public String getMaNganhHoc() {
        return maNganhHoc;
    }

    public void setMaNganhHoc(String maNganhHoc) {
        this.maNganhHoc = maNganhHoc;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
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

    public double getLab1() {
        return lab1;
    }

    public void setLab1(double lab1) {
        this.lab1 = lab1;
    }

    public double getLab2() {
        return lab2;
    }

    public void setLab2(double lab2) {
        this.lab2 = lab2;
    }

    public double getLab3() {
        return lab3;
    }

    public void setLab3(double lab3) {
        this.lab3 = lab3;
    }

    public double getLab4() {
        return lab4;
    }

    public void setLab4(double lab4) {
        this.lab4 = lab4;
    }

    public double getAssignment() {
        return assignment;
    }

    public void setAssignment(double assignment) {
        this.assignment = assignment;
    }

    public double getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(double diemThi) {
        this.diemThi = diemThi;
    }  

    @Override
    public String toString() {
        return "DiemThanhPhan{" + "maSV=" + maSV + ", maMonHoc=" + maMonHoc + ", maNganhHoc=" + maNganhHoc + ", maLopHoc=" + maLopHoc + ", mua=" + mua + ", nam=" + nam + ", lab1=" + lab1 + ", lab2=" + lab2 + ", lab3=" + lab3 + ", lab4=" + lab4 + ", assignment=" + assignment + ", diemThi=" + diemThi + '}';
    }
    
    public Double getDiemTongKet(){
        Double diemTK = (diemThi*50/100) + (lab1*10/100) + (lab2*10/100) + (lab3*10/100) + (lab4*10/100) + (assignment*10/100);
        return diemTK;
    }
    
    public String getTrangThai(){
        if(getDiemTongKet()>=5){
            return "Pass";
        }else{
            return "Fail";
        }
    }
    
}
