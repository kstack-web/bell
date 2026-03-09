package model;

public class Yoyaku {
    private int id;
    private String date;
    private String time;
    private int kanjaID;
    private boolean valid;
    private String kanjaName;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public int getKanjaID() { return kanjaID; }
    public void setKanjaID(int kanjaID) { this.kanjaID = kanjaID; }

    public boolean isValid() { return valid; }
    public void setValid(boolean valid) { this.valid = valid; }
    
    public String getKanjaName() { return kanjaName; }
    public void setKanjaName(String kanjaName) { this.kanjaName = kanjaName; }
}
