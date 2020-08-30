package br.com.rruizdasilva;

import java.io.File;

public class DadosDeTeste {

  public static String obterJsonComDadosVazios(){
    return "{\n" + "    \"matriz\": \"consultaPessoaDefault\",\n" + "    \"parametros\": {\n" +
        "        \"cpf_data_de_nascimento\": \"\",\n" + "        \"cpf_nome\": \"\",\n" +
        "        \"cpf_numero\": \"\"\n" + "    }\n" + "}";
  }

  public static String obterJsonComRegraDeDataDiferente(){
    return "{\n" + "    \"matriz\": \"consultaPessoaDefault\",\n" + "    \"parametros\": {\n" +
        "        \"cpf_data_de_nascimento\": \"28/09/1987\",\n" + "        \"cpf_nome\": \"Gabriel Oliveira\",\n" +
        "        \"cpf_numero\": \"07614917677\"\n" + "    }\n" + "}";
  }

  public static String obterJsonComRegraDeNomeDiferente(){
    return "{\n" + "    \"matriz\": \"consultaPessoaDefault\",\n" + "    \"parametros\": {\n" +
        "        \"cpf_data_de_nascimento\": \"25/05/1988\",\n" + "        \"cpf_nome\": \"Gabriela Oliveira\",\n" +
        "        \"cpf_numero\": \"07614917677\"\n" + "    }\n" + "}";
  }

  public static String obterJsonComOsSeguintesDados(String data, String nome, String cpf){
    return "{\n" + "    \"matriz\": \"consultaPessoaDefault\",\n" + "    \"parametros\": {\n" +
        "        \"cpf_data_de_nascimento\": \"" + data + "\",\n" + "        \"cpf_nome\": \"" + nome + "\",\n" +
        "        \"cpf_numero\": \"" + cpf + "\"\n" + "    }\n" + "}";
  }

  public static String obterToken(){
    return "1a5fc183-665e-404b-8dec-51e69aa02766";
  }

  public static String obterEnderecoAPI(){
    return "https://api-v2.idwall.co/relatorios";
  }

  public static String obterCaminhoArquivoDadosExternos(){
    return "src" + File.separator + "test" + File.separator + "resources" + File.separator +
        "properties/dadosexternos.properties";
  }
}
