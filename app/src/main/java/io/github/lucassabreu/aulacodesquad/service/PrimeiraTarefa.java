package io.github.lucassabreu.aulacodesquad.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * Created by lucas on 04/09/16.
 */
public class PrimeiraTarefa extends AsyncTask<Integer, Integer, Integer> {

    public static final String TAG = "PrimeiraTarefa";

    /*
     * onPreExecute é chamada ainda na Thread do usuário
     * onInBackgroud é onde o processamento acontece
     * onPostExecute o processamento já foi feito e esta sendo feito o retorno
     */

    private final Context mContext;

    public PrimeiraTarefa(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(mContext, "onPreExecute", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        Toast.makeText(mContext, "onPreExecute", Toast.LENGTH_SHORT).show();

        int somado = 0;
        for(int i = 0; i < params[0]; i++) {
            somado++;
            this.publishProgress(somado);
        }

        try {
            // a classe Thread sempre irá atuar sobre a thread em que eh chamada
            // no caso a thread do AsyncTask
            Thread.sleep(TimeUnit.SECONDS.toMillis(30));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, String.format("Progresso Recebido = %d$1", values[0]));
    }

    @Override
    protected void onPostExecute(Integer result) {
        Toast.makeText(mContext, "Somado = " + result, Toast.LENGTH_SHORT).show();
    }
}
