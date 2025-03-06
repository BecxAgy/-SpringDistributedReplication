package ifba.dist.LeaderElection.insfrastructure.queue.rabbit;

import ifba.dist.LeaderElection.domain.queue.CommandProducer;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqCommandProducer implements CommandProducer {

	private final RabbitTemplate rabbitTemplate;
	
	private  final FanoutExchange fanoutExchange;
    
	public RabbitMqCommandProducer(RabbitTemplate rabbitTemplate, FanoutExchange myFanoutExchange) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.fanoutExchange = myFanoutExchange;
	}


	@Override
	public void sendSqlCommand(String sqlCommand) {
			rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", sqlCommand);
	}

}