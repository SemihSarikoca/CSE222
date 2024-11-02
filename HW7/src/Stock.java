/**
 * Represents a stock with a symbol, price, volume, and market capitalization.
 */
public class Stock {
    private String symbol;
    private double price;
    private long volume;
    private long marketCap;
    
    /**
     * Constructs a new Stock with the given symbol, price, volume, and market capitalization.
     *
     * @param symbol    the stock's symbol
     * @param price     the stock's price
     * @param volume    the stock's volume
     * @param marketCap the stock's market capitalization
     */
    public Stock(String symbol, double price, long volume, long marketCap) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }
    
    /**
     * Returns the stock's symbol.
     *
     * @return the stock's symbol
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * Sets the stock's symbol.
     *
     * @param symbol the new symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    /**
     * Returns the stock's price.
     *
     * @return the stock's price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Sets the stock's price.
     *
     * @param price the new price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Returns the stock's volume.
     *
     * @return the stock's volume
     */
    public long getVolume() {
        return volume;
    }
    
    /**
     * Sets the stock's volume.
     *
     * @param volume the new volume
     */
    public void setVolume(long volume) {
        this.volume = volume;
    }
    
    /**
     * Returns the stock's market capitalization.
     *
     * @return the stock's market capitalization
     */
    public long getMarketCap() {
        return marketCap;
    }
    
    /**
     * Sets the stock's market capitalization.
     *
     * @param marketCap the new market capitalization
     */
    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }
    
    /**
     * Returns a string representation of the stock.
     *
     * @return a string representation of the stock
     */
    @Override
    public String toString() {
        return "Stock [symbol=" + symbol + ", price=" + price + ", volume=" + volume + ", marketCap=" + marketCap + "]";
    }
}