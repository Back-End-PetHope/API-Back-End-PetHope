package com.github.znoque.pethope.docs;

import com.github.znoque.pethope.dto.user.*;
import com.github.znoque.pethope.dto.ResponseDto;
import com.github.znoque.pethope.dto.user.clinica.ClinicaRequestDto;
import com.github.znoque.pethope.dto.user.ong.OngRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.github.znoque.pethope.commons.Constants.*;

@Tag(name = UserApi.TAG_USER)
public interface UserApi {

    String TAG_USER = "Gerenciamento do usuário";

    @Operation(
            summary = "Listar Usuários",
            description = "Retorna todos os usuários cadastrados.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<List<GlobalUserResponseDto>>> getAll();

    @Operation(
            summary = "Buscar Usuário por ID",
            description = "Retorna um usuário específico pelo ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_400_BAD_REQUEST, description = DEFAULT_RESPONSE_400),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<UserResponseDto>> getUserById(String id);

    @Operation(
            summary = "Buscar Ong por ID",
            description = "Retorna uma ONG específica pelo ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_400_BAD_REQUEST, description = DEFAULT_RESPONSE_400),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<GlobalUserResponseDto>> getOngById(String id);

    @Operation(
            summary = "Buscar Clínica por ID",
            description = "Retorna uma clínica específica pelo ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_400_BAD_REQUEST, description = DEFAULT_RESPONSE_400),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<GlobalUserResponseDto>> getClinicaById(String id);

    @Operation(
            summary = "Criar Usuário",
            description = "Cria um novo usuário na aplicação."
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_201_CREATED, description = DEFAULT_RESPONSE_201),
            @ApiResponse(responseCode = STATUS_409_CONFLICT, description = DEFAULT_RESPONSE_409),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<UserResponseDto>> createUser(UserRequestDto data);

    @Operation(
            summary = "Criar Clínica",
            description = "Cria um novo usuário do tipo Clínica."
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_201_CREATED, description = DEFAULT_RESPONSE_201),
            @ApiResponse(responseCode = STATUS_409_CONFLICT, description = DEFAULT_RESPONSE_409),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<GlobalUserResponseDto>> createClinica(ClinicaRequestDto data);

    @Operation(
            summary = "Criar Ong",
            description = "Cria um novo usuário do tipo Ong."
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_201_CREATED, description = DEFAULT_RESPONSE_201),
            @ApiResponse(responseCode = STATUS_409_CONFLICT, description = DEFAULT_RESPONSE_409),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<GlobalUserResponseDto>> createOng(OngRequestDto data);

    @Operation(
            summary = "Atualizar Usuário",
            description = "Atualiza as informações de um usuário existente.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_400_BAD_REQUEST, description = DEFAULT_RESPONSE_400),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_409_CONFLICT, description = DEFAULT_RESPONSE_409),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<UserResponseDto>> updateUser(String id, UserUpdateRequestDto data);

    @Operation(
            summary = "Atualizar Clinica",
            description = "Atualiza as informações de uma clínica existente.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_400_BAD_REQUEST, description = DEFAULT_RESPONSE_400),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_409_CONFLICT, description = DEFAULT_RESPONSE_409),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<GlobalUserResponseDto>> updateClinica(String id, ClinicaRequestDto data);

    @Operation(
            summary = "Atualizar Ong",
            description = "Atualiza as informações de uma ONG existente.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_400_BAD_REQUEST, description = DEFAULT_RESPONSE_400),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_409_CONFLICT, description = DEFAULT_RESPONSE_409),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<GlobalUserResponseDto>> updateOng(String id, OngRequestDto data);

    @Operation(
            summary = "Deletar Usuário",
            description = "Deleta um usuário existente pelo ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_204_NO_CONTENT, description = "Recurso removido com sucesso"),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<String>> deleteUser(String id);

    @Operation(
            summary = "Deletar Clinica",
            description = "Deleta uma clínica existente pelo ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_204_NO_CONTENT, description = "Recurso removido com sucesso"),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<String>> deleteClinica(String id);

    @Operation(
            summary = "Deletar Ong",
            description = "Deleta uma ONG existente pelo ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_204_NO_CONTENT, description = "Recurso removido com sucesso"),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<String>> deleteOng(String id);

    @Operation(
            summary = "Login",
            description = "Autentica um usuário e retorna um token de acesso."
    )
    @ApiResponses({
            @ApiResponse(responseCode = STATUS_200_OK, description = DEFAULT_RESPONSE_200),
            @ApiResponse(responseCode = STATUS_404_NOT_FOUND, description = DEFAULT_RESPONSE_404),
            @ApiResponse(responseCode = STATUS_500_INTERNAL_SERVER_ERROR, description = DEFAULT_RESPONSE_500)
    })
    ResponseEntity<ResponseDto<AuthResponseDto>> login(AuthResquestDto data);

}
