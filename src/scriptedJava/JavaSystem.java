package scriptedJava;


import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

public class JavaSystem {
    private ScriptEngine engine;
    private TestRunner runner;
    Path dir;
    WatchKey key;

    public JavaSystem() {
        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        engine = factory.getEngineByName("groovy");

        runner = new TestRunner();

        WatchService watcher = null;
        try {
            watcher = FileSystems.getDefault().newWatchService();
            dir     = Paths.get("/Users/jordi/dev/dynatrace/TryThingsJava/src/scriptedJava/");
            try {
                key = dir.register(watcher,
                        ENTRY_CREATE,
                        ENTRY_MODIFY);
            } catch (IOException x) {
                System.err.println(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JavaSystem system = new JavaSystem();
        
        system.startScriptChangeDetectionLoop();
    }

    private void startScriptChangeDetectionLoop() {
        runScript(getScript());
        while(true){
            if(isThereNewScript()) {
                runScript(getScript());
            }
        }
    }

    private void runScript(FileReader script) {
        // evaluate JavaScript code from String
        try {
            engine.put("runner", runner);
            Integer sum = (Integer)engine.eval(script);//("(1..10).sum()");
            System.out.println("sum="+sum);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    private FileReader getScript() {
        try {
            return new FileReader("/Users/jordi/dev/dynatrace/TryThingsJava/src/scriptedJava/GroovyScript.groovy");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isThereNewScript() {
        for (WatchEvent<?> event: key.pollEvents()) {
            WatchEvent.Kind<?> kind = event.kind();

            // This key is registered only
            // for ENTRY_CREATE events,
            // but an OVERFLOW event can
            // occur regardless if events
            // are lost or discarded.
            if (kind == OVERFLOW) {
                continue;
            }

            if (kind == ENTRY_CREATE) {
                // The filename is the
                // context of the event.
                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();

                System.out.format("New file found: %s%n", filename);

                boolean valid = key.reset();
                return true;
            }
        }

        // Reset the key -- this step is critical if you want to
        // receive further watch events.  If the key is no longer valid,
        // the directory is inaccessible so exit the loop.
        boolean valid = key.reset();

        return false;
    }

}
