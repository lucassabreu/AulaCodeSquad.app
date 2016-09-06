package io.github.lucassabreu.aulacodesquad.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import io.github.lucassabreu.aulacodesquad.model.User;

/**
 * Created by lucas on 05/09/16.
 */
public class UserManager {

    private static final String TABLE_NAME = "user";
    private final SQLiteDatabase mDb;

    public UserManager(Context context, boolean readOnly) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        mDb = readOnly ? dbHelper.getReadableDatabase() : dbHelper.getWritableDatabase();
    }

    private User convertToUser(Cursor c) {
        User user = new User();
        user.setId(c.getLong(c.getColumnIndex("_id")));
        user.setNome(c.getString(c.getColumnIndex("nome")));
        user.setCurso(c.getString(c.getColumnIndex("curso")));
        return user;
    }

    private ContentValues toValues(User user) {
        ContentValues values = new ContentValues();
        if (user.getId() > 0)
            values.put("_id", user.getId());
        values.put("nome", user.getNome());
        values.put("curso", user.getCurso());
        return values;
    }

    public void save(User user) {
        mDb.insert(TABLE_NAME, null, toValues(user));
    }

    public void update(User user) {
        mDb.update(TABLE_NAME, toValues(user), "_id = ?", new String[] {
                Long.toString(user.getId())
        });
    }

    public void saveOrUpdate(User user) {
        if (user.getId() > 0)
            update(user);
        else
            save(user);
    }

    public User getUser(long id) {
        Cursor c = mDb.query(
                TABLE_NAME,
                new String[] { "_id", "nome", "curso" },
                "_id = ?",
                new String[] { Long.toString(id) },
                null, null, null);
        if(c.moveToFirst()) {
            return convertToUser(c);
        }

        return null;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Cursor c = mDb.query(
                TABLE_NAME,
                new String[] { "_id", "nome", "curso" },
                null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                users.add(convertToUser(c));
            } while(c.moveToNext());
        }
        return users;
    }

    public void delete(long id) {
        mDb.delete(TABLE_NAME, "_id = ?", new String[] { Long.toString(id) });
    }
}
