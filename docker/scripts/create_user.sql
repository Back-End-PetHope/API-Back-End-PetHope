USE pethope_db;

CREATE TABLE IF NOT EXISTS usuario (
    usuario_id BINARY(16) PRIMARY KEY,
    usuario_email VARCHAR(155) NOT NULL UNIQUE,
    usuario_password VARCHAR(255) NOT NULL
);

INSERT INTO usuario (usuario_id, usuario_email, usuario_password) VALUES
    (UUID_TO_BIN(UUID()), 'admin@example.com', 'admin123'),
    (UUID_TO_BIN(UUID()), 'user@example.com', 'user123');
