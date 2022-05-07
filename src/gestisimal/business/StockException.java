package gestisimal.business;

/**
 * Lanzada para indicar que hay un problema en el stock
 * 
 * @author Maria Cervilla Alcalde
 *
 */
public class StockException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Construye una excepción StockException con el mensaje especificado
   * 
   * @param message Mensaje
   */
  public StockException(String message) {
    super(message);
  }

}

