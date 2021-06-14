package sample;

public class Patch {
    public String ral;
    public String hex;
    public int[] hlc;
    public int H;
    public int L;
    public int C;
    public int Cmax;
    public int gamme6;//il y a 6 gamme6 car 6*6=36

    Patch(int H, int L, int C) {
        this.ral = H + " " + L + " " + C;
        this.hex = null;
        this.H = H;
        this.L = L;
        this.C = C;
        this.hlc = new int[]{this.H, this.L, this.C};
        this.gamme6 = (H/10) % 6;
    }

    Patch(String hex, String H, String L, String C, String cmax) {
        this.ral = H + " " + L + " " + C;
        this.hex = hex;
        this.H = Integer.parseInt(H);
        this.L = Integer.parseInt(L);
        this.C = Integer.parseInt(C);
        this.hlc = new int[]{this.H, this.L, this.C};
        this.Cmax = Integer.parseInt(cmax);
        this.gamme6 = this.H % 6;
    }
}
