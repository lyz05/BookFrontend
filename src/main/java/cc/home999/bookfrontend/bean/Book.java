package cc.home999.bookfrontend.bean;

import java.math.BigDecimal;

public class Book {

    private String bookno;

    private String bookname;

    private String author;

    private String press;

    private BigDecimal price;

    private String publishdate;

    private Integer shopnum;

    public Book() {
    }

    public Book(String bookno, String bookname, String author, String press) {
        this.bookno = bookno;
        this.bookname = bookname;
        this.author = author;
        this.press = press;
    }

    public Book(String bookno, String bookname, String author, String press, String price, String publishdate, String shopnum) {
        this.bookno = bookno;
        this.bookname = bookname;
        this.author = author;
        this.press = press;
        this.price = price.equals("") ? null : new BigDecimal(price);
        this.publishdate = publishdate;
        this.shopnum = shopnum.equals("") ? null : new Integer(shopnum);
    }

    @Override
    public String toString() {
        return "Book [bookno=" + bookno + ", bookname=" + bookname + ", author=" + author + ", press=" + press
                + ", price=" + price + ", publishdate=" + publishdate + ", shopnum=" + shopnum + "]";
    }

    public String getBookno() {
        return bookno;
    }

    public void setBookno(String bookno) {
        this.bookno = bookno == null ? null : bookno.trim();
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    public Date getPublishdate() {
//        return publishdate;
//    }
    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public Integer getShopnum() {
        return shopnum;
    }

    public void setShopnum(Integer shopnum) {
        this.shopnum = shopnum;
    }
}
