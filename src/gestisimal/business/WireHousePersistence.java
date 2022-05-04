package gestisimal.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * @author Maria Cervilla Alcalde
 *
 */
public class WireHousePersistence {

  private static final String JSON = "json";
  private static final String XML = "xml";

  /**
   * @param articles
   * @param file
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */
  static void load(List<Article> articles,String file) throws ParserConfigurationException, SAXException, IOException {
    String extensionFile = file.substring(file.lastIndexOf(".")+1);
    errorIfTypeFileNotDeduced(extensionFile);
    importXml(articles, file, extensionFile);
    importJson(articles, file, extensionFile);
  }
  
  /**
   * @param articles
   * @param file
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws TransformerException
   */
  static void save(List<Article> articles,String file) throws ParserConfigurationException, IOException, TransformerException {
    String extensionFile = file.substring(file.lastIndexOf(".")+1);
    errorIfTypeFileNotDeduced(extensionFile);
    exportXml(articles);
    exportJson(articles);
  }
  
  /**
   * @param articles
   * @param file
   * @param extensionFile
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */
  private static void importXml(List<Article> articles,String file, String extensionFile)
      throws ParserConfigurationException, SAXException, IOException {
    if (extensionFile.equals(XML)) {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(new File(file));

      document.getDocumentElement().normalize();

      NodeList nodes = document.getElementsByTagName("article");

      for (int i = 0; i < nodes.getLength(); i++) {

        Node node = nodes.item(i);
        Element article = (Element) node;
        
        int code = Integer.parseInt(article.getAttribute("code"));
        String name = article.getElementsByTagName("name").item(0).getTextContent();
        String brand = article.getElementsByTagName("brand").item(0).getTextContent();
        int units = Integer
            .parseInt(article.getElementsByTagName("units").item(0).getTextContent());
        double purchasePrice = Double
            .parseDouble(article.getElementsByTagName("purchasePrice").item(0).getTextContent());
        double sellingPrice = Double
            .parseDouble(article.getElementsByTagName("sellingPrice").item(0).getTextContent());
        int safetyStock = Integer
            .parseInt(article.getElementsByTagName("safetyStock").item(0).getTextContent());
        int maxStock =
            Integer.parseInt(article.getElementsByTagName("maxStock").item(0).getTextContent());

        articles.add(new Article(code, name, brand, units, purchasePrice, sellingPrice,
            safetyStock, maxStock));
      }
    }
  }

   /**
   * @param articles
   * @param file
   * @param extensionFile
   * @throws IOException
   */
  private static void importJson(List<Article> articles,String file, String extensionFile) throws IOException {
    if (extensionFile.equals(JSON)) {
      String json = Files.readString(Paths.get(file));
      Gson gson = new Gson();
      Type ArticlesListType = new TypeToken<ArrayList<Article>>() {}.getType();
      articles = gson.fromJson(json, ArticlesListType);
      return;
    }
  }
   
   /**
   * @param articles
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws TransformerException
   */
  private static void exportXml(List<Article> articles) throws ParserConfigurationException, IOException, TransformerException {
     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
     DocumentBuilder builder = factory.newDocumentBuilder();
     Document document = builder.newDocument();

     Element root = document.createElement("almacen");
     document.appendChild(root);

     for (Article article : articles) {
       // Nodo raiz
       Element elementArt = document.createElement("article");
       root.appendChild(elementArt);

       // Atributo código
       Attr attrCod = document.createAttribute("code");
       attrCod.setValue(Integer.toString(article.getCode()));
       elementArt.setAttributeNode(attrCod);

       // Nodo nombre
       Element elementName = document.createElement("name");
       elementName.appendChild(document.createTextNode(article.getName()));
       elementArt.appendChild(elementName);

       // Nodo marca
       Element elementBrand = document.createElement("brand");
       elementBrand.appendChild(document.createTextNode(article.getBrand()));
       elementArt.appendChild(elementBrand);

       // Nodo numberUnits
       Element elementNumberUnits = document.createElement("units");
       elementNumberUnits.appendChild(document.createTextNode(Integer.toString(article.getNumberUnits())));
       elementArt.appendChild(elementNumberUnits);

       // Nodo purchasePrice
       Element elementPurchasePrice = document.createElement("purchasePrice");
       elementPurchasePrice
           .appendChild(document.createTextNode(Double.toString(article.getPurchasePrice())));
       elementArt.appendChild(elementPurchasePrice);

       // Nodo sellingPrice
       Element elementSellingPrice = document.createElement("sellingPrice");
       elementSellingPrice
           .appendChild(document.createTextNode(Double.toString(article.getSellingPrice())));
       elementArt.appendChild(elementSellingPrice);

       // Nodo safetyStock
       Element elementSafetyStock = document.createElement("safetyStock");
       elementSafetyStock.appendChild(document.createTextNode(Integer.toString(article.getSafetyStock())));
       elementArt.appendChild(elementSafetyStock);

       // Nodo maxStock
       Element elementMaxStock = document.createElement("maxStock");
       elementMaxStock.appendChild(document.createTextNode(Integer.toString(article.getMaxStock())));
       elementArt.appendChild(elementMaxStock);
     }

     TransformerFactory transformerFactory = TransformerFactory.newInstance();
     Transformer transformer = transformerFactory.newTransformer();
     DOMSource source = new DOMSource(document);
     StreamResult result = new StreamResult(new FileWriter("almacen.xml"));
     transformer.transform(source, result);
   }
   

   /**
   * @param articles
   * @throws IOException
   */
  private static void exportJson(List<Article> articles) throws IOException {
     String json = new Gson().toJson(articles);
     BufferedWriter file = new BufferedWriter(new FileWriter("almacen.json"));
     file.write(json);
     file.close();
   }
   
   /**
   * @param extensionFile
   */
  private static void errorIfTypeFileNotDeduced(String extensionFile) {
     if(!extensionFile.equals(XML) || !extensionFile.equals(JSON)) {
       throw new IllegalArgumentException("No se puede deducir el tipo del fichero.");
     }
   }
}
