package SessionStorageSimulator.storage;

public class STDataPiece {
    long start;
    long end;
    byte[] data;

    public STDataPiece(long start, long end, byte[] data) {
        this.start = start;
        this.end = end;
        this.data = data;

    }
}
