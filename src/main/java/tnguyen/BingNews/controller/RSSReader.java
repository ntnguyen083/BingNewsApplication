package tnguyen.BingNews.controller;

import tnguyen.BingNews.controller.parseData.DataFactory;
import tnguyen.BingNews.controller.parseData.ParseNewsData;
import tnguyen.BingNews.controller.parseData.factory.NewsFactory;
import tnguyen.BingNews.model.ChannelConfig;
import tnguyen.BingNews.model.News;
import tnguyen.BingNews.model.RSSConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RSSReader {
    public static void main(String[] args) throws IOException {
        String rssUrl = "https://laodong.vn/rss/thoi-su.rss";
        String json = fetchJsonData(rssUrl);

        List<News> newsList = extractItemList(json.toString());
        for (News news : newsList) {
            news.printOutInfo();
        }
    }

    public static <T> List<T> extractItemList(String rssXml) throws IOException {
        List<T> itemList = new ArrayList<>();

        String channelLink = getChannelLink(rssXml);

        String channel = getChannelByLink(channelLink);

        //Read RSSConfig.json
        String jsonPath = "E:\\BBV\\Source code\\BingNewsApplication\\src\\main\\resources\\RSSConfig.json";
        RSSConfig rssConfig = ParseNewsData.ReadJSON(jsonPath, RSSConfig.class);

        ChannelConfig channelConfig = new ChannelConfig();

        for (var item : rssConfig.getListChanelConfig()) {
            if (item.getChannelConfigName().equals(channel)) {
                channelConfig = item;
                break;
            }
        }

        itemList = (List<T>) parseData(rssXml, channelConfig, new NewsFactory());

        return itemList;
    }

    public static String extractValue(String input, String pattern) {
        Pattern valuePattern = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher valueMatcher = valuePattern.matcher(input);
        if (valueMatcher.find()) {
            return valueMatcher.group(1);
        }
        return "";
    }

    public static String fetchJsonData(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            throw new IOException("Failed to fetch data. Response code: " + responseCode);
        }
    }

    public static String getChannelLink(String rssXml) {
        Pattern channelPattern = Pattern.compile("<channel>(.*?)</channel>", Pattern.DOTALL);
        Matcher channelMatcher = channelPattern.matcher(rssXml);
        String channelLink = "";
        while (channelMatcher.find()) {
            String channelContent = channelMatcher.group(1);
            channelLink = extractValue(channelContent, "<link>(.*?)</link>");
        }
        return channelLink;
    }

    public static String getChannelByLink(String link) throws IOException {
        URL url = new URL(link);
        String host = url.getHost();
        String[] hostParts = host.split("\\.");
        return hostParts[0];
    }

    public static <T> List<T> parseData(String rssXml, ChannelConfig channelConfig, DataFactory<T> factory) {
        List<T> list = new ArrayList<>();
        Pattern itemPattern = Pattern.compile("<item>(.*?)</item>", Pattern.DOTALL);
        Matcher itemMatcher = itemPattern.matcher(rssXml);

        while (itemMatcher.find()) {
            String itemContent = itemMatcher.group(1);

            String guid = extractValue(itemContent, channelConfig.getGUIDXPath());
            String title = extractValue(itemContent, channelConfig.getTitleXPath());
            String description = extractValue(itemContent, channelConfig.getDescriptionXPath());
            String link = extractValue(itemContent, channelConfig.getLinkXPath());
            String pubDate = extractValue(itemContent, channelConfig.getPubDateXPath());
            String image = extractValue(itemContent, channelConfig.getImageXPath());

            T news = factory.createNews(guid, title, description, link, pubDate, image);
            list.add(news);
        }

        return list;
    }


}
