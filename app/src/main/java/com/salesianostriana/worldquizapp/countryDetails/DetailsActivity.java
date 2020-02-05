package com.salesianostriana.worldquizapp.countryDetails;

import androidx.appcompat.app.AppCompatActivity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.salesianostriana.worldquizapp.R;
import com.salesianostriana.worldquizapp.model.unsplash.Image;
import com.salesianostriana.worldquizapp.model.unsplash.Result;
import com.salesianostriana.worldquizapp.repository.UnsplashService;
import com.salesianostriana.worldquizapp.repository.retrofit.UnsplashGenerator;

import java.io.IOException;


import retrofit2.Call;
import retrofit2.Response;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class DetailsActivity extends AppCompatActivity /*implements ResultFragment.OnListFragmentInteractionListener*/ {

    UnsplashService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        service = UnsplashGenerator.createService(UnsplashService.class);
        new ImageUnsplashAsyncTask(this).execute();

    }

    /*@Override
    public void onListFragmentInteraction(Result item) {

    }*/

    public class ImageUnsplashAsyncTask extends AsyncTask<Image, Void, Image> {

        DetailsActivity ctx;
        FlipperLayout flipperLayout;

        public ImageUnsplashAsyncTask(DetailsActivity ctx) {
            this.ctx = ctx;
        }

        @Override
        protected Image doInBackground(Image... images) {

            Call<Image> call = service.getImagesUnsplash(getIntent().getExtras().get("nameCountry").toString(), "1");
            Response<Image> response = null;

            try {
                response = call.execute();

                if (response.isSuccessful()) {
                    return response.body();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(ctx, "Loading data", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Image image) {

            flipperLayout = findViewById(R.id.flipper);


            for (int i = 0; i < image.getResults().size(); i++) {
                FlipperView view = new FlipperView(ctx);
                view.setImageUrl(image.getResults().get(i).getUrls().getRegular());
                view.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                flipperLayout.addFlipperView(view);
            }

        }
    }
}
