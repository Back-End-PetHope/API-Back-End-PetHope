package com.github.znoque.pethope.docs;

import com.github.znoque.pethope.dto.ResponseDto;
import com.github.znoque.pethope.dto.pet.PetRequestDto;
import com.github.znoque.pethope.dto.pet.PetResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static com.github.znoque.pethope.commons.Constants.*;

@Tag(name = PetApi.TAG_PET)
public interface PetApi {

    String TAG_PET = "Gerenciamento de Pets";

    @Operation(summary = "Criar Pet", description = "Cria um novo pet na base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_201_CREATED, description = DEFAULT_RESPONSE_201),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<PetResponseDto>> create(PetRequestDto petRequestDto);

    @Operation(summary = "Atualizar Pet", description = "Atualiza um pet existente com base no ID.")
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<PetResponseDto>> update(int id, PetRequestDto petRequestDto);

    @Operation(summary = "Remover Pet", description = "Remove um pet da base de dados pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_204_NO_CONTENT, description = DEFAULT_RESPONSE_204),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<Void> delete(int id);

    @Operation(summary = "Buscar Pet por ID", description = "Recupera um pet específico pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<PetResponseDto>> getById(int id);

    @Operation(summary = "Listar todos os Pets", description = "Retorna uma lista com todos os pets cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<List<PetResponseDto>>> getAll();

    @Operation(summary = "Listar espécies", description = "Retorna todas as espécies disponíveis para cadastro.")
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200)
    })
    ResponseEntity<List<Map<String, String>>> getEspecies();

    @Operation(summary = "Listar raças por espécie", description = "Retorna as raças de uma determinada espécie.")
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_400_BAD_REQUEST, description = DEFAULT_RESPONSE_400)
    })
    ResponseEntity<List<Map<String, String>>> getRacasbyEspecie(String especie);


    @Operation(summary = "Listar temperamentos", description = "Retorna todos os temperamentos disponíveis.")
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200)
    })
    ResponseEntity<List<Map<String, String>>> getTemperamentos();

    @Operation(summary = "Listar sexos", description = "Retorna todos os sexos disponíveis.")
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200)
    })
    ResponseEntity<List<Map<String, String>>> getSexos();

    @Operation(summary = "Buscar Pets com filtros", description = "Busca pets por filtros de espécie, raça e idade.")
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_400_BAD_REQUEST, description = DEFAULT_RESPONSE_400)
    })
    ResponseEntity<ResponseDto<List<PetResponseDto>>> getByFilters(String especie, String raca, Integer idadeMin, Integer idadeMax);

    @Operation(summary = "Inativar Pet", description = "Inativa um pet, marcando-o como não disponível para adoção.")
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404)
    })
    ResponseEntity<ResponseDto<PetResponseDto>> deactivatePet(int id);
}
