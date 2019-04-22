package br.com.jobbackenddeveloper.jobbackenddeveloper.job;

import br.com.jobbackenddeveloper.jobbackenddeveloper.domain.Cliente;
import br.com.jobbackenddeveloper.jobbackenddeveloper.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Value("${job.on}")
    private Boolean jobOn;

    @Autowired
    private ClienteService clienteService;

    @Scheduled(cron = "* 0/10 * * * *")
    public void jobKeepMeAlive() {

        if (!jobOn) {
            return;
        }

       System.out.println(" --- Job para manter app vivo no Heroku ---");

       Cliente cliente = clienteService.buscarPeloId("1");

       System.out.println(cliente.toString());

    }

}
