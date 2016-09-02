package io.github.lucassabreu.aulacodesquad;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by lucas on 30/08/16.
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        /*
        Intent in = getIntent();
        // lendo um valor passado via intent
        int id = in.getExtras().getInt("id");
        Toast.makeText(this, "ID: " + id, Toast.LENGTH_SHORT).show();

        somaResultado(id);
        */
    }

    private void somaResultado (int id) {
        final int soma = id + 1;
        Intent out = new Intent();
        out.putExtra("soma", soma);

        // send back a result
        setResult(RESULT_OK, out);
    }
}
