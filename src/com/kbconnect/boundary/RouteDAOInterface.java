package com.kbconnect.boundary;

import java.util.ArrayList;
import com.kbconnect.entity.Route;

public interface RouteDAOInterface {
	
	/**
	 * 
	 * @return all the routes from the database
	 */
    public ArrayList<Route> getAllRoutes();

    /**
     * 
     * @param routeId the route id to be retrieved from the database
     * @return the Route if an id matches some id in the database
     */
    public Route getRoute ( int routeId );

    /**
     * 
     * @param newRoute the new route to be created
     * @return true if the insert statement is successful
     */
    public boolean createRoute (Route newRoute );

    /**
     * 
     * @param updatedRoute the route to be updated
     * @return true if the statement is successful
     */
    public boolean updateRoute (Route updatedRoute);
    
    /**
     * 
     * @param deletedRoute the route to be deleted
     * @return true if the statement is successful
     */
    public boolean deleteRoute (Route deletedRoute );

}
