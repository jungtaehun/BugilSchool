package toast.library.meal;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

/*
 * VERSION 5
 * UPDATE 20140906
 * 
 * @author Mir(whdghks913)
 * 
 * Use : getDateNew, getKcalNew, getMealNew, getPeopleNew
 * Deprecated : getDate, getKcal, getMeal, getMonthMeal, getPeople
 */
public class MealLibrary {
	private static Source mSource;

	/**
	 * getDate
	 */
	@Deprecated
	public static String[] getDate(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode, String schMmealScCode) {
		String[] date = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode;

		return getDateSub(date, url);
	}

	@Deprecated
	public static String[] getDate(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String schYmd) {
		String[] date = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + schYmd;

		return getDateSub(date, url);
	}

	@Deprecated
	public static String[] getDate(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String year, String month, String day) {
		String[] date = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + year + "." + month + "." + day;

		return getDateSub(date, url);
	}

	/**
	 * getDateNew
	 */
	public static String[] getDateNew(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode, String schMmealScCode) {

		String[] date = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode;

		return getDateNewSub(date, url);
	}

	public static String[] getDateNew(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String year, String month, String day) {

		String[] date = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + year + "." + month + "." + day;

		return getDateNewSub(date, url);
	}

	private static String[] getDateNewSub(String[] date, String url) {
		try {
			mSource = new Source(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mSource.fullSequentialParse();
		List<?> table = mSource.getAllElements("table");

		for (int i = 0; i < table.size(); i++) {
			if (((Element) table.get(i)).getAttributeValue("class").equals(
					"tbl_type3")) {
				List<?> tr = ((Element) table.get(i)).getAllElements("tr");
				List<?> th = ((Element) tr.get(0)).getAllElements("th");

				for (int j = 0; j < 7; j++) {
					date[j] = ((Element) th.get(j + 1)).getContent().toString();
				}

				break;
			}
		}

		return date;
	}

	@Deprecated
	private static String[] getDateSub(String[] date, String url) {
		try {
			mSource = new Source(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mSource.fullSequentialParse();
		List<?> table = mSource.getAllElements("table");

		for (int i = 0; i < table.size(); i++) {
			if (((Element) table.get(i)).getAttributeValue("class").equals(
					"tableType6")) {
				List<?> tr = ((Element) table.get(i)).getAllElements("tr");
				List<?> th = ((Element) tr.get(0)).getAllElements("th");

				for (int j = 0; j < 7; j++) {
					date[j] = ((Element) th.get(j + 1)).getContent().toString();
				}

				break;
			}

		}

		return date;
	}

	/**
	 * getKcal
	 */
	@Deprecated
	public static String[] getKcal(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode, String schMmealScCode) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode;

		return getKcalSub(content, url);
	}

	@Deprecated
	public static String[] getKcal(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String schYmd) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + schYmd;

		return getKcalSub(content, url);
	}

	@Deprecated
	public static String[] getKcal(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String year, String month, String day) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + year + "." + month + "." + day;

		return getKcalSub(content, url);
	}

	/**
	 * getKcal
	 */
	public static String[] getKcalNew(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode, String schMmealScCode) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode;

		return getKcalSubNew(content, url);
	}

	public static String[] getKcalNew(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String year, String month, String day) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + year + "." + month + "." + day;

