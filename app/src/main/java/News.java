public class News {
    private String Ntitle;
    private String Nauthor;
    private String NwebUrl;

    public News(String title, String author, String webUrl) {
        Ntitle = title;
        Nauthor = author;
        NwebUrl = webUrl;
    }
    public String getTitle() {
        return Ntitle;
    }
    public void setTitle(String title) {
        Ntitle = title;
    }
    public String getAuthor() {
        return Nauthor;
    }
    public void setAuthor(String author) {
        Nauthor = author;
    }
    public String getWebUrl() {
        return NwebUrl;
    }
    public void setWebUrl(String webUrl) {
        NwebUrl = webUrl;
    }
    @Override
    public String toString() {
        return "News{" + "title='" + Ntitle + '\'' + ", author='" + Nauthor + '\'' + ", webUrl='" + NwebUrl + '\'' + '}';
    }
}