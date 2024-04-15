package com.example.group5.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.group5.model.LoaiTienTe;

@Dao
public interface LoaiTienTeDao {

    @Update
    void capNhatLoaiTienTe(LoaiTienTe loaiTienTe);

    @Insert
    void themTienTe(LoaiTienTe loaiTienTe);

    @Query("SELECT * FROM tb_money_type WHERE money_id LIKE 1")
    LiveData<LoaiTienTe> xuatLoaiTienTe();

    @Query("SELECT * FROM tb_money_type WHERE money_id LIKE 1")
    LoaiTienTe loaiTienTe();
}
