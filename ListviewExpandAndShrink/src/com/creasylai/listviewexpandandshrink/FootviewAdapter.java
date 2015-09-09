package com.creasylai.listviewexpandandshrink;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class FootviewAdapter extends BaseFootviewAdapter<StationImage> {

	private ListItemView listItemView = null;

	public final class ListItemView {
		public LinearLayout gas_station_groupon_ll;
		public TextView gpn_name;
		public TextView gpn_price;
		public TextView gpn_old_price;
	}

	public FootviewAdapter(Context mContext, ListView mListView) {
		super(mContext, mListView);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final StationImage object = (StationImage) getItem(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.lv_gas_station_detail_groupon, parent, false);
			listItemView = new ListItemView();
			creatView(convertView, listItemView);
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}
		listItemView.gpn_name.setText(object.getImg_name());
		listItemView.gpn_price.setText("￥" + object.getImg_url_l());
		listItemView.gpn_old_price.setText("￥" + object.getImg_url_s());
		listItemView.gpn_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中划线
		return convertView;
	}

	private void creatView(View rowView, ListItemView listItemView) {
		listItemView.gas_station_groupon_ll = (LinearLayout) rowView.findViewById(R.id.gas_station_groupon_ll);
		listItemView.gpn_name = (TextView) rowView.findViewById(R.id.gpn_name);
		listItemView.gpn_price = (TextView) rowView.findViewById(R.id.gpn_price);
		listItemView.gpn_old_price = (TextView) rowView.findViewById(R.id.gpn_old_price);
	}

}