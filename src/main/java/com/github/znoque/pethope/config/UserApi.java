package com.github.znoque.pethope.config;

import com.github.znoque.pethope.dto.GlobalPatternResponseDto;
import com.github.znoque.pethope.dto.GlobalResponseDto;
import com.github.znoque.pethope.dto.clinica.ClinicaRequestDto;
import com.github.znoque.pethope.dto.ong.OngRequestDto;
import com.github.znoque.pethope.dto.user.AuthResquestDto;
import com.github.znoque.pethope.dto.user.UserRequestDto;
import com.github.znoque.pethope.dto.user.UserResponseDto;
import com.github.znoque.pethope.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;


import java.util.List;

@Tag(name = UserApi.TAG_USER)
public interface UserApi {

  // Mensagens de erro
  public static final String RESPONSE_200 = "Login efetuado com sucesso";
  public static final String RESPONSE_201 = "Usuário criado com sucesso";
  public static final String RESPONSE_404 = "Usuário não encontrado";
  public static final String RESPONSE_422 = "O usuário com este email já está registrado. Por favor, utilize um email diferente.";
  public static final String RESPONSE_500 = "Erro interno no servidor";

  // Títulos e descrições

  // User
  public static final String TAG_USER = "Documentação do usuário";
  public static final String POST_SUMARIO_USER = "Criar Usuário";
  public static final String POST_DESCRICAO_USER = "Cria um novo usuário na aplicação.";
  public static final String SUMARIO_LOGIN = "Login de usuário";
  public static final String DESCRICAO_LOGIN = "Autentica um usuário utilizando credenciais.";
  public static final String GET_SUMARIO_USER = "Busca Usuários";
  public static final String GET_DESCRICAO_USER = "Busca todos os Usuários na aplicação";
  public static final String GET_SUMARIO_USERBYID = "Busca pelo Id";
  public static final String GET_DESCRICAO_USERBYID = "Busca os Usuários pelo Id Unico na aplicação";
  public static final String SUMARIO_UPDATE = "Atualizar informações do usuário";
  public static final String DESCRICAO_UPDATE = "Atualiza as informações do usuário na aplicação.";
  public static final String SUMARIO_DELETE = "Deletar usuário";
  public static final String DESCRICAO_DELETE = "Remove um usuário existente com base no ID fornecido.";

  // Clinica
  public static final String POST_SUMARIO_CLINICA = "Criar Clinica";
  public static final String POST_DESCRICAO_CLINICA = "Cria um novo usuário tipo Clinica na aplicação.";
  public static final String GET_SUMARIO_CLINICABYID = "Busca pelo Id";
  public static final String GET_DESCRICAO_CLINICABYID = "Busca os Usuários Tipo Clinica pelo Id Unico na aplicação";
  public static final String SUMARIO_UPDATE_CLINICA = "Atualizar informações da clínica";
  public static final String DESCRICAO_UPDATE_CLINICA = "Atualiza as informações da clínica na aplicação.";
  public static final String SUMARIO_DELETE_CLINICA = "Deletar clínica";
  public static final String DESCRICAO_DELETE_CLINICA = "Remove uma clínica existente com base no ID fornecido.";
  // Ong
  public static final String POST_SUMARIO_ONG = "Criar Ong";
  public static final String POST_DESCRICAO_ONG = "Cria um novo usuário tipo Ong na aplicação.";
  public static final String GET_SUMARIO_ONGBYID = "Busca pelo Id";
  public static final String GET_DESCRICAO_ONGBYID = "Busca os Usuários tipo Ong pelo Id Unico na aplicação";

  // Auth
  public static final String TAG_AUTH = "Gerenciamento de autenticação de usuários";

  // Google Info
  public static final String SUMARIO_GOOGLE_INFO = "Informações do usuário autenticado com Google";
  public static final String DESCRICAO_GOOGLE_INFO = "Recupera informações do usuário autenticado via OAuth do Google.";

