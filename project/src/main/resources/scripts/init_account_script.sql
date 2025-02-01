CREATE TABLE clients(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100) NOT NULL,
    blocked_for BOOLEAN NOT NULL,
    blocked_whom VARCHAR(100) NOT NULL,
    create_at TIMESTAMP WITHOUT TIME ZONE
        DEFAULT CURRENT_TIMESTAMP NOT NULL
);
COMMENT ON TABLE clients IS 'Таблица клиентов';
ALTER TABLE clients ADD CONSTRAINT clients_id_pk PRIMARY KEY (id);


CREATE TABLE account(
    id BIGSERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL,
    account_type VARCHAR(19) NOT NULL,
    balance DECIMAL(19,2) NOT NULL,
    CONSTRAINT accounts_to_clients_fk
        FOREIGN KEY (client_id)
            REFERENCES clients(id)
)


-- Вставляем клиентов
    INSERT INTO clients (first_name, last_name, middle_name, blocked_for, blocked_whom)
VALUES
('Иван', 'Иванов', 'Иванович', false, 'не заблокирован', DEFAULT),
('Ольга', 'Петрова', 'Сергеевна', true, 'просрочка платежа', DEFAULT),
('Алексей', 'Сидоров', 'Викторович', false, 'не заблокирован', DEFAULT)
RETURNING id;  -- Возвращаем сгенерированные ID

-- Вставляем счета (предположим, что client_id 1, 2, 3 существуют)
INSERT INTO account (client_id, account_type, balance)
VALUES
    (1, 'ДЕБЕТОВЫЙ', 150000.50),
    (1, 'НАКОПИТЕЛЬНЫЙ', 50000.00),
    (2, 'КРЕДИТНЫЙ', -25000.75),
    (3, 'ИПОТЕЧНЫЙ', 1000000.00);