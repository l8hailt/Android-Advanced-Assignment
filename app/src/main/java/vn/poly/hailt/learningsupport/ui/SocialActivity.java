package vn.poly.hailt.learningsupport.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import vn.poly.hailt.learningsupport.R;

public class SocialActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private ProfilePictureView imgProfile;
    private TextView tvProfileName;
    private AccessTokenTracker att;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        initViews();

        callbackManager = CallbackManager.Factory.create();

        initActions();
        checkLogin();

        att = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    tvProfileName.setVisibility(View.INVISIBLE);
                    imgProfile.setProfileId(null);
                }
            }
        };

    }

    private void checkLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            loginSuccess();
        }
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_social);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgProfile = findViewById(R.id.imgProfile);
        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileName.setVisibility(View.INVISIBLE);
        loginButton = findViewById(R.id.login_button);
    }

    private void initActions() {
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.e("onSuccess", "Login onSuccess");
                loginSuccess();
            }

            @Override
            public void onCancel() {
                // App code
                Log.e("onCancel", "Login onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.e("onError", "Login onError");
            }
        });
    }

    private void loginSuccess() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("JSON", response.getJSONObject().toString());

                        try {
                            String name = object.getString("name");
                            tvProfileName.setVisibility(View.VISIBLE);
                            tvProfileName.setText(name);
                            imgProfile.setProfileId(Profile.getCurrentProfile().getId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, name");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        att.stopTracking();
        super.onDestroy();
    }

    //    @Override
//    protected void onStart() {
//        LoginManager.getInstance().logOut();
//        super.onStart();
//    }
}
