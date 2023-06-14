package com.example.noteapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<NoteModel> noteModels;

    Adapter(Context context, List<NoteModel> noteModels) {
        this.inflater = LayoutInflater.from(context);
        this.noteModels = noteModels;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.note_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = noteModels.get(position).getNoteTitle();
        String date = noteModels.get(position).getNoteDate();
        String time = noteModels.get(position).getNoteTime();

        holder.noteTitle.setText(title);
        holder.noteDate.setText(date);
        holder.noteTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView noteTitle, noteDetails, noteDate, noteTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.nTitle);
            noteTime = itemView.findViewById(R.id.nTime);
            noteDate = itemView.findViewById(R.id.nDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
