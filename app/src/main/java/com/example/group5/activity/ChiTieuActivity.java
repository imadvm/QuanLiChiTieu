package com.example.group5.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group5.adapter.ChiTieuAdapter;
import com.example.group5.model.ChiTieu;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.example.group5.R;
import com.example.group5.adapter.ViTienAdapter;
import com.example.group5.database.AppViewModel;
import com.example.group5.fragment.ThemViTienFragment;
import com.example.group5.model.ViTien;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class ChiTieuActivity extends AppCompatActivity {
    private MaterialToolbar tbSetting;
    private RecyclerView rvChiTieu;
    private ChiTieuAdapter chiTieuAdapter;
    private AppViewModel appViewModel;
    private LiveData<List<ChiTieu>> listChiTieu;
    private int soLuongChiTieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tieu);

        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        //Toolbar
        tbSetting = findViewById(R.id.tbChiTieu);
        tbSetting.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tbSetting.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(getApplicationContext(), com.example.group5.activity.ThemChiTieuActivity.class));
                return true;
            }
        });

        //Đổ dữ liệu ví tiền vào RecyclerView
        rvChiTieu = findViewById(R.id.rvChiTieu);
        rvChiTieu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        chiTieuAdapter = new ChiTieuAdapter(this);
        rvChiTieu.setAdapter(chiTieuAdapter);
        listChiTieu = appViewModel.tatCaChiTieu();
        listChiTieu.observe(this, new Observer<List<ChiTieu>>() {
            @Override
            public void onChanged(List<ChiTieu> listChiTieu) {
                chiTieuAdapter.submitList(listChiTieu);
                soLuongChiTieu = listChiTieu.size();
            }
        });
        chiTieuAdapter.setOnItemClickListener(new ChiTieuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ChiTieu chiTieu) {
                int chiTieuID = chiTieu.getId();
                Intent intent = new Intent(ChiTieuActivity.this, SuaChiTieuActivity.class);
                intent.putExtra("chiTieuID", chiTieuID);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the list when the activity is resumed
        listChiTieu = appViewModel.tatCaChiTieu();
        listChiTieu.observe(this, new Observer<List<ChiTieu>>() {
            @Override
            public void onChanged(List<ChiTieu> listChiTieu) {
                chiTieuAdapter.submitList(listChiTieu);
                soLuongChiTieu = listChiTieu.size();
            }
        });
    }
}