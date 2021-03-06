package com.magpiehunt.magpie;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.magpiehunt.magpie.Fragments.GoogleMapFragment;
import com.magpiehunt.magpie.Fragments.MyCollectionsFragment;
import com.magpiehunt.magpie.Fragments.PrizesFragment;
import com.magpiehunt.magpie.Fragments.QRFragment;
import com.magpiehunt.magpie.Fragments.SearchCollectionsFragment;

/**
 * Author:  Blake Impecoven
 * Date:    11/14/17.
 */

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, MyCollectionsFragment.OnFragmentInteractionListener, GoogleMapFragment.OnFragmentInteractionListener, QRFragment.OnFragmentInteractionListener, SearchCollectionsFragment.OnFragmentInteractionListener,PrizesFragment.OnFragmentInteractionListener {

    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 123;
    private int requestCode = 0;

    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    /*
     * Firebase/Google instance variables
    **/
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    GoogleApiClient mGoogleApiClient;
    public Menu optionsMenu;

    private String mUsername;
    private String mPhotoUrl; // Optional - if we want their photo

//    private DatabaseReference mFirebaseDatabaseReference;
//    private FirebaseAnalytics mFirebaseAnalytics;
//    private FirebaseRecyclerAdapter<type, type> mFirebaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Initialize Firebase
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        int requestCode = 0;
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivityForResult(new Intent(this, SignInActivity.class), requestCode);
       } else {
            // Just thought I'd throw this is in we need it in the future,
            // if not, it isnt hurting anything.
            mUsername = mFirebaseUser.getDisplayName();

            // Optional, will grab their photo url if it exists
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }//end if
        }//end if/else

        // Firebase Database Initialization - Soon to come...
//        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //Create bottom navigation bar to switch between app pages
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        setupFragments();

       // MagpieDatabase db = MagpieDatabase.getMagpieDatabase(this);
        //log.e(TAG, db.collectionDao().getCollection(1).getName());



    }//end onCreate

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(requestCode == this.requestCode) {
                if (resultCode == RESULT_OK) {
                    // Google Sign-In was successful, authenticate with Firebase
                    mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                } else {
                    // Google Sign-In failed.
                    Log.e(TAG, "Google Sign-In failed.");
                }//if: successful. else: failure.
            }
        }//end if
    }//end

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        optionsMenu = menu;
        menu.findItem(R.id.add_location).setVisible(false);
        menu.findItem(R.id.save_locations).setVisible(false);
        return true;
    }

    /*public void toggleOptionsMenu(int id, boolean enabled){
        if(optionsMenu != null){
            MenuItem item = optionsMenu.findItem(id);
            item.setVisible(enabled);
        }
    }*/

    //this method creates the fragments for each page accessible from the bottom navigation
    // bar and sets up the listener for the navigation bar.
    private void setupFragments()
    {
<<<<<<< HEAD
        final MyCollectionsFragment myCollectionsFragment = new MyCollectionsFragment();
        final GoogleMapFragment mapFragment = new GoogleMapFragment();
        final QRFragment qrFragment = new QRFragment();
        final SearchCollectionsFragment searchCollectionsFragment = new SearchCollectionsFragment();
        final PrizesFragment prizesFragment = new PrizesFragment();
=======
        final MyCollectionsFragment myCollectionsFragment = MyCollectionsFragment.newInstance();
        final MapFragment mapFragment = MapFragment.newInstance();
        final QRFragment qrFragment = QRFragment.newInstance();
        final SearchCollectionsFragment searchCollectionsFragment = SearchCollectionsFragment.newInstance();
        final PrizesFragment prizesFragment = PrizesFragment.newInstance();
>>>>>>> origin
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, myCollectionsFragment).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        FragmentTransaction replace;
                        fragment = null;
                        switch (item.getItemId()) {
                            case R.id.menu_map:
                                fragment = mapFragment;
                                break;
                            case R.id.menu_qr:
                                fragment = qrFragment;
                                break;
                            case R.id.menu_home:
                                fragment = myCollectionsFragment;
                                break;
                            case R.id.menu_search:
                                fragment = searchCollectionsFragment;
                                break;
                            case R.id.menu_prizes:
                                fragment = prizesFragment;
                                break;
                        }
                        replace = fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment);
                        replace.addToBackStack(null);
                        replace.commit();
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out:
                mFirebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                mFirebaseUser = null;
                mUsername = null;
                mPhotoUrl = null;
                startActivity(new Intent(this, SignInActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }//end switch
    }//end



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        /*
         * An unresolvable error has occurred and Google APIs (including Sign-In) will not
         * be available.
        **/
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
