package io.github.lucassabreu.aulacodesquad.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.github.lucassabreu.aulacodesquad.model.User;

/**
 * Created by lucas on 04/09/16.
 */
public class UserService extends IntentService {

    public static final String TAG = UserService.class.getName();
    public static final String SERVICE_NAME = UserService.class.getName();
    public static final String ACTION_BUSCA_USUARIOS = SERVICE_NAME + ".ACTION_BUSCA_USUARIOS";
    private static final String SERVER_URL = "http://192.168.2.103:8080/";

    public UserService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            URL url = new URL(SERVER_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            final int statusCode = connection.getResponseCode();
            InputStream serverResponse = null;

            if (statusCode >= 300) {
                // provavelmente deu erro
                serverResponse = connection.getErrorStream();
            } else {
                serverResponse = connection.getInputStream();
            }

            String response = null;
            if (serverResponse != null) {
                response = inputStreamToString(serverResponse);
            }

            Log.i(TAG, response);

            if (response != null) {
                ArrayList<User> usuarios = new ArrayList<>();
                JSONArray list = new JSONArray(response);
                final int listLength = list.length();
                for(int i = 0; i < listLength; i++) {
                    JSONObject obj = list.getJSONObject(i);
                    User usuario = new User();
                    usuario.setId(obj.getLong("id"));
                    usuario.setNome(obj.getString("nome"));
                    usuario.setCurso(obj.getString("curso"));
                    usuarios.add(usuario);
                    //Log.d(TAG, String.format("Nome : %s$1", obj.getString("nome")));
                }
                Intent responseIntent = new Intent(ACTION_BUSCA_USUARIOS);
                responseIntent.putParcelableArrayListExtra("usuarios", usuarios);
                sendBroadcast(responseIntent);
            } else {
                sendError(new NullPointerException("Response is null"));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            sendError(e);
        } catch (IOException e) {
            e.printStackTrace();
            sendError(e);
        } catch (JSONException e) {
            e.printStackTrace();
            sendError(e);
        }
    }

    private void sendError(Exception error) {
        Intent in = new Intent(ACTION_BUSCA_USUARIOS);
        in.putExtra("error", error.getMessage());
        sendBroadcast(in);
    }


    /**
     * Convert InputStream to String
     * @param is
     * @return
     * @throws IOException
     */
    private static String inputStreamToString (InputStream is) throws IOException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
