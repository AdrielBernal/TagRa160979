package br.univel.meustestes;

import java.math.BigDecimal;

import br.univel.minhaarvore.UniArvore;
import br.univel.minhaarvore.UniArvoreImpl;
import br.univel.minhaarvore.UniNode;
import br.univel.minhaarvore.UniNodeImpl;

public class Principal {

	public Principal() {

		Conta despesasOper = new Conta(1, "Despesas Operacionais", new BigDecimal(0));
		UniNode<Conta> noDeOpr = new UniNodeImpl<>(despesasOper);

		Conta contaAgua = new Conta(1, "Água", new BigDecimal("101.28"));
		UniNode<Conta> noAgua = new UniNodeImpl<>(contaAgua);

		Conta contaAluguel = new Conta(2, "Aluguel", new BigDecimal("900.00"));
		UniNode<Conta> noAlu = new UniNodeImpl<>(contaAluguel);

		Conta contaIntTel = new Conta(3, "Internet e Telefone", new BigDecimal("165.35"));
		UniNode<Conta> noIntTel = new UniNodeImpl<>(contaIntTel);

		Conta contaEnElet = new Conta(4, "Energia Elétrica", new BigDecimal("252.58"));
		UniNode<Conta> noEnElet = new UniNodeImpl<>(contaEnElet);

		Conta despesasAdm = new Conta(1, "Despesas Administrativas", new BigDecimal(0));
		UniNode<Conta> noDeAdm = new UniNodeImpl<>(despesasAdm);
		noDeAdm.addFilho(noAgua);
		noDeAdm.addFilho(noAlu);
		noDeAdm.addFilho(noIntTel);
		noDeAdm.addFilho(noEnElet);

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

		Conta contaLimpeza = new Conta(1, "Serviços de Limpeza", new BigDecimal("799.25"));
		UniNode<Conta> noLimpeza = new UniNodeImpl<>(contaLimpeza);

		Conta contaManutencao = new Conta(2, "Serviços de Manutenção", new BigDecimal("948.90"));
		UniNode<Conta> noManutencao = new UniNodeImpl<>(contaManutencao);

		Conta manutencaoLimpeza = new Conta(3, "Manutenção e Limpeza", new BigDecimal(0));
		UniNode<Conta> noManutencaoLimpeza = new UniNodeImpl<>(manutencaoLimpeza);
		noManutencaoLimpeza.addFilho(noLimpeza);
		noManutencaoLimpeza.addFilho(noManutencao);

		noDeOpr.addFilho(noManutencaoLimpeza);

		Conta contaMatEs = new Conta(1, "Material de Escritorio", new BigDecimal("1123.65"));
		UniNode<Conta> noMatEs = new UniNodeImpl<>(contaMatEs);

		Conta contaMatLim = new Conta(2, "Material de Limpeza", new BigDecimal("235.70"));
		UniNode<Conta> noMatLim = new UniNodeImpl<>(contaMatLim);

		Conta mat = new Conta(4, "Materiais", new BigDecimal(0));
		UniNode<Conta> noMat = new UniNodeImpl<>(mat);
		noMat.addFilho(noMatEs);
		noMat.addFilho(noMatLim);

		noDeOpr.addFilho(noMat);

		UniArvore<Conta> planoContas = new UniArvoreImpl(noDeOpr);
		noDeOpr.getConteudo().setValor(somarFilhos(noDeOpr));
		/**
		 * Mostra todo o plano de contas, inclusive com tabulações (\t) a cada
		 * nível.
		 */
		// UniNode<Conta> nodeteste = (UniNode<Conta>) planoContas.getRaiz();
		mostrarTodosConsole(noDeOpr);

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

	public BigDecimal somarFilhos(UniNode<Conta> node) {
		BigDecimal soma = node.getConteudo().getValor();
		if (!node.isLeaf()) {
			soma = node.getConteudo().getValor();
			for (UniNode<Conta> n : node.getFilhos()) {
				soma = soma.add(somarFilhos(n));
				if (n.getPai() != null) {
					n.getPai().getConteudo().setValor(n.getPai().getConteudo().getValor().add(soma));
				}
			}
		}
		return soma;
	}

	public void mostrarTodosConsole(UniNode<Conta> node) {
		StringBuilder sb = new StringBuilder();

		if (node != null) {

			sb.append(node.getConteudo().getId() + "." + node.getConteudo().getNome() + " R$"
					+ node.getConteudo().getValor() + "\n");

		}
		sb.append(printFilhos(node, node.getConteudo().getId()));

		System.out.println(sb.toString());
	}

	public String printFilhos(UniNode<Conta> node, Object id) {

		StringBuilder sb = new StringBuilder();
		if (node.isLeaf() == false) {
			for (UniNode<Conta> no : node.getFilhos()) {
				if (no.isLeaf()) {
					sb.append("\t");
				}
				sb.append(" " + no.getConteudo().getId() + "." + no.getConteudo().getNome() + " R$"
						+ no.getConteudo().getValor() + "\n");
				sb.append(printFilhos(no, id));
			}

		}
		return sb.toString();

	}

	public static void main(String[] args) {

		new Principal();

	}

}
