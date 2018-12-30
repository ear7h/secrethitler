import java.util.List;

public class Game {
  public static final int MAX_PLAYERS = 10;
  /* TODO: check these constants */
  public static final int NUM_FACIST_POLICIES = 10;
  public static final int NUM_LIBERAL_POLICIES = 7;
  public static final int NUM_POLICIES = 
      NUM_FACIST_POLICIES + NUM_LIBERAL_POLICIES;

  /**
   * State describes which part of gameplay the game is in.
   * For example, if currentState == vote, the next method to
   * be called should be countVotes(boolean[]). In turn, count
   * votes should:
   *    a. change the game state to PRESIDENT_DISCARD_POLICY
   *      if the vote passes
   *    b. increment numConsecutiveTies if it fails
   *    c. if numConsecutiveTies force the next president and
   *      chancelor, and change the game state to PRESIDENT_DISCARD_POLICY
   *
   */
  public enum State {
    VOTE, PRESIDENT_DISCARD_POLICY, CHANCELOR_DISCARD_POLICY,
    PRESIDENT_ASSASINATE_PLAYER, PRESIDENT_PEEK_POLICIES,
    PRESIDENT_PEEK_ID_CARD, PRESIDENT_PICK_PRESIDENT,
    PRESIDENT_VETO, FACIST_WIN, LIBRAL_WIN,
  }

  /**
   * GameBoard represents all the completely public assets of
   * the game board. This object is used to broadcast the game board
   * to all of the players. The public fields exist in order to
   * turn it into a JSON Object using GSON.
   */
  public class Board {
    // once again, the fields are public but only for the
    // consumption of an external library. DO NOT access the fields
    // dirrectly, use getters and setters. These will be crucial
    // for debugging the program which, will be very necessary.
    public int numConsecutiveTies = 0;
    public int facistPolicies = 0;
    public int liberalPolicies = 0;

    // this field represents the varyious boards
    // which can be used for facist progression
    // some array constants would be good for this
    public State[] facistProgressions = null;
    
    // these two should never be null during the game,
    // hence the word sitting. These variables will be used
    // to describe election candidates. Their actual status as
    // candidates can be implied by knowing the game state.
    // If currentState != VOTE then they were elected.
    public String sittingPresident = null;
    public String sittingChancellor = null;
  }


  State state = State.VOTE; // game starts with a vote for chancellor
  Board board = new Board();
  Player[] players = null;
  List<Player.Party> freshPolicies = null;
  List<Player.Party> stalePolicies = null;

	public Game( String[] players ) {
		// for each player name make a player object
    // generate the policies, shuffle them, and
    // assign it to fresh policies
	}

  public Player[] getFacists() {
    return null;
  }

  public Player GetHitler() {
    return null;
  }

  public State getState() {
    return this.state;
  }

  public Board getBoard() {
    return this.board;
  }

  public Player.Party[/* should always be 3*/] nextThreePolicies() {
    // check if proper state for this call

    // check if there's enough fresh policies
      // if not, put the stale policies in the fresh lista
      // in a random manner.
    // take from fresh policies

    return null;
  }

  public void discardPolicy(Player.Party policy) {
    // check if proper state for this call

    // put policy in stale pile
  }

  /**
   *
   */
  public boolean countVotes( boolean[] votes ) {
    // check if proper state for this call

    return false;
  }

}