package frontend.test;

import frontend.pages.BasePage;
import frontend.pages.HomePage;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Home {
    private HomePage homePage;
    @BeforeAll
    public static void setupDriver() {
        BasePage.setupChromeDriver();
    }

    @BeforeEach
    public void setupRegisterPage() throws InterruptedException {
        homePage = new HomePage();
        homePage.url("http://localhost:5173");
    }

    @AfterEach
    public void closeRegistePge() throws InterruptedException {
        homePage.close();
    }

    @Test
    @Order(1)
    public void addBook() {
        homePage.openAddBookModal();
        homePage.writeTitleAddModal("Test selenium");
        homePage.writeAuthorAddModal("Selenium webdriver");
        homePage.writeGenreAddModal("IT");
        homePage.writeImageUrlAddModal("http://selenium-webdriver.jpg");
        homePage.writePriceAddModal("1000.00");
        homePage.writeQuantityAddModal("10");
        homePage.submitAddBook();
        String notificationText = homePage.getNotificationText();

        assertEquals("Book added successfully!", notificationText);
    }

    @Test
    @Order(2)
    public void searchBookByTitle() {
        homePage.writeSearchInput("Test selenium");
        homePage.search();
        String title = homePage.getTitleFirstBook();

        assertEquals("Test selenium", title);
    }

    @Test
    @Order(3)
    public void sortByPrice() {
        homePage.sortByPriceASC();
        String priceFirstBook = homePage.getPriceFirstBook().replace("$", "");
        String priceSecondBook = homePage.getPriceSecondBook().replace("$", "");
        BigDecimal priceFirstBookDecimal = new BigDecimal(priceFirstBook);
        BigDecimal priceSecondBookDecimal = new BigDecimal(priceSecondBook);

        assertTrue(priceFirstBookDecimal.compareTo(priceSecondBookDecimal) <= 0);
    }

    @Test
    @Order(4)
    public void updateBook() {
        homePage.writeSearchInput("Test selenium");
        homePage.search();
        String titleFirstBookBeforeUpdate = homePage.getTitleFirstBook();
        String priceFirstBookBeforeUpdate = homePage.getPriceFirstBook();

        homePage.openUpdateModalFirstBook();
        homePage.writeTitleUpdateModal("Test selenium updated");
        homePage.writePriceUpdateModal("2500.99");
        homePage.submitUpdateBook();

        String notificationText = homePage.getNotificationText();

        String titleFirstBookAfterUpdate = homePage.getTitleFirstBook();
        String priceFirstBookAfterUpdate = homePage.getPriceFirstBook();

        assertEquals("Book updated successfully!", notificationText);

        assertNotEquals(titleFirstBookBeforeUpdate, titleFirstBookAfterUpdate);
        assertNotEquals(priceFirstBookBeforeUpdate, priceFirstBookAfterUpdate);

        assertEquals("Test selenium updated", titleFirstBookAfterUpdate);
        assertEquals("$2500.99", priceFirstBookAfterUpdate);
    }

    @Test
    @Order(5)
    public void deleteBook() {
        homePage.writeSearchInput("Test selenium updated");
        homePage.search();

        homePage.openDeleteModalFirstBook();
        homePage.submitDeleteBook();

        String notificationText = homePage.getNotificationText();

        assertEquals("Book deleted successfully!", notificationText);
    }
}
