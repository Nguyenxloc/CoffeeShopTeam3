/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author 84374
 */
public class HoaDon {
    private String id;
    private Ban ban;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private String ma;
    private String ngayTao;
    private String ngayThanhToan;
    private int  tinhTrangThanhToan;
    private int  trangThaiPhaChe;
    private GiamGia maGiamGia;
    private int stt;

    public HoaDon() {
    }

    public HoaDon(String id, Ban ban, KhachHang khachHang, NhanVien nhanVien, String ma, String ngayTao, String ngayThanhToan, int tinhTrangThanhToan, int trangThaiPhaChe, GiamGia maGiamGia, int stt) {
        this.id = id;
        this.ban = ban;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrangThanhToan = tinhTrangThanhToan;
        this.trangThaiPhaChe = trangThaiPhaChe;
        this.maGiamGia = maGiamGia;
        this.stt = stt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getTinhTrangThanhToan() {
        return tinhTrangThanhToan;
    }

    public void setTinhTrangThanhToan(int tinhTrangThanhToan) {
        this.tinhTrangThanhToan = tinhTrangThanhToan;
    }

    public int getTrangThaiPhaChe() {
        return trangThaiPhaChe;
    }

    public void setTrangThaiPhaChe(int trangThaiPhaChe) {
        this.trangThaiPhaChe = trangThaiPhaChe;
    }

    public GiamGia getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(GiamGia maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "id=" + id + ", ban=" + ban + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + ", ma=" + ma + ", ngayTao=" + ngayTao + ", ngayThanhToan=" + ngayThanhToan + ", tinhTrangThanhToan=" + tinhTrangThanhToan + ", trangThaiPhaChe=" + trangThaiPhaChe + ", maGiamGia=" + maGiamGia + ", stt=" + stt + '}';
    }
  
}
