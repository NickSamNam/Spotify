package com.nicknam.spotify;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by snick on 10-5-2017.
 */

public class SpotifyTask extends AsyncTask<String, Void, String> {
    private SpotifyTaskListener spotifyTaskListener;

    public SpotifyTask(SpotifyTaskListener spotifyTaskListener) {
        this.spotifyTaskListener = spotifyTaskListener;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        BufferedReader reader = null;
        String urlString = "";
        String response = "";

        try {
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();

            reader = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            response = reader.readLine().toString();
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
        } catch (MalformedURLException e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } catch (Exception e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("TAG", e.getLocalizedMessage());
                    return null;
                }
            }
        }

        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray albums = jsonObject
                    .getJSONObject("albums")
                    .getJSONArray("items");
            for (int i = 0; i < albums.length(); i++) {
                String albumName = albums.getJSONObject(i).getString("name");
                String album_url = albums.getJSONObject(i).getJSONArray("images").getJSONObject(0).getString("url");
                SpotifyItem spotifyItem = new SpotifyItem(albumName, album_url);
                spotifyTaskListener.onSpotifyItemAvailable(spotifyItem);
            }

        } catch (JSONException e) {
            Log.e("JSONException", e.getLocalizedMessage());
        }
    }
}
