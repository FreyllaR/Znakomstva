package com.example.znakomstva;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {


    private ArrayList<Uri> imageUris;
    private ArrayList<String> textItems;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(ArrayList<Uri> imageUris, ArrayList<String> textItems) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("imageUris", imageUris);
        args.putStringArrayList("textItems", textItems);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUris = getArguments().getParcelableArrayList("imageUris");
            textItems = getArguments().getStringArrayList("textItems");
            Log.d("ProfileFragment", "Image URIs: " + imageUris);
            Log.d("ProfileFragment", "Text Items: " + textItems);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Инициализация элементов интерфейса
        GridLayout photosGridLayout = view.findViewById(R.id.photosGridLayout);
        TextView textInfo = view.findViewById(R.id.textInfo);

        // Заполнение ячеек изображениями
        if (imageUris != null) {
            // Убедитесь, что у вас достаточно изображений, чтобы заполнить ячейки
            ((ImageView) view.findViewById(R.id.imageView1)).setImageURI(imageUris.size() > 0 ? imageUris.get(0) : null);
            ((ImageView) view.findViewById(R.id.imageView2)).setImageURI(imageUris.size() > 1 ? imageUris.get(1) : null);
            ((ImageView) view.findViewById(R.id.imageView3)).setImageURI(imageUris.size() > 2 ? imageUris.get(2) : null);
            ((ImageView) view.findViewById(R.id.imageView4)).setImageURI(imageUris.size() > 3 ? imageUris.get(3) : null);
            ((ImageView) view.findViewById(R.id.imageView5)).setImageURI(imageUris.size() > 4 ? imageUris.get(4) : null);
            ((ImageView) view.findViewById(R.id.imageView6)).setImageURI(imageUris.size() > 5 ? imageUris.get(5) : null);
        }

        // Отображение текстовой информации
        StringBuilder fullText = new StringBuilder();
        if (textItems != null) {
            for (String text : textItems) {
                fullText.append(text).append("\n\n");
            }
        }
        textInfo.setText(fullText.toString().trim());

        return view;
    }
}