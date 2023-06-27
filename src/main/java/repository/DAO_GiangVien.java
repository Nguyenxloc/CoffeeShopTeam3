/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BaoCao;
import model.ChuyenNganh;
import model.DiemThanhPhan;
import model.GiangVien;
import model.Lich;
import model.LopHoc;
import model.MonHoc;
import model.PhongHoc;
import ultilities.DBConnection;
import ultilities.DBConnectorGV;

/**
 *
 * @author ADMIN
 */
public class DAO_GiangVien {

    final String INSERT_SQL = "INSERT INTO [dbo].[SINHVIEN] VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    final String DELETE_SQL = "DELETE FROM [dbo].[SINHVIEN] WHERE [MAGV] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[SINHVIEN] WHERE [MAGV] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[SINHVIEN]";
    private GiangVien giangVien;
    private ArrayList<GiangVien> _lstGiangVien;

    public DAO_GiangVien() {
        _lstGiangVien = new ArrayList<>();
    }

    public ArrayList<GiangVien> findAll() {
        _lstGiangVien = new ArrayList<>();
        return getSelectSql(SELECT_ALL_SQL);
    }

    public GiangVien findById(String id) {
        GiangVien giangVien;
        giangVien = getSelectSql(SELECT_BY_SQL, id).get(0);
        return giangVien;
    }

    public GiangVien save(GiangVien giangVien) {
        DBConnection.ExcuteDungna(INSERT_SQL, giangVien.matKhau, giangVien.getVaiTro(), giangVien.getAnh(), giangVien.getMaGV(), giangVien.getHoTen(),
                giangVien.getGioiTinh(), giangVien.getNgaySinh(), giangVien.getEmail(), giangVien.getSoDienThoai(), giangVien.getDiaChi(), giangVien.getNgayVaoLam());
        return giangVien;
    }

    public String delete(String id) {
        DBConnection.ExcuteDungna(DELETE_SQL, id);
        return id;
    }

    public GiangVien update(GiangVien giangVien) {
        DBConnection.ExcuteDungna(UPDATE_SQL, giangVien.matKhau, giangVien.getVaiTro(), giangVien.getAnh(), giangVien.getMaGV(), giangVien.getHoTen(),
                giangVien.getGioiTinh(), giangVien.getNgaySinh(), giangVien.getEmail(), giangVien.getSoDienThoai(), giangVien.getDiaChi(), giangVien.getNgayVaoLam());
        return giangVien;
    }

    public long totalCount() {
        long total = 0;
        total = _lstGiangVien.size();
        return total;
    }

    private ArrayList<GiangVien> getSelectSql(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                System.out.println(rs.getLong(4));
//                _lstGiangVien.add(new GiangVien(rs.getString("MATKHAU"), rs.getString("VAITRO"), rs.getByte("ANH"), rs.getInt("5"), rs.getString("MAGV"), rs.getString("HOTEN"), rs.getInt("GIOITINH"), rs.getDate("NGAYSINH"), rs.getString("EMAIL"), rs.getString("SDT"), rs.getString("DIACHI"), rs.getDate(""));
            }
            return _lstGiangVien;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
    final String UPDATE_SQL = "UPDATE [dbo].[SINHVIEN] SET [MATKHAU]=?, [VAITRO]=?, [ANH]=?, [HOTEN]=?, [GIOITINH]=?, [NGAYSINH]=?, [EMAIL]=?, [SDT]=?, [DIACHI]=?, [NGAYVAOLAM]=?, WHERE [MAGV] = ?";

    // Lấy danh sách tài khoản giảng viên
    public List<GiangVien> getTaiKhoan() throws Exception {  //Lấy danh sách tài khoản của giảng viên
        List<GiangVien> lstTKGiangVien = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT MAGV, MATKHAU, VAITRO FROM GIANGVIEN";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String ma = rs.getString("MAGV");
            String matKhau = rs.getString("MATKHAU");
            String vaiTro = rs.getString("VAITRO");

            GiangVien giangVien = new GiangVien();
            giangVien.setMaGV(ma);
            giangVien.setMatKhau(matKhau);
            giangVien.setVaiTro(vaiTro);

            lstTKGiangVien.add(giangVien);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstTKGiangVien;
    }

