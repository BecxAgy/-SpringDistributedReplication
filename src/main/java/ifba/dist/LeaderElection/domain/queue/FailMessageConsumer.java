package ifba.dist.LeaderElection.domain.queue;

public interface FailMessageConsumer {
	void processFailMessage(String message);
}