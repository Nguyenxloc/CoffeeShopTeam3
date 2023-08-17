/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Ban;
import repository.BanRepository;
import viewModel.QLBan;

/**
 *
 * @author MSI-G8
 */
public class BanServicee implements IBanService{

    private BanRepository banRepository = new BanRepository();
    
    @Override
    public List<QLBan> getALl() {
        List<QLBan> ds = new ArrayList<>();
        List<Ban> list = banRepository.getALL();
        for(Ban b : list){
            QLBan ban = new QLBan(b.getIdBan(), b.getTen(), "");
            ds.add(ban);
        }
        return ds;
    }

    @Override
    public void them(QLBan ban) {
        Ban domainModel = new Ban(ban.getIdBan(), ban.getTen(), "");
        if(banRepository.checkMa(ban.getIdBan())){
            JOptionPane.showMessageDialog(null, "Id đã tồn tại");
            return;
        }else{
            JOptionPane.showMessageDialog(null, "Thêm Thành Công");
            banRepository.them(domainModel);
        }
    }

    @Override
    public void xoa(int id) {
        banRepository.xoa(id);
    }

    @Override
    public void sua(QLBan ban) {
        Ban domainModel = new Ban(ban.getIdBan(), ban.getTen(), "");
        if(banRepository.checkMa(ban.getIdBan())){
            JOptionPane.showMessageDialog(null, "Thêm Thành Công");
             banRepository.them(domainModel);
        }else{
            JOptionPane.showMessageDialog(null, "Id đã tồn tại");
            return;
        }
    }
    
}
