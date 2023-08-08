/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import model.ChiTietDoUong;
import model.News;
import service.ChiTietDoUongService_Master;

/**
 *
 * @author 84374
 */
public class LstNews_singleton {

    private static LstNews_singleton single_instance = null;
//    private static service.ChiTietDoUongService_Master  service = new ChiTietDoUongService_Master();
    // Declaring a variable of type String
    public ArrayList<News> lstNews;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private LstNews_singleton() {
        lstNews = new ArrayList<>();
        String dir = null;
        String path = "src\\main\\java\\com\\view\\icon\\coffe.png";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        dir = absolutePath;
        byte[] imgByte = new byte[5000];
        try {
            BufferedImage bImage = ImageIO.read(new File(dir));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            imgByte = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        News new1 = new News(null, "Hướng dẫn sử dụng máy espresso","Máy espresso là một loại máy từ italia", null, null, null, imgByte);
        News new2 = new News(null, "Hướng dẫn pha phin chuẩn vị","Phin truyền thống cà phê việt nam đem lại hương vị độc đáo cho người thưởng thức",null, null, null, imgByte);
        News new3 = new News(null, "Hướng dẫn dẫn sử dụng máy rửa cốc","Cách vận hành máy rửa cốc mọi nhân viên phải biết", null, null, null, imgByte);
        News new4 = new News(null, "Nội quy quán","Mỗi nhân viên khi làm việc tại quán phải tuân thủ nội quy", null, null, null, imgByte);
        lstNews.add(new1);
        lstNews.add(new2);
        lstNews.add(new3);
        lstNews.add(new4);
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized LstNews_singleton getInstance() {
        if (single_instance == null) {
            single_instance = new LstNews_singleton();
        }
        return single_instance;
    }

}
