/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

/**
 *
 * @author 84374
 */
public class Application {
    public static void main(String[] args) {
        System.out.println(CountProduct.getInstance().count);
        CountProduct.getInstance().count=1;
        System.out.println(CountProduct.getInstance().count);
    }
}
