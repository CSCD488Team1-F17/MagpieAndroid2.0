package com.magpiehunt.magpie;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Author:  Blake Impecoven
 * Date:    11/14/17.
 */

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "MainActivity";

    /*
     * Firebase/Google instance variables
    **/
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    GoogleApiClient mGoogleApiClient;

    private String mUsername;
    private String mPhotoUrl; // Optional - if we want their photo

//    private DatabaseReference mFirebaseDatabaseReference;
//    private FirebaseAnalytics mFirebaseAnalytics;
//    private FirebaseRecyclerAdapter<type, type> mFirebaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            // Just thought I'd throw this is in we need it in the future,
            // if not, it isnt hurting anything.
            mUsername = mFirebaseUser.getDisplayName();

            // Optional, will grab their photo url if it exists
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }//end if
        }//end if/else

        // Build Google API Client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        // Firebase Database Initialization - Soon to come...
//        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();


    }//end onCreate

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

}
