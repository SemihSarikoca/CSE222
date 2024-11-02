/**
 * This class manages the stocks using an AVL tree.
 * It provides methods to add or update a stock, remove a stock, search for a stock, update a stock's details, and traverse the AVL tree.
 */
public class StockDataManager {
    /**
     * The AVL tree used to store the stocks.
     */
    private final AVLTree avlTree;
    
    /**
     * Constructs a new StockDataManager object.
     * It initializes the AVL tree.
     */
    public StockDataManager() {
        avlTree = new AVLTree();
    }
    
    /**
     * Adds a new stock to the AVL tree or updates an existing stock.
     * If the stock already exists in the AVL tree, it updates the stock's price, volume, and market cap.
     * Otherwise, it creates a new stock and inserts it into the AVL tree.
     *
     * @param symbol    The symbol of the stock.
     * @param price     The price of the stock.
     * @param volume    The volume of the stock.
     * @param marketCap The market cap of the stock.
     */
    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) {
        Stock existingStock = avlTree.search(symbol);
        if (existingStock != null) {
            existingStock.setPrice(price);
            existingStock.setVolume(volume);
            existingStock.setMarketCap(marketCap);
        } else {
            Stock newStock = new Stock(symbol, price, volume, marketCap);
            avlTree.insert(newStock);
        }
    }
    
    /**
     * Removes a stock from the AVL tree.
     *
     * @param symbol The symbol of the stock to be removed.
     */
    public void removeStock(String symbol) {
        avlTree.delete(symbol);
    }
    
    /**
     * Searches for a stock in the AVL tree.
     *
     * @param symbol The symbol of the stock to be searched.
     * @return The stock if found, null otherwise.
     */
    public Stock searchStock(String symbol) {
        return avlTree.search(symbol);
    }
    
    /**
     * Updates a stock's details.
     * If the stock exists in the AVL tree, it updates the stock's symbol, price, volume, and market cap.
     * If the symbol is changed, it removes the stock from the AVL tree and inserts it again with the new symbol.
     *
     * @param symbol       The current symbol of the stock.
     * @param newSymbol    The new symbol of the stock.
     * @param newPrice     The new price of the stock.
     * @param newVolume    The new volume of the stock.
     * @param newMarketCap The new market cap of the stock.
     */
    public void updateStock(String symbol, String newSymbol, double newPrice, long newVolume, long newMarketCap) {
        Stock stock = avlTree.search(symbol);
        if (stock != null) {
            if (!symbol.equals(newSymbol)) {
                avlTree.delete(symbol);
                stock.setSymbol(newSymbol);
                avlTree.insert(stock);
            }
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
        }
    }
    
    /**
     * Traverses the AVL tree in a specified order.
     * The order can be pre-order (1), in-order (2), or post-order (3).
     * If the order is not valid, it prints an error message.
     *
     * @param order The order of the traversal.
     */
    public void traverse(int order) {
        switch (order) {
            case 1:
                avlTree.preOrderTraversal();
                break;
            case 2:
                avlTree.inOrderTraversal();
                break;
            case 3:
                avlTree.postOrderTraversal();
                break;
            default:
                System.out.println("Invalid order: " + order);
        }
    }
}