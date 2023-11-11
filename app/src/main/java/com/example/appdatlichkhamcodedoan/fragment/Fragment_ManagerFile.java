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
import com.example.appdatlichkhamcodedoan.adapter.AdapterListFile;
import com.example.appdatlichkhamcodedoan.dao.FileDAO;
import com.example.appdatlichkhamcodedoan.dto.FileDTO;

import java.util.ArrayList;


public class Fragment_ManagerFile extends Fragment {

    private RecyclerView rcvListFile;
    FileDAO fileDAO;
    ArrayList<FileDTO> listFile;
    AdapterListFile adapterListFile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manager_file, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListFile = view.findViewById(R.id.rcvListFile);
        fileDAO = new FileDAO(getContext());
        listFile = fileDAO.getAll();
        adapterListFile = new AdapterListFile(listFile, fileDAO,"admin");
        rcvListFile.setAdapter(adapterListFile);
    }
}