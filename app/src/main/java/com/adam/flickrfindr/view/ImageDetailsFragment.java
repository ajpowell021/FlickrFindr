package com.adam.flickrfindr.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import com.adam.flickrfindr.R;
import com.adam.flickrfindr.model.Photo;
import com.squareup.picasso.Picasso;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;


public class ImageDetailsFragment extends DaggerFragment {

    private Photo photo;
    private Picasso picasso;

    public ImageDetailsFragment(Photo photo, Picasso picasso) {
        this.photo = photo;
        this.picasso = picasso;
    }

    // Overrides

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.image_details_fragment, container, false);

        ImageView imageView = rootView.findViewById(R.id.image_view);
        TextView titleView = rootView.findViewById(R.id.title_text_view);
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        picasso
            .load(photo.getLargeUrl())
            .into(imageView);

        titleView.setText(photo.getTitle());

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
