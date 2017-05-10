package com.nicknam.spotify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SpotifyTaskListener, AdapterView.OnItemClickListener {
    private ArrayList<SpotifyItem> spotifyItems = new ArrayList<>();
    ListView listView;
    ArrayAdapter<SpotifyItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.MainActivity_listview_albums);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spotifyItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        fetchSpotifyItems();
    }

    @Override
    public void onSpotifyItemAvailable(SpotifyItem item) {
        spotifyItems.add(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, AlbumActivity.class);
        intent.putExtra("image_url", ((SpotifyItem) parent.getItemAtPosition(position)).getImageUrl());
        startActivity(intent);
    }

    private void fetchSpotifyItems() {
        SpotifyTask spotifyTask = new SpotifyTask(this);
        String[] urls = new String[] {"https://api.spotify.com/v1/search?query=lil+dicky&type=album"};
        spotifyTask.execute(urls);
    }
}
