package bugil.bada.bugilapp;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPOIItem.CalloutBalloonButtonType;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;




public class Road extends SherlockActivity implements MapView.MapViewEventListener, MapView.POIItemEventListener{
	private static final MapPoint DEFAULT_MARKER_POINT = MapPoint.mapPointWithGeoCoord(36.831905, 127.158167);
	public static final String DAUM_MAPS_ANDROID_APP_API_KEY = "1c98f65d4a8dbb86836d6a4d80672414";
	private MapView mMapView;
	private MapPOIItem mDefaultMarker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road);
        mMapView = (MapView) findViewById(R.id.bugilmaps);
        mMapView.setDaumMapApiKey(DAUM_MAPS_ANDROID_APP_API_KEY);
        mMapView.setMapViewEventListener(this);
        mMapView.setPOIItemEventListener(this);
        
        mDefaultMarker = new MapPOIItem();
        String name = "북일고등학교";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(0);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mMapView.addPOIItem(mDefaultMarker);
        mMapView.selectPOIItem(mDefaultMarker, true);
    	mMapView.setMapCenterPoint(DEFAULT_MARKER_POINT, false);

        
        Button btn = (Button)findViewById(R.id.maps);
        //버튼 클릭시 위도, 경도정보를 인텐트시켜 다른 지도 어플로 보내기
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                double latitude = 36.831905;
                double longitude = 127.158167;
                String pos = String.format("geo:%f,%f?z=16", latitude, longitude);
                Uri uri = Uri.parse(pos);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	getSupportMenuInflater().inflate(R.menu.road, menu);
        return true;
    }


	@Override
	@Deprecated
	public void onCalloutBalloonOfPOIItemTouched(MapView arg0, MapPOIItem arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onCalloutBalloonOfPOIItemTouched(MapView arg0, MapPOIItem arg1,
			CalloutBalloonButtonType arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDraggablePOIItemMoved(MapView arg0, MapPOIItem arg1,
			MapPoint arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPOIItemSelected(MapView arg0, MapPOIItem arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMapViewCenterPointMoved(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMapViewDoubleTapped(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMapViewDragEnded(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMapViewDragStarted(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMapViewInitialized(MapView arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMapViewLongPressed(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMapViewMoveFinished(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMapViewSingleTapped(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMapViewZoomLevelChanged(MapView arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
    
}
