package ifba.dist.LeaderElection.insfrastructure.queue.rabbit;

import ifba.dist.LeaderElection.application.service.CheckIfReplicaIsAliveService;
import ifba.dist.LeaderElection.domain.queue.HeartBeatConsumer;
import ifba.dist.LeaderElection.shared.config.RabbitMqConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "instance.id", havingValue = "1")
public class RabbitMqHeartBeatConsumer implements HeartBeatConsumer {

	private final CheckIfReplicaIsAliveService checkIfReplicaIsAliveUseCase;

	@Autowired
	public RabbitMqHeartBeatConsumer(CheckIfReplicaIsAliveService checkIfReplicaIsAliveUseCase) {
		this.checkIfReplicaIsAliveUseCase = checkIfReplicaIsAliveUseCase;
	}

	@RabbitListener(queues = RabbitMqConfiguration.QUEUE1_NAME)
	@Override
	public void processHeartBeat(String message) {
		System.out.println("Testing if replica "+ message + " is alive.");
		checkIfReplicaIsAliveUseCase.updateReplicaStatus(message, System.currentTimeMillis());
	}
}
