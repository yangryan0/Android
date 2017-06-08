package com.example.ryan.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Ryan on 6/7/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
