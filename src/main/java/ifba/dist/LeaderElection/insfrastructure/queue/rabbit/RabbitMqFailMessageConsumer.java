package ifba.dist.LeaderElection.insfrastructure.queue.rabbit;

import ifba.dist.LeaderElection.domain.queue.FailMessageConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqFailMessageConsumer implements FailMessageConsumer {

	@RabbitListener(queues = "${instance.queue.notify}")
	@Override
	public void processFailMessage(String message) {
		System.out.println("The replica with id:"+message+" has been removed from the cluster.");
	}

}