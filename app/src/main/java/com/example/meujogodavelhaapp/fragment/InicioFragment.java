package com.example.meujogodavelhaapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.meujogodavelhaapp.R;
import com.example.meujogodavelhaapp.databinding.FragmentInicioBinding;
import com.example.meujogodavelhaapp.databinding.FragmentJogoBinding;

public class InicioFragment extends Fragment {
    // variavel para acessar os elementos da view
    private FragmentInicioBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInicioBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btJoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(InicioFragment.this).navigate(R.id.action_inicioFragment_to_jogoFragment);
            }
        });
    }
}