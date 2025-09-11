package com.example.listycity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    EditText inputCity;
    Button btnAdd, btnDelete, btnConfirm;
    View confirmRow;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    int selectedIndex = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cityList = findViewById(R.id.city_list);
        inputCity = findViewById(R.id.input_city);
        btnAdd = findViewById(R.id.add_btn);
        btnDelete = findViewById(R.id.delete_btn);
        btnConfirm = findViewById(R.id.btn_confirm);
        confirmRow = findViewById(R.id.confirm_row);

        confirmRow.setVisibility(View.GONE);
        String []cities = {"Edmonton", "Montreal"};

        dataList = new ArrayList<>(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content,R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
                inputCity.setText(dataList.get(position));
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputCity.requestFocus();
                inputCity.setHint("Type a city name, then press CONFIRM");
                selectedIndex = -1;
                confirmRow.setVisibility(View.VISIBLE);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputCity.getText().toString().trim();
                if (name.isEmpty()){
                    Toast.makeText(MainActivity.this,"Enter a City First", Toast.LENGTH_SHORT).show();
                    return;
                }
                dataList.add(name);
                cityAdapter.notifyDataSetChanged();
                inputCity.setText("");
                inputCity.setHint("");
                confirmRow.setVisibility(View.GONE);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedIndex >= 0 && selectedIndex < dataList.size()){
                    String removed = dataList.remove(selectedIndex);
                    cityAdapter.notifyDataSetChanged();
                    inputCity.setText((""));
                    selectedIndex = -1;
                    Toast.makeText(MainActivity.this,"Deleted:"+removed, Toast.LENGTH_SHORT).show();
                }
            else{
                Toast.makeText(MainActivity.this, "Tap a city first to select it", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
