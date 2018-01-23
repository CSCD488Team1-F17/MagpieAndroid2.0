package com.magpiehunt.magpie.Fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.magpiehunt.magpie.MainActivity;
import com.magpiehunt.magpie.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GoogleMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GoogleMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoogleMapFragment extends Fragment
        implements OnMapReadyCallback, LocationSource.OnLocationChangedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private float zoom;
    private ArrayList<MarkerOptions> marks;
    private ArrayList<Marker> markerList;
    private final int PERMISSION_REQUEST = 1;
    private GoogleMap gMap;
    private FusedLocationProviderClient fusedLoc;
    private Location currLoc;
    private GoogleMapFragment mapFrag;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GoogleMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * param param1 Parameter 1.
     * param param2 Parameter 2.
     * @return A new instance of fragment GoogleMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoogleMapFragment newInstance() {
        GoogleMapFragment fragment = new GoogleMapFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        fusedLoc = LocationServices.getFusedLocationProviderClient(getActivity());
        markerList = new ArrayList<Marker>();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if(savedInstanceState != null){
            //do stuff if saved
        }
        else{
            zoom = -1;
            marks = new ArrayList<MarkerOptions>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {// Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        MapFragment mapFrag = (MapFragment) ((MainActivity)getActivity()).getFragmentManager()
                .findFragmentById(R.id.map);
        if(mapFrag == null)
        {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFrag = MapFragment.newInstance();
            ft.replace(R.id.map, mapFrag).commit();
        }
        mapFrag.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        gMap = googleMap;
        initMap();
    }

    private void initMap() {
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gMap.setMyLocationEnabled(true);
        }
        else
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMISSION_REQUEST);
            if(ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                gMap.setMyLocationEnabled(true);
            }
            else{
                return;
            }
            //return;//might need this?
        }
        UiSettings mapSettings = gMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);
        mapSettings.setCompassEnabled(true);
        mapSettings.setRotateGesturesEnabled(true);
        if(zoom != -1){
            gMap.animateCamera(CameraUpdateFactory.zoomTo(zoom));
        }
        for(MarkerOptions m: marks){
            markerList.add(gMap.addMarker(m));
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                /*Location loc = getLocation();
                if(loc != null){moveToLocation(loc);}*/
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 200); //call functions in runn avter delay
    }

    @Override
    public void onLocationChanged(Location location){
        currLoc = location;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //helper functions==========================
    private boolean hasPermission(){
        if(ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    public  void typePressed(View v){
        int type = gMap.getMapType();
        if(type == GoogleMap.MAP_TYPE_NORMAL){
            gMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else if(type == GoogleMap.MAP_TYPE_TERRAIN){
            gMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        else if(type == GoogleMap.MAP_TYPE_TERRAIN){
            gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    public void markPressed(View v){
        Location l = getLocation();
        LatLng coords = new LatLng(l.getLatitude(), l.getLongitude());
        marks.add(new MarkerOptions());
        marks.get(marks.size() - 1).position(coords);
        marks.get(marks.size() - 1).title("Mark " + marks.size());
        gMap.addMarker(marks.get(marks.size() - 1));
    }

    private Location getLocation(){
        LocationRequest lr = new LocationRequest();
        //0 means do asap
        lr.setInterval(0);
        lr.setFastestInterval(0);
        lr.setNumUpdates(1);//stop after one recieved
        lr.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        /*
        gMap.
        LocationManager locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(result == null){
            String prov = getProvider(locMan, Criteria.ACCURACY_FINE, locMan.GPS_PROVIDER);
            try {
                result = locMan.getLastKnownLocation(prov);
            }catch(SecurityException e){
                Log.e("Keep Out", "Security Exception; Top Secret: " + e.getMessage());
            }
        }
        if(result == null){
            String prov = getProvider(locMan, Criteria.ACCURACY_COARSE, locMan.NETWORK_PROVIDER);
            try {
                result = locMan.getLastKnownLocation(prov);
            }catch(SecurityException e){
                Log.e("Keep Out", "Security Exception; Top Secret: " + e.getMessage());
            }
        }
        return result;//*/
        return null;
    }

    private void moveToLocation(Location l){
        LatLng coords = new LatLng(l.getLatitude(), l.getLongitude());
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coords, 14));
    }
}