    public GiangVien xacThucTaiKhoanGiangVien(String username) throws Exception { // Tìm theo tên tài khoản 
        List<GiangVien> lstTKGiangVien = getTaiKhoan();
        GiangVien maGVCanTim = null;
        for (GiangVien giangVien : lstTKGiangVien) {
            if (giangVien.getMaGV().equals(username)) {
                maGVCanTim = giangVien;
                break;
            }
        }
        return maGVCanTim;
    }

    // Lấy tất cả thông tin giảng viên
    public List<GiangVien> getAllGV() throws Exception {
        List<GiangVien> lstGiangVien = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT ANH, MAGV, HOTEN, GIOITINH, NGAYSINH, EMAIL, SDT, DIACHI, NGAYVAOLAM FROM GiangVien";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Blob blob = rs.getBlob("ANH");
            byte[] anh = blob.getBytes(1, (int) blob.length());

            String maGV = rs.getString("MAGV");
            String hoTen = rs.getString("HOTEN");
            Integer gioiTinh = rs.getInt("GIOITINH");
            Date ngaySinh = rs.getDate("NGAYSINH");
            String email = rs.getString("EMAIL");
            String sdt = rs.getString("SDT");
            String diaChi = rs.getString("DIACHI");
            Date ngayVaoLam = rs.getDate("NGAYVAOLAM");

            GiangVien giangVien = new GiangVien();
            giangVien.setAnh(anh);
            giangVien.setMaGV(maGV);
            giangVien.setHoTen(hoTen);
            giangVien.setGioiTinh(gioiTinh);
            giangVien.setNgaySinh(ngaySinh);
            giangVien.setEmail(email);
            giangVien.setSoDienThoai(sdt);
            giangVien.setDiaChi(diaChi);
            giangVien.setNgayVaoLam(ngayVaoLam);

            lstGiangVien.add(giangVien);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstGiangVien;
    }

    //Lấy danh sách giảng viên theo mã
    public List<GiangVien> getByMaGV(String ma) throws Exception {
        List<GiangVien> lstGiangVien = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT ANH, MAGV, HOTEN, GIOITINH, NGAYSINH, EMAIL, SDT, DIACHI, NGAYVAOLAM FROM GiangVien WHERE MaGV  = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Blob blob = rs.getBlob("ANH");
            byte[] anh = blob.getBytes(1, (int) blob.length());

            String maGV = rs.getString("MAGV");
            String hoTen = rs.getString("HOTEN");
            Integer gioiTinh = rs.getInt("GIOITINH");
            Date ngaySinh = rs.getDate("NGAYSINH");
            String email = rs.getString("EMAIL");
            String sdt = rs.getString("SDT");
            String diaChi = rs.getString("DIACHI");
            Date ngayVaoLam = rs.getDate("NGAYVAOLAM");

            GiangVien giangVien = new GiangVien();
            giangVien.setAnh(anh);
            giangVien.setMaGV(maGV);
            giangVien.setHoTen(hoTen);
            giangVien.setGioiTinh(gioiTinh);
            giangVien.setNgaySinh(ngaySinh);
            giangVien.setEmail(email);
            giangVien.setSoDienThoai(sdt);
            giangVien.setDiaChi(diaChi);
            giangVien.setNgayVaoLam(ngayVaoLam);

            lstGiangVien.add(giangVien);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstGiangVien;
    }

