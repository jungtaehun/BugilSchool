package bugil.bada.bugilapp.schedule;

import bugil.bada.bugilapp.schedule.Schedule.Item;

public class EntryItem implements Item {

	public final String mTitle;
	public final String mSummary;
	public final boolean isHoliday;

	public EntryItem(String mTitle, String mSummary) {
		this.mTitle = mTitle;
		this.mSummary = mSummary;
		this.isHoliday = false;
	}

	public EntryItem(String mTitle, String mSummary, boolean isHoliday) {
		this.mTitle = mTitle;
		this.mSummary = mSummary;
		this.isHoliday = isHoliday;
	}

	@Override
	public boolean isSection() {
		return false;
	}

}

class SectionItem implements Item {

	private final String mTitle;

	public SectionItem(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getTitle() {
		return mTitle;
	}

	@Override
	public boolean isSection() {
		return true;
	}

}