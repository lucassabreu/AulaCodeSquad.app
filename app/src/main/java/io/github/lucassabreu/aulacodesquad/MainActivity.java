package io.github.lucassabreu.aulacodesquad;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    /*
     * O ciclo de vida de uma activity começa no onCreate, neste evento a
     * activity é inicializada. É recomendado inicializar os componentes de tela
     * neste evento.
     *
     * Após a chamada do onCreate é feita a chamada do onStart, onde a activity
     * ainda não é visivel para o cliente, nesse momento podem ser inicializados
     * alguns controles da activity
     *
     * Em seguida é chamado o evento onPostCreate, nesse momento entende-se que
     * a activity já esta inicializada e visível para o usuário, é interessante
     * inicializar threads e outros serviços semelhantes nesse evento.
     *
     * O onResume vem em seguida, indicando que a aplicação/activity está disponível
     * ou retornou para ela. Sempre que o usuário sair da activity e retornar seram
     * chamados apenas os eventos onStart e onResume, no onResume a activity já esta
     * visível. Quando volta do onStop o onResume é chamado, as tarefas/serviços
     * que foram parados no onStop
     *
     * onPause é chamada quando a aplicação é pausada, inicialmente de forma temporária,
     * ainda não esta "invisível", mas irá se tornar
     *
     * Dando continuidade a pausa é chamado o evento onStop, sendo a contraparte do
     * onStart, nesse momento a activity já não esta mais visível. É importante parar
     * serviços que não tenham necessidade de continuar rodando sejam paradas nesse
     * momento, pois embora a aplicação não esteja disponível ela ainda esta rodando
     * em plano de fundo
     *
     * Quando a aplicação for ser destruida (retirada da memória) é chamado o evento
     * onDestroy, eventuais recursos que ainda estejam disponível seria interessante
     * terminá-los nesse momento que não sejam mais necessárias, mas que não
     * necessáriamente serão terminados automaticamente.
     *
     * *****
     *
     * Quando ouver mudança de orientação no android (paisagem/retrato) o life cycle
     * é parcialmente reinicializado, com a diferença que o parametro
     * savedInstanceState estará alimentado, indicando que houve uma mudança de
     * estado que forçou a recriação da activity. Todos os eventos serão chamadas
     * na mudança de orientação, inclusive o onDestroy. Outra situação que pode
     * disparar esse processo é o sistema android eliminar a activity para fins de
     * uso de memória
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        if (savedInstanceState == null)
            Log.d("has been created for the first time, args == null");
        else
            Log.d("has been restart (orientation), args != null");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState, persistentState);
        Log.d(TAG, "onPostCreate");
        if (savedInstanceState == null)
            Log.d("has been called for the first time, args == null");
        else
            Log.d("has been restart (orientation), args != null");

        /*
         * chamada para outra intent
         */

        // start by class
        Intent in = new Intent(this, SecondActivity.class);

        // colocando valores no extra é possível passar valores para o
        // destino da intent
        in.putExtra("id", 1);

        // chama a activity esperabdi um retorno, é necessário passar um
        // requestCode (no caso 8) para identificar os retornos no onActivityResult
        startActivityForResult(in, 8);

        // apenas inicia a activity
        // startActivity(in);


        // start by action
        // Intent in = new Intent(this, "io.github.lucassabreu.aulacodesquad.ACTION_SECOND_ACTIVITY");
        // startActivity(in);
    }

    /**
     * Toma uma atitude sobre o retorno de uma intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 8 && resultCode == RESULT_OK) {
            final int idSomado = data.getExtras().getInt("soma");
            Toast.makeText(this, "soma: " + idSomado, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
