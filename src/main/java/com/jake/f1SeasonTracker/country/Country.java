package com.jake.f1SeasonTracker.country;

import com.jake.f1SeasonTracker.track.F1Track;
import jakarta.persistence.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

@Entity
public class Country {

    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    String name;

    @OneToMany(mappedBy="country")
    private Set<F1Track> items;

    public Country(String name) {
        this.name = name;
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    private String getEmojiOverAPI() throws Exception {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        String urlString = "https://emoji-api.com/emojis/flag-"+this.name.toLowerCase()+"?access_key="+getProperty();
        URL url = new URL(urlString);
        HttpURLConnection conn = getHttpURLConnection(url);
        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        reader.close();
        return parseEmoji(responseContent.toString());
    }

    private static HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
        conn.setReadTimeout(5000);
        return conn;
    }

    private String getProperty() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("application.properties"));
        return properties.getProperty("emoji.api.key");
    }

    public static String parseEmoji(String responseBody) throws JSONException {
        JSONArray results = new JSONArray(responseBody);
        for (int i = 0 ; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            return result.getString("character");
        }
        return null;
    }
}
