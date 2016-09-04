package io.github.lucassabreu.aulacodesquad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import io.github.lucassabreu.aulacodesquad.fragment.SegundoFragment;
import io.github.lucassabreu.aulacodesquad.service.PrimeiraTarefa;
import io.github.lucassabreu.aulacodesquad.service.Servico;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    public static final String FRAGMENT_SEGUNDO_TAG = "SegundoFragment";

    private Button mButton;
    private PrimeiraTarefa mPrimeiraTarefa;
    private PrimeiroBroadcast mPrimeiroBroadcast;

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

    /*
     * É importante manter um gerenciamento das tasks dentro do Activity life cycle,
     * pois mesmo que o usuário saia da sua activity as Thread e outros serviços
     * continuam rodando mesmo assim
     */

    public void startTasks() {
        cancelTasks();
        mPrimeiraTarefa = new PrimeiraTarefa(this);
        mPrimeiraTarefa.execute(100);
    }

    public void cancelTasks() {
        if (mPrimeiraTarefa != null)
            mPrimeiraTarefa.cancel(true);
        mPrimeiraTarefa = null;
    }

    @Override
    public void onClick(View view) {
        // startTasks();
        mPrimeiroBroadcast = new PrimeiroBroadcast();
        registerReceiver(mPrimeiroBroadcast, new IntentFilter(Servico.AULA_CODESQUAD));

        Intent intent = new Intent(this, Servico.class);
        intent.getExtras().putInt("inicial", 87);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        cancelTasks();
        if (mPrimeiroBroadcast != null)
            unregisterReceiver(mPrimeiroBroadcast);
        super.onDestroy();
    }

    private void replace() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_content,
                         SegundoFragment.newInstance("Android do jeito certo"), FRAGMENT_SEGUNDO_TAG)
                .commit();
    }

    private class PrimeiroBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,
                    "Resutaldo Serviço: " + intent.getExtras().getInt("resultado"),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
