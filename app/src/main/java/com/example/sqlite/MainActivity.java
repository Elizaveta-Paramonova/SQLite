package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, phone, dateOfBirth;
    Button insert, select, update, delete;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.txtNumber);
        dateOfBirth = findViewById(R.id.txtDate);
        insert = findViewById(R.id.btnInsert);
        select = findViewById(R.id.btnSelect);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        databaseHelper = new DatabaseHelper(this);

        insert.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.insert(name.getText().toString(), phone.getText().toString(), dateOfBirth.getText().toString());
            if (checkInsertData)
                Toast.makeText(getApplicationContext(), "Данные успешно добавлены", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
        });

        select.setOnClickListener(view -> {
            Cursor res = databaseHelper.getdata();
            //Проверка на наличие данных
            if (res.getCount() == 0){
                Toast.makeText(MainActivity.this, "Нет данных", Toast.LENGTH_LONG).show();
                return;
            }
            //Цикл для перебора и объединения данных
            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()) {
                buffer.append("Имя: ").append(res.getString(0)).append("\n");
                buffer.append("Тел. номер: ").append(res.getString(1)).append("\n");
                buffer.append("Дата рождения: ").append(res.getString(2)).append("\n\n");
            }
            //Диалоговое окно
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Данные пользователей");
            builder.setMessage(buffer.toString());
            builder.show();
        });

        delete.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.Delete(name.getText().toString());
            if (checkInsertData)
                Toast.makeText(getApplicationContext(), "Данные успешно удалены", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
        });

        update.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.Update(name.getText().toString(), phone.getText().toString(), dateOfBirth.getText().toString());
            if (checkInsertData)
                Toast.makeText(getApplicationContext(), "Данные успешно обновлены", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
        });
    }
}