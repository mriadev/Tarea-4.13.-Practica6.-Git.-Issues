package gestisimal.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import static gestisimal.business.WireHousePersistence.*;


/**
 * Clase Almacén para la manipulación de artículos
 * 
 * @author Maria Cervilla Alcalde
 * 
 */
public class Warehouse {


  /**
   * Lista de artículos
   */
  private List<Article> articles = new ArrayList<Article>();

  /**
   * Crea un almacén vacío.
   */
  public Warehouse() {}

  /**
   * Crea un almacén cargando un fichero XML o JSON
   * 
   * @param file Archivo
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
   * @param name Nuevo nombre del artículo
   * @param brand Nueva marca del artículo
   * @param numberUnits Nuevo número de unidades del artículo
   * @param purchasePrice Nuevo precio de compra del artículo
   * @param sellingPrice Nuevo precio de venta del artículo
   * @param safetyStock Nuevo stock de seguridad del artículo
   * @param maxStock Nuevo stock máximo del artículo
   * @throws PreviouslyEnteredArticleException Si el artículo ya ha sido creado anteriormente en el almacén
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
   * Añade un artículo al almacén sin stock de seguridad ni stock máximo
   * 
   * @param name Nombre del artículo
   * @param brand Marca del artículo
   * @param numberUnits Número de unidades del artículo
   * @param purchasePrice Precio de compra del artículo
   * @param sellingPrice Precio de venta del artículo
   * @throws PreviouslyEnteredArticleException Si el artículo ya ha sido creado anteriormente en el almacén
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
   * Modifíca el artículo localizado por su código
   * 
   * @param code Código del artículo
   * @param name Nombre del artículo
   * @param brand Marca del artículo
   * @param numberUnits Número de unidades del artículo
   * @param purchasePrice Precio de compra del artículo
   * @param sellingPrice Precio de venta del artículo
   * @param safetyStock Stock de seguridad del artículo
   * @param maxStock Stock máximo del artículo
   * @throws WarehouseArticleNotExistsException Si el artículo no existe en el almacén
   * @throws PreviouslyEnteredArticleException Si está duplicando el artículo
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
   * Busca un artículo por el código
   * 
   * @param code Código del artículo
   * @return Article Nuevo artículo
   */
  public Article get(int code) {
    return articles.get(articles.indexOf(new Article(code)));
  }


  /**
   * Guarda el almacén en un fichero
   * 
   * @param file Fichero
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws TransformerException
   */
  public void saveFile(String file)
      throws ParserConfigurationException, IOException, TransformerException {
    save(articles, file);
  }


  /**
   * Comprueba la existencia del artículo en el almacén
   * 
   * @param code Código del artículo
   * @throws WarehouseArticleNotExistsException Si el artículo no existe
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

