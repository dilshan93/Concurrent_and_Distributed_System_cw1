package DateTime;

import java.time.LocalDateTime;

public class DateTime implements Comparable <DateTime>{

    private int year;
    private int month;
    private int day;

    private int hours;
    private int minutes;
    private int seconds;

    private String date;
    private String time;

    public DateTime(int year, int month, int day, int hours, int minutes, int seconds) {
        super();
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public DateTime(int year, int month, int day) {
        super();
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public DateTime() {
        this.date = this.getSystemDate();
        this.time = this.getSystemTime();

    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getDate(){
        String formattedDay = String.format("%02d", this.day);
        String formattedMonth = String.format("%02d", this.month);

        return formattedDay + "/" + formattedMonth + "/" + this.year;
    }

    public String getTime(){
        String formattedHour = String.format("%02d", this.hours);
        String formattedMinutes = String.format("%02d", this.minutes);
        String formattedSeconds = String.format("%02d", this.seconds);

        return formattedHour + ":" + formattedMinutes + ":" + formattedSeconds;
    }

    public String getSystemDate(){
        LocalDateTime currentTime = LocalDateTime.now();
        // Initialize day
        this.day = currentTime.getDayOfMonth();
        // Initialize month
        this.month = currentTime.getMonthValue();
        // Initialize year
        this.year = currentTime.getYear();

        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
    public String getSystemTime(){
        LocalDateTime currentTime = LocalDateTime.now();
        // Initialize hour
        this.hours = currentTime.getHour();
        // Initialize minutes
        this.minutes = currentTime.getMinute();
        // Initialize seconds
        this.seconds = currentTime.getSecond();
        return "Time{" +
                "hour=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }

    public long getSecondInaDay(){
        return this.year*365*24*60*60+
                this.month*30*24*60*60+this.day*24*60*60+this.hours*60*60+
                this.minutes*60+this.seconds;
    }

    public String getDateTime(){
        return this.getDate() + " " + this.getTime();
    }

    @Override
    public int compareTo(DateTime o) {

        return (int) (this.getSecondInaDay() - o.getSecondInaDay());
    }



}
