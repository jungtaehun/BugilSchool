Android Meal Library with Mir(whdghks913)
=======================================
VERSION 5 (UPDATE 20140906)
----------------------------



사용하는 라이브러리
===================
- jericho-html-3.3.jar



사용 방법 안내
==============
static으로 선언하여 어디서든 바로 사용가능합니다
MealLibrary.(사용할 메소드 이름)으로 급식을 가져올수 있으며 자세한 사용법은 아래에 있습니다
AsyncTask를 사용하여 라이브러리를 사용해 주세요, 어떻게 쓰는지 모르시면 원당고 앱을 참고해 주세요
개발자는 인터넷이 연결되지 않았을때 라이브러리를 호출하지 않도록 코드를 구성해야 합니다



업데이트 안내
=============
- 나이스 홈페이지 구조 변경에 따라 새로운 파싱 방법 사용
- getDateNew(), getKcalNew(), getMealNew(), getPeopleNew() 사용가능
- 기존 메소드인 getDate(), getKcal(), getMeal(), getMonthMeal(), getPeople()은 Deprecated됨



오픈 소스 안내
==============
- 원본 소스 : http://blog.naver.com/rimal
- ITcraft's Github Project의 오픈소스 : https://github.com/mhkim4886/OkdongMidSch/blob/master/src/toast/library/meal/MealLibrary.java
- 원본 라이센스 : Public Open Library
- 수정 : 2014-03-16 ~
- 업로드 : https://bitbucket.org/whdghks913/wondanghighschool (src/toast/library/meal/MealLibrary.java)



How To Use?
=============
Deprecated API
==============

MealLibrary.getDate()
--------------------

- MealLibrary.getDate(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode)
- MealLibrary.getDate(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode, String schYmd)
- MealLibrary.getDate(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode, String year, String month, String day)

일주일치 날짜를 반환합니다
schYmd 또는 year, month, day를 입력하지 않을경우, 현재 서버 날짜를 기준으로 급식을 가져옵니다
schYmd 또는 year, month, day를 입력해서 원하는 날짜의 한주 급식을 가져올수도 있습니다
원당고 앱에서는 year, month, day를 이용해서 지난주, 다음주, 날짜를 선택해서 급식을 가져올수 있습니다
String[]에서 [0]에는 일요일의 정보가, [6]에는 토요일의 정보가 담기며, 총 index 길이는 0~6 입니다



MealLibrary.getMeal()
-------------------

- MealLibrary.getMeal(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode)
- MealLibrary.getMeal(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode, String schYmd)
- MealLibrary.getMeal(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode, String year, String month, String day)

일주일치 급식을 반환합니다
급식이 없을경우 " " 또는 "" 또는 null으로 반환하며, 자세한 설명은 getDate()와 같습니다




MealLibrary.getMonthMeal()
-------------------------

- MealLibrary.getMonthMeal(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode, String schYm)
- MealLibrary.getMonthMeal(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode, String year, String month)

한달 급식을 반환합니다
getMeal()에서 사용하는 schYm 파라메터와 getMonthMeal()에서 사용하는 schYm파라메터는 다른 형식을 사용해야 합니다 (아래 참조)
String[]의 길이는 한달 날짜 길이와 같으며, 2월은 윤년을 위해 길이가 29입니다



MealLibrary.getKcal()
--------------------

- MealLibrary.getKcal(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode)
- MealLibrary.getKcal(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode, String schYmd)
- MealLibrary.getKcal(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode, String year, String month, String day)

일주일치 급식의 칼로리를 가져옵니다
위와 같습니다



MealLibrary.getPeople()
----------------------

- MealLibrary.getPeople(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode)
- MealLibrary.getPeople(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode, String schYmd)
- MealLibrary.getPeople(String CountryCode, String schulCode, String schulCrseScCode, String schulKndScCode, String schMmealScCode, String year, String month, String day)

일주일치 급식 인원을 반환합니다
위와 같습니다



New API
========

MealLibrary.getDateNew()
-----------------------

- MealLibrary.getDateNew(CountryCode, schulCode, schulCrseScCode, schulKndScCode, schMmealScCode)
- MealLibrary.getDateNew(CountryCode, schulCode, schulCrseScCode, schulKndScCode, schMmealScCode, year, month, day)



MealLibrary.getKcalNew()
-----------------------

- MealLibrary.getKcalNew(CountryCode, schulCode, schulCrseScCode, schulKndScCode, schMmealScCode)
- MealLibrary.getKcalNew(CountryCode, schulCode, schulCrseScCode, schulKndScCode, schMmealScCode, year, month, day)



MealLibrary.getMealNew()
-----------------------

- MealLibrary.getMealNew(CountryCode, schulCode, schulCrseScCode, schulKndScCode, schMmealScCode)
- MealLibrary.getMealNew(CountryCode, schulCode, schulCrseScCode, schulKndScCode, schMmealScCode, year, month, day)



MealLibrary.getPeopleNew()
-----------------------

- MealLibrary.getPeopleNew(CountryCode, schulCode, schulCrseScCode, schulKndScCode, schMmealScCode)
- MealLibrary.getPeopleNew(CountryCode, schulCode, schulCrseScCode, schulKndScCode, schMmealScCode, year, month, day)



변수 설명
=========

CountryCode
------------
- 학교 교육청 코드, nice홈페이지 도메인과 같습니다
- EX) 인천 : ice.go.kr


schulCode
----------
- 학교 고유 나이스 코드
- EX) 인천의 학교 코드 검색 : http://hes.ice.go.kr/sts_sci_si00_001.do (E10000xxxx)
- 참조 : 중학교 나이스 코드 검색 : http://me2.do/xAY6Zij1
- 고등학교 나이스 코드 검색 : http://me2.do/G22fDh8l


schulCrseScCode
----------------
- 학교 분류
- "1" : 병설유치원
- "2" : 초등학교
- "3" : 중학교
- "4" : 고등학교
- 분류번호랑 종류랑 안맞으면 반환이 안됩니다


schulKndScCode
----------------
- 학교 종류
- "01" : 유치원
- "02" : 초등학교
- "03" : 중학교
- "04" : 고등학교


schMmealScCode
----------------
- 반환할 식사 값
- 조식 : "1"
- 중식 : "2"
- 석식 : "3"


schYmd
-------
- 원하는 날짜의 급식 정보를 얻기 위해 필요합니다
- String 형식 : 년.월.일
- EX) "2014.03.16"
- 새로운 New메소드에서는 schYmd을 지원하지 않고 year, month, day만 지원합니다


schYm
------
- 원하는 달의 급식 정보를 얻기 위해 필요합니다
- String 형식 : 년.월
- EX) "2014.03"
- 새로운 New메소드에서는 schYm 지원하지 않고 year, month, day만 지원합니다


year, month, day
---------------
- schYmd와 schYm의 정보를 세분화 해서 각각 정보를 넘겨줄때 사용합니다
- EX) year = "2014", month = "03", day = "16"