package system_bookshop.models.entities;

import system_bookshop.models.enums.AgeRestriction;
import system_bookshop.models.enums.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@NamedStoredProcedureQuery(
        name = "countOfBooks",
        procedureName = "total_number_of_books_by_author",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "fullName", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "count", type = Integer.class)
        })
public class Book {

    private Long bookId;
    private String title; //(between 1..50 symbols)
    private String description; // (optional up to 1000 symbols)
    private EditionType editionType; //(NORMAL PROMO or GOLD)
    private BigDecimal price;
    private Integer copies;
    private Date releaseDate; //(optional)
    private AgeRestriction ageRestriction; // (MINOR TEEN or ADULT)
    private Author author;
    private Set<Category> categories;

    public Book() {
        this.categories = new HashSet<>();
    }

    public Book(String title, String description, EditionType editionType, BigDecimal price, Integer copies, Date releaseDate, AgeRestriction ageRestriction, Author author) {
        this();
        this.title = title;
        this.description = description;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    public Long getBookId() {
        return this.bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Column(name = "title", length = 50, nullable = false)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(length = 1000, nullable = true)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public EditionType getEditionType() {
        return this.editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    @Column(scale = 2, precision = 19, nullable = false)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false)
    public Integer getCopies() {
        return this.copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    @Column(nullable = true)
    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(nullable = false)
    public AgeRestriction getAgeRestriction() {
        return this.ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="author_id")
    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            joinColumns =
            @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
            inverseJoinColumns =
            @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
