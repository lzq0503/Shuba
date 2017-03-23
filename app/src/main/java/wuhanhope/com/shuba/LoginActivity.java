package wuhanhope.com.shuba;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import wuhanhope.com.shuba.Utils.BaseActivity;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 1;

    @BindView(R.id.account)
    public EditText account;

    @BindView(R.id.password)
    public EditText password;

    @BindView(R.id.read_label)
    public CheckBox checkReadLabel;

    @BindView(R.id.login_btn)
    public Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_btn)
    public void LoginIn(View view) {
//        CustomDialog dialog = new CustomDialog(this, R.style.CustomDialog);
//        dialog.show();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        imm.showSoftInput(view,InputMethodManager.SHOW_FORCED); //强制显示键盘
        login();
    }

    @OnClick(R.id.create_account)
    public void CreateAccount(View view) {
        Toast.makeText(this, "Create New Account", Toast.LENGTH_SHORT).show();
    }

    @OnCheckedChanged(R.id.read_label)
    public void setLoginStatus(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            login_btn.setEnabled(true);
        } else {
            login_btn.setEnabled(false);
        }
    }

    @OnClick(R.id.read_policy)
    public void showPolicy(View view) {
        Toast.makeText(this, "Policy", Toast.LENGTH_SHORT).show();
    }

    private void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        }

        account.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.Theme_AppCompat_Dialog_Alert);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("正在登陆...");
        progressDialog.show();

        String username = account.getText().toString();
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
        account.setEnabled(true);
        finish();
    }

    private boolean validate() {
        String username = account.getText().toString();
        String pwd = password.getText().toString();

        if (username.isEmpty()) {
            account.setError("账号不能为空");
        }

        boolean phone_check = Patterns.PHONE.matcher(username).matches();

        if (!phone_check) {
            account.setError("please enter a valid phone number");
            return false;
        } else {
            account.setError(null);
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
