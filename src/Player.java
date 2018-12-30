public Player {
  public enum Party {
    Facist, Liberal, Spectator
  }

  private static boolean hitlerExists = false;

  public static Player newHitler(String name) {
    if (hitlerExists) {
      throw new RuntimeException("hitler already exists")
    }


    Player ret = new Player(name, Party.Facist);
    ret.isHitlerVar = true;
    Player.hitlerExists = true;

    return ret;
  }

  private boolean isHitlerVar = false;
  private Party party = null;

  public Player(String name, Party party) {
    // set values
    setName(name);
    setParty(party);
  }

  private void setName(String name) {
    this.name = name;
  }

  private void setParty() {}

  public void setHitler(boolean isHitler) {
    this.isHitlerVar = isHitler;
  }

  public boolean isHitler() {
    return this.isHitlerVar;
  }
}