package com.example.znakomstva;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final Typeface typeface;

    public CustomSpinnerAdapter(Context context, String[] values, int fontResourceId) {
        super(context, android.R.layout.simple_spinner_item, values);
        this.context = context;
        this.values = values;
        // Получаем шрифт из ресурсов
        this.typeface = ResourcesCompat.getFont(context, fontResourceId);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView textView = (TextView) view;
        textView.setTypeface(typeface);
        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView textView = (TextView) view;
        textView.setTypeface(typeface);
        return view;
    }
}