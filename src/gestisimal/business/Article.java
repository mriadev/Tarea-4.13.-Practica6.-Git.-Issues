package gestisimal.business;

import java.util.Objects;


/**
 * @author Maria Cervilla Alcalde
 */

/**
 * Clase Artículo que representa los artículos del almacén.
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


  /**
   * Crea un artículo.
   * @param name
   * @param brand
   * @param numberUnits
   * @param purchasePrice
   * @param sellingPrice
   * @param safetyStock
   * @param maxStock
   */
  Article(String name, String brand, int numberUnits, double purchasePrice, double sellingPrice,
      int safetyStock, int maxStock) {
    this.code = ++lastCode;
    set(name, brand, numberUnits, purchasePrice, sellingPrice, safetyStock, maxStock);
  }

  /**
   * Asigna los valores de los atributos de artículo.
   * @param name
   * @param brand
   * @param numberUnits
   * @param purchasePrice
   * @param sellingPrice
   * @param safetyStock
   * @param maxStock
   */
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

  /**
   * Crea un artículo.
   * @param code
   * @param name
   * @param brand
   * @param numberUnits
   * @param purchasePrice
   * @param sellingPrice
   * @param safetyStock
   * @param maxStock
   */
  Article(int code, String name, String brand, int numberUnits, double purchasePrice,
      double sellingPrice, int safetyStock, int maxStock) {
    this(name, brand, numberUnits, purchasePrice, sellingPrice, safetyStock, maxStock);
    this.code = code;
  }

  /**
   * Crea un artículo.
   * @param name
   * @param brand
   * @param purchasePrice
   * @param sellingPrice
   * @param numberUnits
   * @param safetyStock
   */
  Article(String name, String brand, double purchasePrice, double sellingPrice, int numberUnits,
      int safetyStock) {
    this(name, brand, numberUnits, purchasePrice, sellingPrice, safetyStock, 0);
  }

  /**
   * Crea un artículo.
   * @param name
   * @param brand
   * @param purchasePrice
   * @param sellingPrice
   * @param numberUnits
   */
  Article(String name, String brand, double purchasePrice, double sellingPrice, int numberUnits) {
    this(name, brand, numberUnits, purchasePrice, sellingPrice, 0, 0);
  }

  /**
   * Crea un artículo.
   * @param code
   */
  Article(int code) {
    this.code = code;
  }


  /**
   * Añade unidades al artículo.
   * @param units
   */
  void addUnits(int units) {
    numberUnits += units;
  }

  /**
   * Incrementa unidades al artículo.
   * @param units
   * @throws StockException
   */
  void deleteUnits(int units) throws StockException {
    if (numberUnits < units) {
      throw new StockException("Stock insuficiente.");
    }
    numberUnits -= units;
  }

  /**
   * Devuelve el código del artículo.
   * @return
   */
  public int getCode() {
    return code;
  }

  /**
   * Devuelve el nombre del artículo.
   * @return
   */
  public String getName() {
    return name;
  }


  /**
   * Asigna el nombre del artículo.
   * @param name
   */
  void setName(String name) {
    throwsExceptionIfIncorrectFact(name);
    this.name = name;
  }


  /**
   * Devuelve la marca del artículo.
   * @return
   */
  public String getBrand() {
    return brand;
  }



  /**
   * Asigna la marca del artículo.
   * @param brand
   */
  void setBrand(String brand) {
    throwsExceptionIfIncorrectFact(brand);
    this.brand = brand;
  }



  /**
   * Devuelve el precio de compra del artículo.
   * @return
   */
  public double getPurchasePrice() {
    return purchasePrice;
  }



  /**
   * Asigna el precio de compra del artículo.
   * @param purchasePrice
   */
  void setPurchasePrice(double purchasePrice) {
    throwsExceptionIfAmountIsNegative(purchasePrice);
    this.purchasePrice = purchasePrice;
  }



  /**
   * Devuelve el precio de venta del artículo.
   * @return
   */
  public double getSellingPrice() {
    return sellingPrice;
  }



  /**
   * Asigna el precio de venta del artículo.
   * @param sellingPrice
   */
  void setSellingPrice(double sellingPrice) {
    throwsExceptionIfAmountIsNegative(sellingPrice);
    this.sellingPrice = sellingPrice;
  }



  /**
   * Devuelve el número de unidades del artículo.
   * @return
   */
  public int getNumberUnits() {
    return numberUnits;
  }



  /**
   * Asigna el número de unidades de un artículo.
   * @param numberUnits
   */
  void setNumberUnits(int numberUnits) {
    throwsExceptionIfAmountIsNegative(numberUnits);
    this.numberUnits = numberUnits;
  }



  /**
   * Devuelve el stock de seguridad del artículo.
   * @return
   */
  public int getSafetyStock() {
    return safetyStock;
  }



  /**
   * Asigna el stock de seguridad del artículo.
   * @param safetyStock
   */
  void setSafetyStock(int safetyStock) {
    throwsExceptionIfAmountIsNegative(safetyStock);
    this.safetyStock = safetyStock;
  }

  /**
   * Devuelve el stock máximo del artículo.
   * @return
   */
  public int getMaxStock() {
    return maxStock;
  }

  /**
   * Asigna el stock máximo del artículo.
   * @param maxStock
   */
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
