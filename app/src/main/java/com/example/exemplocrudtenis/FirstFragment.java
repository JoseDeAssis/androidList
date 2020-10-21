package com.example.exemplocrudtenis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FirstFragment extends Fragment implements TenisDetailsListener {

    private TennisListRVAdapter mAdapter;
    private TennisViewModel tennisViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        RecyclerView mRecyclerView = view.findViewById(R.id.listaTenisRecyclerView);

        mAdapter = new TennisListRVAdapter(getContext(), this);

//      mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//      mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        tennisViewModel = new ViewModelProvider(this).get(TennisViewModel.class);
        tennisViewModel.getAllTennis().observe(getViewLifecycleOwner(), new Observer<List<Tennis>>() {
            @Override
            public void onChanged(List<Tennis> tennis) {
                mAdapter.setTennisList(tennis);
            }
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.tennisListFragmentFAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("type", "CREATE");
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, args);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void editTennis(Tennis tennis) {
        Bundle args = new Bundle();
        args.putString("type", "EDIT");
        args.putString("model", tennis.getTennisModel());
        args.putInt("id", tennis.getId());
        args.putDouble("price", tennis.getTennisPrice());

        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment, args);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteTennis(Tennis tennis) {
        tennisViewModel.delete(tennis);
        mAdapter.notifyDataSetChanged();
    }

}