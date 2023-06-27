/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import model.Lich;
import model.LichFull;
import ultilities.DBConnection;

/**
 *
 * @author 84374
 */
public class DAO_LichFull {

    final String INSERT_SQL = "INSERT INTO [dbo].[LICH] VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[LICH] [MAPHONGHOC]=?,[MAGIANGVIEN]=?,[MACHUYENNGANH]=?,[NAM]=?,[MUA]=?,[NGAYHOC]=?,[THOIGIAN]=?,[LOAILICH]=? WHERE [MASV] =?,[MAMONHOC]=?,[MALOPHOC]=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[LICH] WHERE [MASV] = ?";
    final String SELECT_BY_SQLSV = "SELECT * FROM [dbo].[LICH] WHERE [MASV] = ?";
    final String SELECT_BY_SQLGV = "SELECT * FROM [dbo].[LICH] WHERE [MAGV] = ?";
    final String SELECT_BY_SQLMH = "SELECT * FROM [dbo].[LICH] WHERE [MAMONHOC] = ?";
    final String SELECT_BY_SQLLH = "SELECT * FROM [dbo].[LICH] WHERE [MALOPHOC] = ?";
    final String SELECT_BY_SQLLHMH = "SELECT * FROM [dbo].[LICH] WHERE [MALOPHOC] = ? AND [MAMONHOC]=?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[LICH]";
    final String SELECT_SPLICH = "SELECT LICH.MASV,sv.HOTEN AS HOTENSV,LICH.MAMONHOC,mh.TENMONHOC,LICH.MALOPHOC,MAPHONGHOC,MAGIANGVIEN,gv.HOTEN AS HOTENGV,LICH.MACHUYENNGANH,LICH.NAM,LICH.MUA,NGAYHOC,THOIGIAN,LOAILICH FROM dbo.LICH \n"
            + "JOIN dbo.SINHVIEN sv\n"
            + "ON sv.MASV = LICH.MASV\n"
            + "JOIN dbo.GIANGVIEN gv\n"
            + "ON gv.MAGV = LICH.MAGIANGVIEN\n"
            + "JOIN  dbo.MONHOC mh\n"
            + "ON mh.MAMONHOC = LICH.MAMONHOC";
    final String SELECT_BY_MULIPLECONDITION = "DECLARE @masv AS VARCHAR(12) = ?, @hotensv AS nvarchar(30)=?, @mamonhoc as varchar(10) =?,@malophoc as varchar(10)=?,@maphonghoc as varchar(10) =?, @magiangvien varchar(12)=?,@hotengv nvarchar(20)=?,@tenmonhoc varchar(30)=?,@ngayhoc Date = ?,@thoigian varchar(30)=?,@loailich nvarchar(30) = ? "
            + "SELECT LICH.MASV,sv.HOTEN AS HOTENSV,LICH.MAMONHOC,mh.TENMONHOC,LICH.MALOPHOC,MAPHONGHOC,MAGIANGVIEN,gv.HOTEN AS HOTENGV,LICH.MACHUYENNGANH,LICH.NAM,LICH.MUA,NGAYHOC,THOIGIAN,LOAILICH FROM dbo.LICH \n"
            + "             JOIN dbo.SINHVIEN sv\n"
            + "             ON sv.MASV = LICH.MASV\n"
            + "             JOIN dbo.GIANGVIEN gv\n"
            + "             ON gv.MAGV = LICH.MAGIANGVIEN\n"
            + "             JOIN  dbo.MONHOC mh\n"
            + "             ON mh.MAMONHOC = LICH.MAMONHOC\n"
            + "			 WHERE (@masv IS NULL OR LICH.MASV=@masv) AND (@hotensv IS NULL OR sv.HOTEN=@hotensv) AND (@mamonhoc IS NULL OR LICH.MAMONHOC=@mamonhoc) AND (@malophoc IS NULL OR LICH.MALOPHOC=@malophoc) AND (@maphonghoc IS NULL OR MAPHONGHOC=@maphonghoc) AND (@magiangvien IS NULL OR MAGIANGVIEN=@magiangvien) AND (@hotengv IS NULL OR gv.HOTEN=@hotengv) AND (@tenmonhoc IS NULL OR mh.TENMONHOC=@tenmonhoc) AND (@ngayhoc IS NULL OR NGAYHOC =@ngayhoc)AND (@thoigian IS NULL OR THOIGIAN =@thoigian) AND (@loailich IS NULL OR LOAILICH =@loailich)";

