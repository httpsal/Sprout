package model;

import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import utils.HabitType;
import utils.ObjectiveType;

public class Habit extends RealmObject {
    @PrimaryKey

    public int getImage() {
        return imageResId;
    }

    public void setImage(int image) {
        this.imageResId = image;
    }

    private int imageResId;





    private int id;
    @Required
    private String title;

    //private HabitType habitType; //TODO: Use the util
    private String habitType;
    //private DayOfWeek activeDays; //TODO: Try to make it work with Realm
    //private  RealmList<Integer> notificationList; //TODO: setup notifications

    private @Nullable
    int maxSnoozes;

    //private ObjectiveType objectiveType; //TODO: Use the util
    private int maxCompletedTasks;
    private int maxStreakValue;
    private Date finalDate;

    private RealmList<Task> taskHistory;

    private int bestStreak;
    private int currentStreak;
    private int completedTasks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxSnoozes() {
        return maxSnoozes;
    }

    public void setMaxSnoozes(int maxSnoozes) {
        this.maxSnoozes = maxSnoozes;
    }

    public String getHabitType() {
        return habitType;
    }

    public void setHabitType(String habitType) {
        this.habitType = habitType;
    }

    public int getMaxCompletedTasks() {
        return maxCompletedTasks;
    }

    public void setMaxCompletedTasks(int maxCompletedTasks) {
        this.maxCompletedTasks = maxCompletedTasks;
    }

    public int getMaxStreakValue() {
        return maxStreakValue;
    }

    public void setMaxStreakValue(int maxStreakValue) {
        this.maxStreakValue = maxStreakValue;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public int getBestStreak() {
        return bestStreak;
    }

    public void setBestStreak(int bestStreak) {
        this.bestStreak = bestStreak;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }
}
