package com.example.appdatlichkhamcodedoan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatlichkhamcodedoan.R;
import com.example.appdatlichkhamcodedoan.adapter.ViewHolder.ItemListDoctorStatisticalViewHolder;
import com.example.appdatlichkhamcodedoan.dao.AccountDAO;
import com.example.appdatlichkhamcodedoan.dao.DoctorDAO;
import com.example.appdatlichkhamcodedoan.dao.RoomsDAO;
import com.example.appdatlichkhamcodedoan.dao.ServicesDAO;
import com.example.appdatlichkhamcodedoan.dto.AccountDTO;
import com.example.appdatlichkhamcodedoan.dto.DoctorDTO;
import com.example.appdatlichkhamcodedoan.dto.RoomsDTO;
import com.example.appdatlichkhamcodedoan.dto.ServicesDTO;

import java.util.ArrayList;

public class AdapterListDoctorStatistical extends RecyclerView.Adapter<ItemListDoctorStatisticalViewHolder> {
    private ArrayList<DoctorDTO> list = new ArrayList<>();
    private Context context;

    public AdapterListDoctorStatistical(ArrayList<DoctorDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListDoctorStatisticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor_statistical,parent,false);
        return new ItemListDoctorStatisticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListDoctorStatisticalViewHolder holder, int position) {
        DoctorDTO doctorDTO1 = list.get(position);

        DoctorDAO doctorDAO = new DoctorDAO(context);
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(doctorDTO1.getId());
        AccountDAO accountDAO = new AccountDAO(context);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        holder.tvNameDoctor.setText(accountDTO.getFullName());

        ServicesDAO servicesDAO = new ServicesDAO(context);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        holder.tvNameService.setText("Chuyên khoa : "+servicesDTO.getServicesName());

        RoomsDAO roomsDAO = new RoomsDAO(context);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        holder.tvNameRoom.setText("Phòng làm việc: "+roomsDTO.getName());

        holder.tvSumPriceOrder.setText("Tổng doanh thu : "+doctorDTO1.getSumPrice()+"vnđ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