		return getKcalSubNew(content, url);
	}

	@Deprecated
	private static String[] getKcalSub(String[] content, String url) {
		try {
			mSource = new Source(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mSource.fullSequentialParse();
		List<?> table = mSource.getAllElements("table");
		for (int i = 0; i < table.size(); i++) {

			if (((Element) table.get(i)).getAttributeValue("class").equals(
					"tableType6")) {
				List<?> tbody = ((Element) table.get(i))
						.getAllElements("tbody");
				List<?> __tr = ((Element) tbody.get(0)).getAllElements("tr");
				List<?> __th = ((Element) __tr.get(16)).getAllElements("th");

				if (((Element) __th.get(0)).getContent().toString()
						.equals("에너지(kcal)    ")) {
					List<?> td = ((Element) __tr.get(16)).getAllElements("td");

					for (int j = 0; j < 7; j++) {
						content[j] = ((Element) td.get(j)).getContent()
								.toString();
					}

					break;
				}

				for (int index = 0; index < content.length; index++) {
					content[index] = null;
				}

				break;
			}
		}

		return content;
	}

	private static String[] getKcalSubNew(String[] content, String url) {
		try {
			mSource = new Source(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mSource.fullSequentialParse();
		List<?> table = mSource.getAllElements("table");

		for (int i = 0; i < table.size(); i++) {
			if (((Element) table.get(i)).getAttributeValue("class").equals(
					"tbl_type3")) {
				List<?> tbody = ((Element) table.get(i))
						.getAllElements("tbody");
				List<?> __tr = ((Element) tbody.get(0)).getAllElements("tr");
				List<?> __th = ((Element) __tr.get(16)).getAllElements("th");

				if (((Element) __th.get(0)).getContent().toString()
						.equals("에너지(kcal)")) {
					List<?> td = ((Element) __tr.get(16)).getAllElements("td");

					for (int j = 0; j < 7; j++) {
						content[j] = ((Element) td.get(j)).getContent()
								.toString();
					}

					break;
				}

				for (int index = 0; index < content.length; index++) {
					content[index] = null;
				}

				break;
			}
		}

		return content;
	}

	/**
	 * getMeal
	 */
	@Deprecated
	public static String[] getMeal(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode, String schMmealScCode) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode;

		return getMealSub(content, url);
	}

	@Deprecated
	public static String[] getMeal(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String schYmd) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + schYmd;

		return getMealSub(content, url);
	}

	@Deprecated
	public static String[] getMeal(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String year, String month, String day) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + year + "." + month + "." + day;

		return getMealSub(content, url);
	}

	public static String[] getMealNew(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode, String schMmealScCode) {

		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode;

		return getMealNewSub(content, url);
	}

	/**
	 * getMealNew
	 */
	public static String[] getMealNew(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String year, String month, String day) {

		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + year + "." + month + "." + day;

		return getMealNewSub(content, url);
	}

	private static String[] getMealNewSub(String[] content, String url) {
		try {
			mSource = new Source(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mSource.fullSequentialParse();
		List<?> table = mSource.getAllElements("table");

		for (int i = 0; i < table.size(); i++) {
			if (((Element) table.get(i)).getAttributeValue("class").equals(
					"tbl_type3")) {
				List<?> tbody = ((Element) table.get(i))
						.getAllElements("tbody");
				List<?> tr = ((Element) tbody.get(0)).getAllElements("tr");
				List<?> title = ((Element) tr.get(2)).getAllElements("th");

				if (((Element) title.get(0)).getContent().toString()
						.equals("식재료")) {
					List<?> tdMeal = ((Element) tr.get(1)).getAllElements("td");

					for (int j = 0; j < 7; j++) {
						content[j] = ((Element) tdMeal.get(j)).getContent()
								.toString();
						content[j] = content[j].replace("<br />", "\n");
					}

					break;
				}

				for (int index = 0; index < content.length; index++) {
					content[index] = null;
				}

				break;
			}
		}

		return content;
	}

	@Deprecated
	private static String[] getMealSub(String[] content, String url) {
		try {
			mSource = new Source(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mSource.fullSequentialParse();
		List<?> table = mSource.getAllElements("table");

		for (int i = 0; i < table.size(); i++) {
			if (((Element) table.get(i)).getAttributeValue("class").equals(
					"tableType6")) {
				List<?> tbody = ((Element) table.get(i))
						.getAllElements("tbody");
				List<?> tr = ((Element) tbody.get(0)).getAllElements("tr");
				List<?> title = ((Element) tr.get(2)).getAllElements("th");

				if (((Element) title.get(0)).getContent().toString()
						.equals("식재료")) {
					List<?> tdMeal = ((Element) tr.get(1)).getAllElements("td");

					for (int j = 0; j < 7; j++) {
						content[j] = ((Element) tdMeal.get(j)).getContent()
								.toString();
						content[j] = content[j].replace("<br />", "\n");
					}

					break;
				}

				for (int index = 0; index < content.length; index++) {
					content[index] = null;
				}

				break;
			}
		}
		return content;
	}

	private static int getMonthDays(String CalenderMonth) {
		switch (Integer.parseInt(CalenderMonth)) {
		case 1:
			return 31;
		case 2:
			return 29;
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		}
		return 31;
	}

	/**
	 * getMonthMeal
	 */
	@Deprecated
	public static String[] getMonthMeal(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String schYm) {

		String[] content = null;
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md00_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYm=" + schYm;

		return getMonthMealSub(content, schMmealScCode, url, "1");
	}

	@Deprecated
	public static String[] getMonthMeal(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String year, String month) {

		String[] content = null;
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md00_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYm=" + year + "." + month;

		return getMonthMealSub(content, schMmealScCode, url, month);
	}

	@Deprecated
	private static String[] getMonthMealSub(String[] content,
			String schMmealScCode, String url, String month) {
		int dayChecker = 0;

		try {
			mSource = new Source(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mSource.fullSequentialParse();
		List<?> table = mSource.getAllElements("table");
		for (int i = 0; i < table.size(); i++) {

			String tableType = ((Element) table.get(i))
					.getAttributeValue("class");

			if ("tableType2".equals(tableType)) {

				List<?> tbody = ((Element) table.get(i))
						.getAllElements("tbody");

				for (int ii = 0; ii < tbody.size(); ii++) {
					List<?> tr = ((Element) tbody.get(ii)).getAllElements("tr");
					content = new String[getMonthDays(month)];

					for (int a = 0; a < tr.size(); a++) {
						List<?> title = ((Element) tr.get(a))
								.getAllElements("td");

						for (int iiii = 0; iiii < title.size(); iiii++) {
							String Meal = ((Element) title.get(iiii))
									.getContent().toString();
							// iiii = 날짜

							if (isMealCheck(Meal)) {
								Meal = Meal.replace("<br />", "\n").trim();

								int morning = Meal.indexOf("[조식]");
								int lunch = Meal.indexOf("[중식]");
								int night = Meal.indexOf("[석식]");

								// schMmealScCode == 1 : 아침만
								// schMmealScCode == 2 : 점심만
								// schMmealScCode == 3 : 저녁만

								if ("1".equals(schMmealScCode)) {
									if (morning != -1 && lunch != -1)
										Meal = Meal.substring(morning + 4,
												lunch);
									else if (morning != -1)
										Meal = Meal.substring(morning + 4);
									else if (morning == -1)
										Meal = null;

								} else if ("2".equals(schMmealScCode)) {
									if (lunch != -1 && night != -1)
										Meal = Meal.substring(lunch + 4, night);
									else if (lunch != -1)
										Meal = Meal.substring(lunch + 4);
									else if (lunch == -1)
										Meal = null;

								} else if ("3".equals(schMmealScCode)) {
									if (night != -1)
										Meal = Meal.substring(night + 4);
									else if (night == -1)
										Meal = null;
								}
								content[dayChecker++] = Meal;
							}
						}
					}
				}

				break;
			}
		}

		return content;
	}

	/**
	 * getPeople
	 */
	@Deprecated
	public static String[] getPeople(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode, String schMmealScCode) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode;

		return getPeopleSub(content, url);
	}

	@Deprecated
	public static String[] getPeople(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String schYmd) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + schYmd;

		return getPeopleSub(content, url);
	}

	@Deprecated
	public static String[] getPeople(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String year, String month, String day) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + year + "." + month + "." + day;

		return getPeopleSub(content, url);
	}

	/**
	 * getPeople
	 */
	public static String[] getPeopleNew(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode, String schMmealScCode) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode;

		return getPeopleSubNew(content, url);
	}

	public static String[] getPeopleNew(String CountryCode, String schulCode,
			String schulCrseScCode, String schulKndScCode,
			String schMmealScCode, String year, String month, String day) {
		String[] content = new String[7];
		String url = "http://hes." + CountryCode
				+ "/sts_sci_md01_001.do?schulCode=" + schulCode
				+ "&schulCrseScCode=" + schulCrseScCode + "&schulKndScCode="
				+ schulKndScCode + "&schMmealScCode=" + schMmealScCode
				+ "&schYmd=" + year + "." + month + "." + day;

		return getPeopleSub(content, url);
	}

	@Deprecated
	private static String[] getPeopleSub(String[] content, String url) {
		try {
			mSource = new Source(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mSource.fullSequentialParse();
		List<?> table = mSource.getAllElements("table");

		for (int i = 0; i < table.size(); i++) {
			if (((Element) table.get(i)).getAttributeValue("class").equals(
					"tableType6")) {
				List<?> tbody = ((Element) table.get(i))
						.getAllElements("tbody");
				List<?> __tr = ((Element) tbody.get(0)).getAllElements("tr");
				List<?> __th = ((Element) __tr.get(0)).getAllElements("th");

				if (((Element) __th.get(0)).getContent().toString()
						.equals("급식인원")) {
					List<?> td = ((Element) __tr.get(0)).getAllElements("td");

					for (int j = 0; j < 7; j++) {
						content[j] = ((Element) td.get(j)).getContent()
								.toString();
					}

					break;
				}

				for (int index = 0; index < content.length; index++) {
					content[index] = null;
				}

				break;
			}
		}

		return content;
	}

	private static String[] getPeopleSubNew(String[] content, String url) {
		try {
			mSource = new Source(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mSource.fullSequentialParse();
		List<?> table = mSource.getAllElements("table");

		for (int i = 0; i < table.size(); i++) {
			if (((Element) table.get(i)).getAttributeValue("class").equals(
					"tbl_type3")) {
				List<?> tbody = ((Element) table.get(i))
						.getAllElements("tbody");
				List<?> __tr = ((Element) tbody.get(0)).getAllElements("tr");
				List<?> __th = ((Element) __tr.get(0)).getAllElements("th");

				if (((Element) __th.get(0)).getContent().toString()
						.equals("급식인원")) {
					List<?> td = ((Element) __tr.get(0)).getAllElements("td");

					for (int j = 0; j < 7; j++) {
						content[j] = ((Element) td.get(j)).getContent()
								.toString();
					}

					break;
				}

				for (int index = 0; index < content.length; index++) {
					content[index] = null;
				}

				break;
			}
		}

		return content;
	}

	private static boolean isMealCheck(String meal) {
		if ("".equals(meal) || " ".equals(meal) || meal == null)
			return false;
		else
			return true;
	}
}