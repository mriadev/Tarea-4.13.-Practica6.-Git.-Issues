package gestisimal.business;

import java.util.Objects;

/**
 * @author Maria Cervilla Alcalde
 * 
 *         Clase Artículo que representa a los artículos del almacén. Su estado será: código,
 *         nombre, marca, precio de compra, precio de venta, número de unidades (nunca negativas),
 *         stock de seguridad y stock máximo (si no proporcionamos los valores de stock valen cero,
 *         igualmente si no proporcionamos el del stock de seguridad). Como comportamiento:
 *         Consideramos que el código va a generarse de forma automática en el constructor, así no
 *         puede haber dos artículos con el mismo código. Esto implica que no puede modificarse el
 *         código de un artículo, sí el resto de las propiedades. Podremos mostrar el artículo, por
 *         lo que necesito una representación del artículo en forma de cadena (toString).
 *
 */
public class Article {
  private static int lastCode = 0;

  private int code;
  private String name;
  private String brand;
  private double purchasePrice;
  private double sellingPrice;
  private int numberUnits;
  private int safetyStock;
  private int maxStock;


  Article(String name, String brand, int numberUnits, double purchasePrice, double sellingPrice,
      int safetyStock, int maxStock) {
    this.code = ++lastCode;
    set(name, brand, numberUnits, purchasePrice, sellingPrice, safetyStock, maxStock);
  }

  void set(String name, String brand, int numberUnits, double purchasePrice, double sellingPrice,
      int safetyStock, int maxStock) {
    setName(name);
    setBrand(brand);
    setNumberUnits(numberUnits);
    setPurchasePrice(purchasePrice);
    setSellingPrice(sellingPrice);
    setSafetyStock(safetyStock);
    setMaxStock(maxStock);

  }

  Article(int code, String name, String brand, int numberUnits, double purchasePrice,
      double sellingPrice, int safetyStock, int maxStock) {
    this(name, brand, numberUnits, purchasePrice, sellingPrice, safetyStock, maxStock);
    this.code = code;
  }

  Article(String name, String brand, double purchasePrice, double sellingPrice, int numberUnits,
      int safetyStock) {
    this(name, brand, numberUnits, purchasePrice, sellingPrice, safetyStock, 0);
  }

  Article(String name, String brand, double purchasePrice, double sellingPrice, int numberUnits) {
    this(name, brand, numberUnits, purchasePrice, sellingPrice, 0, 0);
  }

  Article(int code) {
    this.code = code;
  }


  void addUnits(int units) {
    numberUnits += units;
  }

  void deleteUnits(int units) throws StockException {
    if (numberUnits < units) {
      throw new StockException("Stock insuficiente.");
    }
    numberUnits -= units;
  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }


  void setName(String name) {
    throwsExceptionIfIncorrectFact(name);
    this.name = name;
  }


  public String getBrand() {
    return brand;
  }



  void setBrand(String brand) {
    throwsExceptionIfIncorrectFact(brand);
    this.brand = brand;
  }



  public double getPurchasePrice() {
    return purchasePrice;
  }



  void setPurchasePrice(double purchasePrice) {
    throwsExceptionIfAmountIsNegative(purchasePrice);
    this.purchasePrice = purchasePrice;
  }



  public double getSellingPrice() {
    return sellingPrice;
  }



  void setSellingPrice(double sellingPrice) {
    throwsExceptionIfAmountIsNegative(sellingPrice);
    this.sellingPrice = sellingPrice;
  }



  public int getNumberUnits() {
    return numberUnits;
  }



  void setNumberUnits(int numberUnits) {
    throwsExceptionIfAmountIsNegative(numberUnits);
    this.numberUnits = numberUnits;
  }



  public int getSafetyStock() {
    return safetyStock;
  }



  void setSafetyStock(int safetyStock) {
    throwsExceptionIfAmountIsNegative(safetyStock);
    this.safetyStock = safetyStock;
  }

  public int getMaxStock() {
    return maxStock;
  }

  void setMaxStock(int maxStock) {
    throwsExceptionIfAmountIsNegative(maxStock);
    if (maxStock < safetyStock) {
      throw new ArticleIllegalErrorArgumentException(
          "El stock máximo no puede ser inferior al stock mínimo.");
    }
    this.maxStock = maxStock;
  }


  @Override
  public String toString() {
    return "Article [code=" + code + ",name=" + name + ", brand=" + brand + ", purchasePrice="
        + purchasePrice + ", sellingPrice=" + sellingPrice + ", numberUnits=" + numberUnits
        + ", safetyStock=" + safetyStock + ", maxStock=" + maxStock + "]";
  }

  private void throwsExceptionIfAmountIsNegative(double purchasePrice) {
    if (purchasePrice < 0) {
      throw new ArticleIllegalErrorArgumentException("No hay stock suficiente.");
    }
  }

  private void throwsExceptionIfIncorrectFact(String fact) {
    if (fact.isBlank() || fact.equals(null)) {
      throw new ArticleIllegalErrorArgumentException("El dato introducido es incorrecto.");
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Article other = (Article) obj;
    return code == other.code;
  }



}
