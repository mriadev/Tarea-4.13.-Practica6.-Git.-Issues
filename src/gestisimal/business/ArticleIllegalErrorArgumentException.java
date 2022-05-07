package gestisimal.business;

/**
 * Lanzada para indicar que hay un error de argumentos
 * 
 * @author Maria Cervilla Alcalde
 *
 */
public class ArticleIllegalErrorArgumentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Construye una excepción ArticleIllegalErrorArgumentException con el mensaje especificado
   * 
   * @param message Mensaje
   */
  public ArticleIllegalErrorArgumentException(String message) {
    super(message);
  }
}

