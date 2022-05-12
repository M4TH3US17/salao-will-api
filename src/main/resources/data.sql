/* EMAIL DOS CLIENTES */
INSERT INTO email_clientes(email, confirmation_code, confirmed) VALUES 
('matheusdalvino50@gmail.com', 0000, false), 
('maria@gmail.com', 0000, false), 
('pedro@gmail.com', 0000, false), 
('junior@gmail.com', 0000, false), 
('larissa.bonetti@gmail.com', 0000, false), 
('larissa.menezes@gmail.com', 0000, false),
('alberto@gmail.com', 0000, false);
/* CLIENTES */
INSERT INTO cliente (login, email_cliente, senha, contato) 
VALUES ('Matheus Dalvino', 'matheusdalvino50@gmail.com', '$2a$10$Uw95mqRuMr51EDi3N16mVOoumCgQHWgMnO/jKeLK807xJeiP72QFO', '(00) 00000-0000');

INSERT INTO cliente (login, email_cliente, senha, contato) 
VALUES ('Maria Joana', 'maria@gmail.com', '$2a$10$VO4XSkId/sN3iVqew7L83.fRAKJIDk5naIEtRG7GDJTcUbKYKvONe', '(00) 00000-0000');

INSERT INTO cliente (login, email_cliente, senha, contato) 
VALUES ('Pedro Cardoso', 'pedro@gmail.com', '$2a$10$xkZjIiOkIkSAGq6xPimH3el0vF9NqhvB1JzZ6Du5ZauKGw3y7gfNW', '(00) 00000-0000');

INSERT INTO cliente (login, email_cliente, senha, contato)
VALUES ('Júnior Pimentel', 'junior@gmail.com', '$2a$10$ix9ZxkkxZXhFHVb4QoZUPOO8/GffUi9wxjVGzBVwyVp3d35eGqyzy', '(00) 00000-0000');

INSERT INTO cliente (login, email_cliente, senha, contato) 
VALUES ('Larissa Bonetti', 'larissa.bonetti@gmail.com', '$2a$10$DDlr13I2yqeTqnu7rtGpZeR0Bv0WhCXKYDM93X2SQieh19gDR3Ia6', '(00) 00000-0000');

INSERT INTO cliente (login, email_cliente, senha, contato) 
VALUES ('Larissa Menezes', 'larissa.menezes@gmail.com', '$2a$10$iG0prWHTLZ3DhMx30HVTi.rMS05x2c6KPDedEZq5VM32CnG8L/wma', '(00) 00000-0000');

INSERT INTO cliente (login, email_cliente, senha, contato) 
VALUES ('Alberto Cardoso', 'alberto@gmail.com', '$2a$10$QBZy7EPF5LZlD3XMdfDGNe9jmYDbzxOAT0mI469BH9yU2mwsx7vW6', '(00) 00000-0000');


INSERT INTO roles (nome) VALUES
('ROLE_USER'),
('ROLE_ADMIN');

/*ROLE_CLIENTE*/
INSERT INTO cliente_roles (cliente_login, roles_id) VALUES 
('Matheus Dalvino', 2),
('Matheus Dalvino', 1),
('Maria Joana', 1),
('Pedro Cardoso', 1),
('Júnior Pimentel', 1),
('Larissa Bonetti', 1),
('Larissa Menezes', 1),
('Alberto Cardoso', 1);

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

/* CATEGORIA (de serviço)
INSERT INTO categoria (nome) VALUES 
('Manicure'), 
('Depilação'), 
('Cabelo'), 
('Sobrancelhas');*/

/* SERVIÇOS 
   -> Cada serviço pertence a uma categoria
   -> Serviço aponta para categoria com @ManyToOne*/
INSERT INTO servico (nome, descricao, preco, categoria, imagem) VALUES 
('Pé e Mão', null, 50.00, 'MANICURE', null), 
('Mão', null, 30.00, 'MANICURE', null), 
('Pé', null, 30.00, 'MANICURE', null),
('Francesinha', null, 20.00, 'MANICURE', null),
('Desenho', null, 50.00, 'MANICURE', null),
('Unha de Silicone', null, 70.00, 'MANICURE', null), 
('Unha Postiça', null, 50.00, 'MANICURE', null),
('Unha de Gel', null, 70.00, 'MANICURE', null);

INSERT INTO servico (nome, descricao, preco, categoria, imagem) 
VALUES 
('Virilha', null, 70.00, 'DEPILACAO', null),
('Rosto', null, 50.00, 'DEPILACAO', null),
('Sobrancelhas', null, 20.00, 'DEPILACAO', null),
('Axilas', null, 30.00, 'DEPILACAO', null),
('Perna', null, 20.00, 'DEPILACAO', null);

INSERT INTO servico (nome, descricao, preco, categoria, imagem) 
VALUES 
('Mechas', null, 40.00, 'CABELO', null), 
('Relaxamento', null, 50.00, 'CABELO', null), 
('Coloração', null, 60.00, 'CABELO', null), 
('Baby Lise', null, 50.00, 'CABELO', null), 
('Aplicação', null, 40.00, 'CABELO', null),
('Escova', null, 50.00, 'CABELO', null);

/* AGENDAMENTOS 
   -> Um agendamento pode ter muitos serviços (e muitos serviços podem estar em muitos agendamentos)
   -> um agendamento pertence somente um cliente 
   -> agendamento @ManyToMany apontando para servico e também @OneToOne apontando para cliente
*/
INSERT INTO agendamento(cliente_login, data) VALUES 
('Matheus Dalvino', '2022-03-17 14:35:00'),
('Larissa Menezes', '2022-03-17 15:20:00'),
('Maria Joana', '2022-03-18 13:00:00'),
('Pedro Cardoso', '2022-04-21 10:00:00');

INSERT INTO AGENDAMENTO_SERVICO(agendamento_id, servico_id) VALUES 
(1, 1),
(1, 10), 
(2, 11),
(3, 18),
(3, 7),
(4, 1);