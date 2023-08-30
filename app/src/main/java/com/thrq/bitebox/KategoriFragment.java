package com.thrq.bitebox;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class KategoriFragment extends Fragment {

    RecyclerView recyclerView;
    List<KategoriClass> kategoriList;
    DatabaseReference databaseReference;
    KategoriAdapter kategoriAdapter;
    AlertDialog dialog;
    ValueEventListener eventListener;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kategori, container, false);
        // Inflate the layout for this fragment

        recyclerView = rootView.findViewById(R.id.recyclerView);
        searchView = rootView.findViewById(R.id.seacrh);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        dialog = builder.create();


        kategoriList = new ArrayList<>();
        kategoriAdapter = new KategoriAdapter(getActivity(), kategoriList);
        recyclerView.setAdapter(kategoriAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Kategori");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                kategoriList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    KategoriClass kategoriClass = itemSnapshot.getValue(KategoriClass.class);

                    kategoriClass.setKey(itemSnapshot.getKey());

                    kategoriList.add(kategoriClass);
                }
                kategoriAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        SearchView searchView = rootView.findViewById(R.id.seacrh);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
        return rootView;

    }


        public void searchList(String text){
            ArrayList<KategoriClass> searchList = new ArrayList<>();
            for (KategoriClass kategoriClass : kategoriList){
                if (kategoriClass.getDataNama().toLowerCase().contains(text.toLowerCase())){
                    searchList.add(kategoriClass);
                }
            }

            if (kategoriAdapter != null) {
                kategoriAdapter.searchDataList(searchList);
            }



    }

}

