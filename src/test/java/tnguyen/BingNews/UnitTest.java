package tnguyen.BingNews;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import tnguyen.BingNews.controller.RssToJsonConverter;
import tnguyen.BingNews.controller.parseData.ParseNewsData;
import tnguyen.BingNews.model.News;

import java.util.List;

import static org.testng.AssertJUnit.*;

public class UnitTest {

    @Test
    public void testReadCategoryFromJSON() {

    }

    @Test
    public void testReadLaoDongRss() {
        String rssUrl = "https://laodong.vn/rss/thoi-su.rss"; // Replace with the actual RSS feed URL

        JSONObject json = RssToJsonConverter.convertLaoDong(rssUrl);

        assertNotNull(json);
        System.out.println(json.toString(2));
    }

    @Test
    public void testMapNewsToObject() {
        String rssUrl = "https://laodong.vn/rss/thoi-su.rss"; // Replace with the actual RSS feed URL

        JSONObject json = RssToJsonConverter.convertLaoDong(rssUrl);

        List<News> newsList = ParseNewsData.parseNews(json);

        assertNotNull(newsList);
        assertTrue(newsList.size() > 0);
    }

}