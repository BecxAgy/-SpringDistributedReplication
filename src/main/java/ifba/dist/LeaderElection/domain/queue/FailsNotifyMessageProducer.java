package ifba.dist.LeaderElection.domain.queue;

public interface FailsNotifyMessageProducer {
	void notifyFail(String message);
}