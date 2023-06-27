package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CanBo;
import model.ChuyenNganh;
import model.DVDangKy;
import model.DichVu;
import model.DiemThanhPhan;
import model.Lich;
import model.LichFull;
import model.LopHoc;
import model.MonHoc;
import model.PhongHoc;
import model.SinhVien;
import model.ThongTinSV;
import repository.DAO_CanBoQuanLy;
import repository.DAO_ChuyenNganh;
import repository.DAO_DVDK20;
import repository.DAO_DichVu;
import repository.DAO_DiemThanhPhan;
import repository.DAO_Lich;
import repository.DAO_LichFull;
import repository.DAO_LopHoc;
import repository.DAO_MonHoc;
import repository.DAO_PhongHoc;
import repository.DAO_ThongTinSinhVien;

/**
 *
 * @author Hello
 */
public class CanBoService {

    private DAO_CanBoQuanLy canBorepo = new DAO_CanBoQuanLy();
    private DAO_ChuyenNganh chuyenNganhRepo = new DAO_ChuyenNganh();
    private DAO_MonHoc monHocRepo = new DAO_MonHoc();
    private DAO_LopHoc lopHocRepo = new DAO_LopHoc();
    private DAO_PhongHoc phongHocRepo = new DAO_PhongHoc();
    private DAO_Lich lichRepo = new DAO_Lich();
    private DAO_LichFull lichFullRepo = new DAO_LichFull();
    private DAO_DiemThanhPhan diemRepo = new DAO_DiemThanhPhan();
    private DAO_ThongTinSinhVien ttsinhvienRepo = new DAO_ThongTinSinhVien();
    private DAO_DichVu dichVuRepo = new DAO_DichVu();
    private DAO_DVDK20 dvRepo3 = new DAO_DVDK20();
    
    public Integer xacThucTaiKhoanCanBo(CanBo canBo) throws Exception {
        CanBo maCBCanTim = canBorepo.xacThucTaiKhoanCanBo(canBo.getMaCB());
        if (maCBCanTim != null) {
            String matKhau = canBo.getMatKhau();
            if (maCBCanTim.getMatKhau().equals(matKhau)) {
                return 1; // tìm thấy tài khoản và mật khẩu
            } else {
                return -1; // Không tìm thấy mật khẩu
            }
        }
        return 0; // Không tìm thấy tên tài khoản (mã CBs)
    }
    ////////////////////////chuyenNganh///////////////////////////////////

    public ArrayList<ChuyenNganh> selectChuyenNganh() {
        ArrayList<ChuyenNganh> lstCN = new ArrayList<>();
        lstCN = chuyenNganhRepo.findAll();
        return lstCN;
    }

    public ChuyenNganh updateChuyenNganh(ChuyenNganh cn) {
        chuyenNganhRepo.update(cn);
        return cn;
    }

    public ChuyenNganh saveChuyenNganh(ChuyenNganh cn) {
        chuyenNganhRepo.save(cn);
        return cn;
    }

    public ChuyenNganh selectChuyenNganhByID(String id) {
        ChuyenNganh cn = new ChuyenNganh();
        cn = chuyenNganhRepo.findById(id);
        return cn;
    }

    public String deleteChuyenNganh(String id) {
        return chuyenNganhRepo.delete(id);
    }
    ////////////////////////////////////////////////////////////
    //////////////////////////////monhoc//////////////////////////

    public ArrayList<MonHoc> selectMonHoc() {
        ArrayList<MonHoc> lstMH = new ArrayList<>();
        lstMH = monHocRepo.findAll();
        return lstMH;
    }

    public MonHoc updateMonHoc(MonHoc mh) {
        monHocRepo.update(mh);
        return mh;
    }

    public MonHoc saveMonHoc(MonHoc mh) {
        monHocRepo.save(mh);
        return mh;
    }

    public MonHoc selectMonHocByID(String id) {
        MonHoc mh = new MonHoc();
        mh = monHocRepo.findById(id);
        return mh;
    }

