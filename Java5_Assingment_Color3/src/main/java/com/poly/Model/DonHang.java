package com.poly.Model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "don_hang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "don_hang_id", nullable = false)
    public int don_hang_id;

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id", nullable = false)
    public NguoiDung nguoiDung;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_dat_hang")
    Date ngay_dat_hang = new Date();
    
    @Column(name = "tong_gia_tri", nullable = false)
    public double tongGiaTri;
    
    @Column(name = "trang_thai")
    public boolean trangThai;
    
    @OneToMany(mappedBy = "donHang")
    public List<DonHangDichVu> donHangDichVuList;

    @OneToOne(mappedBy = "donHang")
    public PhuongThucThanhToan phuongThucThanhToan;

    @OneToMany(mappedBy = "donHang")
    public List<ChiTietDonHang> chiTietDonHangList;
}
