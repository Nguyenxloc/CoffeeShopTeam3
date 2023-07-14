/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DoUong_ThongKe_Repository;

import DoUong_ThongKe_Model.ChiTietDoUong;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface iChiTietDoUong {

    ArrayList<ChiTietDoUong> selectALl();

    ChiTietDoUong selectByID(String id);

    void save(ChiTietDoUong chiTietDoUong);

    void update(ChiTietDoUong chiTietDoUong);

    void delete(String tenDoUong);

}
