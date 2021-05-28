package com.example.thithuth_18056521_hahuyhoang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Recycle_Adapter extends RecyclerView.Adapter<Recycle_Adapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Customer> list;

    public Recycle_Adapter(Context context, List<Customer> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNameItem.setText(list.get(position).getName());
        holder.txtMoneyItem.setText(String.valueOf(list.get(position).getMoney()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView txtNameItem;
        public final TextView txtMoneyItem;
        public final Button btnEdit;
        public final Button btnDelete;
        public final Recycle_Adapter adt;

        public ViewHolder(@NonNull View itemView, Recycle_Adapter adt) {
            super(itemView);
            this.txtNameItem = itemView.findViewById(R.id.txtNameItem);
            this.txtMoneyItem = itemView.findViewById(R.id.txtMoneyItem);
            this.btnEdit = itemView.findViewById(R.id.btnEdit);
            this.btnDelete = itemView.findViewById(R.id.btnDelete);
            this.adt = adt;

            this.btnDelete.setOnClickListener(this);
            this.btnEdit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnDelete:
                    Toast.makeText(context, "BtnDelete", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnEdit:
                    Toast.makeText(context, "btnedit", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
