/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_ThongKe_Service;

import DoUong_ThongKe_Repository.DAO_LoaiDoUong;
import DoUong_ThongKe_Repository.iLoaiDoUong;
import Model_DoUong_ThongKe.LoaiDoUong;
import java.util.ArrayList;

/**
 *
 * @author LENOVO T560
 */
public class LoaiDoUongService implements iLoaiDoUong {

    private DAO_LoaiDoUong repository = new DAO_LoaiDoUong();

    @Override
    public ArrayList<LoaiDoUong> selectALl() {
        return repository.selectALl();
    }

    @Override
    public LoaiDoUong selectByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(LoaiDoUong loaiDoUong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(LoaiDoUong loaiDoUong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
