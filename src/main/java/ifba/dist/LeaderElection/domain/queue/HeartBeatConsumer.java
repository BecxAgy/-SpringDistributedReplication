package ifba.dist.LeaderElection.domain.queue;

public interface HeartBeatConsumer {
    void processHeartBeat(String message);
}
