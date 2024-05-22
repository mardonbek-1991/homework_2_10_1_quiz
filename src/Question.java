/**
 * Represents a quiz question
 */
public class Question {
    private String question; // question
    private Answer[] answers = new Answer[4];

    public Question(String question, Answer[] answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public Answer[] getAnswers() {
        return answers;
    }
}
