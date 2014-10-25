package com.intro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextMessageDetailFragment extends Fragment {
	public static final String ARG_ITEM_ID = "item_id";

	private MessageChange mItem;

	public TextMessageDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			mItem = MessageHistory.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_textmessage_detail,
				container, false);

		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.textmessage_detail))
					.setText(mItem.toString());
		}

		return rootView;
	}
}
