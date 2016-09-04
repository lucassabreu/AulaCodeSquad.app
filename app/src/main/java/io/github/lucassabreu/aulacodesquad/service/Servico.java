package io.github.lucassabreu.aulacodesquad.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by lucas on 04/09/16.
 */
public class Servico extends IntentService {

    public static final String SERVICE_NAME = "Servico_CodeSquad";
    public static final String AULA_CODESQUAD = "io.github.lucassabreu.aulacodesquad.BROADCAST_DATA";

    public Servico () {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int inicial = intent.getExtras().getInt("inicial");

        for(int i = 0; i < 20; i++) {
            inicial++;
        }

        Intent result = new Intent(AULA_CODESQUAD);
        result.putExtra("resultado", inicial);
        sendBroadcast(intent);
    }
}
