package tnguyen.BingNews;

import org.junit.Test;
import tnguyen.BingNews.controller.APIReader;
import tnguyen.BingNews.controller.RSSReader;
import tnguyen.BingNews.model.BingNewsConfig;
import tnguyen.BingNews.model.News;
import tnguyen.BingNews.model.RSSConfig;
import tnguyen.BingNews.model.WeatherInfo;
import tnguyen.BingNews.repository.NewsRepository;
import tnguyen.BingNews.repository.implement.JDBCNewsRepository;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.*;
import static tnguyen.BingNews.controller.RSSReader.*;

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
        String json = fetchRSS(rssUrl);

        List<News> newsList = mapItemList(json.toString());

        assertNotNull(newsList);
        assertTrue(newsList.size() > 20);
    }

    @Test
    public void testInsertNewsToDB() throws Exception{
        NewsRepository newsRepository = new JDBCNewsRepository();
        String rssUrl = "https://laodong.vn/rss/thoi-su.rss";
        String json = fetchRSS(rssUrl);

        List<News> newsList = mapItemList(json.toString());

        for (var news : newsList) {
            newsRepository.insertNews(news);
        }

        assertTrue(newsRepository.getAllNews().size() > 0);
    }

    @Test
    public void testReadAPIRequest() throws IOException{
        String apiUrl = "https://api.weatherapi.com/v1/forecast.json?key=f845cd8d15dc447784562628232208&q=Ho%20Chi%20Minh";

        String result = APIReader.readAPIRequest(apiUrl);

        assertNotNull(result);
    }

    @Test
    public void testParseAPI() throws Exception{
        String apiUrl = "https://api.weatherapi.com/v1/forecast.json?key=f845cd8d15dc447784562628232208&q=Ho%20Chi%20Minh";

        WeatherInfo weatherInfo = APIReader.extractWeatherInfo(apiUrl);

        assertNotNull(weatherInfo);
    }
}