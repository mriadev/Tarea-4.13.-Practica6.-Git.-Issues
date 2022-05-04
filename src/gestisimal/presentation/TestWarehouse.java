package gestisimal.presentation;

import static util.Util.*;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import gestisimal.business.Article;
import gestisimal.business.ArticleIllegalErrorArgumentException;
import gestisimal.business.Warehouse;
import gestisimal.business.PreviouslyEnteredArticleException;
import gestisimal.business.StockException;
import gestisimal.util.Util;
import gestisimal.business.WarehouseArticleNotExistsException;


/**
 * Programa para probar la clase Warehouse.
 * 
 * @author Rafael del Castillo Gomariz
 *
 */

public class TestWarehouse {

  private static Warehouse warehouse = new Warehouse();

  public static void main(String[] args) {
    Menu menu = createMenu();
    fillWarehouse(); // valores de prueba

    int option;
    do {
      option = menu.choose();
      switch (option) {
        case 1 -> showWarehouse();
        case 2 -> addArticle();
        case 3 -> removeArticle();
        case 4 -> modifyArticle();
        case 5 -> increaseStock();
        case 6 -> decreaseStock();
        case 7 -> saveWareHouse();
        case 8 -> loadWareHouse();
      }
    } while (option != menu.lastOption());

    System.out.println("¡Hasta la próxima! ;-)");
  }


  private static Menu createMenu() {
    return new Menu("\nMenú de opciones", "Listado", "Alta de artículo", "Baja de artículo",
        "Modificación de artículo", "Entrada de mercancía", "Salida de mercancía",
        "Guardar almacén.", "Cargar almacén", "Exportar almacén a XML",
        "Importar XML a almacén", "Terminar");
  }

  private static void fillWarehouse() {
    for (int i = 1; i <= 5; i++) {
      try {
        warehouse.add("Artículo" + i, "Marca" + i, randomInt(1, 100), randomInt(10, 100),
            randomInt(50, 500));
      } catch (PreviouslyEnteredArticleException e) {
        e.printStackTrace();
      }
    }
  }

  private static void showWarehouse() {
    System.out.println(warehouse);
  }

  private static void addArticle() {
    try {
      warehouse.add(readStr("Nombre de artículo a dar de alta"), readStr("Marca"),
          readInt("Unidades"), readDouble("Precio compra"),
          readDouble("Precio venta"), readInt("Stock de seguridad"),
          readInt("Stock máximo"));
    } catch (PreviouslyEnteredArticleException e) {
      System.err.println("ERROR: El artículo que has intentado añadir ya existe en el almacén.");
    } catch (ArticleIllegalErrorArgumentException e) {
      System.err.println("ERROR: " + e.getMessage());
    }
  }

  private static void removeArticle() {
    try {
      warehouse.remove(Util.readInt("Código de artículo a dar de baja"));
    } catch (WarehouseArticleNotExistsException e) {
      printCodeError();
    }
  }

  private static void modifyArticle() {
    try {
      Article article = warehouse.get(readInt("Código de artículo a modificar"));
      System.out.println(article);
      warehouse.modify(article.getCode(), readStr("Nombre"), readStr("Marca"),
          readInt("Unidades"), readDouble("Precio compra"),
          readDouble("Precio venta"), readInt("Stock de seguridad"),
          readInt("Stock máximo"));
    } catch (WarehouseArticleNotExistsException e) {
      printCodeError();
    } catch (PreviouslyEnteredArticleException e) {
      System.err.println("ERROR: El nombre y marca de artículo ya existen en el almacén.");
    } catch (ArticleIllegalErrorArgumentException e) {
      System.err.println("ERROR: " + e.getMessage());
    }
  }

  private static void increaseStock() {
    try {
      warehouse.increaseStock(readInt("Código del artículo a incrementar stock"),
          readInt("Unidades"));
    } catch (WarehouseArticleNotExistsException e) {
      printCodeError();
    } catch (ArticleIllegalErrorArgumentException e) {
      System.err.println("ERROR: " + e.getMessage());
    }
  }

  private static void decreaseStock() {
    try {
      warehouse.decreaseStock(readInt("Código de artículo de decrementar stock"),
          readInt("Unidades"));
    } catch (WarehouseArticleNotExistsException e) {
      printCodeError();
    } catch (StockException e) {
      System.err.println("ERROR: No hay suficientes unidades en el almacén.");
    } catch (ArticleIllegalErrorArgumentException e) {
      System.err.println("ERROR: " + e.getMessage());
    }
  }

  private static void saveWareHouse() {
    try {
      warehouse.saveFile(util.Util.readStr("Fichero donde guardar el almacén. Incluir extensión."));
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }
  
  private static void loadWareHouse() {
    try {
      Warehouse newWareHouse = new Warehouse(util.Util.readStr("Fichero de donde cargar el almacén. Incluir extensión."));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    }
  }

  private static void printCodeError() {
    System.err.println("ERROR: Ese código no corresponde a ningún artículo.");
  }
}
