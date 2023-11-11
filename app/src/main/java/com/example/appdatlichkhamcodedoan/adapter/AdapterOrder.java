package com.example.appdatlichkhamcodedoan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatlichkhamcodedoan.ConfirmActivity;
import com.example.appdatlichkhamcodedoan.OrderDoctorActivity;
import com.example.appdatlichkhamcodedoan.R;
import com.example.appdatlichkhamcodedoan.adapter.ViewHolder.OrderViewHolder;
import com.example.appdatlichkhamcodedoan.dao.AccountDAO;
import com.example.appdatlichkhamcodedoan.dao.DoctorDAO;
import com.example.appdatlichkhamcodedoan.dao.FileDAO;
import com.example.appdatlichkhamcodedoan.dao.OrderDoctorDAO;
import com.example.appdatlichkhamcodedoan.dao.RoomsDAO;
import com.example.appdatlichkhamcodedoan.dao.ServicesDAO;
import com.example.appdatlichkhamcodedoan.dto.AccountDTO;
import com.example.appdatlichkhamcodedoan.dto.DoctorDTO;
import com.example.appdatlichkhamcodedoan.dto.FileDTO;
import com.example.appdatlichkhamcodedoan.dto.OrderDoctorDTO;
import com.example.appdatlichkhamcodedoan.dto.RoomsDTO;
import com.example.appdatlichkhamcodedoan.dto.ServicesDTO;

import java.util.ArrayList;

public class AdapterOrder extends RecyclerView.Adapter<OrderViewHolder> {
    private ArrayList<OrderDoctorDTO> listOrderDoctor = new ArrayList<>();
    private Context context;
    private float sumPrice = 0;
    public ConfirmActivity confirmActivity;

//    public AdapterOrder(ArrayList<OrderDoctorDTO> listOrderDoctor, Context context) {
//        this.listOrderDoctor = listOrderDoctor;
//        this.context = context;
//    }


    public AdapterOrder(ArrayList<OrderDoctorDTO> listOrderDoctor, Context context, ConfirmActivity confirmActivity) {
        this.listOrderDoctor = listOrderDoctor;
        this.context = context;
        this.confirmActivity = confirmActivity;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderDoctorDTO orderDoctorDTO = listOrderDoctor.get(position);
        final int vitri = position;

        FileDAO fileDAO = new FileDAO(context);
        FileDTO fileDTO = fileDAO.getFileDToById(orderDoctorDTO.getFile_id());
        DoctorDAO doctorDAO  = new DoctorDAO(context);
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(orderDoctorDTO.getDoctor_id());

        AccountDAO accountDAO = new AccountDAO(context);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());

        holder.tvNameDoctor.setText(accountDTO.getFullName());

        ServicesDAO servicesDAO = new ServicesDAO(context);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        holder.tvNameService.setText(servicesDTO.getServicesName());

        RoomsDAO roomsDAO = new RoomsDAO(context);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        holder.tvNameRooms.setText(roomsDTO.getName());

        holder.tvStartDate.setText(orderDoctorDTO.getStart_date());
        holder.tvStartTime.setText(orderDoctorDTO.getStart_time());
        holder.tvPrice.setText(orderDoctorDTO.getTotal()+"đ");
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDoctorDAO orderDoctorDAO =new OrderDoctorDAO(context);
                int res = orderDoctorDAO.deleteRow(orderDoctorDTO);
                OrderDoctorActivity.listOrderDoctor.remove(vitri);
                for (int i = 0; i < listOrderDoctor.size(); i++) {
                    sumPrice += listOrderDoctor.get(i).getTotal();
                }
                confirmActivity.tvSumPrice.setText("Thành tiền : " + sumPrice + "đ");
                notifyDataSetChanged();
            }
        });
        holder.tvNamefile.setText(fileDTO.getFullname());
    }

    @Override
    public int getItemCount() {
        return listOrderDoctor.size();
    }
}
