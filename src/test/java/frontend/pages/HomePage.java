package frontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    private By btnAddBook = By.xpath("//*[@id=\"root\"]/div[2]/div/div/button");
    private By inputTitle = By.id("title");
    private By inputAuthor = By.id("author");
    private By inputGenre = By.id("genre");
    private By inputImageUrl = By.id("image_url");
    private By inputPrice = By.id("price");
    private By inputQuantity = By.id("quantity");
    private By btnSubmitAddBook = By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div[2]/div/button");
    private By notification = By.cssSelector("div.Toastify__toast-body div:nth-child(2)");

    private By inputSearch = By.cssSelector("input[placeholder=\"Search\"]");
    private By btnSearch = By.xpath("//*[@id=\"root\"]/div[3]/div/div[1]/form/button");
    private By titleFirstBook = By.xpath("//*[@id=\"root\"]/div[3]/div/div[2]/div/div[1]/div[3]/span[1]");

    private By btnSortBy = By.xpath("//*[@id=\"root\"]/div[3]/div/div[1]/div[2]");
    private By optionSortByPriceASC = By.xpath("//*[@id=\"root\"]/div[3]/div/div[1]/div[2]/div/div[5]");
    private By priceFirstBook = By.xpath("//*[@id=\"root\"]/div[3]/div/div[2]/div/div[1]/div[4]/span[2]");
    private By priceSecondBook = By.xpath("//*[@id=\"root\"]/div[3]/div/div[2]/div/div[2]/div[4]/span[2]");


    private By btnUpdateBook = By.cssSelector("img[src=\"/src/assets/edit.svg\"]");
    private By btnSubmitUpdateBook = By.xpath("//*[@id=\"root\"]/div[3]/div/div[2]/div/div[2]/div[2]/div/button");

    private By btnDeleteBook = By.cssSelector("img[src=\"/src/assets/delete.svg\"]");
    private By btnSubmitDeleteBook = By.xpath("//*[@id=\"root\"]/div[3]/div/div[2]/div/div[3]/div[2]/div/button");

    public void openAddBookModal() {
        this.clickear(btnAddBook);
    }

    public void writeTitleAddModal(String title) {
        this.sendText(title, inputTitle);
    }

    public void writeAuthorAddModal(String author) {
        this.sendText(author, inputAuthor);
    }

    public void writeGenreAddModal(String genre) {
        this.sendText(genre, inputGenre);
    }

    public void writeImageUrlAddModal(String imageUrl) {
        this.sendText(imageUrl, inputImageUrl);
    }

    public void writePriceAddModal(String price) {
        this.sendText(price, inputPrice);
    }

    public void writeQuantityAddModal(String quantity) {
        this.sendText(quantity, inputQuantity);
    }

    public void submitAddBook() {
        this.clickear(btnSubmitAddBook);
    }

    public String getNotificationText() {
        return this.getText(notification);
    }

    public void writeSearchInput (String text) {
        this.clickear(inputSearch);
        this.sendText(text, inputSearch);
    }

    public void search() {
        this.clickear(btnSearch);
    }

    public String getTitleFirstBook() {
        return this.getText(titleFirstBook);
    }

    public String getPriceFirstBook() {
        return this.getText(priceFirstBook);
    }

    public String getPriceSecondBook() {
        return this.getText(priceSecondBook);
    }

    public void sortByPriceASC() {
        this.clickear(btnSortBy);
        this.clickear(optionSortByPriceASC);
    }

    public void openUpdateModalFirstBook() {
        WebElement btnUpdateFirstBook = this.getElementFromElementList(btnUpdateBook, 0);
        btnUpdateFirstBook.click();
    }

    public void writeTitleUpdateModal(String title) {
        WebElement titleInpuUpdateModal = this.getElementFromElementList(inputTitle, 1);
        titleInpuUpdateModal.clear();
        titleInpuUpdateModal.sendKeys(title);
    }

    public void writePriceUpdateModal(String price) {
        WebElement priceInputUpdateModal = this.getElementFromElementList(inputPrice, 1);
        priceInputUpdateModal.clear();
        priceInputUpdateModal.sendKeys(price);
    }

    public void submitUpdateBook() {
        this.clickear(btnSubmitUpdateBook);
    }


    public void openDeleteModalFirstBook() {
        WebElement btnDeleteFirstBook = this.getElementFromElementList(btnDeleteBook, 0);
        btnDeleteFirstBook.click();
    }

    public void submitDeleteBook() {
        this.clickear(btnSubmitDeleteBook);
    }
}