    //Lấy danh sách lịch học 7 ngày tới
    public List<Lich> getLichHoc7(String ma) throws Exception {
        List<Lich> lstLichHoc = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT TOP 7 L.NGAYHOC AS 'NGAYHOC', PH.MAPHONGHOC AS 'MAPHONGHOC',  PH.TENPHONGHOC AS 'TENPHONGHOC', MH.MAMONHOC AS 'MAMONHOC', MH.TENMONHOC AS 'TENMONHOC', LH.MALOPHOC AS 'MALOPHOC', LH.TENLOPHOC AS 'TENLOPHOC', GV.MAGV AS 'MAGV', GV.HOTEN AS 'TENGV', L.THOIGIAN AS 'THOIGIAN'\n"
                + "FROM dbo.LICH L JOIN dbo.MONHOC MH\n"
                + "ON MH.MAMONHOC = L.MAMONHOC JOIN dbo.LOPHOC LH\n"
                + "ON LH.MALOPHOC = L.MALOPHOC JOIN dbo.PHONGHOC PH\n"
                + "ON PH.MAPHONGHOC = L.MAPHONGHOC JOIN dbo.GIANGVIEN GV\n"
                + "ON GV.MAGV = L.MAGIANGVIEN\n"
                + "WHERE L.LOAILICH = N'Lịch học' AND L.MAGIANGVIEN = ?\n"
                + "GROUP BY L.NGAYHOC, PH.MAPHONGHOC,  PH.TENPHONGHOC, MH.MAMONHOC, MH.TENMONHOC, LH.MALOPHOC, LH.TENLOPHOC, GV.MAGV, GV.HOTEN, L.THOIGIAN";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Date ngayHoc = rs.getDate("NGAYHOC");
            String maPhongHoc = rs.getString("MAPHONGHOC");
            String tenPhongHoc = rs.getString("TENPHONGHOC");
            String maMonHoc = rs.getString("MAMONHOC");
            String tenMonHoc = rs.getString("TENMONHOC");
            String maLopHoc = rs.getString("MALOPHOC");
            String tenLopHoc = rs.getString("TENLOPHOC");
            String maGV = rs.getString("MAGV");
            String tenGV = rs.getString("TENGV");
            String thoiGian = rs.getString("THOIGIAN");

            PhongHoc phongHoc = new PhongHoc();
            phongHoc.setMaPhongHoc(maPhongHoc);
            phongHoc.setTenPhongHoc(tenPhongHoc);

            MonHoc monHoc = new MonHoc();
            monHoc.setMaMonHoc(maMonHoc);
            monHoc.setTenMonHoc(tenMonHoc);

            LopHoc lopHoc = new LopHoc();
            lopHoc.setMaLopHoc(maLopHoc);
            lopHoc.setTenLopHoc(tenLopHoc);

            GiangVien giangVien = new GiangVien();
            giangVien.setMaGV(maGV);
            giangVien.setHoTen(tenGV);

            Lich lich = new Lich();
            lich.setNgayHoc(ngayHoc);
            lich.setMaPhongHoc(phongHoc + "");
            lich.setMaMonHoc(monHoc + "");
            lich.setMaLopHoc(lopHoc + "");
            lich.setMaGiangVien(giangVien + "");
            lich.setThoiGian(thoiGian);

            lstLichHoc.add(lich);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstLichHoc;
    }

    // Lấy danh sách lịch học 14 ngày tới
    public List<Lich> getLichHoc14(String ma) throws Exception {
        List<Lich> lstLichHoc = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT TOP 14 L.NGAYHOC AS 'NGAYHOC', PH.MAPHONGHOC AS 'MAPHONGHOC',  PH.TENPHONGHOC AS 'TENPHONGHOC', MH.MAMONHOC AS 'MAMONHOC', MH.TENMONHOC AS 'TENMONHOC', LH.MALOPHOC AS 'MALOPHOC', LH.TENLOPHOC AS 'TENLOPHOC', GV.MAGV AS 'MAGV', GV.HOTEN AS 'TENGV', L.THOIGIAN AS 'THOIGIAN'\n"
                + "FROM dbo.LICH L JOIN dbo.MONHOC MH\n"
                + "ON MH.MAMONHOC = L.MAMONHOC JOIN dbo.LOPHOC LH\n"
                + "ON LH.MALOPHOC = L.MALOPHOC JOIN dbo.PHONGHOC PH\n"
                + "ON PH.MAPHONGHOC = L.MAPHONGHOC JOIN dbo.GIANGVIEN GV\n"
                + "ON GV.MAGV = L.MAGIANGVIEN\n"
                + "WHERE L.LOAILICH = N'Lịch học' AND L.MAGIANGVIEN = ?\n"
                + "GROUP BY L.NGAYHOC, PH.MAPHONGHOC,  PH.TENPHONGHOC, MH.MAMONHOC, MH.TENMONHOC, LH.MALOPHOC, LH.TENLOPHOC, GV.MAGV, GV.HOTEN, L.THOIGIAN";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Date ngayHoc = rs.getDate("NGAYHOC");
            String maPhongHoc = rs.getString("MAPHONGHOC");
            String tenPhongHoc = rs.getString("TENPHONGHOC");
            String maMonHoc = rs.getString("MAMONHOC");
            String tenMonHoc = rs.getString("TENMONHOC");
            String maLopHoc = rs.getString("MALOPHOC");
            String tenLopHoc = rs.getString("TENLOPHOC");
            String maGV = rs.getString("MAGV");
            String tenGV = rs.getString("TENGV");
            String thoiGian = rs.getString("THOIGIAN");

            PhongHoc phongHoc = new PhongHoc();
            phongHoc.setMaPhongHoc(maPhongHoc);
            phongHoc.setTenPhongHoc(tenPhongHoc);

            MonHoc monHoc = new MonHoc();
            monHoc.setMaMonHoc(maMonHoc);
            monHoc.setTenMonHoc(tenMonHoc);

            LopHoc lopHoc = new LopHoc();
            lopHoc.setMaLopHoc(maLopHoc);
            lopHoc.setTenLopHoc(tenLopHoc);

            GiangVien giangVien = new GiangVien();
            giangVien.setMaGV(maGV);
            giangVien.setHoTen(tenGV);

            Lich lich = new Lich();
            lich.setNgayHoc(ngayHoc);
            lich.setMaPhongHoc(phongHoc + "");
            lich.setMaMonHoc(monHoc + "");
            lich.setMaLopHoc(lopHoc + "");
            lich.setMaGiangVien(giangVien + "");
            lich.setThoiGian(thoiGian);

            lstLichHoc.add(lich);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstLichHoc;
    }

