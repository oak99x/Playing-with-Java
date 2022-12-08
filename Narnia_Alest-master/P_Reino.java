public class P_Reino {

    private Integer pessoa;

    public P_Reino() {
        this.pessoa = null;
    }

    public Tipo getPessoa(Integer p) {

        this.pessoa = p;
        Tipo aux = null;

        switch (pessoa) {
            case 0:
                aux = Tipo.Rei;
                break;
            case 1:
                aux = Tipo.Rainha;
                break;
            case 2:
                aux = Tipo.Principe;
                break;
            case 3:
                aux = Tipo.Princesa;
                break;
            case 4:
                aux = Tipo.Bispo;
                break;
            case 5:
                aux = Tipo.Sacerdote;
                break;
            case 6:
                aux = Tipo.Duque;
                break;
            case 7:
                aux = Tipo.Assasino;
                break;
            case 8:
                aux = Tipo.Visconte;
                break;
            case 9:
                aux = Tipo.Cavaleiro;
                break;
            case 10:
                aux = Tipo.Mago;
                break;
            case 11:
                aux = Tipo.Healer;
                break;
            case 12:
                aux = Tipo.Arqueiro;
                break;
            case 13:
                aux = Tipo.Vassalo;
                break;
            case 14:
                aux = Tipo.Monge;
                break;
            case 15:
                aux = Tipo.Cidadao;
                break;
            case 16:
                aux = Tipo.Dragao;
                break;
            case 17:
                aux = Tipo.Centauro;
                break;
            case 18:
                aux = Tipo.Minotauro;
                break;
            case 19:
                aux = Tipo.Ninfa;
                break;
            case 20:
                aux = Tipo.Fada;
                break;

            default:
                aux = Tipo.Homem_livre;
                break;
        }

        return aux;
    }
}
