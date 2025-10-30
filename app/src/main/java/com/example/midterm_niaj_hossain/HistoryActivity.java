package com.example.midterm_niaj_hossain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity {

    ListView historyListView;
    ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = findViewById(R.id.historyListView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MainActivity.historyList);
        historyListView.setAdapter(adapter);

        if (MainActivity.historyList.isEmpty()) {
            Toast.makeText(this, "No history available", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.clearAll) {
            if (!MainActivity.historyList.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("Confirm")
                        .setMessage("Clear all history?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            MainActivity.historyList.clear();
                            adapter.notifyDataSetChanged();
                            Toast.makeText(this, "All rows cleared", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            } else {
                Toast.makeText(this, "History is already empty", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
