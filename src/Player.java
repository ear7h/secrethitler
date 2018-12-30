import java.util.Random;
import java.security.SecureRandom;

public class Player {
  public enum Party {
    Facist, Liberal, Spectator
  }

  /**
   * Explicitly make hitler
   */
  public static Player newHitler( String name ) {
    return new Player( name, Party.Facist, true );
  }

  /* instance vars */
  private final boolean isHitlerVar;
  private final Party party;
  private final String name;

  public Player( String name, Party party ) {
    // set values
    this.name = name;
    this.party = party;
    this.isHitlerVar = false;
  }

  /**
   * The only way to make Hitler, and it's private forcing
   * use of the above static method.
   */
  private Player( String name, Party party, boolean isHitler ) {
    // set values
    this.name = name;
    this.party = party;
    this.isHitlerVar = isHitler;
  }


  public String getName() {
    return this.name;
  }

  public Party getParty() {
    return this.party;
  }

  public boolean isHitler() {
    return this.isHitlerVar;
  }
}