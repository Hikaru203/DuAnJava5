package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "donhang_dichvu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonHangDichVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "donhang_dichvu_id", nullable = false)
    public int donhang_dichvu_id;

    @ManyToOne
    @JoinColumn(name = "donhang_id", nullable = false)
    public DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "dichvu_id", nullable = false)
    public DichVu dichVu;
}

