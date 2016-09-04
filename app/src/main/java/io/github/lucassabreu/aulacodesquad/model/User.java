package io.github.lucassabreu.aulacodesquad.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lucas on 04/09/16.
 */
public class User implements Parcelable {

    private long id;
    private String nome;
    private String curso;

    public User() {}

    protected User(Parcel in) {
        id = in.readLong();
        nome = in.readString();
        curso = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(getId());
        parcel.writeString(getNome());
        parcel.writeString(getCurso());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
