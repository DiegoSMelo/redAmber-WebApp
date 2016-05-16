package br.com.sistema.redAmber.basicas.enums;

public enum StatusTurma {
	PENDENTE {
		@Override
		public String toString() {
			return "Pendente";
		}
	},
	ATIVA {
		@Override
		public String toString() {
			return "Ativa";
		}
	},
	INATIVA {
		@Override
		public String toString() {
			return "Inativa";
		}
	}
}