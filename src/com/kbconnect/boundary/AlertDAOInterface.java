package com.kbconnect.boundary;

import java.util.ArrayList;
import com.kbconnect.entity.Alert;

/**
 *  Create the AlertDAO interface for AlertDAO to CRUD operations
 * @author 300108357 Weijun Shi
 *
 */
public interface AlertDAOInterface {
	
	/**
	 * 
	 * @return
	 */
    public ArrayList<Alert> getAllAlerts();

    /**
     * 
     * @param alertId
     * @return
     */
    public Alert getAlert( int alertId );

    /**
     * 
     * @param newAlert
     * @return
     */
    public boolean createAlert (Alert newAlert);

    /**
     * 
     * @param updatedAlert
     * @return
     */
    public boolean updateAlert (Alert updatedAlert);
    
    /**
     * 
     * @param deletedAlert
     */
    public void deleteAlert (Alert deletedAlert);

}
