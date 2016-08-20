package br.com.radio.fragment.course;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleveroad.slidingtutorial.PageFragment;
import com.cleveroad.slidingtutorial.TransformItem;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.radio.R;
import br.com.radio.activity.RadioActivity;


public class LoginPageFragment extends PageFragment implements View.OnClickListener {
    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected TransformItem[] provideTransformItems() {
        return new TransformItem[0];
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_login_page;
    }


    private void loadFirebaseListener(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("onCreate", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d("onCreate", "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void loadFacebookLogin(View view){
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.bt_facebook);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("onSuccess", "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
                Log.d("onCancel", "facebook:onCancel");
            }
            @Override
            public void onError(FacebookException error) {
                Log.d("onError", "facebook:onError", error);
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("face", "signInWithCredential:onComplete:" + task.isSuccessful());
                        startRadioAtivity();
                        if (!task.isSuccessful()) {
                            Log.w("face", "signInWithCredential", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        Log.d("onclick","clicou");
        if(i==R.id.bt_ignore){
            signInAnonymously();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_page, container, false);
        loadFacebookLogin(view);
        loadFirebaseListener();
        view.findViewById(R.id.bt_ignore).setOnClickListener(this);
        return view;
    }

    private void signInAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        startRadioAtivity();
                        if (!task.isSuccessful()) {
                            Log.w("aa", "signInAnonymously", task.getException());
                        }
                    }
                });
        // [END signin_anonymously]
    }

    private void startRadioAtivity(){
        startActivity(new Intent(getActivity(), RadioActivity.class));
        getActivity().finish();
    }
}
