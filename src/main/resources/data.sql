/* CLIENTES */
INSERT INTO cliente (login, email, senha, contato) 
VALUES ('Matheus Dalvino', 'matheusdalvino@gmail.com', 'matheus123', '(00) 00000-0000');

INSERT INTO cliente (login, email, senha, contato) 
VALUES ('Maria Joana', 'maria@gmail.com', 'maria123', '(00) 00000-0000');

INSERT INTO cliente (login, email, senha, contato) 
VALUES ('Pedro Cardoso', 'pedro@gmail.com', 'pedro123', '(00) 00000-0000');

INSERT INTO cliente (login, email, senha, contato)
VALUES ('Júnior Pimentel', 'junior@gmail.com', 'junior123', '(00) 00000-0000');

INSERT INTO cliente (login, email, senha, contato) 
VALUES ('Larissa Bonetti', 'larissa.bonetti@gmail.com', 'larissa123', '(00) 00000-0000');

INSERT INTO cliente (login, email, senha, contato) 
VALUES ('Larissa Menezes', 'larissa.menezes@gmail.com', 'larissa123', '(00) 00000-0000');

INSERT INTO cliente (login, email, senha, contato) 
VALUES ('Alberto Cardoso', 'alberto@gmail.com', 'alberto123', '(00) 00000-0000');

/* CHATS */
INSERT INTO chat (nome, descricao) VALUES ('Grupo1', 'xxxx xxxxxxxxxxxx xxxxxxxxxxx xxxxxxxxxxxx.');
INSERT INTO chat (nome, descricao) VALUES ('Grupo2', 'xxxxxx xxxxxxxxxxx xxxxxxxxxxx xxxxxxxxxxx xxxxxx.');

/* CLIENTES DENTRO DE UM CHAT 
   -> um cliente pode participar de um ou mais chats;
   -> um chat não pode conter 2 clientes com o mesmo 
   login ("login" é a PK de cliente);*/
INSERT INTO chat_cliente (chat_id, login) VALUES (1, 'Matheus Dalvino'); -- CHAT 1 (4 PARTICIPANTES)
INSERT INTO chat_cliente (chat_id, login) VALUES (1, 'Maria Joana');
INSERT INTO chat_cliente (chat_id, login) VALUES (1, 'Pedro Cardoso');
INSERT INTO chat_cliente (chat_id, login) VALUES (1, 'Júnior Pimentel');

INSERT INTO chat_cliente (chat_id, login) VALUES (2, 'Matheus Dalvino'); -- CHAT 2 (5 PARTICIPANTES)
INSERT INTO chat_cliente (chat_id, login) VALUES (2, 'Maria Joana');
INSERT INTO chat_cliente (chat_id, login) VALUES (2, 'Larissa Bonetti');
INSERT INTO chat_cliente (chat_id, login) VALUES (2, 'Larissa Menezes');
INSERT INTO chat_cliente (chat_id, login) VALUES (2, 'Alberto Cardoso');

/* MENSAGEM 
   -> uma mensagem pertece a um cliente e está em um chat;
   -> clientes podem ter várias mensagens; */
INSERT INTO mensagem(date, mensagem, chat_id, login) VALUES 
('2022-03-29 17:48:52', 'Olá', 1, 'Matheus Dalvino'), 
('2022-03-29 20:13:44', 'Oi, Matheus', 1, 'Maria Joana'),
('2022-03-29 21:11:10',  'Tudo bem?', 1, 'Maria Joana');

INSERT INTO mensagem(date, mensagem, chat_id, login) VALUES 
('2022-03-29 17:11:51', 'Eai, blz?', 2, 'Larissa Menezes'), 
('2022-03-29 20:13:44', 'Eae, Larissa', 2, 'Alberto Cardoso'),
('2022-03-29 21:11:10',  'Já tá chegando?', 2, 'Alberto Cardoso');

/* EVENTOS */
INSERT INTO evento (data, descricao, imagem, nome) VALUES 
('2022-12-25 18:30:00', 'Sorteio de uma progressiva', null, 'Progressiva - Fim de Ano'),
('2022-12-25 13:00:00', 'Sorteio Aleatório Teste', null, 'Sorteio de teste');

/* EVENTO E CLIENTES
  -> Muitos eventos têm muitos clientes;
  -> login é a FK de cliente*/
INSERT INTO evento_cliente (evento_id, login) VALUES 
(1, 'Maria Joana'),
(1, 'Larissa Bonetti'),
(1, 'Larissa Menezes');