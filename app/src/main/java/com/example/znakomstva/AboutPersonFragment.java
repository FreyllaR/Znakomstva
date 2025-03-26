package com.example.znakomstva;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Locale;


public class AboutPersonFragment extends Fragment {

    private static final int GALLERY_REQUEST_CODE = 1;

    private EditText aboutEditText, usernameEditText, ageEditText;
    private RadioGroup purposeRadioGroup;
    private Spinner hobbySpinner, socialLifeSpinner, creativitySpinner, activeLifestyleSpinner, sportSpinner, travelSpinner, homeTimeSpinner, moviesSpinner, animalsSpinner, personalGrowthSpinner, alcoholSmokingSpinner, sportyBodySpinner;
    private GridLayout photosGridLayout;

    private ImageView[] imageViews = new ImageView[6];

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
        View view = inflater.inflate(R.layout.fragment_about_person, container, false);

        usernameEditText = view.findViewById(R.id.nameEditText);
        ageEditText = view.findViewById(R.id.ageEditText);
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

        imageViews[0] = view.findViewById(R.id.imageView1);
        imageViews[1] = view.findViewById(R.id.imageView2);
        imageViews[2] = view.findViewById(R.id.imageView3);
        imageViews[3] = view.findViewById(R.id.imageView4);
        imageViews[4] = view.findViewById(R.id.imageView5);
        imageViews[5] = view.findViewById(R.id.imageView6);

        Button addPhotoButton = view.findViewById(R.id.buttonphoto);
        addPhotoButton.setOnClickListener(v -> openGallery());

        Button nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> goToProfileFragment());

        // Установка адаптеров для спиннеров
        setupSpinners();

        return view;
    }

    private void setupSpinners() {
        hobbySpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.hobby_array), R.font.semibold));
        socialLifeSpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.social_life_array), R.font.semibold));
        creativitySpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.creativity_array), R.font.semibold));
        activeLifestyleSpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.active_lifestyle_array), R.font.semibold));
        sportSpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.sport_array), R.font.semibold));
        travelSpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.travel_array), R.font.semibold));
        homeTimeSpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.home_time_array), R.font.semibold));
        moviesSpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.movies_array), R.font.semibold));
        animalsSpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.animals_array), R.font.semibold));
        personalGrowthSpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.personal_growth_array), R.font.semibold));
        alcoholSmokingSpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.alcohol_smoking_array), R.font.semibold));
        sportyBodySpinner.setAdapter(new CustomSpinnerAdapter(getContext(), getResources().getStringArray(R.array.sporty_body_array), R.font.semibold));
    }

    private void goToProfileFragment() {
        ArrayList<Uri> imageUris = new ArrayList<>();

        // Добавляем все изображения в imageUris
        for (ImageView imageView : imageViews) {
            if (imageView.getDrawable() != null) {
                Uri uri = (Uri) imageView.getTag();
                if (uri != null) {
                    imageUris.add(uri);
                }
            }
        }

        // Проверка на наличие хотя бы одного изображения
        if (imageUris.isEmpty()) {
            Toast.makeText(getContext(), "Пожалуйста, добавьте хотя бы одно фото.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Проверка на заполненные обязательные поля
        if (usernameEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Пожалуйста, введите ваше имя.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ageEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Пожалуйста, введите ваш возраст.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (purposeRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "Пожалуйста, выберите цель знакомства.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Добавьте дополнительные проверки для других обязательных полей, если необходимо

        ArrayList<String> textItems = new ArrayList<>();
        textItems.add(usernameEditText.getText().toString().substring(0, 1).toUpperCase() + usernameEditText.getText().toString().substring(1) + ", " + ageEditText.getText().toString());
        textItems.add("О себе: " + aboutEditText.getText().toString());
        textItems.add("Цель знакомства: " + getSelectedPurpose());
        textItems.add("Хобби: " + (hobbySpinner.getSelectedItem() != null ? hobbySpinner.getSelectedItem().toString() : "Не указано"));
        textItems.add("Социальная жизнь: " + (socialLifeSpinner.getSelectedItem() != null ? socialLifeSpinner.getSelectedItem().toString() : "Не указано"));
        textItems.add("Творчество: " + (creativitySpinner.getSelectedItem() != null ? creativitySpinner.getSelectedItem().toString() : "Не указано"));
        textItems.add("Активный образ жизни: " + (activeLifestyleSpinner.getSelectedItem() != null ? activeLifestyleSpinner.getSelectedItem().toString() : "Не указано"));
        textItems.add("Спорт: " + (sportSpinner.getSelectedItem() != null ? sportSpinner.getSelectedItem().toString() : "Не указано"));
        textItems.add("Путешествия: " + (travelSpinner.getSelectedItem() != null ? travelSpinner.getSelectedItem().toString() : "Не указано"));
        textItems.add("Время дома: " + (homeTimeSpinner.getSelectedItem() != null ? homeTimeSpinner.getSelectedItem().toString() : "Не указано"));
        textItems.add("Фильмы и сериалы: " + (moviesSpinner.getSelectedItem() != null ? moviesSpinner.getSelectedItem().toString() : "Не указано"));
        textItems.add("Животные: " + (animalsSpinner.getSelectedItem() != null ? animalsSpinner.getSelectedItem().toString() : "Не указано"));
        textItems.add("Личное: " + (personalGrowthSpinner.getSelectedItem() != null ? personalGrowthSpinner.getSelectedItem().toString() : "Не указано"));
        textItems.add("Отношение к алкоголю и курению: " + (alcoholSmokingSpinner.getSelectedItem() != null ? alcoholSmokingSpinner.getSelectedItem().toString() : "Не указано"));
        textItems.add("Спортивное телосложение: " + (sportyBodySpinner.getSelectedItem() != null ? sportyBodySpinner.getSelectedItem().toString() : "Не указано"));

        // Создание нового экземпляра ChoosePartnerFragment
        ChoosePartnerFragment choosePartnerFragment = ChoosePartnerFragment.newInstance(imageUris, textItems);
        Log.d("AboutPersonFragment", "Image URIs: " + imageUris);
        Log.d("AboutPersonFragment", "Text Items: " + textItems);
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.fragment_container, choosePartnerFragment)
                .addToBackStack(null)
                .commit();
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
        for (ImageView imageView : imageViews) {
            if (imageView.getDrawable() == null) {
                imageView.setImageURI(imageUri);
                imageView.setTag(imageUri); // Убедитесь, что тег установлен
                return;
            }
        }
        Toast.makeText(getContext(), "Максимальное количество фото достигнуто", Toast.LENGTH_SHORT).show();
    }

    private String getSelectedPurpose() {
        int selectedId = purposeRadioGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.seriousRelationship) {
            return "Серьезные отношения";
        } else if (selectedId == R.id.casualRelationship) {
            return "Свободные отношения";
        } else if (selectedId == R.id.friendship) {
            return "Дружеское общение";
        } else if (selectedId == R.id.newExperience) {
            return "Новый опыт";
        }
        return "Не указано";
    }
}