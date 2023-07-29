/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.GiaoCa;
import repository.DAO_GiaoCa;

/**
 *
 * @author LENOVO T560
 */
public class GiaoCaService {

    private DAO_GiaoCa giaoCaRepository;

    public GiaoCaService() {
        giaoCaRepository = new DAO_GiaoCa();
    }

    public ArrayList<GiaoCa> selectALL() {
        return giaoCaRepository.selectALL();
    }

    public void delete(String id) {
        giaoCaRepository.delete(id);
    }

}
