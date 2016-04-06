package br.com.sistema.redAmber.basicas.enums;

public enum StatusGrade {
	
	ATIVO{
		@Override
		public String toString() {
			return "Ativo";
		}
	},
	
	INATIVO{
		@Override
		public String toString() {
			return "Inativo";
		}
	},
	
	PENDENTE{
		@Override
		public String toString() {
			return "Pendente";
		}
	}
	
}
