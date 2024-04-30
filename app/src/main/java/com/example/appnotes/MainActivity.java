package com.example.appnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;

    public static final ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);

        if(notes.isEmpty()){
            notes.add(new Note("Парикмахер", "Сделать прическу","Понедельник", 2));
            notes.add(new Note("Баскетбол", "Игра со школьной командой","Вторник", 3));
            notes.add(new Note("Магазин", "Купить новые джинсы","Понедельник", 3));
            notes.add(new Note("Стоматолог", "Вылеычить зубы","Понедельник", 2));
            notes.add(new Note("Парикмахер", "Сделать прическу","Среда", 1));
            notes.add(new Note("Баскетбол", "Игра","Четверг", 3));
            notes.add(new Note("Магазин", "Купить хлэб","Пятница", 1));
        }

        NotesAdapter adapter = new NotesAdapter(notes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                Toast.makeText(MainActivity.this, "loooong clicked", Toast.LENGTH_SHORT).show();
                notes.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                notes.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);
    }

    public void onClickAddNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}