/**
 * Created by elnaz on 30.04.16.
 */
public class Result{

    private int TP, FP, FN, TN;

    public Result(){}

    public Result(int TP, int FP, int FN, int TN) {
        this.TP = TP;
        this.FP = FP;
        this.FN = FN;
        this.TN = TN;
    }

    public double precision(){return ((double)this.getTP()/(this.getTP()+this.getFP()));}

    public double recall(){return ((double)this.getTP()/(this.getTP()+this.getFN()));}

    public int getTP() {
        return TP;
    }

    public void setTP(int TP) {
        this.TP = TP;
    }

    public int getFP() {
        return FP;
    }

    public void setFP(int FP) {
        this.FP = FP;
    }

    public int getFN() {
        return FN;
    }

    public void setFN(int FN) {
        this.FN = FN;
    }

    public int getTN() {
        return TN;
    }

    public void setTN(int TN) {
        this.TN = TN;
    }
}