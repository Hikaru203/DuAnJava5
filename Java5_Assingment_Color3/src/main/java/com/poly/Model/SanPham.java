package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "sanpham")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "ten_san_pham", nullable = false)
    @NotBlank(message = "Tên sản phẩm không được trống")
    @Size(min = 1, max = 255, message = "Tên sản phẩm phải có từ 1 đến 255 kí tự")
    public String tenSanPham;

    @Column(name = "gia", nullable = false)
    @PositiveOrZero(message = "Giá sản phẩm phải là số dương hoặc bằng 0")
    @NotNull(message = "Giá không được để trỗng")
    public Double gia;

    @Column(name = "mo_ta")
    public String moTa;

    @Column(name = "hinh_anh")
    public String hinhAnh;
    
    @Column(name = "trang_thai")
    public boolean trangThai;
    
   

    @ManyToOne
    @JoinColumn(name = "nha_cung_cap_id", nullable = false)
    public NhaCungCap nhaCungCap;
    
    @Valid
    @OneToMany(mappedBy = "sanPham")
    public List<Kho> khoList;

    @OneToMany(mappedBy = "sanPham")
    public List<SanPhamMaGiamGia> sanPhamMaGiamGiaList;

    @OneToMany(mappedBy = "sanPham")
    public List<GioHangSanPham> gioHangSanPhamList;

    @OneToMany(mappedBy = "sanPham")
    public List<ChiTietDonHang> chiTietDonHangList;
}

