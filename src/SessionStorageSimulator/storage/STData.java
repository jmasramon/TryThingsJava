package SessionStorageSimulator.storage;

import java.util.ArrayList;
import java.util.ListIterator;

public class STData {
    ArrayList<STDataPiece> dataPieces;
    long tail = 0;

    ArrayList<STDataPiece> addDataPiece(byte[] newData) {
        STDataPiece newDataPiece = new STDataPiece(tail, tail + newData.length, newData);
        dataPieces.add(newDataPiece);
        tail = tail + (newDataPiece.end - newDataPiece.start);
        return dataPieces;
    }

    byte[] getDataPiece(long start, long end) {
        ListIterator<STDataPiece> dataIterator =  dataPieces.listIterator();
        while(dataIterator.hasNext()) {
            STDataPiece current = dataIterator.next();
            if (current.start == start && current.end == end) {
                return current.data;
            }
        }
        return null;
    }
}
