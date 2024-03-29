package ro.visualious.api.rest.endpoint;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import ro.visualious.api.rest.model.SearchDAO;
import ro.visualious.businesslogic.Dispatcher;
import ro.visualious.businesslogic.Result;

/**
 * Created by Spac on 25 Ian 2015.
 */
@Path("query")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class Search {
    public static Logger log = Logger.getLogger(Search.class.getCanonicalName());

    @GET
    public Result query(@BeanParam SearchDAO searchDAO) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Search query : " + searchDAO.toString());
        }

        return Dispatcher.executeQuery(searchDAO);
    }

    @GET
    @Path("topSearches")
    public java.util.List<ro.visualious.responsegenerator.model.Question> topSearches() {
        if (log.isInfoEnabled()) {
            log.info("Top searches");
        }

        return Dispatcher.getTopSearches();
    }


}
