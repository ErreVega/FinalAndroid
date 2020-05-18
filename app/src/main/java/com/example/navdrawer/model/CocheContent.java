package com.example.navdrawer.model;

import com.example.navdrawer.CocheLab;
import com.example.navdrawer.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CocheContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Coche> ITEMS = new ArrayList<Coche>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Coche> ITEM_MAP = new HashMap<String, Coche>();

    static {
        for (Coche c : CocheLab.get(MainActivity.getInstance()).getAllCoches()){
            addItem(c);
        }
    }


    private static void addItem(Coche item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }



}
