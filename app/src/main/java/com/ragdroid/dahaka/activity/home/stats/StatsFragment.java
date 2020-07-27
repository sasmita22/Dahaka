package com.ragdroid.dahaka.activity.home.stats;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ragdroid.dahaka.R;
import com.ragdroid.dahaka.databinding.FragmentStatsBinding;
import com.ragdroid.dahaka.mvp.BaseFragment;

import javax.inject.Inject;

/**
 * Created by garimajain on 16/08/17.
 */

public class StatsFragment extends BaseFragment<StatsContract.Presenter> implements StatsContract.View {

    private FragmentStatsBinding dataBinding;

    @Inject
    public StatsFragment() {
        //required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        dataBinding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void showModel(StatsModel statsModel) {
        dataBinding.setModel(statsModel);
    }
}