    public String deleteMonHoc(String id) {
        return monHocRepo.delete(id);
    }
    
    public String saveMonHoc2(MonHoc monHoc){
        if(monHocRepo.save2(monHoc)){
            return "Thêm thành công";
        }else{
            return "Thất bại";
        }
    }
    
    public String updateMonHoc2(String id,MonHoc monHoc){
        if(monHocRepo.update2(id, monHoc)){
            return "Sửa thành công";
        }else{
            return "Thất bại";
        }
    }
    
    
    /////////////////////LopHoc////////////////////////

    public ArrayList<LopHoc> selectLopHoc() {
        ArrayList<LopHoc> lstLH = new ArrayList<>();
        lstLH = lopHocRepo.findAll();
        return lstLH;
    }

    public LopHoc updateLopHoc(LopHoc lh) {
        lopHocRepo.update(lh);
        return lh;
    }

    
    public String updateLopHoc2(String id,LopHoc lh){
        if(lopHocRepo.updateLH(id, lh)){
            return "Sửa thành công";
        }else{
            return "Thất bại";
        }
    }
    
    public LopHoc saveLopHoc(LopHoc lh) {
        lopHocRepo.save(lh);
        return lh;
    }

    public LopHoc selectLopHocByID(String id) {
        LopHoc lh = new LopHoc();
        lh = lopHocRepo.findById(id);
        return lh;
    }

    public String deleteLopHoc(String id) {
        return lopHocRepo.delete(id);
    }

    //////////////////////////////////////////////////
    ///////////////////PhongHoc///////////////////////
    public ArrayList<PhongHoc> selectPhongHoc() {
        ArrayList<PhongHoc> lstPH = new ArrayList<>();
        lstPH = phongHocRepo.findAll();
        return lstPH;
    }

    public PhongHoc updatePhongHoc(PhongHoc ph) {
        phongHocRepo.update(ph);
        return ph;
    }

    public PhongHoc saveLopHoc(PhongHoc ph) {
        phongHocRepo.save(ph);
        return ph;
    }

    public PhongHoc selectPhongHocByID(String id) {
        PhongHoc ph = new PhongHoc();
        ph = phongHocRepo.findById(id);
        return ph;
    }

    public String deletePhongHoc(String id) {
        return phongHocRepo.delete(id);
    }

    ///////////////////LichHoc//////////////////////
    public ArrayList<Lich> selectLich() {
        ArrayList<Lich> lstPH = new ArrayList<>();
        lstPH = lichRepo.findAll();
        return lstPH;
    }

    public Lich updateLich(Lich lh,String maSv,String maMonHoc,String maLopHoc) {
//        lichRepo.update(lh,maSv,maMonHoc,maLopHoc);
        return lh;
    }

    public Lich saveLich(Lich lh) {
        lichRepo.save(lh);
        return lh;
    }

    public Lich selectLichByIdSV(String id) {
        Lich lh = new Lich();
        lh = lichRepo.findByIdSV(id);
        return lh;
    }

    public Lich selectLichByIdGV(String id) {
        Lich lh = new Lich();
        lh = lichRepo.findByIdGV(id);
        return lh;
    }

    public Lich selectLichByIdMHLH(String idmh, String idlh) {
        Lich lh = new Lich();
        lh = lichRepo.findByIdMHLH(idmh, idlh);
        return lh;
    }

    public String deleteLichHoc(String id) {
        return lichRepo.delete(id);
    }

    public ArrayList<String> getDay() {
        return lichRepo.getDay();
    }

    public ArrayList<String> getTime() {
        return lichRepo.getTime();
    }

    ////////////////////////////////////////////////
    ///////////////////LichFull//////////////////////
    public ArrayList<LichFull> selectLichFull() {
        ArrayList<LichFull> lstLichFull = new ArrayList<>();
        lstLichFull = lichFullRepo.findAll();
        return lstLichFull;
    }

