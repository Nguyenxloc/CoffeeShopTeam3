/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.main;

import ultilities.Utilitys;

/**
 *
 * @author 84374
 */
public class MainController {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
        System.out.println("===================Test jdbc=================");
        Utilitys dbconn = new Utilitys();
        System.out.println(dbconn.openDbConnection());
        System.out.println("=============================================");
//        int triggerNum = 1;/// example that parament must be change follow up actor
//        //// tạo ra những biến để chuyển tab
//             java.awt.EventQueue.invokeLater(new Runnable() {cbc
//        if(triggerNum==0){
//            public void run() {
//                new MainOfSV().setVisible(true);
//            }
//        });
//        }
//        else if(triggerNum==1){
//             java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainOfGV().setVisible(true);
//            }
//        });
//        }
//        else if(triggerNum==2){
//            java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainOfCB().setVisible(true);
//            }
//        });
//        }
    }
}
