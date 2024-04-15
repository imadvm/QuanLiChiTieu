package com.example.group5.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.TextView;

import com.example.group5.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HuongDanActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huong_dan);

        TextView txtHuongDan = findViewById(R.id.textViewHuongDan);
        String textContent = readTextFileFromAssets();

        // Tạo một đối tượng SpannableStringBuilder từ chuỗi văn bản
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textContent);

        // Tìm và đặt kiểu chữ đậm và kích thước cho dòng đầu tiên
        setSpanForHeaderText(spannableStringBuilder);

        // Tìm và đặt kiểu chữ đậm và kích thước cho các dòng bắt đầu bằng số và dấu chấm
        setSpanForListItems(spannableStringBuilder);

        // Tìm và đặt kiểu chữ và kích thước cho các dòng còn lại
        setSpanForRegularText(spannableStringBuilder);

        // Gán văn bản đã định dạng lên TextView
        txtHuongDan.setText(spannableStringBuilder);


        Toolbar toolbar = findViewById(R.id.tbHuongDan);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    // Phương thức để tìm và đặt kiểu chữ đậm và kích thước cho dòng đầu tiên
    private void setSpanForHeaderText(SpannableStringBuilder spannableStringBuilder) {
        int endIndex = spannableStringBuilder.toString().indexOf("\n");
        if (endIndex == -1) {
            endIndex = spannableStringBuilder.length();
        }
        // Đặt kiểu chữ đậm
        spannableStringBuilder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Đặt kích thước
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(30, true), 0, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    // Phương thức để tìm và đặt kiểu chữ đậm và kích thước cho các dòng bắt đầu bằng số và dấu chấm
    private void setSpanForListItems(SpannableStringBuilder spannableStringBuilder) {
        Pattern pattern = Pattern.compile("^\\d+\\..*", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(spannableStringBuilder);
        while (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            // Đặt kiểu chữ đậm
            spannableStringBuilder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // Đặt kích thước
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(20, true), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    // Phương thức để tìm và đặt kiểu chữ và kích thước cho các dòng còn lại
    private void setSpanForRegularText(SpannableStringBuilder spannableStringBuilder) {
        Pattern pattern = Pattern.compile("^\\d+\\..*", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(spannableStringBuilder);
        while (matcher.find()) {
            int startIndex = matcher.end();
            int endIndex = spannableStringBuilder.toString().indexOf("\n", startIndex);
            if (endIndex == -1) {
                endIndex = spannableStringBuilder.length();
            }
            // Đặt kích thước
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(16, true), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    // Phương thức để đọc nội dung từ file readme.txt trong thư mục assets
    private String readTextFileFromAssets() {
        StringBuilder text = new StringBuilder();

        try {
            InputStream is = getAssets().open("Readme.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {
                text.append(line).append('\n');
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textViewHuongDan) {
            startActivity(new Intent(getApplicationContext(), HuongDanActivity.class));
        }
    }
}
