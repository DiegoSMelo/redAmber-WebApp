package br.com.sistema.redAmber.basicas.enums;

public enum StatusDisciplina {
	ATIVO {
		@Override
		public String toString() {

			return "ATIVO";
		}
	},
	INATIVO {
		@Override
		public String toString() {

			return "INATIVO";
		}
	}

}