    // Lấy danh sách lịch học 30 ngày tới
    public List<Lich> getLichHoc30(String ma) throws Exception {
        List<Lich> lstLichHoc = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT TOP 30 L.NGAYHOC AS 'NGAYHOC', PH.MAPHONGHOC AS 'MAPHONGHOC',  PH.TENPHONGHOC AS 'TENPHONGHOC', MH.MAMONHOC AS 'MAMONHOC', MH.TENMONHOC AS 'TENMONHOC', LH.MALOPHOC AS 'MALOPHOC', LH.TENLOPHOC AS 'TENLOPHOC', GV.MAGV AS 'MAGV', GV.HOTEN AS 'TENGV', L.THOIGIAN AS 'THOIGIAN'\n"
                + "FROM dbo.LICH L JOIN dbo.MONHOC MH\n"
                + "ON MH.MAMONHOC = L.MAMONHOC JOIN dbo.LOPHOC LH\n"
                + "ON LH.MALOPHOC = L.MALOPHOC JOIN dbo.PHONGHOC PH\n"
                + "ON PH.MAPHONGHOC = L.MAPHONGHOC JOIN dbo.GIANGVIEN GV\n"
                + "ON GV.MAGV = L.MAGIANGVIEN\n"
                + "WHERE L.LOAILICH = N'Lịch học' AND L.MAGIANGVIEN = ?\n"
                + "GROUP BY L.NGAYHOC, PH.MAPHONGHOC,  PH.TENPHONGHOC, MH.MAMONHOC, MH.TENMONHOC, LH.MALOPHOC, LH.TENLOPHOC, GV.MAGV, GV.HOTEN, L.THOIGIAN";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Date ngayHoc = rs.getDate("NGAYHOC");
            String maPhongHoc = rs.getString("MAPHONGHOC");
            String tenPhongHoc = rs.getString("TENPHONGHOC");
            String maMonHoc = rs.getString("MAMONHOC");
            String tenMonHoc = rs.getString("TENMONHOC");
            String maLopHoc = rs.getString("MALOPHOC");
            String tenLopHoc = rs.getString("TENLOPHOC");
            String maGV = rs.getString("MAGV");
            String tenGV = rs.getString("TENGV");
            String thoiGian = rs.getString("THOIGIAN");

            PhongHoc phongHoc = new PhongHoc();
            phongHoc.setMaPhongHoc(maPhongHoc);
            phongHoc.setTenPhongHoc(tenPhongHoc);

            MonHoc monHoc = new MonHoc();
            monHoc.setMaMonHoc(maMonHoc);
            monHoc.setTenMonHoc(tenMonHoc);

            LopHoc lopHoc = new LopHoc();
            lopHoc.setMaLopHoc(maLopHoc);
            lopHoc.setTenLopHoc(tenLopHoc);

            GiangVien giangVien = new GiangVien();
            giangVien.setMaGV(maGV);
            giangVien.setHoTen(tenGV);

            Lich lich = new Lich();
            lich.setNgayHoc(ngayHoc);
            lich.setMaPhongHoc(phongHoc + "");
            lich.setMaMonHoc(monHoc + "");
            lich.setMaLopHoc(lopHoc + "");
            lich.setMaGiangVien(giangVien + "");
            lich.setThoiGian(thoiGian);

            lstLichHoc.add(lich);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstLichHoc;
    }

