package gestisimal.business;

/**
 * Lanzada para indicar que ya existe el artículo en el almacén
 * 
 * @author Maria Cervilla Alcalde
 * 
 */
public class PreviouslyEnteredArticleException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Construye una excepción PreviouslyEnteredArticleException con el mensaje especificado
   * 
   * @param message Mensaje
   */
  public PreviouslyEnteredArticleException(String message) {
    super(message);
  }
}

