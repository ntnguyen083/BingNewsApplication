package tnguyen.BingNews.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import tnguyen.BingNews.controller.factory.DataFactory;
import tnguyen.BingNews.controller.factory.implement.NewsFactory;
import tnguyen.BingNews.model.ChannelConfig;
import tnguyen.BingNews.model.News;
import tnguyen.BingNews.model.RSSConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RSSReader {
    public static void main(String[] args) throws Exception {
        String rssUrl = "https://laodong.vn/rss/thoi-su.rss";
        String json = fetchJsonData(rssUrl);

        List<News> newsList = extractItemList(json.toString());
        for (News news : newsList) {
            news.printOutInfo();
        }
    }

    public static <T> List<T> extractItemList(String rssXml) throws Exception {
        List<T> itemList = new ArrayList<>();

        String channelLink = getChannelLink(rssXml);

        String channel = getChannelByLink(channelLink);

        //Read RSSConfig.json
        String jsonPath = "E:\\BBV\\Source code\\BingNewsApplication\\src\\main\\resources\\RSSConfig.json";
        RSSConfig rssConfig = readJSON(jsonPath, RSSConfig.class);

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

    public static <T> List<T> parseData(String rssXml, ChannelConfig channelConfig, DataFactory<T> factory) throws Exception{
        List<T> list = new ArrayList<>();
        Pattern itemPattern = Pattern.compile("<item>(.*?)</item>", Pattern.DOTALL);
        Matcher itemMatcher = itemPattern.matcher(rssXml);
        String type = channelConfig.getType();
        while (itemMatcher.find()) {
            String itemContent = itemMatcher.group(1);
            T obj = null;
            switch (type) {
                case "news":
                    obj = factory.createNews();
                    break;
                default:
                    break;
            }
            // Set property value using reflection
            for (var item : channelConfig.getPropertiesConfig()) {
                setPropertyValue(obj, item.getPropertyName(), extractValue(itemContent, item.getPath()));
            }
            list.add(obj);
        }

        return list;
    }

    public static void setPropertyValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static Object getPropertyValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    public static <T> T readJSON(String jsonPath, Class<T> classConfig) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(jsonPath), classConfig);
    }


}
