package ifba.dist.LeaderElection.insfrastructure.queue.rabbit;

import ifba.dist.LeaderElection.domain.queue.RemoveBind;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "instance.id", havingValue = "1")
@Component
public class RabbitMqRemoveBind implements RemoveBind {

	@Autowired
	private RabbitAdmin rabbitAdmin;
	
	@Override
	public void unbindQueue(String queueName) {
		rabbitAdmin.deleteQueue(queueName);
	}

}