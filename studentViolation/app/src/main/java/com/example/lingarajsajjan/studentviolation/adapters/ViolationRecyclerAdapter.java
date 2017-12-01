package com.example.lingarajsajjan.studentviolation.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lingarajsajjan.studentviolation.R;
import com.example.lingarajsajjan.studentviolation.model.ViolationRegister;

import java.util.List;

public class ViolationRecyclerAdapter extends RecyclerView.Adapter<ViolationRecyclerAdapter.UserViewHolder> {

    private List<ViolationRegister> listUsers;

    public ViolationRecyclerAdapter(List<ViolationRegister> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewName.setText(listUsers.get(position).getStdId());
        holder.textViewEmail.setText(listUsers.get(position).getStdName());
        //holder.textViewPassword.setText(listUsers.get(position).getViolationDescription());
        holder.textViewViolationDate.setText(listUsers.get(position).getViolationDate());
        holder.textViewViolationType.setText(listUsers.get(position).getViolationDescription());
        holder.textViewViolationLocation.setText(listUsers.get(position).getViolationLocation());

    }

    @Override
    public int getItemCount() {
        Log.v(ViolationRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName,textViewViolationLocation,textViewViolationType,textViewViolationDate;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;
        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            //textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
            textViewViolationLocation=(AppCompatTextView)view.findViewById(R.id.textViewViolationLocation);
            textViewViolationType=(AppCompatTextView)view.findViewById(R.id.textViewViolationType);
            textViewViolationDate=(AppCompatTextView)view.findViewById(R.id.textViewViolationDate);

        }
    }


}
