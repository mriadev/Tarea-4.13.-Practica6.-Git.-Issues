package gestisimal.business;

import java.util.Objects;

/**
 * Clase Artículo que representa los artículos del almacén.
 * 
 * 
 * @author Maria Cervilla Alcalde
 */
public class Article {
  private static int lastCode = 0;

  /**
   * Código del artículo
   */
  private int code;
  /**
   * Nombre del artículo
   */
  private String name;
  /**
   * Marca del artículo
   */
  private String brand;
  /**
   * Número de unidades del artículo
   */
  private int numberUnits;
  /**
   * Precio de compra del artículo
   */
  private double purchasePrice;
  /**
   * Precio de venta del artículo
   */
  private double sellingPrice;
  /**
   * Stock de seguridad del artículo
   */
  private int safetyStock;
  /**
   * Stock máximo del artículo
   */
  private int maxStock;


  /**
   * Crea un nuevo artículo.
   * @param name Nombre del artículo
   * @param brand Marca del artículo
   * @param numberUnits Número de unidades del artículo
   * @param purchasePrice Precio de compra del artículo
   * @param sellingPrice Precio de venta del artículo
   * @param safetyStock Stock de seguridad del artículo
   * @param maxStock Stock máximo del artículo
   */
  Article(String name, String brand, int numberUnits, double purchasePrice, double sellingPrice,
      int safetyStock, int maxStock) {
    this.code = ++lastCode;
    set(name, brand, numberUnits, purchasePrice, sellingPrice, safetyStock, maxStock);
  }

  /**
   * Asigna los valores de los atributos de artículo.
   * @param name Nuevo nombre del artículo
   * @param brand Nueva marca del artículo
   * @param numberUnits Nuevo número de unidades del artículo
   * @param purchasePrice Nuevo precio de compra del artículo
   * @param sellingPrice Nuevo precio de venta del artículo
   * @param safetyStock Nuevo stock de seguridad del artículo
   * @param maxStock Nuevo stock máximo del artículo
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
   * Crea un artículo con el código.
   * @param code Código del artículo
   * @param name Nombre del artículo
   * @param brand Marca del artículo
   * @param numberUnits Número de unidades del artículo
   * @param purchasePrice Precio de compra del artículo
   * @param sellingPrice Precio de venta del artículo
   * @param safetyStock Stock de seguridad del artículo
   * @param maxStock Stock máximo del artículo
   */
  Article(int code, String name, String brand, int numberUnits, double purchasePrice,
      double sellingPrice, int safetyStock, int maxStock) {
    this(name, brand, numberUnits, purchasePrice, sellingPrice, safetyStock, maxStock);
    this.code = code;
  }

  /**
   * Crea un artículo sin stock máximo
   * @param name Nombre del artículo
   * @param brand Marca del artículo
   * @param numberUnits Número de unidades del artículo
   * @param purchasePrice Precio de compra del artículo
   * @param sellingPrice Precio de venta del artículo
   * @param safetyStock Stock de seguridad del artículo
   */
  Article(String name, String brand, double purchasePrice, double sellingPrice, int numberUnits,
      int safetyStock) {
    this(name, brand, numberUnits, purchasePrice, sellingPrice, safetyStock, 0);
  }

  /**
   * Crea un artículo sin stock de seguridad ni stock máximo
   * @param name Nombre del artículo
   * @param brand Marca del artículo
   * @param numberUnits Número de unidades del artículo
   * @param purchasePrice Precio de compra del artículo
   * @param sellingPrice Precio de venta del artículo
   */
  Article(String name, String brand, double purchasePrice, double sellingPrice, int numberUnits) {
    this(name, brand, numberUnits, purchasePrice, sellingPrice, 0, 0);
  }

  /**
   * Crea un artículo solo con el código.
   * @param code Cófigo del artículo
   */
  Article(int code) {
    this.code = code;
  }


  /**
   * Añade unidades al artículo.
   * @param units Unidades del artículo
   */
  void addUnits(int units) {
    numberUnits += units;
  }

  /**
   * Incrementa unidades al artículo.
   * @param units Unidades del artículo
   * @throws StockException Si al sacar unidades el stock es insuficiente
   */
  void deleteUnits(int units) throws StockException {
    if (numberUnits < units) {
      throw new StockException("Stock insuficiente.");
    }
    numberUnits -= units;
  }

  /**
   * Devuelve el código del artículo.
   * @return code Código del artículo
   */
  public int getCode() {
    return code;
  }

  /**
   * Devuelve el nombre del artículo.
   * @return name Nombre del artículo
   */ 
  public String getName() {
    return name;
  }


  /**
   * Asigna el nombre del artículo
   * @param name Nombre del artículo
   */
  void setName(String name) {
    throwsExceptionIfIncorrectFact(name);
    this.name = name;
  }


  /**
   * Devuelve la marca del artículo
   * @return brand Marca del artículo
   */
  public String getBrand() {
    return brand;
  }



  /**
   * Asigna la marca del artículo
   * @param brand Marca del artículo
   */
  void setBrand(String brand) {
    throwsExceptionIfIncorrectFact(brand);
    this.brand = brand;
  }



  /**
   * Devuelve el precio de compra del artículo
   * @return purchasePrice Precio de compra del artículo
   */
  public double getPurchasePrice() {
    return purchasePrice;
  }



  /**
   * Asigna el precio de compra del artículo
   * @param purchasePrice Precio de compra del artículo
   */
  void setPurchasePrice(double purchasePrice) {
    throwsExceptionIfAmountIsNegative(purchasePrice);
    this.purchasePrice = purchasePrice;
  }



  /**
   * Devuelve el precio de venta del artículo
   * @return sellingPrice Precio de venta del artículo
   */
  public double getSellingPrice() {
    return sellingPrice;
  }



  /**
   * Asigna el precio de venta del artículo
   * @param sellingPrice Precio de venta del artículo
   */
  void setSellingPrice(double sellingPrice) {
    throwsExceptionIfAmountIsNegative(sellingPrice);
    this.sellingPrice = sellingPrice;
  }



  /**
   * Devuelve el número de unidades del artículo
   * @return numberUnits Número de unidades del artículo
   */
  public int getNumberUnits() {
    return numberUnits;
  }



  /**
   * Asigna el número de unidades de un artículo
   * @param numberUnits Número de unidades del artículo
   */
  void setNumberUnits(int numberUnits) {
    throwsExceptionIfAmountIsNegative(numberUnits);
    this.numberUnits = numberUnits;
  }



  /**
   * Devuelve el stock de seguridad del artículo
   * @return safetyStock Stock de seguridad del artículo
   */
  public int getSafetyStock() {
    return safetyStock;
  }



  /**
   * Asigna el stock de seguridad del artículo
   * @param safetyStock Stock de seguridad del artículo
   */
  void setSafetyStock(int safetyStock) {
    throwsExceptionIfAmountIsNegative(safetyStock);
    this.safetyStock = safetyStock;
  }

  /**
   * Devuelve el stock máximo del artículo
   * @return maxStock Stock máximo del artículo
   */
  public int getMaxStock() {
    return maxStock;
  }

  /**
   * Asigna el stock máximo del artículo
   * @param maxStock Stock máximo del artículo
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

