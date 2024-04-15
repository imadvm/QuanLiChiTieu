package com.example.group5.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.group5.model.ViTien;

import java.util.List;

@Dao
public interface ViTienDao {
    @Insert
    void themViTien(ViTien... viTien);

    @Query("SELECT * FROM tb_wallet")
    List<ViTien> xuatViTien();

    @Query("SELECT * FROM tb_wallet WHERE wallet_id LIKE :uid")
    ViTien timViTienTheoID(int uid);

    @Delete
    void xoaViTien(ViTien viTien);

    @Update
    void capNhatViTien(ViTien viTien);

    @Insert
    void themNhieuViTien(List<ViTien> viTienList);

    @Query("SELECT * FROM tb_wallet")
    LiveData<List<ViTien>> xuatViTienLive();
}
