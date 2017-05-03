package br.univel.minhaarvore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UniArvoreImpl<T> implements UniArvore<T> {

	private UniNode<T> raiz;

	@Override
	public UniNode<T> getRaiz() {
		return (UniNode<T>) raiz;
	}

	public UniArvoreImpl(UniNode<T> noRaiz) {
		this.raiz = noRaiz;
	}

	@Override
	public void mostrarTodosConsole() {
	}


}
