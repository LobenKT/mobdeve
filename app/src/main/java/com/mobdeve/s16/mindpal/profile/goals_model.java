package com.mobdeve.s16.mindpal.profile;

public class goals_model {
    int id;
    String GoalTitle;
    String CurrentProgress;

    public goals_model(int id, String goalTitle, String currentProgress) {
        this.id = id;
        GoalTitle = goalTitle;
        CurrentProgress = currentProgress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoalTitle() {
        return GoalTitle;
    }

    public void setGoalTitle(String goalTitle) {
        GoalTitle = goalTitle;
    }

    public String getCurrentProgress() {
        return CurrentProgress;
    }

    public void setCurrentProgress(String currentProgress) {
        CurrentProgress = currentProgress;
    }
}
