package wuhanhope.com.shuba;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import wuhanhope.com.shuba.Utils.BaseActivity;

//https://github.com/Zzz468005600/Stone

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

//        CustomDialog dialog = new CustomDialog(this, R.style.CustomDialog);
//        dialog.show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
