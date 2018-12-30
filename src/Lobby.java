import java.util.Arrays;
import java.util.Set;
import java.util.LinkedHashSet;


/**
 * Lobby represents a group of users (players and spectators)
 * which share a chat, and game instance. It does not fully
 * separate the api enpoint 
 */
class Lobby {
  public static final int MAX_USERS = 15; // lobby max

  /* instance vars */
  boolean isPrivateVar = false;
  Set<String> users = new LinkedHashSet<String>(10);
  Game game = null;

  public Lobby (boolean isPrivate ) {
    setIsPrivate(isPrivate);
  }

  // getter and setter for isPrivateVar
  public boolean isPrivate() {
    return this.isPrivateVar;
  }
  void setIsPrivate(boolean isPrivate) {
    this.isPrivateVar = isPrivate;
  }

  Set<String> getUsers() {
    return this.users;
  }

  public static class UserNameException
      extends RuntimeException {

    UserNameException( String msg ) {
      super( msg );
      
    }

    static UserNameException exists( String name ) {
      return new UserNameException( "name '" + name +"' taken" );
    }

    static UserNameException notExists( String name ) {
      return new UserNameException( "user '" + name +"' does not exist" );
    }
  }

  /**
   * adds a new player to the lobby, returning the
   * total number of players.
   *
   * @return the number of players in the loby, after the addition
   */
  public synchronized int addPlayer( String name )
      throws UserNameException {

    if ( getUsers().size() >= MAX_USERS ) {
      throw new RuntimeException( "lobby full" );
    }

    if ( getUsers().contains(name) ) {
      throw UserNameException.exists( name );
    }

    getUsers().add( name );

    return getUsers().size();
  }

  /**
   * removes a new player from the lobby, returning the
   * total number of players.
   *
   * @return the number of players in the loby, after the removal
   */
  public synchronized int rmUser( String name ) 
      throws UserNameException {

    if ( getUsers().size() <= 0 ) {
      return 0;
    }

    if ( !getUsers().remove( name ) ) {
      throw UserNameException.notExists( name );
    }

    return getUsers().size();
  }

  Game getGame() {
    return this.game;
  }

  void setGame( Game game ) {
    this.game = game;
  }

  public synchronized void startGame() {
    if ( getGame() != null ) { // game was already initialized
      return;
    }

    String[] users = getUsers().toArray(new String[0]);
    int numPlayers = Math.min(users.length, Game.MAX_PLAYERS);

    users = Arrays.copyOf(users, numPlayers);
    setGame( new Game( users ) );
  }
}