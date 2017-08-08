package sample;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.grizzly.http.server.HttpServer;

public class Main {

  private static final URI BASE_URI = URI.create("http://localhost:8090/");

  public static void main(String[] args) throws IOException {

    try {
      System.out.println("Starting Server");

      final ResourceConfig resourceConfig = ResourceConfig.forApplicationClass(MainResourceConfig.class);
      final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);
      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
        @Override
        public void run() {
          server.shutdownNow();
        }
      }));
      server.start();

      Thread.currentThread().join();
    } catch (IOException | InterruptedException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }


  }

  public static class MainResourceConfig extends ResourceConfig {

    public MainResourceConfig() {
      packages("sample.resource");
    }
  }
}
