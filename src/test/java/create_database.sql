INSERT INTO public.usuario(id, nome, email)	VALUES (1, 'Isabela', 'isa@bela.com.br');
INSERT INTO public.usuario(id, nome, email)	VALUES (2, 'Viviane', 'vivi@bela.com.br');	

INSERT INTO public.grupo(id, nome) VALUES (1, 'Grupo 1');
INSERT INTO public.grupo(id, nome) VALUES (2, 'Grupos 2');

INSERT INTO public.grupo_usuario(id_grupo, id_ususario)	VALUES (1, 1);
INSERT INTO public.grupo_usuario(id_grupo, id_ususario)	VALUES (2, 2);

INSERT INTO public.produto(
	id, codigo, cor, descricao, detalhes, margem_lucro_padrao, valor_custo)
	VALUES (1, 123, 'Verde', 'Produto1', 'Detahes', 10, 10);
		
INSERT INTO public.produto(
	id, codigo, cor, descricao, detalhes, margem_lucro_padrao, valor_custo)
	VALUES (2, 321, 'Branco', 'Produto2', 'Detalhes2', 10, 10);
	
INSERT INTO public.estoque(
	id, id_produto, tamanho_pp, tamanho_p, tamanho_m, tamanho_g, tamanho_gg, tamanho_xxg, tamanho_u)
	VALUES (1, 1, 1, 2, 3, 4, 5, 6, 7);	
	
INSERT INTO public.estoque(
	id, id_produto, tamanho_pp, tamanho_p, tamanho_m, tamanho_g, tamanho_gg, tamanho_xxg, tamanho_u)
	VALUES (2, 2, 1, 2, 3, 4, 5, 6, 7);	
		
