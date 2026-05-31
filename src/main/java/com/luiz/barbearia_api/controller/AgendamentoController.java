import com.luiz.barbearia_api.model.Agendamento;
import com.luiz.barbearia_api.repository.AgendamentoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "http://localhost:5173")
public class AgendamentoController {

    private final AgendamentoRepository repository;

    public AgendamentoController(AgendamentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Agendamento> listar(
            @RequestParam String profissional,
            @RequestParam String data
    ) {
        return repository.findByProfissionalAndData(profissional, data);
    }

    @PostMapping
    public String agendar(@RequestBody Agendamento agendamento) {

        boolean ocupado = repository.existsByProfissionalAndDataAndHorario(
                agendamento.getProfissional(),
                agendamento.getData(),
                agendamento.getHorario()
        );

        if (ocupado) {
            return "Horario indisponivel";
        }

        repository.save(agendamento);

        return "Agendado com sucesso";
    }
}