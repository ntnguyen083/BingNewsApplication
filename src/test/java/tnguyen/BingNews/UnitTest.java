package tnguyen.BingNews;

import org.json.JSONObject;
import org.junit.Test;
import tnguyen.BingNews.controller.RSSReader;
import tnguyen.BingNews.model.BingNewsConfig;
import tnguyen.BingNews.model.News;
import tnguyen.BingNews.model.RSSConfig;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.*;
import static tnguyen.BingNews.controller.RSSReader.extractItemList;
import static tnguyen.BingNews.controller.RSSReader.fetchJsonData;

public class UnitTest {

    @Test
    public void testReadCategoryFromJSON() throws IOException {
        String jsonPath = "E:\\BBV\\Source code\\BingNewsApplication\\src\\main\\resources\\NewsCategory.json";

        BingNewsConfig bingNewsConfig = RSSReader.readJSON(jsonPath, BingNewsConfig.class);

        assertNotNull(bingNewsConfig);

        assertTrue(bingNewsConfig.getCategories().size() > 0);
    }

    @Test
    public void testReadRSSJson() throws IOException {
        String jsonPath = "E:\\BBV\\Source code\\BingNewsApplication\\src\\main\\resources\\RSSConfig.json";

        RSSConfig rssConfig = RSSReader.readJSON(jsonPath, RSSConfig.class);

        assertNotNull(rssConfig);

        assertTrue(rssConfig.getListChanelConfig().size() > 0);
    }

    @Test
    public void testReadRSS() throws Exception {
        String rssUrl = "https://laodong.vn/rss/thoi-su.rss";
        String json = fetchJsonData(rssUrl);

        List<News> newsList = extractItemList(json.toString());

        assertNotNull(newsList);
        assertTrue(newsList.size() > 0);
    }
}