package com.example.appdatlichkhamcodedoan.fragment.fragment_nav_user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatlichkhamcodedoan.R;
import com.example.appdatlichkhamcodedoan.adapter.AdapterListFile;
import com.example.appdatlichkhamcodedoan.dao.FileDAO;
import com.example.appdatlichkhamcodedoan.dto.FileDTO;

import java.util.ArrayList;


public class Fragment_file extends Fragment {
    private RecyclerView rcvListFile;
    ArrayList<FileDTO> listFileByUser;
    FileDAO fileDAO;
    AdapterListFile adapterListFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manager_file, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fileDAO = new FileDAO(getContext());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("idUser",-1);
        listFileByUser = fileDAO.getFileByIdUser(idUser);
        adapterListFile = new AdapterListFile(listFileByUser, fileDAO,"user");
        rcvListFile = view.findViewById(R.id.rcvListFile);
        rcvListFile.setAdapter(adapterListFile);
    }
}