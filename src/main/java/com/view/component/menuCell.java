/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;

/**
 *
 * @author 84374
 */
public class menuCell extends javax.swing.JPanel {

    /**
     * Creates new form menuCell
     */
    public menuCell(String nameOfProduct, Double priceOfProduct) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        initComponents();
        lblNameDrink.setText(nameOfProduct);
        lblPriceOfDrink.setText(formatter.format(priceOfProduct) + "VNĐ");
        this.setOpaque(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNameDrink = new javax.swing.JLabel();
        lblPriceOfDrink = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        lblNameDrink.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNameDrink.setForeground(new java.awt.Color(239, 204, 162));
        lblNameDrink.setText("#tenDoUong");

        lblPriceOfDrink.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPriceOfDrink.setForeground(new java.awt.Color(239, 204, 162));
        lblPriceOfDrink.setText("#giaBan");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(239, 204, 162));
        jLabel1.setText("........................");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNameDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPriceOfDrink)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNameDrink)
                    .addComponent(lblPriceOfDrink)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblNameDrink;
    private javax.swing.JLabel lblPriceOfDrink;
    // End of variables declaration//GEN-END:variables
}
