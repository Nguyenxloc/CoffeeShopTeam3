/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository1;

import com.view.model.NhanVien;

/**
 *
 * @author Lê Chấn Khang
 */
public class NhanVienService {
    
    private NhanVienDao repository = new NhanVienDao();
    
      public String them(NhanVien nhanVien) {
        if(repository.addNew(nhanVien)){
            return "Thêm thành công";
        }else{
            return "Thêm thất bại";
        }
    }
    
}
