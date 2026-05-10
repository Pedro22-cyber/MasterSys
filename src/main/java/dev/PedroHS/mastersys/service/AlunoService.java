package dev.PedroHS.mastersys.service;

import dev.PedroHS.mastersys.domain.Aluno;
import dev.PedroHS.mastersys.dto.AlunoFiltroRequest;
import dev.PedroHS.mastersys.dto.AlunoRequest;
import dev.PedroHS.mastersys.dto.AlunoResponse;
import dev.PedroHS.mastersys.exception.RegraNegocioException;
import dev.PedroHS.mastersys.repository.AlunoRepository;
import dev.PedroHS.mastersys.specification.AlunoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    public final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponse cadastrar(AlunoRequest request) {
        if (request.email() != null && alunoRepository.existsByEmail(request.email())) {
            throw new RegraNegocioException("Já existe um aluno cadastrado com esse email");
        }

        Aluno aluno = request.toEntity();
        Aluno alunoSalvo = alunoRepository.save(aluno);
        return AlunoResponse.fromEntity(alunoSalvo);
    }

    public Page<AlunoResponse> listar(AlunoFiltroRequest filtro, Pageable pageable) {
        return alunoRepository.findAll(AlunoSpecification.comfiltros(filtro),pageable).map(AlunoResponse::fromEntity);
    }

    public AlunoResponse buscarPorId(Long id) {
        Aluno aluno = buscarEntidadePorId(id);
        return AlunoResponse.fromEntity(aluno);
    }

    public AlunoResponse atualizar(Long id, AlunoRequest request) {
        Aluno aluno = buscarEntidadePorId(id);
        request.preencher(aluno);
        Aluno alunoAtualizado = alunoRepository.save(aluno);
        return AlunoResponse.fromEntity(alunoAtualizado);
    }

    public void excluir(Long id) {
        Aluno aluno = buscarEntidadePorId(id);
        alunoRepository.delete(aluno);
    }

    private Aluno buscarEntidadePorId(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Aluno não encontrado"));
    }
}
