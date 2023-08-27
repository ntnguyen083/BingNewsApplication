package tnguyen.BingNews.controller;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RssToJsonConverter {
    public static JSONObject convertLaoDong(String rssUrl) throws IOException {

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
        channel.put("pubDate", items.get(0).getChannel().getPubDate().get());

        for (var item : items) {
            JSONObject newsItem = new JSONObject();
            newsItem.put("title", item.getTitle().get());
            newsItem.put("description", item.getDescription().get());
            newsItem.put("pubDate", item.getPubDate().get());
            newsItem.put("link", item.getLink().get());
            newsItem.put("guid", item.getGuid().get());
            newsItem.put("image", item.getEnclosure().get().getUrl());

            newsArray.put(newsItem);
        }
        channel.put("news", newsArray);
        json.put("channel", channel);

        return json;
    }

    public static JSONObject convertVnExpress(String rssUrl) throws IOException {

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
        channel.put("pubDate", items.get(0).getChannel().getPubDate().get());

        String searchTag = "</a></br>";
        String startTag = "<img src=\"";
        String endTag = "\"";

        for (var item : items) {
            JSONObject newsItem = new JSONObject();
            newsItem.put("title", item.getTitle().get());

            int startDeIndex = item.getDescription().get().indexOf(searchTag);

            if (startDeIndex != -1) {
                String extractedText = item.getDescription().get().substring(startDeIndex + searchTag.length());
                newsItem.put("description", extractedText);
            }

            //newsItem.put("description", item.getDescription().get());
            newsItem.put("pubDate", item.getPubDate().get());
            newsItem.put("link", item.getLink().get());
            newsItem.put("guid", item.getGuid().get());

            int startIndex = item.getDescription().get().indexOf(startTag);
            if (startIndex != -1) {
                startIndex += startTag.length();
                int endIndex = item.getDescription().get().indexOf(endTag, startIndex);
                if (endIndex != -1) {
                    newsItem.put("image", item.getDescription().get().substring(startIndex, endIndex));
                }
            }

            newsArray.put(newsItem);
        }
        channel.put("news", newsArray);
        json.put("channel", channel);

        return json;
    }

    public static JSONObject convertTienPhong(String rssUrl) {
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
            channel.put("pubDate", items.get(0).getChannel().getPubDate().get());

            String startTag = "src=\"";
            String endTag = "\"";

            for (var item : items) {
                JSONObject newsItem = new JSONObject();
                newsItem.put("title", item.getTitle().get());
                newsItem.put("description", item.getDescription().get());
                newsItem.put("pubDate", item.getPubDate().get());
                newsItem.put("link", item.getLink().get());
                newsItem.put("guid", item.getGuid().get());

                int startIndex = item.getDescription().get().indexOf(startTag);
                if (startIndex != -1) {
                    startIndex += startTag.length();
                    int endIndex = item.getDescription().get().indexOf(endTag, startIndex);
                    if (endIndex != -1) {
                        String image = item.getDescription().get().substring(startIndex, endIndex);
                        newsItem.put("image", image.replace("80x80", "600x315"));
                    }
                }

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
