package com.example.periodictable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.periodictable.asynctask.AsyncTaskFindDelegate;
import com.example.periodictable.asynctask.FindElementAsyncTask;
import com.example.periodictable.database.AppDatabase;

public class ElementDetailActivity extends AppCompatActivity implements AsyncTaskFindDelegate {

    // Initialisation of the components of the layout
    public TextView atomicNumberTextView;
    public TextView symbolTextView;
    public TextView nameTextView;
    public TextView atomicRadiusTextView;
    public TextView boilingPointTextView;
    public TextView yearDiscoveredTextView;
    public TextView densityTextView;
    public TextView bondingTypeTextView;
    public TextView meltingPointTextView;
    public TextView affinityTextView;
    public TextView negativeTextView;
    public TextView configTextView;
    public TextView ionRadiusTextView;
    public TextView energyTextView;
    public TextView standardTextView;
    public TextView vanTextView;
    public TextView oxiTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        // Getting the atomic number of elements
        int atomicNumber = Integer.parseInt(intent.getStringExtra("atomicNumber"));

        // Directing users to appropriate layout files depending on tile pressed
        if (atomicNumber == 1 || atomicNumber == 6 || atomicNumber == 8 ||
                atomicNumber == 15 || atomicNumber == 16 || atomicNumber == 34) {
            setContentView(R.layout.non_metal_element_detail);
        } else if (atomicNumber == 2 || atomicNumber == 10 || atomicNumber == 18 ||
                    atomicNumber == 36 || atomicNumber == 54 || atomicNumber == 86 ||
                    atomicNumber == 118) {
            setContentView(R.layout.noble_gas_element_detail);
        } else if (atomicNumber == 5 || atomicNumber == 14 || atomicNumber == 32 ||
                    atomicNumber == 33 || atomicNumber == 51 || atomicNumber == 52) {
            setContentView(R.layout.metalloid_element_detail);
        } else if (atomicNumber == 3 || atomicNumber == 11 || atomicNumber == 19 ||
                    atomicNumber == 37 || atomicNumber == 55 || atomicNumber == 87) {
            setContentView(R.layout.alkali_element_detail);
        } else if (atomicNumber == 4 || atomicNumber == 12 || atomicNumber == 20 ||
                    atomicNumber == 38 || atomicNumber == 56 || atomicNumber == 88) {
            setContentView(R.layout.alkaline_element_detail);
        } else if (atomicNumber == 9 || atomicNumber == 17 || atomicNumber == 35 ||
                    atomicNumber == 53 || atomicNumber == 85) {
            setContentView(R.layout.halogen_element_detail);
        } else if (atomicNumber == 13 || atomicNumber == 31 || atomicNumber == 49 ||
                    atomicNumber == 50 || atomicNumber == 81 || atomicNumber == 82 ||
                    atomicNumber == 83 || atomicNumber == 84) {
            setContentView(R.layout.post_transition_metal_element_detail);
        } else if (atomicNumber >= 21 && atomicNumber <= 30) {
            setContentView(R.layout.transition_metal_element_detail);
        } else if (atomicNumber >= 39 && atomicNumber <= 48) {
            setContentView(R.layout.transition_metal_element_detail);
        } else if (atomicNumber >= 72 && atomicNumber <= 80) {
            setContentView(R.layout.transition_metal_element_detail);
        } else if (atomicNumber >= 104 && atomicNumber <= 108) {
            setContentView(R.layout.transition_metal_element_detail);
        } else if (atomicNumber == 112) {
            setContentView(R.layout.transition_metal_element_detail);
        } else if (atomicNumber >= 109 && atomicNumber <= 111) {
            setContentView(R.layout.unknown_element_detail);
        } else if (atomicNumber == 113) {
            setContentView(R.layout.unknown_element_detail);
        } else if (atomicNumber >= 57 && atomicNumber <= 71) {
            setContentView(R.layout.lanthanide_element_detail);
        } else if (atomicNumber >= 89 && atomicNumber <= 103) {
            setContentView(R.layout.actinide_element_detail);
        }

        // Finding the views from the layout
        atomicNumberTextView = findViewById(R.id.atomicNumber);
        symbolTextView = findViewById(R.id.symbol);
        nameTextView = findViewById(R.id.name);
        atomicRadiusTextView = findViewById(R.id.atomicRadius);
        boilingPointTextView = findViewById(R.id.boilingPoint);
        yearDiscoveredTextView = findViewById(R.id.yearDiscovered);
        densityTextView = findViewById(R.id.density);
        bondingTypeTextView = findViewById(R.id.bondingType);
        meltingPointTextView = findViewById(R.id.meltingPoint);
        affinityTextView = findViewById(R.id.electronAffinity);
        negativeTextView = findViewById(R.id.electronNegativity);
        configTextView = findViewById(R.id.electronConfiguration);
        ionRadiusTextView = findViewById(R.id.ionRadius);
        energyTextView = findViewById(R.id.ionisationEnergy);
        standardTextView = findViewById(R.id.standardState);
        vanTextView = findViewById(R.id.vanDerWaalsRadius);
        oxiTextView = findViewById(R.id.oxidisationState);

        // AsyncTask to obtain data
        AppDatabase db = AppDatabase.getInstance(this);
        FindElementAsyncTask findElementsAsyncTask = new FindElementAsyncTask();
        findElementsAsyncTask.setDatabase(db);
        findElementsAsyncTask.setDelegate(ElementDetailActivity.this);
        findElementsAsyncTask.execute(atomicNumber);

    }
    // Setting data to the page
    @Override
    public void handleTaskResult(Element element) {
        atomicNumberTextView.setText(Integer.toString(element.getAtomicNumber()));
        symbolTextView.setText(element.getSymbol());
        nameTextView.setText(element.getName());
        atomicRadiusTextView.setText(element.getAtomicRadius());
        boilingPointTextView.setText(element.getBoilingPoint());
        yearDiscoveredTextView.setText(element.getYearDiscovered());
        densityTextView.setText(element.getDensity());
        bondingTypeTextView.setText(element.getBondingType());
        meltingPointTextView.setText(element.getMeltingPoint());
        affinityTextView.setText(element.getElectronAffinity());
        negativeTextView.setText(element.getElectronNegativity());
        configTextView.setText(element.getElectronicConfiguration());
        ionRadiusTextView.setText(element.getIonRadius());
        energyTextView.setText(element.getIonizationEnergy());
        standardTextView.setText(element.getStandardState());
        vanTextView.setText(element.getVanDerWaalsRadius());
        oxiTextView.setText(element.getOxidationStates());

    }
}