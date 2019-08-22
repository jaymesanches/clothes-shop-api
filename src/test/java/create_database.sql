INSERT INTO public.usuario(id, nome, email, senha)	VALUES (1, 'Isabela', 'isa@bela.com.br', '12345');
INSERT INTO public.usuario(id, nome, email, senha)	VALUES (2, 'Viviane', 'vivi@bela.com.br', '12345');	
INSERT INTO public.usuario(id, nome, email, senha)	VALUES (3, 'Jayme', 'jayme@email.com', '12345');	

INSERT INTO public.grupo(id, nome) VALUES (1, 'Grupo 1');
INSERT INTO public.grupo(id, nome) VALUES (2, 'Grupos 2');

INSERT INTO public.grupo_usuario(id_grupo, id_ususario)	VALUES (1, 1);
INSERT INTO public.grupo_usuario(id_grupo, id_ususario)	VALUES (2, 2);

INSERT INTO public.produto(
	id, codigo, cor, descricao, detalhes, margem_lucro_padrao, valor_custo, tamanho_P)
	VALUES (1, 123, 'Verde', 'Produto1', 'Detahes', 10, 10, 10);
		
INSERT INTO public.produto(
	id, codigo, cor, descricao, detalhes, margem_lucro_padrao, valor_custo, tamanho_G)
	VALUES (2, 321, 'Branco', 'Produto2', 'Detalhes2', 10, 10, 10);
	
INSERT INTO public.cliente(id, nome, email, documento)	VALUES (1, 'Viviane', 'vivi@bela.com.br', '12345789000');

	
