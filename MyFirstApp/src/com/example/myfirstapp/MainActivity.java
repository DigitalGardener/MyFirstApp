package com.example.myfirstapp;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState != null)
			if (savedInstanceState.containsKey("messageId"))
				messageId = savedInstanceState.getInt("messageId");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("messageId", messageId);
		super.onSaveInstanceState(outState);
	}

	private int messageId = 0;

	public void onClick(View view) {
		CharSequence dateTime = "(at " + new Date().toString() + ")";

		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText("Button clicked! " + dateTime);

		Toast.makeText(this, "This is a big toast! " + dateTime, Toast.LENGTH_SHORT)
				.show();

		@SuppressWarnings("deprecation")
		Notification notification = new Notification.Builder(this)
				.setContentTitle("My Notification")
				.setContentText("Notification's text! " + dateTime + ", message ID: " + messageId)
				.setSmallIcon(R.drawable.ic_launcher).getNotification();
		notification.defaults = Notification.DEFAULT_SOUND;
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(messageId++, notification);
	}
	
	public void onClickStartBlankActivity(View view){
		
		Intent activityItent = new Intent(this, BlankActivity.class);
		if(activityItent.resolveActivity(getPackageManager()) != null)
			startActivity(activityItent);
		
	}
}
