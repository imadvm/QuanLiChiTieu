package com.example.group5.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.group5.model.DanhMuc;

import java.util.List;

@Dao
public interface DanhMucDao {
    @Insert
    void themDanhMuc(DanhMuc... danhMuc);

    @Query("SELECT * FROM tb_category")
    List<DanhMuc> xuatDanhMuc();

    @Query("SELECT * FROM tb_category WHERE cat_id LIKE :uid")
    DanhMuc timDanhMucTheoID(int uid);

    @Delete
    void xoaDanhMuc(DanhMuc danhMuc);

    @Update
    void capNhatDanhMuc(DanhMuc danhMuc);

    @Insert
    void themNhieuDanhMuc(List<DanhMuc> danhMucList);

    @Query("SELECT * FROM tb_category")
    LiveData<List<DanhMuc>> xuatDanhMucLive();

    @Query("SELECT * FROM tb_category WHERE cat_type LIKE 1")
    LiveData<List<DanhMuc>> xuatDanhMucThu();

    @Query("SELECT * FROM tb_category WHERE cat_type LIKE 2")
    LiveData<List<DanhMuc>> xuatDanhMucChi();
}
