# Passos
1. Inclua um arquivo .gitignore
2. Inclua a pasta target no .gitignore
3. Rode o comando `mvn package`
4. Rode o comando `java -jar target/endorsed/webapp-runner.jar --port $PORT target/pareaqui-0.0.1-SNAPSHOT.war`
    * Substituindo o `$PORT` pela porta que você desejar usar....
    * Por exemplo: se for usar a porta 3333 para testar basta acessar http://localhost:3333

# Adição no projeto
* Foi adicionado o servlet Contador
    * Esse servlet testa o conceito de que por padrão cada servlet tem apenas 1 instância e que a mesma é compartilhada por todas as threads.
* Ao invés de informar apenas um alô agora o servlet dá bom dia, boa tarde ou boa noite no idioma selecionado de acordo com a hora atual.

# Fonte interessante para estudo
* https://www.caelum.com.br/apostila-java-web/servlets


***

Endereco
-rua
-numero
-complemento
-bairro
-cidade
-estado
-pais

Estacionamento

Usuario
-id
-nome
-email
-senha

UsuarioEstacionamentoDono
-Usuario
-Estacionamento

UsuarioEstacionamentoFuncionario
-Usuario
-Estacionamento

UsuarioFiscalRua
-Usuario
-Endereco

VagaTipo
-id
-tipo
-img

VagaTamanho
-id
-tamanho

Vaga
-preco
-Tipo
-Tamanho
-identificacao

VagaRuas
-Vaga
-temFlanelinha
-Endereco

VagaAcidentes
-Vaga
-Usuario reportador
-descricao
-momento

VagaAgendamento
-Vaga
-momento

VagaOcupacao
-Vaga
-Usuario
-momentoEntrada
-momentoSaida

DenunciaFlanelinha
-Vaga