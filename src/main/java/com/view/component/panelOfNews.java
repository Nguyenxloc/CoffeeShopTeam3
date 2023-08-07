/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.component;

import SingletonClass.LstChiTietDoUong_singleton;
import model.ChiTietDoUong;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import model.HoaDon;
import model.News;
import org.apache.poi.ss.formula.FormulaType;
import viewModel.ChiTietDoUongNoIMG;

/**
 *
 * @author 84374
 */
public class panelOfNews extends JPanel {
//        setLayout(new BorderLayout());
//        add(BorderLayout.WEST, new JScrollPane(paneOfProduct1));
//        paneOfProduct1.setVisible(true);

    static ArrayList<NewsCell> lstCell = new ArrayList<>();
    static News localNews = new News();
    public panelOfNews(ArrayList<News> lstNews) {
        GridLayout grid = new GridLayout(Integer.valueOf(lstNews.size() / 3) + 1, 3);
        grid.setVgap(-5);
        grid.setHgap(0);
        this.setLayout(grid);
        for (int i = 0; i < lstNews.size(); i++) {
            NewsCell cell = new NewsCell(lstNews.get(i));
            lstCell.add(cell);
            lstCell.get(i).setName(String.valueOf(i));
            lstCell.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // index để truyền vào hàm để trả về một sản phẩm
                    int index = Integer.valueOf(e.getComponent().getName());
                    //
                    System.out.println("index: " + index);
//                    localNews = LstChiTietDoUong_singleton.getInstance().lstChiTietDoUongs.get(index);
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            ///show news panel
                        }
                    });
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
                    cell.getLblIMGNews().setForeground(Color.blue);
                    cell.getLblTittle().setForeground(Color.blue);

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    cell.getLblIMGNews().setForeground(Color.red);
                    cell.getLblTittle().setForeground(Color.red);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    cell.getLblIMGNews().setForeground(Color.red);
                    cell.getLblTittle().setForeground(Color.red);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    cell.getLblIMGNews().setForeground(Color.black);
                    cell.getLblTittle().setForeground(Color.black);
                }

            });
            this.add(lstCell.get(i));
        }
    }

//    public static Person getFromSelectedProduct(int count) {
//        return lstPerson.get(count);
//    }
    public static void MoveToHDChiTiet(int count) {
        //////lấy id sản phẩm rồi chuyển sang hóa đơn chi tiết;
    }

}
