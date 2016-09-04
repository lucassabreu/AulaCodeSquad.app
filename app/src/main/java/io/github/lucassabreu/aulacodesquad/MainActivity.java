package io.github.lucassabreu.aulacodesquad;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import io.github.lucassabreu.aulacodesquad.fragment.SegundoFragment;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    public static final String FRAGMENT_SEGUNDO_TAG = "SegundoFragment";

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button_increment);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(savedInstanceState == null)
            replace();
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SegundoFragment segundo = (SegundoFragment) getSupportFragmentManager().
                findFragmentByTag(FRAGMENT_SEGUNDO_TAG);
        segundo.increment();
        Log.d(TAG, "increment button");
    }

    private void replace() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_content,
                         SegundoFragment.newInstance("Android do jeito certo"), FRAGMENT_SEGUNDO_TAG)
                .commit();
    }
}
