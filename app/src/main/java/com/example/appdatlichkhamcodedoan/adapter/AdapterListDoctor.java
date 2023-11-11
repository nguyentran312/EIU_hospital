package com.example.appdatlichkhamcodedoan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatlichkhamcodedoan.ItemOrderDoctorActivity;
import com.example.appdatlichkhamcodedoan.R;
import com.example.appdatlichkhamcodedoan.adapter.ViewHolder.ListDoctorViewHolder;
import com.example.appdatlichkhamcodedoan.dao.AccountDAO;
import com.example.appdatlichkhamcodedoan.dao.ServicesDAO;
import com.example.appdatlichkhamcodedoan.dto.AccountDTO;
import com.example.appdatlichkhamcodedoan.dto.DoctorDTO;
import com.example.appdatlichkhamcodedoan.dto.ServicesDTO;

import java.util.ArrayList;

public class AdapterListDoctor extends RecyclerView.Adapter<ListDoctorViewHolder> {
    private ArrayList<DoctorDTO> listDtoDoctor = new ArrayList<>();
    private Context context;

    public AdapterListDoctor(ArrayList<DoctorDTO> listDtoDoctor, Context context) {
        this.listDtoDoctor = listDtoDoctor;
        this.context = context;
    }

    @NonNull
    @Override
    public ListDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemlistdoctor,parent,false);
        return new ListDoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDoctorViewHolder holder, int position) {
        DoctorDTO doctorDTO = listDtoDoctor.get(position);

        AccountDAO accountDAO = new AccountDAO(context);
        ServicesDAO servicesDAO = new ServicesDAO(context);

        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        holder.tvNameDoctor.setText(accountDTO.getFullName());

        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());

        holder.tvNameService.setText(servicesDTO.getServicesName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ItemOrderDoctorActivity.class);
                intent.putExtra("idDoctor",doctorDTO.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDtoDoctor.size();
    }
}
