package com.examplefourthjuly.todolist;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText description ;
    Button button;
    ListView listView;
    ArrayAdapter<Task> adapterArray ;
    ArrayList<Task> itemList = new ArrayList<>();
    FileHelper FileHelper;
    private static final String FILE_NAME = "tasks.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        description=findViewById(R.id.editText);
        listView=findViewById(R.id.listView);
        FileHelper = new FileHelper();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });
            button.setOnClickListener(view -> {

                View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);
                EditText titleInput = dialogView.findViewById(R.id.taskTitle);
                EditText dueDateInput = dialogView.findViewById(R.id.taskDueDate);
                Spinner prioritySpinner = dialogView.findViewById(R.id.taskPriority);
                CheckBox taskComplete = dialogView.findViewById(R.id.taskCompleted);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogView);
                builder.setTitle("Add Task");

                ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                        MainActivity.this, R.array.priority_array, android.R.layout.simple_spinner_item);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                prioritySpinner.setAdapter(spinnerAdapter);

                builder.setPositiveButton("Add", (dialog, which) -> {
                    String title = titleInput.getText().toString();
                    String txtDescription = description.getText().toString();
                    String dueDate = dueDateInput.getText().toString();
                    String priority = prioritySpinner.getSelectedItem().toString();

                    if (!title.isEmpty()) {
                        Task task = new Task(title,txtDescription, dueDate, priority, false);
                        itemList.add(task);
                        adapterArray.notifyDataSetChanged();
                        saveTasks(itemList);
                        titleInput.setText("");
                        description.setText("");
//                        taskComplete.setChecked(task.isCompleted());
//                        taskComplete.setOnCheckedChangeListener((buttonView, isChecked) -> task.setCompleted(isChecked));
                        Boolean isCompleted = taskComplete.isChecked();
                    } else {
                        Toast.makeText(MainActivity.this, "Enter a task title", Toast.LENGTH_SHORT).show();
                    }

                    // Optional: save to file with Gson
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();

                dueDateInput.setOnClickListener(v1 -> {
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                            (view1, selectedYear, selectedMonth, selectedDay) -> {
                                String date = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                                dueDateInput.setText(date);
                            }, year, month, day);

                    dialog.show();
                });
                itemList = loadTasks();
                adapterArray = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
                listView.setAdapter(adapterArray);

            });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Delete");
                alert.setMessage("Do you want to delete this stuff from List?");
                alert.setCancelable(false);
                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        itemList.remove(position);
                        adapterArray.notifyDataSetChanged();
                        FileHelper.writeData(itemList, getApplicationContext());
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });
    }
    private void saveTasks(ArrayList<Task> itemList) {
        try {
            String json = new Gson().toJson(itemList);
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            String json = new String(buffer);
            Type type = new TypeToken<ArrayList<Task>>() {
            }.getType();
            taskList = new Gson().fromJson(json, type);
        } catch (Exception e) {
            // Ignore if file doesn't exist
        }
        return taskList;
    }



}