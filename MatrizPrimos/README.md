# Descrição

Esse código tem como objetivo calcular a quantidade de números primos contidos em uma matriz.

Para realizar o cálculo, a matriz é dividida em pedaços chamados de "Macroblocos" e cada thread pega pra si  um macrobloco por vez e contabiliza os primos que encontrar. Após encontrar a quantida de primos, a thread envia uma mensagem para o objeto Matriz que a originou e registra de forma sincronizada a quantidade de primos encontrada.
