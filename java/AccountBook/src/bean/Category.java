package bean;

/**
 * Correspond to table Category
 */
public class Category {
    public int id;
    public String name;

    public int recordNumber;

    /**
     * RecordNumber can be obtained by the number of record, so it does not need to be
     * put into the table. If an information can be obtained from two places,
     * the difficulty to maintain the table and risk of data integrity would increase
     * @return
     */
    public int getRecordNumber() {
        return recordNumber;
    }
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
