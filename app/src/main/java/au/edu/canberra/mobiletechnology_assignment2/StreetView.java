package au.edu.canberra.mobiletechnology_assignment2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Andrew on 12/04/2017.
 */

public class StreetView extends AppCompatActivity
{
    private StreetViewPanorama panorama;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getParcelableExtra("LatLng");
        final LatLng position = (LatLng)bundle.get("LatLong");

        SupportStreetViewPanoramaFragment streetViewPanoramaFragment = (SupportStreetViewPanoramaFragment) getSupportFragmentManager().findFragmentById(R.id.street_view_panorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(new OnStreetViewPanoramaReadyCallback()
        {
            @Override
            public void onStreetViewPanoramaReady(StreetViewPanorama panorama)
            {
                if(position != null)
                {
                    panorama.setPosition(position);
                }
                else
                    panorama.setPosition(new LatLng(-35.233435, 149.087161));
                StreetView.this.panorama = panorama;
            }
        });
    }

    @Override
    public void onResume()
    {
        String streetName = getIntent().getStringExtra("StreetName");
        setTitle(streetName);
        super.onResume();
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

}
