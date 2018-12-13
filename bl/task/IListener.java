package bl.task;

public interface IListener {

    void progressSet(int volume);

    void progressChanged(int value);

    float getStat();

}
