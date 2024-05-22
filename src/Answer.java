/**
 * Represents an answer to a quiz question
 */
public class Answer {
    private String response; // response
    private boolean isCorrect; // is this answer correct?

    public Answer(String response, boolean isCorrect) {
        this.response = response;
        this.isCorrect = isCorrect;
    }

    public String getResponse() {
        return response;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
