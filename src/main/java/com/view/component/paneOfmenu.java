/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.component;

import SingletonClass.LstChiTietDoUong_singleton;
import model.ChiTietDoUong;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import model.HoaDon;
import viewModel.ChiTietDoUongNoIMG;

/**
 *
 * @author 84374
 */
public class paneOfmenu extends JPanel {
//        setLayout(new BorderLayout());
//        add(BorderLayout.WEST, new JScrollPane(paneOfProduct1));
//        paneOfProduct1.setVisible(true);

    static ArrayList<menuCell> lstCell = new ArrayList<>();
    static ChiTietDoUong drinkDetail = new ChiTietDoUong();
    static ChiTietDoUongNoIMG drinkDetailNoIMG = new ChiTietDoUongNoIMG();
    static HoaDon localHoaDon = new HoaDon();
    static JTable localTbl = new JTable();
    static JLabel localLblCheck = new JLabel();
    String path = "C:\\Users\\84374\\Documents\\NetBeansProjects\\CoffeeShop\\src\\main\\java\\com\\view\\icon\\coffe.png";
    File file = new File(path);
    String absolutePath = file.getAbsolutePath();
    String dir = absolutePath;
    ImageIcon oriImgIcon = new ImageIcon(dir);
    Image image = oriImgIcon.getImage(); // transform it

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 570, 620, null);
    }

    public paneOfmenu(ArrayList<ChiTietDoUong> lstMenu, byte[] img) {
        int count = 0;
        GridBagLayout gridBag = new GridBagLayout();
        this.setLayout(gridBag);
        GridBagConstraints c = new GridBagConstraints();

        for (int i = 0; i < Integer.valueOf(lstMenu.size() / 2) + 1; i++) {
            for (int j = 0; j < 2; j++) {
                menuCell cell = new menuCell(lstMenu.get(i).getTenDoUong(), lstMenu.get(i).getGiaBan());
                lstCell.add(cell);
                lstCell.get(i).setName(String.valueOf(i));
                this.add(lstCell.get(i));
                count++;
            }
        }

//        GridLayout grid = new GridLayout(Integer.valueOf(lstMenu.size() / 2) + 1, 2);
//        grid.setVgap(-5);
//        grid.setHgap(0);
//        this.setLayout(grid);
//        for (int i = 0; i < lstMenu.size(); i++) {
//            System.out.println("test");
//            menuCell cell = new menuCell(lstMenu.get(i).getTenDoUong(), lstMenu.get(i).getGiaBan());
//            lstCell.add(cell);
//            lstCell.get(i).setName(String.valueOf(i));
//            this.add(lstCell.get(i));
//        }
    }

    public static void MoveToHDChiTiet(int count) {
        //////lấy id sản phẩm rồi chuyển sang hóa đơn chi tiết;
    }

}
