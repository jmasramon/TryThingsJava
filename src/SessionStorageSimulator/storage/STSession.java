package SessionStorageSimulator.storage;

import SessionStorageSimulator.Input.Beacon;
import SessionStorageSimulator.STSimulator;
import SessionStorageSimulator.storage.STData;
import SessionStorageSimulator.storage.STSessionIndex;
import SessionStorageSimulator.storage.STSessionMetadata;

import java.util.ArrayList;

public class STSession {
    STSessionMetadata metadata;
    STSessionIndex    index;
    STData            data;

    public STSession addBeaconData(Beacon beacon) {
//        data.addDataPiece(beacon.payload);
        return this;
    }
}
