package ifba.dist.LeaderElection.domain.queue;

public interface CommandConsumer {
	void processSqlCommand(String message);
}