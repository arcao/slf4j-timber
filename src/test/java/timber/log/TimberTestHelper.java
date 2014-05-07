package timber.log;

public final class TimberTestHelper {
  public static void cleanTimber() {
    Timber.FOREST.clear();
    Timber.TAGGED_TREES.clear();
  }
}