    // Lấy danh sách chuyên ngành
    public List<ChuyenNganh> getAllChuyenNganh() throws Exception {
        List<ChuyenNganh> lstChuyenNganh = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT MACHUYENNGANH, TENCHUYENNGANH, COSO, THOIGIANDAOTAO FROM dbo.CHUYENNGANH";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String maChuyenNganh = rs.getString("MACHUYENNGANH");
            String tenChuyenNganh = rs.getString("TENCHUYENNGANH");
            String coSo = rs.getString("COSO");
            Integer thoiGianDaoTao = rs.getInt("THOIGIANDAOTAO");

            ChuyenNganh chuyenNganh = new ChuyenNganh();
            chuyenNganh.setMaChuyenNganh(maChuyenNganh);
            chuyenNganh.setTenChuyenNganh(tenChuyenNganh);
            chuyenNganh.setCoSo(coSo);
            chuyenNganh.setThoiGianDaoTao(thoiGianDaoTao);

            lstChuyenNganh.add(chuyenNganh);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstChuyenNganh;
    }

    //Lấy danh sách môn học
    public List<MonHoc> getAllMonHoc() throws Exception {
        List<MonHoc> lstMonHoc = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT MAMONHOC, TENMONHOC, SOTINCHI, NAM, MUA FROM dbo.MONHOC\n" +
                    "GROUP BY MAMONHOC, TENMONHOC, SOTINCHI, NAM, MUA";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String maMonHoc = rs.getString("MAMONHOC");
            String tenMonHoc = rs.getString("TENMONHOC");
            Integer soTinChi = rs.getInt("SOTINCHI");
            Integer nam = rs.getInt("NAM");
            String mua = rs.getString("MUA");

            MonHoc monHoc = new MonHoc();
            monHoc.setMaMonHoc(maMonHoc);
            monHoc.setTenMonHoc(tenMonHoc);
            monHoc.setSoTinChi(soTinChi);
            monHoc.setNam(nam);
            monHoc.setMua(mua);

            lstMonHoc.add(monHoc);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstMonHoc;
    }

    // Lấy danh sách lớp học
    public List<LopHoc> getAllLopHoc() throws Exception {
        List<LopHoc> lstLopHoc = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT MALOPHOC, TENLOPHOC, NAM, MUA FROM dbo.LOPHOC";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String maLopHoc = rs.getString("MALOPHOC");
            String tenLopHoc = rs.getString("TENLOPHOC");
            Integer nam = rs.getInt("NAM");
            String mua = rs.getString("MUA");

            LopHoc lopHoc = new LopHoc();
            lopHoc.setMaLopHoc(maLopHoc);
            lopHoc.setTenLopHoc(tenLopHoc);
            lopHoc.setNam(nam);
            lopHoc.setMua(mua);

            lstLopHoc.add(lopHoc);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstLopHoc;
    }

    // Lấy danh sách điểm thành phần
    public List<DiemThanhPhan> getDiemThanhPhan() throws Exception {
        List<DiemThanhPhan> lstDiem = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT MASV, LAB1, LAB2, LAB3, LAB4, ASSIGNMENT, DIEMTHI FROM dbo.DIEMTHANHPHAN";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String maSV = rs.getString("MASV");
            Double lab1 = rs.getDouble("LAB1");
            Double lab2 = rs.getDouble("LAB2");
            Double lab3 = rs.getDouble("LAB3");
            Double lab4 = rs.getDouble("LAB4");
            Double assignment = rs.getDouble("ASSIGNMENT");
            Double diemThi = rs.getDouble("DIEMTHI");

            DiemThanhPhan diem = new DiemThanhPhan();
            diem.setMaSV(maSV);
            diem.setLab1(lab1);
            diem.setLab2(lab2);
            diem.setLab3(lab3);
            diem.setLab4(lab4);
            diem.setAssignment(assignment);
            diem.setDiemThi(diemThi);

            lstDiem.add(diem);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstDiem;
    }

