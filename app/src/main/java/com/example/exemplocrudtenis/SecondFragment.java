package com.example.exemplocrudtenis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.InputStream;

public class SecondFragment extends Fragment {

    private TextInputEditText tennisModel, tennisId, tennisPrice;

    public void updateFields() {
        this.tennisModel.setText(getArguments().getString("model"));
        this.tennisId.setText(getArguments().getInt("id") + "");
        this.tennisPrice.setText(getArguments().getDouble("price") + "");
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tennisModel = view.findViewById(R.id.tennisNameEditTxt);
        tennisId = view.findViewById(R.id.tennisIdEditTxt);
        tennisPrice = view.findViewById(R.id.tennisPriceEditTxt);
        final TennisViewModel tennisViewModel = new ViewModelProvider(this).get(TennisViewModel.class);

        ImageView img = view.findViewById(R.id.secondFragmentTenisImg);
        String image = "steam.png";

        if(getArguments().getString("type").equals("EDIT")) {
            image = getArguments().getString("model") + ".png";

            updateFields();
        }

        try {
            // get input stream
            InputStream ims = getContext().getAssets().open(image);
            // load image as Drawable
            Bitmap bitmap = BitmapFactory.decodeStream(ims);
            img.setImageBitmap(bitmap);
            ims.close();
        } catch (IOException ex) {
            return;
        }

        MaterialButton materialButton = view.findViewById(R.id.secondFragmentButton);

        materialButton.setText(getArguments().getString("type"));

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tennis tennis = new Tennis();
                tennis.setTennisModel(tennisModel.getText().toString());
                tennis.setTennisPrice(Double.parseDouble(tennisPrice.getText().toString()));
                tennis.setId(Integer.parseInt(tennisId.getText().toString()));

                if(getArguments().get("type").equals("EDIT")) {
                    tennisViewModel.update(tennis);
                } else {
                    tennisViewModel.insert(tennis);
                }

                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}