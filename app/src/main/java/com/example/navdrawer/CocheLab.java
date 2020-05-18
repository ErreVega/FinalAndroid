package com.example.navdrawer;

import android.content.Context;

import androidx.room.Room;

import com.example.navdrawer.model.Coche;

import java.util.List;

public class CocheLab {
    // @SuppressLint("StaticFieldLeak")
    private static CocheLab sCocheLab;

    private CocheDao mCocheDao;

    private CocheLab(Context context) {
        Context appContext = context.getApplicationContext();
        CocheDatabase database =
                Room.databaseBuilder(appContext, CocheDatabase.class, "coche").allowMainThreadQueries().build();

        mCocheDao = database.getCocheDaoDB();
    }

    public static CocheLab get(Context context) {
        if (sCocheLab == null) {
            sCocheLab = new CocheLab(context);
        }
        return sCocheLab;
    }

    public List<Coche> getAllCoches() {
        return mCocheDao.getAllCoches();
    }

    public Coche getCoche(String id) {
        return mCocheDao.getCoche(id);
    }

    public void addCoche(Coche c) {
        mCocheDao.addCoche(c);
    }

    public void updateCoche(Coche c) {
        mCocheDao.updateCoche(c);
    }

    public void deleteCoche(Coche c) {
        mCocheDao.deleteCoche(c);
    }
}
