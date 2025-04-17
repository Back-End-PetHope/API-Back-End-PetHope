USE pethope_db;

CREATE TABLE IF NOT EXISTS `usuario` (
  `usuario_id` varchar(255) NOT NULL,
  `usuario_tipo` enum('USUARIO','CLINICA','ONG') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `usuario_cpf_cnpj` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `usuario_username` varchar(155) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `usuario_responsavel_nome` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `usuario_telefone` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `usuario_logradouro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `usuario_cidade` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `usuario_is_prestador_servico` bit(1) NOT NULL DEFAULT b'0',
  `usuario_site` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `usuario_url_instagram` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `usuario_url_facebook` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `usuario_razao_social` varchar(255) DEFAULT NULL,
  `usuario_senha` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `user_cpf_cnpj` (`usuario_cpf_cnpj`),
  UNIQUE KEY `usuario_razao_social` (`usuario_razao_social`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- USUÁRIO COMUM
INSERT INTO `usuario` (
  `usuario_id`,
  `usuario_tipo`,
  `usuario_cpf_cnpj`,
  `usuario_username`,
  `usuario_responsavel_nome`,
  `usuario_telefone`,
  `usuario_logradouro`,
  `usuario_cidade`,
  `usuario_is_prestador_servico`,
  `usuario_site`,
  `usuario_url_instagram`,
  `usuario_url_facebook`,
  `usuario_razao_social`,
  `usuario_senha`
) VALUES (
  '123e4567-e89b-12d3-a456-426614174000',
  'USUARIO',
  '12345678901',
  'maria.silva@gmail.com',
  'Maria da Silva',
  '21988887777',
  'Av. Central, 456',
  'Rio de Janeiro',
  b'0',
  NULL,
  NULL,
  NULL,
  NULL,
  '$2a$10$H9ZBhvZf8Ffq7JdFfW8r9oJqYh3QKcF98ujkDjKbWn5o1zDfUgXpOE' -- hash de senha fictício
);

-- ONG
INSERT INTO `usuario` (
  `usuario_id`,
  `usuario_tipo`,
  `usuario_cpf_cnpj`,
  `usuario_username`,
  `usuario_responsavel_nome`,
  `usuario_telefone`,
  `usuario_logradouro`,
  `usuario_cidade`,
  `usuario_is_prestador_servico`,
  `usuario_site`,
  `usuario_url_instagram`,
  `usuario_url_facebook`,
  `usuario_razao_social`,
  `usuario_senha`
) VALUES (
  '123e4567-e89b-12d3-a456-426614174001',
  'ONG',
  '12345678000199',
  'contato@amigosdosbichos.org',
  'Joana Silva',
  '11999999999',
  'Rua das Flores, 123',
  'São Paulo',
  b'1',
  'https://amigosdosbichos.org',
  'https://instagram.com/amigosdosbichos',
  'https://facebook.com/amigosdosbichos',
  'Associação Amigos dos Bichos',
  '$2a$10$7JdFfW8r9oJqYh3QKcF98ujkDjKbWn5o1zDfUgXpOE9ZBhvZf8Ffq' -- senha hash bcrypt
);

-- CLINICA
INSERT INTO `usuario` (
  `usuario_id`,
  `usuario_tipo`,
  `usuario_cpf_cnpj`,
  `usuario_username`,
  `usuario_responsavel_nome`,
  `usuario_telefone`,
  `usuario_logradouro`,
  `usuario_cidade`,
  `usuario_is_prestador_servico`,
  `usuario_site`,
  `usuario_url_instagram`,
  `usuario_url_facebook`,
  `usuario_razao_social`,
  `usuario_senha`
) VALUES (
  '123e4567-e89b-12d3-a456-426614174002',
  'CLINICA',
  '98765432000188',
  'contato@vetvida.com.br',
  'Dr. Ricardo Almeida',
  '3133332222',
  'Rua dos Veterinários, 89',
  'Belo Horizonte',
  b'1',
  'https://vetvida.com.br',
  'https://instagram.com/vetvida_clinica',
  NULL,
  'Clínica Veterinária VetVida LTDA',
  '$2a$10$XpOE9ZBhvZf8Ffq7JdFfW8r9oJqYh3QKcF98ujkDjKbWn5o1zDfUg' -- hash fictício
);
