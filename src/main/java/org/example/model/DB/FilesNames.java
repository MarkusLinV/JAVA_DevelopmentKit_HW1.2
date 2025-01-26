package org.example.model.DB;

public enum FilesNames {
    MESSAGE_HISTORY("MessageHistory.txt"),
    LIST_OF_USERS("ListOfUsers.txt");

    private String failName;

    private FilesNames(String failName) {
        this.failName = failName;
    }

    public String getFailName() {
        return failName;
    }
}