package com.example.navdrawer.ui.room;

import android.app.Activity;
import android.os.Bundle;

import com.example.navdrawer.CocheLab;
import com.example.navdrawer.MainActivity;
import com.example.navdrawer.model.Coche;
import com.example.navdrawer.model.CocheContent;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navdrawer.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A fragment representing a single Coche detail screen.
 * This fragment is either contained in a {@link CocheListActivity}
 * in two-pane mode (on tablets) or a {@link CocheDetailActivity}
 * on handsets.
 */
public class CocheDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Coche mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CocheDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean debug1 = getArguments().containsKey(ARG_ITEM_ID);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            mItem = CocheContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));



            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getNombre());
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.coche_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.coche_detail)).setText(mItem.getDesc());
        }

        return rootView;
    }
}
