public class Hole {

    private int seedCount;
    private TypeHole type;

    public Hole(int seed, TypeHole aType){
        this.seedCount = seed;
        this.type = aType;
    }

    public int getSeedCount(){
        return seedCount;
    }

    public void setSeedCount(int seedCount){
        this.seedCount = seedCount;
    }

    public TypeHole getType() {
        return type;
    }

    public void addSeeds(int count){
        this.seedCount += count;
    }
    public int takeAllSeeds(){
        int takenSeeds = seedCount;
        seedCount = 0;
        return takenSeeds;
    }
}
