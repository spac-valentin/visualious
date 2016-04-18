package ro.visualious.api.rest.endpoint;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import ro.visualious.api.rest.model.Feedback;
import ro.visualious.api.rest.model.ResultsDAO;
import ro.visualious.businesslogic.QuestionDispatcher;
import ro.visualious.businesslogic.Result;

/**
 * Created by Spac on 6/2/2015.
 */
@Path("question/{questionId}")
public class Question {
    public static Logger log = Logger.getLogger(Question.class.getCanonicalName());

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Result getMoreResults(@BeanParam ResultsDAO resultsDAO) {
        if (log.isInfoEnabled()) {
            log.info("More results for : " + resultsDAO.toString());
        }
        return QuestionDispatcher.getMoreResults(resultsDAO);
    }

    @POST
    @Path("{answerId}/like/{feedback}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateLike(@BeanParam Feedback feedback) {
        if (log.isInfoEnabled()) {
            log.info("Like for : " + feedback.getAnswerId());
        }
        if(QuestionDispatcher.updateLike(feedback) > 0) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("{answerId}/dislike/{feedback}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateDislike(@BeanParam Feedback feedback) {
        if (log.isInfoEnabled()) {
            log.info("Dislike for : " + feedback.getAnswerId());
        }
        if(QuestionDispatcher.updateDislike(feedback) > 0) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }
}
