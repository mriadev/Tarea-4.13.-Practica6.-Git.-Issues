package gestisimal.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import static gestisimal.business.WireHousePersistence.*;




/**
 * @author Maria Cervilla Alcalde
 */

/**
 * Clase Almacén para la manipulación de artículos
 * 
 */
public class Warehouse {


  private List<Article> articles = new ArrayList<Article>();

  /**
   * Crea un almacén vacío.
   */
  public Warehouse() {}

  /**
   * Crea un almacén cargando un fichero xml o json.
   * 
   * @param file
   * @throws IOException
   * @throws ParserConfigurationException
   * @throws SAXException
   */
  public Warehouse(String file) throws IOException, ParserConfigurationException, SAXException {
    load(articles, file);
  }

  /**
   * Añade un artículo al almacén.
   * 
   * @param name
   * @param brand
   * @param numberUnits
   * @param purchasePrice
   * @param sellingPrice
   * @param safetyStock
   * @param maxStock
   * @throws PreviouslyEnteredArticleException
   */
  public void add(String name, String brand, int numberUnits, double purchasePrice,
      double sellingPrice, int safetyStock, int maxStock) throws PreviouslyEnteredArticleException {
    for (Article art : articles) {
      if (art.getName().equals(name) && art.getBrand().equals(brand)) {
        throw new PreviouslyEnteredArticleException("Artículo anteriormente introducido.");
      }
    }
    articles.add(
        new Article(name, brand, numberUnits, purchasePrice, sellingPrice, safetyStock, maxStock));
  }


  /**
   * 
   * Añade un artículo al almacén.
   * 
   * @param name
   * @param brand
   * @param numberUnits
   * @param purchasePrice
   * @param sellingPrice
   * @throws PreviouslyEnteredArticleException
   */
  public void add(String name, String brand, int numberUnits, double purchasePrice,
      double sellingPrice) throws PreviouslyEnteredArticleException {
    add(name, brand, numberUnits, purchasePrice, sellingPrice, 0, 0);

  }

  /**
   * Borra un artículo del almacén.
   * 
   * @param code
   * @throws WarehouseArticleNotExistsException
   */
  public void remove(int code) throws WarehouseArticleNotExistsException {
    existCode(code);
    articles.removeIf(articles -> articles.getCode() == code);
  }


  /**
   * Incrementa el stock de un artículo.
   * 
   * @param code
   * @param units
   * @throws WarehouseArticleNotExistsException
   */
  public void increaseStock(int code, int units) throws WarehouseArticleNotExistsException {
    existCode(code);
    Article art = get(code);
    art.addUnits(units);
  }


  /**
   * Decrementa el stock de un artículo
   * 
   * @param code
   * @param units
   * @throws StockException
   * @throws WarehouseArticleNotExistsException
   */
  public void decreaseStock(int code, int units)
      throws StockException, WarehouseArticleNotExistsException {
    existCode(code);
    Article art = get(code);
    art.deleteUnits(units);
  }

  /**
   * Modifíca un artículo.
   * 
   * @param code
   * @param name
   * @param brand
   * @param numberUnits
   * @param purchasePrice
   * @param sellingPrice
   * @param safetyStock
   * @param maxStock
   * @throws WarehouseArticleNotExistsException
   * @throws PreviouslyEnteredArticleException
   */
  public void modify(int code, String name, String brand, int numberUnits, double purchasePrice,
      double sellingPrice, int safetyStock, int maxStock)
      throws WarehouseArticleNotExistsException, PreviouslyEnteredArticleException {
    existCode(code);
    for (Article art : articles) {
      if (art.getName().equals(name) && art.getBrand().equals(brand) && art.getCode() != code) {
        throw new PreviouslyEnteredArticleException(
            "No es posible modificar el artículo porque ya se ha introducido previamente.");
      }
    }
    Article art = articles.get(articles.indexOf(new Article(code)));
    art.set(name, brand, numberUnits, purchasePrice, sellingPrice, safetyStock, maxStock);
  }


  /**
   * Busca un artículo.
   * 
   * @param code
   * @return
   */
  public Article get(int code) {
    return articles.get(articles.indexOf(new Article(code)));
  }


  /**
   * Guarda el almacén en un fichero.
   * 
   * @param file
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws TransformerException
   */
  public void saveFile(String file)
      throws ParserConfigurationException, IOException, TransformerException {
    save(articles, file);
  }


  /**
   * Comprueba si exite un artículo.
   * 
   * @param code
   * @throws WarehouseArticleNotExistsException
   */
  private void existCode(int code) throws WarehouseArticleNotExistsException {
    if (articles.contains(new Article(code))) {
      return;
    }
    throw new WarehouseArticleNotExistsException("No se encuentra el artículo.");
  }


  /**
   * Imprime el almacén
   */
  @Override
  public String toString() {
    String str = "";
    if (articles.isEmpty()) {
      return "No hay ningún artículo en el almacén";
    }
    for (Article a : articles) {
      str += a.getCode() + " \t" + a.getName() + "\t" + a.getBrand() + "\t" + a.getPurchasePrice()
          + "\t" + a.getSellingPrice() + "\t" + a.getNumberUnits() + "\t" + a.getSafetyStock()
          + "\t" + a.getMaxStock() + "\n";
    }
    return str;
  }



}
