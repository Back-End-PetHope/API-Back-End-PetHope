package com.github.znoque.adote_me_api.config;

public class SwaggerDocumentacionConfig {

    // Mensagens de erro
    public static final String RESPONSE_200 = "Login efetuado com sucesso";
    public static final String RESPONSE_201 = "Usuário criado com sucesso";
    public static final String RESPONSE_404 = "Usuário não encontrado";
    public static final String RESPONSE_422 = "O usuário com este email já está registrado. Por favor, utilize um email diferente.";
    public static final String RESPONSE_500 = "Erro interno no servidor";

    // Títulos e descrições

    // User
    public static final String TAG_USER = "Endpoint para criar usuário";
    public static final String SUMARIO_USER = "Criar Usuário";
    public static final String DESCRICAO_USER = "Cria um novo usuário na aplicação.";

    // Auth
    public static final String TAG_AUTH = "Gerenciamento de autenticação de usuários";
    public static final String SUMARIO_LOGIN = "Login de usuário";
    public static final String DESCRICAO_LOGIN = "Autentica um usuário utilizando credenciais.";

    // Google Info
    public static final String SUMARIO_GOOGLE_INFO = "Informações do usuário autenticado com Google";
    public static final String DESCRICAO_GOOGLE_INFO = "Recupera informações do usuário autenticado via OAuth do Google.";

    // Redirecionador
    public static final String SUMARIO_GOOGLE_REDIRECT = "Redirecionar para Google OAuth";
    public static final String DESCRICAO_GOOGLE_REDIRECT = "Redireciona o cliente para a página de autenticação do Google.";
}

