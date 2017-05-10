package com.nicknam.spotify;

/**
 * Created by snick on 10-5-2017.
 */

public class SpotifyItem {
    private String albumName;
    private String imageUrl;

    public SpotifyItem(String albumName, String imageUrl) {
        this.albumName = albumName;
        this.imageUrl = imageUrl;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "SpotifyItem{" +
                "albumName='" + albumName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
