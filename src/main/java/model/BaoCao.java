package model;

/**
 *
 * @author Hello
 */
public class BaoCao {
    private String maBaoCao;
    private String tieuDe;
    private String NoiDung;
    private String maMonHoc;
    private String maLopHoc;
    private String maSV;
    private String maGV;

    public BaoCao() {
    }

    public BaoCao(String maBaoCao, String tieuDe, String NoiDung, String maMonHoc, String maLopHoc, String maSV, String maGV) {
        this.maBaoCao = maBaoCao;
        this.tieuDe = tieuDe;
        this.NoiDung = NoiDung;
        this.maMonHoc = maMonHoc;
        this.maLopHoc = maLopHoc;
        this.maSV = maSV;
        this.maGV = maGV;
    }

    public String getMaBaoCao() {
        return maBaoCao;
    }

    public void setMaBaoCao(String maBaoCao) {
        this.maBaoCao = maBaoCao;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    @Override
    public String toString() {
        return "BaoCao{" + "maBaoCao=" + maBaoCao + ", tieuDe=" + tieuDe + ", NoiDung=" + NoiDung + ", maMonHoc=" + maMonHoc + ", maLopHoc=" + maLopHoc + ", maSV=" + maSV + ", maGV=" + maGV + '}';
    }
}
