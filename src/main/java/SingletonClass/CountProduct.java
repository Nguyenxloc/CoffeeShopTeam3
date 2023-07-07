/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author 84374
 */
public class CountProduct {

    private static CountProduct single_instance = null;

    // Declaring a variable of type String
    public int count = 0;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private CountProduct() {
        count = 0;
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized CountProduct getInstance() {
        if (single_instance == null) {
            single_instance = new CountProduct();
        }
        return single_instance;
    }
    
    
}
