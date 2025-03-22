INSERT INTO proprietario (nome, email, fone) VALUES ('Joao Silva', 'joao@silva.com', '11999887766');
INSERT INTO proprietario (nome, email, fone) VALUES ('Maria Souza', 'maria@souza.com', '21988776655');
INSERT INTO proprietario (nome, email, fone) VALUES ('Carlos Pereira', 'carlos@pereira.com', '31977665544');
INSERT INTO proprietario (nome, email, fone) VALUES ('Ana Lima', 'ana@lima.com', '41966554433');
INSERT INTO proprietario (nome, email, fone) VALUES ('Pedro Oliveira', 'pedro@oliveira.com', '51955443322');
INSERT INTO proprietario (nome, email, fone) VALUES ('Fernanda Costa', 'fernanda@costa.com', '61944332211');
INSERT INTO proprietario (nome, email, fone) VALUES ('Lucas Fernandes', 'lucas@fernandes.com', '71933221100');
INSERT INTO proprietario (nome, email, fone) VALUES ('Juliana Almeida', 'juliana@almeida.com', '81922110099');
INSERT INTO proprietario (nome, email, fone) VALUES ('Rafael Gomes', 'rafael@gomes.com', '91911009988');
INSERT INTO proprietario (nome, email, fone) VALUES ('Beatriz Ramos', 'beatriz@ramos.com', '11900998877');
INSERT INTO proprietario (nome, email, fone) VALUES ('Gabriel Martins', 'gabriel@martins.com', '21999887766');


INSERT INTO veiculo (proprietario_id, marca, modelo, placa, status, data_cadastro, data_apreensao)
VALUES (1, 'Chevrolet', 'Onix', 'MNO6P45', 'REGULAR', '2023-05-20 13:40:00', null);
INSERT INTO veiculo (proprietario_id, marca, modelo, placa, status, data_cadastro, data_apreensao)
VALUES (2, 'Volkswagen', 'Polo', 'ABC1D23', 'REGULAR', '2023-06-10 10:20:00', null);
INSERT INTO veiculo (proprietario_id, marca, modelo, placa, status, data_cadastro, data_apreensao)
VALUES (3, 'Ford', 'Ka', 'XYZ9Q87', 'APREENDIDO', '2023-04-15 08:45:00', '2023-04-16 14:30:00');
INSERT INTO veiculo (proprietario_id, marca, modelo, placa, status, data_cadastro, data_apreensao)
VALUES (4, 'Fiat', 'Toro', 'LMN3S56', 'REGULAR', '2023-07-01 16:00:00', null);
INSERT INTO veiculo (proprietario_id, marca, modelo, placa, status, data_cadastro, data_apreensao)
VALUES (5, 'Toyota', 'Corolla', 'GHJ5T89', 'APREENDIDO', '2023-03-25 11:15:00', '2023-03-26 09:50:00');


INSERT INTO autuacao (veiculo_id, descricao, valor_multa, data_ocorrencia)
VALUES (1, 'Estacionamento em local proibido', 195.23, '2023-04-10 09:30:00');
INSERT INTO autuacao (veiculo_id, descricao, valor_multa, data_ocorrencia)
VALUES (2, 'Excesso de velocidade em via urbana', 293.47, '2023-05-12 13:40:00');
INSERT INTO autuacao (veiculo_id, descricao, valor_multa, data_ocorrencia)
VALUES (3, 'Avanço de sinal vermelho', 293.47, '2023-06-18 15:20:00');
INSERT INTO autuacao (veiculo_id, descricao, valor_multa, data_ocorrencia)
VALUES (4, 'Condução sem cinto de segurança', 130.16, '2023-07-25 11:45:00');
