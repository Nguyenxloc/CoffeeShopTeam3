/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.HoaDon;
import repository.DAO_HoaDon;

/**
 *
 * @author 84374
 */
public class HoaDonService {

    DAO_HoaDon dao_HoaDon = new DAO_HoaDon();

    public void saveHoaDon(HoaDon hoaDon) {
        dao_HoaDon.save(hoaDon);
    }

    public ArrayList<HoaDon> getListHoaDon() {
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        lstHoaDon = dao_HoaDon.selectALl();
        return lstHoaDon;
    }

    public ArrayList<HoaDon> getListHoaDonCho() {
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        lstHoaDon = dao_HoaDon.selectHoaDonCho();
        return lstHoaDon;
    }

    public void updateHoaDon(HoaDon hoaDon) {
        dao_HoaDon.update(hoaDon);
    }

    public void updateStt(int stt, String id) {
        dao_HoaDon.updateStt(stt, id);
    }

//    public void deleteChiTietDoUong(String  tenDoUong){
//        dAO_ChiTietDoUong.delete(tenDoUong);
//    }
    //     public ArrayList<HoaDon> getTimKiem(String tenDoUong,String idLoaiDoUong,double giaBatDau,double giaKetThuc ){
//        ArrayList<ChiTietDoUong> lstChiTietDoUong  = new ArrayList<>();
//        lstChiTietDoUong = dAO_ChiTietDoUong.selectByFlexibleCondition(tenDoUong, idLoaiDoUong,giaBatDau,giaKetThuc);
//        return lstChiTietDoUong;
//    }
    public ArrayList<HoaDon> getHoaDonDangPhaChe() {
        ArrayList<HoaDon> lstHoaDonDangPhaChe = new ArrayList<>();
        lstHoaDonDangPhaChe = dao_HoaDon.selectHoaDonDangPhaChe();
        return lstHoaDonDangPhaChe;
    }

    public void updateTTPhaChe(String id, int i) {
         dao_HoaDon.updateTTPhaChe(id,i);
    }

    public HoaDon getHoaDonByID(String LocalId) {
        return dao_HoaDon.selectByID(LocalId);

    }
}
