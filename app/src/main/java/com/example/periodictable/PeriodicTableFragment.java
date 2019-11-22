package com.example.periodictable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.periodictable.asynctask.AsyncTaskGetDelegate;
import com.example.periodictable.asynctask.AsyncTaskInsertDelegate;
import com.example.periodictable.asynctask.GetElementsAsyncTask;
import com.example.periodictable.asynctask.InsertElementsAsyncTask;
import com.example.periodictable.database.AppDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeriodicTableFragment extends Fragment implements AsyncTaskInsertDelegate, AsyncTaskGetDelegate {

    public String packageName;
    public Context context;

    // List of element ID's for reference when looping through all the elements to insert
    // data into the individual tiles on the periodic table.
    // Main purpose is to simplify and reduce redundant code.
    List<Integer> allElements = Arrays.asList(R.id.element1, R.id.element2, R.id.element3,
            R.id.element4, R.id.element5, R.id.element6, R.id.element7, R.id.element8,
            R.id.element9, R.id.element10, R.id.element11, R.id.element12, R.id.element13,
            R.id.element14, R.id.element15, R.id.element16, R.id.element17, R.id.element18,
            R.id.element19, R.id.element20, R.id.element21, R.id.element22, R.id.element23,
            R.id.element24, R.id.element25, R.id.element26, R.id.element27, R.id.element28,
            R.id.element29, R.id.element30, R.id.element31, R.id.element32, R.id.element33,
            R.id.element34, R.id.element35, R.id.element36, R.id.element37, R.id.element38,
            R.id.element39, R.id.element40, R.id.element41, R.id.element42, R.id.element43,
            R.id.element44, R.id.element45, R.id.element46, R.id.element47, R.id.element48,
            R.id.element49, R.id.element50, R.id.element51, R.id.element52, R.id.element53,
            R.id.element54, R.id.element55, R.id.element56, R.id.element57, R.id.element58,
            R.id.element59, R.id.element60, R.id.element61, R.id.element62, R.id.element63,
            R.id.element64, R.id.element65, R.id.element66, R.id.element67, R.id.element68,
            R.id.element69, R.id.element70, R.id.element71, R.id.element72, R.id.element73,
            R.id.element74, R.id.element75, R.id.element76, R.id.element77, R.id.element78,
            R.id.element79, R.id.element80, R.id.element81, R.id.element82, R.id.element83,
            R.id.element84, R.id.element85, R.id.element86, R.id.element87, R.id.element88,
            R.id.element89, R.id.element90, R.id.element91, R.id.element92, R.id.element93,
            R.id.element94, R.id.element95, R.id.element96, R.id.element97, R.id.element98,
            R.id.element99, R.id.element100, R.id.element101, R.id.element102, R.id.element103,
            R.id.element104, R.id.element105, R.id.element106, R.id.element107, R.id.element108,
            R.id.element109, R.id.element110, R.id.element111, R.id.element112, R.id.element113,
            R.id.element114, R.id.element115, R.id.element116, R.id.element117, R.id.element118);
    View placeholder;

    public PeriodicTableFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_periodic_table, container, false);
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        placeholder = view;
        packageName = getActivity().getPackageName();
        // API link
        String url = "https://neelpatel05.pythonanywhere.com/";

        context = getContext();
        // Obtain a response from the API link and add the response to an array which
        // will store our response data
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new GsonBuilder().serializeNulls().create();
                Type listType = new TypeToken<ArrayList<Element>>() {
                }.getType();
                ArrayList<Element> elementResults = gson.fromJson(response, listType);
                AppDatabase db = AppDatabase.getInstance(context);
                InsertElementsAsyncTask insertElementsAsyncTask = new InsertElementsAsyncTask();
                insertElementsAsyncTask.setDatabase(db);
                insertElementsAsyncTask.setDelegate(PeriodicTableFragment.this);
                Element[] elements = elementResults.toArray(new Element[elementResults.size()]);
                insertElementsAsyncTask.execute(elements);
                requestQueue.stop();
            }
        };

        // Toast response in case an error occurs
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };
        // Creating a request for data
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);

        setElementListeners1();
        return placeholder;
    }
    // establishing a loop to identify all the tiles in the periodic table fragment
    public void setElementListeners1() {
        int elementCounter = 0;
        while (elementCounter < allElements.size()) {
            final int counter = elementCounter;
            placeholder.findViewById(allElements.get(elementCounter)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setElementListeners2(counter);
                }
            });
            elementCounter++;
        }
    }
    // placing the atomic number in the specified element tile
    public void setElementListeners2(int counter) {
        Context context = getContext();
        Intent intent = new Intent(context, ElementDetailActivity.class);
        TextView atomicNumber = placeholder.findViewById(allElements.get(counter)).findViewById(R.id.atomicNumber);
        intent.putExtra("atomicNumber", atomicNumber.getText());
        context.startActivity(intent);
    }

    // AsyncTask to obtain elements
    @Override
    public void handleTaskResult(String result) {
        System.out.println("AsyncTask insert Done");
        GetElementsAsyncTask getElementsAsyncTask = new GetElementsAsyncTask();
        getElementsAsyncTask.setDatabase(AppDatabase.getInstance(getContext()));
        getElementsAsyncTask.setDelegate(this);
        getElementsAsyncTask.execute();
    }
    // Establishing a loop to identify all the tiles in the periodic table fragment
    // as well as placing the data in the relevant tile
    @Override
    public void handleTaskGetResult(List<Element> elements) {
        int elementCounter = 0;
        while (elementCounter < allElements.size()) {
            ConstraintLayout box = placeholder.findViewById(allElements.get(elementCounter));
            TextView name = box.findViewById(R.id.name);
            TextView symbol = box.findViewById(R.id.symbol);
            TextView atomicNumber = box.findViewById(R.id.atomicNumber);
            name.setText(elements.get(elementCounter).getName());
            symbol.setText(elements.get(elementCounter).getSymbol());
            atomicNumber.setText(Integer.toString(elements.get(elementCounter).getAtomicNumber()));
            elementCounter++;
        }
        // Placing placeholder tiles for the lanthanides and actinide tiles
        ConstraintLayout lanthanides = placeholder.findViewById(R.id.Lanthanides);
        TextView lanthinidesName = lanthanides.findViewById(R.id.name);
        TextView lanthinidesSymbol = lanthanides.findViewById(R.id.symbol);
        TextView lanthinidesAtomicNumber = lanthanides.findViewById(R.id.atomicNumber);
        lanthinidesName.setText(R.string.lanthanides);
        lanthinidesSymbol.setText(R.string.down_arrow);
        lanthinidesSymbol.setTextSize(14);
        lanthinidesAtomicNumber.setText(R.string.lanthanides_atomic_number);
        ConstraintLayout actinides = placeholder.findViewById(R.id.Actinides);
        TextView actinidesName = actinides.findViewById(R.id.name);
        TextView actinidesSymbol = actinides.findViewById(R.id.symbol);
        TextView actinidesAtomicNumber = actinides.findViewById(R.id.atomicNumber);
        actinidesName.setText(R.string.actinides);
        actinidesSymbol.setText(R.string.down_arrow);
        actinidesSymbol.setTextSize(14);
        actinidesAtomicNumber.setText(R.string.actinides_atomic_number);
    }
}