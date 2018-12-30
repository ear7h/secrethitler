import java.util.Map;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server implements HttpHandler{

  HttpServer server = null;
  String webRoot = ".";
  Map<String, Game> lobbies = null;

  public Server( int port, String webRoot ) throws IOException {
    var server = HttpServer.create( new InetSocketAddress( port ), 0 );
    server.createContext( "/", this );
    server.setExecutor( null );
    setServer( server );

    setWebRoot( webRoot );
  }

  // getter and setter for server
  void setServer( HttpServer server ) { this.server = server; }
  HttpServer getServer() { return this.server; }

  // getter and setter for webroot
  void setWebRoot( String webRoot ) { this.webRoot = webRoot; }
  String getWebRoot() { return this.webRoot; }

  public void start() {
    getServer().start();
  }

  @Override
  public void handle( HttpExchange exc ) throws IOException {
    String path = exc.getRequestURI().getPath();
    System.out.println( path );

    if ( path.startsWith("/api") ) {

      switch ( path ) {
      case "/api/lobby": // lobby join and create mechanism
        handleLobby( exc );
        break;
      case "/api/chat": // game chat connection upgrade
        handleChat( exc );
        break;
      }

      return;
    }

    path = path.replace("../", "");
    if ( path.endsWith( "/" ) ) {
      path += "index.html";
    }
    System.out.println("path: "+ path);

    File f = new File(getWebRoot() + path);
    if ( !f.canRead() || f.isDirectory() ) {
      String msg = "file not found";
      exc.sendResponseHeaders( 404, msg.length());
      OutputStream os = exc.getResponseBody();
      os.write( msg.getBytes() );
      os.close();

      return;
    }
    
    exc.sendResponseHeaders(200, f.length());
    Util.copy( exc.getResponseBody(),
        new FileInputStream( f ) );
  }

  public void handleLobby( HttpExchange exc ) throws IOException {
    exc.sendResponseHeaders(501, 0); // 501 not implemented
    exc.getResponseBody().close();
  }

  public void handleChat( HttpExchange exc ) throws IOException {
    exc.sendResponseHeaders(501, 0); // 501 not implemented
    exc.getResponseBody().close();
  }

  public static void main( String[] args ) throws Exception {
    for ( int i = 0; i < args.length; i++ ) {
      switch ( args[i] ) {
      case "1":
        Tests.test1();
        break;
      case "2":
        Tests.test2();
        break;
      default:
        throw new RuntimeException("test not found: " + args[i]);
      }
    }
  }
}


/**
 * Utility functions
 */
class Util {
  public static void copy(OutputStream out, InputStream in)
      throws IOException {
    int n;
    byte[] buf = new byte[1024];
    while( (n = in.read( buf )) > -1 ) {
      out.write( buf, 0, n );
    }
    out.close();
  }
}

class Tests {
  public static void test1() {
    try {
      new Server(8080, ".").start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void test2() {
    try {
      new Server(8080, "../web").start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
