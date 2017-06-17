package com.example.ryan.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import java.util.Date;

/**
 * Created by Ryan on 6/16/2017.
 */

public class ChoiceFragment extends DialogFragment {

    private static final String EXTRA_DATE =
            "com.example.ryan.criminalintent.date";
    String[] choice_array = new String[]{"Date", "Time"};

    public static ChoiceFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        ChoiceFragment fragment = new ChoiceFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_edit)
                .setItems(choice_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        if (pos == 0) {
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            DatePickerFragment d = DatePickerFragment.newInstance((Date) getArguments().getSerializable(EXTRA_DATE));
                            d.setTargetFragment(getTargetFragment(), 0);
                            d.show(fm, "DATE");
                        } else if (pos == 1) {
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            TimePickerFragment d = TimePickerFragment.newInstance((Date) getArguments().getSerializable(EXTRA_DATE));
                            d.setTargetFragment(getTargetFragment(), 1);
                            d.show(fm, "TIME");
                        }
                    }
                });
        return builder.create();
    }

}
