package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "giohang_sanpham")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "giohang_sanpham_id")
    public int gioHangSanPhamId;

    @ManyToOne
    @JoinColumn(name = "giohang_id", nullable = false)
    public GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "sanpham_id", nullable = false)
    public SanPham sanPham;

    @Column(name = "so_luong", nullable = false)
    public int soLuong;
}

