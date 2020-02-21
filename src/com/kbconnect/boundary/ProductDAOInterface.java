package com.kbconnect.boundary;

import java.util.ArrayList;
import com.kbconnect.entity.Product;

/**
 * 
 * @author Gursewak Singh
 *
 */
public interface ProductDAOInterface {

	/**
	 * 
	 * @return
	 */
    public ArrayList<Product> getAllProducts();

    /**
     * 
     * @param productId
     * @return
     */
    public Product getProduct( int productId );

    /**
     * 
     * @param newProduct
     * @return
     */
    public boolean createProduct (Product newProduct);

    /**
     * 
     * @param updatedProduct
     * @return
     */
    public boolean updateProduct (Product updatedProduct);
    
    /**
     * 
     * @param deletedProduct
     * @return
     */
    public boolean deleteProduct (Product deletedProduct);
}
