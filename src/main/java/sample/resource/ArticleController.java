package sample.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;
import javax.ws.rs.DefaultValue;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Request;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import org.seasar.doma.jdbc.tx.TransactionManager;

import sample.resource.NotFoundException;
import sample.resource.InvalidParametersException;
import sample.entity.Article;
import sample.AppConfig;
import sample.dao.ArticleDao;


@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleController {

  // curl -XDELETE http://localhost:8090/articles/1/
  @DELETE
  @Path("/{id}")
  public Response deleteArticle(@PathParam("id") int id) throws NotFoundException {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    boolean ok = tm.required(() -> {
      ArticleDao dao = new sample.dao.ArticleDaoImpl();
      Article entity = dao.find(id);
      if ( entity == null  ) {
        return false;
      }
      dao.delete(entity);
      return true;
    });

    if (!ok) {
      throw new NotFoundException();
    }

    return Response.ok("{ok:1}").build();
  }


  // curl -XPUT http://localhost:8090/articles/1/ --data "title=title&body=body"
  @PUT
  @Path("/{id}")
  public Article putArticle(@PathParam("id") int id,@Context Request request,@FormParam("title") String title, @FormParam("body") String body) throws NotFoundException, InvalidParametersException {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    if ( title == null || body == null  ) {
      throw new InvalidParametersException();
    }

    Article article = tm.required(() -> {
      ArticleDao dao = new sample.dao.ArticleDaoImpl();
      Article entity = dao.find(id);

      if ( entity == null  ) {
        return null;
      }

      entity.setTitle(title);
      entity.setBody(body);
      dao.update(entity);
      return entity;
    });

    if (article == null ) {
      throw new NotFoundException();
    }
    return article;
  }

  // curl -XPOST http://localhost:8090/articles/ --data "title=title&body=body"
  @POST
  public Article postArticle(@Context Request request,@FormParam("title") String title, @FormParam("body") String body) throws InvalidParametersException {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    if ( title == null || body == null  ) {
      throw new InvalidParametersException();
    }

    Article article = tm.required(() -> {
      ArticleDao dao = new sample.dao.ArticleDaoImpl();
      Article entity = new Article();
      entity.setTitle(title);
      entity.setBody(body);
      dao.save(entity);
      return entity;
    });
    return article;
  }

  @GET
  @Path("/{id}")
  public Article getArticleById(@PathParam("id") int id) throws NotFoundException{
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    Article article = tm.required(() -> {
      ArticleDao dao = new sample.dao.ArticleDaoImpl();
      return dao.find(id);
    });

    if (article == null ) {
      throw new NotFoundException();
    }

    return article;
  }

}
