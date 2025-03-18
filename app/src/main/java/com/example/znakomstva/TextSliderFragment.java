package com.example.znakomstva;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TextSliderFragment extends BottomSheetDialogFragment {

    private static final String ARG_TEXT_ITEMS = "textItems";

    public static TextSliderFragment newInstance(ArrayList<String> textItems) {
        TextSliderFragment fragment = new TextSliderFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_TEXT_ITEMS, textItems);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_text, container, false);
        TextView textView = view.findViewById(R.id.textInfo);

        if (getArguments() != null) {
            ArrayList<String> textItems = getArguments().getStringArrayList(ARG_TEXT_ITEMS);
            StringBuilder fullText = new StringBuilder();
            for (String text : textItems) {
                fullText.append(text).append("\n\n"); // Добавляем текст с отступами
            }
            textView.setText(fullText.toString().trim());
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TextSliderFragment", "onStart called"); // Лог для отладки
        if (getDialog() != null) {
            View bottomSheet = getDialog().findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setPeekHeight(300); // Установите высоту, на которой будет виден BottomSheet
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED); // Убедитесь, что он в состоянии сворачивания
            }
        }
    }
}