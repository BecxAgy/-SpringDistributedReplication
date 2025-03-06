package ifba.dist.LeaderElection.insfrastructure.ws.rest;

import ifba.dist.LeaderElection.application.command.SendSQLCommand;
import ifba.dist.LeaderElection.application.service.SendSqlCommandService;
import ifba.dist.LeaderElection.domain.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/command")
public class SqlCommandController {
	
	@Autowired
	private SendSqlCommandService sendSqlCommandService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void enviarComando(@RequestBody SendSQLCommand command) {
		var newCommand = new Command(command.sql());
		sendSqlCommandService.executeCommand(newCommand);
	}
	
}