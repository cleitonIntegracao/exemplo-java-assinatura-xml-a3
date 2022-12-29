# NSSuiteJava

 Utilizando a NS API, este exemplo - criado em Java - possui funcionalidades para consumir documentos fiscais eletrônicos em geral, como por exemplo: 
+ NFe; 
+ CTe; 
+ NFCe;
+ MDFe;
+ BPe;
+ NF3e

Simplificando todos os projetos utilizados em um único exemplo, deixando mais pratica e facil a integração com a NS API.

## Primeiros passos:

### Integrando ao sistema:

Para utilizar as funções de comunicação com a API, você precisa realizar os seguintes passos:

1. Extraia o conteúdo da pasta compactada que você baixou;
2. Copie para sua aplicação a pasta src, na qual contem todos as classes que serão utilizadas;
3. Abra o seu projeto e importe a pasta copiada.
4. A aplicação utiliza as bibliotecas **jersey-client** e **jackson-databind** para realizar a comunicação com a API e fazer a manipulação de dados JSON, respectivamente. Ative as duas referencias em seu projeto, como mostrado abaixo:

**Pronto!** Agora, você já pode consumir a NS Suite API através do seu sistema. Todas as funcionalidades de comunicação foram implementadas na classe **NSSuite.java**.

------

## Emissão Sincrona:

### Realizando uma Emissão Sincrona:

Para realizar uma emissão completa de uma NFe (utilizada para exemplo), você poderá utilizar a função emitirNFeSincrono da classe NSSuite. Veja abaixo sobre os parâmetros necessários, e um exemplo de chamada do método.

##### Parâmetros:

**ATENÇÃO:** o **token** também é um parâmetro necessário e você deve, primeiramente, defini-lo na classe **NSSuite.java**, como pode ver abaixo:


Parametros     | Descrição
:-------------:|:-----------
conteudo       | Conteúdo de emissão do documento.
tpConteudo     | Tipo de conteúdo que está sendo enviado. Valores possíveis: json, xml, txt
CNPJ           | CNPJ do emitente do documento.
tpDown         | Tipo de arquivos a serem baixados.Valores possíveis: <ul> <li>**X** - XML</li> <li>**J** - JSON</li> <li>**P** - PDF</li> <li>**XP** - XML e PDF</li> <li>**JP** - JSON e PDF</li> </ul> 
tpAmb          | Ambiente onde foi autorizado o documento.Valores possíveis:<ul> <li>1 - produção</li> <li>2 - homologação</li> </ul>
caminho        | Caminho onde devem ser salvos os documentos baixados.
exibeNaTela    | Se for baixado, exibir o PDF na tela após a autorização.Valores possíveis: <ul> <li>**True** - será exibido</li> <li>**False** - não será exibido</li> </ul> 

##### Exemplo de chamada:

Após ter todos os parâmetros listados acima, você deverá fazer a chamada da função. Veja o código de exemplo abaixo:
           
    String retorno = NSSuite.emitirNFeSincrono(conteudo, tpConteudo, cnpjEmit, tpDown, tpAmb, caminho, exibeNaTela)
    
    JOptionPane.showMessageDialog(null, retorno);

A função emitirNFeSincrono fará o envio, a consulta e download do documento, utilizando as funções emitirDocumento, consultarStatusProcessamento e downloadDocumentoESalvar, presentes na classe **NSSuite.java**. Por isso, o retorno será um JSON com os principais campos retornados pelos métodos citados anteriormente. No exemplo abaixo, veja como tratar o retorno da função emitirNFeSincrono:

##### Exemplo de tratamento de retorno:

O JSON retornado pelo método terá os seguintes campos: statusEnvio, statusConsulta, statusDownload, cStat, chNFe, nProt, motivo, nsNRec, erros. Veja o exemplo abaixo:

    {
        "statusEnvio": "200",
        "statusConsulta": "200",
        "statusDownload": "200",
        "cStat": "100",
        "chNFe": "43181007364617000135550000000119741004621864",
        "nProt": "143180007036833",
        "motivo": "Autorizado o uso da NF-e",
        "nsNRec": "313022",
        "erros": ""
    }
      
