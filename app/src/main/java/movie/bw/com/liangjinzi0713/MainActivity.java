package movie.bw.com.liangjinzi0713;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.bw.com.liangjinzi0713.core.DataCall;
import movie.bw.com.liangjinzi0713.entity.LogEntity;
import movie.bw.com.liangjinzi0713.entity.Result;
import movie.bw.com.liangjinzi0713.persenter.LogPersenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.btn_login)
    Button login;
    @BindView(R.id.check)
    CheckBox check;
    private LogPersenter logPersenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        login.setOnClickListener(this);
        check.setOnCheckedChangeListener(this);
        logPersenter = new LogPersenter(new LogData());
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            int option=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login://登录
                String phones = phone.getText().toString().trim();
                String pwds = pwd.getText().toString().trim();
                if (TextUtils.isEmpty(phones)||TextUtils.isEmpty(pwds)){
                    Toast.makeText(MainActivity.this,"不能为空",Toast.LENGTH_LONG).show();
                }

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("phone",phones);
                hashMap.put("pwd",pwds);
                logPersenter.request(hashMap);
                break;
        }
    }
    public static boolean checkPhoneNumber(String phone){
        Pattern pattern=Pattern.compile("^1[0-9]{10}$");
        Matcher matcher=pattern.matcher(phone);
        return matcher.matches();
    }
//    隐藏/显示密码
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        选中显示
            if (isChecked){
                pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

    }

    class LogData implements DataCall<LogEntity>{

        @Override
        public void success(LogEntity data) {
            Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this,ShowActivity.class));
        }

        @Override
        public void fail(Result data) {
            Toast.makeText(MainActivity.this,data.message,Toast.LENGTH_LONG).show();
        }
    }
}
