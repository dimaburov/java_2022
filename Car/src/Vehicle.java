public class Vehicle {
    private String firm;
    private String model;
    private int price;
    private double rate;
    private double percent;

    public Vehicle(String firm, String model, int price, double rate, double percent) {
        this.firm = firm;
        this.model = model;
        this.price = price;
        this.rate = rate;
        this.percent = percent;
    }

    public int getPrice() { return price; }
    public String getFirm() { return firm; }
    public String getModel() { return model; }
    public double getRate() { return rate; }
    public double getPercent()  {return percent; }

    @Override
    public String toString() {
        return "firm='" + firm + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", rate=" + rate +
                ", percent=" + percent +
                '}';
    }
}
