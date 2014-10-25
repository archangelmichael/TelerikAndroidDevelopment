package com.addressbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayContact extends ActionBarActivity {

	int from_Where_I_Am_Coming = 0;
	private DBHelper mydatabase;
	TextView name;
	TextView phone;
	TextView email;
	TextView street;
	TextView place;
	int id_To_Update = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_contact);
		name = (TextView) findViewById(R.id.editTextName);
		phone = (TextView) findViewById(R.id.editTextPhone);
		email = (TextView) findViewById(R.id.editTextStreet);
		street = (TextView) findViewById(R.id.editTextEmail);
		place = (TextView) findViewById(R.id.editTextCity);

		mydatabase = new DBHelper(this);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int Value = extras.getInt("id");
			if (Value > 0) {
				// means this is the view part not the add contact part.
				Contact contact = mydatabase.getContactData(Value);
				
				Button b = (Button) findViewById(R.id.button1);
				b.setVisibility(View.INVISIBLE);

				name.setText(contact.getName());
				name.setFocusable(false);
				name.setClickable(false);

				phone.setText(contact.getPhone());
				phone.setFocusable(false);
				phone.setClickable(false);

				email.setText(contact.getEmail());
				email.setFocusable(false);
				email.setClickable(false);

				street.setText(contact.getStreet());
				street.setFocusable(false);
				street.setClickable(false);

				place.setText(contact.getPlace());
				place.setFocusable(false);
				place.setClickable(false);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int Value = extras.getInt("id");
			if (Value > 0) {
				getMenuInflater().inflate(R.menu.display_contact, menu);
			} else {
				getMenuInflater().inflate(R.menu.main, menu);
			}
		}
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.Edit_Contact:
			Button b = (Button) findViewById(R.id.button1);
			b.setVisibility(View.VISIBLE);
			name.setEnabled(true);
			name.setFocusableInTouchMode(true);
			name.setClickable(true);

			phone.setEnabled(true);
			phone.setFocusableInTouchMode(true);
			phone.setClickable(true);

			email.setEnabled(true);
			email.setFocusableInTouchMode(true);
			email.setClickable(true);

			street.setEnabled(true);
			street.setFocusableInTouchMode(true);
			street.setClickable(true);

			place.setEnabled(true);
			place.setFocusableInTouchMode(true);
			place.setClickable(true);

			return true;
		case R.id.Delete_Contact:

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.deleteContact)
					.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								mydatabase.deleteContact(id_To_Update);
								Toast.makeText(getApplicationContext(),	"Deleted Successfully", Toast.LENGTH_SHORT).show();
								Intent intent = new Intent( getApplicationContext(), com.addressbook.MainActivity.class);
								startActivity(intent);
							}
						})
					.setNegativeButton(R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Toast.makeText(getApplicationContext(),	"Contact Saved", Toast.LENGTH_SHORT).show();
							}
						});
			AlertDialog d = builder.create();
			d.setTitle("Are you sure");
			d.show();

			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}

	public void run(View view) {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int Value = extras.getInt("id");
			Contact contact = new Contact(
					name.getText().toString(),
					phone.getText().toString(), 
					email.getText().toString(), 
					street.getText().toString(), 
					place.getText().toString());
			
			if (Value > 0) {
				if (mydatabase.updateContact(id_To_Update, contact)) {
					Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(getApplicationContext(),	com.addressbook.MainActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
				}
			} else {
				if (mydatabase.insertContact(contact)) {
					Toast.makeText(getApplicationContext(), "done",	Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "not done",	Toast.LENGTH_SHORT).show();
				}
				
				Intent intent = new Intent(getApplicationContext(),	com.addressbook.MainActivity.class);
				startActivity(intent);
			}
		}
	}
}
