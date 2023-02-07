package org.bolcom.app.domain.models;


public class Pits {

    private int bigPit;
    private int [] littlePits;

    public Pits (){
        bigPit = 0;
        littlePits = new int[]{6,6,6,6,6,6};
    }

    public Pits (int first,int second,int third,int fourth, int fifth,int seventh,int bigPit){
        this.bigPit = bigPit;
        littlePits = new int[]{first,second,third,fourth,fifth,seventh};
    }


    public int pickStones(int from){
        int stones = this.littlePits[from];
        this.littlePits[from] = 0;
        return stones;
    }

    public void putStones(int where, int stonesQty){
        int stones = this.littlePits[where];
        this.littlePits[where] = stones + stonesQty;
    }

    public void putStonesOnBigPit(int qtyStones){
        int total = bigPit;
        bigPit = total + qtyStones;
    }

    public int[] getLittlePits(){
        return littlePits;
    }

    public int getBigPit() {
        return bigPit;
    }

    public void setLittlePits(int[] littlePits) {
        this.littlePits = littlePits;
    }

    public void setBigPit(int bigPit){
        this.bigPit = bigPit;
    }
}
