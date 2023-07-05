/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author 84374
 */
public class paneOfProduct extends JPanel {
//        setLayout(new BorderLayout());
//        add(BorderLayout.WEST, new JScrollPane(paneOfProduct1));
//        paneOfProduct1.setVisible(true);

    static ArrayList<Person> lstPerson = new ArrayList<>();
    static ArrayList<ProductCell> lstCell = new ArrayList<>();

    public paneOfProduct() {
        lstPerson.add(new Person("John111111111111111", "DEV1"));
        lstPerson.add(new Person("John2111111111111", "DEV2"));
        lstPerson.add(new Person("John3", "DEV3"));
        lstPerson.add(new Person("John4", "DEV4"));
        lstPerson.add(new Person("John5", "DEV5"));
        lstPerson.add(new Person("John6", "DEV6"));
        lstPerson.add(new Person("John7", "DEV7"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        lstPerson.add(new Person("John8", "DEV8"));
        System.out.println("original number: " + lstPerson.size() / 3);
        System.out.println("ceil: " + Math.ceil(lstPerson.size() / 3));
        System.out.println("num of row: "+(Integer.valueOf(lstPerson.size() / 2)));
        GridLayout grid = new GridLayout(Integer.valueOf(lstPerson.size() /3)+1, 3);
        grid.setVgap(2);
        grid.setHgap(2);
        this.setLayout(grid);
        for (int i = 0; i < lstPerson.size(); i++) {
            ProductCell cell = new ProductCell(null, lstPerson.get(i).getName(), 25.50, lstPerson.get(i).getJob());
            lstCell.add(cell);
            lstCell.get(i).setName(String.valueOf(i));
            lstCell.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // index để truyền vào hàm để trả về một sản phẩm
                    int index = Integer.valueOf(e.getComponent().getName());
                    //
                    System.out.println("index: " + index);
//////////////////////handling  folow old style, too much risk                    
//                    System.out.println("source: "+ e.getSource());
//                    System.out.println(e.getComponent().getX());
//                    System.out.println(e.getComponent().getY());
//                    System.out.println((e.getComponent().getX() / 198) + 1);
//                    System.out.println((e.getComponent().getY() / 289) + 1);
//                    int colNum = (e.getComponent().getX() / 198) + 1;
//                    int rowNum = (e.getComponent().getY() / 289) + 1;
//                    int index = 4 * (rowNum - 1) + (colNum);
//                    System.out.println("index: " + index);
//                    System.out.println(getFromSelectedProduct(index - 1).getName());
//////////////////////
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    cell.getLblNameProduct().setForeground(Color.blue);
                    cell.getLblPriceProduct().setForeground(Color.blue);
                    cell.getLblDes().setForeground(Color.blue);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    cell.getLblNameProduct().setForeground(Color.red);
                    cell.getLblPriceProduct().setForeground(Color.red);
                    cell.getLblDes().setForeground(Color.red);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    cell.getLblNameProduct().setForeground(Color.red);
                    cell.getLblPriceProduct().setForeground(Color.red);
                    cell.getLblDes().setForeground(Color.red);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    cell.getLblNameProduct().setForeground(Color.black);
                    cell.getLblPriceProduct().setForeground(Color.black);
                    cell.getLblDes().setForeground(Color.black);
                }

            });
            this.add(lstCell.get(i));
        }
    }

    public static Person getFromSelectedProduct(int count) {
        return lstPerson.get(count);
    }

    public static void MoveToHDChiTiet(int count) {
        //////lấy id sản phẩm rồi chuyển sang hóa đơn chi tiết;
    }

}
