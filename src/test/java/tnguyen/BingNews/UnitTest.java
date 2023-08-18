package tnguyen.BingNews;

import org.junit.jupiter.api.Test;
import tnguyen.BingNews.model.AdArticle;
import tnguyen.BingNews.repository.AdArticleRepository;
import tnguyen.BingNews.repository.implement.JDBCAdArticleRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    @Test
    public void testGetListAdArticle()
    {
        // Create an instance of the repository
        AdArticleRepository adArticleRepository = new JDBCAdArticleRepository();

        // Get all adArticles from the database
        List<AdArticle> adArticleList = adArticleRepository.getAllAdArticle();

        assertEquals(1, adArticleList.size());
    }
    @Test
    public void testInsertAdArticle()
    {
        // Create an instance of the repository
        AdArticleRepository adArticleRepository = new JDBCAdArticleRepository();

        // Create a AdArticle object with data
        AdArticle adArticle = new AdArticle();
        adArticle.setID(UUID.randomUUID().toString());
        adArticle.setImgURL("https://images.dable.io/thumbnail/images.dable.io/…00/ac4/a3e2942e1405491abed8a8b5e575a21b655ea.jpeg");
        adArticle.setTitle("Bạn có bị tiểu đường không? Bạn nên đọc");
        adArticle.setSourceURL("https://centious.com/wcvyk5?utm_source=dable&utm_medium=vn.msn.com&utm_campaign=Glucoactive+VN&utm_content=6&ecid=RQUFulhT8WutPQ2VjwbhnsIarr9cIXIPpfzql9XaC3sqt1e5oiNhi0fK9xRCprIr&request_id=e179e09008154242bcaf1f0728ec1b40");

        // Insert the new user into the database
        adArticleRepository.insertAdArticle(adArticle);

        List<AdArticle> adArticleList = adArticleRepository.getAllAdArticle();

        assertEquals(1, adArticleList.size());
    }

    public void testUpdateArticle()
    {

    }

}