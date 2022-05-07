package gestisimal.business;

/**
 * Lanzada para indicar que no existe el artículo en el almacén
 * 
 * @author Maria Cervilla Alcalde
 *
 */
public class WarehouseArticleNotExistsException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Construye una excepción WarehouseArticleNotExistsException con el mensaje especificado
   * 
   * @param message Mensaje
   */
  public WarehouseArticleNotExistsException(String message) {
    super(message);
  }

}

