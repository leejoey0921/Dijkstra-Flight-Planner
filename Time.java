public class Time {
    int hour, min;

    public Time(String time) {
        int temp = Integer.parseInt(time);
        this.hour = (temp / 100) % 24;
        this.min = temp % 100;
    }

    public Time(int hour, int min) {
        this.hour = (hour + min/60) % 24;
        this.min = min % 60;
    }

    public static Time add(Time t1, Time t2) {
        int m = t1.min + t2.min;
        int h = t1.hour + t2.hour;

        return new Time(h, m);
    }

    public static int elapsed(Time start, Time end) { // returns elapsed time in minutes(int)
        int ans = (end.hour - start.hour) * 60 + (end.min - start.min);
        if (ans < 0) { ans += 24*60; }
        return ans;
    }

    public String toString() { // returns time in HHMM format
        String h;
        if (this.hour < 10) { h = "0"+ Integer.toString(this.hour); }
        else { h = Integer.toString(this.hour); }
        String m;
        if (this.min < 10) { m = "0"+ Integer.toString(this.min); }
        else { m = Integer.toString(this.min); }

        return h+m;
    }

    public void print() {
        System.out.println(this.toString());
    }
}
