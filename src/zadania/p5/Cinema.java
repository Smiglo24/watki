package zadania.p5;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private List<Seance> seanceList = new ArrayList<>();
    private List<Hall> hallList = new ArrayList<>();

    public Cinema(List<Hall> hallList) {
        this.hallList = hallList;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "seanceList=" + seanceList +
                ", hallList=" + hallList +
                '}';
    }

    public List<Seance> getSeanceList() {
        return seanceList;
    }

    public void setSeanceList(List<Seance> seanceList) {
        this.seanceList = seanceList;
    }

    public List<Hall> getHallList() {
        return hallList;
    }

    public void setHallList(List<Hall> hallList) {
        this.hallList = hallList;
    }
}
