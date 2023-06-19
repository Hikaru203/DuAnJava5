package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "giohang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "gio_hang_id", nullable = false)
    public int gio_hang_id;

    @ManyToOne
    @JoinColumn(name = "nguoidung_id", nullable = false)
    public NguoiDung nguoiDung;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_tao")
    Date ngay_tao = new Date();

    @OneToMany(mappedBy = "gioHang")
    public List<GioHangSanPham> gioHangSanPhamList;
}
