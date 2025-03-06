package ifba.dist.LeaderElection.application.service;

import ifba.dist.LeaderElection.domain.Command;
import ifba.dist.LeaderElection.domain.queue.CommandProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SendSqlCommandService {

	@Autowired
	private CommandProducer commandProducer;

	@Value("${instance.leader}")
	private String liderId;
	
	@Value("${instance.id}")
	private String instaciaId;

	public void executeCommand(Command command) {
		if (this.instaciaId.equals(this.liderId)) {
			commandProducer.sendSqlCommand(command.getCommandSql());
		}
	}

}