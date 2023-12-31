package com.example.demo.repository;

import com.example.demo.model.ChitietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface IChiTietSanPhamRepository extends JpaRepository<ChitietSanPham,Integer> {

    @Query("select ctsp from ChitietSanPham ctsp order by ctsp.idTrangThai.id ")
    Page<ChitietSanPham> findAll(Pageable pageable);
    @Query("update ChitietSanPham set giaKhuyenMai =:giaSauKM")
    void updateAll(@Param("giaSauKM")BigDecimal giaSauKM);

    ChitietSanPham findChitietSanPhamById(Integer id);

    @Query("SELECT ctsp FROM ChitietSanPham ctsp WHERE ctsp.ten LIKE %:ten%")
    Page<ChitietSanPham> searchByTen(@Param("ten") String ten, Pageable pageable);

    @Query("SELECT ctsp FROM ChitietSanPham ctsp WHERE ctsp.ten LIKE %:ten% AND ctsp.giaBan BETWEEN :min AND :max")
    Page<ChitietSanPham> searchByTenAndGiaRange(@Param("ten") String ten, @Param("min") BigDecimal min, @Param("max") BigDecimal max, Pageable pageable);

    @Query("SELECT ctsp FROM ChitietSanPham ctsp JOIN ctsp.idLoai d WHERE ctsp.idLoai.id = :id")
    Page<ChitietSanPham> findByIdLoai(@Param("id") Integer idLoai, Pageable pageable);



}
