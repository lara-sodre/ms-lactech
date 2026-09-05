-- http://localhost:8080/swagger-ui/index.html

INSERT INTO PROFISSIONAL (nome, email, telefone, cargo) VALUES ('Dra. Ana Souza', 'ana.souza@lactare.com.br', '11999990001', 'Pediatra');
INSERT INTO PROFISSIONAL (nome, email, telefone, cargo) VALUES ('Dr. Carlos Lima', 'carlos.lima@lactare.com.br', '11999990002', 'Enfermeiro');
INSERT INTO PROFISSIONAL (nome, email, telefone, cargo) VALUES ('Dra. Bianca Rocha', 'bianca.rocha@lactare.com.br', '11999990003', 'Psicologa');

INSERT INTO APOIO (nome, usuario, senha, data_nascimento, email, endereco, rg, cpf, telefone, cidade, bairro, cep) VALUES ('Familia Oliveira', 'familia_oliveira', 'senha123', '1990-05-10', 'familia.oliveira@email.com', 'Rua das Palmeiras, 55', '123456789', '98765432100', '11988887777', 'Sao Paulo', 'Vila Nova', '05100000');
INSERT INTO APOIO (nome, usuario, senha, data_nascimento, email, endereco, rg, cpf, telefone, cidade, bairro, cep) VALUES ('Familia Santos', 'familia_santos', 'senha123', '1988-11-22', 'familia.santos@email.com', 'Av. Central, 900', '987654321', '11122233344', '11977776666', 'São Paulo', 'Jardim das Flores', '05200000');

INSERT INTO DOADORA (cpf, nome, data_nascimento, telefone, email, endereco, bairro, cidade, cep, usuario, senha) VALUES ('12345678900', 'Maria Aparecida Silva', '1992-03-12', '11998765432', 'maria.silva@email.com', 'Rua das Flores, 142', 'Centro', 'Sao Paulo', '05000000', 'nutriz_maria', 'senha123');
INSERT INTO DOADORA (cpf, nome, data_nascimento, telefone, email, endereco, bairro, cidade, cep, usuario, senha) VALUES ('98765432199', 'Fernanda Costa', '1995-07-25', '11991234567', 'fernanda.costa@email.com', 'Rua dos Ipes, 320', 'Vila Mariana', 'Sao Paulo', '04100000', 'nutriz_fernanda', 'senha123');

INSERT INTO INFORMACAO (idade_bebe, amamentando, uso_medicamento, metodo_coleta, alergia, doenca_cronicas, observacoes, fuma, alcool, consentimento, DOADORA_ID_DOADORA) VALUES ('4 meses', 'SIM', 'NAO', 'BE', 'Nenhuma', 'Nenhuma', 'Sem observacoes', 'NAO', 'NAO', 'SIM', 1);
INSERT INTO INFORMACAO (idade_bebe, amamentando, uso_medicamento, metodo_coleta, alergia, doenca_cronicas, observacoes, fuma, alcool, consentimento, DOADORA_ID_DOADORA) VALUES ('2 meses', 'SIM', 'NAO', 'MA', 'Nenhuma', 'Nenhuma', 'Sem observacoes', 'NAO', 'NAO', 'SIM', 2);

INSERT INTO COLETA (data, volume, status, observacoes, DOADORA_ID_DOADORA) VALUES ('2026-08-20', 180, 'AGENDADA', 'Melhor horario: manha', 1);
INSERT INTO COLETA (data, volume, status, observacoes, DOADORA_ID_DOADORA) VALUES ('2026-08-13', 210, 'RECEBIDA', 'Sem observacoes', 1);
INSERT INTO COLETA (data, volume, status, observacoes, DOADORA_ID_DOADORA) VALUES ('2026-08-18', 150, 'AGENDADA', 'Sem observacoes', 2);

INSERT INTO CONSULTA (data, horario, motivo, informacoes_adicionais, status, DOADORA_ID_DOADORA, APOIO_ID_APOIO, PROFISSIONAL_ID_PROFISSIONAL) VALUES ('2026-08-28', '09:00', 'Triagem para se tornar doadora', 'Sem observacoes', 'AGENDADA', 1, 1, 1);
INSERT INTO CONSULTA (data, horario, motivo, informacoes_adicionais, status, DOADORA_ID_DOADORA, APOIO_ID_APOIO, PROFISSIONAL_ID_PROFISSIONAL) VALUES ('2026-08-25', '14:15', 'Acolhimento emocional', 'Sem observacoes', 'REALIZADA', 2, 2, 3);

INSERT INTO AVALIACAO (data, nota, comentario, DOADORA_ID_DOADORA, APOIO_ID_APOIO, PROFISSIONAL_ID_PROFISSIONAL, CONSULTA_ID_CONSULTA) VALUES ('2026-08-25', 5, 'Atendimento excelente! Equipe muito atenciosa.', 2, 2, 3, 2);
