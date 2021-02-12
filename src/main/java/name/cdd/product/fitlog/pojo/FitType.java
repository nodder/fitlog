package name.cdd.product.fitlog.pojo;

public class FitType {
    private int id;
    private String subtype;
    private String type;
    private int weight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "FitType{" +
                "id=" + id +
                ", subtype='" + subtype + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                '}';
    }
}
