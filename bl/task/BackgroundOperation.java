package bl.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackgroundOperation implements IBackgroundOperation {

    private List<IListener> listeners = new ArrayList<>();

    @Override
    public void add(IListener listener) {
        listeners.add(listener);
    }

    @Override
    public void setProgress(int volume) {
        for (IListener listener : listeners) {
            listener.progressSet(volume);
        }
    }

    @Override
    public void changeProgress(int value) {
        for (IListener listener : listeners) {
            listener.progressChanged(value);
        }
    }

    @Override
    public void run(Runnable r) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(r);
    }

}
