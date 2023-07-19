/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 84374
 */
public class Ban {
    private String idBan;
    private String ngayTao;

    public Ban(String idBan, String ngayTao) {
        this.idBan = idBan;
        this.ngayTao = ngayTao;
    }

    public Ban() {
    }

    public String getIdBan() {
        return idBan;
    }

    public void setIdBan(String idBan) {
        this.idBan = idBan;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "Ban{" + "idBan=" + idBan + ", ngayTao=" + ngayTao + '}';
    }

}
