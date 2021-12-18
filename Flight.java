public class Flight {

  String src, dest;  // source and destination airport names
  Time stime, dtime;  // start time, arrival time
  String printForm;  // data in print format [src -> dest:stime -> dtime]

  // constructor
  public Flight(String src, String dest, String stime, String dtime) {
    this.src = src;
    this.dest = dest;
    this.stime = new Time(stime);
    this.dtime = new Time(dtime);
    this.printForm = "[" + src + "->" + dest + ":" + stime + "->" + dtime + "]";

  }

  public void print() {
    System.out.print(printForm);
  }
}
