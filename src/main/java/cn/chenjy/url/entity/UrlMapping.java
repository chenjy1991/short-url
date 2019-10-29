package cn.chenjy.url.entity;


import javax.persistence.*;

@Entity
@Table(name = "url_mapping")
public class UrlMapping {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(length = 6)
    private String shortUrlCode;
    @Column(length = 511)
    private String longUrlPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortUrlCode() {
        return shortUrlCode;
    }

    public void setShortUrlCode(String shortUrlCode) {
        this.shortUrlCode = shortUrlCode;
    }

    public String getLongUrlPath() {
        return longUrlPath;
    }

    public void setLongUrlPath(String longUrlPath) {
        this.longUrlPath = longUrlPath;
    }
}
