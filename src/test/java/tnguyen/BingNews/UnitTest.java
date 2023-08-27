package tnguyen.BingNews;

import org.json.JSONObject;
import org.junit.Test;
import tnguyen.BingNews.controller.RssToJsonConverter;
import tnguyen.BingNews.controller.parseData.ParseNewsData;
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

        BingNewsConfig bingNewsConfig = ParseNewsData.ReadJSON(jsonPath, BingNewsConfig.class);

        assertNotNull(bingNewsConfig);

        assertTrue(bingNewsConfig.getCategories().size() > 0);
    }

    @Test
    public void testReadRSSJson() throws IOException {
        String jsonPath = "E:\\BBV\\Source code\\BingNewsApplication\\src\\main\\resources\\RSSConfig.json";

        RSSConfig rssConfig = ParseNewsData.ReadJSON(jsonPath, RSSConfig.class);

        assertNotNull(rssConfig);

        assertTrue(rssConfig.getListChanelConfig().size() > 0);
    }

    @Test
    public void testReadLaoDongRss() throws IOException {
        String rssUrl = "https://laodong.vn/rss/thoi-su.rss"; // Replace with the actual RSS feed URL

        JSONObject json = RssToJsonConverter.convertLaoDong(rssUrl);

        assertNotNull(json);
    }

    @Test
    public void testReadVnExpressRss() throws IOException {
        String rssUrl = "https://vnexpress.net/rss/thoi-su.rss"; // Replace with the actual RSS feed URL

        JSONObject json = RssToJsonConverter.convertVnExpress(rssUrl);

        assertNotNull(json);
    }

    @Test
    public void testReadTienPhongRss() {
        String rssUrl = "https://tienphong.vn/rss/thoi-su-421.rss"; // Replace with the actual RSS feed URL

        JSONObject json = RssToJsonConverter.convertTienPhong(rssUrl);

        assertNotNull(json);
    }

    @Test
    public void testMapNewsToObject() throws IOException {
        String rssUrl = "https://laodong.vn/rss/thoi-su.rss"; // Replace with the actual RSS feed URL

        JSONObject json = RssToJsonConverter.convertLaoDong(rssUrl);

        List<News> newsList = ParseNewsData.parseNews(json);

        for (News news : newsList) {
            news.printOutInfo();
        }

        assertNotNull(newsList);
        assertTrue(newsList.size() > 0);
    }

    @Test
    public void testReadMappingNews() throws IOException {
        String rssUrl = "https://tienphong.vn/rss/thoi-su-421.rss";
        String json = fetchJsonData(rssUrl);

        List<News> newsList = extractItemList(json.toString());

        assertNotNull(newsList);
    }

}