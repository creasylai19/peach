package com.creasylai.listviewexpandandshrink;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<StationImage> mStationImages = new ArrayList<StationImage>();
		ListView listview = null;
		for (int i = 0; i < 5; i++) {
		    StationImage mStationImage = new StationImage();
		    mStationImage.setImg_name("第" + i + "个项目的名字");
		    mStationImage.setImg_url_l("第" + i + "个项目的长URL");
		    mStationImage.setImg_url_s("第" + i + "个项目的短URL");
		    mStationImages.add(mStationImage);
		}
		listview = (ListView) findViewById(R.id.listview);
		FootviewAdapter mFootviewAdapter = new FootviewAdapter(this, listview);
		listview.setAdapter(mFootviewAdapter);
		mFootviewAdapter.setAdapterData(mStationImages);
	}

	
}
