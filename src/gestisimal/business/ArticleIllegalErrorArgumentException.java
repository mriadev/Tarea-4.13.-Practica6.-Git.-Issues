package gestisimal.business;

/**
 * @author Maria Cervilla Alcalde
 *
 */
public class ArticleIllegalErrorArgumentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * @param message
   */
  public ArticleIllegalErrorArgumentException(String message) {
    super(message);
  }
}