  // Redirecionador
  public static final String SUMARIO_GOOGLE_REDIRECT = "Redirecionar para Google OAuth";
  public static final String DESCRICAO_GOOGLE_REDIRECT = "Redireciona o cliente para a página de autenticação do Google.";

  @Operation(summary = UserApi.GET_SUMARIO_USER, description = UserApi.GET_DESCRICAO_USER)
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = UserApi.RESPONSE_200),
      @ApiResponse(responseCode = "404", description = UserApi.RESPONSE_404),
      @ApiResponse(responseCode = "500", description = UserApi.RESPONSE_500)
  })
  public ResponseEntity<GlobalPatternResponseDto<List<GlobalResponseDto>>> findAllUser();

  @Operation(summary = UserApi.GET_SUMARIO_USERBYID, description = UserApi.GET_DESCRICAO_USERBYID)
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = UserApi.RESPONSE_200),
      @ApiResponse(responseCode = "404", description = UserApi.RESPONSE_404),
      @ApiResponse(responseCode = "500", description = UserApi.RESPONSE_500)
  })
  public ResponseEntity<GlobalPatternResponseDto<UserResponseDto>> findByIdUser(String id);

  @Operation(summary = UserApi.GET_SUMARIO_ONGBYID, description = UserApi.GET_DESCRICAO_ONGBYID)
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = UserApi.RESPONSE_200),
          @ApiResponse(responseCode = "404", description = UserApi.RESPONSE_404),
          @ApiResponse(responseCode = "500", description = UserApi.RESPONSE_500)
  })
  public ResponseEntity<GlobalPatternResponseDto<GlobalResponseDto>> findByIdOng(String id);

  @Operation(summary = UserApi.GET_SUMARIO_CLINICABYID, description = UserApi.GET_DESCRICAO_CLINICABYID)
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = UserApi.RESPONSE_200),
          @ApiResponse(responseCode = "404", description = UserApi.RESPONSE_404),
          @ApiResponse(responseCode = "500", description = UserApi.RESPONSE_500)
  })
  public ResponseEntity<GlobalPatternResponseDto<GlobalResponseDto>> findByIdClinica(String id);

  @Operation(summary = UserApi.POST_SUMARIO_USER, description = UserApi.POST_DESCRICAO_USER)
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = UserApi.RESPONSE_201),
      @ApiResponse(responseCode = "422", description = UserApi.RESPONSE_422),
      @ApiResponse(responseCode = "500", description = UserApi.RESPONSE_500)
  })

  public ResponseEntity<?> createUser(UserRequestDto data);

  @Operation(summary = UserApi.POST_SUMARIO_CLINICA, description = UserApi.POST_DESCRICAO_CLINICA)
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = UserApi.RESPONSE_201),
      @ApiResponse(responseCode = "422", description = UserApi.RESPONSE_422),
      @ApiResponse(responseCode = "500", description = UserApi.RESPONSE_500)
  })
  public ResponseEntity<?> createClinica(ClinicaRequestDto data);

  @Operation(summary = UserApi.POST_SUMARIO_ONG, description = UserApi.POST_DESCRICAO_ONG)
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = UserApi.RESPONSE_201),
      @ApiResponse(responseCode = "422", description = UserApi.RESPONSE_422),
      @ApiResponse(responseCode = "500", description = UserApi.RESPONSE_500)
  })
  public ResponseEntity<?> createOng(OngRequestDto data);

  @Operation(summary = UserApi.SUMARIO_LOGIN, description = UserApi.DESCRICAO_LOGIN)
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = UserApi.RESPONSE_200),
      @ApiResponse(responseCode = "404", description = UserApi.RESPONSE_404),
      @ApiResponse(responseCode = "500", description = UserApi.RESPONSE_500)
  })
  public ResponseEntity<?> loginUser(AuthResquestDto data);

}
