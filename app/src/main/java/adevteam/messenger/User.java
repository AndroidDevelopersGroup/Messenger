package adevteam.messenger;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by dima on 04.11.15.
 */

public class User {

    private int ID;
    private String name;
    private String email;
    private String password;
    private String photoPath;

    private Bitmap small_image;

    public User(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Bitmap getSmall_image(Context context) {
        return small_image;
    }

}
