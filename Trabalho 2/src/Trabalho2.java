import javax.swing.JOptionPane;

public class Trabalho2 {
    final int tamanhoDoDB = 100;
    Produto cadastro[] = new Produto[tamanhoDoDB];
    int lastId = 0;
    public static void main(String[] a) {
        Trabalho2 classe = new Trabalho2();
        final boolean loop = true;
        boolean estadoLoop = true;
        while (estadoLoop == loop){
            String[] escolha = { "Listar Produtos", "Cadastrar Produtos", "Quantidade de produtos"};
            String input = (String) JOptionPane.showInputDialog(null, "Operação","Qual operação deseja efetuar", JOptionPane.QUESTION_MESSAGE, null,  escolha, escolha[1]);
            if (input.equals("Listar Produtos")){
                String[] metodo = { "Id", "Preço"};
                String metodoEscolhido = (String) JOptionPane.showInputDialog(null, "Operação","Listar por: ", JOptionPane.QUESTION_MESSAGE, null,  metodo, metodo[1]);
                JOptionPane.showMessageDialog(null, classe.listar(classe.cadastro ,metodoEscolhido));
            } else if (input.equals("Cadastrar Produtos")){
                classe.cadastro();
            } else if(input.equals("Quantidade de produtos")){
                String mensagem = "";
                if(classe.lastId == 0){
                    mensagem = "Não ha produtos cadastrados";
                } else {
                    int quantidade  = classe.lastId;
                    mensagem = "Há " + quantidade + " cadastrado(s)";
                }
                JOptionPane.showMessageDialog(null, mensagem);
            }
            if (String.valueOf(JOptionPane.showConfirmDialog(null,"Deseja fazer mais alguma operação?", "", JOptionPane.YES_OPTION)).equals("1")){
                estadoLoop = false;
            }
        }
    }

    public void cadastro(){
        int controle = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantos produtos deseja cadastrar?"));
        for (int i = 0; i < controle; i++){
            Produto produto = new Produto();
            int numero = i + 1;
            produto.id = lastId++;
            produto.nome = JOptionPane.showInputDialog(null,"Qual o nome do Jogo " + numero  + "?");
            produto.preco = Float.valueOf(JOptionPane.showInputDialog(null, "Qual o valor do produto " + numero + "?"));
            cadastro[produto.id] = produto;
        }
    }

    public String listar(Produto[] lista, String metodo){
        String listagem = "";
        if(metodo.equals("Id")){
            for(int i = 0; i < lastId; i++){
                listagem = listagem + "ID - " + lista[i].id + ", Nome: " + lista[i].nome + ", Preço: " + lista[i].preco + " dinheiros\n";
            }
        } else if(metodo.equals("Preço")){
            Produto listaPreco[];
            listaPreco = listarPreco();

            for(int i = 0; i < lastId; i++){
                listagem = listagem + ", Nome: " + listaPreco[i].nome + ", Preço: " + listaPreco[i].preco + " dinheiros\n";
            }
        }
        return listagem;
    }

    public Produto[] listarPreco(){
        Produto novaListaCadastro[];
        novaListaCadastro = cadastro;
        float aux;
        for (int i = 0; i < lastId; i++){
            for(int j = 0; j < lastId; j++) {
                if(novaListaCadastro[j + 1] == null){
                    break;
                }
                if(novaListaCadastro[j].preco > novaListaCadastro[j + 1].preco ){
                    aux = novaListaCadastro[j].preco;
                    novaListaCadastro[j].preco = novaListaCadastro[j + 1].preco;
                    novaListaCadastro[j + 1].preco = aux;
                }
            }
        }
        for(int i = 0; i < lastId; i++){
            System.out.println(novaListaCadastro[i].preco);
        }
        return novaListaCadastro;
    }

    public static class Produto{
        public int id = 0;
        public String nome = "";
        public float preco = 0;
    }
}