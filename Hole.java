public class Hole {
    private TypeHole type;
    private int seedCount;

    public Hole(TypeHole type, int initialSeeds) {
        this.type = type;
        this.seedCount = initialSeeds;
    }

    public int getSeedCount() {
        return seedCount;
    }

    public void addSeed() {
        seedCount++;
    }

    public int takeSeeds() {
        int seeds = seedCount;
        seedCount = 0;
        return seeds;
    }

    public TypeHole getType() {
        return type;
    }
}
