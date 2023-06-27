package service;

import java.util.List;
import model.BaoCao;
import model.CanBo;
import model.ChuyenNganh;
import model.DiemThanhPhan;
import model.GiangVien;
import model.Lich;
import model.LopHoc;
import model.MonHoc;
import repository.DAO_GiangVien;

/**
 *
 * @author Hello
 */
public class GiangVienService {
    private DAO_GiangVien giangVienRepo = new DAO_GiangVien();
    
    public Integer xacThucTaiKhoanGiangVien(GiangVien giangVien) throws Exception{
        GiangVien maGVCanTim = giangVienRepo.xacThucTaiKhoanGiangVien(giangVien.getMaGV());
       if(maGVCanTim != null){
           String matKhau = giangVien.getMatKhau();
           if(maGVCanTim.getMatKhau().equals(matKhau)){
               return 1; // tìm thấy tài khoản và mật khẩu
           }else{
               return -1; // Không tìm thấy mật khẩu
           }
       }
       return 0; // Không tìm thấy tên tài khoản (mã GV)
   }
    
    public List<GiangVien> getAll(){
        try {
            return giangVienRepo.getAllGV();
        } catch (Exception e) {
            return null;
        }
    }
    
    //Lấy danh sách giảng viên theo mã (Đăng nhập)
    public GiangVien getByMaGV(String maGV){
        try {
            return giangVienRepo.getByMaGV(maGV).get(0);
        } catch (Exception e) {
            return null;
        }
    }
    
    //Lấy danh sách lịch học 7 ngày
    public List<Lich> getLichHoc7(String ma){
        try {
            return giangVienRepo.getLichHoc7(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Lấy danh sách lịch học 14 ngày
    public List<Lich> getLichHoc14(String ma){
        try {
            return giangVienRepo.getLichHoc14(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Lấy danh sách lịch học 30 ngày
    public List<Lich> getLichHoc30(String ma){
        try {
            return giangVienRepo.getLichHoc30(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Lấy danh sách chuyên ngành
    public List<ChuyenNganh> getAllChuyenNganh(){
        try {
            return giangVienRepo.getAllChuyenNganh();
        } catch (Exception e) {
            return null;
        }
    }
    
    //Lấy danh sách môn học
    public List<MonHoc> getAllMonHoc(){
        try {
            return giangVienRepo.getAllMonHoc();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<LopHoc> getAllLopHoc(){
        try {
            return giangVienRepo.getAllLopHoc();
        } catch (Exception e) {
            return null;
        }
    }
    
    // Lấy danh sách điểm
    public List<DiemThanhPhan> getDiemThanhPhan(){
        try {
            return giangVienRepo.getDiemThanhPhan();
        } catch (Exception e) {
            return null;
        }
    }
    
    //Lấy danh sách chuyên ngành theo năm
    public List<ChuyenNganh> getChuyenNganhTheoNam(int nam){
        try {
            return giangVienRepo.getChuyenNganhTheoNam(nam);
        } catch (Exception e) {
            return null;
        }
    }
    
    //Lấy danh sách chuyên ngành theo mùa
    public List<ChuyenNganh> getChuyenNganhTheoMua(String mua){
        try {
            return giangVienRepo.getChuyenNganhTheoMua(mua);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Lấy danh sách môn học theo chuyên ngành
    public List<MonHoc> getMonHocTheoChuyenNganh(String maCN){
        try {
            return giangVienRepo.getMonHocTheoChuyenNganh(maCN);
        } catch (Exception e) {
            return null;
        }
    }
    
    //Lấy danh sách lớp học theo môn học
    public List<LopHoc> getLopHocTheoMonHoc(String maMH){
        try {
            return giangVienRepo.getLopHocTheoMonHoc(maMH);
        } catch (Exception e) {
            return null;
        }
    }
    
    //Lấy danh sách sinh viên theo mã để nhập điểm
    public List<DiemThanhPhan> getSinhVienDeNhapDiem(String maSV, String maCN, String maMH, String maLH){
        try {
            return giangVienRepo.getSinhVienDeNhapDiem(maSV, maCN, maMH, maLH);
        } catch (Exception e) {
            return null;
        }
    }
    
    //Lấy danh sách điểm theo mã lớp học và mã giảng viên
    public List<DiemThanhPhan> getDiemTheoMaLH_MaGV(String maMH, String maLH, String maGV){
        try {
            return giangVienRepo.getDiemTheoMaLH_MaGV(maMH, maLH, maGV);
        } catch (Exception e) {
            return null;
        }
    }
    
    //Nhập điểm theo mã
    public Integer nhapDiem(DiemThanhPhan diem){
        try {
            return giangVienRepo.nhapDiem(diem);
        } catch (Exception e) {
            return 0;
        }
    }
    
    //Lấy ra mã lớp học theo tên lớp học
    public MonHoc getMaMHByTenMH(String ten){
        try {
            return giangVienRepo.getMaMHByTenMH(ten);
        } catch (Exception e) {
            return null;
        }
    }
    
    //Gửi báo cáo
    public Integer GuiBaoCao(BaoCao baoCao){
        try {
            return giangVienRepo.GuiBaoCao(baoCao);
        } catch (Exception e) {
            return 0;
        }
    }
    
    //Lấy danh sách lịch sử báo cáo
    public List<BaoCao> getLichSuBaoCaoTheoMaGV(String maGV){
        try {
            return giangVienRepo.getLichSuBaoCaoTheoMaGV(maGV);
        } catch (Exception e) {
            return null;
        }
    }
}
