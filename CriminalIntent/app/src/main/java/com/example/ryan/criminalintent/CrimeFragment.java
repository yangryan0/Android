package com.example.ryan.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Ryan on 6/5/2017.
 */

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    public static final String EXTRA_CRIME_ID =
            "com.example.ryan.criminalintent.crime_id";
    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    public static CrimeFragment newInstance(UUID crimeID) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeID);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeID = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        java.text.DateFormat dateFormat = DateFormat.getLongDateFormat(getContext());
        java.text.DateFormat timeFormat = DateFormat.getTimeFormat(getContext());
        mDateButton.setText(dateFormat.format(mCrime.getDate()) + " " + timeFormat.format(mCrime.getDate()));
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                ChoiceFragment dialog = ChoiceFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, 0);
                dialog.show(fm, DIALOG_DATE);
            }
        });
        mSolvedCheckBox.setChecked(mCrime.getSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            Date a = mCrime.getDate();
            a.setDate(date.getDate());
            mCrime.setDate(a);
            java.text.DateFormat dateFormat = DateFormat.getLongDateFormat(getContext());
            java.text.DateFormat timeFormat = DateFormat.getTimeFormat(getContext());
            mDateButton.setText(dateFormat.format(mCrime.getDate()) + " " + timeFormat.format(mCrime.getDate()));
        }
        if (requestCode == REQUEST_TIME) {
            Date date = (Date) data
                    .getSerializableExtra(TimePickerFragment.EXTRA_DATE);
            Date a = mCrime.getDate();
            a.setHours(date.getHours());
            a.setMinutes(date.getMinutes());
            mCrime.setDate(a);
            java.text.DateFormat dateFormat = DateFormat.getLongDateFormat(getContext());
            java.text.DateFormat timeFormat = DateFormat.getTimeFormat(getContext());
            mDateButton.setText(dateFormat.format(mCrime.getDate()) + " " + timeFormat.format(mCrime.getDate()));
        }
    }
}
