package com.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class TextMessageListActivity extends FragmentActivity implements
		TextMessageListFragment.Callbacks {

	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_textmessage_list);

		if (findViewById(R.id.textmessage_detail_container) != null) {
			mTwoPane = true;

			((TextMessageListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.textmessage_list))
					.setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putString(TextMessageDetailFragment.ARG_ITEM_ID, id);
			TextMessageDetailFragment fragment = new TextMessageDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.textmessage_detail_container, fragment)
					.commit();

		} else {
			Intent detailIntent = new Intent(this,
					TextMessageDetailActivity.class);
			detailIntent.putExtra(TextMessageDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
