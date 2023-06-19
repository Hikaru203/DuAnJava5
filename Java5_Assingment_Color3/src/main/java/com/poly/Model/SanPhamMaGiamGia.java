package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "sanpham_magiamgia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamMaGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "sanpham_magiamgia_id", nullable = false)
    public int sanpham_magiamgia_id;

    @ManyToOne
    @JoinColumn(name = "sanpham_id", nullable = false)
    public SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "magiamgia_id", nullable = false)
    public MaGiamGia maGiamGia;
}

