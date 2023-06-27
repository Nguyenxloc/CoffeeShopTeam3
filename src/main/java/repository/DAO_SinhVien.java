/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import com.view.model.SinhVien.ThongTinSinhVien;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.GiangVien;
import model.SinhVien;
import ultilities.DBConnection;
import ultilities.DBConnectorGV;
import ultilities.DbConnection1;

/**
 *
 * @author 84374
 */
public class DAO_SinhVien{
  
  final String INSERT_SQL = "INSERT INTO [dbo].[SINHVIEN] VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  final String UPDATE_SQL = "UPDATE [dbo].[SINHVIEN] SET [TAIKHOAN]=?, [MATKHAU]=?, [VAITRO]=?, [ANH]=?, [HOTEN]=?, [GIOITINH]=?, [NGAYSINH]=?, [EMAIL]=?, [SDT]=?, [DIACHI]=?, [TRANGTHAI]=?, [NIENKHOA]=?, [MACHUYENNGANH]=?, [NGAYNHAPHOC]=? WHERE [MaSV] = ?";
  final String DELETE_SQL = "DELETE FROM [dbo].[SINHVIEN] WHERE [MaSV] = ?";
  final String SELECT_BY_SQL = "SELECT * FROM [dbo].[SINHVIEN] WHERE [MaSV] = ?";
  final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[SINHVIEN]";
  private SinhVien sinhVien;
  private ArrayList<SinhVien> _lstSV;
      public DAO_SinhVien() {
    _lstSV = new ArrayList<>();

  }
  public ArrayList<SinhVien> findAll() {
     _lstSV = new ArrayList<>();
    return getSelectSql(SELECT_ALL_SQL);
  }

  public SinhVien findById(String id) {
    SinhVien sv;
    sv = getSelectSql(SELECT_BY_SQL, id).get(0);
    return sv;
  }

  public SinhVien save(SinhVien sv) {   
    DBConnection.ExcuteDungna(INSERT_SQL,sv.getMaSV(), sv.getMatKhau(),sv.getVaiTro(),sv.getAnh()
           ,sv.getHoTen(),sv.getGioitinh(),sv.getNgaySinh(),sv.getEmail(),sv.getSoDienThoai(),sv.getDiaChi(),sv.getTrangThai(),sv.getNienKhoa(),sv.getMaChuyenNganh(),sv.getNgayNhapHoc());
    return sv;
  }

  public String delete(String id) {
     DBConnection.ExcuteDungna(DELETE_SQL, id);
        return id;
  }
  
  public SinhVien update(SinhVien sv){
         DBConnection.ExcuteDungna(UPDATE_SQL, sv.getMatKhau(),sv.getVaiTro(),sv.getAnh()
           ,sv.getHoTen(),sv.getGioitinh(),sv.getNgaySinh(),sv.getEmail(),sv.getSoDienThoai(),sv.getDiaChi(),sv.getTrangThai(),sv.getNienKhoa(),sv.getMaChuyenNganh(),sv.getNgayNhapHoc(),sv.getMaSV());
    return sv;
  }

  public long totalCount() {
    long total = 0;
    total = _lstSV.size();
    return total;
  }

  private ArrayList<SinhVien> getSelectSql(String sql, Object... args) {
    try {
      ResultSet rs = DBConnection.getDataFromQuery(sql, args);
      while (rs.next()) {      
        System.out.println(rs.getLong(4));
//        _lstSV.add(new SinhVien(rs.getString(1),rs.getString(2), rs.getString(3), rs.getByte(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getDate(16))
//        );
      }
      return _lstSV;
    } catch (SQLException ex) {
      throw new RuntimeException();
    }
  }
  
  
  public List<SinhVien> getTaiKhoan() throws Exception{  //Lấy danh sách tài khoản của sinh viên
        List<SinhVien> lstTKSinhVien = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT MASV, MATKHAU, VAITRO FROM SINHVIEN";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
           String ma = rs.getString("MASV");
           String matKhau = rs.getString("MATKHAU");
           String vaiTro = rs.getString("VAITRO");
           
           SinhVien sinhVien = new SinhVien();   
           sinhVien.setMaSV(ma);
           sinhVien.setMatKhau(matKhau);
           sinhVien.setVaiTro(vaiTro);
           
           lstTKSinhVien.add(sinhVien);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstTKSinhVien;
    }
    
