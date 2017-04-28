package br.univel.minhaarvore;

public class UniArvoreImpl<T> implements UniArvore<T> {

	class UniNode {

	}

	private T raiz;

	@Override
	public T getRaiz() {
		return this.raiz;
	}

	public UniArvoreImpl(T noRaiz) {
		this.raiz = noRaiz;
	}

	@Override
	public void mostrarTodosConsole() {
		// TODO Auto-generated method stub

	}

}
