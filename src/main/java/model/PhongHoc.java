package model;

/**
 *
 * @author Hello
 */
public class PhongHoc {
    private String maPhongHoc;
    private String tenPhongHoc;
    private String toaNha;

    public PhongHoc() {
    }

    public PhongHoc(String maPhongHoc, String tenPhongHoc, String toaNha) {
        this.maPhongHoc = maPhongHoc;
        this.tenPhongHoc = tenPhongHoc;
        this.toaNha = toaNha;
    }

    public String getMaPhongHoc() {
        return maPhongHoc;
    }

    public void setMaPhongHoc(String maPhongHoc) {
        this.maPhongHoc = maPhongHoc;
    }

    public String getTenPhongHoc() {
        return tenPhongHoc;
    }

    public void setTenPhongHoc(String tenPhongHoc) {
        this.tenPhongHoc = tenPhongHoc;
    }

    public String getToaNha() {
        return toaNha;
    }

    public void setToaNha(String toaNha) {
        this.toaNha = toaNha;
    }

    @Override
    public String toString() {
        return tenPhongHoc;
    }
    
    
}
