package com.example.egemenozdag.reserveit0001;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
@SuppressWarnings("ALL")
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button button;
    private Button button2;
    private boolean giris;
    private String resName = "restoran@hotmail.com";
    private String resName1 = "resta@hotmail.com";
    private String resName2 = "rest@hotmail.com";
    private String resName3 = "res@hotmail.com";
    private String resPass = "1a2A3#456";
    private String resPass1 = "1a2A3#456";
    private String resPass2 = "1a2A3#456";
    private String resPass3 = "1a2A3#456";
    private String kulName = "kullanici@hotmail.com";
    private String kulName1 = "kullan@hot.com";
    private String kulName2 = "kul@hotmail.com";
    private String kulName3 = "kullanici@h.com";
    private String kulName4 = "kullanici4@hotmail.com";
    private String kulPass = "123456aB#";
    private String kulPass1 = "123456bB#";
    private String kulPass2 = "123456vB#";
    private String kulPass3 = "123456BQ#"; private String kulPass4 = "123456#aB";
    private String admin = "admin@hotmail.com";
    private String adminPass = "123456";
    private String[] RestoranName = {resName,resName1,resName2,resName3};
    private String[] RestoranPass = {resPass,resPass1,resPass2,resPass3};
    private String[] KullanName = {kulName,kulName1,kulName2,kulName3,kulName4};
    private String[] KullanPass = {kulPass,kulPass1,kulPass2,kulPass3,kulPass4};
    private FirebaseAuth auth;
    private FirebaseUser firebaseuser;
    private DatabaseReference DB;
    private String pass=null;
    private String gelMail=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        // Set up the login form.

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();
        mPasswordView = (EditText) findViewById(R.id.password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin(pass,gelMail);
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                attemptLogin(pass,gelMail);
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        button = (Button) findViewById(R.id.buttonCustomer);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openCreateAccountActivity();
            }
        });

        button2 = (Button) findViewById(R.id.buttonRestaurant);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openCreateRestaurant();
            }
        });

    }



    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */

    private void attemptLogin(String gelenPass, String GelenMail) {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password)&&!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        else if (!TextUtils.isEmpty(password)&&!isPasswordIncCharacter(password)) {
            mPasswordView.setError(getString(R.string.error_not_character));
            focusView = mPasswordView;
            cancel = true;
        }
        else if (!TextUtils.isEmpty(password)&&!isPasswordBig(password)) {
            mPasswordView.setError(getString(R.string.error_pass_bigLetter));
            focusView = mPasswordView;
            cancel = true;
        }
        else if (!TextUtils.isEmpty(password)&&!isPasswordlet(password)) {
            mPasswordView.setError(getString(R.string.error_pass_letter));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (!TextUtils.isEmpty(password)&&TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!TextUtils.isEmpty(password)&&!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else if(!cancel){
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            for(int i=0;i<RestoranName.length;i++) {
                if (email.equals(RestoranName[i])) {
                    if (password.equals(RestoranPass[i])) {
                        showProgress(true);
                        mAuthTask = new UserLoginTask(email, password);
                        openRestaurantProfileActivity();
                    } else mPasswordView.setError(getString(R.string.error_incorrect_password));

                }
            }
            for(int i=0;i<KullanName.length;i++) {
                if (email.equals(KullanName[i])) {
                    if (password.equals(KullanPass[i])) {
                        showProgress(true);
                        mAuthTask = new UserLoginTask(email, password);
                        openkulProfileActivity();
                    } else mPasswordView.setError(getString(R.string.error_incorrect_password));

                }
            }
            if (email.equals(admin)) {
                if (password.equals(adminPass)) {
                    showProgress(true);
                    openAdminProfileActivity();
                    mAuthTask = new UserLoginTask(email, password);
                } else mPasswordView.setError(getString(R.string.error_incorrect_password));
            }


        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic

        return password.length() > 7;
    }
    private boolean isPasswordIncCharacter(String password) {
        String[] charac = {"é","!","'","#","^","%","&","/","=","*","?"};
        for(int i=0; i<charac.length;i++){
            if(password.contains(charac[i])){
                return true;
            }
        }return false;
    }
    private boolean isPasswordBig(String password) {
        String[] bigletter = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","R","S","T","U","V","Y","Z"};
        for(int i=0; i<bigletter.length;i++){
            if(password.contains(bigletter[i])){
                return true;
            }
        }return false;
    }
    private boolean isPasswordlet(String password) {
        String[] letter = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","r","s","t","u","v","y","z"};
        for(int i=0; i<letter.length;i++){
            if(password.contains(letter[i])){
                return true;
            }
        }return false;

    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail ;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();

            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
    public void openCreateAccountActivity(){
        Intent intent = new Intent(LoginActivity.this, CreateCustomerAccountActivity.class);
        startActivity(intent);
    }
    public void openCreateRestaurant(){
        Intent intent = new Intent(LoginActivity.this, createRestaurantAccount.class);
        startActivity(intent);
    }
    public void openRestaurantProfileActivity(){
        Intent in = new Intent(LoginActivity.this, restaurantProfile.class);
        startActivity(in);
    }
    public void openkulProfileActivity(){
        Intent in = new Intent(LoginActivity.this, UserProfile.class);
        startActivity(in);
    }
    public void openAdminProfileActivity(){
        Intent in = new Intent(LoginActivity.this, adminProfile.class);
        startActivity(in);
    }

}