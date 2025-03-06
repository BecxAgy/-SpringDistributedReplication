package ifba.dist.LeaderElection.insfrastructure.queue.rabbit;

import java.util.List;
import java.util.Map;

import ifba.dist.LeaderElection.domain.queue.CommandConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqCommandConsumer implements CommandConsumer {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RabbitListener(queues = "${instance.queue.name}")
	@Override
	public void processSqlCommand(String message) {
		processSQL(message);
	}
	
	private void processSQL(String message) {
		System.out.println("Received SQL Command: " + message);
		try {
			if (message.trim().toUpperCase().startsWith("SELECT")) {
				List<Map<String, Object>> results = jdbcTemplate.queryForList(message);


	            for (Map<String, Object> row : results) {
	                System.out.println(row);
	            }
	            return;
			}
			
			jdbcTemplate.execute(message);
		} catch (Exception e) {
			e.printStackTrace();;
		}
	}

}