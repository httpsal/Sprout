package utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hcifedii.sprout.enumerations.Days;

import java.util.Calendar;
import java.util.List;

import model.Habit;
import model.Reminder;

/**
 * You can test this class from command like with:
 * <p>
 * > adb shell am broadcast -a android.intent.action.BOOT_COMPLETED com.hcifedii.sprout
 * <p>
 * If it fails, then try first: > adb root
 */
public class BootCompletedReceiver extends BroadcastReceiver {
    private final Calendar calendar = Calendar.getInstance();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Log.i(this.getClass().getSimpleName(), "Boot received " + intent.getAction());

            // Boot detected. Recreate the alarms for today
            habitsAlarmsSetUp(context);

            // Do other stuff here
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
                long millis = cal.getTimeInMillis();

                Intent mIntent = new Intent(context, DBAlarmReceiver.class);
                mIntent.putExtra("repeat", false);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1000, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC, millis, pendingIntent);
            } else {
                Log.e("AlarmManager", "Non sono riuscito a creare l'alarm.");
            }
        }
    }


    private void habitsAlarmsSetUp(Context context) {

        NotificationAlarmManager manager = new NotificationAlarmManager(context);

        Days today = Days.today(calendar);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        // Get today's habit list
        List<Habit> habitList = HabitRealmManager.getUnarchivedHabits();

        for (Habit habit : habitList) {

            if (!habit.getFrequency().contains(today))
                // This habit is not scheduled for today
                continue;

            List<Reminder> reminderList = habit.getReminders();

            if (reminderList.size() < 1)
                // No reminders for this habit
                continue;

            // Set the notification info
            manager.setNotificationData(habit.getTitle(), habit.getId());

            for (Reminder reminder : reminderList) {

                if (!reminder.isActive() || reminder.isInThePast(hour, minutes))
                    // This reminder shouldn't get an alarm
                    continue;

                int oldRequestCode = reminder.getAlarmRequestCode();

                manager.setRequestCode(reminder.getAlarmRequestCode());
                int reqCode = manager.setAlarmAt(reminder.getHours(), reminder.getMinutes());

                reminder.setAlarmRequestCode(reqCode);

                // Reset the request code
                manager.setRequestCode(0);

                if (oldRequestCode == 0) {
                    // Since it's an unmanaged RealmList, I have to save the reminder changes.
                    HabitRealmManager.saveOrUpdateHabit(habit);
                }
            }
        }
    }

}