    public ArrayList<LichFull> selectLichFullByMultipleCondition(String maSv, String hoTenSv, String maMonHoc, String maLopHoc, String maPhongHoc, String maGiangVien, String hoTenGV, String tenMonHoc, Date ngayHoc,String  thoiGian, String loaiLich){
        ArrayList<LichFull> lstLichFull = new ArrayList<>();
        lstLichFull =  lichFullRepo.selectByMultipleCondition(maSv, hoTenSv, maMonHoc, maLopHoc, maPhongHoc, maGiangVien, hoTenGV, tenMonHoc, ngayHoc,thoiGian, loaiLich);
        return lstLichFull;
    }
    
    public Lich selectLichFullByIdSV(String id) {
        Lich lh = new Lich();
        lh = lichRepo.findByIdSV(id);
        return lh;
    }

    public Lich selectLichFullByIdGV(String id) {
        Lich lh = new Lich();
        lh = lichRepo.findByIdGV(id);
        return lh;
    }

    public Lich selectLichFullByIdMHLH(String idmh, String idlh) {
        Lich lh = new Lich();
        lh = lichRepo.findByIdMHLH(idmh, idlh);
        return lh;
    }

    ////////////////////////////////////////////////
    //////////////////DiemThanhPhan/////

    public ArrayList<DiemThanhPhan> getAllDiem(){
        return diemRepo.getList();
    }

    public ArrayList<SinhVien> getCboSV(){
        return diemRepo.getSV();
    }
    
    public ArrayList<LopHoc> getCboLH(){
        return diemRepo.getLH();
    }
    
     public ArrayList<ChuyenNganh> getCboCN(){
         return diemRepo.getCNganh();
     }
    
     public ArrayList<MonHoc> getCboMHoc(){
         return diemRepo.getMHoc();
     }
     
     public String addDiem(DiemThanhPhan diemThanhPhan){
         if(diemRepo.themDiem(diemThanhPhan)){
             return "Thành công";
         }else{
             return "Thất bại";
         }
     }
     
     public String updateDiem(String id,DiemThanhPhan diemThanhPhan){
         if(diemRepo.suaDiem(id, diemThanhPhan)){
             return "Thành công";
         }else{
             return "Thất bại";
         }
     }
     ///////Thoongtinsv////////
     public ArrayList<ThongTinSV> getAllTTSV(){
         return ttsinhvienRepo.getAllTTSV();
     }
     
     public String addTTSV(ThongTinSV thongTinSV){
         if(ttsinhvienRepo.addTTSV(thongTinSV)){
             return "Thêm thành công";
         }else{
             return "Thất bại";
         }
     }
     
     public String suaTTSV(String ma,ThongTinSV thongTinSV){
         if(ttsinhvienRepo.updateTTSV(ma, thongTinSV)){
             return "Thành công";
         }else{
             return "Thất bại";
         }
     }
    
     
     /////////DIchvuSINhVien////////////
     public ArrayList<DichVu> getAllDV2023(){
         return dichVuRepo.getListDV2023();
     }
     
     public String themDV2023(DichVu dichVu){
         if(dichVuRepo.addDV2023(dichVu)){
             return "Thêm thành công";
         }else{
             return "Thất bại";
         }
     }
     
     public String suaDV2023(String ma,DichVu dichVu){
         if(dichVuRepo.updateDV2023(ma, dichVu)){
             return "Sửa thành công";
         }else{
             return "Thất bại";
         }
     }
     
     public String xoaDV2023(String ma){
         if(dichVuRepo.delete2023(ma)){
             return "Xóa thành công";
         }else{
             return "Thất bại";
         }
     }
     
     /////////DV đã đăng kí////////////
     public ArrayList<DVDangKy> getAll3(){
         return dvRepo3.getList3();
     }
     
     
     public String suaTT3(String masv,DVDangKy dVDangKy){
         if(dvRepo3.update3(masv, dVDangKy)){
             return "Thành công";
         }else{
             return "Thất bại";
         }
     }
     
     
     
}
