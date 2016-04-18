package ro.visualious.businesslogic;

import java.util.List;

import ro.visualious.api.rest.model.Feedback;
import ro.visualious.api.rest.model.ResultsDAO;
import ro.visualious.persistence.MongoDBManager;
import ro.visualious.responsegenerator.model.Answer;
import ro.visualious.responsegenerator.parser.helper.Constants;

/**
 * Created by Spac on 6/21/2015.
 */
public class QuestionDispatcher {

    public static int updateDislike(Feedback feedback) {
        return MongoDBManager.dislikeQuestion(feedback.getQuestionId(), feedback.getAnswerId(),
                feedback.getFeedback());
    }

    public static int updateLike(Feedback feedback) {
        return MongoDBManager.likeQuestion(feedback.getQuestionId(), feedback.getAnswerId(),
                feedback.getFeedback());

    }

    public static Result getMoreResults(ResultsDAO resultsDAO) {
        List<Answer> answers = MongoDBManager.getAnswersForQuestion(
                resultsDAO.getQuestionId(),
                resultsDAO.getOffset(),
                Math.max(resultsDAO.getMax(), Constants.MAX_CHUNK_SIZE)
        );

        return Dispatcher.toResultObject(answers);
    }
}
