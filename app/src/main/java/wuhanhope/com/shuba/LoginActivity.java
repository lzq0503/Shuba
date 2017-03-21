package wuhanhope.com.shuba;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.username)
    public EditText userName;

    @BindView(R.id.password)
    public EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    public void LoginIn(View view) {

    }

    @OnClick(R.id.exit)
    public void LoginOut(View view) {
        ActivityCollector.finishAll();
    }
}
