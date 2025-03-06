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
		try {
			String trimmedMessage = message.trim().replaceAll("(?m)^\\s*--.*$", ""); // Remove comentários de linha
			String firstWord = trimmedMessage.split("\\s+")[0].toUpperCase(); // Pega a primeira palavra real

			if ("SELECT".equals(firstWord)) {
				System.out.println("Executing SQL Command: " + message);
				List<Map<String, Object>> results = jdbcTemplate.queryForList(message);

				for (Map<String, Object> row : results) {
					System.out.println(row);
				}
				return;
			}

			// Se não for SELECT, impede execução de DDL ou comandos perigosos (opcional)
			if (!List.of("INSERT", "UPDATE", "DELETE").contains(firstWord)) {
				throw new IllegalArgumentException("Invalid SQL command: " + firstWord);
			}

			jdbcTemplate.execute(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}