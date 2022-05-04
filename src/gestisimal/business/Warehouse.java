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
 *
 *         Clase Almacén que realice el alta, baja, modificación, entrada de mercancía (incrementa
 *         unidades), salida de mercancía (decrementa unidades). El estado será un ArrayList de
 *         artículos (pero la clase no será un ArrayList) Su comportamiento será: añadir artículos
 *         (no puede haber dos artículos con el mismo nombre y marca), eliminar artículos,
 *         incrementar las existencias de un artículo (se delega en la clase Artículo), decrementar
 *         las existencias de un artículo (nunca por debajo de cero, se delega en la clase
 *         Artículo), devolver un artículo (para mostrarlo). Para listar el almacén podría
 *         devolverse una cadena con todos los artículos del almacén (toString).
 */
public class Warehouse {

  private List<Article> articles = new ArrayList<Article>();


  public Warehouse() {}

  public Warehouse(String file) throws IOException, ParserConfigurationException, SAXException { 
    load(articles,file);
  }

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


  public void add(String name, String brand, int numberUnits, double purchasePrice,
      double sellingPrice) throws PreviouslyEnteredArticleException {
    add(name, brand, numberUnits, purchasePrice, sellingPrice, 0, 0);

  }

  public void remove(int code) throws WarehouseArticleNotExistsException {
    existCode(code);
    articles.removeIf(articles -> articles.getCode() == code);
  }


  public void increaseStock(int code, int units) throws WarehouseArticleNotExistsException {
    existCode(code);
    Article art = get(code);
    art.addUnits(units);
  }


  public void decreaseStock(int code, int units)
      throws StockException, WarehouseArticleNotExistsException {
    existCode(code);
    Article art = get(code);
    art.deleteUnits(units);
  }

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


  public Article get(int code) {
    return articles.get(articles.indexOf(new Article(code)));
  }


  public void saveFile(String file) throws ParserConfigurationException, IOException, TransformerException {
    save(articles, file);
  }


  private void existCode(int code) throws WarehouseArticleNotExistsException {
    if (articles.contains(new Article(code))) {
      return;
    }
    throw new WarehouseArticleNotExistsException("No se encuentra el artículo.");
  }


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
