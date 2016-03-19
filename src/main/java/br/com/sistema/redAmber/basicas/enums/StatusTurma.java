package br.com.sistema.redAmber.basicas.enums;

public enum StatusTurma {
	
	PENDENTE{
		@Override
		public String toString() {

			return "Pendente";
		}
	},
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
