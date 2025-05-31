USE pethope_db;

CREATE TABLE `pet` (
  `pet_id` int NOT NULL AUTO_INCREMENT,
  `pet_nome` varchar(50) NOT NULL,
  `pet_especie` ENUM('CACHORRO', 'GATO', 'COELHO', 'PASSARO') NOT NULL,
  `pet_raca` ENUM(
    'PASTOR_ALEMAO',
    'LABRADOR',
    'CAO_SRD',
    'POODLE',
    'GOLDEN_RETRIEVER',
    'ROTTWEILER',
    'PERSA',
    'SIAMES',
    'ANGORA',
    'GATO_SRD',
    'CALOPSITA',
    'PERIQUITO'
  ) NOT NULL,
  `pet_idade` int DEFAULT NULL,
  `pet_temperamento` enum('TRANQUILO','AGITADO','BRINCALHAO','TIMIDO','CURIOSO', 'PROTETOR', 'CARINHOSO', 'INDEPENDENTE', 'SOCIAL', 'OBEDIENTE', 'ARREDIO') DEFAULT NULL,
  `pet_ativo` tinyint(1) DEFAULT '1',
  `pet_status` tinyint(1) DEFAULT '0',
  `usuario_id` varchar(255) NOT NULL,
  `pet_descricao` varchar(255) NOT NULL,
  `pet_sexo` enum('M','F') NOT NULL,
  PRIMARY KEY (`pet_id`),
  KEY `fk_pet_usuario` (`usuario_id`),
  CONSTRAINT `fk_pet_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO pet (pet_nome, pet_especie, pet_raca, pet_idade, pet_temperamento, pet_ativo, pet_status, usuario_id, pet_descricao, pet_sexo)
VALUES ('Rex', 'CACHORRO', 'LABRADOR', 3, 'TRANQUILO', 1, 0, '123e4567-e89b-12d3-a456-426614174000', 'Um cachorro muito feliz', 'M');

INSERT INTO pet (pet_nome, pet_especie, pet_raca, pet_idade, pet_temperamento, pet_ativo, pet_status, usuario_id, pet_descricao, pet_sexo)
VALUES ('Miau', 'GATO', 'SIAMES', 2, 'AGITADO', 1, 0, '123e4567-e89b-12d3-a456-426614174001', 'Um gato muito brincalhão', 'F');

INSERT INTO pet (pet_nome, pet_especie, pet_raca, pet_idade, pet_temperamento, pet_ativo, pet_status, usuario_id, pet_descricao, pet_sexo)
VALUES ('Luna', 'PASSARO', 'PERIQUITO', 1, 'CARINHOSO', 1, 0, '123e4567-e89b-12d3-a456-426614174002', 'Um pássaro muito fofinho', 'F');
