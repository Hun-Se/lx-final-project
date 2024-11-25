package com.lx.finalproject.dto.chatGPT;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class AiChatbotResponseDTO {
    @JsonProperty("question")
    private String question;

    @JsonProperty("result")
    private Result result;

    @JsonProperty("message")
    private String message;

    // Getters and Setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Result {
        @JsonProperty("friendly_response")
        private String friendlyResponse;

        @JsonProperty("result_data")
        private List<Map<String, Object>> resultData;

        @JsonProperty("sql_query")
        private String sqlQuery;

        // Getters and Setters
        public String getFriendlyResponse() {
            return friendlyResponse;
        }

        public void setFriendlyResponse(String friendlyResponse) {
            this.friendlyResponse = friendlyResponse;
        }

        public List<Map<String, Object>> getResultData() {
            return resultData;
        }

        public void setResultData(List<Map<String, Object>> resultData) {
            this.resultData = resultData;
        }

        public String getSqlQuery() {
            return sqlQuery;
        }

        public void setSqlQuery(String sqlQuery) {
            this.sqlQuery = sqlQuery;
        }
    }
}
