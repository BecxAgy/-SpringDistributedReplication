package ifba.dist.LeaderElection.domain.queue;

public interface CommandProducer {
	void sendSqlCommand(String sqlCommand);
}