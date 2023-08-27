package tnguyen.BingNews.controller.parseData;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import tnguyen.BingNews.model.BingNewsConfig;
import tnguyen.BingNews.model.Category;
import tnguyen.BingNews.model.Channel;
import tnguyen.BingNews.model.News;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParseNewsData {
    public static List<News> parseNews(JSONObject json) {
        // Extract the "news" array from the JSON object
        JSONArray newsArray = json.getJSONObject("channel").getJSONArray("news");
        // Create a list to store News objects
        List<News> newsList = new ArrayList<>();

        // Iterate through the newsArray and create News objects
        for (int i = 0; i < newsArray.length(); i++) {
            JSONObject newsObject = newsArray.getJSONObject(i);

            String guid = newsObject.optString("guid");
            String title = newsObject.optString("title");
            String description = newsObject.optString("description");
            String pubDate = newsObject.optString("pubDate");
            String link = newsObject.optString("link");
            String image = newsObject.optString("image");

            News news = new News(guid, title, description, link, pubDate, image);
            newsList.add(news);
        }
        return newsList;
    }

    public static <T> T ReadJSON(String jsonPath, Class<T> classConfig) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(jsonPath), classConfig);
    }
}
