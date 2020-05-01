package GroupProjectPersonalErik;

public class counter {
    private int countnumber;
    private int intersect;

    public counter(int intersection){
        intersect=intersection;
        countnumber=intersection;
    }

    public void countdown(){
        this.countnumber=countnumber-1;
    }
    public int getcountnumber(){
        return countnumber;
    }
    public boolean checkcountnumber(){
        return(countnumber<=0);
    }
    public void refreshtime(){
        countnumber=intersect;
    }
    public void changeintersect(int intersect1){
        intersect=intersect1;
    }
}
