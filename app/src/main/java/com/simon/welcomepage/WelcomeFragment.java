package com.simon.welcomepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WelcomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle bundle = getArguments();
		int wel_layout_id = bundle.getInt("wel_layout_id");
		int position = bundle.getInt("position");
		View view = inflater.inflate(wel_layout_id, null);
		view.setTag(position);
		return view;
	}
}
