package br.univel.meustestes;

import java.math.BigDecimal;
import java.util.List;

import br.univel.minhaarvore.UniArvore;
import br.univel.minhaarvore.UniArvoreImpl;
import br.univel.minhaarvore.UniNode;
import br.univel.minhaarvore.UniNodeImpl;

public class Principal {

	public Principal() {

		Conta contaAgua = new Conta(1, "Água", new BigDecimal(101.28));
		UniNode<Conta> noAgua = new UniNodeImpl<>(contaAgua);

		Conta contaAluguel = new Conta(2, "Aluguel", new BigDecimal(900.00));
		UniNode<Conta> noAlu = new UniNodeImpl<>(contaAluguel);

		Conta contaIntTel = new Conta(3, "Internet e Telefone", new BigDecimal(165.35));
		UniNode<Conta> noIntTel = new UniNodeImpl<>(contaIntTel);

		Conta contaEnElet = new Conta(4, "Energia Elétrica", new BigDecimal(252.58));
		UniNode<Conta> noEnElet = new UniNodeImpl<>(contaEnElet);

		Conta despesasAdm = new Conta(1, "Despesas Administrativas", new BigDecimal(0));
		UniNode<Conta> noDeAdm = new UniNodeImpl<>(despesasAdm);
		noDeAdm.addFilho(noAgua);
		noDeAdm.addFilho(noAlu);
		noDeAdm.addFilho(noIntTel);
		noDeAdm.addFilho(noEnElet);

		Conta despesasOper = new Conta(1, "Despesas Operacionais", new BigDecimal(0));
		UniNode<Conta> noDeOpr = new UniNodeImpl<>(despesasOper);
		noDeOpr.addFilho(noDeAdm);

		Conta contaBeneficios = new Conta(1, "Benefícios", new BigDecimal("500.35"));
		UniNode<Conta> noBeneficios = new UniNodeImpl<>(contaBeneficios);

		Conta contaEncargos = new Conta(2, "Encargos", new BigDecimal("241.78"));
		UniNode<Conta> noEncargos = new UniNodeImpl<>(contaEncargos);

		Conta contaSalarios = new Conta(3, "Salários", new BigDecimal("2580.12"));
		UniNode<Conta> noSalarios = new UniNodeImpl<>(contaSalarios);

		Conta gastoCPessoal = new Conta(2, "Gasto com Pessoal", new BigDecimal(0));
		UniNode<Conta> noGastoPessoal = new UniNodeImpl<>(gastoCPessoal);
		noGastoPessoal.addFilho(noBeneficios);
		noGastoPessoal.addFilho(noEncargos);
		noGastoPessoal.addFilho(noSalarios);

		noDeOpr.addFilho(noGastoPessoal);

		Conta contaLimpeza = new Conta(1, "Serviços de Limpeza", new BigDecimal("1250.55"));
		UniNode<Conta> noLimpeza = new UniNodeImpl<>(contaLimpeza);

		Conta contaManutencao = new Conta(2, "Serviços de Manutenção", new BigDecimal("987.90"));
		UniNode<Conta> noManutencao = new UniNodeImpl<>(contaManutencao);

		Conta manutencaoLimpeza = new Conta(3, "Manutenção e Limpeza", new BigDecimal(0));
		UniNode<Conta> noManutencaoLimpeza = new UniNodeImpl<>(manutencaoLimpeza);
		noManutencaoLimpeza.addFilho(noLimpeza);
		noManutencaoLimpeza.addFilho(noManutencao);

		noDeOpr.addFilho(noManutencaoLimpeza);

		Conta contaMatEs = new Conta(1, "Material de Escritorio", new BigDecimal("128.95"));
		UniNode<Conta> noMatEs = new UniNodeImpl<>(contaMatEs);

		Conta contaMatLim = new Conta(2, "Material de Limpeza", new BigDecimal("87.20"));
		UniNode<Conta> noMatLim = new UniNodeImpl<>(contaMatLim);

		Conta mat = new Conta(4, "Materiais", new BigDecimal(0));
		UniNode<Conta> noMat = new UniNodeImpl<>(mat);
		noMat.addFilho(noMatEs);
		noMat.addFilho(noMatLim);

		noDeOpr.addFilho(noMat);

		UniArvore<Conta> planoContas = new UniArvoreImpl(noDeOpr);
		
		somarFilhos(noDeOpr);

		/**
		 * Mostra todo o plano de contas, inclusive com tabulações (\t) a cada
		 * nível.
		 */
		planoContas.mostrarTodosConsole();

		// O exercício consiste em identificar a necessidade de
		// novos métodos para finalizar a tarefa, sempre lembrando
		// de coesão, acoplamento e encapsulamento.
		// Entrega link do repositório para fernandod@univel.br com assunto:
		// TrabalhoComplementar 3o Sem

	}

	/**
	 * Percorre toda a arvore, recursivamente, encontra todas as contas
	 * analíticas (isLeaf() == true), soma seus valores e atribui o total na
	 * conta pai.
	 * 
	 * Depois pega todos os pais e somam no avô.
	 * 
	 * @param planoContas
	 */
	private void somarFilhos(UniNode<Conta> noDeOpr) {
		List<UniNode<Conta>> conta = noDeOpr.getFilhos();
		for (int i = 0; i < conta.size(); i++) {
			BigDecimal valor = new BigDecimal(0);
			for (int j = 0; j < conta.get(i).getFilhos().size(); j++) {
				valor = valor.add(conta.get(i).getFilhos().get(j).getConteudo().getValor());
			}
			conta.get(i).getConteudo().setValor(valor);
			System.out.println(conta.get(i).getConteudo().getValor());
		}

	}

	public static void main(String[] args) {

		new Principal();

	}

}
