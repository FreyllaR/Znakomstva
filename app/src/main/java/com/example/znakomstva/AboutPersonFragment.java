package com.example.znakomstva;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AboutPersonFragment extends Fragment {

    private static final int GALLERY_REQUEST_CODE = 1;

    private EditText aboutEditText;
    private RadioGroup purposeRadioGroup;
    private Spinner hobbySpinner, socialLifeSpinner, creativitySpinner, activeLifestyleSpinner, sportSpinner, travelSpinner, homeTimeSpinner, moviesSpinner, animalsSpinner, personalGrowthSpinner, alcoholSmokingSpinner, sportyBodySpinner;
    private GridLayout photosGridLayout;

    private ImageView[] imageViews = new ImageView[6]; // Массив для хранения ссылок на ImageView

    public AboutPersonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_person, container, false);

        aboutEditText = view.findViewById(R.id.aboutEditText);
        purposeRadioGroup = view.findViewById(R.id.purposeRadioGroup);
        hobbySpinner = view.findViewById(R.id.hobbySpinner);
        socialLifeSpinner = view.findViewById(R.id.socialLifeSpinner);
        creativitySpinner = view.findViewById(R.id.creativitySpinner);
        activeLifestyleSpinner = view.findViewById(R.id.activeLifestyleSpinner);
        sportSpinner = view.findViewById(R.id.sportSpinner);
        travelSpinner = view.findViewById(R.id.travelSpinner);
        homeTimeSpinner = view.findViewById(R.id.homeTimeSpinner);
        moviesSpinner = view.findViewById(R.id.moviesSpinner);
        animalsSpinner = view.findViewById(R.id.animalsSpinner);
        personalGrowthSpinner = view.findViewById(R.id.personalGrowthSpinner);
        alcoholSmokingSpinner = view.findViewById(R.id.alcoholSmokingSpinner);
        sportyBodySpinner = view.findViewById(R.id.sportyBodySpinner);
        photosGridLayout = view.findViewById(R.id.photosGridLayout);

        // Инициализация массива ImageView
        imageViews[0] = view.findViewById(R.id.imageView1);
        imageViews[1] = view.findViewById(R.id.imageView2);
        imageViews[2] = view.findViewById(R.id.imageView3);
        imageViews[3] = view.findViewById(R.id.imageView4);
        imageViews[4] = view.findViewById(R.id.imageView5);
        imageViews[5] = view.findViewById(R.id.imageView6);

        // Настройка адаптеров для каждого Spinner
        hobbySpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.hobby_array, android.R.layout.simple_spinner_item));
        socialLifeSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.social_life_array, android.R.layout.simple_spinner_item));
        creativitySpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.creativity_array, android.R.layout.simple_spinner_item));
        activeLifestyleSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.active_lifestyle_array, android.R.layout.simple_spinner_item));
        sportSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.sport_array, android.R.layout.simple_spinner_item));
        travelSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.travel_array, android.R.layout.simple_spinner_item));
        homeTimeSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.home_time_array, android.R.layout.simple_spinner_item));
        moviesSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.movies_array, android.R.layout.simple_spinner_item));
        animalsSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.animals_array, android.R.layout.simple_spinner_item));
        personalGrowthSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.personal_growth_array, android.R.layout.simple_spinner_item));
        alcoholSmokingSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.alcohol_smoking_array, android.R.layout.simple_spinner_item));
        sportyBodySpinner.setAdapter(ArrayAdapter.createFromResource(getContext(),
                R.array.sporty_body_array, android.R.layout.simple_spinner_item));

        // Устанавливаем обработчик нажатия на кнопку
        Button addPhotoButton = view.findViewById(R.id.buttonphoto);
        addPhotoButton.setOnClickListener(v -> openGallery());

        return view;
    }

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                addPhotoCellWithImage(selectedImageUri);
            }
        }
    }

    private void addPhotoCellWithImage(Uri imageUri) {
        // Проверяем, есть ли свободные ячейки
        for (ImageView imageView : imageViews) {
            if (imageView.getDrawable() == null) { // Если ячейка пустая
                imageView.setImageURI(imageUri); // Устанавливаем изображение
                return; // Выходим из метода после добавления изображения
            }
        }

        // Если все ячейки заняты, можно показать сообщение
        Toast.makeText(getContext(), "Максимальное количество фото достигнуто", Toast.LENGTH_SHORT).show();
    }
}