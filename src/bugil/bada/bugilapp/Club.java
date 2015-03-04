package bugil.bada.bugilapp;

import bugil.bada.bugilapp.R;

import java.util.ArrayList;
import java.util.HashMap;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuInflater;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SimpleAdapter;



public class Club extends SherlockListActivity {
HashMap<String,String> item;
ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        //item에 값 넣기
		item = new HashMap<String,String>();
	    item.put("title","Bada(앱개발동아리)");
	    item.put("intro", "JAVA언어와 XML을 이용한 안드로이드 앱 개발 등 프로그래밍을 통해 논리적사고를 신장");
		list.add(item);
        item = new HashMap<String,String>();
        item.put("title","BTTC");
		item.put("intro", "탁구를 통한 집중력향상 및 스트레스 해소, 건강유지가 목적으로 운영.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","북일 오케스트라");
		item.put("intro", "개인연습, 관현악합주연습, 북일 축제공연, 노인 및 소외계층을 대상으로 찾아가는 음악회 개최.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","UDS");
		item.put("intro", "물리부, 화학부, 생명과학부로 구성되어 있으며 각 부서의 부장과 그 분야를 좋아하는 학생들이 부를 이루고 있고 부에서 2명씩 뽑아 다시 작은 융합조를 만들어 한 가지 주제를 정해 그 주제에 대해 물리적, 화학적, 생명과학적으로 탐구를 진행하고 발표하는 활동을 함. 융합과학 활동을 통해 한 주제에 대한 물리적, 화학적, 생명과학적 사고력을 함양.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","가나다");
		item.put("intro", "가나다는 출판동아리로, 각자 관심있는 분야에 대해 주제를 하나 잡아서 자신만의 책을 만들어 출판하는 것을 목표로 함.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","MES");
		item.put("intro", "의학과 생명에 관심있는 학생들이 모여 진로에 도움이 될 만한 실험과 견학을 주 목적으로 하기 위해 조직됨.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","ELSE");
		item.put("intro", "법/ 경제 학술 동아리. Tesset 및 경시대회, 모의 법정 준비.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","RED");
		item.put("intro", "환경 문제에 대한 지식습득, 천연 방향제 만들기, 환경 UCC 제작, 환경운동 참여.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","Highway");
		item.put("intro", "교내토론대회, 지역학술 동아리 연합 조직활동, 사회과 관련 학술활동, 진로탐색활동.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","북일사회동아리(Highway)");
		item.put("intro", "교내토론대회, 지역학술동아리 연합조직활동, 사회과 관련 학술활동, 진로탐색활동");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","AIR");
		item.put("intro", "일상생활에서 흔히 사용하는 물건들을 창의적인 생각으로 변화시키는 발명을 통해 창의력 향상을 도모하고 발명 아이디어를 직접 구상하고 모형을 만들어 제작하는 과정으로 진행.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","신노리");
		item.put("intro", "풍물 동아리. '신나게 노는 우리들'의 줄임말. 교내외 풍물 공연, 재능 기부 봉사활동 등을 함.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","SYNERGY");
		item.put("intro", "과학(물,화,생,지)의 융합을 통한 통합적 사고력을 바탕으로 사회 속에서 시너지 효과를 내겠다는 포부를 가지고 시작된 것으로, 의·과학분야로 진출하고자 하는 학생들이 모여 생각을 공유하고, 직접 실험을 설계하고 탐구, 수행함으로써 살아있는 과학을 접하는 동아리.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","FOCUS");
		item.put("intro", "사진 기술의 이론 습득, 출사활동으로 작품감상 및 기법 습득.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","BEC");
		item.put("intro", "경영, 경제에 관심이 많은 학생들의 자발적인 학습 및 발표 모임.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","BRC");
		item.put("intro", "럭비는 그 어떤 스포츠보다 팀이라는 단어를 가장 확실히 나타 낼 수 있으며, 예의가 있는 스포츠로 힘의 강함과 약함 그 어떠한 제한도 없이 모두가 할 수 있는 스포츠.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","IB");
		item.put("intro", "1,2학년 학생들로 구성된 학교 홍보 동아리\n1) 교내 설명회 준비 및 진행\n2) 각종 홍보용 책자 및 동영상(UCC) 제작\n3) 입시 상담 센터 카페 관리 \n4) 학교 홍보 모델 ");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","GQ");
        item.put("intro", "개인 미술작품 제작 전시회, 미술관련 진로탐색활동, 북일축제 작품전시회");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","Amond");
		item.put("intro", "영화감상(교내 또는 교외) 후 소감 발표 및 토론, 결과물 정리");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","Astro");
        item.put("intro", "태양, 달, 행성, 성단, 성운, 은하 등 천체 관측과 천문대 견학, 천문 관련 탐구 활동, 로켓 연구 등");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","ME&RAE");
		item.put("intro", "과학분야 분야별 탐구 및 영어 보고서 쓰기");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","JMP");
		item.put("intro", "Jebal Murly Pogihajima(제발 물리 포기하지마)의 약자로 물리를 좋아하는 학생들의 물리를 좋아해달라는 강한 의지가 담겨있는 북일고 유일의 학생 물리 동아리이며 다양한 MBL 장비와 센서 등을 이용하여 정밀하고 정확한 측정으로 물리법칙과 현상을 공부하고 이러한 공부를 실력으로 쌓아 올려 과학 관련의 다양한 대회 등에 참가함.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","웨이트");
		item.put("intro", "체지방을 줄이고 근육량을 늘려 균형잡힌 신체를 통한 자신감을 키운다.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","BFC");
		item.put("intro", "축구시합, 특목고리그 출전, 전술훈련 등의 동아리 활동을 통해 일차적으로 축구와 관련된 기술이나 지식을 함양하고 나아가 기초체력관리, 협동심, 단결력, 스포츠정신 등을 배우는 전인적인 북일인 육성을 목표로 함.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","여섯줄");
		item.put("intro", "클래식 기타 동아리로서 기타연주를 배우며, 공연하는 것에 주목적을 두며 주말에는 기타연주를 통해 재능기부 봉사활동을 실천함.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","EOS");
		item.put("intro", "대중가요 부르기, 대중가요의 창법 익히기, 동아리 발표회");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","티키타카");
		item.put("intro", "풋살을 통하여 신체를 단련하고 학우들간의 협동심을 기르는 운동동아리");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","BBC");
        item.put("intro", "전국 최강의 야구실력을 자랑하는 북일고의 순수 아마추어 학생야구 동아리이며, 주 1회 점심시간을 이용해 연습하고, 월 1,2회 동아리 활동시간에 연습 및 연습경기 실시, 년 4회정도 선배들과의 친선경기나 타학교와의 친선경기 개최");
        list.add(item);
        item = new HashMap<String,String>();
        item.put("title","BIMIL");
		item.put("intro", "북일고등학교 도서부 BIMIL은 독서토론, 독서포트폴리오 제작, 소논문 작성 등의 독서 활동을 하고 있으며, 독서 활동 외에 부실로 사용하는 도서관 관리 및 운영을 돕고 있다.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","BBS");
		item.put("intro", "방송기기 다루기, UCC 공모대회 참가, 방송관련 진로탐색, 학교행사지원");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","트리커");
		item.put("intro", "춤은 입으로 구사하는 언어는 아니지만 몸으로 구사하는 언어이자 메시지이다. 춤을 출때의 즐거움과 친구들과의 소통, 서로의 감정 표현을 하고 감정을 느끼는 친구들이 모인 동아리이다. 우린 트리커에요!!!");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","국사봉");
		item.put("intro", "북일고 밴드 동아리 국사봉은 밴드 음악 연주 뿐만 아니라 재능봉사를 위한 트로트, 봄과 가을에 야외 공연을 위한 어쿠스틱 음악, 단독 콘서트 개최 등 다양한 음악적 활동을 통해 리더쉽과 팀워크 정신을 배우며 학교생활을 더욱 의미있게 만들어 간다.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","나누미");
        item.put("intro", "이 세상 모든 학생들에게 평등한 교육의 기회가 주어져야 한다.’라는 슬로건으로  재능기부형식의 교육 봉사를 진행하고 있다. 멘토링 봉사활동은 천안의 보육원인 신아원과 삼일육아원에서 진행 되고 있다.  멘토링 교육봉사는 한명의 멘토와 한명의 멘티로 구성되어 1년간 주기적으로 1:1 멘토링 활동을 진행하고 있다.  멘토가 자신의 멘티의 학업을 돌봐주는 것을 주요활동이며 단순 과외 활동이 아닌 자신의 멘티와 개인 상담, 진로 상담 등 다양한 교육활동을 하며 진정한 의미의 재능 봉사를 진행하고 있으며 또한 선플(positive comment)운동을 적극적으로 홍보하여 건전한 인터넷 문화 확산을 위해 힘쓰고 있다.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","오퓨스");
		item.put("intro", "나눔과 봉사, 협동을 통하여 따뜻한 학교 생활을 열어가는 봉사동아리.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","다태우");
		item.put("intro", "언어 및 신체 표현 기능의 강화를 통하여 자율적·창의적 학습 능력을 신장하게 하며, 연극 공연과 연극 무대를 준비하는 활동을 통하여 창의력과 개성을 신장하고, 협동 정신과 공동체 의식을 함양하게 함.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","SKT");
		item.put("intro", "수학적 지식습득, 아동센터에 수학교과학습법 봉사활동, 수학 경시대회 준비 및 참가");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","Flyer");
		item.put("intro", "각각의 농구포지션 별 개인기와 팀 전술의 습득과 함께 각종 농구대회에 참가하고 이를 통해 조화로운 팀워크의 참된 의미를 찾는 동아리.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","솔리언 또래상담");
		item.put("intro", "친구에게 다가가기, 대화하는 친구되기, 도움주는 친구되기");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","EMU HAPPY");
		item.put("intro", "영어에세이 작성 및 영자신문 발간, 신문에 올릴 에세이 작성 및 신문기사를 직접 영작하여 제작.");
		list.add(item);
		item = new HashMap<String,String>();
        item.put("title","에너자이저");
		item.put("intro", "천안 종합 운동장에서 인라인스케이트와 수영을 병행하여 체력 증진을 목적으로 운영.");
		list.add(item);

		//item에 저장했던 값을 list-Adapter에 출력<title은 text1에, intro는 text2에>
        SimpleAdapter aaa = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, 
                new String[] {"title","intro"}, 
                new int[] {android.R.id.text1, android.R.id.text2});
        setListAdapter(aaa);


        
    }
    public boolean onCreateOptionMenu(Menu menu){
    	MenuInflater Inf = getSupportMenuInflater();
    	return true;
    	}


}
