package br.com.sistema.redAmber.basicas.enums;

public enum StatusCurso {
	ATIVO {
		@Override
		public String toString() {
			return "Ativo";
		}
	},
	INATIVO {
		@Override
		public String toString() {
			return "Inativo";
		}
	}
}
