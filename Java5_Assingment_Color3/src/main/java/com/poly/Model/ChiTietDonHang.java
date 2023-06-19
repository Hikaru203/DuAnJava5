package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "chi_tiet_don_hang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chi_tiet_don_hang_id")
    public int id;

    @ManyToOne
    @JoinColumn(name = "don_hang_id", nullable = false)
    public DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "san_pham_id", nullable = false)
    public SanPham sanPham;

    @Column(name = "so_luong")
    public int soLuong;

    @Column(name = "gia_ban")
    public double gia;
}

