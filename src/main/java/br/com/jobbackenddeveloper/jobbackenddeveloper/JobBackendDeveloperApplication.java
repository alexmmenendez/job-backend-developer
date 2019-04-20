package br.com.jobbackenddeveloper.jobbackenddeveloper;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.hardcoded.PerfilEnum;
import br.com.jobbackenddeveloper.jobbackenddeveloper.repository.ClienteRepository;
import br.com.jobbackenddeveloper.jobbackenddeveloper.util.SenhaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class JobBackendDeveloperApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobBackendDeveloperApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ClienteRepository clienteRepository) {
		return args -> {
			clienteRepository.save(new Cliente("Alex", "alex.menendez", SenhaUtils.gerarBCrypt("123"), PerfilEnum.ROLE_ADMIN));
		};
	}

}
