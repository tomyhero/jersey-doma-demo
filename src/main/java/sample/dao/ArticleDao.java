package sample.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Delete;
import org.seasar.doma.Update;

import sample.AppConfig;
import sample.entity.Article;

@Dao(config = AppConfig.class)
  public interface ArticleDao {

    @Select
    List<Article> findAll();

    @Select
    Article find(int id);

    @Insert
    int save(Article s);

    @Update
    int update(Article s);

    @Delete
    int delete(Article s);

  }