Confira um código para tratamento do retorno, no qual pegará as informações dispostas no JSON de Retorno disponibilizado:


    ObjectMapper objectMapper = new ObjectMapper();
    String retorno = NSSuite.emitirNFeSincrono(conteudo,"json", "07364617000135", "XP", "2", "./Notas", true);
    JsonNode respostaJSON = objectMapper.readTree(retorno);
    String statusEnvio = respostaJSON.get("statusEnvio").asText();
    String statusConsulta = respostaJSON.get("statusConsulta").asText();
    String statusDownload = respostaJSON.get("statusDownload").asText();
    String cStat = respostaJSON.get("cStat").asText();
    String chNFe = respostaJSON.get("chNFe").asText();
    String nProt = respostaJSON.get("nProt").asText();
    String motivo = respostaJSON.get("motivo").asText();
    String nsNRec = respostaJSON.get("nsNRec").asText();
    ArrayList<String> erros = objectMapper.readValue(respostaJSON.get("erros").toString(), new TypeReference<ArrayList<String>>(){});
    if (statusEnvio.equals("200") || statusEnvio.equals("-6")){
        if (statusConsulta.equals("200")){
            if (cStat.equals("100")){
                JOptionPane.showMessageDialog(null, motivo);
                if (!statusDownload.equals("200")){
                    JOptionPane.showMessageDialog(null, "Erro Downlaod");
                }
            }else{
                JOptionPane.showMessageDialog(null, motivo);
            }
        }else{
            JOptionPane.showMessageDialog(null, motivo + "\n" + erros.toString());
        }
    }else{
        JOptionPane.showMessageDialog(null, motivo + "\n" + erros.toString());
    }      

-----

## Cancelamento de Documento:

### Realizando um Cancelamento:

tilizando NFe como exemplo para o cancelamento deve-se ter em mente que você deverá usar a função cancelarDocumentoESalvar da classe NSSuite. Veja abaixo sobre os parâmetros necessários, e um exemplo de chamada do método.

##### Parâmetros:

**ATENÇÃO:** o **token** também é um parâmetro necessário e você deve, primeiramente, defini-lo na classe **NSSuite.java**, como pode ver abaixo:

Parametros     | Descrição
:-------------:|:-----------
**modelo**            | Conteúdo de emissão do documento.<ul> <li>"63" (BPe);</li> <li>"57" (CTe);</li> <li>"67" (CTeOS);</li> <li>"58" (MDFe);</li> <li>"65" (NFCe);</li> <li>"55" (NFe);</li> </ul>
**CancelarReq**       | JSON contendo as informações de uma requisição de cancelamento de documento
**DownloadEventoReq** | JSON contendo as informações de uma requisição de Download de Evento
**caminho**           | Caminho onde devem ser salvos os documentos baixados.
**chave**             | Ambiente onde foi autorizado o documento.Valores possíveis:<ul> <li>**1** - produção</li> <li>**2** - homologação</li> </ul> 
**exibeNaTela**       | Se for baixado, exibir o PDF na tela após a autorização.Valores possíveis: <ul> <li>**True** - será exibido</li> <li>**False** - não será exibido</li> </ul> 

##### Exemplo de chamada:

Após ter todos os parâmetros listados acima, você deverá fazer a chamada da função. Veja o código de exemplo abaixo:

    CancelarReqNFe cancelarReqNFe = new CancelarReqNFe();
    cancelarReqNFe.chNFe = "43190307364617000135550000000130621004621863";
    cancelarReqNFe.dhEvento="2019-03-15T15:37:14-03:00";
    cancelarReqNFe.nProt = "143190000501923";
    cancelarReqNFe.tpAmb = "2";
    cancelarReqNFe.xJust = "TESTE DE CANCELAMENTO INTEGRAÇÃO NS";
     
    DownloadEventoReqNFe down = new DownloadEventoReqNFe();
    down.tpDown = "XP";
    down.tpEvento= "CANC";
    down.nSeqEvento = "1";
    down.tpAmb = "2";
    down.chNFe = "43190307364617000135550000000130621004621863";
    try {
        String Retorno = NSSuite.cancelarDocumentoESalvar("55", cancelarReqNFe, down, "./Notas", "43190307364617000135550000000130621004621863", true);
    } catch (Exception e1) {
        e1.printStackTrace();
    }
    
A função **cancelarDocumentoESalvar** fará o cancelamento de qualquer documento que possa ser cancelado e fazendo o download do evento feito, neste caso hipotético, uma NFe, utilizando as funções cancelarDocumento e downloadEventoESalvar, presentes na classe **NSSuite.java**. Dessa forma, o retorno será um JSON com os principais campos retornados pelos métodos citados anteriormente. No exemplo abaixo, veja o retorno da nossa API em um cancelamento:

