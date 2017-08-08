package sample.entity;

import java.util.Date;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;
import sample.entity.ArticleListener;
import java.sql.Timestamp;

/*
create table article ( 
   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '',
  `body` text NOT NULL,
  `created_at` datetime ,
  `updated_at` datetime ,
  PRIMARY KEY (`id`)
 );
 insert into article(title,body,created_at,updated_at) values('title','body',NOW(),NOW());
 */

@Entity(naming = NamingType.LOWER_CASE,listener = ArticleListener.class)
  public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String body;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public int getId() {
      return id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getBody() {
      return body;
    }

    public void setBody(String body) {
      this.body = body;
    }

    public Date getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(Timestamp t) {
      this.createdAt = t;
    }



    public Date getUpdatedAt() {
      return createdAt;
    }

    public void setUpdatedAt(Timestamp t) {
      this.updatedAt = t;
    }

  }
