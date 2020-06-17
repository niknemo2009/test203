package dairy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Record {
    private Action action;
    private String recordName;
    private int recordDuration;
    private LocalDate recordDate;
    private static ArrayList<Record> journal = new ArrayList<>();

    public int getRecordDuration() {
        return recordDuration;
    }

    public String getRecordName() {
        return recordName;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public Action getAction() {
        return action;
    }

    public ArrayList<Record> getJournal() {
        return journal;
    }

    public Record() {
    }

    public Record(String recordName, int recordDuration, LocalDate recordDate) {
        this.recordName = recordName;
        this.action = findByName(recordName);
        this.recordDate = recordDate;
        this.recordDuration = recordDuration;
        calculateTime();
        journal.add(this);
    }

    private void calculateTime() {
        int minuend;//уменьшаемое
        int subtrahend;//вычетаемое
        Target target = new Target();
        for (int i = 0; i < target.getTargetsArrayList().size(); i++) {
            minuend=target.getTargetsArrayList().get(i).getTimeOnTargetRequired();
            for (int j = 0; j < this.getAction().getActionsArrayList().size(); j++) {
                if (j==this.getAction().getActionsArrayList().indexOf(this.getAction())){
                    subtrahend=(int)(this.recordDuration*target.getTargetsCoefficients().get(i).get(j));
                    target.getTargetsArrayList().get(i).setTimeOnTargetRequired(minuend-subtrahend);
                }
            }
        }
    }

    private Action findByName(String actionName){
        Action action = new Action();
        List<Action> result=action.getActionsArrayList().stream().filter(r->r.getActionName().equals(actionName)).collect(Collectors.toList());
        return result.get(0);
    }

    public String recordsInInterval(LocalDate startDate, LocalDate endDate)
    {
        Record record = new Record();
        String result = "";
        for (int i = startDate.getDayOfYear(); i <= endDate.getDayOfYear(); i++) {
            for (int j = 0; j < journal.size(); j++) {
                if (journal.get(j).getRecordDate().getDayOfYear()==i)
                {
                    result += record.getJournal().get(j).getRecordDate()+" ";
                    result += record.getJournal().get(j).getRecordName()+" ";
                    result += record.getJournal().get(j).getRecordDuration()+" minutes\n";
                }
            }
        }
        return result;
    }
}
