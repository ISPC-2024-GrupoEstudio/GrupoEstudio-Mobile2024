package com.example.proy_mobile2024.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.proy_mobile2024.IntroCard1Fragment;
import com.example.proy_mobile2024.IntroCard2Fragment;
import com.example.proy_mobile2024.IntroCard3Fragment;

public class IntroAdapter extends FragmentStateAdapter {

    public IntroAdapter(@NonNull FragmentActivity fa){
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){
            case 0: return new IntroCard1Fragment();
            case 1: return new IntroCard2Fragment();
            case 2: return new IntroCard3Fragment();
            default: return new IntroCard1Fragment();
        }
    }

    @Override
    public int getItemCount(){
        return 3;
    }
}
