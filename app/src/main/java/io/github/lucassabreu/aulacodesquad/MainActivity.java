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

import java.util.List;

import io.github.lucassabreu.aulacodesquad.fragment.SegundoFragment;
import io.github.lucassabreu.aulacodesquad.model.User;
import io.github.lucassabreu.aulacodesquad.service.PrimeiraTarefa;
import io.github.lucassabreu.aulacodesquad.service.Servico;
import io.github.lucassabreu.aulacodesquad.service.UserService;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    private Button mButton;
    private UserBroadcast mBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button_increment);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (mBroadcast == null) {
            mBroadcast = new UserBroadcast();
            registerReceiver(mBroadcast, new IntentFilter(UserService.ACTION_BUSCA_USUARIOS));
        }

        Intent intent = new Intent(this, UserService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBroadcast != null)
            unregisterReceiver(mBroadcast);
    }

    private class UserBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras().containsKey("error")) {
                Toast.makeText(context, "Algum erro aconteceu: " +
                        intent.getStringExtra("error"), Toast.LENGTH_SHORT).show();
            } else {
                List<User> usuarios =
                    intent.getExtras().getParcelableArrayList("usuarios");

                String userName = "";
                for(User u : usuarios)
                    userName += u.getNome() + " ";

                Toast.makeText(context, "Usuarios: " + userName, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