    //Lấy danh sách chuyên ngành theo năm
    public List<ChuyenNganh> getChuyenNganhTheoNam(int namCN) throws Exception {
        List<ChuyenNganh> lstChuyenNganh = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT CN.MACHUYENNGANH AS 'MACHUYENNGANH', CN.TENCHUYENNGANH AS 'TENCHUYENNGANH' FROM dbo.CHUYENNGANH CN JOIN dbo.MONHOC MH\n"
                + "ON  MH.MACHUYENNGANH = CN.MACHUYENNGANH\n"
                + "WHERE MH.NAM = ?\n"
                + "GROUP BY CN.MACHUYENNGANH, CN.TENCHUYENNGANH";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, namCN);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String maChuyenNganh = rs.getString("MACHUYENNGANH");
            String tenChuyenNganh = rs.getString("TENCHUYENNGANH");

            ChuyenNganh chuyenNganh = new ChuyenNganh();
            chuyenNganh.setMaChuyenNganh(maChuyenNganh);
            chuyenNganh.setTenChuyenNganh(tenChuyenNganh);

            lstChuyenNganh.add(chuyenNganh);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstChuyenNganh;
    }

    //Lấy danh sách chuyên ngành theo mùa
    public List<ChuyenNganh> getChuyenNganhTheoMua(String mua) throws Exception {
        List<ChuyenNganh> lstChuyenNganh = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT CN.MACHUYENNGANH AS 'MACHUYENNGANH', CN.TENCHUYENNGANH AS 'TENCHUYENNGANH' FROM dbo.CHUYENNGANH CN JOIN dbo.MONHOC MH\n"
                + "ON MH.MACHUYENNGANH = CN.MACHUYENNGANH\n"
                + "WHERE MH.MUA = ?\n"
                + "GROUP BY CN.MACHUYENNGANH, CN.TENCHUYENNGANH";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, mua);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String maChuyenNganh = rs.getString("MACHUYENNGANH");
            String tenChuyenNganh = rs.getString("TENCHUYENNGANH");

            ChuyenNganh chuyenNganh = new ChuyenNganh();
            chuyenNganh.setMaChuyenNganh(maChuyenNganh);
            chuyenNganh.setTenChuyenNganh(tenChuyenNganh);

            lstChuyenNganh.add(chuyenNganh);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstChuyenNganh;
    }

    //Lấy danh sách môn học theo chuyên ngành
    public List<MonHoc> getMonHocTheoChuyenNganh(String maCN) throws Exception {
        List<MonHoc> lstMonHoc = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT MH.MAMONHOC AS 'MAMONHOC', MH.TENMONHOC AS 'TENMONHOC' FROM dbo.MONHOC MH JOIN dbo.CHUYENNGANH CN\n"
                + "ON CN.MACHUYENNGANH = MH.MACHUYENNGANH\n"
                + "WHERE CN.MACHUYENNGANH = ?\n"
                + "GROUP BY MH.MAMONHOC, MH.TENMONHOC";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maCN);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String maMonHoc = rs.getString("MAMONHOC");
            String tenMonHoc = rs.getString("TENMONHOC");

            MonHoc monHoc = new MonHoc();
            monHoc.setMaMonHoc(maMonHoc);
            monHoc.setTenMonHoc(tenMonHoc);

            lstMonHoc.add(monHoc);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstMonHoc;
    }

    //Lấy danh sách lớp học theo môn học
    public List<LopHoc> getLopHocTheoMonHoc(String maMH) throws Exception {
        List<LopHoc> lstLopHoc = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT LH.MALOPHOC AS 'MALOPHOC', LH.TENLOPHOC AS 'TENLOPHOC' FROM dbo.LOPHOC LH JOIN dbo.MONHOC MH\n"
                + "ON MH.MALOPHOC = LH.MALOPHOC\n"
                + "WHERE MH.MAMONHOC = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maMH);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String maLopHoc = rs.getString("MALOPHOC");
            String tenLopHoc = rs.getString("TENLOPHOC");

            LopHoc lopHoc = new LopHoc();
            lopHoc.setMaLopHoc(maLopHoc);
            lopHoc.setTenLopHoc(tenLopHoc);

            lstLopHoc.add(lopHoc);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstLopHoc;
    }

    //Tìm sinh viên để nhập điểm
    public List<DiemThanhPhan> getSinhVienDeNhapDiem(String maSv, String maCn, String maMh, String maLh) throws Exception {
        List<DiemThanhPhan> lstDiem = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT MASV, MACHUYENNGANH, MAMONHOC, MALOPHOC, NAM, MUA, LAB1, LAB2, LAB3, LAB4, ASSIGNMENT, DIEMTHI FROM dbo.DIEMTHANHPHAN \n" +
                    "WHERE MASV = ? AND MACHUYENNGANH = ? AND MAMONHOC = ? AND MALOPHOC = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maSv);
        ps.setString(2, maCn);
        ps.setString(3, maMh);
        ps.setString(4, maLh);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String maSV = rs.getString("MASV");
            String maCN = rs.getString("MACHUYENNGANH");
            String maMH = rs.getString("MAMONHOC");
            String maLH = rs.getString("MALOPHOC");
            Integer nam = rs.getInt("NAM");
            String mua = rs.getString("MUA");
            Double lab1 = rs.getDouble("LAB1");
            Double lab2 = rs.getDouble("LAB2");
            Double lab3 = rs.getDouble("LAB3");
            Double lab4 = rs.getDouble("LAB4");
            Double assignment = rs.getDouble("ASSIGNMENT");
            Double diemThi = rs.getDouble("DIEMTHI");

            DiemThanhPhan diem = new DiemThanhPhan();
            diem.setMaSV(maSV);
            diem.setMaNganhHoc(maCN);
            diem.setMaMonHoc(maMH);
            diem.setMaLopHoc(maLH);
            diem.setNam(nam);
            diem.setMua(mua);
            diem.setLab1(lab1);
            diem.setLab2(lab2);
            diem.setLab3(lab3);
            diem.setLab4(lab4);
            diem.setAssignment(assignment);
            diem.setDiemThi(diemThi);

            lstDiem.add(diem);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstDiem;
    }

    public List<DiemThanhPhan> getDiemTheoMaLH_MaGV(String maMh, String maLh, String maGV) throws Exception {
        List<DiemThanhPhan> lstDiem = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT D.MASV AS 'MASV', D.MACHUYENNGANH AS 'MACHUYENNGANH', D.MAMONHOC AS 'MAMONHOC', D.MALOPHOC AS 'MALOPHOC', D.NAM AS 'NAM', D.MUA AS 'MUA' , D.LAB1 AS 'LAB1', D.LAB2 AS 'LAB2', D.LAB3 AS 'LAB3', D.LAB4 AS 'LAB4', D.ASSIGNMENT AS 'ASSIGNMENT', D.DIEMTHI AS 'DIEMTHI' FROM dbo.DIEMTHANHPHAN D JOIN dbo.LOPHOC LH\n" +
                    "ON LH.MALOPHOC = D.MALOPHOC JOIN dbo.LICH L\n" +
                    "ON L.MALOPHOC = LH.MALOPHOC\n" +
                    "WHERE D.MAMONHOC = ? AND LH.MALOPHOC = ? AND L.MAGIANGVIEN = ?\n" +
                    "GROUP BY D.MASV, D.MACHUYENNGANH, D.MAMONHOC, D.MALOPHOC, D.NAM, D.MUA, D.LAB1, D.LAB2, D.LAB3, D.LAB4, D.ASSIGNMENT, D.DIEMTHI";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maMh);
        ps.setString(2, maLh);
        ps.setString(3, maGV);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String maSV = rs.getString("MASV");
            String maCN = rs.getString("MACHUYENNGANH");
            String maMH = rs.getString("MAMONHOC");
            String maLH = rs.getString("MALOPHOC");
            Integer nam = rs.getInt("NAM");
            String mua = rs.getString("MUA");
            Double lab1 = rs.getDouble("LAB1");
            Double lab2 = rs.getDouble("LAB2");
            Double lab3 = rs.getDouble("LAB3");
            Double lab4 = rs.getDouble("LAB4");
            Double assignment = rs.getDouble("ASSIGNMENT");
            Double diemThi = rs.getDouble("DIEMTHI");

            DiemThanhPhan diem = new DiemThanhPhan();
            diem.setMaSV(maSV);
            diem.setMaNganhHoc(maCN);
            diem.setMaMonHoc(maMH);
            diem.setMaLopHoc(maLH);
            diem.setNam(nam);
            diem.setMua(mua);
            diem.setLab1(lab1);
            diem.setLab2(lab2);
            diem.setLab3(lab3);
            diem.setLab4(lab4);
            diem.setAssignment(assignment);
            diem.setDiemThi(diemThi);

            lstDiem.add(diem);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstDiem;
    }
    
    public Integer nhapDiem(DiemThanhPhan diem) throws Exception{
        Integer ketQua = -1;
        Connection connection = DBConnectorGV.getConnection();
        String sql = "UPDATE DIEMTHANHPHAN SET LAB1 = ?, LAB2 = ?, LAB3 = ?, LAB4 = ?, ASSIGNMENT = ?, DIEMTHI = ? WHERE MASV = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setDouble(1, diem.getLab1());
        ps.setDouble(2, diem.getLab2());
        ps.setDouble(3, diem.getLab3());
        ps.setDouble(4, diem.getLab4());
        ps.setDouble(5, diem.getAssignment());
        ps.setDouble(6, diem.getDiemThi());
        ps.setString(7, diem.getMaSV());
        
        ketQua = ps.executeUpdate();
        
        ps.close();
        connection.close();
        return ketQua;
    }
    
    //Lấy ra mã lớp học theo tên lớp học
    public MonHoc getMaMHByTenMH(String tenMH) throws Exception{
        MonHoc monHoc = new MonHoc();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT MAMONHOC FROM dbo.MONHOC WHERE TENMONHOC = ?\n" +
                    "GROUP BY MAMONHOC";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, tenMH);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String ma = rs.getString("MAMONHOC");
            monHoc.setMaMonHoc(ma);
            System.out.println("Get Mã môn học: "+ monHoc);
        }
        return monHoc;
    }
    
    public Integer GuiBaoCao(BaoCao baoCao) throws Exception{
        Integer ketQua = -1;
        Connection connection = DBConnectorGV.getConnection();
        String sql = "INSERT INTO BAOCAO (TIEUDE, NOIDUNG, MAMONHOC, MALOPHOC, MAGV) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, baoCao.getTieuDe());
        ps.setString(2, baoCao.getNoiDung());
        ps.setString(3, baoCao.getMaMonHoc());
        ps.setString(4, baoCao.getMaLopHoc());
        ps.setString(5, baoCao.getMaGV());
        
        ketQua = ps.executeUpdate();
        ps.close();
        connection.close();
        return ketQua;
    }
    
    public List<BaoCao> getLichSuBaoCaoTheoMaGV(String maGV) throws Exception{
        List<BaoCao> lstBaoCao = new ArrayList<>();
        Connection connection = DBConnectorGV.getConnection();
        String sql = "SELECT BC.MAGV AS 'MAGV', MH.MAMONHOC AS 'MAMONHOC', MH.TENMONHOC AS 'TENMONHOC', LH.MALOPHOC AS 'MALOPHOC', LH.TENLOPHOC AS 'TENLOPHOC', TIEUDE AS 'TIEUDE', NOIDUNG AS 'NOIDUNG' FROM dbo.BAOCAO BC JOIN dbo.MONHOC MH\n" +
                    "ON MH.MAMONHOC = BC.MAMONHOC JOIN dbo.LOPHOC LH\n" +
                    "ON LH.MALOPHOC = MH.MALOPHOC\n" +
                    "WHERE BC.MAGV = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maGV);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String maGv = rs.getString("MAGV");
            String maMH = rs.getString("MAMONHOC");
            String tenMH = rs.getString("TENMONHOC");
            String maLH = rs.getString("MALOPHOC");
            String tenLH = rs.getString("TENLOPHOC");
            String tieuDe = rs.getString("TIEUDE");
            String noiDung = rs.getString("NOIDUNG");
            
            MonHoc monHoc = new MonHoc();
            monHoc.setMaMonHoc(maMH);
            monHoc.setTenMonHoc(tenMH);
            LopHoc lopHoc = new LopHoc();
            lopHoc.setMaLopHoc(maLH);
            lopHoc.setTenLopHoc(tenLH);
            
            BaoCao baoCao = new BaoCao();
            baoCao.setMaGV(maGv);
            baoCao.setMaMonHoc(monHoc+"");
            baoCao.setMaLopHoc(lopHoc+"");
            baoCao.setTieuDe(tieuDe);
            baoCao.setNoiDung(noiDung);
            
            lstBaoCao.add(baoCao);
        }
        rs.close();
        ps.close();
        connection.close();
        return lstBaoCao;
    }
}
