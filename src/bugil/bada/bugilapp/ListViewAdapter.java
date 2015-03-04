package bugil.bada.bugilapp;

import java.util.ArrayList;

import bugil.bada.bugilapp.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ViewHolder {
	public ImageView mListImage;

	public TextView mListLargeText;

	public TextView mListMediumText;
}

public class ListViewAdapter extends BaseAdapter {
	private Context mContext = null;
	public ArrayList<ListData> mListData = new ArrayList<ListData>();

	public ListViewAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	public void addItem(Drawable icon, String mTitle, String mText) {
		ListData addInfo = new ListData();
		addInfo.mIcon = icon;
		addInfo.mTitle = mTitle;
		addInfo.mText = mText;

		mListData.add(addInfo);
	}

	@Override
	public int getCount() {
		return mListData.size();
	}

	@Override
	public Object getItem(int position) {
		return mListData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();

			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.main_list_item, null);

			holder.mListImage = (ImageView) convertView
					.findViewById(R.id.mListImage);
			holder.mListLargeText = (TextView) convertView
					.findViewById(R.id.mListLargeText);
			holder.mListMediumText = (TextView) convertView
					.findViewById(R.id.mListMediumText);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ListData mData = mListData.get(position);

		if (mData.mIcon == null) {
			holder.mListImage.setImageDrawable(null);
			holder.mListImage.setVisibility(View.GONE);

		} else {
			holder.mListImage.setImageDrawable(mData.mIcon);
			holder.mListImage.setVisibility(View.VISIBLE);
		}

		holder.mListLargeText.setText(mData.mTitle);
		holder.mListMediumText.setText(mData.mText);

		return convertView;
	}
}

class ListData {
	/**
	 * 리스트 정보를 담고 있을 객체 생성
	 */

	public Drawable mIcon;
	public String mTitle;
	public String mText;
}