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
    private String idBan;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private String ma;
    private Date ngayTao;
    private Date ngayThanhToan;
    private int  tinhTrangThanhToan;
    private int  trangThaiPhaChe;
    private GiamGia maGiamGia;

    public HoaDon() {
    }

    public HoaDon(String id, String idBan, KhachHang khachHang, NhanVien nhanVien, String ma, Date ngayTao, Date ngayThanhToan, int tinhTrangThanhToan, int trangThaiPhaChe, GiamGia maGiamGia) {
        this.id = id;
        this.idBan = idBan;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrangThanhToan = tinhTrangThanhToan;
        this.trangThaiPhaChe = trangThaiPhaChe;
        this.maGiamGia = maGiamGia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdBan() {
        return idBan;
    }

    public void setIdBan(String idBan) {
        this.idBan = idBan;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
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

    @Override
    public String toString() {
        return "HoaDon{" + "id=" + id + ", idBan=" + idBan + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + ", ma=" + ma + ", ngayTao=" + ngayTao + ", ngayThanhToan=" + ngayThanhToan + ", tinhTrangThanhToan=" + tinhTrangThanhToan + ", trangThaiPhaChe=" + trangThaiPhaChe + ", maGiamGia=" + maGiamGia + '}';
    }

}
