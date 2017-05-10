package com.nicknam.spotify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        String imageURL = getIntent().getStringExtra("image_url");
        ImageView imageView = (ImageView) findViewById(R.id.AlbumActivity_imageView_albumArt);
        Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
    }
}