    public SinhVien xacThucTaiKhoanSinhVien(String username) throws Exception{ // Tìm theo tên tài khoản 
        List<SinhVien> lstTKSinhVien = getTaiKhoan();
        SinhVien maSVCanTim = null;
        for (SinhVien sinhVien : lstTKSinhVien) {
            if(sinhVien.getMaSV().equals(username)){
                maSVCanTim = sinhVien;
                break;
            }
        }
        return maSVCanTim;
    }
    
    public ArrayList<ThongTinSinhVien> getAll(){
        ArrayList<ThongTinSinhVien> lstThongTinSinhVien = new ArrayList<>();
        String sql = "select * from SinhVien";
        try (Connection con = DbConnection1.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { 
                Blob blob = rs.getBlob("ANH");
                byte[] anh = blob.getBytes(1, (int) blob.length());
                
                ThongTinSinhVien thongTinSinhVien = new ThongTinSinhVien();
                thongTinSinhVien.setMaSV(rs.getString("MaSV"));
                thongTinSinhVien.setHoTen(rs.getString("HoTen"));
                thongTinSinhVien.setGioitinh(rs.getInt("GioiTinh"));
                thongTinSinhVien.setNgaySinh(rs.getDate("NgaySinh"));
                thongTinSinhVien.setEmail(rs.getString("Email"));
                thongTinSinhVien.setSoDienThoai(rs.getString("SDT"));
                thongTinSinhVien.setDiaChi(rs.getString("DiaChi"));
                thongTinSinhVien.setTrangThai(rs.getString("TrangThai"));
                thongTinSinhVien.setNienKhoa(rs.getInt("NienKhoa"));
                thongTinSinhVien.setMaChuyenNganh(rs.getString("MaChuyenNganh"));
                thongTinSinhVien.setNgayNhapHoc(rs.getDate("NGAYNHAPHOC"));
                thongTinSinhVien.setAnh(anh);
                lstThongTinSinhVien.add(thongTinSinhVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstThongTinSinhVien;
    }
    
    public ArrayList<ThongTinSinhVien> getByMaSV(String maSV){
        ArrayList<ThongTinSinhVien> lstThongTinSinhVien = new ArrayList<>();
        String sql = "select MaSV, Anh, HoTen, GioiTinh, NgaySinh, Email, SDT, DiaChi, TrangThai, NienKhoa, MaChuyenNganh, NGAYNHAPHOC from SinhVien where MaSV = ?";
        try (Connection con = DbConnection1.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){    
            ps.setObject(1, maSV);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {   
                Blob blob = rs.getBlob("ANH");
                byte[] anh = blob.getBytes(1, (int) blob.length());
                
                ThongTinSinhVien thongTinSinhVien = new ThongTinSinhVien();
                thongTinSinhVien.setMaSV(rs.getString("MaSV"));
                thongTinSinhVien.setHoTen(rs.getString("HoTen"));
                thongTinSinhVien.setGioitinh(rs.getInt("GioiTinh"));
                thongTinSinhVien.setNgaySinh(rs.getDate("NgaySinh"));
                thongTinSinhVien.setEmail(rs.getString("Email"));
                thongTinSinhVien.setSoDienThoai(rs.getString("SDT"));
                thongTinSinhVien.setDiaChi(rs.getString("DiaChi"));
                thongTinSinhVien.setTrangThai(rs.getString("TrangThai"));
                thongTinSinhVien.setNienKhoa(rs.getInt("NienKhoa"));
                thongTinSinhVien.setMaChuyenNganh(rs.getString("MaChuyenNganh"));
                thongTinSinhVien.setNgayNhapHoc(rs.getDate("NGAYNHAPHOC"));
                thongTinSinhVien.setAnh(anh);
                lstThongTinSinhVien.add(thongTinSinhVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstThongTinSinhVien;
    }
       
}