##### Exemplo de retorno de cancelamento:

    {
      "status": 135,
      "motivo": "NF-e cancelada com sucesso",
      "retEvento": {
        "cStat": 135,
        "xMotivo": "Evento registrado e vinculado a NF-e",
        "chNFe": "43190307364617000135550000000130621004621863",
        "dhRegEvento": "2019-03-15T15:37:14-03:00",
        "nProt": "143190000501923"
      }
    }

-----

## Carta de Correção(CC):

### Realizando uma Correção de Documento:

Utilizando NFe como exemplo para a criação de uma carta de correção, deve-se ter em mente que você deverá usar a função corrigirDocumentoESalvar da classe NSSuite. Veja abaixo sobre os parâmetros necessários, e um exemplo de chamada do método.

##### Parâmetros:

**ATENÇÃO:** o **token** também é um parâmetro necessário e você deve, primeiramente, defini-lo na classe **NSSuite.java**, como pode ver abaixo:


Parametros     | Descrição
:-------------:|:-----------
**modelo**            | Conteúdo de emissão do documento.<ul> <li>"63" (BPe);</li> <li>"57" (CTe);</li> <li>"67" (CTeOS);</li> <li>"58" (MDFe);</li> <li>"65" (NFCe);</li> <li>"55" (NFe);</li> </ul>
**CorrigirReq**       | JSON contendo as informações de uma requisição de carta de correção
**DownloadEventoReq** | JSON contendo as informações de uma requisição de Download de Evento
**caminho**           | Caminho onde devem ser salvos os documentos baixados.
**chave**             | Ambiente onde foi autorizado o documento.Valores possíveis:<ul> <li>**1** - produção</li> <li>**2** - homologação</li> </ul> 
**nSeqEvento**        | Número sequencial do evento
**exibeNaTela**       | Se for baixado, exibir o PDF na tela após a autorização.Valores possíveis: <ul> <li>**True** - será exibido</li> <li>**False** - não será exibido</li> </ul> 

##### Exemplo de chamada:

Após ter todos os parâmetros listados acima, você deverá fazer a chamada da função. Veja o código de exemplo abaixo:

    CorrigirReqNFe cceTeste  = new CorrigirReqNFe();
    cceTeste.chNFe = "43190207364617000135550000000129281004621862";
    cceTeste.dhEvento = "2019-03-06T12:00:00-03:00";
    cceTeste.tpAmb = "2";
    cceTeste.nSeqEvento = "1";
    cceTeste.xCorrecao = "CC-e realizada para teste de integração";
     
    DownloadEventoReqNFe downTeste = new DownloadEventoReqNFe();
    downTeste.chNFe = "43190207364617000135550000000129281004621862";
    downTeste.tpAmb = "2";
    downTeste.nSeqEvento = "1";
    downTeste.tpDown = "XP";
    downTeste.tpEvento = "CCE";
     
    try {
        String retorno = NSSuite.corrigirDocumentoESalvar("55", cceTeste, downTeste, "./Notas", "43190207364617000135550000000129281004621862", "1", true);
    } catch (Exception e1) {
        e1.printStackTrace();
    }
    
A função corrigirDocumentoESalvar irá vincular um CCe (carta de correção) ao projeto selecionado, neste caso hipotético, à uma NFe, utilizando as funções corrigirDocumento e downloadEventoESalvar, presentes na classe NSSuite.java. Dessa forma, o retorno será um JSON com os principais campos retornados pelos métodos citados anteriormente. No exemplo abaixo, veja o retorno da nossa API em uma CCe:

##### Exempo de retorno de correção de documento:

    {
      "status": 200,
      "motivo": "CC-e vinculada com sucesso",
      "retEvento": {
        "cStat": 135,
        "xMotivo": "Evento registrado e vinculado a NF-e",
        "chNFe": "43190207364617000135550000000129281004621862",
        "dhRegEvento": "2019-03-06T12:00:50-03:00",
        "nProt": "143190000330112"
      }
    }

![Ns](https://nstecnologia.com.br/blog/wp-content/uploads/2018/11/ns%C2%B4tecnologia.png) | Obrigado pela atenção!
