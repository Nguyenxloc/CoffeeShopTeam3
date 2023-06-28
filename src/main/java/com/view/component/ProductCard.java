/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 84374
 */
public class ProductCard extends JPanel implements ActionListener {

    public ProductCard(byte[]img,String tenLoaiDoUong,String tenDoUong,double giaBan) {
        setLayout(new BorderLayout());
        this.setLayout(new FlowLayout());
        JLabel lblName = new JLabel(tenLoaiDoUong);
        JLabel lblJob = new JLabel(tenDoUong);
        add(lblName, BorderLayout.NORTH);
        add(lblJob,BorderLayout.SOUTH);
        setSize(100, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
}
