package ifba.dist.LeaderElection.domain.queue;

public interface HeartBeatProducer {
	void sendHeartbeat(String message);
}