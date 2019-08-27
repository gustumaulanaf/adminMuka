package com.gustu.adminmuka.fragment;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.gustu.adminmuka.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */public class DatePickerFragment extends AppCompatDialogFragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "DatePickerFragment";
    final Calendar c = Calendar.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Set the current date as the default date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Return a new instance of DatePickerDialog
        return new DatePickerDialog(getActivity(), DatePickerFragment.this, year, month, day);
    }

    // called when a date has been selected
    public void onDateSet(DatePicker view, int year, int month, int day) {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        Locale locale = new Locale("in", "ID");
        String selectedDate = new SimpleDateFormat("yyyy-MM-dd", locale).format(c.getTime());
        String selectHari = new SimpleDateFormat("EEEE", locale).format(c.getTime());
        Intent intent = new Intent();
        intent.putExtra("hari",selectHari);
        intent.putExtra("selectedDate",selectedDate);
       // Log.d(TAG, "onDateSet: " + selectedDate);
        // send date back to the target fragment
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                intent
        );
    }
}