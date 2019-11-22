package com.example.periodictable;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
// Creating a quiz fragment
public class QuizFragment extends Fragment {

    Button easy;
    Button medium;
    Button hard;

    public QuizFragment() {
        // Empty constructor required
    }
    // Directing the user based on the difficulty selected
    // The values 20, 50, 118 are the atomic number, with 20 being a quiz up to the 20th element,
    // 50 being a quiz up to the 50th element and 118 being a quiz up to the 118th element
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        easy = view.findViewById(R.id.easy);
        medium = view.findViewById(R.id.medium);
        hard = view.findViewById(R.id.hard);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MultipleChoice.class);
                intent.putExtra("difficulty", 20);
                startActivity(intent);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MultipleChoice.class);
                intent.putExtra("difficulty", 50);
                startActivity(intent);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MultipleChoice.class);
                intent.putExtra("difficulty", 118);
                startActivity(intent);
            }
        });
        return view;
    }

}
