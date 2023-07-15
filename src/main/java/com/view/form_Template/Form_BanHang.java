/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import model.DoUong;
import com.view.component.paneOfProduct;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Form_BanHang extends javax.swing.JPanel {

    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private paneOfProduct paneProduct;
    private ArrayList<DoUong> lstPerson = new ArrayList<>();

    /**
     * Creates new form Form_QlThongTinSV
     */
    public Form_BanHang() {
        initComponents();
        jScrollPane1.setBorder(null);
        this.setBorder(null);
        LoadlstProduct();
        //Truyền biến vào panel productOfPane
//      paneProduct.setVisible(true);
    }

    private void reLoadProduct() {
        paneProduct = new paneOfProduct(lstPerson);
        jScrollPane1.setViewportView(paneProduct);
        jScrollPane1.getViewport().repaint();
        jScrollPane1.getViewport().revalidate();
    }
//gọi từ service list sản phầm theo order by desc để sql trả về danh sách từng loại đồ uống
    public void LoadlstProduct() {
        lstPerson.clear();
        System.out.println(lstPerson);
        lstPerson.add(new DoUong("Coffe egg1","Coffe",25000.0,"Cafe+Trứng",null));
        lstPerson.add(new DoUong("Coffe egg2", "Coffe",25000.0,"Cafe+Trứng",null));
        lstPerson.add(new DoUong("Coffe egg3", "Coffe",25000.0,"Cafe+Trứng",null));
        lstPerson.add(new DoUong("Coffe egg4", "Coffe",25000.0,"Cafe+Trứng",null));
        lstPerson.add(new DoUong("Coffe egg5", "Coffe",25000.0,"Cafe+Trứng",null));
        lstPerson.add(new DoUong("Coffe egg6", "Coffe",25000.0,"Cafe+Trứng",null));
        lstPerson.add(new DoUong("Coffe egg7", "Coffe",25000.0,"Cafe+Trứng",null));
        lstPerson.add(new DoUong("Coffe egg8", "Coffe",25000.0,"Cafe+Trứng",null));
        lstPerson.add(new DoUong("Coffe egg9", "Coffe",25000.0,"Cafe+Trứng",null));
        lstPerson.add(new DoUong("Coffe egg10", "Coffe",25000.0,"Cafe+Trứng",null));
        lstPerson.add(new DoUong("Coffe egg11", "Coffe",25000.0,"Cafe+Trứng",null));
        lstPerson.add(new DoUong("Coffe egg12", "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break1",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break2",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break3",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break4",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break5",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break6",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break7",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break8",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break9",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break10",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break11",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break12",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break13",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea break14",  "Trà",25000.0,"Trà truyền thống",null));
        lstPerson.add(new DoUong("Tea Milk1",  "Trà sữa",25000.0,"Trà mix sữa",null));
        lstPerson.add(new DoUong("Tea Milk2",   "Trà sữa",25000.0,"Trà mix sữa",null));
        lstPerson.add(new DoUong("Tea Milk3",   "Trà sữa",25000.0,"Trà mix sữa",null));
        lstPerson.add(new DoUong("Tea Milk4",   "Trà sữa",25000.0,"Trà mix sữa",null));
        lstPerson.add(new DoUong("Tea Milk5",   "Trà sữa",25000.0,"Trà mix sữa",null));
        lstPerson.add(new DoUong("Tea Milk6",   "Trà sữa",25000.0,"Trà mix sữa",null));
        lstPerson.add(new DoUong("Tea Milk7",   "Trà sữa",25000.0,"Trà mix sữa",null));
        lstPerson.add(new DoUong("Tea Milk8",   "Trà sữa",25000.0,"Trà mix sữa",null));
        lstPerson.add(new DoUong("Tea Milk9",   "Trà sữa",25000.0,"Trà mix sữa",null));
        lstPerson.add(new DoUong("Tea Milk10",   "Trà sữa",25000.0,"Trà mix sữa",null));
        lstPerson.add(new DoUong("Tea Milk11",   "Trà sữa",25000.0,"Trà mix sữa",null));
        reLoadProduct();
    }


    ////////////////////////svfunc//////////////////////////////////////
    public void loadToChuyenNganhCBO() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(531, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