    public DAO_LichFull() {

    }

    public ArrayList<LichFull> findAll() {
        LichFull lich;
        ArrayList<LichFull> _lstLichFull;
        return getSelectSql(SELECT_SPLICH);
    }

    public LichFull findByIdSV(String id) {
        LichFull lich;
        lich = getSelectSql(SELECT_SPLICH, id).get(0);
        return lich;
    }

    public LichFull findByIdGV(String id) {
        LichFull lich;
        lich = getSelectSql(SELECT_SPLICH, id).get(0);
        return lich;
    }

    public LichFull findByIdMH(String id) {
        LichFull lich;
        lich = getSelectSql(SELECT_SPLICH, id).get(0);
        return lich;
    }

    public LichFull findByIdLH(String id) {
        LichFull lich;
        lich = getSelectSql(SELECT_SPLICH, id).get(0);
        return lich;
    }

    public LichFull findByIdMHLH(String idmh, String idlh) {
        LichFull lich;
        lich = getSelectSql(SELECT_SPLICH, idmh, idlh).get(0);
        return lich;
    }

    public ArrayList<LichFull> selectByMultipleCondition(String maSv, String hoTenSv, String maMonHoc, String maLopHoc, String maPhongHoc, String maGiangVien, String hoTenGV, String tenMonHoc, Date ngayHoc, String thoiGian, String loaiLich) {
        ArrayList<LichFull> lstLichFull = new ArrayList<>();
        System.out.println(maSv + hoTenSv + maMonHoc + maLopHoc + maPhongHoc + maGiangVien + hoTenGV + tenMonHoc + ngayHoc + thoiGian + "Lich Hoc");
        lstLichFull = getSqlNarrow(SELECT_BY_MULIPLECONDITION, maSv, hoTenSv, maMonHoc, maLopHoc, maPhongHoc, maGiangVien, hoTenGV, tenMonHoc, ngayHoc, thoiGian, loaiLich);
        System.out.println(lstLichFull);
        System.out.println(SELECT_BY_MULIPLECONDITION);
        return lstLichFull;
    }
    
   
    public Lich save(Lich lich) {
        DBConnection.ExcuteDungna(INSERT_SQL, lich.getMaSV(), lich.getMaMonHoc(), lich.getMaLopHoc(), lich.getMaPhongHoc(), lich.getMaGiangVien(), lich.getMaChuyenNganh(), lich.getNam(), lich.getMua(), lich.getNgayHoc(), lich.getThoiGian(), lich.getLoaiLich());
        return lich;
    }

    public String delete(String id) {
        DBConnection.ExcuteDungna(DELETE_SQL, id);
        return id;
    }

    public Lich update(Lich lh, String maSv, String maMonHoc, String maLopHoc) {

        DBConnection.ExcuteDungna(UPDATE_SQL, lh.getMaPhongHoc(), lh.getMaGiangVien(), lh.getMaChuyenNganh(), lh.getNam(), lh.getMua(), lh.getNgayHoc(), lh.getThoiGian(), lh.getLoaiLich(), lh.getMaSV(), lh.getMaMonHoc(), lh.getMaLopHoc());
        return lh;
    }

