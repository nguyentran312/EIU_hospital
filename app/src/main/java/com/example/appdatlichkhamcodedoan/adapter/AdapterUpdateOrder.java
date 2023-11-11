package com.example.appdatlichkhamcodedoan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatlichkhamcodedoan.R;
import com.example.appdatlichkhamcodedoan.UpdateOrderActivity;
import com.example.appdatlichkhamcodedoan.adapter.ViewHolder.UpdateOrderViewHolder;
import com.example.appdatlichkhamcodedoan.dao.AccountDAO;
import com.example.appdatlichkhamcodedoan.dao.DoctorDAO;
import com.example.appdatlichkhamcodedoan.dao.FileDAO;
import com.example.appdatlichkhamcodedoan.dao.OrderDAO;
import com.example.appdatlichkhamcodedoan.dao.OrderDoctorDAO;
import com.example.appdatlichkhamcodedoan.dao.RoomsDAO;
import com.example.appdatlichkhamcodedoan.dao.ServicesDAO;
import com.example.appdatlichkhamcodedoan.dto.AccountDTO;
import com.example.appdatlichkhamcodedoan.dto.DoctorDTO;
import com.example.appdatlichkhamcodedoan.dto.FileDTO;
import com.example.appdatlichkhamcodedoan.dto.OrderDTO;
import com.example.appdatlichkhamcodedoan.dto.OrderDetailDTO;
import com.example.appdatlichkhamcodedoan.dto.OrderDoctorDTO;
import com.example.appdatlichkhamcodedoan.dto.RoomsDTO;
import com.example.appdatlichkhamcodedoan.dto.ServicesDTO;

import java.util.ArrayList;

public class AdapterUpdateOrder extends RecyclerView.Adapter<UpdateOrderViewHolder> {
    private ArrayList<OrderDetailDTO> listUpdateOrder = new ArrayList<>();
    private Context context;

    public AdapterUpdateOrder(ArrayList<OrderDetailDTO> listUpdateOrder, Context context) {
        this.listUpdateOrder = listUpdateOrder;
        this.context = context;
    }

    @NonNull
    @Override
    public UpdateOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_update_order,parent,false);
        return new UpdateOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateOrderViewHolder holder, int position) {
        OrderDetailDTO orderDetailDTO = listUpdateOrder.get(position);
        final int index = position;
        holder.tvOrderId.setText("Mã đặt lịch: "+orderDetailDTO.getOrder_id());

        OrderDAO orderDAO = new OrderDAO(context);
        OrderDTO orderDTO = orderDAO.getOrderDTOById(orderDetailDTO.getOrder_id());
        holder.tvOrderDate.setText(orderDTO.getOrder_date());
        holder.tvOrderTime.setText(orderDTO.getOrder_time());

        OrderDoctorDAO orderDoctorDAO = new OrderDoctorDAO(context);
        OrderDoctorDTO orderDoctorDTO = orderDoctorDAO.getOrderDoctorDtoById(orderDetailDTO.getOrderDoctor_id());

        FileDAO fileDAO = new FileDAO(context);
        FileDTO fileDTO = fileDAO.getFileDToById(orderDoctorDTO.getFile_id());
        holder.tvNamefile.setText(fileDTO.getFullname());

        DoctorDAO doctorDAO = new DoctorDAO(context);
        DoctorDTO doctorDTO =doctorDAO.getDtoDoctorByIdDoctor(orderDoctorDTO.getDoctor_id());

        AccountDAO accountDAO = new AccountDAO(context);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        holder.tvNameDoctor.setText(accountDTO.getFullName());

        ServicesDAO servicesDAO = new ServicesDAO(context);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        holder.tvNameService.setText(servicesDTO.getServicesName());

        RoomsDAO roomsDAO = new RoomsDAO(context);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        holder.tvNameRoomsUpateOrder.setText(roomsDTO.getName());
        holder.tvStartDateUpdateOrder.setText(orderDoctorDTO.getStart_date());
        holder.tvStartTimeUpateOrder.setText(orderDoctorDTO.getStart_time());


        holder.tvPrice.setText(servicesDTO.getServicesPrice()+"vnđ");
        holder.tvStatusOrder.setText(orderDTO.getStatus());
        holder.btnUpdateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateOrderActivity.class);
                intent.putExtra("idOrderDetailDTO",orderDetailDTO.getOrder_id());
                intent.putExtra("index",index);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUpdateOrder.size();
    }
}
