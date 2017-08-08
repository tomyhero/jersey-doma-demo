package sample.entity;

import java.sql.Timestamp;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

public class ArticleListener<E extends Article> implements EntityListener<E> {

  @Override
  public void preInsert(E article, PreInsertContext<E> context) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    article.setUpdatedAt(timestamp);
    article.setCreatedAt(timestamp);
  }

  @Override
  public void preUpdate(E article, PreUpdateContext<E> context) {
    if (context.isEntityChanged()) {
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      article.setUpdatedAt(timestamp);
    }
  }

}
