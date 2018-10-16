package fr.eseo.dis.lemerlal.somanager.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.lemerlal.somanager.R;
import fr.eseo.dis.lemerlal.somanager.data.Role;
import fr.eseo.dis.lemerlal.somanager.data.Students;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder>{

    private final Context context;

    private List<Students> students;

    public StudentsAdapter(Context context){
        this.context = context;
        students = new ArrayList<>();
    }
    @Override
    public int getItemCount() {
        return students.size();
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.roles_card_layout, parent, false);
        return new StudentsViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsAdapter.StudentsViewHolder holder, int position) {
        Students studentsRole = students.get(position);
        holder.studentsName.setText(studentsRole.getForename()+" "+studentsRole.getSurename());
    }

    public void setRoles(List<Students> roles){
        this.students = roles;
    }

    class StudentsViewHolder extends RecyclerView.ViewHolder {

        private final TextView studentsName;

        public StudentsViewHolder(View view){
            super(view);
            studentsName = view.findViewById(R.id.tv_role_students);
        }
    }
}
