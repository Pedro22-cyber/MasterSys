package dev.PedroHS.mastersys.controlle;

import dev.PedroHS.mastersys.dto.AlunoRequest;
import dev.PedroHS.mastersys.dto.AlunoResponse;
import dev.PedroHS.mastersys.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponse cadastrar(@RequestBody @Valid AlunoRequest alunoRequest) {
        return alunoService.cadastrar(alunoRequest);
    }

    @GetMapping
    public Page<AlunoResponse> listar(Pageable pageable){
        return alunoService.listar(pageable);
    }

    @GetMapping("{id}")
    public AlunoResponse burcarPorId(@PathVariable Long id){
        return alunoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AlunoResponse atualizar(@PathVariable Long id, @RequestBody @Valid AlunoRequest alunoRequest) {
        return alunoService.atualizar(id, alunoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        alunoService.excluir(id);
    }
}
