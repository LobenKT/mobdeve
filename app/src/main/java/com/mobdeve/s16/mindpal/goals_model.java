package com.mobdeve.s16.mindpal;

public class goals_model {
    String GoalTitle;
    int CurrentProgress;
    int GoalValue;

    public goals_model(String goalTitle, int currentProgress, int goalValue) {
        GoalTitle = goalTitle;
        CurrentProgress = currentProgress;
        GoalValue = goalValue;
    }

    public String getGoalTitle() {
        return GoalTitle;
    }

    public int getCurrentProgress() {
        return CurrentProgress;
    }

    public int getGoalValue() {
        return GoalValue;
    }

    public void setGoalTitle(String goalTitle) {
        GoalTitle = goalTitle;
    }

    public void setCurrentProgress(int currentProgress) {
        CurrentProgress = currentProgress;
    }

    public void setGoalValue(int goalValue) {
        GoalValue = goalValue;
    }
}
