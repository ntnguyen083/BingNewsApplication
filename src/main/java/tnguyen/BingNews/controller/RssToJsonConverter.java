package tnguyen.BingNews.controller;
import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RssToJsonConverter {
    public static JSONObject convertLaoDong(String rssUrl)
    {
        try {
            RssReader rssReader = new RssReader();
            List<Item> items = rssReader.read(rssUrl)
                    .collect(Collectors.toList());

            JSONObject json = new JSONObject();
            JSONObject channel = new JSONObject();
            JSONArray newsArray = new JSONArray();

            channel.put("title", items.get(0).getChannel().getTitle());
            channel.put("description", items.get(0).getChannel().getDescription());
            channel.put("link", items.get(0).getChannel().getLink());
            channel.put("image", items.get(0).getChannel().getImage().get().getUrl());
            channel.put("pubDate",items.get(0).getChannel().getPubDate().get());

            for (var item : items) {
                JSONObject newsItem = new JSONObject();
                newsItem.put("title", item.getTitle().get());
                newsItem.put("description", item.getDescription().get());
                newsItem.put("pubDate", item.getPubDate().get());
                newsItem.put("link", item.getLink().get());
                newsItem.put("guid", item.getGuid().get());
                newsItem.put("image",item.getEnclosure().get().getUrl());

                newsArray.put(newsItem);
            }

            channel.put("news", newsArray);
            json.put("channel", channel);

            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