    private ArrayList<LichFull> getSelectSql(String sql, Object... args) {
        ArrayList<LichFull> _lstLichFull = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                _lstLichFull.add(new LichFull(rs.getString("MASV"), rs.getString("HOTENSV"), rs.getString("MAMONHOC"), rs.getString("TENMONHOC"), rs.getString("MALOPHOC"), rs.getString("MAPHONGHOC"), rs.getString("MAGIANGVIEN"), rs.getString("HOTENGV"), rs.getString("MACHUYENNGANH"), rs.getInt("NAM"), rs.getString("MUA"), rs.getDate("NGAYHOC"), rs.getString("THOIGIAN"), rs.getString("LOAILICH")));
            }
            return _lstLichFull;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private ArrayList<LichFull> getSqlNarrow(String sql, String maSv, String hoTenSv, String maMonHoc, String maLopHoc, String maPhongHoc, String maGiangVien, String hoTenGV, String tenMonHoc, Date ngayHoc, String thoiGian, String loaiLich) {
        ArrayList<LichFull> _lstLichFull = new ArrayList<>();
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ppstm = conn.prepareStatement(SELECT_BY_MULIPLECONDITION);
            if (!maSv.strip().equals("")) {
                ppstm.setString(1,maSv);
                
            } else {
                System.out.println("test maSv null");
                ppstm.setNull(1, Types.VARCHAR);
            }
            
            if (!hoTenSv.strip().equals("")) {
                ppstm.setString(2,hoTenSv);
            } else {
                System.out.println("test hotensv null");
                ppstm.setNull(2, Types.NVARCHAR);
            }
            
            if (maMonHoc!=null) {
                ppstm.setString(3,maMonHoc);
            } else {
                System.out.println("test maMonhoc null");
                ppstm.setNull(3, Types.VARCHAR);
            }
            
            if (maLopHoc!=null) {
                ppstm.setString(4,maLopHoc);
            } else {
                System.out.println("test maLopHoc null");
                ppstm.setNull(4, Types.VARCHAR);
            }
            
            if (maPhongHoc!=null) {
                ppstm.setString(5,maPhongHoc);
            } else {
                System.out.println("test maPhongHoc null");
                ppstm.setNull(5, Types.VARCHAR);
            }
            
            if (!maGiangVien.strip().equals("")) {
                ppstm.setString(6,maGiangVien);
            } else {
                System.out.println("test maGiangVien null");
                ppstm.setNull(6, Types.VARCHAR);
            }
            
            if (!hoTenGV.strip().equals("")) {
                ppstm.setString(7,hoTenGV);
            } else {
                System.out.println("test hoTenGV null");
                ppstm.setNull(7, Types.NVARCHAR);
            }
            
            if (!tenMonHoc.strip().equalsIgnoreCase("")) {
                ppstm.setString(8,tenMonHoc);
            } else {
                System.out.println("test tenMonHoc null");
                ppstm.setNull(8, Types.VARCHAR);
            }
            
            if (ngayHoc!=null) {
                ppstm.setDate(9,ngayHoc);
            } else {
                System.out.println("test ngayHoc null");
                ppstm.setNull(9, Types.DATE);
            }
            
            if (thoiGian!=null) {
                ppstm.setString(10,thoiGian);
            } else {
                System.out.println("test thoiGian null");
                ppstm.setNull(10, Types.VARCHAR);
            }
            
            
            if (loaiLich!=null) {
                ppstm.setString(11,loaiLich);
            } else {
                System.out.println("test loaiLich null");
                ppstm.setNull(11, Types.VARCHAR);
            }
            
            ResultSet rs =  ppstm.executeQuery();
            while (rs.next()) {
                System.out.println("test loop");
                _lstLichFull.add(new LichFull(rs.getString("MASV"), rs.getString("HOTENSV"), rs.getString("MAMONHOC"), rs.getString("TENMONHOC"), rs.getString("MALOPHOC"), rs.getString("MAPHONGHOC"), rs.getString("MAGIANGVIEN"), rs.getString("HOTENGV"), rs.getString("MACHUYENNGANH"), rs.getInt("NAM"), rs.getString("MUA"), rs.getDate("NGAYHOC"), rs.getString("THOIGIAN"), rs.getString("LOAILICH")));
            }
            return _lstLichFull;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
