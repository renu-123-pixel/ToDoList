package com.examplefourthjuly.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileHelper implements Serializable {
    public static final String FILENAME="listinfo.dat";
    public static void writeData(ArrayList<Task> itemList, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream obs = new ObjectOutputStream(fos);
            obs.writeObject(itemList);
            obs.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

            public static ArrayList<Task> readData(Context context){
            ArrayList<Task> itemList = null;
            try {
                FileInputStream fis = context.openFileInput(FILENAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                itemList = (ArrayList<Task>) ois.readObject();
            } catch (FileNotFoundException e) {
                itemList = new ArrayList<Task>();
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return itemList;
        }
}
