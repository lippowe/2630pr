package com.example.kachalkaapp.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kachalkaapp.EditNoteActivity;
import com.example.kachalkaapp.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    LayoutInflater inflater;
    List<Note> notes;
    DBManager dbManager;

    public void updateData(List<Note> notes) {
        this.notes = notes;
    }

    public NotesAdapter(List<Note> notes, Context context, DBManager dbManager){
        this.notes = notes;
        this.inflater = LayoutInflater.from(context);
        this.dbManager = dbManager;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.notes_list, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.date.setText("Дата: " + note.getDate());
        holder.action.setText("Действие: " + note.getAction());
        holder.count.setText("Подходы: " + note.getCountAction());
        holder.time.setText("Время: " + note.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext());
                builder.setTitle("Выберите действие");
                // Кнопка редактировать заметку
                builder.setPositiveButton("Редактировать", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent editIntent = new Intent(v.getContext(), EditNoteActivity.class);
                        editIntent.putExtra(Note.class.getSimpleName(), note);
                        v.getContext().startActivity(editIntent);
                    }
                });
                // Кнопка удалить заметку
                builder.setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dbManager.openDB();
                        dbManager.deleteNote(note);
                        dbManager.closeDB();
                    }
                });
                // Кнопка отмена
                builder.setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date, action, count, time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dateNote);
            action = itemView.findViewById(R.id.actionNote);
            count = itemView.findViewById(R.id.countActoinNote);
            time = itemView.findViewById(R.id.timeActionNote);
        }
    }
}
