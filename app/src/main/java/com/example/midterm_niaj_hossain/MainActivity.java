package com.example.midterm_niaj_hossain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber;
    Button btnGenerate, btnHistory;
    ListView listViewTable;

    public static ArrayList<Integer> historyList = new ArrayList<>();
    ArrayList<String> table = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = findViewById(R.id.editTextNumber);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnHistory = findViewById(R.id.btnHistory);
        listViewTable = findViewById(R.id.listViewTable);

        btnGenerate.setOnClickListener(v -> {
            String input = editTextNumber.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show();
                return;
            }

            int num = Integer.parseInt(input);
            generateTable(num);

            if (!historyList.contains(num)) {
                historyList.add(num);
            }
        });

        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

        listViewTable.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = table.get(position);

            new AlertDialog.Builder(this)
                    .setTitle("Confirm Deletion")
                    .setMessage("Delete this row?\n" + selectedItem)
                    .setPositiveButton("Yes", (dialog, which) -> {
                        table.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Deleted: " + selectedItem, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    private void generateTable(int num) {
        table.clear();
        for (int i = 1; i <= 10; i++) {
            table.add(num + " x " + i + " = " + (num * i));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, table);
        listViewTable.setAdapter(adapter);
    }
}
