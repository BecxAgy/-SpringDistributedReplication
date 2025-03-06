package ifba.dist.LeaderElection.insfrastructure.queue.rabbit;

import ifba.dist.LeaderElection.domain.queue.FailsNotifyMessageProducer;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqFailsNotifiyMessageProducer implements FailsNotifyMessageProducer{
	private final RabbitTemplate rabbitTemplate;
	private  final FanoutExchange fanoutExchange;

	public RabbitMqFailsNotifiyMessageProducer(RabbitTemplate rabbitTemplate, FanoutExchange myFanoutExchangeFails) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.fanoutExchange = myFanoutExchangeFails;
	}


	@Override
	public void notifyFail(String message) {
		rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
	}

}