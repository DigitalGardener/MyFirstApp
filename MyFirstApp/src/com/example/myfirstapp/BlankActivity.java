package com.example.myfirstapp;

import java.io.File;
import java.net.URI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BlankActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blank);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.blank, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private ActivityType selectedActivityType = ActivityType.NONE;
	public void onClickSelecteActivityRadioButton(View view) {
		
		if (((RadioButton) view).isChecked()) {
			
			
			switch (view.getId()) {
			
				case R.id.radio0:
					selectedActivityType = ActivityType.CAMERA;
					Toast.makeText(this, "Launch 'Camera' activty selected", Toast.LENGTH_SHORT).show();
					break;
					
				case R.id.radio1:
					selectedActivityType = ActivityType.CONTACTS;
					Toast.makeText(this, "Launch 'Contacts' activty selected", Toast.LENGTH_SHORT).show();
					break;
					
				case R.id.radio2:
					selectedActivityType = ActivityType.EMAIL;
					Toast.makeText(this, "Launch 'E-Mail' activty selected", Toast.LENGTH_SHORT).show();
					break;
			}
		}
	}
	
	public void onClickLaunchSelectedActvity(View view){
		switch (selectedActivityType) {
		case CAMERA:
			
			capturePhoto();
			break;

		case CONTACTS:
			
			selectContact();
			break;

		case EMAIL:

			Toast.makeText(this, "Launch 'Mail' activity has not been implemented", Toast.LENGTH_SHORT).show();
			break;

		case NONE:
			
			Toast.makeText(this, "Please select an actvity before clicking launch", Toast.LENGTH_SHORT).show();
			break;

		default:
			
			Toast.makeText(this, "selectedActivityType has invalid value", Toast.LENGTH_SHORT).show();
			break;
		}
	}
	
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	private static final int REQUEST_SELECT_CONTACT = 2;
	
	private void capturePhoto()
	{
		Uri imageFileUri = Uri.fromFile(new File(getFilesDir(), "MyFirstAppCameraImage"));
		
		Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		
		startActivityForResult(photoIntent, REQUEST_IMAGE_CAPTURE);
	}

	private void selectContact()
	{
		Intent contactIntent = new Intent(Intent.ACTION_PICK);
		contactIntent.setType(ContactsContract.Contacts.CONTENT_TYPE);
		if(contactIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(contactIntent, REQUEST_SELECT_CONTACT);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Uri contact = null;;
		if( requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
		{
			Bitmap image = data.getParcelableExtra("data");
		}
		
		if (requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK) {
			contact = data.getData();
		}
		String contactProperty = contact.toString();
	}
}
