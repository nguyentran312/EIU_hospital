package com.example.appdatlichkhamcodedoan.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appdatlichkhamcodedoan.R;
import com.example.appdatlichkhamcodedoan.adapter.AdapterManagerAccount_Doctor;
import com.example.appdatlichkhamcodedoan.dao.AccountDAO;
import com.example.appdatlichkhamcodedoan.dto.AccountDTO;

import java.util.ArrayList;


public class Fragment_ManagerAccountDoctor extends Fragment {

    AccountDAO accountDAO;
    ArrayList<AccountDTO> listAccount;
    AdapterManagerAccount_Doctor adapterAccount;
    private RecyclerView rcvManagerAccountDoctor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__manager_account_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvManagerAccountDoctor = view.findViewById(R.id.rcv_managerAccountDoctor);
        accountDAO = new AccountDAO(getContext());
        listAccount = accountDAO.getAccountDoctor();
        adapterAccount = new AdapterManagerAccount_Doctor(accountDAO, listAccount);
        rcvManagerAccountDoctor.setAdapter(adapterAccount);
    }
}