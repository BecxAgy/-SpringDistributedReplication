package ifba.dist.LeaderElection.insfrastructure.queue.rabbit;


import ifba.dist.LeaderElection.domain.queue.HeartBeatProducer;
import ifba.dist.LeaderElection.shared.config.RabbitMqConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqHeartBeatProducer implements HeartBeatProducer {

	private final RabbitTemplate rabbitTemplate;
	
	
	public RabbitMqHeartBeatProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}


	@Override
	public void sendHeartbeat(String message) {
			System.out.println("Replica " + message + " sending heartbeat");
			rabbitTemplate.convertAndSend(RabbitMqConfiguration.QUEUE1_NAME, message);
	}
	
}