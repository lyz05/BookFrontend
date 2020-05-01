package cc.home999.bookfrontend.Model;

public class BookReaderModel {

    private String bookno;
    private String bookname;
    private String author;
    private String press;
    private String publishdate_1;
    private String publishdate_2;
    private boolean check;

    public String getPublishdate_1() {
        return publishdate_1;
    }

    public String getPublishdate_2() {
        return publishdate_2;
    }

    public void setPublishdate_1(String publishdate_1) {
        this.publishdate_1 = publishdate_1;
    }

    public void setPublishdate_2(String publishdate_2) {
        this.publishdate_2 = publishdate_2;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public BookReaderModel(String bookno, String bookname, String author, String press, String publishdate_1, String publishdate_2, boolean check) {
        this.bookno = bookno;
        this.bookname = bookname;
        this.author = author;
        this.press = press;
        this.publishdate_1 = publishdate_1;
        this.publishdate_2 = publishdate_2;
        this.check = check;
    }

    public String getBookno() {
        return bookno;
    }

    public String getBookname() {
        return bookname;
    }

    public String getAuthor() {
        return author;
    }

    public String getPress() {
        return press;
    }

    public void setBookno(String bookno) {
        this.bookno = bookno;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPress(String press) {
        this.press = press;
    }

    @Override
    public String toString() {
        return "BookReaderModel{" + "bookno=" + bookno + ", bookname=" + bookname + ", author=" + author + ", press=" + press + ", publishdate_1=" + publishdate_1 + ", publishdate_2=" + publishdate_2 + ", check=" + check + '}';
    }
}
