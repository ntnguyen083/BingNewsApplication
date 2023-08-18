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
    public void testGetListAdArticle() {
        // Create an instance of the repository
        AdArticleRepository adArticleRepository = new JDBCAdArticleRepository();

        // Get all adArticles from the database
        List<AdArticle> adArticleList = adArticleRepository.getAllAdArticle();

        assertEquals(1, adArticleList.size());
    }

    @Test
    public void testInsertAdArticle() {
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

    @Test
    public void testGetAdArticleByID() {
        // Create an instance of the repository
        AdArticleRepository adArticleRepository = new JDBCAdArticleRepository();

        String userIdToRetrieve = "58de5bff-956c-48f3-a01e-cf0da4b94fb3"; // Set the ID of the article you want to retrieve

        // Get the user from the database by ID
        AdArticle retrievedUser = adArticleRepository.getAdArticleById(userIdToRetrieve);

        assertEquals(userIdToRetrieve, retrievedUser.getID());
    }

    @Test
    public void testUpdateArticle() {
        // Create an instance of the repository
        AdArticleRepository adArticleRepository = new JDBCAdArticleRepository();

        // Select AdArticle want to update
        String selectAdArticle = "58de5bff-956c-48f3-a01e-cf0da4b94fb3";

        // Create data want to update
        String imgURL = "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AAHghMa?w=300&h=157&q=60&m=6&f=jpg&u=t";
        String title = "Quang cảnh các thành phố trên khắp thế giới";
        String sourceURL = "https://www.msn.com/vi-vn/lifestyle/travel/nh%E1%BB%AFng-c%E1%BA%A3nh-quan-thi%C3%AAn-nhi%C3%AAn-di%E1%BB%85m-l%E1%BB%87/ss-AAL4HVj?ocid=msedgdhp&pc=U531&cvid=03a29467d21643b78cbb204bf8acd345&ei=17";

        // Update the user in the database
        adArticleRepository.updateAdArticle(imgURL, title, sourceURL, selectAdArticle);

        AdArticle retrievedUser = adArticleRepository.getAdArticleById(selectAdArticle);
        assertEquals(title, retrievedUser.getTitle());
    }

    @Test
    public void testDeleteArticle()
    {
        // Create an instance of the repository
        AdArticleRepository adArticleRepository = new JDBCAdArticleRepository();

        String selectAdArticle = "2984b317-4f86-435b-8f21-8fc9f92b4932"; // Set the ID of the user you want to delete

        // Delete the user from the database
        adArticleRepository.deleteAdArticle(selectAdArticle);

        List<AdArticle> adArticleList = adArticleRepository.getAllAdArticle();
        assertEquals(0, adArticleList.size());
    }

}