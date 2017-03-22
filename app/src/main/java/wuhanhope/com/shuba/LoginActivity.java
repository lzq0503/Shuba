package wuhanhope.com.shuba;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 1;

    @BindView(R.id.user_mobile)
    public EditText userName;

    @BindView(R.id.user_password)
    public EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_btn)
    public void LoginIn(View view) {
        login();
    }

//    @OnClick(R.id.sign_up)
//    public void CreateAccount(View view) {
//
//    }

    private void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        userName.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.Theme_AppCompat_Dialog_Alert);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String username = userName.getText().toString();
        String psd = password.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }
        ,3000);
    }

    private void onLoginFailed() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
    }

    private void onLoginSuccess() {
        userName.setEnabled(true);
        finish();
    }

    private boolean validate() {
        String username = userName.getText().toString();
        String pwd = password.getText().toString();

        if (username.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            userName.setError("please enter a valid email address");
            return false;
        } else {
            userName.setError(null);
        }

        if (pwd.isEmpty() || password.length() < 4 || password.length() > 10) {
            password.setError("password between 4 and 10 alphanumeric characters");
            return false;
        } else {
            password.setError(null);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
