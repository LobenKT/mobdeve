package com.mobdeve.s16.mindpal.profile;

public class goals_model {
    String GoalTitle;
    String CurrentProgress;

    public goals_model(String goalTitle, String currentProgress) {
        GoalTitle = goalTitle;
        CurrentProgress = currentProgress;
    }
    public String getGoalTitle() {
        return GoalTitle;
    }

    public String getCurrentProgress() {
        return CurrentProgress;
    }

    public void setGoalTitle(String goalTitle) {
        GoalTitle = goalTitle;
    }

    public void setCurrentProgress(String currentProgress) {
        CurrentProgress = currentProgress;
    }

}
