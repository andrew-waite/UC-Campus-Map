package au.edu.canberra.mobiletechnology_assignment2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
{

    private GoogleMap googleMap;
    private HashMap<String, Integer> mapMarkers = new HashMap<>();
    private List<Marker> allMarkers = new ArrayList<Marker>();
    private HashMap<String, String> mapMarkersWebsites = new HashMap<>();
    private HashMap<String, String> streetViewMarkers = new HashMap<>();

    private Polygon polygon;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id)
        {
            case R.id.map_terrain_normal:
                this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.map_terrain_hybrid:
                this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.map_terrain_satellite:
                this.googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.map_terrain_terrain:
                this.googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.map_terrain_none:
                this.googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;

            default: return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        this.googleMap = googleMap;

        this.googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

        //First location, this should be the center of the uni
        LatLng centerLocation = new LatLng(-35.238161, 149.084087);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLocation, 15.0f));

        /**
         * STUDENT CENTER
         */
        Marker markerStudentCenter = this.googleMap.addMarker(new MarkerOptions().position(new LatLng(-35.240035, 149.084227)).title("UC Student Center").snippet("Your gateway to access support and advice"));
        markerStudentCenter.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ask_uc));
        markerStudentCenter.setVisible(true);

        this.mapMarkers.put(markerStudentCenter.getId(), R.drawable.drawable_askuc);
        this.mapMarkersWebsites.put(markerStudentCenter.getId(), "http://www.canberra.edu.au/current-students/canberra-students/student-centre");
        this.allMarkers.add(markerStudentCenter);

        /**
         * COFFEE GROUNDS
         *
         */
        Marker markerCoffeeGrounds = this.googleMap.addMarker(new MarkerOptions().position(new LatLng(-35.239041, 149.082299)).title("Coffee Grounds").snippet("The best coffee on campus, underneath Cooper Lodge."));
        markerCoffeeGrounds.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.coffee_grounds_icon));
        markerCoffeeGrounds.setVisible(true);

        this.mapMarkers.put(markerCoffeeGrounds.getId(), R.drawable.drawable_coffegrounds);
        this.mapMarkersWebsites.put(markerCoffeeGrounds.getId(), "http://www.canberra.edu.au/on-campus/accommodation/campus-accommodation#cooper_lodge");
        this.allMarkers.add(markerCoffeeGrounds);

        /**
         * UC Library
         */

        Marker markerUCLibrary = this.googleMap.addMarker(new MarkerOptions().position(new LatLng(-35.238017, 149.082400)).title("UC Library").snippet("24 hour access for all staff and students"));
        markerUCLibrary.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.uc_library_icon));
        markerUCLibrary.setVisible(true);

        this.mapMarkers.put(markerUCLibrary.getId(), R.drawable.drawable_uc_library);
        this.mapMarkersWebsites.put(markerUCLibrary.getId(), "http://www.canberra.edu.au/library");
        this.allMarkers.add(markerUCLibrary);

        /**
         * The Hub
         */

        Marker markerTheHub = this.googleMap.addMarker(new MarkerOptions().position(new LatLng(-35.238035, 149.084227)).title("The Hub").snippet("Below Concourse level between building 1 and 8"));
        markerTheHub.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.hub_icon));
        markerTheHub.setVisible(true);

        this.mapMarkers.put(markerTheHub.getId(), R.drawable.drawable_the_hub);
        this.mapMarkersWebsites.put(markerTheHub.getId(), "https://www.canberra.edu.au/maps/buildings-directory/the-hub");
        this.allMarkers.add(markerTheHub);

        /**
         * UC GYM
         */

        Marker markerUCGym = this.googleMap.addMarker(new MarkerOptions().position(new LatLng(-35.238562, 149.087415)).title("UC Gym").snippet("Open to students, staff and the general public"));
        markerUCGym.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.uc_gym_icon));
        markerUCGym.setVisible(true);

        this.mapMarkers.put(markerUCGym.getId(), R.drawable.drawable_uc_gym);
        this.mapMarkersWebsites.put(markerUCGym.getId(), "http://www.ucunion.com.au/gym/");
        this.allMarkers.add(markerUCGym);

        /**
         * Main Parking Area
         */

        Marker markerMainParkingArea = this.googleMap.addMarker(new MarkerOptions().position(new LatLng(-35.240105, 149.088164)).title("Main Parking Area").snippet("Several Hundred Parks Available"));
        markerMainParkingArea.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.main_parking_icon));
        markerMainParkingArea.setVisible(true);

        this.mapMarkers.put(markerMainParkingArea.getId(), R.drawable.drawable_main_parking_ground);
        this.mapMarkersWebsites.put(markerMainParkingArea.getId(), "https://www.canberra.edu.au/maps/parking");
        this.allMarkers.add(markerMainParkingArea);

        /**
         * NATSEM Center
         */

        Marker markerNATSEMCenter = this.googleMap.addMarker(new MarkerOptions().position(new LatLng(-35.240669, 149.086978)).title("NATSEM Center").snippet("The National Center for Social and Economic Monitoring"));
        markerNATSEMCenter.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.natsem_center_icon));
        markerNATSEMCenter.setVisible(true);

        this.mapMarkers.put(markerNATSEMCenter.getId(), R.drawable.drawable_natsem_center);
        this.mapMarkersWebsites.put(markerNATSEMCenter.getId(), "http://www.natsem.canberra.edu.au/");
        this.allMarkers.add(markerNATSEMCenter);

        /**
         * Ginninderra Drive
         */

        Marker markerGinninderraDrive = this.googleMap.addMarker(new MarkerOptions().position(new LatLng(-35.233435, 149.087161)));
        markerGinninderraDrive.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.pegman_icon));
        markerGinninderraDrive.setVisible(true);

        this.streetViewMarkers.put(markerGinninderraDrive.getId(), "Ginninderra Drive");
        this.allMarkers.add(markerGinninderraDrive);

        /**
         * University Drive
         */

        Marker markerUniversityDrive = this.googleMap.addMarker(new MarkerOptions().position(new LatLng(-35.238472, 149.089557)));
        markerUniversityDrive.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.pegman_icon));
        markerUniversityDrive.setVisible(true);

        this.streetViewMarkers.put(markerUniversityDrive.getId(), "University Drive");
        this.allMarkers.add(markerUniversityDrive);

        this.drawOverlay();

        this.onClickListener();

        /*LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(Marker marker : this.allMarkers)
        {
            builder.include(marker.getPosition());
        }

        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.01) + 300;

        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));*/
    }

    public void onClickListener()
    {
        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng latLng)
            {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for(LatLng point : polygon.getPoints())
                {
                    builder.include(point);
                }


                LatLngBounds bounds = builder.build();

                if(bounds.contains(latLng))
                {
                    polygon.setStrokeColor(Color.argb(150, 0, 0, 0));
                    polygon.setFillColor(Color.argb(120, 0, 0, 255));
                    showToast();
                }
                else
                {
                    polygon.setStrokeColor(Color.argb(50, 0, 0, 255));
                    polygon.setFillColor(Color.argb(20, 0, 0, 255));
                }
            }
        });

        this.googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker)
            {
                if(streetViewMarkers.containsKey(marker.getId()))
                {
                    Intent intent = new Intent(MainActivity.this, StreetView.class);
                    intent.putExtra("StreetName", streetViewMarkers.get(marker.getId()));

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("LatLong", marker.getPosition());

                    intent.putExtra("LatLng", bundle);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        this.googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker marker)
            {
                if(mapMarkersWebsites.keySet().contains(marker.getId()))
                {
                    String website = mapMarkersWebsites.get(marker.getId());

                    Intent intent = new Intent(MainActivity.this, WebBrowser.class);
                    intent.putExtra("gotoWebsite", website);
                    startActivity(intent);

                }
            }
        });
    }

    public void showToast()
    {
        final Toast toast = Toast.makeText(this, "University of Canberra", Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                toast.cancel();
            }
        }, 2000); //2000 milliseconds = 2 seconds
    }

    public void drawOverlay()
    {
        PolygonOptions overlayOptions = new PolygonOptions().add(new LatLng(-35.231282, 149.080507),
                                                                 new LatLng(-35.231866, 149.083668),
                                                                 new LatLng(-35.233641, 149.086919),
                                                                 new LatLng(-35.234447, 149.089150),
                                                                 new LatLng(-35.234938, 149.091468),
                                                                 new LatLng(-35.235214, 149.091607),
                                                                 new LatLng(-35.238456, 149.090384),
                                                                 new LatLng(-35.240139, 149.090019),
                                                                 new LatLng(-35.241909, 149.090062),
                                                                 new LatLng(-35.242259, 149.088668),
                                                                 new LatLng(-35.242364, 149.080170),
                                                                 new LatLng(-35.242364, 149.078003),
                                                                 new LatLng(-35.242662, 149.076415),
                                                                 new LatLng(-35.240980, 149.074935),
                                                                 new LatLng(-35.238929, 149.076458),
                                                                 new LatLng(-35.237475, 149.077231),
                                                                 new LatLng(-35.236178, 149.077574),
                                                                 new LatLng(-35.233005, 149.079355),
                                                                 new LatLng(-35.232427, 149.079934),
                                                                 new LatLng(-35.231282, 149.080507));
        overlayOptions.strokeColor(Color.argb(50, 0, 0, 255));
        overlayOptions.fillColor(Color.argb(20, 0, 0, 255));

        this.polygon = this.googleMap.addPolygon(overlayOptions);
    }

    private class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter
    {
        private View myMarkerView;

        public CustomInfoWindowAdapter()
        {
            myMarkerView = getLayoutInflater().inflate(R.layout.custominfowindow, null);
        }

        public View getInfoContents(Marker marker)
        {
            ImageView imageView = (ImageView)myMarkerView.findViewById(R.id.marker_picture);

            TextView title = (TextView)myMarkerView.findViewById(R.id.marker_title);

            TextView description = (TextView)myMarkerView.findViewById(R.id.marker_description);

            int drawableId = 0;

            for(String loopMarker : mapMarkers.keySet())
            {
                if(loopMarker.equals(marker.getId()))
                {
                    drawableId = mapMarkers.get(loopMarker);
                    break;
                }
            }

            title.setText(marker.getTitle());
            description.setText(marker.getSnippet());
            imageView.setImageResource(drawableId);

            return myMarkerView;
        }

        @Override
        public View getInfoWindow(final Marker marker)
        {
            return null;
        }
    }
}
